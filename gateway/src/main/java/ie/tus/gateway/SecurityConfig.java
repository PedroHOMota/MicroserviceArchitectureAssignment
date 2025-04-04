
//
//package ie.tus.gateway;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfiguration {
//
//    @Bean
//    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {
//        serverHttpSecurity.authorizeExchange(authorizeExchangeSpec ->
//            authorizeExchangeSpec.pathMatchers("/actuator/gateway/routes").permitAll().pathMatchers("/b").authenticated()
//            )
//            .oauth2ResourceServer(oAuth2ResourceServerSpec ->
//                oAuth2ResourceServerSpec.jwt(Customizer.withDefaults())
//            );
//        serverHttpSecurity.csrf(csrfSpec -> csrfSpec.disable());
//
//        return serverHttpSecurity.build();
//    }
//
//}
