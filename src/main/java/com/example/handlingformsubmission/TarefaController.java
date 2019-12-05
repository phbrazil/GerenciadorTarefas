package com.example.handlingformsubmission;

import com.example.DAO.tarefas.addTarefaDAO;
import com.example.DAO.tarefas.deleteTarefaDAO;
import com.example.DAO.tarefas.getTarefaDAO;
import com.example.DAO.tarefas.iniciarTarefaDAO;
import com.example.DAO.tarefas.listTarefas;
import com.example.Model.tarefa;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TarefaController {

    @GetMapping("/list")
    public String listTarefas(Model tarefas, Model tarefa) {

        listTarefas list = new listTarefas();

        List<tarefa> listTarefas = list.List();

        tarefas.addAttribute("tarefas", listTarefas);
        tarefas.addAttribute("tarefa", new tarefa());

        return "listaTarefas";
    }

    @GetMapping("/tarefa")
    public String rerefaForm(Model tarefa) {

        tarefa.addAttribute("tarefa", new tarefa());

        return "tarefa";
    }

    @PostMapping("/tarefa")
    public String tarefaSubmit(@ModelAttribute tarefa tarefa, Model tarefas,
            RedirectAttributes redirectAttrs) {

        addTarefaDAO add = new addTarefaDAO();

        add.addTarefa(tarefa);

        listTarefas list = new listTarefas();

        List<tarefa> listTarefas = list.List();

        //redirectAttrs.addAttribute("tarefas", listTarefas);
        tarefas.addAttribute("tarefas", listTarefas);

        return "listaTarefas";
        //return  "redirect:listaTarefas";
    }

    @PostMapping("/deleteTarefa")
    public String deleteTarefa(@ModelAttribute tarefa tarefa, Model tarefas) {

        System.out.println("Deleting tarefa");

        //PEGAR A TAREFA NO BANCO PELO ID
        getTarefaDAO get = new getTarefaDAO();

        tarefa = get.getTarefa(tarefa.getId());

        // DELETAR A TAREFA
        deleteTarefaDAO delete = new deleteTarefaDAO();

        delete.deleteTarefa(tarefa);

        //BUSCAR LISTA ATUALIZADA        
        listTarefas list = new listTarefas();

        List<tarefa> listTarefas = list.List();

        tarefas.addAttribute("tarefas", listTarefas);

        return "listaTarefas";
    }

    @PostMapping("/iniciarTarefa")
    public String iniciarTarefa(@ModelAttribute tarefa tarefa, Model tarefas) {

        System.out.println("Iniciando tarefa");

        //PEGAR A TAREFA NO BANCO PELO ID
        getTarefaDAO get = new getTarefaDAO();

        tarefa = get.getTarefa(tarefa.getId());

        // INICIAR A TAREFA
        iniciarTarefaDAO iniciar = new iniciarTarefaDAO();

        int result = iniciar.iniciarTarefa(tarefa);
        
        System.out.println(result);

        //BUSCAR LISTA ATUALIZADA        
        listTarefas list = new listTarefas();

        List<tarefa> listTarefas = list.List();
        
        tarefas.addAttribute("result","Tarefa iniciada");

        tarefas.addAttribute("tarefas", listTarefas);

        return "listaTarefas";
    }

}
