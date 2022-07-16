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
import javax.persistence.Transient;

import predictivo.controller.dto.PrediccionPictogramaDto;

@Entity
@Table(name = "PrediccionPictograma")
public class PrediccionPictograma implements Guardable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
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

	public PrediccionPictogramaDto toDto() {
		PrediccionPictogramaDto dto = new PrediccionPictogramaDto();
		dto.setId(getId());
		dto.setPeso(getPeso());
		return dto;
	}

	public static PrediccionPictograma fromFraseUsadaDw(Integer peso, String pictoIds) {
		PrediccionPictograma prediccionPictograma = new PrediccionPictograma();
		prediccionPictograma.setPeso(peso);
		prediccionPictograma.setPictogramaIds(pictoIds);
		return prediccionPictograma;
	}

	@Override
	public String toString() {
		return "PrediccionPictograma [id=" + id + ", pictogramaIds=" + pictogramaIds + ", peso=" + peso + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
		result = prime * result + ((pictogramaIds == null) ? 0 : pictogramaIds.hashCode());
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
		if (pictogramaIds == null) {
			if (other.pictogramaIds != null)
				return false;
		} else if (!pictogramaIds.equals(other.pictogramaIds))
			return false;
		return true;
	}
	
	
}
