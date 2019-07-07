/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOFactory;
import model.ListEleve;
import model.Parent;
import model.Reclamation;
import model.Utilisateur;
import modelPersonne.DAOEleveImpl;
import modelPersonne.DAOParentImpl;
import modelPersonne.DAOPersonneImpl;

/**
 *
 * @author Ouzy NDIAYE
 */
public class Connexion extends HttpServlet {

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
    public ArrayList<model.Eleve> eleve2;
    public ArrayList<ListEleve> eleve3;
    public ArrayList<model.Eleve> eleve4;
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
    public ArrayList<model.Eleve> listeEleve;
    public ArrayList<Reclamation> reclamation;
    public ArrayList<model.Eleve> rechercheParElev;

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
        String connect = request.getParameter("connect");
        RequestDispatcher rd = null;
        String login = "";
        ///////////////////////////////////////////////
        if (connect == null) {
            rd = request.getRequestDispatcher("accue.html");
        } else if (connect.equals("connection")) {
            rd = request.getRequestDispatcher("vue/SeConnecter.jsp");
        } else if (connect.equals("authentifier")) {
            String profils = request.getParameter("profils");

            login = request.getParameter("login");
            String motDePasse = request.getParameter("motDePasse");
            personnes = daoPersonne.listPersonne(profils);
            System.out.println("Profils " + profils);
            int i = 0;
            for (Utilisateur p : personnes) {
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
                    rd = request.getRequestDispatcher("vue/prof/acceuilProf.jsp");
                } else if ((profils.equalsIgnoreCase("directeur")) && (login.equals(p.getLogin())) && (motDePasse.equals(p.getMotDePasse()))) {
                    session.setAttribute("log", login);
                    session.setAttribute("motDePasse", motDePasse);
                    session.setAttribute("prenom", p.getPrenom());
                    session.setAttribute("nom", p.getNom());
                    session.setAttribute("profils", profils);
                    i = 1;
                    rd = request.getRequestDispatcher("directeur/acceuilDir.jsp");
                } else if ((profils.equalsIgnoreCase("surveillant")) && (login.equals(p.getLogin())) && (motDePasse.equals(p.getMotDePasse()))) {
                    session.setAttribute("log", login);
                    session.setAttribute("motDePasse", motDePasse);
                    session.setAttribute("prenom", p.getPrenom());
                    session.setAttribute("nom", p.getNom());
                    session.setAttribute("profils", "surveillant");
                    i = 1;
                    rd = request.getRequestDispatcher("accueilSurv.jsp");
                } else if ((profils.equalsIgnoreCase("comptable")) && (login.equals(p.getLogin())) && (motDePasse.equals(p.getMotDePasse()))) {
                    session.setAttribute("log", login);
                    session.setAttribute("motDePasse", motDePasse);
                    session.setAttribute("prenom", p.getPrenom());
                    session.setAttribute("nom", p.getNom());
                    session.setAttribute("profils", "Comptable");
                    i = 1;
                    rd = request.getRequestDispatcher("Comptable/accueilCompta.jsp");
                }
            }
            if (i == 0) {
                String message = "Login et/ou mot de passe incorrect";
                request.setAttribute("mess", message);
                rd = request.getRequestDispatcher("vue/SeConnecter.jsp");
            }
        } else if (connect.equals("deconnection")) {
            session.invalidate();
            rd = request.getRequestDispatcher("vue/SeConnecter.jsp");
        }
        ////////////////////////////////////////////////
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
