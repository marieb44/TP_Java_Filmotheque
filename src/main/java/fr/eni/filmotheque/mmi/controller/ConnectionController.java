package fr.eni.filmotheque.mmi.controller;

import fr.eni.filmotheque.bll.ConnectionService;
import fr.eni.filmotheque.bo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ConnectionController {

    private ConnectionService connectionService;

    @Autowired // Optionnel sur le constructeur si pas d'ambiguité
    public ConnectionController(ConnectionService connectionService) {

        this.connectionService = connectionService;
    }

    @GetMapping("/login")
    public String login() {
        return "connexion";
    }

    @PostMapping ("/login")
    public String checkConnection (@RequestParam(required = true) String login,
                                   @RequestParam(required = true) String password,
                                   HttpSession session, Model model) {
        //System.out.println("Login: " + login + " // password : " + password);
        Optional<Member> optConnectedMember = connectionService.login(login, password);
        if (optConnectedMember.isPresent()) {
            //System.out.println("SUCCESS - Login: " + login + " // password : " + password);
            session.setAttribute("member", optConnectedMember.get());
            return "redirect:/films";
        } else {
            //System.out.println("ERROR - Login: " + login + " // password : " + password);
            model.addAttribute("error", "Le pseudo et le mot de passe ne sont pas corrects.");
            return "connexion";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
