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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fraseInicial == null) ? 0 : fraseInicial.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
		result = prime * result + ((proximoPictograma == null) ? 0 : proximoPictograma.hashCode());
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
		PrediccionPictograma other = (PrediccionPictograma) obj;
		if (fraseInicial == null) {
			if (other.fraseInicial != null)
				return false;
		} else if (!fraseInicial.equals(other.fraseInicial))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (peso == null) {
			if (other.peso != null)
				return false;
		} else if (!peso.equals(other.peso))
			return false;
		if (proximoPictograma == null) {
			if (other.proximoPictograma != null)
				return false;
		} else if (!proximoPictograma.equals(other.proximoPictograma))
			return false;
		return true;
	}
	
	
}
