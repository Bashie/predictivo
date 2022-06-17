package predictivo.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PrediccionPictogramaDto {

	private Integer id;
	private List<PictogramaDto> fraseInicial = new ArrayList<>();
	private PictogramaDto proximoPictograma;
	private Integer peso;
	private UsuarioDto usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<PictogramaDto> getFraseInicial() {
		return fraseInicial;
	}

	public void setFraseInicial(List<PictogramaDto> fraseInicial) {
		this.fraseInicial = fraseInicial;
	}

	public PictogramaDto getProximoPictograma() {
		return proximoPictograma;
	}

	public void setProximoPictograma(PictogramaDto proximoPictograma) {
		this.proximoPictograma = proximoPictograma;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public JsonObject toJson() {
		JsonObject json = new JsonObject();
		json.add("proximoPictograma", proximoPictograma.toJson());
		json.addProperty("id", id);
		JsonArray data = new JsonArray();
		fraseInicial.stream().map(pictograma -> pictograma.toJson()).forEach(data::add);
		json.add("fraseInicial", data);
		json.addProperty("id", id);
		json.addProperty("peso", peso);
		json.addProperty("usuario", usuario.getId());
		return json;
	}
}
