package predictivo.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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
			throw new InvalidLoginException();
		}
		if (Objects.nonNull(usuario) && usuario.getClaveEncriptada().equals(Cryptor.cryptWithCipher(password))) {
			return createToken(usuario);
		}
		throw new InvalidLoginException();
	}
	
	private String createToken(Usuario usuario) {
		JsonObject json = new JsonObject();
		json.addProperty("email", usuario.getEmail());
		json.addProperty("nombre", usuario.getNombre());
		json.addProperty("id", usuario.getId());
		json.addProperty("token", UsuarioService.getJWTToken(String.valueOf(usuario.getId())));
		return json.toString();
	}
	
	public static String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return token;
	}

	public String getDatos(String id) {
		Usuario usuario = this.findById(Integer.valueOf(id));
		return usuario.toDto().toJson().toString();
	}
}
