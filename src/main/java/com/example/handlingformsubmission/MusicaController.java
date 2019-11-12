package com.example.handlingformsubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MusicaController {

    @GetMapping("/musica")
    public String musicaForm(Model model) {
        model.addAttribute("musica", new Musica());
        return "musica";
    }

    @PostMapping("/musica")
    public String musicaSubmit(@ModelAttribute Musica musica) {
        return "listaMusicas";
    }

}
