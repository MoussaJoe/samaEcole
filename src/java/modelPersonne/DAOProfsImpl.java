/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelPersonne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Classe;
import model.DAOFactory;
import model.Eleve;
import model.EleveDevoir;
import model.EleveRecherche;
import model.ListEleve;
import model.MessageRecu;
import model.Parent;
import model.Prof;
import model.Reclamation;
import model.Utilisateur;
import modelTables.Personne;

/**
 *
 * @author ibrah
 */
public class DAOProfsImpl {

    private DAOFactory daoFactory;
    public Eleve eleve = new Eleve();
    public ListEleve eleve1 = new ListEleve();
    private static int idDevoir = 1;

    public DAOProfsImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    Connection con;
        

    public ArrayList<Eleve> listEleve(String classe, String matiere, String annee,String regime) {
        ArrayList<Eleve> listEleve = new ArrayList<>();
        Connection con;
        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
//            String requete = "select distinct eleve.loginEleve,prenom,nom,classematiere.nomMatiere,classe.nomClasse FROM classe,eleve,classematiere,evaluation where classematiere.nomMatiere='" + matiere + "' and classe.nomClasse='" + classe + "' order by nom";
//            String requete = "select distinct eleve.loginEleve,prenom,nom,matiere.nomMatiere,classe.nomClasse,note,evaluation.idEvaluation FROM classe,eleve,matiere,classematiere,evaluation where classematiere.nomMatiere='"+matiere+"' and classe.nomClasse='"+classe+"' and classematiere.nomClasse=eleve.nomClasse and matiere.idEvaluation=evaluation.idEvaluation order by nom";
            String requete = "select eleve.login,prenom,nom,classematiere.nomMatiere FROM eleve,classematiere,personne where classematiere.nomMatiere='" + matiere + "' and classematiere.nomClasse='" + classe + "' and eleve.nomClasse='" + classe + "' and eleve.anneeScolaire='" + annee + "' and personne.login=eleve.login and personne.etatPers=1 and eleve.regime='" + regime + "' and classematiere.regime='" + regime + "' order by nom";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                eleve = new Eleve();
                eleve.setNom(rs.getString("nom"));
                eleve.setPrenom(rs.getString("prenom"));
                eleve.setClasse(classe);
                eleve.setMatiere(matiere);
                eleve.setMatriculeEleve(rs.getString("login"));
                listEleve.add(eleve);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listEleve;
    }

    public void ajouterNoteComposition(String composition, String matricule, String semestre, String annee, String matiere) {
        Connection con;
        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
            String requete = "insert into evaluation (nomMatiere,login,anneeScolaire,semestre,noteComposition) values ('" + matiere + "','" + matricule + "','" +annee  + "','" + semestre + "'," + composition + ")";
            pst = con.prepareStatement(requete);
            System.out.println(matiere + " " + matricule + " " + annee + " " + semestre + " " + composition);
//            pst.setString(1, matiere);
//            pst.setString(2, matricule);
//            pst.setString(3, semestre);
//            pst.setString(4, annee);
//            pst.setFloat(5, Float.parseFloat(composition));
            int rs = pst.executeUpdate(requete);
            pst.close();
            if (rs > 0) {
                System.out.println("la requete est bien exécutée");
            } else {
                System.out.println("Erreur d'exécution");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ajouterNoteDevoir(String note, String matricule, String semestre, String matiere, String annee) {
        Connection con;
        Statement st;
        PreparedStatement pst;

        try {
            con = daoFactory.getConnection();
//            String requete = "UPDATE  elevevaluation SET  note ='" + note + "'" + "WHERE  matriculeEleve='"+matricule+"'";
            String requete = "insert into devoir (nomMatiere,login,semestre,anneeScolaire,devoir) values('" + matiere + "','" + matricule + "','" + semestre + "','" + annee + "'," + note + ")";
            pst = con.prepareStatement(requete);
//            pst.setInt(1, idDevoir);
//            pst.setString(2, matiere);
//            pst.setString(4, matricule);
//            pst.setString(5, semestre);
//            pst.setString(6, note);
            int i = pst.executeUpdate(requete);
            if (i > 0) {
                System.out.println("la requete est bien exécutée");
            } else {
                System.out.println("Erreur d'exécution");
            }
            pst.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<ListEleve> listNoteComposition(String classe, String matiere, String annee,String regime) {
        ArrayList<ListEleve> list = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select distinct eleve.login,prenom,nom,evaluation.nomMatiere,classe.nomClasse,evaluation.noteComposition,evaluation.semestre,evaluation.anneeScolaire"
                    + " FROM classe,eleve,evaluation,personne,profclasse p,classematiere"
                    + " where evaluation.nomMatiere='" + matiere + "' and classe.nomClasse='" + classe + "' and eleve.login=evaluation.login and eleve.nomClasse='" + classe + "'"
                    + " and evaluation.anneeScolaire='" + annee + "' and p.nomClasse='" + classe + "'"
                    + " and classematiere.nomMatiere='" + matiere + "' and classematiere.nomClasse=p.nomClasse and p.anneeScolaire='" + annee + "'"
                    + " and eleve.anneeScolaire='" + annee + "' and eleve.regime='" + regime + "' and evaluation.anneeScolaire=eleve.anneeScolaire and personne.etatPers=1 "
                    + "and personne.login=eleve.login order by nom";
//            String requete = "select eleve.loginEleve,prenom,nom,classematiere.nomMatiere,classe.nomClasse,elevevaluation.devoir1,elevevaluation.devoir2,elevevaluation.composition,evaluation.idEvaluation FROM classe,eleve,classematiere,evaluation,elevevaluation where elevevaluation.loginEleve=eleve.loginEleve and classematiere.nomMatiere='" + matiere + "' and classe.nomClasse='" + classe + "' and eleve.nomClasse=classe.nomClasse order by nom ";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                eleve1 = new ListEleve();
                eleve1.setNom(rs.getString("nom"));
                eleve1.setPrenom(rs.getString("prenom"));
                eleve1.setClasse(classe);
                eleve1.setMatiere(matiere);
                eleve1.setMatriculeEleve(rs.getString("login"));
                eleve1.setComposition(rs.getFloat("noteComposition"));
                eleve1.setSemestre(rs.getString("semestre"));
                eleve1.setAnnee(rs.getString("anneeScolaire"));
                list.add(eleve1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<ListEleve> listNoteDevoir(String classe, String matiere, String annee, String regime) {
        ArrayList<ListEleve> list = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select devoir.idDevoir,eleve.login,devoir.nomMatiere,devoir.semestre,devoir.anneeScolaire,devoir,personne.prenom,personne.nom from personne,eleve,devoir,classematiere "
                    + "where eleve.nomClasse=classematiere.nomClasse and classematiere.nomMatiere='" + matiere + "' and devoir.nomMatiere=classematiere.nomMatiere and eleve.login=personne.login and eleve.login=devoir.login "
                    + "and eleve.anneeScolaire='" + annee + "' and eleve.anneeScolaire=devoir.anneeScolaire and eleve.regime='" + regime + "' and eleve.nomClasse='" + classe + "' and eleve.regime=classematiere.regime order by nom";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                eleve1 = new ListEleve();
                eleve1.setNom(rs.getString("nom"));
                eleve1.setPrenom(rs.getString("prenom"));
                eleve1.setClasse(classe);
                eleve1.setMatiere(matiere);
                eleve1.setMatriculeEleve(rs.getString("login"));
                eleve1.setDevoir1(rs.getFloat("devoir"));
                eleve1.setSemestre(rs.getString("semestre"));
                eleve1.setAnnee(rs.getString("anneeScolaire"));
                eleve1.setIdDevoir(rs.getInt("idDevoir"));
                list.add(eleve1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /*joe*/ public ArrayList<String> eleveParent(String loginEleve) {

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

    public ArrayList<Eleve> eleveParent(String annee, String loginEleve) {
        ArrayList<Eleve> elevePar = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete1 = "select  devoir,nomMatiere,semestre from devoir,eleve,personne where devoir.anneeScolaire='" + annee + "'and devoir.login='" + loginEleve + "' and devoir.login=eleve.login and eleve.login=personne.login and etatPers=1";
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

    public void modifierNoteCompo(String composition, String loginEleve, String matiere, String semestre, String annee) {
        Connection con;
        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
            String requete = "UPDATE  evaluation SET  noteComposition ='" + composition + "'" + "WHERE  login='" + loginEleve + "' and nomMatiere='" + matiere + "' and semestre='" + semestre + "' and anneeScolaire='" + annee + "'";
            st = con.createStatement();
            int rs = st.executeUpdate(requete);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifierNoteDevoir(String devoir, String idDevoir, String login) {
        Connection con;
        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
            String requete = "UPDATE  devoir SET  devoir =? WHERE  idDevoir=? and login=?";
            pst = con.prepareStatement(requete);
            pst.setString(1, devoir);
            pst.setString(2, idDevoir);
            pst.setString(3, login);
            int result0 = pst.executeUpdate();
            if (result0 > 0) {
                System.out.println("Requete bien executee");
            } else {
                System.out.println("Erreur d'execution");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String verifProf(String matiere, String classe) {
        String loginProf = "";
        Connection con;
        Statement st;
        Prof pro = new Prof();
        try {
            con = daoFactory.getConnection();
            String requete = "select profclasse.login from profclasse,profmatiere,professeur,personne where profclasse.login=profmatiere.login and profmatiere.nomMatiere='" + matiere + "'" + " and profclasse.nomClasse='" + classe + "' and personne.etatPers=1 and personne.login=professeur.login";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                pro.setLoginProf(rs.getString("login"));
                loginProf = rs.getString("login");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return loginProf;

    }

    public ArrayList<String> selectMatiere(String loginProf) {
        ArrayList<String> listMatiere = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomMatiere from profmatiere where login='" + loginProf + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                listMatiere.add(rs.getString("nomMatiere"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listMatiere;
    }

    public ArrayList<Classe> selectClasse(String loginProf) {
        ArrayList<Classe> listClasse = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomClasse,regime from profclasse where login='" + loginProf + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Classe cl= new Classe();
                cl.setNomClasse(rs.getString("nomClasse"));
                cl.setRegime(rs.getString("regime"));
                listClasse.add(cl);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listClasse;
    }

    public ArrayList<Utilisateur> compte(String loginProf) {
        Utilisateur uti;
        ArrayList<Utilisateur> compteProf = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select personne.login,motDePasse from personne,professeur where professeur.login='" + loginProf + "' and professeur.login=personne.login";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                uti = new Utilisateur();
                uti.setLogin(rs.getString("login"));
                uti.setMotDePasse(rs.getString("motDePasse"));
                compteProf.add(uti);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return compteProf;
    }

    public void modifierCompte(String login, String newPassword) {
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete1 = "UPDATE  personne SET  motDePasse ='" + newPassword + "'" + "WHERE  login='" + login + "'";
            st = con.createStatement();
            int rs1 = st.executeUpdate(requete1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<EleveDevoir> selectEleve(String classe,String annnee,String regime) {
        ArrayList<EleveDevoir> listEleve = new ArrayList<>();
        EleveDevoir elD;
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nom,prenom from personne,eleve where personne.login=eleve.login and nomClasse='"+classe+"' and anneeScolaire='"+annnee+"' and eleve.regime='"+regime+"' order by nom";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                elD=new EleveDevoir();
                elD.setNom(rs.getString("nom"));
                elD.setPrenom(rs.getString("prenom"));
                listEleve.add(elD);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listEleve;
    }

    public ArrayList<String> selectAnnee() {
        ArrayList<String> listAnnee = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select distinct anneeScolaire from eleve ";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                listAnnee.add(rs.getString("anneeScolaire"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listAnnee;
    }

    public String verifProf1(String matiere, String classe, String loginProf,String regime) {
        String annne = "";
        Connection con;
        Statement st;
        Prof pro = new Prof();
        try {
            con = daoFactory.getConnection();
            String requete = "select anneeScolaire from profclasse pc,classematiere cl,profmatiere p where pc.login='" + loginProf + "'" + " and pc.login=p.login and pc.nomClasse='" + classe + "' and pc.nomClasse=cl.nomClasse and cl.nomMatiere='" + matiere + "' and cl.regime='" + regime + "' and cl.nomMatiere=p.nomMatiere";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                pro.setLoginProf(rs.getString("anneeScolaire"));
                annne = rs.getString("anneeScolaire");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return annne;
    }

    public int compte2(String ancienMdp) {
        String login = "null";
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select personne.login from personne,professeur where motDePasse='" + ancienMdp + "' and personne.login=professeur.login";
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

    public ArrayList<MessageRecu> messageRecuProf(String pro_login) {

        ArrayList<MessageRecu>listMsg= new ArrayList<>();
        MessageRecu mess;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select pro_login,message.login,texteMsg,dateMsg,enTete,statut,prenom,nom from message,personne where pro_login='"+pro_login+"' and message.login=personne.login and statut='recu'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               mess=new MessageRecu();
               mess.setPro_login(rs.getString("pro_login"));
               mess.setLogin(rs.getString("login"));
               mess.setTexteMsg(rs.getString("texteMsg"));
               mess.setDateMsg(rs.getString("dateMsg"));
               mess.setEnTete(rs.getString("enTete"));
               mess.setStatut(rs.getString("statut"));
               mess.setPrenom(rs.getString("prenom"));
               mess.setNom(rs.getString("nom"));
               listMsg.add(mess);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listMsg;
    }
    public ArrayList<String> selectMessageRecuProf(String pro_login,String login) {

        ArrayList<String>listMsg= new ArrayList<>();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select texteMsg from message where pro_login='"+pro_login+"' and login='"+login+"' and statut='recu' order by dateMsg,heure";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               listMsg.add(rs.getString("texteMsg"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listMsg;
    }
    public ArrayList<String> selectMessageEnvoyeProf(String pro_login,String login) {

        ArrayList<String>listMsg= new ArrayList<>();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select texteMsg from message where pro_login='"+pro_login+"' and login='"+login+"' and statut='envoye' order by dateMsg,heure";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               listMsg.add(rs.getString("texteMsg"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listMsg;
    }
    public void reponseProf(String pro_login,String login,String texteMsg,String date,String heure) {
        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
            String requete = "insert into message (login,Pro_login,texteMsg,dateMsg,statut,heure) values(?,?,?,?,?,?)";
            pst = con.prepareStatement(requete);
            pst.setString(1, login);
            pst.setString(2, pro_login);
            pst.setString(3, texteMsg);
            pst.setString(4, date);
            pst.setString(5, "envoye");
            pst.setString(6, heure);
            int result0 = pst.executeUpdate();
            if(result0>0){
                System.out.println("requete bien execute");
            }
            else{
                System.out.println("Erreur requete");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insereReclamation(String loginEleve, String loginProf, String message, String enTete, String date) {

        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
            String requete = "insert into reclamation (loginEleve,loginProf,enTete,message,Date,reponse,lue) values(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(requete);
            pst.setString(1, loginEleve);
            pst.setString(2, loginProf);
            pst.setString(3, enTete);
            pst.setString(4, message);
            pst.setString(5, date);
            pst.setInt(6, 0);
            pst.setInt(7, 0);
            int result0 = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Reclamation> selectReclamation(String loginProf) {

        ArrayList<Reclamation> message = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select enTete,message,loginEleve,Date,idReclam from reclamation  where loginProf='" + loginProf + "' and reponse=0 and lue=0";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setEnTete(rs.getString("enTete"));
                rec.setMessage(rs.getString("message"));
                rec.setLoginEleve(rs.getString("loginEleve"));
                rec.setDate(rs.getString("Date"));
                rec.setIdReclamation(rs.getString("idReclam"));
                message.add(rec);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return message;
    }

    public ArrayList<Reclamation> selectMessEnvoyeProf(String loginProf) {

        ArrayList<Reclamation> message = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select enTete,message,loginEleve,Date,idReclam from reclamation  where loginProf='" + loginProf + " and reponse=0 and lue=1'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setEnTete(rs.getString("enTete"));
                rec.setMessage(rs.getString("message"));
                rec.setLoginEleve(rs.getString("loginEleve"));
                rec.setDate(rs.getString("Date"));
                rec.setIdReclamation(rs.getString("idReclam"));
                message.add(rec);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return message;
    }

    public void insereReclamationRep(String loginEleve, String loginProf, String message, String enTete, String date, String idReclamation) {

        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
            String requete = "insert into reclamation (loginEleve,loginProf,enTete,message,Date,reponse,lue) values(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(requete);
            pst.setString(1, loginEleve);
            pst.setString(2, loginProf);
            pst.setString(3, enTete);
            pst.setString(4, message);
            pst.setString(5, date);
            pst.setInt(6, 0);
            pst.setInt(7, 1);
            int result0 = pst.executeUpdate();
//            String requete1 = "update reclamation set lue=1 where idReclam='" + idReclamation + "'";
//            st = con.createStatement();
//            int rs1 = st.executeUpdate(requete1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Reclamation> selectReclamationRep(String loginEleve) {

        ArrayList<Reclamation> message = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select enTete,message,LoginProf,Date from reclamation  where loginEleve='" + loginEleve + "' and reponse=1";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setEnTete(rs.getString("enTete"));
                rec.setMessage(rs.getString("message"));
                rec.setLoginProf(rs.getString("LoginProf"));
                rec.setDate(rs.getString("Date"));
                message.add(rec);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return message;
    }

    public void search(String loginEleve, String mdp) {
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomClasse,annee from eleve  where loginEleve='" + loginEleve + "' and motDePasse='" + mdp + "' and active=0";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                EleveRecherche.setNomClasse(rs.getString("nomClasse"));
                EleveRecherche.setAnnee(rs.getString("annee"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}