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
public class addMusicaDAO {

    public Integer addMusica(musica musica) {

        int id;

        //GRAVAR NO BANCO
        //indica as configuracoes do banco
        //PODE SER USAR MAIS DE UMA CLASSE SEPARANDO POR VIRGULAS NO tbPauta.class,tb...
        Configuration con = new Configuration().configure().addAnnotatedClass(musica.class);
        SessionFactory sf = con.buildSessionFactory();

        //abre sessao com o banco
        Session session = sf.openSession();

        try {

            //inicia a transacao com o banco
            Transaction tx = session.beginTransaction();
            id = (Integer) session.save(musica);

            //comita as informacoes
            tx.commit();

        } finally {
            if (session != null) {
                session.close();
                sf.close();
            }
        }
        
        return  id;

    }

}
