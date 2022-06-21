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
import predictivo.service.exception.InvalidLoginException;
import predictivo.util.UnitTestHelper;

public class UsuarioServiceTest {

	@Spy
	@InjectMocks
	private UsuarioService usuarioService;
	@Mock
	private UsuarioDao usuarioDao;
	private String clave = "test";
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void loginTest() throws InvalidLoginException {
		doReturn(UnitTestHelper.getUsuario()).when(usuarioDao).findByUsername(UnitTestHelper.USUARIO_EMAIL);
		
		String resultado = usuarioService.login(UnitTestHelper.USUARIO_EMAIL, clave);
		
		Assertions.assertTrue(resultado.startsWith("{\"email\":\"mi@email.com\",\"nombre\":\"MiNombre\",\"usuarioId\":2,\"token\":"));
	}

	
	
	
}
