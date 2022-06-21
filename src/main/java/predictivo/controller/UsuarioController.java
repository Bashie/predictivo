package predictivo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;

import predictivo.service.UsuarioService;
import predictivo.service.exception.InvalidLoginException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

	Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/users/authenticate")
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public String login(@RequestBody JsonNode payload) {
		logger.info("Login usuario: " + payload.get("username").textValue());
		try {
			return usuarioService.login(payload.get("username").textValue(), payload.get("password").textValue());
		} catch (InvalidLoginException e) {
			JsonObject json = new JsonObject();
			json.addProperty("error", "Esto es un error");
			return json.toString();
		}
	}
	
	@RequestMapping("/users/register")
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public String register(@RequestBody JsonNode payload) {
		logger.info("Login usuario: " + payload.get("username").textValue());
		try {
			return usuarioService.register(payload.get("username").textValue(), payload.get("password").textValue(), payload.get("nombre").textValue(), payload.get("apellido").textValue());
		} catch (InvalidLoginException e) {
			JsonObject json = new JsonObject();
			json.addProperty("error", e.getMessage());
			return json.toString();
		}
	}
}
