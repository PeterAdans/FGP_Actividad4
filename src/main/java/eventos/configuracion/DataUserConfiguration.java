package eventos.configuracion;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class DataUserConfiguration{
	
	@Bean

	UserDetailsManager usersCustom(DataSource dataSource) {

	JdbcUserDetailsManager users = 
			new JdbcUserDetailsManager(dataSource); 
	users.setUsersByUsernameQuery("select username,password,enabled from Usuarios u where username=?"); 
	users.setAuthoritiesByUsernameQuery("select u.username,p.nombre from Usuario_Perfiles up " +
	 "inner join usuarios u on u.username = up.username " +
			"inner join perfiles p on p.id_perfil = up.id_perfil " +
			"where u.username = ?");

	return users;

	}
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.csrf(csrf -> csrf.disable());
		// Los recursos estáticos no requieren autenticación
		http.authorizeHttpRequests(authorize -> authorize
			.requestMatchers("static/**", "/css/**", "/imagenes/**").permitAll()
			// Las vistas públicas no requieren autenticación
			.requestMatchers("/login-error", "/registro","/", "/home","/login", "/logout", "/inicioSesion", "/public/**").permitAll()
	//		.requestMatchers("/rest/encriptar/**").permitAll()
			// Todas las demás URLs de la Aplicación requieren autenticación
			// Asignar permisos a URLs por ROLES
	 		.requestMatchers("/eventos/alta/**").hasAnyAuthority("ROLE_ADMON")
			.requestMatchers("/app/usuarios/**").hasAnyAuthority("ROLE_ADMON")
			.requestMatchers("/app/perfiles/**").hasAnyAuthority("ROLE_CLIENTE")
			.requestMatchers("/app/tipos/**").hasAnyAuthority("ROLE_ADMON") 
			.anyRequest().authenticated()
			)
		// El formulario de Login no requiere autenticacion
		.formLogin(form -> form
		  //	.loginPage("/login")
			.defaultSuccessUrl("/inicioSesion")
			.failureUrl("/login-error")
			.permitAll()
			)
			.logout(logout -> logout
			//    .logoutUrl("/logout") // Define la URL para realizar el logout
			// .logoutSuccessUrl("/login") // URL a la que se redirige después de cerrar sesión
			.permitAll() // Permite acceso público a la acción de logout
						    
		);
				
		return http.build();
	}
	
/*	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	*/
	/*
	@Bean
	public InMemoryUserDetailsManager usersdetails() throws Exception{
		List<UserDetails> users=List.of(
				User
				.withUsername("user1")
				//.password("$2a$12$YUq1fO2Vbz.ONbIo./xmBeGCYFr5m4OLNC8H9HFafn4fpcOnUbqda")
				.password("{noop}user1")
				.roles("USERS")
				.build(),
				User
				.withUsername("user2")
				.password("{noop}user2")
				.roles("OPERATOR")
				.build(),
				User
				.withUsername("admin")
				.password("{noop}admin")
				.roles("USERS","ADMIN")
				.build());		
		return new InMemoryUserDetailsManager(users);					
	}
*/
	
	

}
