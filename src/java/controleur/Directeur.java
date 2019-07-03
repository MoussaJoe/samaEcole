/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Bulletin;
import model.Classe;
import model.DAOFactory;
import modelPersonne.DAODirecteurImpl;
import modelPersonne.DAOPersonneImpl;
import modelPersonne.DAOProfImpl;
import model.Eleve;
import model.GenererPDF;
import model.Parent;
import modelTables.Personne;
import modele.tables.ProfClasse;
import model.Professeur;
import model.Surveillant;
import model.Utilisateur;
import modelPersonne.DAOEleveImpl;
import modelPersonne.DAOParentImpl;

/**
 *
 * @author Moussa Joseph Sarr
 */
public class Directeur extends HttpServlet {

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
    ArrayList<Classe> classes;
    ArrayList<Eleve> eleves;
    ArrayList<String> annees;
    private DAODirecteurImpl daoDirecteur;
    public Parent parent;
    public Professeur professeur;
    private DAOProfImpl daoProf;
    ArrayList<String> matieres;
    ArrayList<String> regimes;
    ArrayList<Professeur> profs;
    ArrayList<Surveillant> surv;
    private DAOPersonneImpl daoPersonne;
    private DAOParentImpl daoParent;
    public ArrayList<Eleve> rechercheParElev;

    Random rd1;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.daoEleve = daoFactory.getDAOEleve();
        this.daoProf = daoFactory.getDAOProf();
        this.daoPersonne = daoFactory.getDAOPersonne();
        this.daoDirecteur = daoFactory.getDAODirecteur();
        this.daoParent = daoFactory.getDAOParent();
        classes = new ArrayList();
        matieres = new ArrayList();
        profs = new ArrayList();
        surv = new ArrayList();
        eleves = new ArrayList();
        regimes = new ArrayList();
        rechercheParElev = new ArrayList<>();
        regimes.add("Privee");
        regimes.add("Public");

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        classes = daoDirecteur.listerClasse();
        session.setAttribute("classes", classes);
        int id = 0;
        matieres = daoProf.listerMatiere();
        session.setAttribute("matieres", matieres);
        RequestDispatcher rd = null;
        annees = daoEleve.listerAnnee();
        session.setAttribute("regimes", regimes);
        session.setAttribute("annees", annees);

        rd1 = new Random();

        String anInscr;
        String mois = new SimpleDateFormat("MM", Locale.FRANCE).format(Calendar.getInstance().getTime());
        System.out.println(mois);
        int newmois = Integer.parseInt(mois);
        if ((newmois >= 1) && (newmois <= 9)) {
            String annee = new SimpleDateFormat("yyyy", Locale.FRANCE).format(Calendar.getInstance().getTime());
            int newYear = Integer.parseInt(annee);
            int anneeBd = newYear - 1;
            anInscr = "" + anneeBd + "-" + annee;

        } else {
            String annee = new SimpleDateFormat("yyyy", Locale.FRANCE).format(Calendar.getInstance().getTime());
            int newYear = Integer.parseInt(annee);
            int anneeBd = newYear + 1;
            anInscr = "" + annee + "-" + anneeBd;

        }

        if (action.equals("ajoutEleve")) {
            rd = request.getRequestDispatcher("vue/surveillant/ajoutEleve.jsp");
        } else if (action.equals("inscription")) {
            request.setAttribute("anInscr", anInscr);
            rd = request.getRequestDispatcher("vue/surveillant/inscription.jsp");
        } else if (action.equals("modifierElv")) {

            String login = request.getParameter("nom");
            eleve = daoEleve.listerUnEleve(login);
            request.setAttribute("login", login);
            request.setAttribute("eleve", eleve);
            String year = daoEleve.listerAnnee(login);
            request.setAttribute("year", year);
            rd = request.getRequestDispatcher("directeur/modificationEleve.jsp");

        } else if (action.equals("formEleve")) {
            String loginIns = "";
            String mdpIns = "";
            String loginPar = "";
            String mdpPar = "";
            String idLog = request.getParameter("idLog");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String nomClasse = request.getParameter("nomClasse");
            String dateNaissance = request.getParameter("dateNaissance");
            String lieuNaissance = request.getParameter("lieuNaissance");
            String adresse = request.getParameter("adresse");
            String annee = request.getParameter("annee");
            String year = request.getParameter("year");
            String tel1 = request.getParameter("tel1");
            String tel2 = request.getParameter("tel2");
            String tel = tel1 + tel2;
            System.out.println(tel);
            String prenomParent = request.getParameter("prenomParent");
            String telParent1 = request.getParameter("telParent1");
            String telParent2 = request.getParameter("telParent2");
            String telParent = telParent1 + telParent2;
            System.out.println(tel);
            String chL = "";
            System.out.println(annee);
            int dateSaisi = Integer.parseInt(dateNaissance.substring(0, 4));
            int dateSco = Integer.parseInt(annee.substring(5, annee.length()));
            int dateCal = dateSaisi + 10;
            System.out.println("date saisie: " + dateSaisi);
            System.out.println("date scolaire: " + dateSco);
            for (int i = 0; i < 4; i++) {
                chL += rd1.nextInt(10);

            }

            String chM = "";
            for (int i = 0; i < 4; i++) {
                chM += rd1.nextInt(10);

            }

            String chpl = "";
            for (int i = 0; i < 4; i++) {
                chpl += rd1.nextInt(10);

            }

            String chpm = "";
            for (int i = 0; i < 4; i++) {
                chpm += rd1.nextInt(10);

            }
            if (dateCal > dateSco) {
                String message = "Veuillez revoir la date de naissance saisie!!!";
                request.setAttribute("message4", message);
                rd = request.getRequestDispatcher("vue/surveillant/inscription.jsp");
            } else if (nom.length() > 2) {
                eleve = new Eleve(nomClasse, nom, prenom, adresse, tel, dateNaissance, lieuNaissance, annee, null, null);
                eleve.setLogin(eleve.getNom().substring(0, 3).toUpperCase().concat(eleve.getPrenom().substring(0, 1).toUpperCase()) + "" + chL);
                eleve.setMotDePasse(eleve.getNom().substring(0, 3).toUpperCase() + "" + chM);
                loginIns = eleve.getNom().substring(0, 3).toUpperCase().concat(eleve.getPrenom().substring(0, 1).toUpperCase()) + "" + chL;
                mdpIns = eleve.getNom().substring(0, 3).toUpperCase() + "" + chM;
                String loginParent = eleve.getNom().substring(0, 3).toUpperCase().concat(prenomParent.substring(0, 1).toUpperCase()) + "" + chpl;
                String parentmdp = eleve.getNom().substring(0, 3).toUpperCase() + "" + chpm;
//                parent = new Parent(loginParent, nom, prenomParent, null, parentmdp);
                parent.setTel(telParent);
                loginPar = parent.getLoginParent();
                mdpPar = parent.getMotDePasse();
            } else {
                eleve = new Eleve(nomClasse, nom, prenom, adresse, tel, dateNaissance, lieuNaissance, annee, null, null);
                eleve.setLogin(eleve.getNom().substring(0, nom.length()).toUpperCase().concat(eleve.getPrenom().substring(0, 1).toUpperCase()) + "" + chL);
                eleve.setMotDePasse(eleve.getNom().substring(0, nom.length()).toUpperCase() + "" + chM);
                loginIns = eleve.getNom().substring(0, nom.length()).toUpperCase().concat(eleve.getPrenom().substring(0, 1).toUpperCase()) + "" + chL;
                mdpIns = eleve.getNom().substring(0, nom.length()).toUpperCase() + "" + chM;
                String loginParent = eleve.getNom().substring(0, nom.length()).toUpperCase().concat(prenomParent.substring(0, 1).toUpperCase()) + "" + chpl;
                String parentmdp = eleve.getNom().substring(0, nom.length()).toUpperCase() + "" + chpm;
                //parent = new Parent(loginParent, nom, prenomParent, null, parentmdp);
                parent.setTel(telParent);
                loginPar = parent.getLoginParent();
                mdpPar = parent.getMotDePasse();
            }

            Boolean resultat = daoEleve.inscription(eleve, parent);
            if (resultat) {
//                SendMessage app = new SendMessage();
//                try {
//                    app.doIt(tel, eleve.getLogin(), eleve.getMotDePasse());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                String message = "Inscription  effectué avec succés";
                request.setAttribute("message1", message);
                request.setAttribute("loginIns", loginIns);
                request.setAttribute("mdpIns", mdpIns);
                request.setAttribute("loginPar", loginPar);
                request.setAttribute("mdpPar", mdpPar);
                rd = request.getRequestDispatcher("vue/surveillant/inscription.jsp");
            } else {
                String message = "Inscription a échoué!!!";
                request.setAttribute("message2", message);
                rd = request.getRequestDispatcher("vue/surveillant/inscription.jsp");
            }

        } else if (action.equals("reinscription")) {
            rd = request.getRequestDispatcher("vue/surveillant/reinscription.jsp");
        } else if (action.equals("reinscription-form")) {
            String login = request.getParameter("login");
            String nomClasse = request.getParameter("nomClasse");
            String annee = request.getParameter("annee");
            eleve = daoEleve.reinscriptionEleve(login);
            if (eleve == null) {
                String message = "Le login ne correspond à aucun éléve!!";
                request.setAttribute("message3", message);
                rd = request.getRequestDispatcher("vue/surveillant/reinscription.jsp");
            } else {
                Boolean resultat = daoEleve.reinscription(eleve, annee, nomClasse);
                if (resultat) {
//                    SendMessage app = new SendMessage();
//                    try {
//                        app.doIt(eleve.getTel(), eleve.getLogin(), eleve.getMotDePasse());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                    String message = "Réinscription réussie !!!";
                    request.setAttribute("message1", message);
                    rd = request.getRequestDispatcher("vue/surveillant/reinscription.jsp");
                } else {
                    String message = "La Réinscription a échoué !!!";
                    request.setAttribute("message2", message);
                    rd = request.getRequestDispatcher("vue/surveillant/reinscription.jsp");
                }

            }
        } else if (action.equals("ajoutSurv")) {

            rd = request.getRequestDispatcher("directeur/ajoutSurv.jsp");
        } else if (action.equals("formSurv")) {
            int idPerson = 0;
            if (request.getParameter("idPerson") != null) {
                idPerson = Integer.parseInt(request.getParameter("idPerson"));
            }
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String adresse = request.getParameter("adresse");
            String tel1 = request.getParameter("tel1");
            String tel2 = request.getParameter("tel2");
            String tel = tel1 + tel2;

            String chpr = "";
            for (int i = 0; i < 4; i++) {
                chpr += rd1.nextInt(10);
            }

            String chprM = "";
            for (int i = 0; i < 4; i++) {
                chprM += rd1.nextInt(10);

            }
            String motDePasse;
            String login;
            if (nom.length() > 2) {

                login = nom.substring(0, 3).toUpperCase().concat(prenom.substring(0, 1).toUpperCase()) + "" + chpr;
                motDePasse = nom.substring(0, 3).toUpperCase() + "" + chprM;
                session.setAttribute("loginSurv", login);
                session.setAttribute("mdpSurv", motDePasse);
            } else {
                login = nom.substring(0, nom.length()).toUpperCase().concat(prenom.substring(0, 1).toUpperCase()) + "" + chpr;
                motDePasse = nom.substring(0, nom.length()).toUpperCase() + "" + chprM;
                session.setAttribute("loginSurv", login);
                session.setAttribute("mdpSurv", motDePasse);
            }

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setLogin(login);
            utilisateur.setMotDePasse(motDePasse);

            Personne personne = new Personne(id, nom, prenom, adresse, tel);
            boolean resultat = daoDirecteur.ajouterSurv(personne, utilisateur);
            if (resultat) {
                String msg = "ajout";
                request.setAttribute("msg", msg);
                rd = request.getRequestDispatcher("directeur/ajoutSurv.jsp");

            }
        } else if (action.equals("valideModEleve")) {

            String idLog = request.getParameter("idLog");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String nomClasse = request.getParameter("nomClasse");
            String dateNaissance = request.getParameter("dateNaissance");
            String lieuNaissance = request.getParameter("lieuNaissance");
            String adresse = request.getParameter("adresse");
            String year = request.getParameter("year");
            String tel = request.getParameter("tel");
            eleve = new Eleve(nomClasse, nom, prenom, adresse, tel, dateNaissance, lieuNaissance, year, null, null);
            daoEleve.modifierEleve(eleve, idLog);
            //eleves = daoEleve.listerEleve(nomClasse, year);
            String an = year;
            System.out.println(an);
            String nomCl = nomClasse;
            request.setAttribute("nomCl", nomCl);
            request.setAttribute("an", an);
            request.setAttribute("eleves", eleves);
            rd = request.getRequestDispatcher("directeur/listeClasse.jsp");
        } else if (action.equals("ajoutProf")) {
            request.setAttribute("anInscr", anInscr);
            rd = request.getRequestDispatcher("vue/surveillant/ajoutProfesseur.jsp");

        } else if (action.equals("bulletin")) {

            rd = request.getRequestDispatcher("vue/surveillant/classeBulletin.jsp");

        } else if (action.equals("bulletinClasse")) {

            String nomClasse = request.getParameter("nomClasse");
            String semestre = request.getParameter("semestre");
            String annee = request.getParameter("annee");
            request.setAttribute("annee", annee);
            request.setAttribute("semestre", semestre);
            // eleves = daoEleve.listerEleve(nomClasse, annee);
            request.setAttribute("eleves", eleves);
            for (Eleve e : eleves) {
                System.out.println(e.getLogin() + " " + annee + " " + semestre + " " + nomClasse);
                daoDirecteur.moyenne(e.getLogin(), annee, semestre, nomClasse);
            }

            Collections.sort(eleves);
            Collections.reverse(eleves);
            for (Eleve e : eleves) {
                System.out.println(e.getNom() + " " + e.getPrenom() + " " + e.getMoySemestre1());
            }
            rd = request.getRequestDispatcher("vue/surveillant/listeBulletin.jsp");
        } else if (action.equals("creerBulletin")) {
            String semestre = request.getParameter("semestre");
            String login = request.getParameter("login");
            String annee = request.getParameter("annee");
            request.setAttribute("eleves", eleves);
            int i = 0;

            System.out.println();
            Bulletin bulletin = new Bulletin();

            bulletin = daoDirecteur.eleve(login, annee, semestre);
            bulletin.setAnnee(annee);
            bulletin.setSemestre(semestre);

            if (semestre.equals("2eme_semestre")) {

                for (Eleve e : eleves) {
                    i++;
                    if ((e.getLogin().equals(login)) || (bulletin.getMoySemestre2() == e.getMoySemestre2())) {
                        break;
                    }
                }
            } else {
                for (Eleve e : eleves) {
                    i++;
                    if ((e.getLogin().equals(login)) || (bulletin.getMoySemestre1() == e.getMoySemestre1())) {
                        break;
                    }
                }
            }
            bulletin.setRang(i);
            System.out.println(bulletin.getRang());
            GenererPDF generer = new GenererPDF();

            try {
                generer.documentPDF(bulletin);
            } catch (DocumentException ex) {
                Logger.getLogger(Directeur.class.getName()).log(Level.SEVERE, null, ex);
            }

            String message = "Le bulletin a été créer avec succés";
            String nomClasse = bulletin.getNomClasse();
            request.setAttribute("annee", annee);
            request.setAttribute("semestre", semestre);
            //eleves = daoEleve.listerEleve(nomClasse, annee);
            Collections.sort(eleves);
            Collections.reverse(eleves);
            request.setAttribute("login", login);
            request.setAttribute("eleves", eleves);
            request.setAttribute("mess", message);
            rd = request.getRequestDispatcher("vue/surveillant/listeBulletin.jsp");
        } else if (action.equals("formProf")) {
            int idPerson = 0;
            if (request.getParameter("idPerson") != null) {
                idPerson = Integer.parseInt(request.getParameter("idPerson"));
            }
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String adresse = request.getParameter("adresse");
            String tel1 = request.getParameter("tel1");
            String tel2 = request.getParameter("tel2");
            String tel = tel1 + tel2;
            String annee = request.getParameter("annee");

            String chpr = "";
            for (int i = 0; i < 4; i++) {
                chpr += rd1.nextInt(10);

            }

            String chprM = "";
            for (int i = 0; i < 4; i++) {
                chprM += rd1.nextInt(10);

            }
            String motDePasse;
            String login;
            if (nom.length() > 2) {

                login = nom.substring(0, 3).toUpperCase().concat(prenom.substring(0, 1).toUpperCase()) + "" + chpr;
                motDePasse = nom.substring(0, 3).toUpperCase() + "" + chprM;
                session.setAttribute("loginProfesseur", login);
                session.setAttribute("mdpProfesseur", motDePasse);
            } else {
                login = nom.substring(0, nom.length()).toUpperCase().concat(prenom.substring(0, 1).toUpperCase()) + "" + chpr;
                motDePasse = nom.substring(0, nom.length()).toUpperCase() + "" + chprM;
                session.setAttribute("loginProfesseur", login);
                session.setAttribute("mdpProfesseur", motDePasse);
            }

            String[] nomClasse = request.getParameterValues("nomClasse");
            String[] nomMatiere = request.getParameterValues("nomMatiere");
            if ((nomClasse == null) || (nomMatiere == null)) {
                rd = request.getRequestDispatcher("vue/surveillant/ajoutProfesseur.jsp");
            } else {
                Collection<String> nomMat = Arrays.asList(nomMatiere);
                Collection<String> nomCl = Arrays.asList(nomClasse);

                Personne personne = new Personne(idPerson, nom, prenom, adresse, tel);

                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setLogin(login);

                utilisateur.setMotDePasse(motDePasse);
                request.setAttribute("login", login);
                request.setAttribute("motDePasse", motDePasse);

                request.setAttribute("annee", annee);
                Professeur professeur = new Professeur(personne, utilisateur, nomMat, nomCl);
                session.setAttribute("nomMat", nomMat);
                session.setAttribute("nomCl", nomCl);

                id = daoPersonne.nbrePersonne();
                id++;
                request.setAttribute("id", id);
                boolean resultat = daoProf.ajouterProf(professeur, id);
                if (resultat) {

                    rd = request.getRequestDispatcher("vue/surveillant/profclasse.jsp");

                }

            }
        } else if (action.equals("valideModProf")) {
            int idPerson = Integer.parseInt(request.getParameter("idPerson"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String adresse = request.getParameter("adresse");
            String tel = request.getParameter("tel");
            String annee = request.getParameter("annee");
            Personne personne = new Personne(idPerson, nom, prenom, adresse, tel);

            boolean resultat = daoProf.modifierProf(personne);
            if (resultat) {
              //  profs = daoProf.listerProf();
                request.setAttribute("profs", profs);
                rd = request.getRequestDispatcher("directeur/listeProfs.jsp");
            } else {
                rd = request.getRequestDispatcher("directeur/modificationProf.jsp");
            }
        } else if (action.equals("profclasse")) {
            int idPerson = Integer.parseInt(request.getParameter("id"));
            String loginProf = request.getParameter("login");
            String annee = request.getParameter("annee");
            Collection<String> nomCl = (Collection<String>) session.getAttribute("nomCl");
            ArrayList<ProfClasse> pc = new ArrayList<ProfClasse>();
            for (String r : nomCl) {
                String[] mats = request.getParameterValues(r);
                for (String q : mats) {
                    pc.add(new ProfClasse(idPerson, loginProf, r, q, annee));
                }

            }
            if (daoProf.ajouterProfclasse(pc)) {
                String msg = "ajout";
                request.setAttribute("msg", msg);
                rd = request.getRequestDispatcher("vue/surveillant/ajoutProfesseur.jsp");
            } else {
                rd = request.getRequestDispatcher("directeur/modificationProf.jsp");
            }
        } else if (action.equals("listerProfs")) {

            profs = daoProf.listerProf(anInscr);
            request.setAttribute("profs", profs);
            rd = request.getRequestDispatcher("directeur/listeProfs.jsp");

        } else if (action.equals("listerSurv")) {
            surv = daoDirecteur.listerSurv();
            request.setAttribute("surv", surv);
            rd = request.getRequestDispatcher("directeur/listeSurv.jsp");
        } else if (action.equals("modifierSurv")) {
            String loginSurv = request.getParameter("loginSurv");
            surv = daoDirecteur.selectSurv(loginSurv);
            for (Surveillant sur : surv) {
                request.setAttribute("idPersonne", sur.getIdPersonne());
                request.setAttribute("nom", sur.getNom());
                request.setAttribute("prenom", sur.getPrenom());
                request.setAttribute("adresse", sur.getAdresse());
                request.setAttribute("telephone", sur.getTelephone());
            }
            rd = request.getRequestDispatcher("directeur/modificationSurv.jsp");
        } else if (action.equals("valideModSurv")) {
            String loginSurv = request.getParameter("loginSurv");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String adresse = request.getParameter("adresse");
            String tel = request.getParameter("tel");
            daoDirecteur.updateSurv(loginSurv, nom, prenom, adresse, tel);
            surv = daoDirecteur.listerSurv();
            request.setAttribute("surv", surv);
            rd = request.getRequestDispatcher("directeur/listeSurv.jsp");
        } else if (action.equals("desactiverSurv")) {
            int idPersonne = Integer.parseInt(request.getParameter("idSurv"));
            daoDirecteur.desactiverSurv(idPersonne);
            surv = daoDirecteur.listerSurv();
            request.setAttribute("surv", surv);
            rd = request.getRequestDispatcher("directeur/listeSurv.jsp");
        } else if (action.equals("detailProf")) {
            String loginProf = request.getParameter("loginProf");
            ArrayList<Professeur> detailProf = daoProf.detailProf(loginProf);
            request.setAttribute("detailProf", detailProf);
            rd = request.getRequestDispatcher("directeur/detailProf.jsp");

        } else if (action.equals("listerClasse") || action.equals("supprimerEleve")) {
            String an = request.getParameter("year");
            String nomCl = request.getParameter("nomClasse");
            String regime = request.getParameter("regime");
            String moisEnLettre = "";
            if (mois.equalsIgnoreCase("01")) {
                moisEnLettre = "Janvier";
            } else if (mois.equalsIgnoreCase("02")) {
                moisEnLettre = "Fevrier";
            } else if (mois.equalsIgnoreCase("03")) {
                moisEnLettre = "Mars";
            } else if (mois.equalsIgnoreCase("04")) {
                moisEnLettre = "Avril";
            } else if (mois.equalsIgnoreCase("05")) {
                moisEnLettre = "Mai";
            } else if (mois.equalsIgnoreCase("06")) {
                moisEnLettre = "Juin";
            } else if (mois.equalsIgnoreCase("07")) {
                moisEnLettre = "Juillet";
            } else if (mois.equalsIgnoreCase("12")) {
                moisEnLettre = "Decembre";
            } else if (mois.equalsIgnoreCase("11")) {
                moisEnLettre = "Novembre";
            } else if (mois.equalsIgnoreCase("10")) {
                moisEnLettre = "Octobre";
            }
            eleves = daoEleve.listerEleve(nomCl, an, regime, moisEnLettre);
            request.setAttribute("nomCl", nomCl);
            request.setAttribute("regime", regime);
            request.setAttribute("an", an);
            for (Eleve el : eleves) {
                System.out.println(el.getLogin() + " " + el.getEtatPaiement());
            }
            request.setAttribute("eleves", eleves);

            rd = request.getRequestDispatcher("directeur/listeClasse.jsp");
        } else if (action.equals("formAffClass")) {

            rd = request.getRequestDispatcher("directeur/formAffClasse.jsp");
        } else if (action.equals("modifierProf")) {
            int idProf = Integer.parseInt(request.getParameter("idProf"));
            professeur = daoProf.listerUnProf(idProf);

            request.setAttribute("professeur", professeur);
            rd = request.getRequestDispatcher("directeur/modificationProf.jsp");
        } else if (action.equals("formNote")) {

            rd = request.getRequestDispatcher("directeur/formNote.jsp");

        } else if (action.equals("voirNote")) {
            eleves = null;
            String nomClasse = request.getParameter("nomClasse");
            String nomMatiere = request.getParameter("nomMatiere");
            String semestre = request.getParameter("semestre");
            String annee = request.getParameter("annee");
            String regime = request.getParameter("regime");
          //  eleves = daoDirecteur.consulterNotes(nomClasse, nomMatiere, semestre, annee);
            if (eleves == null) {
                rd = request.getRequestDispatcher("directeur/formNote.jsp");
            } else {
                request.setAttribute("semestre", semestre);
                request.setAttribute("annee", annee);
                request.setAttribute("nomClasse", nomClasse);
                request.setAttribute("nomMatiere", nomMatiere);
                request.setAttribute("eleves", eleves);
                rd = request.getRequestDispatcher("directeur/affichageNote.jsp");
            }

        } else if (action.equals("saveMatiere")) {
            rd = request.getRequestDispatcher("directeur/formMatiere.jsp");

        } else if (action.equals("validerMatiere")) {
            String nomMatiere = request.getParameter("nomMatiere");
            nomMatiere = nomMatiere.toLowerCase();
            boolean result = daoDirecteur.insertMatiere(nomMatiere);
            if (result) {
                String msg = "Matière: " + nomMatiere + "  ajoutée avec succée";
                request.setAttribute("mes", msg);
                rd = request.getRequestDispatcher("directeur/formMatiere.jsp");
            } else {
                String msg = "échec de l'ajout";
                request.setAttribute("message", msg);
                rd = request.getRequestDispatcher("directeur/formMatiere.jsp");
            }

        } else if (action.equals("saveClasse")) {

            rd = request.getRequestDispatcher("directeur/formClasse.jsp");
        } else if (action.equals("validerClasse")) {
            String regime = request.getParameter("regime");
            String nomClasse = request.getParameter("nomClasse");
            String[] nomMatiere = request.getParameterValues("nomMatiere");

            boolean result = daoDirecteur.insertClasse(nomClasse, nomMatiere, regime);
            if (result) {
                session.setAttribute("nomMatiere", nomMatiere);
                request.setAttribute("nomClasse", nomClasse);
                request.setAttribute("regime", regime);

                rd = request.getRequestDispatcher("directeur/coefficient.jsp");

            } else {
                rd = request.getRequestDispatcher("directeur/formClasse.jsp");
            }
        } else if (action.equals("insertcoef")) {
            String regime = request.getParameter("regime");
            String nomClasse = request.getParameter("nomClasse");
            String[] coefs = request.getParameterValues("coef");
            String[] nomMatieres = (String[]) session.getAttribute("nomMatiere");
            System.out.println(nomClasse);
            for (String nm : nomMatieres) {
                System.out.println(nm);
            }
            for (String c : coefs) {
                System.out.println(c);
            }
            boolean result = daoDirecteur.insertCoef(nomClasse, nomMatieres, coefs, regime);
            rd = request.getRequestDispatcher("directeur/formClasse.jsp");
        } //ajouter en fin 
        else if (action.equals("compte")) {
            rd = request.getRequestDispatcher("directeur/compte.jsp");

        } else if (action.equals("listeclasses")) {
            rd = request.getRequestDispatcher("directeur/classes.jsp");

        } else if (action.equals("detailsClasse")) {
            String regime = request.getParameter("regime");
            String nomClasse = request.getParameter("nomClasse");
            Classe classe = null;
            for (Classe cl : classes) {
                if ((cl.getNomClasse().equals(nomClasse)) && (cl.getRegime().equals(regime))) {
                    classe = new Classe(nomClasse, regime, cl.getMatieres());
                }
            }
            request.setAttribute("classe", classe);

            rd = request.getRequestDispatcher("directeur/detailsClasse.jsp");

        } else if (action.equals("annee")) {
            rd = request.getRequestDispatcher("directeur/annee.jsp");

        } else if (action.equals("ajoutAnnee")) {

            String newAnnee = request.getParameter("nv-annee");
            System.out.println("nouveau :" + newAnnee);
            boolean res = daoDirecteur.insertAnnee(newAnnee);
            request.setAttribute("res", res);
            annees = daoEleve.listerAnnee();
            rd = request.getRequestDispatcher("directeur/annee.jsp");

        } else if (action.equals("confirmPasswd")) {
            String newpasswd = request.getParameter("newpasswd");
            System.out.println("nouveau :" + newpasswd);
            String oldpasswd = request.getParameter("oldpasswd");
            System.out.println("ancien :" + oldpasswd);
            String newpasswd1 = request.getParameter("newpasswd1");
            System.out.println("nouveau :" + newpasswd1);
            String oldpasswd1 = (String) session.getAttribute("motDePasse");
            System.out.println("mot de passe :" + oldpasswd1);
            String login = (String) session.getAttribute("log");

            String profils = (String) session.getAttribute("profils");
            System.out.println("login :" + profils);
            if ((newpasswd.equals(newpasswd1)) && (oldpasswd.equals(oldpasswd1)) && (profils.equals("Directeur"))) {
                daoDirecteur.changePasswdDir(login, newpasswd);
                session.setAttribute("motDePasse", newpasswd);
                String msg = "Mot de Passe modifiée avec succés";
                request.setAttribute("mes", msg);
                rd = request.getRequestDispatcher("directeur/compte.jsp");

            } else if ((newpasswd.equals(newpasswd1)) && (oldpasswd.equals(oldpasswd1)) && (profils.equals("surveillant"))) {

                daoDirecteur.changePasswdSurv(login, newpasswd);
                session.setAttribute("motDePasse", newpasswd);
                String msg = "Mot de Passe modifiée avec succés";
                request.setAttribute("mes", msg);
                rd = request.getRequestDispatcher("directeur/compte.jsp");
            } else {
                String msg = "échec de la modification";
                request.setAttribute("message", msg);
                rd = request.getRequestDispatcher("directeur/compte.jsp");
            }
        } /////////////////////////////////////////////////Recherche////////////////////////////////////
        else if (action.equals("rechercher")) {
            String nom = request.getParameter("recherche");
            rechercheParElev = daoParent.rechercheEleveDir(nom);
//            for (Eleve e : rechercheParElev) {
//                System.out.println("nom: " + e.getNom());
//                System.out.println("prenom: " + e.getPrenom());
//            }
            if (rechercheParElev.isEmpty()) {
                String message = "Aucun résultat !!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("directeur/rechercheDir.jsp");
            } else {
                request.setAttribute("rechercheParElev", rechercheParElev);
                rd = request.getRequestDispatcher("directeur/rechercheDir.jsp");
            }
        } else if (action.equals("rechercherParSurv")) {
            String nom = request.getParameter("recherche");
            rechercheParElev = daoParent.rechercheEleveDir(nom);
//            for (Eleve e : rechercheParElev) {
//                System.out.println("nom: " + e.getNom());
//                System.out.println("prenom: " + e.getPrenom());
//            }
            if (rechercheParElev.isEmpty()) {
                String message = "Aucun résultat !!!";
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("vue/surveillant/rechercheSurv.jsp");
            } else {
                request.setAttribute("rechercheParElev", rechercheParElev);
                rd = request.getRequestDispatcher("vue/surveillant/rechercheSurv.jsp");
            }
        } ////////////////////////////////////////////////Fin Recheerche///////////////////////////////
        ////////////////////////////////Désactiver un prof///////////////////////////////////
        else if (action.equals("desactiverProf")) {
            int idProf = Integer.parseInt(request.getParameter("idProf"));
            daoProf.desactiverProf(idProf);
           // profs = daoProf.listerProf();
            request.setAttribute("profs", profs);
            rd = request.getRequestDispatcher("directeur/listeProfs.jsp");
        } else if (action.equals("desactiverEleve")) {
            String loginEleve = request.getParameter("loginEleve");
            //daoEleve.desactiverEleve(loginEleve);
            String an = request.getParameter("year");
            System.out.println(an);
            String nomCl = request.getParameter("nomCl");
            // eleves = daoEleve.listerEleve(nomCl, an);          
            request.setAttribute("nomCl", nomCl);
            request.setAttribute("an", an);
            request.setAttribute("eleves", eleves);
            rd = request.getRequestDispatcher("directeur/listeClasse.jsp");
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