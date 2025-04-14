package demo.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import demo.spring.model.Age;
import demo.spring.repository.AgeRepository;

@Controller
public class AgeController {
    private final AgeRepository ageRepository;
    public AgeController(AgeRepository ageRepository){
        this.ageRepository = ageRepository;
    }

    @GetMapping("/conf")
    public String showForm(Model model) {
        model.addAttribute("ages", new Age());
        return "age-form";
    }

    @PostMapping("/save-parametre")
    public String saveAge(@ModelAttribute Age age){
        ageRepository.save(age);
        return "redirect:/list-age";
    }

    @GetMapping("/list-age")
    public String showList(Model model) {


        Optional<Age> dernierEnfant = ageRepository.findLastAgeByType("Enfant");
        Optional<Age> dernierAdulte = ageRepository.findLastAgeByType("Adulte");
        List<Age> derniersAges = new ArrayList<>();
        dernierEnfant.ifPresent(derniersAges::add);
        dernierAdulte.ifPresent(derniersAges::add);
        model.addAttribute("ages", derniersAges);
        return "age-list";
    }
}
