package onyx.controllers;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final Environment environment;

    public HomeController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/${HomeController.RootUrl}")
    String getLoginPage() {
        return environment.getRequiredProperty("RootHTML");
    }
}
