package onyx.security.login;

import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginWebConfigurator implements WebMvcConfigurer {

    private final Environment environment;

    public LoginWebConfigurator(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void addViewControllers(
        @NonNull
        ViewControllerRegistry viewControllerRegistry
    ) {
        viewControllerRegistry
            .addViewController(environment.getRequiredProperty("LoginPageURLPath"))
            .setViewName(environment.getRequiredProperty("LoginPageFullPath"));
        viewControllerRegistry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}


