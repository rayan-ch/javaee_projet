package com.junia.projet.projet.Controllers;

import com.junia.projet.projet.Entities.Authors;
import com.junia.projet.projet.Entities.Tutorials;
import com.junia.projet.projet.Repo.AuthorsRepo;
import com.junia.projet.projet.Repo.TutorialsRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class Login {
    @Autowired
    private AuthorsRepo authorsRepo;

    @Autowired
    private TutorialsRepo tutorialsRepo;

    @GetMapping("/inscription")
    public String AfficherInscription(Model model) {
        model.addAttribute("newAuthor", new Authors());
        return "inscription";
    }

    @PostMapping("/inscription")
    public String Inscrire(Authors author) {
        authorsRepo.save(author);
        return "redirect:/connexion";
    }

    @GetMapping("/connexion")
    public String AfficherConnexion(Model model, HttpServletRequest request) {
        String error = request.getParameter("error");
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("loginAuthor", new Authors());
        return "connexion";
    }

    @PostMapping("/connexion")
    public String Connexion(Authors author, HttpSession session) {
        Authors lcl_author = authorsRepo.findByEmail(author.getEmail());
        if (lcl_author != null) {
            if (lcl_author.getPassword().equals(author.getPassword())) {
                session.setAttribute("author_id", lcl_author.getId());
                return "redirect:/dashboard";
            }
        }
        return "redirect:/connexion?error=Email ou mot de passe incorrect";
    }

}
