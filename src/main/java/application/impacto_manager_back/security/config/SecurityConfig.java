package application.impacto_manager_back.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private String[] rotasPublicas() {
		return new String[] {
			"/login/**",
			"/user/register/**",
			"/v3/api-docs/**"
		};
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		configureAuthorization(http);
		configureSessionManagement(http);
		
		return http.build();
	}
	
	private void configureAuthorization(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(
				auth ->
					auth.requestMatchers(rotasPublicas())
						.permitAll()
						.anyRequest().authenticated()
			)
			.httpBasic(
				Customizer.withDefaults())
			.oauth2ResourceServer(
				conf -> conf.jwt(Customizer.withDefaults()));
	}
	
	private void configureSessionManagement(HttpSecurity http) throws Exception {
		http.sessionManagement(sessions ->
			sessions.maximumSessions(1)
		);
	}
}

