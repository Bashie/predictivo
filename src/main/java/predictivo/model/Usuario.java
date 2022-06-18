package predictivo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import predictivo.controller.dto.UsuarioDto;

@Entity
@Table(name = "Usuario")
public class Usuario implements Guardable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private String claveEncriptada;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClaveEncriptada() {
		return claveEncriptada;
	}

	public void setClaveEncriptada(String claveEncriptada) {
		this.claveEncriptada = claveEncriptada;
	}

	public UsuarioDto toDto() {
		UsuarioDto dto = new UsuarioDto();
		dto.setId(getId());
		dto.setApellido(getApellido());
		dto.setNombre(getNombre());
		dto.setEmail(getEmail());
		return dto;
	}

}
