package co.edu.escuelaing.apigateway.secureapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AuthenticationBeansConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("secureuser")
                        .password(passwordEncoder.encode("SecurePass123!"))
                        .roles("USER")
                        .build(),

                User.withUsername("miguel")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER")
                        .build(),

                User.withUsername("juan")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER")
                        .build(),

                User.withUsername("jhon")
                        .password(passwordEncoder.encode("1234"))
                        .roles("USER")
                        .build(),

                User.withUsername("admin")
                        .password(passwordEncoder.encode("AdminPass123!"))
                        .roles("ADMIN")
                        .build()
        );
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }
}

