/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DAO.tarefas;

import com.example.Model.tarefa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author paulo.bezerra
 */
public class getTarefaDAO {
    
    public tarefa getTarefa(int id){
        
                //indica as configuracoes do banco
        Configuration con = new Configuration().configure().addAnnotatedClass(tarefa.class);
        SessionFactory sf = con.buildSessionFactory();

        //abre sessao com o banco
        Session session = sf.openSession();
        tarefa tarefa;
        try {

            Transaction tx = session.beginTransaction();

            tarefa = (tarefa) session.get(tarefa.class, id);

            //comita as informacoes
            tx.commit();

        } finally {
            if (session != null) {
                session.close();
                sf.close();
            }

        }

        return tarefa;
    }
    
}
