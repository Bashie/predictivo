package predictivo.service;

import static com.codeborne.selenide.Selenide.sleep;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import predictivo.controller.dto.PictogramaDto;
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

	public Integer cargarPictogramas(Integer from, Integer to) throws InterruptedException {

		System.out.println("From: " + from + " To: " + to);
		Integer total = 0;
		for (int i = from; i <= to; i++) {
			System.out.println("Cargando: " + i);
			total += cargarPictograma(i);
		}
		return total;
	}

	private Integer cargarPictograma(Integer pictogramaId) throws InterruptedException {
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

		pictograma.setImagen("https://static.arasaac.org/pictograms/" + pictogramaId + "/" + pictogramaId + "_300.png");

		JsonArray categorias = object.get("categories").getAsJsonArray();
		for (JsonElement jsonElement : categorias) {
			String nombreCategoria = jsonElement.getAsString();
			if (nombreCategoria.contains("-")) {
				nombreCategoria = nombreCategoria.split("-")[0];
			}
			Categoria categoria = categoriaDao.findByNameIngles(nombreCategoria);

			if (Objects.isNull(categoria)) {
				String nombreCategoriaEnEspaniol = traducir(nombreCategoria);
				if (nombreCategoriaEnEspaniol.isEmpty()) {
					continue;
				}

				categoria = new Categoria();
				categoria.setNombreIngles(nombreCategoria);
				categoria.setNombre(nombreCategoriaEnEspaniol);
				categoria = categoriaDao.save(categoria);
			}
			pictograma.getCategorias().add(categoria);
		}

		pictograma.setAarasacId(pictogramaId);
		System.out.println("pictograma : " + pictograma.toString());
		if (pictograma.getCategorias().isEmpty()) {
			return 0;
		}
		pictogramaDao.save(pictograma);
		return 1;
	}

	private String traducir(String nombreCategoria) throws InterruptedException {
		System.out.println("Traduciendo: " + nombreCategoria);
		String translation = "";

		WebDriver driver = ChromeDriverManager.getInstance(DriverManagerType.CHROME).create();
		driver.get("https://translate.google.com/?hl=en#view=home&op=translate&sl=en&tl=es");
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		sleep(1000);
		String[] strings = { nombreCategoria };
		try {
			for (String data : strings) {
				while (!isDisplayed(driver.findElement(By.xpath("//textarea[@class='er8xn']")))) {
					sleep(1000);
				}
				WebElement elementIn = driver.findElement(By.xpath("//textarea[@class='er8xn']"));
				elementIn.clear();
				elementIn.click();
				elementIn.sendKeys(data);
				sleep(1000);
				while (!isDisplayed(driver.findElement(By.xpath("//span[@class='Q4iAWc']")))) {
					sleep(1000);
				}
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@class='Q4iAWc']"))));
				translation = element.getText();
				System.out.println("translation: " + translation);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}
		return translation;
	}

	public static boolean isDisplayed(WebElement element) {
        try {
            if(element.isDisplayed())
                return element.isDisplayed();
            }catch (NoSuchElementException ex) {
            return false;
        }
        return false;
    }
	
	public List<PictogramaDto> getPictogramas(Integer categoria) {
		return pictogramaDao.findByCategoria(categoria).stream().map(Pictograma::toDto).collect(Collectors.toList());

	}
}
