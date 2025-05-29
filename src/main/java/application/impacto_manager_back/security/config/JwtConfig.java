package application.impacto_manager_back.security.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.io.InputStream;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class JwtConfig {
	
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
		return RsaKeyConverters.pkcs8().convert(privateKeyPem);
	}
	
	@Bean
	JwtDecoder jwtDecoder(RSAPublicKey publicKey) {
		return NimbusJwtDecoder.withPublicKey(publicKey).build();
	}
	
	@Bean
	JwtEncoder jwtEncoder(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
		RSAKey jwk = new RSAKey.Builder(publicKey)
			.privateKey(privateKey)
			.build();
		ImmutableJWKSet<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
}