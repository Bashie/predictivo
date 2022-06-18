package predictivo.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import predictivo.model.Usuario;

public class FraseUsadaDto {

	private Integer id;
	private Usuario usuario;
	private List<PictogramaDto> frase = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<PictogramaDto> getFrase() {
		return frase;
	}

	public void setFrase(List<PictogramaDto> frase) {
		this.frase = frase;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.addProperty("id", id);
		JsonArray data = new JsonArray();
		frase.stream().map(pictograma -> pictograma.toJson()).forEach(data::add);
		json.add("frase", data);
		json.addProperty("usuario", usuario.getId());
		return json;
	}
}
