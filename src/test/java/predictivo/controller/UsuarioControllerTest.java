package predictivo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import predictivo.service.UsuarioService;
import predictivo.service.exception.InvalidLoginException;

public class UsuarioControllerTest {
	@Spy
	@InjectMocks
	private UsuarioController usuarioController;
	@Mock 
	private UsuarioService usuarioService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void loginTest() throws JsonMappingException, JsonProcessingException, InvalidLoginException {
		String json = "{\"username\":\"mi@email.com\",\"password\":\"6NJFknbaKhr9P4WeOtTrzQ==\"}";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(json);
		doReturn("token").when(usuarioService).login(any(), any());
		
		String result = usuarioController.login(node);
		Assertions.assertEquals("token", result);
		
	}
	
	@Test
	public void loginTestException() throws JsonMappingException, JsonProcessingException, InvalidLoginException {
		String json = "{\"username\":\"mi@email.com\",\"password\":\"6NJFknbaKhr9P4WeOtTrzQ==\"}";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(json);
		doThrow(new InvalidLoginException("Clave inválida")).when(usuarioService).login(any(), any());
		
		String result = usuarioController.login(node);
		
		Assertions.assertEquals("{\"error\":\"Esto es un error\"}", result);
	}
	
}
