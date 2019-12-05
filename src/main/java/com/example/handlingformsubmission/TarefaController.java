package com.example.handlingformsubmission;

import com.example.DAO.tarefas.addTarefaDAO;
import com.example.DAO.tarefas.deleteTarefaDAO;
import com.example.DAO.tarefas.getTarefaDAO;
import com.example.DAO.tarefas.listTarefas;
import com.example.Model.tarefa;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String tarefaSubmit(@ModelAttribute tarefa tarefa, Model tarefas) {

        addTarefaDAO add = new addTarefaDAO();

        add.addTarefa(tarefa);

        listTarefas list = new listTarefas();

        List<tarefa> listTarefas = list.List();

        tarefas.addAttribute("tarefas", listTarefas);

        return "listaTarefas";
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

}
