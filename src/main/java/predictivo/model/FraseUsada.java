package predictivo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import predictivo.controller.dto.FraseUsadaDto;

@Entity
@Table(name = "FraseUsada")
public class FraseUsada implements Guardable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne()
	private Usuario usuario;
	private String pictogramaIds = "";
	private Integer peso = 0;
	@Transient
	private List<Pictograma> pictogramas = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPictogramaIds() {
		return pictogramaIds;
	}

	public void setPictogramaIds(String pictogramaIds) {
		this.pictogramaIds = pictogramaIds;
	}

	public List<Pictograma> getPictogramas() {
		return pictogramas;
	}

	public void setPictogramas(List<Pictograma> pictogramas) {
		this.pictogramas = pictogramas;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public FraseUsadaDto toDto() {
		FraseUsadaDto dto = new FraseUsadaDto();
		dto.setId(getId());
		dto.setUsuario(getUsuario());
		// TODO actualizar aca
		// dto.setFrase(getFrase().stream().map(orden ->
		// orden.getPictograma().toDto()).collect(Collectors.toList()));
		return dto;
	}

	public void addFrase(List<Pictograma> fraseUsada) {
		for (Pictograma pictograma : fraseUsada) {
			pictogramaIds += pictograma.getId() + ",";
		}
	}

	@Override
	public String toString() {
		return "FraseUsada [id=" + id + ", usuario=" + usuario + ", pictogramaIds=" + pictogramaIds + ", peso=" + peso + "]";
	}

	public void addUso() {
		this.peso++;
	}

}
