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
//            con = daoFactory.getConnection();
//            ///Connexion Eleve
//            if(profils.equalsIgnoreCase("eleve"))
//                requete = "select login,motDePasse,nom,prenom from personne where"
//                        + " profil='"+profils+"' and etatPers=1";
//            //Fin connexion Eleve
//            //Connexion Comptable
//            else if (profils.equalsIgnoreCase("comptable"))
//                requete ="select login,motDePasse,nom,prenom from personne where"
//                        + " profil='"+profils+"' and etatPers=1";
//            else if (profils.equalsIgnoreCase("professeur"))
//                requete = "select loginProf,motDePasse,nom,prenom from professeur,personne where active=0";
//            else if (profils.equalsIgnoreCase("directeur"))
//                requete = "select login,motDePasse,nom,prenom from personne where  profil='"+profils+"'";
//             else if (profils.equalsIgnoreCase("surveillant"))
//                requete = "select loginSurv,motDePasse,nom,prenom from surveillant,personne where active=0";
//            st = con.createStatement();
//            ResultSet rs = st.executeQuery(requete);
//            while (rs.next()) {
//                Utilisateur person = new Utilisateur();
//                person.setLogin(rs.getString(1));
//                person.setMotDePasse(rs.getString(2));
//                person.setNom(rs.getString("nom"));
//                person.setPrenom(rs.getString("prenom"));
//                listPerson.add(person);
//              
            con = daoFactory.getConnection();
            requete = "select login,motDePasse,nom,prenom from personne where etatPers=1 and profil='" + profils + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Utilisateur person = new Utilisateur();
                person.setLogin(rs.getString(1));
                person.setMotDePasse(rs.getString(2));
                person.setNom(rs.getString("nom"));
                person.setPrenom(rs.getString("prenom"));
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
