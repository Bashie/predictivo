package predictivo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import predictivo.controller.dto.PictogramaDto;

@Entity
@Table(name = "Pictograma")
public class Pictograma implements Guardable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String imagen;
	@ManyToMany()
	@JoinTable(name = "PICTOGRAMA_CATEGORIA", joinColumns = {
			@JoinColumn(name = "PICTOGRAMA_ID") }, inverseJoinColumns = { @JoinColumn(name = "CATEGORIA_ID") })
	private Set<Categoria> categorias = new HashSet<Categoria>();
	private FuncionSintactica funcionSintactica;
	private Integer aarasacId;

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

	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(HashSet<Categoria> categorias) {
		this.categorias = categorias;
	}

	public FuncionSintactica getFuncionSintactica() {
		return funcionSintactica;
	}

	public void setFuncionSintactica(FuncionSintactica funcionSintactica) {
		this.funcionSintactica = funcionSintactica;
	}

	public Integer getAarasacId() {
		return aarasacId;
	}

	public void setAarasacId(Integer aarasacId) {
		this.aarasacId = aarasacId;
	}

	public PictogramaDto toDto() {
		PictogramaDto dto = new PictogramaDto();
		dto.setId(getId());
		dto.setImagen(getImagen());
		dto.setNombre(getNombre());
		dto.setFuncionSintactica(getFuncionSintactica().name());
		return dto;
	}

	@Override
	public String toString() {
		return "Pictograma [id=" + id + ", nombre=" + nombre + ", imagen=" + imagen + ", categorias=" + categorias
				+ ", funcionSintactica=" + funcionSintactica + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aarasacId == null) ? 0 : aarasacId.hashCode());
		result = prime * result + ((categorias == null) ? 0 : categorias.hashCode());
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
		Pictograma other = (Pictograma) obj;
		if (aarasacId == null) {
			if (other.aarasacId != null)
				return false;
		} else if (!aarasacId.equals(other.aarasacId))
			return false;
		if (categorias == null) {
			if (other.categorias != null)
				return false;
		} else if (!categorias.equals(other.categorias))
			return false;
		if (funcionSintactica != other.funcionSintactica)
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
