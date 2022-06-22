package predictivo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("fraseusada")
public class FraseUsadaDw {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String pictogramaIds;
	private Integer peso;

	public FraseUsadaDw(String pictogramaIds, Integer peso) {
		this.pictogramaIds = pictogramaIds;
		this.peso = peso;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPictogramaIds() {
		return pictogramaIds;
	}

	public void setPictogramaIds(String pictogramaIds) {
		this.pictogramaIds = pictogramaIds;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

}
