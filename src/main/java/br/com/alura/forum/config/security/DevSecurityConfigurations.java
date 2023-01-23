package br.com.alura.forum.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@Profile("dev")
public class DevSecurityConfigurations {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity  http) throws Exception {
        // Configurações de Autorização.
        http.authorizeHttpRequests()
                .requestMatchers("/**").permitAll()
                .and().cors()
                .and().headers().frameOptions().disable()
                .and().csrf().disable();

        return http.build();
    }

// public static void main(String[] args) {
//     System.out.println(new BCryptPasswordEncoder().encode("123456"));
// }

}
