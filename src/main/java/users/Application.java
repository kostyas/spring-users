package users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import users.model.Address;
import users.model.User;
import users.model.UserRole;
import users.repository.UserRepository;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "users.config"
})
public class Application implements CommandLineRunner {

    public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.findOneByEmail("admin@gmail.com").isPresent()) {
            userRepository.saveAndFlush(new User("admin", passwordEncoder.encode("admin"), "admin@gmail.com", UserRole.editor, Arrays.asList(
                    new Address("Russia", "Moscow", "Arbat, 9"),
                    new Address("Russia", "Novorossiysk", "Vidova, 1")
            )));
        }
    }

}
