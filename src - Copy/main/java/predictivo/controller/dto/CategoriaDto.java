package predictivo.controller.dto;

import com.google.gson.JsonObject;

public class CategoriaDto {

	private Integer id;
	private String nombre;

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

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("id", id);
		json.addProperty("nombre", nombre);
		return json;
	}
}
