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

import predictivo.controller.dto.FraseUsadaDto;

@Entity
@Table(name = "FraseUsada")
public class FraseUsada implements Guardable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne()
	private Usuario usuario;
	@OneToMany(mappedBy="fraseUsada")
	@OrderBy("orden")
	private List<FraseUsadaOrden> frase = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<FraseUsadaOrden> getFrase() {
		return frase;
	}

	public void setFrase(List<FraseUsadaOrden> frase) {
		this.frase = frase;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public FraseUsadaDto toDto() {
		FraseUsadaDto dto = new FraseUsadaDto();
		dto.setId(getId());
		dto.setUsuario(getUsuario());
		dto.setFrase(getFrase().stream().map(orden -> orden.getPictograma().toDto()).collect(Collectors.toList()));
		return dto;
	}

	public void addFrase(List<Pictograma> fraseUsada) {
		Integer i = 0;
		for (Pictograma pictograma : fraseUsada) {
			frase.add(new FraseUsadaOrden(i, pictograma, this));
		}
	}
}
