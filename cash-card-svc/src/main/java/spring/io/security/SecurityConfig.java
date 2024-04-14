package spring.io.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        request -> request.requestMatchers("/cashcards/**")
                                .hasRole("CARD-OWNER"))
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    UserDetailsService testOnlyUsers(PasswordEncoder encoder) {
        User.UserBuilder users = User.builder();
        UserDetails sarah= users.username("sarah1")
                .password(encoder.encode("abc123"))
                .roles("CARD-OWNER").build();
        UserDetails unknown = users.username("unknown")
                .password(encoder.encode("abc123"))
                .roles("NON-OWNER").build();
        return new InMemoryUserDetailsManager(sarah, unknown);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}