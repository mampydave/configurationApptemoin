package demo.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import demo.spring.model.Users;
import demo.spring.repository.UserRepository;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/user-form")
    public String showForm(Model model) {
        model.addAttribute("users", new Users());
        return "user-form";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute Users user) {
        userRepository.save(user); // Sauvegarder dans la base de données
        return "redirect:/users"; // Rediriger vers la liste des utilisateurs
    }


    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user-list";
    }
}
