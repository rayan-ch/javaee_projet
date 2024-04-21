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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
            List<Tutorials> tutorials = tutorialsRepo.findByAuthorId(id);
            model.addAttribute("tutorials", tutorials);
            model.addAttribute("author", author);
        } else {
            return "redirect:/connexion";
        }
        return "dashboard";
    }

    @GetMapping("/dashboard/edit/{id}")
    public String editTutorial(@PathVariable("id") Long id, Model model, HttpSession session) {
        Long authorId = (Long) session.getAttribute("author_id");
        if (authorId != null) {
            Tutorials tutorial = tutorialsRepo.findById(id).orElse(null);
            if (tutorial != null) {
                model.addAttribute("tutorial", tutorial);
                return "edit_tuto";
            } else {
                return "redirect:/dashboard";
            }
        }
        return "redirect:/connexion";
    }

    @PostMapping("/dashboard/edit/{id}")
    public String SaveTutorial(@PathVariable("id") Long id, HttpSession session, Tutorials newTutorial) {
        Long authorId = (Long) session.getAttribute("author_id");
        if (authorId != null) {
            Tutorials tutorial = tutorialsRepo.findById(id).orElse(null);
            if (tutorial != null) {
                tutorial.setTitle(newTutorial.getTitle());
                tutorial.setDescription(newTutorial.getDescription());
                tutorialsRepo.save(tutorial);
            }
            return "redirect:/dashboard";
        }
        return "redirect:/connexion";
    }

    @GetMapping("/dashboard/add")
    public String editTutorial(Model model, HttpSession session) {
        Long authorId = (Long) session.getAttribute("author_id");
        if (authorId != null) {
            model.addAttribute("newTutorial", new Tutorials());
            return "add_tuto";
        }
        return "redirect:/connexion";
    }

    @PostMapping("/dashboard/add")
    public String SaveTutorial(HttpSession session, Tutorials newTutorial) {
        Long authorId = (Long) session.getAttribute("author_id");
        if (authorId != null) {
            LocalDate date = LocalDate.now();
            newTutorial.setDate(date);
            newTutorial.setAuthor(authorsRepo.findById(authorId).orElse(null));
            if (newTutorial.getAuthor() != null) {
                tutorialsRepo.save(newTutorial);
            }
            return "redirect:/dashboard";
        }
        return "redirect:/connexion";
    }

    @GetMapping("/dashboard/delete/{id}")
    public String DeleteTutorial(@PathVariable("id") Long id, HttpSession session) {
        Long authorId = (Long) session.getAttribute("author_id");
        if (authorId != null) {
            Tutorials tutorial = tutorialsRepo.findById(id).orElse(null);
            if (tutorial != null) {
                if (Objects.equals(tutorial.getAuthor().getId(), authorId)) {
                    tutorialsRepo.delete(tutorial);
                }
            }
            return "redirect:/dashboard";
        }
        return "redirect:/connexion";
    }
}
