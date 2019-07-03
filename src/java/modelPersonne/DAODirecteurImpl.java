/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelPersonne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bulletin;
import model.Classe;
import model.Evaluation;
import model.DAOFactory;
import model.Eleve;
import model.Professeur;
import model.Surveillant;
import model.Utilisateur;
import modelTables.Personne;

/**
 *
 * @author Moussa Joseph Sarr
 */
public class DAODirecteurImpl {

    public model.DAOFactory daoFactory;

    public DAODirecteurImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    Connection con;

    public ArrayList<Eleve> consulterNotes(String nomClasse, String nomMatiere, String semestre, String annee, String regime) {

        ArrayList<Eleve> eleves = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select e1.dateNaissance,e1.lieuNaissance,e1.nom, e1.prenom, devoir, noteComposition, e1.telephone from eleve e1, evaluation e2,devoir d, classematiere cm where e1.regime='"+regime+"' and e1.login=e2.login and e1.login=d.login and e1.nomClasse='" + nomClasse + "' and e2.nomMatiere='" + nomMatiere + "'and e2.nomMatiere=d.nomMatiere and cm.nomClasse='" + nomClasse + "' and cm.nomMatiere='" + nomMatiere + "' and e2.semestre='" + semestre + "'and  and d.annee='" + annee + "' and e1.annee=e2.annee and e1.annee='" + annee + "' and order by e1.nom ";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Eleve elev = new Eleve(nomClasse, rs.getString(3), rs.getString(4), null, rs.getString(8), rs.getString(1), rs.getString(2), null, null, null);

                elev.setComposition(rs.getFloat(7));
                elev.setDevoir1(rs.getFloat(5));
                elev.setDevoir2(rs.getFloat(6));
                eleves.add(elev);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eleves;

    }

    public boolean insertMatiere(String nomMatiere) {
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;

            String requete1 = "insert into matiere values(?)";
            pst = con.prepareStatement(requete1);
            pst.setString(1, nomMatiere);

            int result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("l'insertion de matiere a reussie");
                resultat = true;
            } else {

                System.out.println("Erreur de requete(s) : l'insertion de matiere n'a pas reussie");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;
    }

    public boolean insertClasse(String nomClasse, String[] matieres, String regime) {

        boolean resultat = false;
        try {
            con = daoFactory.getConnection();

            PreparedStatement pst;
            String requete1 = "insert into classe values(?,?)";
            pst = con.prepareStatement(requete1);
            pst.setString(1, nomClasse);
            pst.setString(2, regime);
            int result = pst.executeUpdate();

            for (int i = 0; i < matieres.length; i++) {
                PreparedStatement pst1;
                String requete = "insert into classematiere values(?,?,?,?)";
                pst1 = con.prepareStatement(requete);
                pst1.setString(1, nomClasse);
                pst1.setString(2, regime);
                pst1.setString(3, matieres[i]);
                pst1.setInt(4, 0);
                int result1 = pst1.executeUpdate();
            }

            if (result > 0) {
                System.out.println("l'insertion de matiere a reussie");
                resultat = true;
            } else {

                System.out.println("Erreur de requete(s) : l'insertion de matiere n'a pas reussie");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;

    }

    public boolean insertCoef(String nomClasse, String[] nomMatieres, String[] coefs, String regime) {
        int coef = 0;
        int result = 0;
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();
            for (int i = 0; i < coefs.length; i++) {
                coef = Integer.parseInt(coefs[i]);
                PreparedStatement pst;
                System.out.println(nomClasse + " " + nomMatieres[i] + " " + coef);
                String requete1 = "update classematiere set coef=? where nomClasse=? and nomMatiere=?";
                pst = con.prepareStatement(requete1);
                pst.setInt(1, coef);
                pst.setString(2, nomClasse);
                pst.setString(3, nomMatieres[i]);
                result = pst.executeUpdate();

            }
            if (result > 0) {
                System.out.println("la requete a bien été executée");
                resultat = true;
            } else {
                System.out.println("la requete a eu une erreur");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;

    }

    public ArrayList<Classe> listerClasse() {
        ArrayList<Classe> classes = new ArrayList<>();
        
        Statement st1;
        Statement st;
        try {
            con = daoFactory.getConnection();

            String requete = "select nomClasse, regime from classe";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Classe cl = new Classe(rs.getString("nomClasse"), rs.getString("regime"), null);
                String requete1 = "select nomMatiere from classematiere where nomClasse = '" + rs.getString("nomClasse") + "' and regime ='" + rs.getString("regime") + "'";
                st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(requete1);
                ArrayList<String> nomMatiere = new ArrayList<>();
                while(rs1.next()){
                    nomMatiere.add(rs1.getString("nomMatiere"));
                }
                cl.setMatieres(nomMatiere);
                classes.add(cl);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return classes;

    }

    public Bulletin eleve(String login, String annee, String semestre) {

        Bulletin bulletin = new Bulletin();
        Evaluation eva;
        ArrayList<Evaluation> evaluations = new ArrayList<>();
        int totalCoef = 0;
        float totalMoy = 0;
        Statement st;
        Statement st1;
        Statement st2;
        try {
            ///////////requet select pour les donnees de l'eleve//////////////////////////////////////////
            con = daoFactory.getConnection();
            String requete = "select nom,prenom,lieuNaissance,dateNaissance,adresse,nomClasse,retards,absences,moySemestre1,moySemestre2 from eleve where loginEleve = '" + login + "' and annee='" + annee + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                bulletin.setLogin(login);
                bulletin.setNom(rs.getString("nom"));
                bulletin.setPrenom(rs.getString("prenom"));
                bulletin.setNomClasse(rs.getString("nomClasse"));
                bulletin.setLieuNaissance(rs.getString("lieuNaissance"));
                bulletin.setDateNaissance(rs.getString("dateNaissance"));
                bulletin.setAbsences(rs.getInt("absences"));
                bulletin.setRetards(rs.getInt("retards"));
                bulletin.setMoySemestre1(rs.getFloat("moySemestre1"));
                bulletin.setMoySemestre2(rs.getFloat("moySemestre2"));
                ////////////////////requete select pour connaitre le nombre d'eleves/////////////////////////////
                String requete1 = "select count(*) as nombreEleve from eleve where nomClasse = '" + rs.getString("nomClasse") + "' and annee='" + annee + "'";
                st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(requete1);
                if (rs1.next()) {
                    bulletin.setNbreEleve(rs1.getString("nombreEleve"));
                }
            }

            /////////////////////requete select pour recuperer les notes d'evaluations//////////////////////////////
            String requete2 = "select evaluation.nomMatiere,noteDevoir1,noteDevoir2,noteComposition,coef from evaluation,classeMatiere where loginEleve = '" + login + "' and evaluation.nomMatiere=classeMatiere.nomMatiere and nomClasse='" + bulletin.getNomClasse() + "' and semestre='" + semestre + "' and annee='" + annee + "'";
            st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(requete2);
            while (rs2.next()) {
                eva = new Evaluation();
                eva.setNomMatiere(rs2.getString("nomMatiere"));
                eva.setDevoir((rs2.getFloat("noteDevoir1") + rs2.getFloat("noteDevoir2")) / 2);
                System.out.println(eva.getDevoir());
                eva.setComposition(rs2.getFloat("noteComposition"));
                System.out.println(eva.getComposition());
                eva.setCoef(rs2.getInt("coef"));
                eva.setMoyenneCC((eva.getDevoir() + eva.getComposition()) / 2);

                //*******************Appreciations**********************************
                if (eva.getMoyenneCC() >= 19) {
                    eva.setAppreciations("Excellant");
                } else if ((eva.getMoyenneCC() < 19) && (eva.getMoyenneCC() >= 17)) {
                    eva.setAppreciations("Tres Bien");
                } else if ((eva.getMoyenneCC() < 17) && (eva.getMoyenneCC() >= 14)) {
                    eva.setAppreciations("Bien");
                } else if ((eva.getMoyenneCC() < 14) && (eva.getMoyenneCC() >= 12)) {
                    eva.setAppreciations("A.Bien");
                } else if ((eva.getMoyenneCC() < 12) && (eva.getMoyenneCC() >= 10)) {
                    eva.setAppreciations("Passable");
                } else if ((eva.getMoyenneCC() < 10) && (eva.getMoyenneCC() >= 7)) {
                    eva.setAppreciations("Peux mieux faire");
                } else if ((eva.getMoyenneCC() < 7) && (eva.getMoyenneCC() >= 4)) {
                    eva.setAppreciations("Insuffisant");
                } else {
                    eva.setAppreciations("Médiocre");
                }
                eva.setMoyX(eva.getMoyenneCC() * eva.getCoef());
                evaluations.add(eva);
                bulletin.setEvaluation(evaluations);
                totalCoef += rs2.getInt("coef");
                totalMoy += eva.getMoyX();
            }
            bulletin.setTotalCoef(totalCoef);
            System.out.println(totalCoef);
            bulletin.setTotalMoyenne(totalMoy);
            System.out.println(totalMoy);
            bulletin.setMoyenneGenerale(totalMoy / totalCoef);

            ////////////////////requete pour inserer la moyenne generale du 1er semestre ou du 2eme////////////////////////
            PreparedStatement pst;
            String requete1 = null;
            if (semestre.equals("1er_semestre")) {
                requete1 = "update eleve set moySemestre1=? where loginEleve=? and annee=?";
            } else {
                requete1 = "update eleve set moySemestre2=? where loginEleve=? and annee=?";
            }
            pst = con.prepareStatement(requete1);
            pst.setFloat(1, bulletin.getMoyenneGenerale());
            pst.setString(2, login);
            pst.setString(3, annee);
            int result = pst.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return bulletin;

    }

    public void moyenne(String login, String annee, String semestre, String nomClasse) {

        Bulletin bulletin = new Bulletin();
        Evaluation eva;
        ArrayList<Evaluation> evaluations = new ArrayList<>();
        int totalCoef = 0;
        float totalMoy = 0;
        Statement st2;
        try {
            con = daoFactory.getConnection();
            /////////////////////requete select pour recuperer les notes d'evaluations//////////////////////////////
            String requete2 = "select evaluation.nomMatiere,noteDevoir1,noteDevoir2,noteComposition,coef from evaluation,classeMatiere where loginEleve = '" + login + "' and evaluation.nomMatiere=classeMatiere.nomMatiere and nomClasse='" + nomClasse + "' and semestre='" + semestre + "' and annee='" + annee + "'";

            st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(requete2);
            while (rs2.next()) {

                eva = new Evaluation();
                eva.setNomMatiere(rs2.getString("nomMatiere"));
                eva.setDevoir((rs2.getFloat("noteDevoir1") + rs2.getFloat("noteDevoir2")) / 2);
                eva.setComposition(rs2.getFloat("noteComposition"));
                eva.setCoef(rs2.getInt("coef"));
                eva.setMoyenneCC((eva.getDevoir() + eva.getComposition()) / 2);

                eva.setMoyX(eva.getMoyenneCC() * eva.getCoef());
                evaluations.add(eva);
                bulletin.setEvaluation(evaluations);
                totalCoef += rs2.getInt("coef");
                totalMoy += eva.getMoyX();
            }
            bulletin.setTotalCoef(totalCoef);
            System.out.println(totalCoef);
            bulletin.setTotalMoyenne(totalMoy);
            System.out.println(totalMoy);
            bulletin.setMoyenneGenerale(totalMoy / totalCoef);

            ////////////////////requete pour inserer la moyenne generale du 1er semestre ou du 2eme////////////////////////
            PreparedStatement pst;
            String requete1 = null;
            if (semestre.equals("1er_semestre")) {
                requete1 = "update eleve set moySemestre1=? where loginEleve=? and annee=?";
            } else {
                requete1 = "update eleve set moySemestre2=? where loginEleve=? and annee=?";
            }
            pst = con.prepareStatement(requete1);
            pst.setFloat(1, bulletin.getMoyenneGenerale());
            pst.setString(2, login);
            pst.setString(3, annee);
            int result = pst.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void changePasswdDir(String login, String passwd) {

        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;
            String requete1 = "update directeur set motDePasse=? where loginDir=?";
            pst = con.prepareStatement(requete1);
            pst.setString(1, passwd);
            pst.setString(2, login);
            int result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAODirecteurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changePasswdSurv(String login, String passwd) {

        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;
            String requete1 = "update surveillant set motDePasse=? where loginSurv=?";
            pst = con.prepareStatement(requete1);
            pst.setString(1, passwd);
            pst.setString(2, login);
            int result = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAODirecteurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertAnnee(String annee) {
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;

            String requete1 = "insert into annee values(?)";
            pst = con.prepareStatement(requete1);
            pst.setString(1, annee);

            int result = pst.executeUpdate();

            if (result > 0) {
                System.out.println("l'insertion de matiere a reussie");
                resultat = true;
            } else {

                System.out.println("Erreur de requete(s) : l'insertion de matiere n'a pas reussie");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;
    }

    public boolean ajouterSurv(Personne pers, Utilisateur utilisateur) {
        boolean resultat = false;

        try {
            con = daoFactory.getConnection();
            PreparedStatement pst1;
            PreparedStatement pst2;

            String requete1 = "insert into personne values(?,?,?,?,?,?,?,?,?)";
            pst1 = con.prepareStatement(requete1);
            pst1.setString(1, utilisateur.getLogin());
            pst1.setString(2, pers.getNom());
            pst1.setString(3, pers.getPrenom());
            pst1.setString(4, pers.getAdresse());
            pst1.setString(5, pers.getTel());
            pst1.setString(6, utilisateur.getMotDePasse());
            pst1.setString(7, "");
            pst1.setInt(8, 0);
            pst1.setString(9, "DE");
            int result1 = pst1.executeUpdate();

            String requete2 = "insert into surveillant values(?)";
            pst2 = con.prepareStatement(requete2);
            pst2.setString(1, utilisateur.getLogin());
            int result2 = pst2.executeUpdate();

            if ((result1 > 0) && (result2 > 0)) {
                System.out.println("les requetes ont été bien éxécutées");
                resultat = true;
            } else {

                System.out.println("Erreur de requete(s)");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;

    }

    public ArrayList<Surveillant> listerSurv() {

        ArrayList<Surveillant> surv = new ArrayList();
        Statement st;

        try {
            con = daoFactory.getConnection();
            String requete = "select login,nom,prenom,adresse,telephone from personne where profil='DE'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Surveillant sur = new Surveillant();
                sur.setAdresse(rs.getString("adresse"));
                sur.setNom(rs.getString("nom"));
                sur.setPrenom(rs.getString("prenom"));
                sur.setTelephone(rs.getString("telephone"));
                sur.setLogin(rs.getString("login"));
                surv.add(sur);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return surv;
    }

    public ArrayList<Surveillant> selectSurv(String loginSurv) {

        ArrayList<Surveillant> surv = new ArrayList();
        Statement st;

        try {
            con = daoFactory.getConnection();
            String requete = "select nom,prenom,adresse,telephone from personne where login='" + loginSurv + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Surveillant sur = new Surveillant();
                sur.setAdresse(rs.getString("adresse"));
                sur.setNom(rs.getString("nom"));
                sur.setPrenom(rs.getString("prenom"));
                sur.setTelephone(rs.getString("telephone"));
                sur.setLogin(loginSurv);
                surv.add(sur);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return surv;
    }

    public void updateSurv(String loginSurv, String nom, String prenom, String adresse, String tel) {
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;

            String requete1 = "update personne set nom=?,prenom=?,adresse=?,telephone=? where login=?";
            pst = con.prepareStatement(requete1);
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, adresse);
            pst.setString(4, tel);
            pst.setString(5, loginSurv);
            int result = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAODirecteurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void desactiverSurv(int idPers) {
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;

            String requete1 = "update surveillant set active=? where idPersonne=?";
            pst = con.prepareStatement(requete1);
            pst.setInt(1, 1);
            pst.setInt(2, idPers);
            int result = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAODirecteurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
