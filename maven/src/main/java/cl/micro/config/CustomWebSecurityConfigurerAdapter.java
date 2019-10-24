package cl.micro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


/*
 * Clase donde se especifican principales configuraciones de seguridad 
 */
@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
	
	//Para habilitar swagger
	private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };
    
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication()
	.withUser("user").password(passwordEncoder().encode("pass"))
	.authorities("ROLE_USER");
	}


   @Override
   protected void configure(HttpSecurity http) throws Exception {
	   
	   http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
       .cors().and()
       .csrf().disable()
       .authorizeRequests()
       .antMatchers("/").permitAll().and().authorizeRequests()
       .antMatchers("/console/**").permitAll()
       .antMatchers(AUTH_WHITELIST).permitAll() 
	    .antMatchers("/persons/**").permitAll()
	    .antMatchers("/securityNone").permitAll()
		.antMatchers("/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.authenticationEntryPoint(authenticationEntryPoint);
		
		http.addFilterAfter(new CustomFilter(),BasicAuthenticationFilter.class);;
	   
	
		}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}