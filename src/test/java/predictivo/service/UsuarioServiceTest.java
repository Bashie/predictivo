package predictivo.service;

import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import predictivo.dao.UsuarioDao;
import predictivo.model.Usuario;
import predictivo.service.exception.InvalidLoginException;

public class UsuarioServiceTest {

	@Spy
	@InjectMocks
	private UsuarioService usuarioService;
	@Mock
	private UsuarioDao usuarioDao;
	private String usuario = "mi@mail.com";
	private String clave = "test";
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void loginTest() throws InvalidLoginException {
		doReturn(getUsuario()).when(usuarioDao).findByUsername(usuario);
		
		String resultado = usuarioService.login(usuario, clave);
		
		Assertions.assertTrue(resultado.startsWith("{\"email\":\"mi@mail.com\",\"nombre\":\"ana\",\"id\":null,\"token\":"));
		
		
	}
	
	private Usuario getUsuario() {
		Usuario user = new Usuario();
		user.setApellido("Alguien");
		user.setNombre("ana");
		user.setClaveEncriptada("6NJFknbaKhr9P4WeOtTrzQ==");
		user.setEmail(usuario);
		return user;		
	}
	
	
	
}
