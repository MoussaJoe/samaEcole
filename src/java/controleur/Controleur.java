/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOFactory;
import model.Eleve;
import model.ListEleve;
import model.Parent;
import model.Reclamation;
import model.Utilisateur;
import modelPersonne.DAOEleveImpl;
import modelPersonne.DAOParentImpl;
import modelPersonne.DAOPersonneImpl;

/**
 *
 * @author ibrah
 */
public class Controleur extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private DAOPersonneImpl daoPersonne;
    public List<Utilisateur> personnes;
    public ArrayList<Eleve> eleve2;
    public ArrayList<ListEleve> eleve3;
    public ArrayList<Eleve> eleve4;
    public ArrayList<String> listMatiere;
    public ArrayList<String> listAnnee;
    public ArrayList<String> listClasse;
    public ArrayList<String> listMatiere2;
    public ArrayList<String> listAnnee2;
    public ArrayList<String> listClasse2;
    public ArrayList<Utilisateur> compte;
    private DAOEleveImpl daoEleve;
    private DAOParentImpl daoParent;
    public ArrayList<Parent> listParent;
    public ArrayList<Eleve> listeEleve;
    public ArrayList<Reclamation> reclamation;
    public ArrayList<Eleve> rechercheParElev;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.daoPersonne = daoFactory.getDAOPersonne();
        personnes = new ArrayList();
        this.daoEleve = daoFactory.getDAOEleve();
        this.daoParent = daoFactory.getDAOParent();
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
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        RequestDispatcher rd = null;
        String login = "";

        if (action == null) {
            rd = request.getRequestDispatcher("accue.html");
        } else if (action.equals("connection")) {
            rd = request.getRequestDispatcher("vue/SeConnecter.jsp");
        } else if (action.equals("parent")) {
            rd = request.getRequestDispatcher("vue/parent/parent.jsp");
        } else if (action.equals("authentifier")) {
            String profils = request.getParameter("profils");
            login = request.getParameter("login");
            String motDePasse = request.getParameter("motDePasse");
            personnes = daoPersonne.listPersonne(profils);
            System.out.println("Profils " + profils);
            int i = 0;
            for (Utilisateur p : personnes) {
                System.out.println("login " + p.getLogin());
                System.out.println("Mdp " + p.getMotDePasse());
                if ((profils.equalsIgnoreCase("eleve")) && (login.equals(p.getLogin())) && (motDePasse.equals(p.getMotDePasse()))) {
                    session.setAttribute("log", login);
                    session.setAttribute("motDePasse", motDePasse);
                    session.setAttribute("prenom", p.getPrenom());
                    session.setAttribute("nom", p.getNom());
                    i = 1;
                    daoEleve.search(login, motDePasse);
                    rd = request.getRequestDispatcher("acceuilEleve.jsp");
                } else if ((profils.equalsIgnoreCase("professeur")) && (login.equals(p.getLogin())) && (motDePasse.equals(p.getMotDePasse()))) {
                    session.setAttribute("login", login);
                    session.setAttribute("log", login);
                    session.setAttribute("motDePasse", motDePasse);
                    session.setAttribute("prenom", p.getPrenom());
                    session.setAttribute("nom", p.getNom());
                    i = 1;
                    rd = request.getRequestDispatcher("acceuilProf.jsp");
                } else if ((profils.equalsIgnoreCase("directeur")) && (login.equals(p.getLogin())) && (motDePasse.equals(p.getMotDePasse()))) {
                    session.setAttribute("log", login);
                    session.setAttribute("motDePasse", motDePasse);
                    session.setAttribute("prenom", p.getPrenom());
                    session.setAttribute("nom", p.getNom());
                    session.setAttribute("profils", profils);
                    i = 1;
                    rd = request.getRequestDispatcher("acceuilDir.jsp");
                }
                else if ((profils.equalsIgnoreCase("surveillant")) && (login.equals(p.getLogin())) && (motDePasse.equals(p.getMotDePasse()))) {
                    session.setAttribute("log", login);
                    session.setAttribute("motDePasse", motDePasse);
                    session.setAttribute("prenom", p.getPrenom());
                    session.setAttribute("nom", p.getNom());
                    session.setAttribute("profils", "surveillant");
                    i = 1;
                    rd = request.getRequestDispatcher("accueilSurv.jsp");
                }
            }
            if (i == 0) {
                String message = "Login et/ou mot de passe incorrect";
                request.setAttribute("mess", message);
                rd = request.getRequestDispatcher("vue/SeConnecter.jsp");
            }
            //***********************************************Ajouter note Eleve************************************************************************  
        } else if (action.equals("ajoutNote")) {
            String loginProf = request.getParameter("login");
            System.out.println("loginProf " + loginProf);
            listMatiere = daoEleve.selectMatiere(loginProf);
            listClasse = daoEleve.selectClasse(loginProf);
            listAnnee = daoEleve.selectAnnee();
            request.setAttribute("listMatiere", listMatiere);
            request.setAttribute("listClasse", listClasse);
            request.setAttribute("listAnnee", listAnnee);
            rd = request.getRequestDispatcher("demandeMatClasse.jsp");
        } else if (action.equals("demandeMatClasse")) {
            String login1 = request.getParameter("login");
            String matiere = request.getParameter("matiere");
            String classe = request.getParameter("classe");
            String semestre = request.getParameter("semestre");
            String annee = request.getParameter("annee");
            String message = "";
            String annee1 = daoEleve.verifProf1(matiere, classe, login1);
            eleve2 = daoEleve.listEleve(classe, matiere, annee);
            System.out.println("loginEleve ");
            for (Eleve el : eleve2) {
                System.out.println("loginEleve " + el.getLogin());
            }
            String loginProf = daoEleve.verifProf(matiere, classe);
            if (!annee.equals(annee1)) {
                message = "L'année est incorrect!!!";
                request.setAttribute("message", message);
                request.setAttribute("listMatiere", listMatiere);
                request.setAttribute("listClasse", listClasse);
                request.setAttribute("listAnnee", listAnnee);
                rd = request.getRequestDispatcher("demandeMatClasse.jsp");
            } else if (matiere == null || classe == null) {
                message = "Vous n'avez aucun privilège ici!!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("demandeMatClasse.jsp");
            } else if (eleve2.isEmpty()) {
                request.setAttribute("listMatiere", listMatiere);
                request.setAttribute("listClasse", listClasse);
                request.setAttribute("listAnnee", listAnnee);
                message = "Cette classe n'a pas encore d'élèves";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("demandeMatClasse.jsp");
            } else {
                request.setAttribute("semestre", semestre);
                request.setAttribute("matiere", matiere);
                request.setAttribute("annee", annee);
                request.setAttribute("eleve", eleve2);
                rd = request.getRequestDispatcher("AjouterNote.jsp");
            }

            for (Eleve eleve1 : eleve2) {
                System.out.println("Prenom " + eleve1.getPrenom());
                System.out.println("Nom " + eleve1.getNom());
                System.out.println("classe " + eleve1.getClasse());
                System.out.println("matiere " + eleve1.getMatiere());
            }
        } else if (action.equals("ajouterNote")) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DAOEleveImpl d = new DAOEleveImpl(daoFactory);
            String annee = request.getParameter("annee");
            String semestre = request.getParameter("semestre");
            String matiere = request.getParameter("matiere");
            String[] devoir1 = request.getParameterValues("devoir1");
            String[] devoir2 = request.getParameterValues("devoir2");
            String[] composition = request.getParameterValues("composition");
            System.out.println("semestre " + semestre + " annee " + annee + " matiere" + matiere);
            int i = 0;
            boolean ex = true;
            for (int j = 0; j < devoir1.length; j++) {
                if (Float.parseFloat(devoir1[j]) > 20 || Float.parseFloat(devoir2[j]) > 20 || Float.parseFloat(composition[j]) > 20) {
                    System.out.println(devoir1[j] + " " + devoir2[j] + " " + composition[j]);
                    ex = false;
                }
                break;
            }
            if (ex == true) {
                for (Eleve eleve1 : eleve2) {
                    d.ajouterNote1(devoir1[i], devoir2[i], composition[i], eleve1.getMatriculeEleve(), semestre, annee, matiere);
                    i++;
                }
                if (ex == true) {
                    rd = request.getRequestDispatcher("AjouterNote.jsp");
                }
            } else {
                String message = "les notes ne doivent pas etre supérieur à 20";
                request.setAttribute("message", message);
                request.setAttribute("eleve", eleve2);
                rd = request.getRequestDispatcher("AjouterNote.jsp");
            }
        } //***********************************************Fin Ajouter note Eleve************************************************************************  
        //***********************************************Lister note Eleve************************************************************************  
        else if (action.equals("Note")) {
            String loginProf = request.getParameter("login");
            System.out.println("loginProf " + loginProf);
            listMatiere2 = daoEleve.selectMatiere(loginProf);
            listClasse2 = daoEleve.selectClasse(loginProf);
            listAnnee2 = daoEleve.selectAnnee();
            System.out.println("******************listMatiere****************");
            System.out.println(listMatiere2.toString());
            System.out.println("******************listClasse****************");
            System.out.println(listClasse2.toString());
            System.out.println("******************listAnnee****************");
            System.out.println(listAnnee2.toString());
            request.setAttribute("listMatiere2", listMatiere2);
            request.setAttribute("listClasse2", listClasse2);
            request.setAttribute("listAnnee2", listAnnee2);
            rd = request.getRequestDispatcher("demandeClasseMatListe.jsp");
        } else if (action.equals("demandeMatClasseListe")) {
            String login2 = request.getParameter("login");
            String matiere = request.getParameter("matiere");
            String classe = request.getParameter("classe");
            String annee = request.getParameter("annee");
            String message = "Veuillez vérifier les informations saisies";
            eleve3 = daoEleve.listNote(classe, matiere, annee);
            request.setAttribute("eleve", eleve3);
            String loginProf = daoEleve.verifProf(matiere, classe);
            System.out.println("loginProf-----------------"+loginProf);
            if (matiere == null || classe == null) {
                message = "Vous n'avez aucun privilège ici!!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("demandeClasseMatListe.jsp");
            } else {
                if (eleve3.isEmpty()) {
                    request.setAttribute("message", message);
                    request.setAttribute("listMatiere2", listMatiere2);
                    request.setAttribute("listClasse2", listClasse2);
                    request.setAttribute("listAnnee2", listAnnee2);
                    rd = request.getRequestDispatcher("demandeClasseMatListe.jsp");
                } else {
                    rd = request.getRequestDispatcher("listerNote.jsp");
                }
            }
            //***********************************************Fin Lister note Eleve************************************************************************  
        } //***********************************************Parent************************************************************************     
        else if (action.equals("portailParent")) {
            rd = request.getRequestDispatcher("acceuilParent.jsp");
        } else if (action.equals("authen_parent")) {
            String nom="";
            String prenom="";
            String annee = new SimpleDateFormat("yyyy", Locale.FRANCE).format(Calendar.getInstance().getTime());
            int newYear = Integer.parseInt(annee);
            int anneeBd = newYear - 1;
            String an = "" + anneeBd + "-" + annee;
            System.out.println("Annee :" + an);
            String loginPar = request.getParameter("login");
            String motDePasse = request.getParameter("mdp");
            System.out.println("login " + loginPar);
            System.out.println("Mot de passe " + motDePasse);
            listParent = daoParent.listPar(loginPar, motDePasse);
            String loginParent = daoParent.loginParent(loginPar, motDePasse);
            System.out.println("login parent " + loginParent);
            System.out.println("//////////////////////////////parent//////////////////////////// ");
            for (Parent par : listParent) {
                nom=par.getNom();
                prenom=par.getPrenom();
                System.out.println("Nom :" + par.getNom());
                System.out.println("Prenom :" + par.getPrenom());
            }
            if (!listParent.isEmpty()) {
                session.setAttribute("login", loginParent);
                session.setAttribute("mdp", motDePasse);
                session.setAttribute("nom", nom);
                session.setAttribute("prenom", prenom);
                rd = request.getRequestDispatcher("accueilPar.jsp");
            } else {
                String mes = "Login et/ou mot de passe invalide(s)";
                request.setAttribute("message", mes);
                rd = request.getRequestDispatcher("acceuilParent.jsp");

            }

        } else if (action.equals("parentLien")) {
            String an = "";
            String mois = new SimpleDateFormat("MM", Locale.FRANCE).format(Calendar.getInstance().getTime());
            int newMois = Integer.parseInt(mois);
            if (newMois >= 1 && newMois <= 9) {
                String annee = new SimpleDateFormat("yyyy", Locale.FRANCE).format(Calendar.getInstance().getTime());
                int newYear = Integer.parseInt(annee);
                int anneeBd = newYear - 1;
                an = "" + anneeBd + "-" + annee;
            } else {
                String annee = new SimpleDateFormat("yyyy", Locale.FRANCE).format(Calendar.getInstance().getTime());
                int newYear = Integer.parseInt(annee);
                int anneeBd = newYear + 1;
                an = "" + annee + "-" + anneeBd;
            }

            System.out.println("Annee :" + an);
            String loginPar = request.getParameter("login");
            String motDePasse = request.getParameter("mdp");
            System.out.println("login " + loginPar);
            System.out.println("Mot de passe " + motDePasse);
            listParent = daoParent.listPar(loginPar, motDePasse);
            String loginParent = daoParent.loginParent(loginPar, motDePasse);
            System.out.println("login parent " + loginParent);
            System.out.println("//////////////////////////////parent//////////////////////////// ");
            listeEleve = daoParent.listerEleve(loginParent);
            request.setAttribute("listeEleve", listeEleve);
            request.setAttribute("login", loginParent);
//            request.setAttribute("mdp", motDePasse);
            session.setAttribute("mdp", motDePasse);
            rd = request.getRequestDispatcher("parentForm.jsp");
        } else if (action.equals("eleveparent")) {
//            String message = "Votre enfant n'a pas encore de notes.";
//            String prenom = request.getParameter("prenom");
//            String nom = request.getParameter("nom");
//            String dateNaissance = request.getParameter("dateNaissance");
//            String lieuNaissance = request.getParameter("lieuNaissance");
//            String annee = request.getParameter("annee");
//            String classe = request.getParameter("nomClasse");
            //String loginEleve = daoEleve.eleveParent(prenom, nom, dateNaissance, lieuNaissance, classe);
            String loginEleve = request.getParameter("loginEleve");
            System.out.println(loginEleve);
            if (loginEleve != null) {
                ArrayList<String> annees = daoEleve.eleveParent(loginEleve);
                request.setAttribute("annees", annees);
                Eleve el = daoEleve.listerUnEleve(loginEleve);
                session.setAttribute("el", el);
                rd = request.getRequestDispatcher("anEleve.jsp");
            } else {
                rd = request.getRequestDispatcher("acceuil.html");
            }
        }else if(action.equals("note")){
            String message = "Votre enfant n'a pas encore de notes.";
            String annee = request.getParameter("annee");
            String loginEleve = request.getParameter("loginEleve");
                eleve4 = daoEleve.eleveParent(annee,loginEleve);
                
                request.setAttribute("annee", annee);
                request.setAttribute("eleve4", eleve4);
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("eleveparent.jsp");
            
            //***********************************************Fin Parent************************************************************************

            //***********************************************Modification note Eleve************************************************************************  
        } else if (action.equals("modifier")) {
            String prenom = request.getParameter("prenom");
            String nom = request.getParameter("nom");
            String devoir1 = request.getParameter("devoir1");
            String devoir2 = request.getParameter("devoir2");
            String composition = request.getParameter("composition");
            String loginEleve = request.getParameter("loginEleve");
            String matiere = request.getParameter("matiere");
            String semestre = request.getParameter("semestre");
            String classe = request.getParameter("classe");
            String annee = request.getParameter("annee");
            request.setAttribute("prenom", prenom);
            request.setAttribute("nom", nom);
            request.setAttribute("devoir1", devoir1);
            request.setAttribute("devoir2", devoir2);
            request.setAttribute("composition", composition);
            request.setAttribute("loginEleve", loginEleve);
            request.setAttribute("matiere", matiere);
            request.setAttribute("semestre", semestre);
            request.setAttribute("classe", classe);
            request.setAttribute("annee", annee);
            System.out.println(loginEleve);

            rd = request.getRequestDispatcher("modifier.jsp");
        } else if (action.equals("modification")) {
            String loginEleve = request.getParameter("loginEleve");
            String devoir1 = request.getParameter("devoir1");
            String devoir2 = request.getParameter("devoir2");
            String composition = request.getParameter("composition");
            String matiere = request.getParameter("matiere");
            String semestre = request.getParameter("semestre");
            String classe = request.getParameter("classe");
            String annee = request.getParameter("annee");
            DAOFactory daoFactory = DAOFactory.getInstance();
            DAOEleveImpl d = new DAOEleveImpl(daoFactory);
            d.modifierNote(devoir1, devoir2, composition, loginEleve, matiere, semestre, classe, annee);

            rd = request.getRequestDispatcher("acceuilProf.jsp");
        } //***********************************************Fin modif note Eleve************************************************************************  
        //***********************************************compte Prof************************************************************************  
        //**********************************************Compte Parent**********************************************************************
        else if(action.equals("compteParent")){
            rd=request.getRequestDispatcher("compteParent.jsp");
        }
        else if (action.equals("modifCompteParent")) {
            String loginPar = (String)session.getAttribute("login");
            String ancienMdp = request.getParameter("ancienMdp");
            String nouveauMdp = request.getParameter("nouveauMdp");
            String confirmerMdp = request.getParameter("confirmerMdp");
            System.out.println("login: "+loginPar);
            System.out.println("ancienMdp: "+ancienMdp);
            System.out.println("nouveauMdp: "+nouveauMdp);
            System.out.println("confirmerMdp: "+confirmerMdp);
            if (nouveauMdp.equals(confirmerMdp)) {
                int i = daoParent.compte2(ancienMdp);
                if (i == 0) {
                    daoParent.modifierCompte(loginPar, nouveauMdp);
                    String mes1 = "Modification effectuée avec succée";
                    request.setAttribute("mes1", mes1);
                    rd = request.getRequestDispatcher("compteParent.jsp");
                } else {
                    String mes2 = "L'ancien mot de passe n'est pas conforme";
                    request.setAttribute("mes2", mes2);
                    rd = request.getRequestDispatcher("compteParent.jsp");
                }
            } else {
                String mes3 = "Les mots de passes ne sont pas conformes";
                request.setAttribute("mes3", mes3);
                rd = request.getRequestDispatcher("compteParent.jsp");
            }

        } //**********************************************Fin Compte Parent******************************************************************
        else if (action.equals("compte")) {
            String loginProf = request.getParameter("login");
            compte = daoEleve.compte(loginProf);
            for (Utilisateur ut : compte) {
                request.setAttribute("idPersonne", ut.getIdPersonne());
            }
            request.setAttribute("compte", compte);
            rd = request.getRequestDispatcher("Compte.jsp");
        } else if (action.equals("modifCompte")) {
            String loginProf = request.getParameter("login");
            String ancienMdp = request.getParameter("ancienMdp");
            String nouveauMdp = request.getParameter("nouveauMdp");
            String confirmerMdp = request.getParameter("confirmerMdp");
            String idPersonne = request.getParameter("idPersonne");
            if (nouveauMdp.equals(confirmerMdp)) {
                int i = daoEleve.compte2(ancienMdp);
                if (i == 0) {
                    daoEleve.modifierCompte(loginProf, nouveauMdp, idPersonne);
                    String mes = "Modification effectuée avec succée";
                    request.setAttribute("message", mes);
                    request.setAttribute("compte", compte);
                    rd = request.getRequestDispatcher("Compte.jsp");
                } else {
                    String mes = "L'ancien mot de passe n'est pas conforme";
                    request.setAttribute("message", mes);
                    request.setAttribute("compte", compte);
                    rd = request.getRequestDispatcher("Compte.jsp");
                }
            } else {
                String mes = "Les mots de passes ne sont pas conformes";
                request.setAttribute("message", mes);
                request.setAttribute("compte", compte);
                rd = request.getRequestDispatcher("Compte.jsp");
            }

        } //***********************************************Fin Compte Prof************************************************************************ 
        //***********************************************Réclamation************************************************************************
        else if (action.equals("afficheMessage")) {
            String loginProf = request.getParameter("login");
            reclamation = daoEleve.selectReclamation(loginProf);
            System.out.println("//////////////////////idReclamation////////////////////////////");
            for (Reclamation rec : reclamation) {
                System.out.println("idReclam: " + rec.getIdReclamation());
            }
            if (reclamation.isEmpty()) {
                String message = "Vous n'avez aucun message";
                request.setAttribute("message", message);
            }
            request.setAttribute("listReclation", reclamation);
            rd = request.getRequestDispatcher("reclamationProf.jsp");
        } else if (action.equals("repondre")) {
            String idReclamation = request.getParameter("idReclamation");
            System.out.println("//////////////////////idReclamation////////////////////////////");
            System.out.println(idReclamation);
            System.out.println("//////////////////////Fin idReclamation////////////////////////");
            String loginEleve = request.getParameter("loginEleve");
            String loginProf = request.getParameter("loginProf");
            String nom = (String) session.getAttribute("nom");
            String prenom = (String) session.getAttribute("prenom");
            request.setAttribute("idReclamation", idReclamation);
            request.setAttribute("loginEleve", loginEleve);
            request.setAttribute("loginProf", loginProf);
            request.setAttribute("nom", nom);
            request.setAttribute("prenom", prenom);
            rd = request.getRequestDispatcher("message.jsp");
        } else if (action.equals("reponse")) {
            String idReclamation = request.getParameter("idReclamation");
            System.out.println("//////////////////////idReclamation////////////////////////////");
            System.out.println(idReclamation);
            System.out.println("//////////////////////Fin idReclamation////////////////////////");
            String loginEleve = request.getParameter("loginEleve");
            String loginProf = request.getParameter("loginProf");
            String nom = (String) session.getAttribute("nom");
            String prenom = (String) session.getAttribute("prenom");
            String message = request.getParameter("message");
            String enTete = prenom + " " + nom;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date today = Calendar.getInstance().getTime();
            String date = df.format(today);
            daoEleve.insereReclamationRep(loginEleve, loginProf, message, enTete, date, idReclamation);
            reclamation = daoEleve.selectReclamation(loginProf);
            request.setAttribute("listReclation", reclamation);
            rd = request.getRequestDispatcher("reclamationProf.jsp");
        } //***********************************************Fin Réclamation************************************************************************
        ///////////////////////////////////////////////Recherche///////////////////////////////////////////////////////////
        //********************************************Reherche eleve par parent*******************************************
        else if (action.equals("rechercherParent")) {
            String nom = request.getParameter("recherche");
            String loginPar= (String)session.getAttribute("login");
            rechercheParElev = daoParent.recherchePar(nom,loginPar);
            for (Eleve e : rechercheParElev) {
                System.out.println("nom: " + e.getNom());
                System.out.println("prenom: " + e.getPrenom());
            }
            if (rechercheParElev.isEmpty()) {
                String message = "Aucun résultat !!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("rechercheParent.jsp");
            } else {
                request.setAttribute("rechercheParElev", rechercheParElev);
                rd = request.getRequestDispatcher("rechercheParent.jsp");
            }

        } //*******************************************Recherche eleve par prof*******************************************
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
                rd = request.getRequestDispatcher("rechercheProf.jsp");
            } else {
                request.setAttribute("rechercheParElev", rechercheParElev);
                rd = request.getRequestDispatcher("rechercheProf.jsp");
            }
        } ///////////////////////////////////////////////Fin Recherche//////////////////////////////////////////////////////
        else if (action.equals("deconnection")) {
            session.invalidate();
            rd = request.getRequestDispatcher("vue/SeConnecter.jsp");
        } else if (action.equals("deconnectionParent")) {
            session.invalidate();
            rd = request.getRequestDispatcher("acceuilParent.jsp");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
