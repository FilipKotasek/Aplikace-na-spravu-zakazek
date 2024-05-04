package Kotasek.Contract.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true,jsr250Enabled = true)
public class AppSecurityConfig {

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity.authorizeHttpRequests()
            .anyRequest()
            .permitAll()
            .and()
            .formLogin()
              .loginPage("/account/login")
              .loginProcessingUrl("/account/login")
              .defaultSuccessUrl("/contracts",true)
              .usernameParameter("email")
              .permitAll()
            .and()
            .logout()
            .logoutUrl("/account/logout")
            .and()
            .build();
}

@Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

}
