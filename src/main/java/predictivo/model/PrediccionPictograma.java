package predictivo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import predictivo.controller.dto.PrediccionPictogramaDto;

@Entity
@Table(name = "PrediccionPictograma")
public class PrediccionPictograma implements Guardable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToMany(mappedBy="prediccionPictograma")
	@OrderBy("orden")
	private List<FraseInicial> fraseInicial = new ArrayList<>();
	@ManyToOne()
	private Pictograma proximoPictograma;
	private Integer peso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<FraseInicial> getFraseInicial() {
		return fraseInicial;
	}

	public void setFraseInicial(List<FraseInicial> fraseInicial) {
		this.fraseInicial = fraseInicial;
	}

	public Pictograma getProximoPictograma() {
		return proximoPictograma;
	}

	public void setProximoPictograma(Pictograma proximoPictograma) {
		this.proximoPictograma = proximoPictograma;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public PrediccionPictogramaDto toDto() {
		PrediccionPictogramaDto dto = new PrediccionPictogramaDto();
		dto.setId(getId());
		dto.setProximoPictograma(getProximoPictograma().toDto());
		dto.setFraseInicial(
				getFraseInicial().stream().map(frase -> frase.getPictograma().toDto()).collect(Collectors.toList()));
		dto.setPeso(getPeso());
		return dto;
	}
}
