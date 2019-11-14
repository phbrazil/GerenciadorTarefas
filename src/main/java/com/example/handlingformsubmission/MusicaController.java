package com.example.handlingformsubmission;

import com.example.DAO.musicas.addMusicaDAO;
import com.example.DAO.musicas.listMusicas;
import com.example.Model.musica;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MusicaController {

    @GetMapping("/list")
    public String listMusicas(Model musicas, Model musica) {

        listMusicas list = new listMusicas();

        List<musica> listMusicas = list.List();

        musicas.addAttribute("musicas", listMusicas);
        musicas.addAttribute("musica", new musica());

        return "listaMusicas";
    }

    @GetMapping("/musica")
    public String musicaForm(Model musica) {

        musica.addAttribute("musica", new musica());

        return "musica";
    }

    @PostMapping("/musica")
    public String musicaSubmit(@ModelAttribute musica musica, Model musicas) {

        addMusicaDAO add = new addMusicaDAO();

        add.addMusica(musica);

        listMusicas list = new listMusicas();

        List<musica> listMusicas = list.List();

        musicas.addAttribute("musicas", listMusicas);

        return "listaMusicas";
    }

    @PostMapping("/deleteMusica")
    public String deleteMusica(@ModelAttribute musica musica, Model musicas) {

        System.out.println("Deleting musica");
        System.out.println(musica);

        listMusicas list = new listMusicas();

        List<musica> listMusicas = list.List();

        musicas.addAttribute("musicas", listMusicas);

        return "listaMusicas";
    }

}
