package com.junia.projet.projet.Controllers;

import com.junia.projet.projet.Entities.Authors;
import com.junia.projet.projet.Entities.Tutorials;
import com.junia.projet.projet.Repo.AuthorsRepo;
import com.junia.projet.projet.Repo.TutorialsRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Dashboard {
    @Autowired
    private AuthorsRepo authorsRepo;
    @Autowired
    private TutorialsRepo tutorialsRepo;

    @GetMapping("/dashboard")
    public String Dashboard(Model model, HttpSession session) {
        Long id = (Long) session.getAttribute("author_id");
        if (id != null) {
            Authors author = authorsRepo.findAllById(id);
            System.out.println(author.getFirstname());
            List<Tutorials> tutorials = tutorialsRepo.findByAuthorId(id);
            model.addAttribute("tutorials", tutorials);
            model.addAttribute("author", author);
        } else {
            return "redirect:/connexion";
        }
        return "dashboard";
    }
}
