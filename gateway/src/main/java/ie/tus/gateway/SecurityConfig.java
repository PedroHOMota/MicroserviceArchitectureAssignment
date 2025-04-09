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
//                    .pathMatchers(HttpMethod.POST,"/cookbook/**").hasRole("ADMIN")
//                    .pathMatchers(HttpMethod.PUT,"/cookbook/**").hasRole("ADMIN")
//                    .pathMatchers(HttpMethod.DELETE,"/cookbook/**").hasRole("ADMIN")
                    //.pathMatchers("/cookbook/**").authenticated()
                    .pathMatchers("/cookbook/**").permitAll()

                    .pathMatchers(HttpMethod.POST,"/recipes/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.PUT,"/recipes/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.DELETE,"/recipes/**").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.GET,"/recipes/**").authenticated()
                //.pathMatchers("/recipes/**").authenticated()
            )
            .oauth2ResourceServer(oAuth2ResourceServerSpec ->
                oAuth2ResourceServerSpec.jwt(jwtSpec -> Customizer.withDefaults())
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

//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Stream;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    interface AuthoritiesConverter extends Converter<Map<String, Object>, Collection<GrantedAuthority>> {}
//
//    @Bean
//    AuthoritiesConverter realmRolesAuthoritiesConverter() {
//        return claims -> {
//            final var realmAccess = Optional.ofNullable((Map<String, Object>) claims.get("realm_access"));
//            final var roles =
//                realmAccess.flatMap(map -> Optional.ofNullable((List<String>) map.get("roles")));
//            return roles.map(List::stream).orElse(Stream.empty()).map(SimpleGrantedAuthority::new)
//                .map(GrantedAuthority.class::cast).toList();
//        };
//    }
//
//    @Bean
//    JwtAuthenticationConverter authenticationConverter(
//        Converter<Map<String, Object>, Collection<GrantedAuthority>> authoritiesConverter) {
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter
//            .setJwtGrantedAuthoritiesConverter(jwt -> authoritiesConverter.convert(jwt.getClaims()));
//        return jwtAuthenticationConverter;
//    }
//
//    @Bean
//    SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http,
//                                                          Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter) throws Exception {
//        http.oauth2ResourceServer(resourceServer -> {
//            resourceServer.jwt(jwtDecoder -> {
//                jwtDecoder.jwtAuthenticationConverter(jwtAuthenticationConverter);
//            });
//        });
//
//        http.sessionManagement(sessions -> {
//            sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        }).csrf(csrf -> {
//            csrf.disable();
//        });
//
//        http.authorizeHttpRequests(requests -> {
//            requests.requestMatchers("/me").authenticated();
//            requests.anyRequest().denyAll();
//        });
//
//        return http.build();
//    }
//
//    @Bean
//    SecurityFilterChain clientSecurityFilterChain(
//        HttpSecurity http,
//        ClientRegistrationRepository clientRegistrationRepository) throws Exception {
//        http.oauth2Login(Customizer.withDefaults());
//        http.logout((logout) -> {
//            var logoutSuccessHandler =
//                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
//            logoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/");
//            logout.logoutSuccessHandler(logoutSuccessHandler);
//        });
//
//        http.authorizeHttpRequests(requests -> {
//            requests.requestMatchers("/actuator/gateway/routes", "/favicon.ico").permitAll();
//            requests.requestMatchers("/**").hasAuthority("NICE");
//            requests.anyRequest().denyAll();
//        });
//
//        return http.build();
//    }
//}
