package application.impacto_manager_back.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(Customizer.withDefaults())
			.oauth2ResourceServer(
				conf -> conf.jwt(Customizer.withDefaults()));
		
		configureAuthorization(http);
		
		configureSessionManagement(http);
		
		return http.build();
	}
	
	private void configureAuthorization(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
			.requestMatchers(
				"/user/login/**",
				"/v3/api-docs/**",
				"/swagger-ui/**",
				"/user/register"
			).permitAll()
			//Rotas protegidas por Role
			.requestMatchers("/user/**").hasAnyRole("ROOT", "ADMIN")
			.anyRequest().authenticated()
		)
		;
	}
	
	@Bean
	static RoleHierarchy roleHierarchy() {
		return RoleHierarchyImpl.withDefaultRolePrefix()
			.role("ROOT").implies("ADMIN")
			.role("ADMIN").implies("USER")
			.build();
	}
	
	@Bean
	static MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {
		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setRoleHierarchy(roleHierarchy);
		return expressionHandler;
	}
	
	private void configureSessionManagement(HttpSecurity http) throws Exception {
		http.sessionManagement(sessions ->
			sessions.maximumSessions(1)
		);
	}
}

