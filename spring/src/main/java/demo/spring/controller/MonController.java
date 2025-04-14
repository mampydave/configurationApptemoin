package demo.spring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import demo.spring.model.Users;
import demo.spring.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class MonController {
    // @Autowired
    private UserRepository userRepository;

    public MonController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public Users ajouterUtilisateur(@RequestBody Users user) {
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public List<Users> listeUtilisateurs() {
        return userRepository.findAll();
    }

    @GetMapping("/hello")
    public String direBonjour() {
        return "Hello, Spring Boot!";
    }
}
