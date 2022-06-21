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

	@Override
	public String toString() {
		return "PictogramaDto [id=" + id + ", nombre=" + nombre + ", imagen=" + imagen + ", categoria=" + categoria
				+ ", funcionSintactica=" + funcionSintactica + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((funcionSintactica == null) ? 0 : funcionSintactica.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PictogramaDto other = (PictogramaDto) obj;
		if (funcionSintactica == null) {
			if (other.funcionSintactica != null)
				return false;
		} else if (!funcionSintactica.equals(other.funcionSintactica))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
}
