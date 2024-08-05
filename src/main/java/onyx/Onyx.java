package onyx;

import onyx.security.login.LoginApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Onyx {
    public static void main(String[] args) {
        SpringApplication.run(LoginApp.class, args);
    }
}

