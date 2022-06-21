package predictivo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import predictivo.model.FraseUsada;
import predictivo.model.FuncionSintactica;
import predictivo.model.Pictograma;
import predictivo.model.Usuario;

public class UnitTestHelper {

	private static final DateTimeFormatter formatter = DateTimeFormatter
			.ofPattern("EEE MMM dd yyyy HH:mm:ss 'GMT-0300 (Argentina Standard Time)'");

	public static final String INICIO = LocalDateTime.now().format(formatter);
	public static final String FIN = LocalDateTime.now().format(formatter);
	public static final String CATEGORIA_NOMBRE = "Verbo";
	public static final String PICTOGRAMAS_ID = "1,2,3";
	public static final Integer FRASEUSADA_ID = 1;
	public static final Integer FRASEUSADA_PESO = 5;
	public static final String USUARIO_APELLIDO = "MiApellido";
	public static final String USUARIO_NOMBRE = "MiNombre";
	public static final String USUARIO_CLAVE_ENCRIPTADA = "6NJFknbaKhr9P4WeOtTrzQ==";
	public static final String USUARIO_EMAIL = "mi@email.com";
	private static final Integer USUARIO_ID = 2;
	public static final Integer AARASAC_ID = 123;
	private static final Integer PICTOGRAMA_ID = 3;
	private static final String PICTOGRAMA_IMG = "imgLink";
	private static final String PICTOGRAMA_NOMBRE = "pictograma";
	
	
	public static FraseUsada getFraseUsada() {
		FraseUsada frase = new FraseUsada();
		frase.setId(FRASEUSADA_ID);
		frase.setPeso(FRASEUSADA_PESO);
		frase.setPictogramaIds(PICTOGRAMAS_ID);
		frase.setUsuario(getUsuario());
		return frase;
	}
	
	public static Usuario getUsuario() {
		Usuario user = new Usuario();
		user.setApellido(USUARIO_APELLIDO);
		user.setNombre(USUARIO_NOMBRE);
		user.setClaveEncriptada(USUARIO_CLAVE_ENCRIPTADA);
		user.setEmail(USUARIO_EMAIL);
		user.setId(USUARIO_ID);
		return user;
	}
	
	public static Pictograma getPictograma() {
		Pictograma picto = new Pictograma();
		picto.setAarasacId(AARASAC_ID);
		picto.setFuncionSintactica(FuncionSintactica.SUSTANTIVO);
		picto.setId(PICTOGRAMA_ID);
		picto.setImagen(PICTOGRAMA_IMG);
		picto.setNombre(PICTOGRAMA_NOMBRE);
		return picto;
	}
}
