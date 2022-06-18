package predictivo.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import predictivo.dao.CategoriaDao;
import predictivo.dao.PictogramaDao;
import predictivo.model.Categoria;
import predictivo.model.FuncionSintactica;
import predictivo.model.Pictograma;

@Component
public class PictogramaService {
	@Autowired
	private PictogramaDao pictogramaDao;
	@Autowired
	private CategoriaDao categoriaDao;

	public Integer cargarPictogramas(Integer from, Integer to) {
		System.out.println("From: " + from + " To: " + to);
		Integer total = 0;
		for (int i = from; i <= to; i++) {
			System.out.println("Cargando: " + i);
			total += cargarPictograma(i);
		}
		return total;
	}

	private Integer cargarPictograma(Integer pictogramaId) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> requestEntity = new HttpEntity(headers);
		RestTemplate restTemplate = new RestTemplate();
		URI uri;
		try {
			uri = new URI("https://api.arasaac.org/api/pictograms/es/" + pictogramaId);
		} catch (URISyntaxException e) {
			return 0;
		}
		ResponseEntity<String> response;
		try {
			response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);
		} catch (RestClientException e) {
			return 0;
		}
		System.out.println(response.getBody());
		
		if (!response.getStatusCode().is2xxSuccessful()) {
			return 0;
		}
		
	    JsonElement element = JsonParser.parseString(response.getBody());
		JsonObject object = element.getAsJsonObject();
	    
	    Pictograma pictograma = new Pictograma();

	    JsonObject palabrasClave = object.get("keywords").getAsJsonArray().get(0).getAsJsonObject();
		pictograma.setNombre(palabrasClave.get("keyword").getAsString());
		
		Pictograma pic = pictogramaDao.findByName(pictograma.getNombre());
		if (!Objects.isNull(pic)) {
			return 0;
		}
		
		JsonArray funciones = object.get("tags").getAsJsonArray();
		for (JsonElement funcion : funciones) {
			if (funcion.getAsString().contains("verb")) {
				pictograma.setFuncionSintactica(FuncionSintactica.VERVO);
				break;
			}
			if (funcion.getAsString().contains("adjective")) {
				pictograma.setFuncionSintactica(FuncionSintactica.ADJETIVO);
				break;
			}
			pictograma.setFuncionSintactica(FuncionSintactica.SUSTANTIVO);
		}
		
		pictograma.setImagen("https://static.arasaac.org/pictograms/"+pictogramaId+"/"+pictogramaId+"_300.png");
		
		JsonArray categorias = object.get("categories").getAsJsonArray();
		for (JsonElement jsonElement : categorias) {
			String nombreCategoria = jsonElement.getAsString();
			Categoria categoria = categoriaDao.findByName(nombreCategoria);
			if (Objects.isNull(categoria)) {
				categoria = new Categoria();
				categoria.setNombreIngles(nombreCategoria);
				categoria = categoriaDao.save(categoria);
			}
			pictograma.getCategorias().add(categoria);
		}

		System.out.println("pictograma : " + pictograma.toString());
		pictogramaDao.save(pictograma);
		return 1;
	}
	
	public List<Pictograma> getPictogramas(Integer categoria) {
		return pictogramaDao.findByCategoria(categoria);
		
	}
}
