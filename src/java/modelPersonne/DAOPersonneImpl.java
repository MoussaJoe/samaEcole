/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelPersonne;

import java.sql.*;
import java.util.ArrayList;
import model.DAOFactory;
import model.Eleve;
import model.Utilisateur;
import modelTables.Personne;

/**
 *
 * @author Moussa Joseph Sarr
 */
public class DAOPersonneImpl {

    private DAOFactory daoFactory;

    public DAOPersonneImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public ArrayList<Utilisateur> listPersonne(String profils) {
        String requete = null;
        ArrayList<Utilisateur> listPerson = new ArrayList();
        Connection con;
        Statement st;
        try {    
            con = daoFactory.getConnection();
            requete = "select login,motDePasse,nom,prenom,nomImgPers from personne where etatPers=1 and profil='" + profils + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Utilisateur person = new Utilisateur();
                person.setLogin(rs.getString(1));
                person.setMotDePasse(rs.getString(2));
                person.setNom(rs.getString("nom"));
                person.setPrenom(rs.getString("prenom"));
                person.setNomImgPers(rs.getString("nomImgPers"));
                listPerson.add(person);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listPerson;
    }

    public int nbrePersonne() {
        int nb = 0;
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select idPersonne from personne";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);

            if (rs.last()) {
                nb = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return nb;
    }

}
