package ie.tus.gateway;

import com.nimbusds.oauth2.sdk.AbstractAuthenticatedRequest;
import ie.tus.gateway.config.KeycloakRoleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {
        serverHttpSecurity.authorizeExchange(authorizeExchangeSpec ->
            authorizeExchangeSpec.pathMatchers("/actuator/**").permitAll()
                   .pathMatchers(HttpMethod.POST,"/cookbook/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.PUT,"/cookbook/**").hasRole("ADMIN")
                   .pathMatchers(HttpMethod.DELETE,"/cookbook/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.GET,"/cookbook/1").permitAll()
                    .pathMatchers(HttpMethod.GET,"/cookbook/**").hasRole("ADMIN")
                    //.pathMatchers("/cookbook/**").permitAll()

                    .pathMatchers(HttpMethod.POST,"/recipes/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.PATCH,"/recipes/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.PUT,"/recipes/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.DELETE,"/recipes/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.GET,"/recipes/**").permitAll()
                //.pathMatchers("/recipes/**").authenticated()
            )
                .oauth2ResourceServer(oAuth2ResourceServerSpec ->
                                oAuth2ResourceServerSpec.jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(authoritiesExtractor()))
//            .oauth2ResourceServer(oAuth2ResourceServerSpec ->
//                oAuth2ResourceServerSpec.jwt(jwtSpec -> Customizer.withDefaults())
            );
        serverHttpSecurity.csrf(csrfSpec -> csrfSpec.disable());

        return serverHttpSecurity.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> authoritiesExtractor(){
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
