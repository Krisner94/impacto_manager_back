package application.impacto_manager_back.security.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.InputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.public.key}")
    private InputStream publicKeyPem;

    @Value("${jwt.private.key}")
    private InputStream privateKeyPem;

    @Bean
    public RSAPublicKey publicKey() {
        return RsaKeyConverters.x509().convert(publicKeyPem);
    }

    @Bean
    public RSAPrivateKey privateKey() {
        // Converte a chave privada (formato PEM) para RSAPrivateKey
        return RsaKeyConverters.pkcs8().convert(privateKeyPem);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                auth -> auth.requestMatchers("/authenticate/**", "/user/register/**").permitAll()
                    .anyRequest().authenticated()
            ).httpBasic(Customizer.withDefaults())
            .oauth2ResourceServer(
                conf -> conf.jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        // Usa a chave pública convertida para criar o JwtDecoder
        return NimbusJwtDecoder.withPublicKey(publicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        // Cria um JWK com as chaves pública e privada
        RSAKey jwk = new RSAKey.Builder(publicKey())
            .privateKey(privateKey())
            .build();

        // Cria um JWKSet com o JWK
        ImmutableJWKSet<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));

        // Retorna o JwtEncoder
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}