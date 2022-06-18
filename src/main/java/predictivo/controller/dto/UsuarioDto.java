package predictivo.controller.dto;

import com.google.gson.JsonObject;

public class UsuarioDto {

	private String login;
	private String claveEncriptada;
	private String nombre;
	private String apellido;
	private Integer id;
	private String email;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClaveEncriptada() {
		return claveEncriptada;
	}

	public void setClaveEncriptada(String claveEncriptada) {
		this.claveEncriptada = claveEncriptada;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("nombre", nombre);
		json.addProperty("apellido", apellido);
		json.addProperty("id", id);
		json.addProperty("email", email);
		return json;
	}
}
