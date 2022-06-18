package predictivo.controller.dto;

import com.google.gson.JsonObject;

public class PictogramaDto {

	private Integer id;
	private String nombre;
	private String imagen;
	private CategoriaDto categoria;
	private String funcionSintactica;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public CategoriaDto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDto categoria) {
		this.categoria = categoria;
	}

	public String getFuncionSintactica() {
		return funcionSintactica;
	}

	public void setFuncionSintactica(String funcionSintactica) {
		this.funcionSintactica = funcionSintactica;
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("nombre", nombre);
		json.addProperty("imagen", imagen);
		json.addProperty("id", id);
		json.addProperty("categoria", categoria.getId());
		json.addProperty("funcionSintactica", funcionSintactica);
		return json;
	}
}
