/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOFactory;
import model.Reclamation;
import modelPersonne.DAOEleveImpl;
import modelPersonne.DAOParentImpl;
import modelTables.Personne;

/**
 *
 * @author Ouzy NDIAYE
 */
public class Eleve extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private DAOEleveImpl daoEleve;
    public model.Eleve eleve;
    ArrayList<model.Eleve> eleves;
    public ArrayList<Reclamation> reclamation;
    public Personne personne;
    ArrayList<Personne> profs;
    private DAOParentImpl daoParent;
    public ArrayList<model.Eleve> rechercheParElev;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.daoEleve = daoFactory.getDAOEleve();
        this.daoParent = daoFactory.getDAOParent();
        reclamation = new ArrayList<>();
        rechercheParElev = new ArrayList<>();
        //this.daoProf = daoFactory.getDAOProf();
        //this.daoPersonne = daoFactory.getDAOPersonneImpl();
        // this.daoDirecteur = daoFactory.getDAODirecteur();

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String connect = request.getParameter("connect");
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("log");
        RequestDispatcher rd = null;

        String an;
        String mois = new SimpleDateFormat("MM", Locale.FRANCE).format(Calendar.getInstance().getTime());
        int newmois = Integer.parseInt(mois);
        if ((newmois >= 1) && (newmois <= 9)) {
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
        ///////////////////////////|||||||||||||||||||||||||||||||||||||||||||//////////////////////
        if (connect == null) {
            rd = request.getRequestDispatcher("acceuilEleve.jsp");
        } //////Lister la classe de l'élève connecter
        else if (connect.equals("listerMaClasse")) {
            eleves = daoEleve.listerclasse(login, an);
            request.setAttribute("eleves", eleves);
            request.setAttribute("login", login);
            rd = request.getRequestDispatcher("Eleve/listeMaClasse.jsp");
        } //Lister les prof de l'élève connecter
        else if (connect.equals("listeMesProfs")) {
            profs = daoEleve.listerProf(login, an);
            request.setAttribute("profs", profs);
            rd = request.getRequestDispatcher("Eleve/listeMesProfs.jsp");
        } ///Fonctionnalité Afficher notes
        else if (connect.equals("afficherNote")) {
            ArrayList<String> annees = new ArrayList<>();
            annees = daoEleve.anneeScolaire(login);
            request.setAttribute("annees", annees);
            rd = request.getRequestDispatcher("Eleve/listeAnnee.jsp");
        } else if ((connect.equals("anneeScolaire")) || (connect.equals("1er_semestre")) || (connect.equals("2eme_semestre"))) {

            String annee = request.getParameter("annee");
            if ((connect.equals("1er_semestre")) || (connect.equals("2eme_semestre"))) {

                request.setAttribute("action", connect);
                System.out.println(login);
                eleves = daoEleve.evaluationEleve(login, connect, annee);
                for (model.Eleve e : eleves) {
                    System.out.println(e.getDevoir1());
                    System.out.println(e.getMatiere());
                }
                request.setAttribute("eleves", eleves);
            }
            request.setAttribute("an", an);
            request.setAttribute("annee", annee);
            String varAn = null;
            if(an.equals(annee)){
               varAn  = "exist"; 
            }
            request.setAttribute("varAn", varAn);
            rd = request.getRequestDispatcher("Eleve/affichageNoteEleve.jsp");
        }
        //////////////////////////||||||||||||||||||||||||||||||||||||||||||||///////////////////////
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
