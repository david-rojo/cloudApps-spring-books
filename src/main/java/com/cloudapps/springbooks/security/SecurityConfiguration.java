package com.cloudapps.springbooks.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.cloudapps.springbooks.services.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImpl userDetailsService;
	
	public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        
        //http.authorizeRequests().antMatchers("/logout").permitAll();
        //http.authorizeRequests().antMatchers("/api/v1/books/**").permitAll();
        
		/*
		 * 1. Se desactiva el uso de cookies
		 * 2. Se activa la configuración CORS con los valores por defecto
		 * 3. Se desactiva el filtro CSRF
		 * 4. Se indica que el login no requiere autenticación
		 * 5. Se indica que el resto de URLs esten securizadas
		 */
		//httpSecurity.authorizeRequests().antMatchers("/").permitAll();
		//httpSecurity.csrf().disable();
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.cors().and().csrf().disable()
			//.authorizeRequests().antMatchers(HttpMethod.POST, Constants.LOGIN_URL).permitAll()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, Constants.LOGIN_URL).permitAll()
				.antMatchers(HttpMethod.GET, "/api/v1/books/").permitAll()
				.antMatchers(HttpMethod.POST, "/api/v1/users/").permitAll()
				.antMatchers("/springbooks-manager-api.html").permitAll()
				.antMatchers("/swagger-ui/**").permitAll()
			.anyRequest().authenticated().and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()));

    }
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Se define la clase que recupera los usuarios y el algoritmo para procesar las passwords
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}
