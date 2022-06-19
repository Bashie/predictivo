package predictivo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import predictivo.controller.dto.PictogramaDto;
import predictivo.service.PrediccionService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PrediccionController {

	Logger logger = LoggerFactory.getLogger(PrediccionController.class);
	
	@Autowired
	private PrediccionService prediccionService;
	
	@RequestMapping("/prediccion")
	@ResponseBody
	public String buscarPrediccion(@RequestBody JsonNode payload) throws JsonMappingException, JsonProcessingException {
		System.out.println(payload.asText());
		JsonNode fraseUsada = payload.get("fraseUsada");
		Integer usuarioId = payload.get("usuarioId").intValue();
		System.out.println(fraseUsada);
		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, PictogramaDto.class);
		ArrayList<PictogramaDto> frase = mapper.readValue(fraseUsada.toString(), listType);
		prediccionService.getPredicciones(frase, usuarioId);
		return null;
	}
}
