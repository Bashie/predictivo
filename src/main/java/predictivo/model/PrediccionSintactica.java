package predictivo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PrediccionPictograma")
public class PrediccionSintactica implements Guardable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private FuncionSintactica tipoInicial;
	private FuncionSintactica proximoTipo;
	private Integer peso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FuncionSintactica getTipoInicial() {
		return tipoInicial;
	}

	public void setTipoInicial(FuncionSintactica tipoInicial) {
		this.tipoInicial = tipoInicial;
	}

	public FuncionSintactica getProximoTipo() {
		return proximoTipo;
	}

	public void setProximoTipo(FuncionSintactica proximoTipo) {
		this.proximoTipo = proximoTipo;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

}
