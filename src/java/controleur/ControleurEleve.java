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
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOFactory;
import model.Eleve;
import model.MessageRecu;
import model.Reclamation;
import modelTables.Personne;
import modelPersonne.DAOEleveImpl;
import modelPersonne.DAOParentImpl;

/**
 *
 * @author Moussa Joseph Sarr
 */
public class ControleurEleve extends HttpServlet {

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
    public Eleve eleve;
    ArrayList<Eleve> eleves;
    public ArrayList<Reclamation> reclamation;
    public Personne personne;
    ArrayList<Personne> profs;
    private DAOParentImpl daoParent;
    public ArrayList<Eleve> rechercheParElev;
    public ArrayList<MessageRecu> messR;

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
        String action = request.getParameter("action");
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

        if ((action.equals("anneeScolaire")) || (action.equals("1er_semestre")) || (action.equals("2eme_semestre"))) {

            String annee = request.getParameter("annee");
            if ((action.equals("1er_semestre")) || (action.equals("2eme_semestre"))) {

                request.setAttribute("action", action);
                System.out.println(login);
                eleves = daoEleve.evaluationEleve(login, action, annee);
                for (Eleve e : eleves) {
                    System.out.println(e.getDevoir1());
                    System.out.println(e.getMatiere());
                }
                request.setAttribute("eleves", eleves);
            }
            request.setAttribute("an", an);
            request.setAttribute("annee", annee);
            String varAn = null;
            if (an.equals(annee)) {
                varAn = "exist";
            }
            request.setAttribute("varAn", varAn);
            rd = request.getRequestDispatcher("vue/elev/affichageNoteEleve.jsp");
        } else if ((action.equals("anneeScolaireCompo")) || (action.equals("1er_semestre_compo")) || (action.equals("2eme_semestre_compo"))) {

            String annee = request.getParameter("annee");
            if ((action.equals("1er_semestre_compo"))) {
                action = "1er_semestre";
            } else if (action.equals("2eme_semestre_compo")) {
                action = "2eme_semestre";
            }

            request.setAttribute("action", action);
            System.out.println(login);
            eleves = daoEleve.evaluationEleveCompo(login, action, annee);
            request.setAttribute("eleves", eleves);

            request.setAttribute("an", an);
            request.setAttribute("annee", annee);
            String varAn = null;
            if (an.equals(annee)) {
                varAn = "exist";
            }
            request.setAttribute("varAn", varAn);
            rd = request.getRequestDispatcher("vue/elev/affichageNoteCompoEleve.jsp");
        } else if (action.equals(
                "afficherNote")) {
            ArrayList<String> annees = new ArrayList<>();

            annees = daoEleve.anneeScolaire(login);
            for (String a : annees) {
                System.out.println(a);
            }
            request.setAttribute("annees", annees);
            rd = request.getRequestDispatcher("vue/elev/listeAnnee.jsp");
        } else if (action.equals(
                "afficherNoteCompo")) {
            ArrayList<String> annees = new ArrayList<>();

            annees = daoEleve.anneeScolaire(login);
            for (String a : annees) {
                System.out.println(a);
            }
            request.setAttribute("annees", annees);
            rd = request.getRequestDispatcher("vue/elev/listeAnneeCompo.jsp");
        } else if (action.equals(
                "listerMaClasse")) {
            eleves = daoEleve.listerclasse(login, an);
            request.setAttribute("eleves", eleves);
            request.setAttribute("login", login);
            rd = request.getRequestDispatcher("vue/elev/listeMaClasse.jsp");
        } else if (action.equals(
                "listeMesProfs")) {

            profs = daoEleve.listerProf(login, an);

            request.setAttribute("profs", profs);
            rd = request.getRequestDispatcher("vue/elev/listeMesProfs.jsp");
        } /////Dattte////////////////////////////////////////////////////////
        else if (action.equals(
                "reclamer")) {
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
            String nomMatiere = request.getParameter("nomMatiere");
            String loginEleve = (String) session.getAttribute("log");
            String nom = (String) session.getAttribute("nom");
            String prenom = (String) session.getAttribute("prenom");
            String nomClasse = daoEleve.nomClasse(loginEleve, an);
            String loginProf = daoEleve.reclamation(nomClasse, nomMatiere, an);
            System.out.println("loginProf: " + loginProf);
            request.setAttribute("loginEleve", loginEleve);
            request.setAttribute("loginProf", loginProf);
            request.setAttribute("nom", nom);
            request.setAttribute("prenom", prenom);
            request.setAttribute("nomClasse", nomClasse);

            rd = request.getRequestDispatcher("vue/elev/reclamation.jsp");
        } else if (action.equals(
                "reclamation")) {
            String loginEleve = request.getParameter("loginEleve");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String loginProf = request.getParameter("loginProf");
            String nomClasse = request.getParameter("nomClasse");
            String message = request.getParameter("message");
            String enTete = prenom.toUpperCase() + " " + nom.toUpperCase() + " " + nomClasse;
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date today = Calendar.getInstance().getTime();
            String date = df.format(today);
            daoEleve.insereReclamation(loginEleve, loginProf, message, enTete, date);
            String accuRec = "Réclamation envoyée";
            request.setAttribute("messAcu", accuRec);
            rd = request.getRequestDispatcher("vue/elev/reclamation.jsp");
        } else if (action.equals(
                "afficheMessage")) {
            String loginEleve = (String) session.getAttribute("log");
            reclamation = daoEleve.selectReclamationRep(loginEleve);
            if (reclamation.isEmpty()) {
                String message = "Vous n'avez aucun message";
                request.setAttribute("message", message);
            }
            request.setAttribute("listReponse", reclamation);
            for (Reclamation rec : reclamation) {
                System.out.println(rec.getMessage());
            }
            rd = request.getRequestDispatcher("vue/elev/messageEleve.jsp");
        } ///////////////////////////////////////Compte////////////////////////////////////////////
        else if (action.equals(
                "compte")) {
            rd = request.getRequestDispatcher("vue/elev/compteEleve.jsp");

        } else if (action.equals(
                "confirmPasswd")) {
            String newpasswd = request.getParameter("newpasswd");
            System.out.println("nouveau :" + newpasswd);
            String oldpasswd = request.getParameter("oldpasswd");
            System.out.println("ancien :" + oldpasswd);
            String newpasswd1 = request.getParameter("newpasswd1");
            System.out.println("nouveau :" + newpasswd1);
            String oldpasswd1 = (String) session.getAttribute("motDePasse");
            System.out.println("mot de passe :" + oldpasswd1);
            login = (String) session.getAttribute("log");
            System.out.println("login :" + login);
            if ((newpasswd.equals(newpasswd1)) && (oldpasswd.equals(oldpasswd1))) {
                daoEleve.changepswd(login, newpasswd);
                session.setAttribute("motDePasse", newpasswd);
                String msg = "Mot de Passe modifiée avec succés";
                request.setAttribute("mes", msg);
                rd = request.getRequestDispatcher("vue/elev/compteEleve.jsp");

            } else {
                String msg = "échec de la modification";
                request.setAttribute("message", msg);
                rd = request.getRequestDispatcher("vue/elev/compteEleve.jsp");
            }
        } ///////////////////////////////////////Fin Compte////////////////////////////////////////////
        /////////////////////////////////////////////////Recherche////////////////////////////////////
        else if (action.equals(
                "rechercher")) {
            String nom = request.getParameter("recherche");
            rechercheParElev = daoParent.rechercheEleve(nom);
            for (Eleve e : rechercheParElev) {
                System.out.println("nom: " + e.getNom());
                System.out.println("prenom: " + e.getPrenom());
            }
            if (rechercheParElev.isEmpty()) {
                String message = "Aucun résultat !!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("vue/elev/rechercheEleve.jsp");
            } else {
                request.setAttribute("rechercheParElev", rechercheParElev);
                rd = request.getRequestDispatcher("vue/elev/rechercheEleve.jsp");
            }
        }
        
        
        else if (action.equals("afficheMess")) {
            String loginEleve = (String)session.getAttribute("log");
            System.out.println("login-------------------- "+loginEleve);
            messR = daoEleve.messageRecuEleve(loginEleve);
            System.out.println("//////////////////////idReclamation////////////////////////////");
            
            request.setAttribute("listMessage", messR);
            rd = request.getRequestDispatcher("vue/elev/message.jsp");
        }
        
        
        else if(action.equals("afficheMesse")){
            String date= request.getParameter("date");
            String texte= request.getParameter("texte");
            String login_pro= request.getParameter("login_pro");
            String loginEleve= request.getParameter("login");
            System.out.println("login_pro: "+login_pro);
            System.out.println("login: "+login);
            ArrayList<String> messRecu=daoEleve.selectMessageRecuEleve(login_pro, login); 
            ArrayList<String> messEvoye=daoEleve.selectMessageEnvoyeEleve(login_pro, login);
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
            rd = request.getRequestDispatcher("vue/elev/chat.jsp");
        }
        else if(action.equals("repondre")){
            String loginEleve=(String)session.getAttribute("log");
            String message= request.getParameter("message");
            String pro_login=request.getParameter("login");
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date today = Calendar.getInstance().getTime();
            String date = df.format(today);
            daoEleve.reponseEleve(loginEleve, pro_login, message, date);
            ArrayList<String> messRecu=daoEleve.selectMessageRecuEleve(pro_login, login); 
            ArrayList<String> messEvoye=daoEleve.selectMessageEnvoyeEleve(pro_login, login);
            request.setAttribute("login", login);
            request.setAttribute("messRecu", messRecu);
            request.setAttribute("messEvoye", messEvoye);
            rd = request.getRequestDispatcher("vue/elev/chat.jsp");
        }
        
        
        
        
        
        
        
        
        
        
        
        ////////////////////////////////////////////////Fin Recheerche///////////////////////////////
        if (rd
                != null) {
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
