package predictivo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import javax.persistence.NoResultException;

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

		Assertions.assertTrue(
				resultado.startsWith("{\"email\":\"mi@email.com\",\"nombre\":\"MiNombre\",\"usuarioId\":2,\"token\":"));
	}

	@Test
	public void registerLongitudTest() throws InvalidLoginException {
		String clave = "test";
		doThrow(new NoResultException()).when(usuarioDao).findByUsername(UnitTestHelper.USUARIO_EMAIL);

		Throwable exception = assertThrows(InvalidLoginException.class,
				() -> usuarioService.register(UnitTestHelper.USUARIO_EMAIL, clave, UnitTestHelper.USUARIO_NOMBRE,
						UnitTestHelper.USUARIO_APELLIDO));
		assertEquals("Password Inválida: longitud menor a 12", exception.getMessage());
	}

	@Test
	public void registerSinMayusculaTest() throws InvalidLoginException {
		String clave = "testtesttest";
		doThrow(new NoResultException()).when(usuarioDao).findByUsername(UnitTestHelper.USUARIO_EMAIL);

		Throwable exception = assertThrows(InvalidLoginException.class,
				() -> usuarioService.register(UnitTestHelper.USUARIO_EMAIL, clave, UnitTestHelper.USUARIO_NOMBRE,
						UnitTestHelper.USUARIO_APELLIDO));
		assertEquals("Password Inválida: no contiene mayúscula", exception.getMessage());
	}

	@Test
	public void registerSinMinusculaTest() throws InvalidLoginException {
		String clave = "TESTTESTTEST";
		doThrow(new NoResultException()).when(usuarioDao).findByUsername(UnitTestHelper.USUARIO_EMAIL);

		Throwable exception = assertThrows(InvalidLoginException.class,
				() -> usuarioService.register(UnitTestHelper.USUARIO_EMAIL, clave, UnitTestHelper.USUARIO_NOMBRE,
						UnitTestHelper.USUARIO_APELLIDO));
		assertEquals("Password Inválida: no contiene minúscula", exception.getMessage());
	}

	@Test
	public void registerSinDigitosTest() throws InvalidLoginException {
		String clave = "TE1STTESTtest";
		doThrow(new NoResultException()).when(usuarioDao).findByUsername(UnitTestHelper.USUARIO_EMAIL);

		Throwable exception = assertThrows(InvalidLoginException.class,
				() -> usuarioService.register(UnitTestHelper.USUARIO_EMAIL, clave, UnitTestHelper.USUARIO_NOMBRE,
						UnitTestHelper.USUARIO_APELLIDO));
		assertEquals("Password Inválida: no posee al menos 3 dígitos", exception.getMessage());
	}

	@Test
	public void registerDigitosConsecutivosTest() throws InvalidLoginException {
		String clave = "TE123STTESTtest";
		doThrow(new NoResultException()).when(usuarioDao).findByUsername(UnitTestHelper.USUARIO_EMAIL);

		Throwable exception = assertThrows(InvalidLoginException.class,
				() -> usuarioService.register(UnitTestHelper.USUARIO_EMAIL, clave, UnitTestHelper.USUARIO_NOMBRE,
						UnitTestHelper.USUARIO_APELLIDO));
		assertEquals("Password Inválida: posee caracteres consecutivos", exception.getMessage());
	}

	@Test
	public void registerEspecialesTest() throws InvalidLoginException {
		String clave = "TE13STTE1STtest";
		doThrow(new NoResultException()).when(usuarioDao).findByUsername(UnitTestHelper.USUARIO_EMAIL);

		Throwable exception = assertThrows(InvalidLoginException.class,
				() -> usuarioService.register(UnitTestHelper.USUARIO_EMAIL, clave, UnitTestHelper.USUARIO_NOMBRE,
						UnitTestHelper.USUARIO_APELLIDO));
		assertEquals("Password Inválida: no posee caracteres especiales", exception.getMessage());
	}

	@Test
	public void registerOkTest() throws InvalidLoginException {
		String clave = "TE13ST*TE1STtest";
		doReturn(UnitTestHelper.getUsuario()).when(usuarioDao).save(any());
		doThrow(new NoResultException()).when(usuarioDao).findByUsername(UnitTestHelper.USUARIO_EMAIL);

		String resultado = usuarioService.register(UnitTestHelper.USUARIO_EMAIL, clave, UnitTestHelper.USUARIO_NOMBRE,
				UnitTestHelper.USUARIO_APELLIDO);
		Assertions.assertTrue(
				resultado.startsWith("{\"email\":\"mi@email.com\",\"nombre\":\"MiNombre\",\"usuarioId\":2,\"token\":"));
	}
}
