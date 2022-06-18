package predictivo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@SpringBootApplication
public class SipeApp {
	
	public static void main(String[] args) {
		SpringApplication.run(SipeApp.class, args);

	}  
	
	@EnableWebSecurity
   	class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   		@Override
   		protected void configure(HttpSecurity http) throws Exception {
   			http.csrf().disable()
   				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
   				.authorizeRequests()
   				.antMatchers("/**").permitAll()
   				.anyRequest().authenticated().and()
   				.cors();
   		}
   	}
	
}
