package com.example.handlingformsubmission;

import com.example.DAO.addMusica.addMusicaDAO;
import com.example.Model.musica;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MusicaController {

    @GetMapping("/musica")
    public String musicaForm(Model model) {
        model.addAttribute("musica", new musica());
        System.out.println("to aqui");
        return "musica";
    }

    @PostMapping("/musica")
    public String musicaSubmit(@ModelAttribute musica musica) {
        
        addMusicaDAO add = new addMusicaDAO();
        
        add.addMusica(musica);
        
        return "listaMusicas";
    }

}
