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
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOFactory;
import model.GestionDate;
import model.ListEleve;
import model.Mensuel;
import model.Parent;
import model.Reclamation;
import model.Utilisateur;
import modelPersonne.DAOEleveImpl;
import modelPersonne.DAOParentImpl;
import modelPersonne.DAOPersonneImpl;
import modelTables.Personne;

/**
 *
 * @author Ouzy NDIAYE
 */
public class EDT extends HttpServlet {

    private DAOPersonneImpl daoPersonne;
    public ArrayList<model.Eleve> listClasse;
    public ArrayList<model.Eleve> afficherEDT;
    public ArrayList<Mensuel> listMensuel;
    public ArrayList<String> listMatiere;
    public ArrayList<String> listSalle;
    private DAOEleveImpl daoEleve;
    private DAOParentImpl daoParent;
    public ArrayList<Personne> listerProf;
    Random rd1;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.daoPersonne = daoFactory.getDAOPersonne();
        this.daoEleve = daoFactory.getDAOEleve();
        this.daoParent = daoFactory.getDAOParent();
        listClasse = new ArrayList();
        listMatiere = new ArrayList();
        listerProf = new ArrayList();
        listSalle = new ArrayList();
        afficherEDT = new ArrayList();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String req = request.getParameter("connect");
        RequestDispatcher rd = null;
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        String dateToday = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(Calendar.getInstance().getTime());
        String anInscr;
        String annee = "";
        String mois = new SimpleDateFormat("MM", Locale.FRANCE).format(Calendar.getInstance().getTime());
        int newmois = Integer.parseInt(mois);
        if ((newmois >= 1) && (newmois <= 9)) {
            annee = new SimpleDateFormat("yyyy", Locale.FRANCE).format(Calendar.getInstance().getTime());
            int newYear = Integer.parseInt(annee);
            int anneeBd = newYear - 1;
            anInscr = "" + anneeBd + "-" + annee;

        } else {
            annee = new SimpleDateFormat("yyyy", Locale.FRANCE).format(Calendar.getInstance().getTime());
            int newYear = Integer.parseInt(annee);
            int anneeBd = newYear + 1;
            anInscr = "" + annee + "-" + anneeBd;

        }
        /////////////////////
        GestionDate gd = new GestionDate();
        //Heure de cours
        String[] heures = {"8h-10h", "10h-12h", "13h-15h",
            "15h-17h"};
        if (req == null) {
            rd = request.getRequestDispatcher("EmploiDuTemps/afficherEDT.jsp");
        } else if (req.equals("accueilEDT")) {
            listClasse = daoEleve.listerClasse();
            session.setAttribute("listClasse", listClasse);
            rd = request.getRequestDispatcher("EmploiDuTemps/accueilEDT.jsp");
        } else if (req.equals("creerEDT")) {
            String nomClasse = request.getParameter("nomClasse");
            String[] regime = nomClasse.split("///");
            String nomClasse1 = regime[0];
            String regime1 = regime[1];      
            session.setAttribute("heure", heures);
            session.setAttribute("classe", nomClasse1);
            session.setAttribute("regime", regime1);
            session.setAttribute("annee", anInscr);
            rd = request.getRequestDispatcher("EmploiDuTemps/creerEDT.jsp");
        } else if (req.equals("ajouterCours")) {
            String nomClasse = request.getParameter("nomClasse");
            String regime = request.getParameter("regime");
            String heure = request.getParameter("heure");
            String jour = request.getParameter("jour"); 
            System.out.println("annee-sco "+anInscr);
            ////Gesttion des erreurs d'ajout
            //A faire verif prof
            boolean rsDisponibilite = daoEleve.verifDisponibilite(gd.formatJour(Integer.parseInt(jour)), heure, nomClasse, regime);
            if (rsDisponibilite == true) {
                String msg = "erreurDisponibilité";
                request.setAttribute("noDispo", msg);
                rd = request.getRequestDispatcher("EmploiDuTemps/creerEDT.jsp");
            }else{
            listSalle = daoEleve.listerSalleClasse();
            listMatiere = daoEleve.listerMatiereClasse(nomClasse, regime);
            listerProf = daoEleve.listerProfClasse(nomClasse, regime, anInscr);            
            session.setAttribute("jour", jour);
            session.setAttribute("heure", heure);
            session.setAttribute("nomClasse", nomClasse);
            session.setAttribute("listMatiere", listMatiere);
            session.setAttribute("listerProf", listerProf);
            session.setAttribute("regime", regime);
            session.setAttribute("listSalle", listSalle);
            rd = request.getRequestDispatcher("EmploiDuTemps/ajouterCours.jsp");
            }
        } else if (req.equals("valider-ajout")) {
            String heure = request.getParameter("heure");
            int numJour = Integer.parseInt(request.getParameter("jour"));
            int numHeure = gd.numeroHeure(heure);
            String nomClasse = request.getParameter("nomClasse");
            String regime = request.getParameter("regime");
            String nomMatiere = request.getParameter("nomMatiere");
            String loginProf = request.getParameter("nomProf");
            String nomSalle = request.getParameter("nomSalle");

            String[] loginEtNomProf = loginProf.split("///");
            String nomProf = loginEtNomProf[0];
            String login = loginEtNomProf[1];            

            /*boolean resultat = daoEleve.ajouterCours(login, nomMatiere, regime, nomClasse, nomSalle, gd.formatJour(numJour), heure);
            if (resultat == true) {
                String msg = "insertion réussit";
                session.setAttribute("classe", nomClasse);
                session.setAttribute("regime", regime);
                session.setAttribute("heure", heures);
                request.setAttribute("msgInsertReussit", msg);
                rd = request.getRequestDispatcher("EmploiDuTemps/creerEDT.jsp");
            }*/
        } ////Afficher emploi du temps
        else if (req.equals("afficherEDT")) {
            listClasse = daoEleve.listerClasse();
            session.setAttribute("listClasse", listClasse);
            rd = request.getRequestDispatcher("EmploiDuTemps/voirEDT.jsp");
        } else if (req.equals("afficherEDTClasse")) {
            String nomClasse = request.getParameter("nomClasse");
            String[] regime = nomClasse.split("///");
            String nomClasse1 = regime[0];
            String regime1 = regime[1];
            String emploiDuTemps[][] = daoEleve.afficherEDT(nomClasse1, regime1);
            session.setAttribute("heure", heures);
            session.setAttribute("classe", nomClasse1);
            session.setAttribute("regime", regime1);
            session.setAttribute("afficherEDT", emploiDuTemps);
            rd = request.getRequestDispatcher("EmploiDuTemps/afficherEDT.jsp");
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
