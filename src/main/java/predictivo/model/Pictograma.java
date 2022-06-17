package predictivo.model;

import java.util.ArrayList;
import java.util.List;

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
	private List<Categoria> categorias = new ArrayList<Categoria>();
	private FuncionSintactica funcionSintactica;

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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public FuncionSintactica getFuncionSintactica() {
		return funcionSintactica;
	}

	public void setFuncionSintactica(FuncionSintactica funcionSintactica) {
		this.funcionSintactica = funcionSintactica;
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

}
