/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Classe;
import model.DAOFactory;
import model.Eleve;
import model.EleveDevoir;
import model.GenererPDF;
import model.ListEleve;
import model.MessageRecu;
import model.Parent;
import model.Reclamation;
import model.Utilisateur;
import modelPersonne.DAOProfsImpl;
import modelPersonne.DAOParentImpl;

/**
 *
 * @author mac
 */
public class ControleurProf extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public List<Utilisateur> personnes;
    public ArrayList<Eleve> eleve2;
    public ArrayList<ListEleve> eleve3;
    public ArrayList<Eleve> eleve4;
    public ArrayList<String> listMatiere;
    public ArrayList<String> listAnnee;
    public ArrayList<Classe> listClasse;
    public ArrayList<String> listMatiere2;
    public ArrayList<String> listAnnee2;
    public ArrayList<Classe> listClasse2;
    public ArrayList<Utilisateur> compte;
    private DAOProfsImpl daoProf;
    public ArrayList<Parent> listParent;
    public ArrayList<Eleve> listeEleve;
    public ArrayList<Reclamation> reclamation;
    public ArrayList<MessageRecu> messR;
    public ArrayList<Eleve> rechercheParElev;
    private DAOParentImpl daoParent;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        personnes = new ArrayList();
        this.daoProf = daoFactory.getDAOProfs();
        eleve2 = new ArrayList();
        eleve3 = new ArrayList();
        eleve4 = new ArrayList();
        compte = new ArrayList<>();
        listParent = new ArrayList();
        reclamation = new ArrayList<>();
        rechercheParElev = new ArrayList<>();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //***********************************************Ajouter note Eleve************************************************************************  
        RequestDispatcher rd = null;
        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        //////Composition///////////////////////
        if (action.equals("ajoutNote")) {
            String loginProf = request.getParameter("login");
            System.out.println("loginProf " + loginProf);
            listMatiere = daoProf.selectMatiere(loginProf);
            listClasse = daoProf.selectClasse(loginProf);
            listAnnee = daoProf.selectAnnee();
            request.setAttribute("listMatiere", listMatiere);
            request.setAttribute("listClasse", listClasse);
            request.setAttribute("listAnnee", listAnnee);
            rd = request.getRequestDispatcher("Professeur/demandeMatClasseComp.jsp");
        } else if (action.equals("demandeMatClasseComp")) {
            String login1 = request.getParameter("login");
            String matiere = request.getParameter("matiere");
            //String classe = request.getParameter("classe");
            
            String nomClasse = request.getParameter("nomClasse");
            String[] regime = nomClasse.split("///");
            String nomClasse1 = regime[0];
            String regime1 = regime[1];
            
            String semestre = request.getParameter("semestre");
            String annee = request.getParameter("annee");
            String message = "";
            String annee1 = daoProf.verifProf1(matiere, nomClasse1, login1,regime1);
            eleve2 = daoProf.listEleve(nomClasse1, matiere, annee,regime1);
            System.out.println("loginEleve ");
            for (Eleve el : eleve2) {
                System.out.println("loginEleve " + el.getMatriculeEleve());
            }
            String loginProf = daoProf.verifProf(matiere, nomClasse1);
            if (!annee.equals(annee1)) {
                message = "L'année est incorrect!!!";
                request.setAttribute("message", message);
                request.setAttribute("listMatiere", listMatiere);
                request.setAttribute("listClasse", listClasse);
                request.setAttribute("listAnnee", listAnnee);
                rd = request.getRequestDispatcher("Professeur/demandeMatClasseComp.jsp");
            } else if (matiere == null || nomClasse1 == null) {
                message = "Vous n'avez aucun privilège ici!!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("Professeur/demandeMatClasseComp.jsp");
            } else if (eleve2.isEmpty()) {
                request.setAttribute("listMatiere", listMatiere);
                request.setAttribute("listClasse", listClasse);
                request.setAttribute("listAnnee", listAnnee);
                message = "Cette classe n'a pas encore d'élèves";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("Professeur/demandeMatClasseComp.jsp");
            } else {
                request.setAttribute("semestre", semestre);
                request.setAttribute("matiere", matiere);
                request.setAttribute("annee", annee);
                request.setAttribute("eleve", eleve2);
                rd = request.getRequestDispatcher("Professeur/AjouterNote.jsp");
            }

            for (Eleve eleve1 : eleve2) {
                System.out.println("Prenom " + eleve1.getPrenom());
                System.out.println("Nom " + eleve1.getNom());
                System.out.println("classe " + eleve1.getClasse());
                System.out.println("matiere " + eleve1.getMatiere());
            }
        } else if (action.equals("ajouterNoteComposition")) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DAOProfsImpl d = new DAOProfsImpl(daoFactory);
            String annee = request.getParameter("annee");
            String semestre = request.getParameter("semestre");
            String matiere = request.getParameter("matiere");
            String[] composition = request.getParameterValues("composition");
            System.out.println("semestre " + semestre + " annee " + annee + " matiere" + matiere);
            int i = 0;
            boolean ex = true;
            for (int j = 0; j < composition.length; j++) {
                if (Float.parseFloat(composition[j]) > 20) {
                    System.out.println(composition[j]);
                    ex = false;
                }
                break;
            }
            if (ex == true) {
                System.out.println("*******************Ajout Note Composition*****************");
                for (Eleve eleve1 : eleve2) {
                    System.out.println("++++++++++++++++++++"+eleve1.getMatriculeEleve()+"+++++++++++++++++++++");
                    d.ajouterNoteComposition(composition[i],eleve1.getMatriculeEleve(), semestre, annee, matiere);
                    i++;
                }
                if (ex == true) {
                    String mess="ajouter";
                    request.setAttribute("message1", mess);
                    request.setAttribute("eleve", eleve2);
                    rd = request.getRequestDispatcher("Professeur/AjouterNote.jsp");
                }
            } else {
                String message = "les notes ne doivent pas etre supérieur à 20";
                request.setAttribute("message", message);
                request.setAttribute("eleve", eleve2);
                rd = request.getRequestDispatcher("Professeur/AjouterNote.jsp");
            }
        }
            //////////////////////////////////////Note devoir//////////////////////////////
        else if (action.equals("ajoutNoteDevoir")) {
            String loginProf = request.getParameter("login");
            System.out.println("loginProf " + loginProf);
            listMatiere = daoProf.selectMatiere(loginProf);
            listClasse = daoProf.selectClasse(loginProf);
            listAnnee = daoProf.selectAnnee();
            request.setAttribute("listMatiere", listMatiere);
            request.setAttribute("listClasse", listClasse);
            request.setAttribute("listAnnee", listAnnee);
            rd = request.getRequestDispatcher("Professeur/demandeMatClasse.jsp");
        } else if (action.equals("demandeMatClasseDevoir")) {
            System.out.println("Devoir");
            String login1 = request.getParameter("login");
            String matiere = request.getParameter("matiere");
           // String classe = request.getParameter("classe");
            
            String nomClasse = request.getParameter("nomClasse");
            String[] regime = nomClasse.split("///");
            String nomClasse1 = regime[0];
            String regime1 = regime[1];
            
            String semestre = request.getParameter("semestre");
            String annee = request.getParameter("annee");
            String message = "";
            //System.out.println("login " + login1+" matiere "+matiere+" nomClasse "+nomClasse1+" regime "+regime1);
            String annee1 = daoProf.verifProf1(matiere, nomClasse1, login1,regime1);
            eleve2 = daoProf.listEleve(nomClasse1, matiere, annee,regime1);
//            System.out.println("loginEleve ");
//            for (Eleve el : eleve2) {
//                System.out.println("loginEleve " + el.getLogin());
//            }
            String loginProf = daoProf.verifProf(matiere, nomClasse1);
            if (!annee.equals(annee1)) {
                message = "L'année est incorrect!!!";
                request.setAttribute("message", message);
                request.setAttribute("listMatiere", listMatiere);
                request.setAttribute("listClasse", listClasse);
                request.setAttribute("listAnnee", listAnnee);
                rd = request.getRequestDispatcher("Professeur/demandeMatClasse.jsp");
            } else if (matiere == null || nomClasse1 == null) {
                message = "Vous n'avez aucun privilège ici!!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("Professeur/demandeMatClasse.jsp");
            } else if (eleve2.isEmpty()) {
                request.setAttribute("listMatiere", listMatiere);
                request.setAttribute("listClasse", listClasse);
                request.setAttribute("listAnnee", listAnnee);
                message = "Cette classe n'a pas encore d'élèves";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("Professeur/demandeMatClasse.jsp");
            } else {
                request.setAttribute("semestre", semestre);
                request.setAttribute("matiere", matiere);
                request.setAttribute("annee", annee);
                request.setAttribute("eleve", eleve2);
                rd = request.getRequestDispatcher("Professeur/AjouterNoteDevoir.jsp");
            }
            
            } else if (action.equals("ajouterNoteDevoir")) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DAOProfsImpl d = new DAOProfsImpl(daoFactory);
            String annee = request.getParameter("annee");
            String semestre = request.getParameter("semestre");
            String matiere = request.getParameter("matiere");
            String[] devoir = request.getParameterValues("devoir");
            System.out.println("semestre " + semestre + " annee " + annee + " matiere" + matiere);
            int i = 0;
            boolean ex = true;
            for (int j = 0; j < devoir.length; j++) {
                if (Float.parseFloat(devoir[j]) > 20) {
                    System.out.println(devoir[j]);
                    ex = false;
                }
                break;
            }
            if (ex == true) {
                for (Eleve eleve1 : eleve2) {
                    d.ajouterNoteDevoir(devoir[i], eleve1.getMatriculeEleve(), semestre, matiere,annee);
                    i++;
                }
                if (ex == true) {
                    String mess="ajouter";
                    request.setAttribute("message1", mess);
                    request.setAttribute("eleve", eleve2);
                    rd = request.getRequestDispatcher("Professeur/AjouterNoteDevoir.jsp");
                }
            } else {
                String message = "les notes ne doivent pas etre supérieur à 20";
                request.setAttribute("message", message);
                request.setAttribute("eleve", eleve2);
                rd = request.getRequestDispatcher("Professeur/AjouterNoteDevoir.jsp");
            }
        }
            //***********************************************Fin Ajouter note Eleve************************************************************************
            //***********************************************Lister note Eleve************************************************************************  
            else if (action.equals("NoteCompo")) {
            String loginProf = request.getParameter("login");
            System.out.println("loginProf " + loginProf);
            listMatiere2 = daoProf.selectMatiere(loginProf);
            listClasse2 = daoProf.selectClasse(loginProf);
            listAnnee2 = daoProf.selectAnnee();
            System.out.println("******************listMatiere****************");
            System.out.println(listMatiere2.toString());
            System.out.println("******************listClasse****************");
            //System.out.println(listClasse2.toString());
            System.out.println("******************listAnnee****************");
            System.out.println(listAnnee2.toString());
            request.setAttribute("listMatiere2", listMatiere2);
            request.setAttribute("listClasse2", listClasse2);
            request.setAttribute("listAnnee2", listAnnee2);
            rd = request.getRequestDispatcher("vue/prof/demandeClasseMatListe.jsp");
        } else if (action.equals("demandeMatClasseListe")) {
            String login2 = request.getParameter("login");
            String matiere = request.getParameter("matiere");
            //String classe = request.getParameter("classe");
            
            String nomClasse = request.getParameter("nomClasse");
            String[] regime = nomClasse.split("///");
            String nomClasse1 = regime[0];
            String regime1 = regime[1]; 
            
            String annee = request.getParameter("annee");
            String message = "Veuillez vérifier les informations saisies";
            eleve3 = daoProf.listNoteComposition(nomClasse1, matiere, annee,regime1);
            request.setAttribute("eleve", eleve3);
            String loginProf = daoProf.verifProf(matiere, nomClasse1);
            System.out.println("loginProf-----------------" + loginProf);
            if (matiere == null || nomClasse1 == null) {
                message = "Vous n'avez aucun privilège ici!!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("vue/prof/demandeClasseMatListe.jsp");
            } else {
                if (eleve3.isEmpty()) {
                    request.setAttribute("message", message);
                    request.setAttribute("listMatiere2", listMatiere2);
                    request.setAttribute("listClasse2", listClasse2);
                    request.setAttribute("listAnnee2", listAnnee2);
                    rd = request.getRequestDispatcher("vue/prof/demandeClasseMatListe.jsp");
                } else {
                    rd = request.getRequestDispatcher("vue/prof/listerNoteComposition.jsp");
                }
            }
        }
        //////////////Consulter note devoir///////////////
        else if (action.equals("NoteDevoir")) {
            String loginProf = request.getParameter("login");
            System.out.println("loginProf " + loginProf);
            listMatiere2 = daoProf.selectMatiere(loginProf);
            listClasse2 = daoProf.selectClasse(loginProf);
            listAnnee2 = daoProf.selectAnnee();
            System.out.println("******************listMatiere****************");
            System.out.println(listMatiere2.toString());
            System.out.println("******************listClasse****************");
            System.out.println(listClasse2.toString());
            System.out.println("******************listAnnee****************");
            System.out.println(listAnnee2.toString());
            request.setAttribute("listMatiere2", listMatiere2);
            request.setAttribute("listClasse2", listClasse2);
            request.setAttribute("listAnnee2", listAnnee2);
            rd = request.getRequestDispatcher("Professeur/demandeNoteDevoir.jsp");
        } else if (action.equals("demandeNoteDevoir")) {
            String login2 = request.getParameter("login");
            String matiere = request.getParameter("matiere");
            //String classe = request.getParameter("classe");
            
            String nomClasse = request.getParameter("nomClasse");
            String[] regime = nomClasse.split("///");
            String nomClasse1 = regime[0];
            String regime1 = regime[1];
            
            String annee = request.getParameter("annee");
            String message = "Veuillez vérifier les informations saisies";
            eleve3 = daoProf.listNoteDevoir(nomClasse1, matiere, annee,regime1);
            request.setAttribute("eleve", eleve3);
            String loginProf = daoProf.verifProf(matiere, nomClasse1);
            System.out.println("loginProf-----------------" + loginProf);
            if (matiere == null || nomClasse1 == null) {
                message = "Vous n'avez aucun privilège ici!!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("Professeur/demandeNoteDevoir.jsp");
            } else {
                if (eleve3.isEmpty()) {
                    request.setAttribute("message", message);
                    request.setAttribute("listMatiere2", listMatiere2);
                    request.setAttribute("listClasse2", listClasse2);
                    request.setAttribute("listAnnee2", listAnnee2);
                    rd = request.getRequestDispatcher("Professeur/demandeNoteDevoir.jsp");
                } else {
                    rd = request.getRequestDispatcher("Professeur/listerNoteDevoir.jsp");
                }
            }
        }
        //***********************************************Fin Lister note Eleve************************************************************************
        //***********************************************Modification note Eleve************************************************************************ 
        else if (action.equals("modifierCompo")) {
            String prenom = request.getParameter("prenom");
            String nom = request.getParameter("nom");
            String composition = request.getParameter("composition");
            String loginEleve = request.getParameter("loginEleve");
            String matiere = request.getParameter("matiere");
            String semestre = request.getParameter("semestre");
            String classe = request.getParameter("classe");
            String annee = request.getParameter("annee");
            request.setAttribute("prenom", prenom);
            request.setAttribute("nom", nom);
            request.setAttribute("composition", composition);
            request.setAttribute("loginEleve", loginEleve);
            request.setAttribute("matiere", matiere);
            request.setAttribute("semestre", semestre);
            request.setAttribute("classe", classe);
            request.setAttribute("annee", annee);
            System.out.println(loginEleve);

            rd = request.getRequestDispatcher("vue/prof/modifier.jsp");
        } else if (action.equals("modificationCompo")) {
            String loginEleve = request.getParameter("loginEleve");
            String composition = request.getParameter("composition");
            String matiere = request.getParameter("matiere");
            String semestre = request.getParameter("semestre");
            String classe = request.getParameter("classe");
            String annee = request.getParameter("annee");
            DAOFactory daoFactory = DAOFactory.getInstance();
            DAOProfsImpl d = new DAOProfsImpl(daoFactory);
            d.modifierNoteCompo(composition, loginEleve, matiere, semestre, annee);

            rd = request.getRequestDispatcher("Professeur/acceuilProf.jsp");
            
            
        }
         else if (action.equals("modifierDevoir")) {
            String prenom = request.getParameter("prenom");
            String nom = request.getParameter("nom");
            String devoir = request.getParameter("devoir");
            String idDevoir = request.getParameter("idDevoir");
            String loginEleve = request.getParameter("loginEleve");
            String matiere = request.getParameter("matiere");
            String semestre = request.getParameter("semestre");
            String classe = request.getParameter("classe");
            String annee = request.getParameter("annee");
            request.setAttribute("prenom", prenom);
            request.setAttribute("nom", nom);
            request.setAttribute("devoir", devoir);
            request.setAttribute("idDevoir", idDevoir);
            request.setAttribute("loginEleve", loginEleve);
            request.setAttribute("matiere", matiere);
            request.setAttribute("semestre", semestre);
            request.setAttribute("classe", classe);
            request.setAttribute("annee", annee);
            System.out.println(loginEleve);

            rd = request.getRequestDispatcher("vue/prof/modifierDevoir.jsp");
        } else if (action.equals("modificationDevoir")) {
            String loginEleve = request.getParameter("loginEleve");
            String devoir = request.getParameter("devoir");
            String idDevoir = request.getParameter("idDevoir");
            String matiere = request.getParameter("matiere");
            String semestre = request.getParameter("semestre");
            String classe = request.getParameter("classe");
            String annee = request.getParameter("annee");
            DAOFactory daoFactory = DAOFactory.getInstance();
            DAOProfsImpl d = new DAOProfsImpl(daoFactory);
            System.out.println("Info: "+devoir+" "+idDevoir+" "+loginEleve);
            d.modifierNoteDevoir(devoir, idDevoir, loginEleve);

            rd = request.getRequestDispatcher("Professeur/acceuilProf.jsp");
        }
        //***********************************************Fin modif note Eleve************************************************************************
        //***********************************************compte Prof************************************************************************  
        else if (action.equals("compte")) {
            String loginProf = request.getParameter("login");
            compte = daoProf.compte(loginProf);
            request.setAttribute("compte", compte);
            rd = request.getRequestDispatcher("Professeur/Compte.jsp");
        } else if (action.equals("modifCompte")) {
            String loginProf = (String)session.getAttribute("log");
            String ancienMdp = request.getParameter("ancienMdp");
            String nouveauMdp = request.getParameter("nouveauMdp");
            String confirmerMdp = request.getParameter("confirmerMdp");
            if (nouveauMdp.equals(confirmerMdp)) {
                int i = daoProf.compte2(ancienMdp);
                if (i == 0) {
                    daoProf.modifierCompte(loginProf, nouveauMdp);
                    String mes = "Modification effectuée avec succée";
                    request.setAttribute("message", mes);
                    request.setAttribute("compte", compte);
                    rd = request.getRequestDispatcher("vue/prof/Compte.jsp");
                } else {
                    String mes = "L'ancien mot de passe n'est pas conforme";
                    request.setAttribute("message", mes);
                    request.setAttribute("compte", compte);
                    rd = request.getRequestDispatcher("vue/prof/Compte.jsp");
                }
            } else {
                String mes = "Les mots de passes ne sont pas conformes";
                request.setAttribute("message", mes);
                request.setAttribute("compte", compte);
                rd = request.getRequestDispatcher("vue/prof/Compte.jsp");
            }

        } //***********************************************Fin Compte Prof************************************************************************ 
        //***********************************************Réclamation************************************************************************
        else if (action.equals("afficheMessage")) {
            String loginProf = request.getParameter("login");
            System.out.println("login-------------------- "+loginProf);
            messR = daoProf.messageRecuProf(loginProf);
            System.out.println("//////////////////////idReclamation////////////////////////////");
            
            request.setAttribute("listMessage", messR);
            rd = request.getRequestDispatcher("Professeur/message.jsp");
        }
        else if(action.equals("afficheMess")){
            String date= request.getParameter("date");
            String texte= request.getParameter("texte");
            String login_pro= request.getParameter("login_pro");
            String login= request.getParameter("login");
            System.out.println("login_pro: "+login_pro);
            System.out.println("login: "+login);
            ArrayList<String> messRecu=daoProf.selectMessageRecuProf(login_pro, login); 
            ArrayList<String> messEvoye=daoProf.selectMessageEnvoyeProf(login_pro, login);
            System.out.println("Message recu");
            for (String string : messRecu) {
                System.out.println(string);
            }
            System.out.println("Message envoye");
            for (String string : messEvoye) {
                System.out.println(string);
            }
            int taille=messRecu.size()+messEvoye.size();
            request.setAttribute("login", login);
            request.setAttribute("messRecu", messRecu);
            request.setAttribute("messEvoye", messEvoye);
            rd = request.getRequestDispatcher("vue/prof/chat.jsp");
        }
        else if(action.equals("repondre")){
            String pro_login=(String)session.getAttribute("log");
            String message= request.getParameter("message");
            String login=request.getParameter("login");
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date today = Calendar.getInstance().getTime();
            String date = df.format(today);
            daoProf.reponseProf(pro_login, login, message, date);
            ArrayList<String> messRecu=daoProf.selectMessageRecuProf(pro_login, login); 
            ArrayList<String> messEvoye=daoProf.selectMessageEnvoyeProf(pro_login, login);
            request.setAttribute("login", login);
            request.setAttribute("messRecu", messRecu);
            request.setAttribute("messEvoye", messEvoye);
            rd = request.getRequestDispatcher("vue/prof/chat.jsp");
        }
//        else if (action.equals("MessageRecu")) {
//            String loginProf = request.getParameter("login");
//            reclamation = daoEleve.selectReclamation(loginProf);
//            System.out.println("//////////////////////idReclamation////////////////////////////");
//            for (Reclamation rec : reclamation) {
//                System.out.println("idReclam: " + rec.getIdReclamation());
//            }
//            if (reclamation.isEmpty()) {
//                String message = "Vous n'avez aucun message";
//                request.setAttribute("message", message);
//            }
//            request.setAttribute("listReclation", reclamation);
//            rd = request.getRequestDispatcher("vue/prof/reclamationProf.jsp");
//        } else if (action.equals("repondre")) {
//            String idReclamation = request.getParameter("idReclamation");
//            System.out.println("//////////////////////idReclamation////////////////////////////");
//            System.out.println(idReclamation);
//            System.out.println("//////////////////////Fin idReclamation////////////////////////");
//            String loginEleve = request.getParameter("loginEleve");
//            String loginProf = request.getParameter("loginProf");
//            String nom = (String) session.getAttribute("nom");
//            String prenom = (String) session.getAttribute("prenom");
//            request.setAttribute("idReclamation", idReclamation);
//            request.setAttribute("loginEleve", loginEleve);
//            request.setAttribute("loginProf", loginProf);
//            request.setAttribute("nom", nom);
//            request.setAttribute("prenom", prenom);
//            rd = request.getRequestDispatcher("vue/prof/message.jsp");
//        } else if (action.equals("reponse")) {
//            String idReclamation = request.getParameter("idReclamation");
//            System.out.println("//////////////////////idReclamation////////////////////////////");
//            System.out.println(idReclamation);
//            System.out.println("//////////////////////Fin idReclamation////////////////////////");
//            String loginEleve = request.getParameter("loginEleve");
//            String loginProf = request.getParameter("loginProf");
//            String nom = (String) session.getAttribute("nom");
//            String prenom = (String) session.getAttribute("prenom");
//            String message = request.getParameter("message");
//            String enTete = prenom + " " + nom;
//            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//            Date today = Calendar.getInstance().getTime();
//            String date = df.format(today);
//            daoEleve.insereReclamationRep(loginEleve, loginProf, message, enTete, date, idReclamation);
//            reclamation = daoEleve.selectReclamation(loginProf);
//            request.setAttribute("listReclation", reclamation);
//            rd = request.getRequestDispatcher("vue/prof/reclamationProf.jsp");
//        } //***********************************************Fin Réclamation************************************************************************
        else if(action.equals("ficheDevoir")){
            String loginProf=request.getParameter("login");
            listClasse2 = daoProf.selectClasse(loginProf);
            listAnnee2 = daoProf.selectAnnee();
            request.setAttribute("listClasse2", listClasse2);
            request.setAttribute("listAnnee2", listAnnee2);
            rd = request.getRequestDispatcher("Professeur/imprimerFDevoir.jsp");
        }
        else if(action.equals("impressionDevoir")){
           // String classe = request.getParameter("classe");
           String nomClasse = request.getParameter("nomClasse");
            System.out.println("nomClasse "+nomClasse);
            String[] regime = nomClasse.split("///");
            String nomClasse1 = regime[0];
            System.out.println("nomClasse 2 "+nomClasse1 );
            String regime1 = regime[1];
           
            String annee = request.getParameter("annee");
            ArrayList<EleveDevoir>elD=new ArrayList<>();
            elD= daoProf.selectEleve(nomClasse1, annee,regime1);
            System.out.println("Liste de la classe: ");
            for (EleveDevoir eleveDevoir : elD) {
                System.out.println(eleveDevoir.getPrenom()+" "+eleveDevoir.getNom());
            }
            GenererPDF pdf= new GenererPDF();
           try {
                pdf.documentPDF(elD,annee,nomClasse1);
            } catch (DocumentException ex) {
                Logger.getLogger(ControleurProf.class.getName()).log(Level.SEVERE, null, ex);
            }
            String message="Fiche créé avec succes";
            String loginProf=(String)session.getAttribute("log");
            listClasse2 = daoProf.selectClasse(loginProf);
            listAnnee2 = daoProf.selectAnnee();
            request.setAttribute("listClasse2", listClasse2);
            request.setAttribute("listAnnee2", listAnnee2);
            request.setAttribute("message", message);
            request.setAttribute("classe", nomClasse1);
            rd = request.getRequestDispatcher("Professeur/imprimerFDevoir.jsp");
        }
        //*******************************************Recherche eleve par prof*******************************************
        else if (action.equals("rechercherProf")) {
            String nom = request.getParameter("recherche");
            String loginPro= (String)session.getAttribute("log");
            rechercheParElev = daoParent.rechercheEleveProf(nom,loginPro);
            for (Eleve e : rechercheParElev) {
                System.out.println("nom: " + e.getNom());
                System.out.println("prenom: " + e.getPrenom());
            }
            if (rechercheParElev.isEmpty()) {
                String message = "Aucun résultat !!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("vue/prof/rechercheProf.jsp");
            } else {
                request.setAttribute("rechercheParElev", rechercheParElev);
                rd = request.getRequestDispatcher("vue/prof/rechercheProf.jsp");
            }
        } ///////////////////////////////////////////////Fin Recherche//////////////////////////////////////////////////////
        else if (action.equals("deconnection")) {
            session.invalidate();
            rd = request.getRequestDispatcher("vue/SeConnecter.jsp");
        }

            if (rd != null) {
                rd.forward(request, response);
            }
        }

        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
        /**
         * Handles the HTTP <code>GET</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Handles the HTTP <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
