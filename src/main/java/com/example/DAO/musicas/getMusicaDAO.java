/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.DAO.musicas;

import com.example.Model.musica;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author paulo.bezerra
 */
public class getMusicaDAO {
    
    public musica getMusica(int id){
        
                //indica as configuracoes do banco
        Configuration con = new Configuration().configure().addAnnotatedClass(musica.class);
        SessionFactory sf = con.buildSessionFactory();

        //abre sessao com o banco
        Session session = sf.openSession();
        musica musica;
        try {

            Transaction tx = session.beginTransaction();

            musica = (musica) session.get(musica.class, id);

            //comita as informacoes
            tx.commit();

        } finally {
            if (session != null) {
                session.close();
                sf.close();
            }

        }

        return musica;
    }
    
}
