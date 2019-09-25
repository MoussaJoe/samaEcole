/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Absence;
import model.Bulletin;
import model.Classe;
import model.DAOFactory;
import model.Parent;
import model.Eleve;
import model.GenererPDF;
import model.GenererRef;
import model.Matiere_Absence;
import model.Professeur;
import model.Utilisateur;
import modelPersonne.DAODirecteurImpl;
import modelPersonne.DAOEleveImpl;
import modelPersonne.DAOParentImpl;
import modelPersonne.DAOPersonneImpl;
import modelPersonne.DAOProfImpl;
import modelTables.Personne;
import modele.tables.ProfClasse;

/**
 *
 * @author Moussa Joseph D Sarr
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20)
public class Surveillant extends HttpServlet {

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
    public Absence absence;
    ArrayList<Classe> classes;
    ArrayList<Absence> absences;
    ArrayList<Eleve> eleves;
    ArrayList<String> annees;
    private DAODirecteurImpl daoDirecteur;
    public Parent parent;
    public Professeur professeur;
    private DAOProfImpl daoProf;
    public ArrayList<Utilisateur> compte;
    ArrayList<String> matieres;
    ArrayList<String> regimes;
    ArrayList<Professeur> profs;
    ArrayList<Surveillant> surv;
    private DAOPersonneImpl daoPersonne;
    private DAOParentImpl daoParent;
    public ArrayList<Eleve> rechercheParElev;

    private final String RepDestinationImg = "D:\\Personnel\\7Tup\\Projet_7Tup\\samaEcole\\web\\ImageUser";

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
        absences = new ArrayList<>();
        profs = new ArrayList();
        surv = new ArrayList();
        eleves = new ArrayList();
        regimes = new ArrayList();
        rechercheParElev = new ArrayList<>();
        regimes.add("Privée");
        regimes.add("Public");

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        RequestDispatcher rd = null;

        HttpSession session = request.getSession();
        classes = daoDirecteur.listerClasse();
        session.setAttribute("classes", classes);
        int id = 0;
        matieres = daoProf.listerMatiere();
        session.setAttribute("matieres", matieres);
        annees = daoEleve.listerAnnee();
        session.setAttribute("regimes", regimes);
        session.setAttribute("annees", annees);

        rd1 = new Random();

        String anInscr;
        String mois = new SimpleDateFormat("MM", Locale.FRANCE).format(Calendar.getInstance().getTime());
        int newmois = Integer.parseInt(mois);
        if ((newmois >= 1) && (newmois <= 7)) {
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
            rd = request.getRequestDispatcher("surveillant/ajoutEleve.jsp");
        } else if (action.equals("inscription")) {
            request.setAttribute("anInscr", anInscr);
            rd = request.getRequestDispatcher("surveillant/inscription.jsp");
        } else if (action.equals("modifierElv")) {
            String regime = request.getParameter("regime");
            String an = request.getParameter("year");
            String login = request.getParameter("nom");
            eleve = daoEleve.listerUnEleve(login, an, regime);
            request.setAttribute("login", login);
            request.setAttribute("eleve", eleve);
            rd = request.getRequestDispatcher("surveillant/modificationEleve.jsp");
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
                rd = request.getRequestDispatcher("surveillant/inscription.jsp");
            } else if (nom.length() > 2) {
                eleve = new model.Eleve(nomClasse, nom, prenom, adresse, tel, dateNaissance, lieuNaissance, annee, null, null);
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
                eleve = new model.Eleve(nomClasse, nom, prenom, adresse, tel, dateNaissance, lieuNaissance, annee, null, null);
                eleve.setLogin(eleve.getNom().substring(0, nom.length()).toUpperCase().concat(eleve.getPrenom().substring(0, 1).toUpperCase()) + "" + chL);
                eleve.setMotDePasse(eleve.getNom().substring(0, nom.length()).toUpperCase() + "" + chM);
                loginIns = eleve.getNom().substring(0, nom.length()).toUpperCase().concat(eleve.getPrenom().substring(0, 1).toUpperCase()) + "" + chL;
                mdpIns = eleve.getNom().substring(0, nom.length()).toUpperCase() + "" + chM;
                String loginParent = eleve.getNom().substring(0, nom.length()).toUpperCase().concat(prenomParent.substring(0, 1).toUpperCase()) + "" + chpl;
                String parentmdp = eleve.getNom().substring(0, nom.length()).toUpperCase() + "" + chpm;
                parent = new Parent(loginParent, nom, prenomParent, telParent, parentmdp, "Parent", 1, null);
                parent.setTel(telParent);
                loginPar = parent.getLoginParent();
                mdpPar = parent.getMotDePasse();
            }

            Boolean resultat = daoEleve.inscription(eleve, parent);
            if (resultat) {
                String message = "Inscription  effectué avec succés";
                request.setAttribute("message1", message);
                request.setAttribute("loginIns", loginIns);
                request.setAttribute("mdpIns", mdpIns);
                request.setAttribute("loginPar", loginPar);
                request.setAttribute("mdpPar", mdpPar);
                rd = request.getRequestDispatcher("surveillant/inscription.jsp");
            } else {
                String message = "Inscription a échoué!!!";
                request.setAttribute("message2", message);
                rd = request.getRequestDispatcher("surveillant/inscription.jsp");
            }

        } else if (action.equals("reinscription")) {
            rd = request.getRequestDispatcher("surveillant/reinscription.jsp");
        } else if (action.equals("reinscription-form")) {
            String login = request.getParameter("login");
            String nomClasse = request.getParameter("nomClasse");
            String annee = request.getParameter("annee");
            eleve = daoEleve.reinscriptionEleve(login);
            if (eleve == null) {
                String message = "Le login ne correspond à aucun éléve!!";
                request.setAttribute("message3", message);
                rd = request.getRequestDispatcher("surveillant/reinscription.jsp");
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
                    rd = request.getRequestDispatcher("surveillant/reinscription.jsp");
                } else {
                    String message = "La Réinscription a échoué !!!";
                    request.setAttribute("message2", message);
                    rd = request.getRequestDispatcher("surveillant/reinscription.jsp");
                }

            }
        } else if ((action.equals("valideModEleve")) || (action.equals("supprimerElv"))) {
            String nomClasse = request.getParameter("nomClasse");
            String regime = request.getParameter("regime");
            String year = request.getParameter("year");
            if (action.equals("valideModEleve")) {
                String login = request.getParameter("idLog");
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String dateNaissance = request.getParameter("dateNaissance");
                String lieuNaissance = request.getParameter("lieuNaissance");
                String adresse = request.getParameter("adresse");
                String tel = request.getParameter("tel");

                eleve = new Eleve(nomClasse, nom, prenom, adresse, tel, dateNaissance, lieuNaissance, year, null, null);
                eleve.setRegime(regime);
                daoEleve.modifierEleve(eleve, login);
            } else if (action.equals("supprimerElv")) {
                String login = request.getParameter("nom");
                System.out.println(nomClasse + " " + year + " " + regime);
                daoEleve.supprimerEleve(regime, year, login);
            }
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
            System.out.println(nomClasse + " " + year + " " + regime + " " + moisEnLettre);
            eleves = daoEleve.listerEleve(nomClasse, year, regime, moisEnLettre);
            request.setAttribute("nomCl", nomClasse);
            request.setAttribute("regime", regime);
            request.setAttribute("an", year);
            request.setAttribute("eleves", eleves);
            rd = request.getRequestDispatcher("surveillant/listeClasse.jsp");
        } else if (action.equals("validerInscr")) {
            rd = request.getRequestDispatcher("surveillant/validerInscr.jsp");
        } else if (action.equals("inscrit")) {
            String login = request.getParameter("login");

            Utilisateur resultat = new Utilisateur();
            resultat = daoDirecteur.validerInscr(login);
            System.out.println(resultat.getMotDePasse());
            System.out.println(resultat.getLogin());
            rd = request.getRequestDispatcher("surveillant/validerInscr.jsp");
        } else if (action.equals("ajoutProf")) {
            request.setAttribute("anInscr", anInscr);
            rd = request.getRequestDispatcher("surveillant/ajoutProfesseur.jsp");
        } else if (action.equals("bulletin")) {

            rd = request.getRequestDispatcher("surveillant/classeBulletin.jsp");

        } else if (action.equals("bulletinClasse")) {

            String nomClasse = request.getParameter("nomClasse");
            String semestre = request.getParameter("semestre");
            String annee = request.getParameter("annee");
            String regime = request.getParameter("regime");
            request.setAttribute("annee", annee);
            request.setAttribute("semestre", semestre);
            eleves = daoEleve.listerEleve(nomClasse, annee, regime, mois);
            request.setAttribute("eleves", eleves);
            for (Eleve e : eleves) {

                daoDirecteur.moyenne(e.getLogin(), annee, semestre, nomClasse);
            }

            eleves = daoEleve.rangerEleve(nomClasse, annee, regime, mois, semestre);

            rd = request.getRequestDispatcher("surveillant/listeBulletin.jsp");
        } else if (action.equals("creerBulletin")) {
            String semestre = request.getParameter("semestre");
            String login = request.getParameter("login");
            String annee = request.getParameter("annee");
            String regime = request.getParameter("regime");
            request.setAttribute("eleves", eleves);
            int i = 0;
            Bulletin bulletin = new Bulletin();

            bulletin = daoDirecteur.eleve(login, annee, semestre, regime);
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
            System.out.println(bulletin.toString());
            // Donnees ajoutees
            request.setAttribute("bulletin", bulletin);
            rd = request.getRequestDispatcher("surveillant/bulletin.jsp");
        } else if (action.equals("imprimer")) {
            rd = request.getRequestDispatcher("surveillant/bulletin.jsp");
        } else if (action.equals("recap")) {
            rd = request.getRequestDispatcher("surveillant/form-recap.jsp");
        } else if (action.equals("recapMoy")) {
            String nomClasse = request.getParameter("nomClasse");
            String semestre = request.getParameter("semestre");
            String annee = request.getParameter("annee");
            String regime = request.getParameter("regime");
            request.setAttribute("annee", annee);
            request.setAttribute("semestre", semestre);
            request.setAttribute("nomClasse", nomClasse);
            eleves = daoEleve.rangerEleve(nomClasse, annee, regime, mois, semestre);
            ArrayList<Bulletin> bulletins = new ArrayList<>();

            for (Eleve leve : eleves) {
                bulletins.add(daoDirecteur.recapMoyenne(leve.getLogin(), annee, semestre, regime));
            }
            int i = 0;
            for (Bulletin bul : bulletins) {
                i++;
                bul.setRang(i);
            }
            request.setAttribute("bulletins", bulletins);
            rd = request.getRequestDispatcher("surveillant/recap-moyenne.jsp");

        } else if (action.equals("retourner")) {

            rd = request.getRequestDispatcher("surveillant/form-recap.jsp");

        } else if (action.equals("formProf")) {

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
                rd = request.getRequestDispatcher("surveillant/ajoutProfesseur.jsp");
            } else {
                Collection<String> nomMat = Arrays.asList(nomMatiere);
                Collection<String> nomCl = Arrays.asList(nomClasse);

                Personne personne = new Personne(0, nom, prenom, adresse, tel);

                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setLogin(login);

                utilisateur.setMotDePasse(motDePasse);

                Professeur professeur = new Professeur(personne, utilisateur, nomMat, nomCl);
                System.out.println(professeur.getPersonne().getPrenom());
                boolean resultat = daoProf.ajouterProf(professeur);
                if (resultat) {
                    request.setAttribute("motDePasse", motDePasse);

                    request.setAttribute("annee", annee);
                    session.setAttribute("login", login);
                    session.setAttribute("nomMat", nomMat);
                    session.setAttribute("nomCl", nomCl);
                    rd = request.getRequestDispatcher("surveillant/profclasse.jsp");
                } else {
                    rd = request.getRequestDispatcher("surveillant/ajoutProfesseur.jsp");
                }
            }
        } else if (action.equals("valideModProf")) {
            String login = request.getParameter("login");
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String adresse = request.getParameter("adresse");
            String tel = request.getParameter("tel");
            String annee = request.getParameter("annee");
            Personne personne = new Personne(0, nom, prenom, adresse, tel);
            personne.setLogin(login);
            boolean resultat = daoProf.modifierProf(personne);
            if (resultat) {
                profs = daoProf.listerProf(anInscr);
                request.setAttribute("profs", profs);
                rd = request.getRequestDispatcher("surveillant/listeProfs.jsp");
            } else {
                rd = request.getRequestDispatcher("surveillant/modificationProf.jsp");
            }
        } else if (action.equals("profclasse")) {
            String loginProf = request.getParameter("login");
            String annee = request.getParameter("annee");
            Collection<String> nomCl = (Collection<String>) session.getAttribute("nomCl");
            ArrayList<ProfClasse> pc = new ArrayList<ProfClasse>();
            for (String r : nomCl) {
                String[] mats = request.getParameterValues(r);
                String regimes = request.getParameter("regime" + r);

                for (String q : mats) {
                    System.out.println(regimes);
                    System.out.println(annee);
                    pc.add(new ProfClasse(0, loginProf, r, q, annee, regimes));

                }

            }
            if (daoProf.ajouterProfclasse(pc)) {
                String msg = "ajout";
                request.setAttribute("msg", msg);
                rd = request.getRequestDispatcher("surveillant/ajoutProfesseur.jsp");
            } else {
                rd = request.getRequestDispatcher("surveillant/ajoutProfesseur.jsp");
            }
        } else if (action.equals("listerProfs")) {

            profs = daoProf.listerProf(anInscr);
            request.setAttribute("profs", profs);
            rd = request.getRequestDispatcher("surveillant/listeProfs.jsp");

        } else if (action.equals("detailProf")) {
            String loginProf = request.getParameter("loginProf");
            ArrayList<Professeur> detailProf = daoProf.detailProf(loginProf);
            request.setAttribute("detailProf", detailProf);
            rd = request.getRequestDispatcher("surveillant/detailProf.jsp");

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
            request.setAttribute("eleves", eleves);

            rd = request.getRequestDispatcher("surveillant/listeClasse.jsp");
        } else if (action.equals("formAffClass")) {

            rd = request.getRequestDispatcher("surveillant/formAffClasse.jsp");
        } else if (action.equals("modifierProf")) {
            String loginProf = request.getParameter("loginProf");
            professeur = daoProf.listerUnProf(loginProf);
            request.setAttribute("professeur", professeur);
            request.setAttribute("login", loginProf);
            rd = request.getRequestDispatcher("surveillant/modificationProf.jsp");
        } else if (action.equals("formNote")) {

            rd = request.getRequestDispatcher("surveillant/formNote.jsp");

        } else if (action.equals("voirNote")) {
            eleves = null;
            String nomClasse = request.getParameter("nomClasse");
            String nomMatiere = request.getParameter("nomMatiere");
            String semestre = request.getParameter("semestre");
            String annee = request.getParameter("annee");
            String regime = request.getParameter("regime");
            eleves = daoDirecteur.consulterNotes(nomClasse, nomMatiere, semestre, annee, regime);
            if (eleves == null) {
                rd = request.getRequestDispatcher("surveillant/formNote.jsp");
            } else {
                request.setAttribute("semestre", semestre);
                request.setAttribute("annee", annee);
                request.setAttribute("nomClasse", nomClasse);
                request.setAttribute("nomMatiere", nomMatiere);
                request.setAttribute("eleves", eleves);
                rd = request.getRequestDispatcher("surveillant/affichageNote.jsp");
            }

        } else if (action.equals("compte")) {
            String login = request.getParameter("login");
            String nomImgPers = daoProf.compte(login);
            session.setAttribute("nomImgPers", nomImgPers);
            rd = request.getRequestDispatcher("surveillant/compte.jsp");

        } else if (action.equals("listeclasses")) {
            rd = request.getRequestDispatcher("surveillant/classes.jsp");

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

            rd = request.getRequestDispatcher("surveillant/detailsClasse.jsp");

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
            System.out.println("Profils " + profils);
            if ((newpasswd.equals(newpasswd1)) && (oldpasswd.equals(oldpasswd1)) && (profils.equals("surveillant"))) {
                daoDirecteur.changePasswdSurv(login, newpasswd);
                session.setAttribute("motDePasse", newpasswd);
                String msg = "Mot de Passe modifiée avec succés";
                request.setAttribute("mes", msg);
                rd = request.getRequestDispatcher("surveillant/compte.jsp");
            } else {
                String msg = "échec de la modification";
                request.setAttribute("message", msg);
                rd = request.getRequestDispatcher("surveillant/compte.jsp");
            }
        }///////////////////////////////Changer Image Utilisateur/////////////////////
        else if (action.equals("photoProfil")) {
            GenererRef ref = new GenererRef();
            Part partImg1 = request.getPart("nomImage");
            String login = request.getParameter("login");
            String profils = request.getParameter("profils");
            String image1 = "";
            String cheminImg = RepDestinationImg + File.separator;
            image1 = nomFichier(partImg1);
            String chemin = cheminImg + image1;
            int position = chemin.indexOf(".");
            String extension = chemin.substring(position + 1);
            if (extension.equalsIgnoreCase("png")
                    || extension.equalsIgnoreCase("jpg")
                    || extension.equalsIgnoreCase("jpeg")
                    || extension.equalsIgnoreCase("gif")) {
                String reference = ref.genererRef();
                File f = new File(cheminImg + reference + image1);
                //boolean result = true;
                boolean result = daoProf.ajouterImageCompte(login, reference + image1);
                partImg1.write(cheminImg + reference + image1);
                if (result) {
                    String message = "image modifier avec succes";
                    request.setAttribute("msg1", message);
                    rd = request.getRequestDispatcher("surveillant/accueilSurv.jsp");
                }
            } else {
                String message = "erreur extension";
                request.setAttribute("msg", message);
                //session.setAttribute("compte", compte);
                rd = request.getRequestDispatcher("surveillant/compte.jsp");
            }

        } else if (action.equals("suppPhotoProfil")) {
            String login = request.getParameter("login");
            String profils = request.getParameter("profils");
            boolean result = daoProf.supprimerImageCompte(login);
            if (result) {
                String message = "image supprimer avec succes";
                request.setAttribute("msg2", message);
                rd = request.getRequestDispatcher("surveillant/accueilSurv.jsp");
            } else {
                String message = "erreur suppression";
                request.setAttribute("msgSupp", message);
                rd = request.getRequestDispatcher("surveillant/compte.jsp");
            }

        } ////////////////////////////////////////////////////////
        else if (action.equals("absence")) {
            request.setAttribute("anInscr", anInscr);
            rd = request.getRequestDispatcher("surveillant/formAbsence.jsp");

        } else if (action.equals("saveAbsence")) {
            String annee = request.getParameter("annee");
            String login = request.getParameter("login");
            String nomMatiere = request.getParameter("nomMatiere");
            String semestre = request.getParameter("semestre");
            String sujet = request.getParameter("sujet");
            int absence = Integer.parseInt(request.getParameter("absence"));
            daoDirecteur.enregistrerAbsence(annee, login, nomMatiere, semestre, sujet, absence);
            request.setAttribute("anInscr", anInscr);
            rd = request.getRequestDispatcher("surveillant/formAbsence.jsp");

        } else if (action.equals("listeAbsence")) {
            request.setAttribute("anInscr", anInscr);
            rd = request.getRequestDispatcher("surveillant/form-listeAbsence.jsp");

        } else if (action.equals("absents")) {
            String nomClasse = request.getParameter("nomClasse");
            String regime = request.getParameter("regime");
            String semestre = request.getParameter("semestre");
            absences = daoDirecteur.listerAbsence(nomClasse, regime, semestre, anInscr);
            request.setAttribute("anInscr", anInscr);
            request.setAttribute("absences", absences);
            rd = request.getRequestDispatcher("surveillant/listeAbsence.jsp");

        } else if (action.equals("detailsAbsence")) {
            String login = request.getParameter("login");
            for (Absence abs : absences) {
                if (login.equals(abs.getLogin())) {
                    request.setAttribute("matabs", abs.getMatAbs());
                    request.setAttribute("login", login);

                    request.setAttribute("semestre", abs.getSemestre());
                    request.setAttribute("anInscr", anInscr);
                    rd = request.getRequestDispatcher("surveillant/detailsAbsence.jsp");
                }
            }

        } else if (action.equals("modifierAbs")) {
            String semestre = request.getParameter("semestre");
            String login = request.getParameter("login");
            String matiere = request.getParameter("matiere");
            String ret = request.getParameter("ret");
            String abs = request.getParameter("abs");
            request.setAttribute("anInscr", anInscr);
            request.setAttribute("semestre", semestre);
            request.setAttribute("login", login);
            request.setAttribute("ret", ret);
            request.setAttribute("abs", abs);
            request.setAttribute("matiere", matiere);
            rd = request.getRequestDispatcher("surveillant/modificationAbsence.jsp");

        } else if ((action.equals("validModAbs")) || (action.equals("supprimerAbs"))) {
            String semestre = request.getParameter("semestre");
            String login = request.getParameter("login");
            String matiere = request.getParameter("matiere");
            int ret = Integer.parseInt(request.getParameter("ret"));
            int abse = Integer.parseInt(request.getParameter("abs"));
            String annee = request.getParameter("annee");
            daoDirecteur.mofifierAbs(login, matiere, annee, semestre, abse, ret);
            ArrayList<String> clasReg = daoDirecteur.classeRegime(login, annee);
            String nomClasse = null;
            String regime = null;
            Iterator it = clasReg.iterator();
            while (it.hasNext()) {
                String val = it.next().toString();
                if (val.contains(" ")) {
                    nomClasse = val;
                } else {
                    regime = val;
                }

            }
            absences = daoDirecteur.listerAbsence(nomClasse, regime, semestre, anInscr);
            for (Absence abs : absences) {
                if (login.equals(abs.getLogin())) {
                    request.setAttribute("matabs", abs.getMatAbs());
                    request.setAttribute("login", login);
                    request.setAttribute("semestre", abs.getSemestre());
                    request.setAttribute("anInscr", anInscr);
                    rd = request.getRequestDispatcher("surveillant/detailsAbsence.jsp");
                }
            }

        } else if (action.equals("supprimerAbs")) {
            request.setAttribute("anInscr", anInscr);
            rd = request.getRequestDispatcher("surveillant/form-listeAbsence.jsp");

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

    private String nomFichier(Part part) {
        String contenu = part.getHeader("content-disposition");
        String[] items = contenu.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
