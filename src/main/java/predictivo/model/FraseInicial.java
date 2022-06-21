package predictivo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FraseInicial")
public class FraseInicial implements Guardable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer orden;
	@ManyToOne()
	private Pictograma pictograma;
	@ManyToOne()
	private PrediccionPictograma prediccionPictograma;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Pictograma getPictograma() {
		return pictograma;
	}

	public void setPictograma(Pictograma pictograma) {
		this.pictograma = pictograma;
	}

	public PrediccionPictograma getPrediccionPictograma() {
		return prediccionPictograma;
	}

	public void setPrediccionPictograma(PrediccionPictograma prediccionPictograma) {
		this.prediccionPictograma = prediccionPictograma;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FraseInicial other = (FraseInicial) obj;
		if (id.equals(other.id)) {
			return true;
		}
		if (orden == null) {
			if (other.orden != null) {
				return false;
			}
		} else if (!orden.equals(other.orden)) {
			return false;
		}
		if (pictograma == null) {
			if (other.pictograma != null) {
				return false;
			}
		} else if (!pictograma.equals(other.pictograma)) {
			return false;
		}
		return true;
	}

}
