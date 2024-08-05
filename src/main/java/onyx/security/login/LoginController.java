package onyx.security.login;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginController {

    private final Environment environment;

    public LoginController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/${LoginPageURLPath}")
    String getLoginPage() {
        return environment.getRequiredProperty("LoginPageHTMLName");
    }
}

