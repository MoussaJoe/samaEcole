<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import model.Utilisateur;
import modelPersonne.DAOProfsImpl;
import modelPersonne.DAOParentImpl;

/**
 *
 * @author mac
 */
public class ControleurParent extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public ArrayList<Parent> listParent;
    public List<Utilisateur> personnes;
    private DAOProfsImpl daoEleve;
    public ArrayList<Eleve> eleve2;
    public ArrayList<ListEleve> eleve3;
    public ArrayList<Eleve> eleve4;
    public ArrayList<Utilisateur> compte;
    private DAOParentImpl daoParent;
    public ArrayList<Eleve> listeEleve;
    public ArrayList<Eleve> rechercheParElev;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        personnes = new ArrayList();
        this.daoEleve = daoFactory.getDAOProfs();
        eleve2 = new ArrayList();
        eleve3 = new ArrayList();
        eleve4 = new ArrayList();
        compte = new ArrayList<>();
        listParent = new ArrayList();
        this.daoParent = daoFactory.getDAOParent();
        rechercheParElev = new ArrayList<>();

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        RequestDispatcher rd = null;
        
        if (action.equals("authen_parent")) {
            String nom="";
            String prenom="";
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
                rd = request.getRequestDispatcher("vue/parent/accueilPar.jsp");
            } else {
                String mes = "Login et/ou mot de passe invalide(s)";
                request.setAttribute("message", mes);
                rd = request.getRequestDispatcher("vue/parent/acceuilParent.jsp");
            }
        }
        else if (action.equals("parentLien")) {
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
            rd = request.getRequestDispatcher("vue/parent/parentForm.jsp");
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
                ArrayList<String> annees = daoParent.eleveParent(loginEleve);
                request.setAttribute("annees", annees);
                Eleve el = daoParent.listerUnEleve(loginEleve);
                session.setAttribute("el", el);
                rd = request.getRequestDispatcher("vue/parent/anEleve.jsp");
            } else {
                rd = request.getRequestDispatcher("acceuil.html");
            }
        }else if(action.equals("note")){
            String message = "Votre enfant n'a pas encore de notes.";
            String annee = request.getParameter("annee");
            String loginEleve = request.getParameter("loginEleve");
                eleve4 = daoParent.eleveParent(annee,loginEleve);
                
                request.setAttribute("annee", annee);
                request.setAttribute("eleve4", eleve4);
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("vue/parent/eleveparent.jsp");  
        } 
        else if (action.equals("eleveparent")) {
            String loginEleve = request.getParameter("loginEleve");
            System.out.println(loginEleve);
            if (loginEleve != null) {
                ArrayList<String> annees = daoParent.eleveParent(loginEleve);
                request.setAttribute("annees", annees);
                Eleve el = daoParent.listerUnEleve(loginEleve);
                session.setAttribute("el", el);
                rd = request.getRequestDispatcher("vue/parent/anEleve.jsp");
            } else {
                rd = request.getRequestDispatcher("");
            }
        }
        
        
        
        
        
        else if (action.equals("parentLienCompo")) {
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
            rd = request.getRequestDispatcher("vue/parent/parentFormCompo.jsp");
        }
        else if (action.equals("eleveCompo")) {
            String loginEleve = request.getParameter("loginEleve");
            System.out.println(loginEleve);
            if (loginEleve != null) {
                ArrayList<String> annees = daoParent.eleveParent(loginEleve);
                request.setAttribute("annees", annees);
                Eleve el = daoParent.listerUnEleve(loginEleve);
                session.setAttribute("el", el);
                rd = request.getRequestDispatcher("vue/parent/anEleveCompo.jsp");
            } else {
                rd = request.getRequestDispatcher("acceuil.html");
            }
        }else if(action.equals("noteCompo")){
            String message = "Votre enfant n'a pas encore de notes.";
            String annee = request.getParameter("annee");
            String loginEleve = request.getParameter("loginEleve");
                eleve4 = daoParent.eleveParentCompo(annee,loginEleve);
                
                request.setAttribute("annee", annee);
                request.setAttribute("eleve4", eleve4);
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("vue/parent/eleveparentCompo.jsp");  
        } 
        
        //**********************************************Compte Parent**********************************************************************
        else if (action.equals("compteParent")) {
            rd = request.getRequestDispatcher("vue/parent/compteParent.jsp");
        } else if (action.equals("modifCompteParent")) {
            String loginPar = (String) session.getAttribute("login");
            String ancienMdp = request.getParameter("ancienMdp");
            String nouveauMdp = request.getParameter("nouveauMdp");
            String confirmerMdp = request.getParameter("confirmerMdp");
            System.out.println("login: " + loginPar);
            System.out.println("ancienMdp: " + ancienMdp);
            System.out.println("nouveauMdp: " + nouveauMdp);
            System.out.println("confirmerMdp: " + confirmerMdp);
            if (nouveauMdp.equals(confirmerMdp)) {
                int i = daoParent.compte2(ancienMdp);
                if (i == 0) {
                    daoParent.modifierCompte(loginPar, nouveauMdp);
                    String mes1 = "Modification effectuée avec succée";
                    request.setAttribute("mes1", mes1);
                    rd = request.getRequestDispatcher("vue/parent/compteParent.jsp");
                } else {
                    String mes2 = "L'ancien mot de passe n'est pas conforme";
                    request.setAttribute("mes2", mes2);
                    rd = request.getRequestDispatcher("vue/parent/compteParent.jsp");
                }
            } else {
                String mes3 = "Les mots de passes ne sont pas conformes";
                request.setAttribute("mes3", mes3);
                rd = request.getRequestDispatcher("vue/parent/compteParent.jsp");
            }

        } //**********************************************Fin Compte Parent******************************************************************
        //********************************************Reherche eleve par parent*******************************************
        else if (action.equals("rechercherParent")) {
            String nom = request.getParameter("recherche");
            String loginPar = (String) session.getAttribute("login");
            rechercheParElev = daoParent.recherchePar(nom, loginPar);
            for (Eleve e : rechercheParElev) {
                System.out.println("nom: " + e.getNom());
                System.out.println("prenom: " + e.getPrenom());
            }
            if (rechercheParElev.isEmpty()) {
                String message = "Aucun résultat !!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("vue/parent/rechercheParent.jsp");
            } else {
                request.setAttribute("rechercheParElev", rechercheParElev);
                rd = request.getRequestDispatcher("vue/parent/rechercheParent.jsp");
            }

        }
        else if (action.equals("deconnectionParent")) {
            session.invalidate();
            rd = request.getRequestDispatcher("accueilPar.jsp");
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
=======

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import model.Utilisateur;
import modelPersonne.DAOProfsImpl;
import modelPersonne.DAOParentImpl;

/**
 *
 * @author mac
 */
public class ControleurParent extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public ArrayList<Parent> listParent;
    public List<Utilisateur> personnes;
    private DAOProfsImpl daoEleve;
    public ArrayList<Eleve> eleve2;
    public ArrayList<ListEleve> eleve3;
    public ArrayList<Eleve> eleve4;
    public ArrayList<Utilisateur> compte;
    private DAOParentImpl daoParent;
    public ArrayList<Eleve> listeEleve;
    public ArrayList<Eleve> rechercheParElev;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        personnes = new ArrayList();
        this.daoEleve = daoFactory.getDAOProfs();
        eleve2 = new ArrayList();
        eleve3 = new ArrayList();
        eleve4 = new ArrayList();
        compte = new ArrayList<>();
        listParent = new ArrayList();
        this.daoParent = daoFactory.getDAOParent();
        rechercheParElev = new ArrayList<>();

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        RequestDispatcher rd = null;
        
        if (action.equals("authen_parent")) {
            String nom="";
            String prenom="";
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
                rd = request.getRequestDispatcher("vue/parent/accueilPar.jsp");
            } else {
                String mes = "Login et/ou mot de passe invalide(s)";
                request.setAttribute("message", mes);
                rd = request.getRequestDispatcher("vue/parent/acceuilParent.jsp");
            }
        }
        else if (action.equals("parentLien")) {
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
            String loginPar = (String)session.getAttribute("login");
            String motDePasse = (String)session.getAttribute("mdp");
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
            rd = request.getRequestDispatcher("vue/parent/parentForm.jsp");
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
                ArrayList<String> annees = daoParent.eleveParent(loginEleve);
                request.setAttribute("annees", annees);
                Eleve el = daoParent.listerUnEleve(loginEleve);
                session.setAttribute("el", el);
                rd = request.getRequestDispatcher("vue/parent/anEleve.jsp");
            } else {
                rd = request.getRequestDispatcher("acceuil.html");
            }
        }else if(action.equals("note")){
            String message = "Votre enfant n'a pas encore de notes.";
            String annee = request.getParameter("annee");
            String loginEleve = request.getParameter("loginEleve");
                eleve4 = daoParent.eleveParent(annee,loginEleve);
                
                request.setAttribute("annee", annee);
                request.setAttribute("eleve4", eleve4);
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("vue/parent/eleveparent.jsp");  
        } 
        else if (action.equals("eleveparent")) {
            String loginEleve = request.getParameter("loginEleve");
            System.out.println(loginEleve);
            if (loginEleve != null) {
                ArrayList<String> annees = daoParent.eleveParent(loginEleve);
                request.setAttribute("annees", annees);
                Eleve el = daoParent.listerUnEleve(loginEleve);
                session.setAttribute("el", el);
                rd = request.getRequestDispatcher("vue/parent/anEleve.jsp");
            } else {
                rd = request.getRequestDispatcher("");
            }
        }
        
        
        
        
        
        else if (action.equals("parentLienCompo")) {
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
            String loginPar = (String)session.getAttribute("login");
            String motDePasse = (String)session.getAttribute("mdp");
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
            rd = request.getRequestDispatcher("vue/parent/parentFormCompo.jsp");
        }
        else if (action.equals("eleveCompo")) {
            String loginEleve = request.getParameter("loginEleve");
            System.out.println(loginEleve);
            if (loginEleve != null) {
                ArrayList<String> annees = daoParent.eleveParent(loginEleve);
                request.setAttribute("annees", annees);
                Eleve el = daoParent.listerUnEleve(loginEleve);
                session.setAttribute("el", el);
                rd = request.getRequestDispatcher("vue/parent/anEleveCompo.jsp");
            } else {
                rd = request.getRequestDispatcher("acceuil.html");
            }
        }else if(action.equals("noteCompo")){
            String message = "Votre enfant n'a pas encore de notes.";
            String annee = request.getParameter("annee");
            String loginEleve = request.getParameter("loginEleve");
                eleve4 = daoParent.eleveParentCompo(annee,loginEleve);
                
                request.setAttribute("annee", annee);
                request.setAttribute("eleve4", eleve4);
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("vue/parent/eleveparentCompo.jsp");  
        } 
        
        //**********************************************Compte Parent**********************************************************************
        else if (action.equals("compteParent")) {
            rd = request.getRequestDispatcher("vue/parent/compteParent.jsp");
        } else if (action.equals("modifCompteParent")) {
            String loginPar = (String) session.getAttribute("login");
            String ancienMdp = request.getParameter("ancienMdp");
            String nouveauMdp = request.getParameter("nouveauMdp");
            String confirmerMdp = request.getParameter("confirmerMdp");
            System.out.println("login: " + loginPar);
            System.out.println("ancienMdp: " + ancienMdp);
            System.out.println("nouveauMdp: " + nouveauMdp);
            System.out.println("confirmerMdp: " + confirmerMdp);
            if (nouveauMdp.equals(confirmerMdp)) {
                int i = daoParent.compte2(ancienMdp);
                if (i == 0) {
                    daoParent.modifierCompte(loginPar, nouveauMdp);
                    String mes1 = "Modification effectuée avec succée";
                    request.setAttribute("mes1", mes1);
                    rd = request.getRequestDispatcher("vue/parent/compteParent.jsp");
                } else {
                    String mes2 = "L'ancien mot de passe n'est pas conforme";
                    request.setAttribute("mes2", mes2);
                    rd = request.getRequestDispatcher("vue/parent/compteParent.jsp");
                }
            } else {
                String mes3 = "Les mots de passes ne sont pas conformes";
                request.setAttribute("mes3", mes3);
                rd = request.getRequestDispatcher("vue/parent/compteParent.jsp");
            }

        } //**********************************************Fin Compte Parent******************************************************************
        //********************************************Reherche eleve par parent*******************************************
        else if (action.equals("rechercherParent")) {
            String nom = request.getParameter("recherche");
            String loginPar = (String) session.getAttribute("login");
            rechercheParElev = daoParent.recherchePar(nom, loginPar);
            for (Eleve e : rechercheParElev) {
                System.out.println("nom: " + e.getNom());
                System.out.println("prenom: " + e.getPrenom());
            }
            if (rechercheParElev.isEmpty()) {
                String message = "Aucun résultat !!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("vue/parent/rechercheParent.jsp");
            } else {
                request.setAttribute("rechercheParElev", rechercheParElev);
                rd = request.getRequestDispatcher("vue/parent/rechercheParent.jsp");
            }

        }
        else if (action.equals("deconnectionParent")) {
            session.invalidate();
            rd = request.getRequestDispatcher("accueilPar.jsp");
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
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403
