package demo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import demo.spring.model.Users;
import demo.spring.repository.UserRepository;

import java.util.List;

@Controller
public class ThymeleafController {
    private UserRepository userRepository;

    public ThymeleafController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @GetMapping("/usersth")
    public String afficherUtilisateurs(Model model) {
        List<Users> utilisateurs = userRepository.findAll();
        model.addAttribute("utilisateurs", utilisateurs);
        return "users"; 
    }
}
