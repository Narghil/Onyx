package onyx.security.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class LoginSecurityConfigurator {

    private final Environment environment;

    public LoginSecurityConfigurator(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());

        httpSecurity.formLogin(withDefaults());
        httpSecurity.formLogin(form -> form
            .loginPage(environment.getRequiredProperty("LoginPageURLPath"))
            .permitAll()
        );
        httpSecurity.logout(form -> form
            .logoutSuccessUrl( environment.getRequiredProperty("LogoutSuccessURL") )
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll()
        );

        return httpSecurity.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User
            .withUsername(environment.getRequiredProperty("LoginTestUserName"))
            .password(environment.getRequiredProperty("LoginTestPassword"))
            .roles("NONE")
            .build();

        return new InMemoryUserDetailsManager(user);
    }
}