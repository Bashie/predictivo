package predictivo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import predictivo.controller.dto.CategoriaDto;

@Entity
@Table(name = "Categoria")
public class Categoria implements Guardable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String nombreIngles;
	
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

	public String getNombreIngles() {
		return nombreIngles;
	}

	public void setNombreIngles(String nombreIngles) {
		this.nombreIngles = nombreIngles;
	}

	public CategoriaDto toDto() {
		CategoriaDto dto = new CategoriaDto();
		dto.setId(getId());
		dto.setNombre(getNombre());
		return dto;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + "]";
	}

}
