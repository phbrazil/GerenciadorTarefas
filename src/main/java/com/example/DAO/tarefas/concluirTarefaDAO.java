/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DAO.tarefas;

import com.example.Model.tarefa;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author paulo.bezerra
 */
public class concluirTarefaDAO {

    public int iniciarTarefa(int id, int nota) {

        //GRAVAR NO BANCO
        //indica as configuracoes do banco
        //PODE SER USAR MAIS DE UMA CLASSE SEPARANDO POR VIRGULAS NO tbPauta.class,tb...
        Configuration con = new Configuration().configure().addAnnotatedClass(tarefa.class);
        SessionFactory sf = con.buildSessionFactory();

        //abre sessao com o banco
        Session session = sf.openSession();
        
        int result = 0;

        try {

            //inicia a transacao com o banco
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update tarefa set nota = :nota, status = :status"
                    + " where id = :id");
            query.setParameter("nota", nota);
            query.setParameter("status", "Finalizada");
            query.setParameter("id", id);
            
            result = query.executeUpdate();

            //comita as informacoes
            tx.commit();

        } finally {
            if (session != null) {
                session.close();
                sf.close();
            }
        }

        return result;

    }

}
