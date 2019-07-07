/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelPersonne;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.DAOFactory;
import model.Eleve;
import model.EleveRecherche;
import model.Parent;

/**
 *
 * @author ibrah
 */
public class DAOParentImpl {
    private DAOFactory daoFactory;
    
    public DAOParentImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    public ArrayList<Eleve> listerEleve(String loginParent){
        ArrayList<Eleve> eleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select eleve.login,nomClasse,prenom,nom,dateNaissance,lieuNaissance,adresse from eleve,personne where Par_login='"+ loginParent +"' and eleve.login=personne.login";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Eleve e = new Eleve();
                e.setNomClasse(rs.getString("nomClasse"));
                e.setPrenom(rs.getString("prenom"));
                e.setNom(rs.getString("nom"));
                e.setDateNaissance(rs.getString("dateNaissance"));
                e.setLieuNaissance(rs.getString("lieuNaissance"));
                e.setAdresse(rs.getString("adresse"));
                e.setLogin(rs.getString("login"));
                eleve.add(e);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eleve;
    }

    public ArrayList<String> prenomEleve(String loginParent){
        ArrayList<String> prenomEleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select prenom from eleve where loginParent='" + loginParent + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                prenomEleve.add(rs.getString("prenom"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return prenomEleve;
    }
    
    public ArrayList<String> nomleve(String loginParent){
        ArrayList<String> nomleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nom from eleve where loginParent='" + loginParent + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                nomleve.add(rs.getString("nom"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return nomleve;
    }

    public ArrayList<String> dateNaiEleve(String loginParent){
       ArrayList<String> dateNaiEleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select dateNaissance from eleve where loginParent='" + loginParent + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                dateNaiEleve.add(rs.getString("dateNaissance"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dateNaiEleve; 
    }

    public ArrayList<String> lieuNaiEleve(String loginParent){
       ArrayList<String> lieuNaiEleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select lieuNaissance from eleve where loginParent='" + loginParent + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                lieuNaiEleve.add(rs.getString("lieuNaissance"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lieuNaiEleve; 
    }

    public ArrayList<String> classeEleve(String loginParent){
      ArrayList<String> classeEleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomClasse from eleve where loginParent='" + loginParent + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                classeEleve.add(rs.getString("nomClasse"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return classeEleve;  
    }

    public ArrayList<String> anneeEleve(){
        ArrayList<String> anneeEleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select annee from annee ";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                anneeEleve.add(rs.getString("annee"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return anneeEleve;
    }

    public ArrayList<Parent> listPar(String login, String motDePasse){
       ArrayList<Parent> listParent = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select parent.login,motDePasse,prenom,nom from personne,parent where parent.login='" + login + "' and motDePasse='" + motDePasse + "' and etatPers=1 and personne.login=parent.login";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()) {
                Parent par= new Parent();
                par.setLoginParent(rs.getString("login"));
                par.setMotDePasse(rs.getString("motDePasse"));
                par.setPrenom(rs.getString("prenom"));
                par.setNom(rs.getString("nom"));
                listParent.add(par);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listParent; 
    }

    public String loginParent(String loginParent, String motDePasse){
        String idParent="";
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select parent.login from parent,personne where parent.login='" + loginParent + "' and motDePasse='" + motDePasse + "' and personne.login=parent.login";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                idParent=rs.getString("login");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return idParent;
    }
    ///////////////////////////////////////////////Recherche eleve par Parent//////////////////////////////////////////////////////////////
    public ArrayList<Eleve> recherchePar(String nom, String loginPar){
        ArrayList<Eleve> eleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select * from eleve where nom='" + nom + "' and loginPar='" + loginPar + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Eleve e = new Eleve();
                e.setNomClasse(rs.getString("nomClasse"));
                e.setPrenom(rs.getString("prenom"));
                e.setNom(rs.getString("nom"));
                e.setDateNaissance(rs.getString("dateNaissance"));
                e.setLieuNaissance(rs.getString("lieuNaissance"));
                e.setClasse(rs.getString("nomClasse"));
                eleve.add(e);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eleve;
    }
    ///////////////////////////////////////////////Recherche eleve par Eleve//////////////////////////////////////////////////////////////
    public ArrayList<Eleve> rechercheEleve(String nom){
        ArrayList<Eleve> eleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select * from eleve where nom='" + nom + "' and nomClasse='" + EleveRecherche.getNomClasse() + "' and annee='" + EleveRecherche.getAnnee() + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Eleve e = new Eleve();
                e.setNomClasse(rs.getString("nomClasse"));
                e.setPrenom(rs.getString("prenom"));
                e.setNom(rs.getString("nom"));
                e.setDateNaissance(rs.getString("dateNaissance"));
                e.setLieuNaissance(rs.getString("lieuNaissance"));
                e.setTel(rs.getString("telephone"));
                e.setClasse(rs.getString("nomClasse"));
                eleve.add(e);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eleve;
    }
    ///////////////////////////////////////////////Recherche eleve par Directeur//////////////////////////////////////////////////////////////
    public ArrayList<Eleve> rechercheEleveDir(String nom){
        ArrayList<Eleve> eleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select distinct nom,prenom,nomClasse,dateNaissance,lieuNaissance,telephone from eleve where nom='" + nom + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Eleve e = new Eleve();
                e.setNomClasse(rs.getString("nomClasse"));
                e.setPrenom(rs.getString("prenom"));
                e.setNom(rs.getString("nom"));
                e.setDateNaissance(rs.getString("dateNaissance"));
                e.setLieuNaissance(rs.getString("lieuNaissance"));
                e.setTel(rs.getString("telephone"));
                e.setClasse(rs.getString("nomClasse"));
                eleve.add(e);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eleve;
    }
    public ArrayList<Eleve> eleveParent(String annee, String loginEleve) {
        ArrayList<Eleve> elevePar = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete1 = "select  devoir,nomMatiere,semestre from devoir,eleve,personne where devoir.anneeScolaire='" + annee + "' and devoir.login='" + loginEleve + "' and devoir.login=eleve.login and eleve.login=personne.login and etatPers=1";
            st = con.createStatement();
            ResultSet rs1 = st.executeQuery(requete1);
            while (rs1.next()) {
                Eleve eleve = new Eleve();

                eleve.setSemestre(rs1.getString("semestre"));
                eleve.setDevoir1(rs1.getFloat("devoir"));
                eleve.setMatiere(rs1.getString("nomMatiere"));
                eleve.setAnnee(annee);
                elevePar.add(eleve);
            }
            for (Eleve e : elevePar) {
                System.out.println(e.getComposition() + " " + e.getDevoir1() + " " + e.getDevoir2());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return elevePar;
    }
    public ArrayList<Eleve> eleveParentCompo(String annee, String loginEleve) {
        ArrayList<Eleve> elevePar = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete1 = "select  noteComposition,evaluation.nomMatiere,semestre from evaluation,eleve,personne where evaluation.anneeScolaire='" + annee + "' and evaluation.login='" + loginEleve + "' and eleve.login=personne.login and eleve.login=evaluation.login and etatPers=1";
            st = con.createStatement();
            ResultSet rs1 = st.executeQuery(requete1);
            while (rs1.next()) {
                Eleve eleve = new Eleve();

                eleve.setSemestre(rs1.getString("semestre"));
                eleve.setMatiere(rs1.getString("nomMatiere"));
                eleve.setComposition(rs1.getFloat("noteComposition"));
                eleve.setAnnee(annee);
                elevePar.add(eleve);
            }
            for (Eleve e : elevePar) {
                System.out.println(e.getComposition() + " " + e.getDevoir1() + " " + e.getDevoir2());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return elevePar;
    }
    public Eleve listerUnEleve(String login) {
        Eleve eleve = null;
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select * from eleve,personne where eleve.login = '" + login + "' and eleve.login=personne.login and etatPers=1";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                eleve = new Eleve(rs.getString("nomClasse"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("dateNaissance"), rs.getString("lieuNaissance"), null, rs.getString("login"), rs.getString("motDePasse"), null);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eleve;
    }
    ///////////////////////////////////////////////Recherche eleve par Prof//////////////////////////////////////////////////////////////
    public ArrayList<Eleve> rechercheEleveProf(String nom, String loginProf){
        ArrayList<Eleve> eleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select distinct * from eleve,profclasse where nom='" + nom + "' and loginProf='"+loginProf+"' and eleve.nomClasse=profclasse.nomClasse";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Eleve e = new Eleve();
                e.setClasse(rs.getString("nomClasse"));
                e.setPrenom(rs.getString("prenom"));
                e.setNom(rs.getString("nom"));
                e.setDateNaissance(rs.getString("dateNaissance"));
                e.setLieuNaissance(rs.getString("lieuNaissance"));
                e.setTel(rs.getString("telephone"));
                e.setNomClasse(rs.getString("nomClasse"));
                eleve.add(e);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eleve;
    }
    ////////////////////////////////////////////////////////Fin Recherche/////////////////////////////////////////////////////////////////////////
    public int compte2(String ancienMdp) {
        String login = "null";
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select personne.login from parent,personne where motDePasse='" + ancienMdp + "' and personne.login=parent.login and etatPers=1";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                login = rs.getString("login");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (!login.equals("null")) {
            return 0;
        } else {
            return 1;
        }
    }
    public void modifierCompte(String login, String nouveauMdp) {
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete1 = "UPDATE  personne SET  motDePasse ='" + nouveauMdp + "'" + "WHERE  login='" + login + "'";
            st = con.createStatement();
            int rs1 = st.executeUpdate(requete1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<String> eleveParent(String loginEleve) {
        Connection con;
        ArrayList<String> annee = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select eleve.anneeScolaire from eleve,personne where eleve.login ='" + loginEleve + "' and personne.etatPers=1 and eleve.login=personne.login";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                annee.add(rs.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return annee;
    }
}


