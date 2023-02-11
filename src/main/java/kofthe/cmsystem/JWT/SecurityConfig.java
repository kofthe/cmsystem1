package kofthe.cmsystem.JWT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    CustomerUserDetailsService customerUserDetailsService;

    public SecurityConfig(CustomerUserDetailsService customerUserDetailsService) {
        this.customerUserDetailsService = customerUserDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
