package fr.eni.filmotheque.mmi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class PrincipalController {

    @GetMapping("/")
    public String accueil() {
        return "index";
    }
}
