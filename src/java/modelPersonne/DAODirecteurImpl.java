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
import model.Absence;
import model.Bulletin;
import model.Classe;
import model.Evaluation;
import model.DAOFactory;
import model.Eleve;
import model.Matiere_Absence;
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
            String requete = "select e1.dateNaissance,e1.lieuNaissance,p.nom, p.prenom, devoir, noteComposition, p.telephone from personne p, eleve e1, evaluation e2,devoir d, classematiere cm where e1.regime='" + regime + "' and p.login=e1.login and e1.login=e2.login and e1.login=d.login and e1.nomClasse='" + nomClasse + "' and e2.nomMatiere='" + nomMatiere + "' and e2.nomMatiere=d.nomMatiere and cm.nomClasse='" + nomClasse + "' and cm.nomMatiere='" + nomMatiere + "' and cm.regime='" + regime + "' and e2.semestre=d.semestre and e2.semestre='" + semestre + "' and d.anneeScolaire='" + annee + "' and e1.anneeScolaire=e2.anneeScolaire and e1.anneeScolaire='" + annee + "' order by p.nom ";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Eleve elev = new Eleve(nomClasse, rs.getString(3), rs.getString(4), null, rs.getString(7), rs.getString(1), rs.getString(2), null, null, null);

                elev.setComposition(rs.getFloat(6));
                elev.setDevoir(rs.getFloat(5));
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

    public Utilisateur validerInscr(String login) {
        Utilisateur user = new Utilisateur();
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;
            Statement st;

            String requete1 = "update personne set etatPers=1 where login = ?";
            pst = con.prepareStatement(requete1);
            pst.setString(1, login);
            int result = pst.executeUpdate();

            String requete = "select login, motDePasse from personne where login = '" + login + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                user.setLogin(rs.getString("login"));
                user.setMotDePasse(rs.getString("motDePasse"));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public boolean supprimerMatiere(String nomMatiere) {
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;

            String requete1 = "delete from matiere where nomMatiere=?";
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

    public boolean modifierMatiere(String nomMatiere, String oldMatiere) {
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;

            String requete1 = "update  matiere set nomMatiere=? where nomMatiere=?";
            pst = con.prepareStatement(requete1);
            pst.setString(1, nomMatiere);
            pst.setString(2, oldMatiere);
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

    public boolean insertClasse(String nomClasse, String[] matieres,String[] coefs, String regime) {

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
                pst1.setInt(4, Integer.parseInt(coefs[i]));
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
                PreparedStatement pst;
                String requete1 = "update classematiere set coef=" + Integer.parseInt(coefs[i]) + " where nomClasse='" + nomClasse + "' and nomMatiere='" + nomMatieres[i] + "'";
                pst = con.prepareStatement(requete1);
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

    public boolean modifierNomClasse(String nomClasse, String regime, String old) {
        int coef = 0;
        int result = 0;
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();

            PreparedStatement pst;
            String requete1 = "update classe set nomClasse='" + nomClasse + "' where nomClasse='" + old + "' and regime='" + regime + "'";
            pst = con.prepareStatement(requete1);
            result = pst.executeUpdate();

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

    public boolean modifierRegime(String updateRegime, String nomcl, String oldregime) {

        int result = 0;
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();

            PreparedStatement pst;
            System.out.println(updateRegime + " " + nomcl + " " + oldregime);
            String requete1 = "update classe set regime='" + updateRegime + "' where nomClasse='" + nomcl + "' and regime='" + oldregime + "'";
            pst = con.prepareStatement(requete1);
            result = pst.executeUpdate();

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

    public boolean deleteMatFromClasse(String nomClasse, String regime, String matiere) {
        int result = 0;
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();

            PreparedStatement pst;
            String requete1 = "delete from classematiere where nomClasse='" + nomClasse + "' and regime='" + regime + "' and nomMatiere='" + matiere + "'";
            pst = con.prepareStatement(requete1);
            result = pst.executeUpdate();

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
                while (rs1.next()) {
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

    public Bulletin eleve(String login, String annee, String semestre, String regime) {

        Bulletin bulletin = new Bulletin();
        Evaluation eva;
        ArrayList<Evaluation> evaluations = new ArrayList<>();
        int totalCoef = 0;
        float totalMoy = 0;
        Statement st;
        Statement st1;
        Statement st2;
        Statement std;
        try {
            ///////////requet select pour les donnees de l'eleve//////////////////////////////////////////
            con = daoFactory.getConnection();
            String requete = "select nom,prenom,regime,lieuNaissance,dateNaissance,adresse,nomClasse,moySemestre1,moySemestre2 from eleve,personne where eleve.login = '" + login + "'and eleve.login=personne.login and anneeScolaire='" + annee + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                bulletin.setLogin(login);
                bulletin.setNom(rs.getString("nom"));
                bulletin.setPrenom(rs.getString("prenom"));
                bulletin.setRegime(rs.getString("regime"));
                bulletin.setNomClasse(rs.getString("nomClasse"));
                bulletin.setLieuNaissance(rs.getString("lieuNaissance"));
                bulletin.setDateNaissance(rs.getString("dateNaissance"));
                String req = "select sum(durée_abs) as abs,sum(nbre_retard) as retard from absence where login = '" + login + "' and semestre='" + semestre + "' and anneeScolaire='" + annee + "'";
                std = con.createStatement();
                ResultSet rsd = std.executeQuery(req);
                float devoir = 0;
                while (rsd.next()) {
                    bulletin.setAbsences(rsd.getInt("abs"));
                    bulletin.setRetards(rsd.getInt("retard"));
                }

                bulletin.setMoySemestre1(rs.getFloat("moySemestre1"));
                bulletin.setMoySemestre2(rs.getFloat("moySemestre2"));
                ////////////////////requete select pour connaitre le nombre d'eleves/////////////////////////////
                String requete1 = "select count(*) as nombreEleve from eleve where nomClasse = '" + rs.getString("nomClasse") + "' and anneeScolaire='" + annee + "'";
                st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(requete1);
                if (rs1.next()) {
                    bulletin.setNbreEleve(rs1.getString("nombreEleve"));
                }
            }

            /////////////////////requete select pour recuperer les notes d'evaluations//////////////////////////////
            String requete2 = "select evaluation.nomMatiere,noteComposition,coef from evaluation,classeMatiere where login = '" + login + "' and evaluation.nomMatiere=classeMatiere.nomMatiere and nomClasse='" + bulletin.getNomClasse() + "' and semestre='" + semestre + "' and anneeScolaire='" + annee + "' and regime='" + regime + "'";
            st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(requete2);
            while (rs2.next()) {
                String req = "select devoir from devoir where login = '" + login + "' and nomMatiere='" + rs2.getString("nomMatiere") + "' and semestre='" + semestre + "' and anneeScolaire='" + annee + "'";
                std = con.createStatement();
                ResultSet rsd = std.executeQuery(req);
                float devoir = 0;
                while (rsd.next()) {
                    devoir += rsd.getFloat("devoir");
                }
                eva = new Evaluation();
                eva.setNomMatiere(rs2.getString("nomMatiere"));
                eva.setDevoir(devoir / 2);
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
                requete1 = "update eleve set moySemestre1=? where login=? and anneeScolaire=?";
            } else {
                requete1 = "update eleve set moySemestre2=? where login=? and anneeScolaire=?";
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

    public Bulletin recapMoyenne(String login, String annee, String semestre, String regime) {

        Bulletin bulletin = new Bulletin();
        Statement st;
        Statement st1;
        Statement std;
        try {
            ///////////requet select pour les donnees de l'eleve//////////////////////////////////////////
            con = daoFactory.getConnection();
            String requete = "select nom,prenom,regime,lieuNaissance,dateNaissance,adresse,nomClasse,moySemestre1,moySemestre2 from eleve,personne where eleve.login = '" + login + "'and eleve.login=personne.login and anneeScolaire='" + annee + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                bulletin.setLogin(login);
                bulletin.setNom(rs.getString("nom"));
                bulletin.setPrenom(rs.getString("prenom"));
                bulletin.setRegime(rs.getString("regime"));
                bulletin.setNomClasse(rs.getString("nomClasse"));
                bulletin.setLieuNaissance(rs.getString("lieuNaissance"));
                bulletin.setDateNaissance(rs.getString("dateNaissance"));
                bulletin.setLogin(login);
                String req = "select sum(durée_abs) as abs,sum(nbre_retard) as retard from absence where login = '" + login + "' and semestre='" + semestre + "' and anneeScolaire='" + annee + "'";
                std = con.createStatement();
                ResultSet rsd = std.executeQuery(req);
                while (rsd.next()) {
                    bulletin.setAbsences(rsd.getInt("abs"));
                    bulletin.setRetards(rsd.getInt("retard"));
                }

                bulletin.setMoySemestre1(rs.getFloat("moySemestre1"));
                bulletin.setMoySemestre2(rs.getFloat("moySemestre2"));
                if (semestre.equals("1er_semestre")) {
                    if ((rs.getFloat("moySemestre1") >= 0) && (rs.getFloat("moySemestre1") <5)) {
                        bulletin.setAppreciation("Tres Faible-Blame");
                    } else if ((rs.getFloat("moySemestre1") >= 5) && (rs.getFloat("moySemestre1") < 8)) {
                        bulletin.setAppreciation("Assez Faible");
                    } else if ((rs.getFloat("moySemestre1") >= 8) && (rs.getFloat("moySemestre1") < 10)) {
                        bulletin.setAppreciation("Peux mieux Faire");
                    } else if ((rs.getFloat("moySemestre1") >= 10) && (rs.getFloat("moySemestre1") < 12)) {
                        bulletin.setAppreciation("Passable");
                    } else if ((rs.getFloat("moySemestre1") >= 12) && (rs.getFloat("moySemestre1") < 14)) {
                        bulletin.setAppreciation("ABien-TH");
                    } else if ((rs.getFloat("moySemestre1") >= 14) && (rs.getFloat("moySemestre1") < 16)) {
                        bulletin.setAppreciation("Bien-TH-Enc");
                    } else if ((rs.getFloat("moySemestre1") >= 16) && (rs.getFloat("moySemestre1") < 18)) {
                        bulletin.setAppreciation("TrèsBien-TH-Enc");
                    } else if ((rs.getFloat("moySemestre1") >= 18) && (rs.getFloat("moySemestre1") < 20)) {
                        bulletin.setAppreciation("Excellent-TH-Enc");
                    }
                } else if (semestre.equals("2eme_semestre")) {
                    if ((rs.getFloat("moySemestre2") >= 0) && (rs.getFloat("moySemestre2") <5)) {
                        bulletin.setAppreciation("Tres Faible-Blame");
                    } else if ((rs.getFloat("moySemestre2") >= 5) && (rs.getFloat("moySemestre2") < 8)) {
                        bulletin.setAppreciation("Assez Faible");
                    } else if ((rs.getFloat("moySemestre2") >= 8) && (rs.getFloat("moySemestre2") < 10)) {
                        bulletin.setAppreciation("Peux mieux Faire");
                    } else if ((rs.getFloat("moySemestre2") >= 10) && (rs.getFloat("moySemestre2") < 12)) {
                        bulletin.setAppreciation("Passable");
                    } else if ((rs.getFloat("moySemestre2") >= 12) && (rs.getFloat("moySemestre2") < 14)) {
                        bulletin.setAppreciation("ABien-TH");
                    } else if ((rs.getFloat("moySemestre2") >= 14) && (rs.getFloat("moySemestre2") < 16)) {
                        bulletin.setAppreciation("Bien-TH-Enc");
                    } else if ((rs.getFloat("moySemestre2") >= 16) && (rs.getFloat("moySemestre2") < 18)) {
                        bulletin.setAppreciation("TrèsBien-TH-Enc");
                    } else if ((rs.getFloat("moySemestre2") >= 18) && (rs.getFloat("moySemestre2") < 20)) {
                        bulletin.setAppreciation("Excellent-TH-Enc");
                    }
                }
                ////////////////////requete select pour connaitre le nombre d'eleves/////////////////////////////
                String requete1 = "select count(*) as nombreEleve from eleve where nomClasse = '" + rs.getString("nomClasse") + "' and anneeScolaire='" + annee + "'";
                st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(requete1);
                if (rs1.next()) {
                    bulletin.setNbreEleve(rs1.getString("nombreEleve"));
                }
            }
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
        Statement st;
        try {
            con = daoFactory.getConnection();
            /////////////////////requete select pour recuperer les notes d'evaluations//////////////////////////////
            String requete2 = "select evaluation.nomMatiere,noteComposition,coef from evaluation,classeMatiere where login = '" + login + "' and evaluation.nomMatiere=classeMatiere.nomMatiere and nomClasse='" + nomClasse + "' and semestre='" + semestre + "' and anneeScolaire='" + annee + "'";
            st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(requete2);
            while (rs2.next()) {

                String req = "select devoir from devoir where login = '" + login + "' and nomMatiere='" + rs2.getString("nomMatiere") + "' and semestre='" + semestre + "' and anneeScolaire='" + annee + "'";
                st = con.createStatement();
                ResultSet rs = st.executeQuery(req);
                float devoir = 0;
                while (rs.next()) {
                    devoir += rs.getFloat("devoir");
                }
                eva = new Evaluation();
                eva.setNomMatiere(rs2.getString("nomMatiere"));
                eva.setDevoir(devoir / 2);
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
                requete1 = "update eleve set moySemestre1=? where login=? and anneeScolaire=?";
            } else {
                requete1 = "update eleve set moySemestre2=? where login=? and anneeScolaire=?";
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
            String requete1 = "update personne set motDePasse=? where login=?";
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
            String requete1 = "update personne set motDePasse=? where login=?";
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
            pst1.setString(7, null);
            pst1.setInt(8, 1);
            pst1.setString(9, pers.getProfil());
            int result1 = pst1.executeUpdate();

            String requete2 = "";
            String profil = pers.getProfil();
            System.out.println(profil);
            if (profil.equals("Directeur des études")) {
                requete2 = "insert into directeur_études values(?)";
            } else if (profil.equals("Surveillant Général")) {
                requete2 = "insert into surveillant_general values(?)";
            } else if (profil.equals("Surveillant")) {
                requete2 = "insert into surveillant values(?)";
            } else if (profil.equals("Comptable")) {
                requete2 = "insert into comptable values(?)";
            }
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

    public ArrayList<Personne> listerAdmin(String profil) {

        ArrayList<Personne> perso = new ArrayList();
        Statement st;

        try {
            con = daoFactory.getConnection();
            String requete = "select login,nom,prenom,adresse,telephone from personne where profil='" + profil + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Personne per = new Personne();
                per.setAdresse(rs.getString("adresse"));
                per.setNom(rs.getString("nom"));
                per.setPrenom(rs.getString("prenom"));
                per.setTel(rs.getString("telephone"));
                per.setLogin(rs.getString("login"));
                perso.add(per);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return perso;
    }

    public ArrayList<Personne> select(String login) {

        ArrayList<Personne> perso = new ArrayList();
        Statement st;

        try {
            con = daoFactory.getConnection();
            String requete = "select nom,prenom,adresse,telephone,profil from personne where login='" + login + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Personne pers = new Personne();
                pers.setAdresse(rs.getString("adresse"));
                pers.setNom(rs.getString("nom"));
                pers.setPrenom(rs.getString("prenom"));
                pers.setTel(rs.getString("telephone"));
                pers.setLogin(login);
                pers.setProfil(rs.getString("profil"));
                perso.add(pers);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return perso;
    }

    public void updateSurv(String login, String nom, String prenom, String adresse, String tel, String profil) {
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;

            String requete1 = "update personne set nom=?,prenom=?,adresse=?,telephone=?,profil=? where login=?";
            pst = con.prepareStatement(requete1);
            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, adresse);
            pst.setString(4, tel);
            pst.setString(5, profil);
            pst.setString(6, login);
            int result = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAODirecteurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerPers(String login) {
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;

            String requete1 = "delete from personne where login=?";
            pst = con.prepareStatement(requete1);
            pst.setString(1, login);
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

    public boolean enregistrerAbsence(String annee, String login, String nomMatiere, String semestre, String sujet, int absence) {

        boolean resultat = false;
        try {
            con = daoFactory.getConnection();

            Statement st;
            int result = 0;
            PreparedStatement pst = null;
            String requete1 = null;

            if (sujet.equals("Absence")) {
                String requete = "select  durée_abs from absence where login='" + login + "' and anneeScolaire='" + annee + "' and semestre='" + semestre + "'and nomMatiere='" + nomMatiere + "'";
                st = con.createStatement();
                ResultSet rs = st.executeQuery(requete);
                if (rs.next()) {
                    requete1 = "update absence set durée_abs=? where login=? and nomMatiere=? and semestre=? and anneeScolaire=?";
                    pst = con.prepareStatement(requete1);
                    pst.setInt(1, rs.getInt("durée_abs") + absence);
                    pst.setString(2, login);
                    pst.setString(3, nomMatiere);
                    pst.setString(4, semestre);
                    pst.setString(5, annee);
                    result = pst.executeUpdate();
                }

            } else if (sujet.equals("Retard")) {
                String requete = "select  nbre_retard from absence where login='" + login + "' and anneeScolaire='" + annee + "' and semestre='" + semestre + "'and nomMatiere='" + nomMatiere + "'";
                st = con.createStatement();
                ResultSet rs = st.executeQuery(requete);
                if (rs.next()) {
                    requete1 = "update absence set nbre_retard=? where login=? and nomMatiere=? and semestre=? and anneeScolaire=?";
                    pst = con.prepareStatement(requete1);
                    pst.setInt(1, rs.getInt("nbre_retard") + 1);
                    pst.setString(2, login);
                    pst.setString(3, nomMatiere);
                    pst.setString(4, semestre);
                    pst.setString(5, annee);
                    result = pst.executeUpdate();
                }

            }
            if (result > 0) {
                System.out.println("les requetes ont été bien éxécutées");
                resultat = true;
            } else {

                System.out.println("Erreur de requete(s)");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAODirecteurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultat;
    }

    public ArrayList<Absence> listerAbsence(String nomClasse, String regime, String semestre, String annee) {

        ArrayList<Absence> absents = new ArrayList();
        Statement st;
        Statement st1;

        try {
            con = daoFactory.getConnection();
            String requete = "select sum(durée_abs) as abs,sum(nbre_retard) as retard,eleve.login,nom,prenom from absence,eleve,personne where eleve.login=personne.login and nomClasse='" + nomClasse + "' and regime='" + regime + "' and semestre='" + semestre + "' and eleve.login=absence.login and eleve.anneeScolaire=absence.anneeScolaire and absence.anneeScolaire='" + annee + "' group by absence.login";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                ArrayList<Matiere_Absence> matabs = new ArrayList<>();
                Absence abs = new Absence();
                System.out.println(rs.getString("nom"));
                abs.setNom(rs.getString("nom"));
                abs.setPrenom(rs.getString("prenom"));
                abs.setTotalAbsence(rs.getInt("abs"));
                abs.setTotalRetard(rs.getInt("retard"));
                abs.setLogin(rs.getString("eleve.login"));
                abs.setSemestre(semestre);
                String requete1 = "select durée_abs,nbre_retard,nomMatiere from absence where login='" + rs.getString("eleve.login") + "' and anneeScolaire='" + annee + "' and semestre='" + semestre + "'";
                st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(requete1);
                while (rs1.next()) {
                    Matiere_Absence ma = new Matiere_Absence();
                    System.out.println(rs1.getString("nomMatiere"));
                    ma.setMatiere(rs1.getString("nomMatiere"));
                    ma.setHeureRetard(rs1.getInt("nbre_retard"));
                    ma.setHeureAbsence(rs1.getInt("durée_abs"));
                    matabs.add(ma);
                }
                abs.setMatAbs(matabs);

                absents.add(abs);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return absents;
    }

    public void mofifierAbs(String login, String matiere, String annee, String semestre, int abs, int ret) {
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;

            String requete1 = "update absence set durée_abs=?, nbre_retard=? where login=? and nomMatiere=? and anneeScolaire=? and semestre=?";
            pst = con.prepareStatement(requete1);
            pst.setInt(1, abs);
            pst.setInt(2, ret);
            pst.setString(3, login);
            pst.setString(4, matiere);
            pst.setString(5, annee);
            pst.setString(6, semestre);
            int result = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DAODirecteurImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<String> classeRegime(String login, String annee) {

        ArrayList<String> perso = new ArrayList<>();
        Statement st;

        try {
            con = daoFactory.getConnection();
            String requete = "select nomClasse,regime from eleve where login='" + login + "' and anneeScolaire='" + annee + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);

            if (rs.next()) {

                perso.add(rs.getString("nomClasse"));
                perso.add(rs.getString("regime"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return perso;
    }
}
