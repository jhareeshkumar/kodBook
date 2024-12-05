package com.kodbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MyWebSecurityConfiguration {

    @Bean
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
	
//	http.csrf(csrf->csrf.disable());
	http.cors(cors->cors.disable());
		
		
	http.authorizeHttpRequests(auth->auth
		.requestMatchers("/login","/openSignUp","/signUp","/css/**")
		.permitAll()
		.anyRequest().hasRole("USER")
		)
		.formLogin(form->form
			.loginPage("/login")
//			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/home", true)
			.permitAll()
			)
		.sessionManagement(session->session
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
			.sessionFixation(sessinonFixation->sessinonFixation.migrateSession())
			.invalidSessionUrl("/login")
			.maximumSessions(1)
			.maxSessionsPreventsLogin(true)
			.expiredUrl("/login")
			)
		.logout(logout->logout
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.deleteCookies("JSESSIONID")
			.permitAll()
			);
	return http.build();
    }
    
    
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//	return config.getAuthenticationManager();
//    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }
}