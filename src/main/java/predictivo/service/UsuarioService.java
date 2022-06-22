package predictivo.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import predictivo.dao.UsuarioDao;
import predictivo.model.Usuario;
import predictivo.service.exception.InvalidLoginException;
import predictivo.util.Cryptor;

@Component
public class UsuarioService {
	@Autowired
	private UsuarioDao usuarioDao;

	public Usuario findById(Integer id) {
		return usuarioDao.findById(id);
	}

	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	public String login(String username, String password) throws InvalidLoginException {
		Usuario usuario;
		try {
			usuario = this.findByUsername(username);
		} catch (NoResultException e) {
			throw new InvalidLoginException("Usuario Inexistente");
		}
		if (Objects.nonNull(usuario) && (Cryptor.pText.equals(Cryptor.decryptWithCipher(password, usuario.getClaveEncriptada())))) {
			return createToken(usuario);
		}
		throw new InvalidLoginException("Clave Inválida");
	}

	private String createToken(Usuario usuario) {
		JsonObject json = new JsonObject();
		json.addProperty("email", usuario.getEmail());
		json.addProperty("nombre", usuario.getNombre());
		json.addProperty("usuarioId", usuario.getId());
		json.addProperty("token", UsuarioService.getJWTToken(String.valueOf(usuario.getId())));
		return json.toString();
	}

	public static String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return token;
	}

	public String getDatos(String id) {
		Usuario usuario = this.findById(Integer.valueOf(id));
		return usuario.toDto().toJson().toString();
	}

	public String register(String username, String password, String nombre, String apellido)
			throws InvalidLoginException {
		Usuario usuario;
		try {
			usuario = this.findByUsername(username);
			throw new InvalidLoginException("Usuario existente");
		} catch (NoResultException e) {
			// no hace nada, el usuario no existe y tengo que crearlo
		}
		String error = "";
		if (!(error = validarPassword(password)).isEmpty()) {
			throw new InvalidLoginException("Password Inválida: " + error);
		}
		usuario = new Usuario();
		usuario.setApellido(apellido);
		usuario.setEmail(username);
		usuario.setNombre(nombre);
		usuario.setClaveEncriptada(Cryptor.cryptWithCipher(password));

		usuario = usuarioDao.save(usuario);

		return createToken(usuario);
	}

	private String validarPassword(String password) {
		if (password.length() < 12) {
			return "longitud menor a 12";
		}
		if (password.toLowerCase().equals(password)) {
			return "no contiene mayúscula";
		}
		if (password.toUpperCase().equals(password)) {
			return "no contiene minúscula";
		}
		Integer digits = 0;
		char[] chars = password.toCharArray();
		for (Integer i = 0; i < chars.length - 1; i++) {
			if (Character.isDigit(chars[i])) {
				digits++;
			}
			if (checkearConsecutivos(chars, i)) {
				return "posee caracteres consecutivos";
			}
		}
		if (digits < 3) {
			return "no posee al menos 3 dígitos";
		}
		if (!checkearCaracteresEspeciales(password)) {
			return "no posee caracteres especiales";
		}
		return "";
	}

	private Boolean checkearCaracteresEspeciales(String password) {
		Pattern especial = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
		Matcher tieneEspecial = especial.matcher(password);
		return tieneEspecial.find();
	}

	private Boolean checkearConsecutivos(char[] chars, Integer i) {
		if (i < chars.length - 2) {
			return (chars[i] + 1 == chars[i + 1]) && (chars[i] + 2 == chars[i + 2]);
		}
		return false;
	}
}
