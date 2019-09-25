<<<<<<< HEAD
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
import model.Eleve;
import model.GestionParamUser;
import model.ListEleve;
import model.Mensuel;
import model.Parent;
import model.Reclamation;
import model.Utilisateur;
import modelPersonne.DAOEleveImpl;
import modelPersonne.DAOParentImpl;
import modelPersonne.DAOPersonneImpl;
import modelTables.Inscription;
import modelTables.Personne;

/**
 *
 * @author Ouzy NDIAYE
 */
public class Comptable extends HttpServlet {

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
    public ArrayList<String> classePublic;
    public ArrayList<String> classePrivee;
    public ArrayList<String> listAnnee;
    public ArrayList<String> listClasse;
    public ArrayList<String> listMatiere2;
    public ArrayList<String> listAnnee2;
    public ArrayList<String> listClasse2;
    public ArrayList<Utilisateur> compte;
    public ArrayList<Mensuel> listMensuel;
    private DAOEleveImpl daoEleve;
    private DAOParentImpl daoParent;
    public ArrayList<Parent> listParent;
    public ArrayList<model.Eleve> listeEleve;
    public ArrayList<Reclamation> reclamation;
    public ArrayList<model.Eleve> rechercheParElev;
    Random rd1;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.daoPersonne = daoFactory.getDAOPersonne();
        personnes = new ArrayList();
        this.daoEleve = daoFactory.getDAOEleve();
        this.daoParent = daoFactory.getDAOParent();
        eleve2 = new ArrayList();
        classePublic = new ArrayList();
        classePrivee = new ArrayList();
        eleve3 = new ArrayList();
        eleve4 = new ArrayList();
        compte = new ArrayList<>();
        listParent = new ArrayList();
        reclamation = new ArrayList<>();
        rechercheParElev = new ArrayList<>();
        listMensuel = new ArrayList<>();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String req = request.getParameter("connect");
        RequestDispatcher rd = null;
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        ////////////////////////////////////////////////////////
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
        String profil = "Eleve";
        ////////////////////////////////////////////////////////
        if (req == null) {
            rd = request.getRequestDispatcher("Comptable/accueilCompta.jsp");
        } else if (req.equals("inscrireEleve")) {
            classePrivee = daoEleve.listerClassePrivee();
            classePublic = daoEleve.listerClassePublic();
            session.setAttribute("classePrivee", classePrivee);
            session.setAttribute("classePublic", classePublic);
            rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
        } else if (req.equals("valider-inscription")) {
            //Infos Eleves
            String prenom = request.getParameter("prenom");
            String nom = request.getParameter("nom");
            String dateNaiss = request.getParameter("dateNaiss");
            String lieuNaiss = request.getParameter("lieuNaiss");
            String nomClassePub = request.getParameter("nomClassePub");
            String nomClassePriv = request.getParameter("nomClassePriv");
            String regime = request.getParameter("regime");
            String adresse = request.getParameter("adresse");
            String sexe = request.getParameter("sexe");
            String numTelElv = request.getParameter("opeTelElv") + request.getParameter("numTelElv");

            int validerInsc = 0;

            if (request.getParameter("numTelElv").equals("")) {
                numTelElv = "";
            }
            //Infos New Parent
            String prenomPar = request.getParameter("prenomPar");
            String nomPar = request.getParameter("nomPar");
            String numTelPar = request.getParameter("opeTelPar") + request.getParameter("numTelPar");
            String email = request.getParameter("email");
            String profilPar = "Parent";
            //Infos Ancien Parent
            String loginAncienPar = request.getParameter("loginPar");

            ////////////////|||||||||||||TEST|||||||||////////////////////
            if (request.getParameter("email") != null && request.getParameter("email").equals("")) {
                email = "";
            }
            //Inscription
            int reliquat = 0;
            String montantInsc = request.getParameter("montantInsc");

            ///Test erreur
            int dateSaisi = Integer.parseInt(dateNaiss.substring(0, 4));
            int dateSco = Integer.parseInt(annee);
            int dateCal = dateSaisi + 10;

            boolean erreur = false;
            //Erreur sur la date
            if (dateCal > dateSco) {
                String message = "Veuillez revoir la date de naissance saisie!!!";
                request.setAttribute("msgeErreurDate", message);
                erreur = true;
                rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
            }
            //Erreur sur le numéro de Tel Eleve
            if (request.getParameter("numTelElv").length() >= 1 && request.getParameter("numTelElv").length() < 7) {
                String message = "erreur numTel eleve";
                request.setAttribute("msgErreurTelElv", message);
                erreur = true;
                rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
            }

            //Erreur sur le numéro de Tel Parent
            if (request.getParameter("numTelPar") != null && request.getParameter("numTelPar").length() < 7) {
                String message = "erreur numTel Parent";
                request.setAttribute("msgErreurTelPar", message);
                erreur = true;
                rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
            }
            if (regime.equals("")) {
                String message = "erreur regime";
                request.setAttribute("msgErreurRegime", message);
                erreur = true;
                rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
            }
            GestionParamUser gpu = new GestionParamUser();
            //Inscription Ecole Privee
            Eleve eleve = null;
            int verifMontantIns = 0;
            if (nomClassePriv != null) {
                verifMontantIns = daoEleve.verifMontantInscription(nomClassePriv, regime);
            }
            if (nomClassePub != null) {
                verifMontantIns = daoEleve.verifMontantInscription(nomClassePub, regime);
            }
            if ((erreur == false)) {

                String loginElv = gpu.genererLogin(nom, prenom);
                String password = gpu.genererMdp();
                int idInscription = gpu.genererIdInscrip();
                if (verifMontantIns > Integer.parseInt(montantInsc)) {
                    reliquat = verifMontantIns - Integer.parseInt(montantInsc);
                }
                if (verifMontantIns < Integer.parseInt(montantInsc)) {
                    String message = "erreur montant";
                    request.setAttribute("msgErreurMontant", message);
                    rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
                }
                if (verifMontantIns >= Integer.parseInt(montantInsc)) {
                    if (prenomPar != null) {
                        String loginPar = gpu.genererLogin(nomPar, prenomPar);
                        String passwordPar = gpu.genererMdp();
                        Personne p = new Personne(nom, prenom, adresse, numTelElv, loginElv, password, profil, 0);
                        //Fin Requete Ajouter Personne
                        //Ajouter Inscription
                        Inscription inscrip = new Inscription(idInscription, dateToday, 1, Integer.parseInt(montantInsc), reliquat);

                        //Parent
                        Parent parent = new Parent(loginPar, nomPar, prenomPar, numTelPar, passwordPar, profilPar, 0, email);

                        //Eleve
                        if (nomClassePriv != null) {
                            eleve = new Eleve(dateNaiss, lieuNaiss, loginPar, loginElv, nomClassePriv, anInscr, sexe,
                                    validerInsc, regime, idInscription);

                            Mensuel mensuel = new Mensuel(loginElv, anInscr, "0", "", 0, 0);
                            boolean result = daoEleve.inscrireElevePrivee(p, inscrip, mensuel, parent, eleve);
                            if (result == true) {
                                request.setAttribute("loginElv", loginElv);
                                request.setAttribute("passwordElv", password);
                                request.setAttribute("loginPar", loginPar);
                                request.setAttribute("passwordPar", passwordPar);
                                request.setAttribute("prenom", prenom);
                                request.setAttribute("nom", nom);
                                request.setAttribute("sexe", sexe);
                                request.setAttribute("montantIns", montantInsc);
                                request.setAttribute("reliquat", reliquat);
                                rd = request.getRequestDispatcher("Comptable/infoInscription.jsp");
                            } else {
                                String message = "erreur enregistrement";
                                request.setAttribute("msgErreurEnreg", message);
                                rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
                            }

                        }
                        if (nomClassePub != null) {
                            eleve = new Eleve(dateNaiss, lieuNaiss, loginPar, loginElv, nomClassePub, anInscr, sexe,
                                    validerInsc, regime, idInscription);
                            boolean resultat = daoEleve.inscrireEleve(p, inscrip, parent, eleve);
                            if (resultat == true) {
                                request.setAttribute("loginElv", loginElv);
                                request.setAttribute("passwordElv", password);
                                request.setAttribute("loginPar", loginPar);
                                request.setAttribute("passwordPar", passwordPar);
                                request.setAttribute("prenom", prenom);
                                request.setAttribute("nom", nom);
                                request.setAttribute("sexe", sexe);
                                request.setAttribute("montantIns", montantInsc);
                                request.setAttribute("reliquat", reliquat);
                                rd = request.getRequestDispatcher("Comptable/infoInscription.jsp");
                            } else {
                                String message = "erreur enregistrement";
                                request.setAttribute("msgErreurEnreg", message);
                                rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
                            }
                        }
                    }
                    /////////Inscrire Eleve avec Parent existant
                    if (loginAncienPar != null) {
                        String loginAncienPar1 = daoEleve.verifloginParent(loginAncienPar);
                        if (loginAncienPar1.equals(loginAncienPar)) {
                            Personne p = new Personne(nom, prenom, adresse, numTelElv, loginElv, password, profil, 0);
                            //Fin Requete Ajouter Personne
                            //Ajouter Inscription
                            Inscription inscrip = new Inscription(idInscription, dateToday, 1, Integer.parseInt(montantInsc), reliquat);

                            //Parent
                            Parent parent = new Parent(loginAncienPar1);
                            //Eleve
                            if (nomClassePriv != null) {
                                eleve = new Eleve(dateNaiss, lieuNaiss, loginAncienPar1, loginElv, nomClassePriv, anInscr, sexe,
                                        validerInsc, regime, idInscription);
                                Mensuel mensuel = new Mensuel(loginElv, anInscr, "0", "", 0, 0);
                                boolean result = daoEleve.inscrireEleve2Privee(p, inscrip, mensuel, parent, eleve);
                                if (result == true) {
                                    request.setAttribute("loginElv", loginElv);
                                    request.setAttribute("passwordElv", password);
                                    request.setAttribute("prenom", prenom);
                                    request.setAttribute("nom", nom);
                                    request.setAttribute("sexe", sexe);
                                    request.setAttribute("montantIns", montantInsc);
                                    request.setAttribute("reliquat", reliquat);
                                    rd = request.getRequestDispatcher("Comptable/infoInscription.jsp");
                                } else {
                                    String message = "erreur enregistrement";
                                    request.setAttribute("msgErreurEnreg", message);
                                    rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
                                }
                            }
                            if (nomClassePub != null) {
                                eleve = new Eleve(dateNaiss, lieuNaiss, loginAncienPar1, loginElv, nomClassePub, anInscr, sexe,
                                        validerInsc, regime, idInscription);
                                boolean resultat = daoEleve.inscrireEleve2(p, inscrip, parent, eleve);
                                if (resultat == true) {
                                    request.setAttribute("loginElv", loginElv);
                                    request.setAttribute("passwordElv", password);
                                    request.setAttribute("prenom", prenom);
                                    request.setAttribute("nom", nom);
                                    request.setAttribute("sexe", sexe);
                                    request.setAttribute("montantIns", montantInsc);
                                    request.setAttribute("reliquat", reliquat);
                                    rd = request.getRequestDispatcher("Comptable/infoInscription.jsp");
                                } else {
                                    String message = "erreur enregistrement";
                                    request.setAttribute("msgErreurEnreg", message);
                                    rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
                                }
                            }

                        } else {
                            String message = "erreur login Parent";
                            request.setAttribute("msgErreurLogAnsPar", message);
                            rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
                        }

                    }
                }
            }
        } ////Fin Inscription Eleve
        /////////////////////////////////------REINSCRIRE ELEVE ------/////////////////////////////////////////////////
        else if (req.equals("reinscrireEleve")) {
            classePrivee = daoEleve.listerClassePrivee();
            classePublic = daoEleve.listerClassePublic();
            session.setAttribute("classePrivee", classePrivee);
            session.setAttribute("classePublic", classePublic);
            rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
        } else if (req.equals("valider-reinscription")) {
            ArrayList<Eleve> infoElv = new ArrayList();
            GestionParamUser gpu = new GestionParamUser();
            //Inscription
            int reliquat = 0;
            String montantInsc = request.getParameter("montantInsc");
            String loginElv = request.getParameter("loginElv");
            String nomClassePub = request.getParameter("nomClassePub");
            String nomClassePriv = request.getParameter("nomClassePriv");
            String regime = request.getParameter("regime");
            int idInscription = gpu.genererIdInscrip();
            infoElv = daoEleve.infoEleve(loginElv);
            if (infoElv.isEmpty()) {
                String msg = "erreur elv";
                request.setAttribute("msgErreurLoginElv", msg);
                rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
            }
            if (!infoElv.isEmpty()) {
                int verifMontantIns = 0;
                Eleve eleve = null;
                if (nomClassePriv != null) {
                    verifMontantIns = daoEleve.verifMontantInscription(nomClassePriv, regime);
                }
                if (nomClassePub != null) {
                    verifMontantIns = daoEleve.verifMontantInscription(nomClassePub, regime);
                }
                if (verifMontantIns > Integer.parseInt(montantInsc)) {
                    reliquat = verifMontantIns - Integer.parseInt(montantInsc);
                }
                if (verifMontantIns < Integer.parseInt(montantInsc)) {
                    String message = "erreur montant";
                    request.setAttribute("msgErreurMontant", message);
                    rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
                }
                if (regime.equals("")) {
                    String message = "erreur regime";
                    request.setAttribute("msgErreurRegime", message);
                    rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
                }
                if (verifMontantIns >= Integer.parseInt(montantInsc)) {
                    //Fin Requete Ajouter Personne
                    //Ajouter Inscription
                    Inscription inscrip = new Inscription(idInscription, dateToday, 1, Integer.parseInt(montantInsc), reliquat);
                    String prenom = "";
                    String nom = "";
                    for (Eleve elv : infoElv) {
                        //Eleve
                        if (nomClassePriv != null) {
                            eleve = new Eleve(elv.getDateNaissance(), elv.getLieuNaissance(), elv.getLoginParent(),
                                    loginElv, nomClassePriv, anInscr, elv.getSexe(), 0, regime, idInscription);
                            prenom = elv.getPrenom();
                            nom = elv.getNom();
                            Mensuel mensuel = new Mensuel(loginElv, anInscr, "0", "", 0, 0);
                            boolean result = daoEleve.reInscrireElevePrivee(inscrip, mensuel, eleve);
                            if (result == true) {
                                String msg = "reinscription reussit";
                                request.setAttribute("msgReussiReinscrip", msg);
                                request.setAttribute("prenom", prenom);
                                request.setAttribute("nom", nom);
                                request.setAttribute("classePriv", nomClassePriv);
                                request.setAttribute("classePub", nomClassePub);
                                rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
                            } else {
                                String msg = "erreur réinscrip";
                                request.setAttribute("erreurReinscrip", msg);
                                rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
                            }
                        }
                        if (nomClassePub != null) {
                            eleve = new Eleve(elv.getDateNaissance(), elv.getLieuNaissance(), elv.getLoginParent(),
                                    loginElv, nomClassePub, anInscr, elv.getSexe(), 0, regime, idInscription);
                            prenom = elv.getPrenom();
                            nom = elv.getNom();
                            boolean resultat = daoEleve.reInscrireEleve(inscrip, eleve);
                            if (resultat == true) {
                                String msg = "reinscription reussit";
                                request.setAttribute("msgReussiReinscrip", msg);
                                request.setAttribute("prenom", prenom);
                                request.setAttribute("nom", nom);
                                request.setAttribute("classePriv", nomClassePriv);
                                request.setAttribute("classePub", nomClassePub);
                                rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
                            } else {
                                String msg = "erreur réinscrip";
                                request.setAttribute("erreurReinscrip", msg);
                                rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
                            }
                        }
                    }

                }
            }
        } //////////////////////////////////////////////////////////////////////////////
        ////////////////////MENSUALITE/////////////////////////
        else if (req.equals("mensualite")) {
            classePrivee = daoEleve.listerClassePrivee();
            request.setAttribute("classePrivee", classePrivee);
            rd = request.getRequestDispatcher("Comptable/mensualite.jsp");
        } else if (req.equals("choixClasse")) {
            ArrayList<Eleve> eleve = new ArrayList();
            String nomClasse = request.getParameter("nomClasse");
            System.out.println("nomClasse "+nomClasse);
            eleve = daoEleve.listerEleveClasse(nomClasse, anInscr);
            String message = "";
            for (Eleve eleve1 : eleve) {
                System.out.println("eleve "+eleve1.getPrenom());
            }
            if (!eleve.isEmpty()) {
                request.setAttribute("nomClasse", nomClasse);
                request.setAttribute("eleve", eleve);                
                rd = request.getRequestDispatcher("Comptable/mensualite.jsp");
            } else {
                message = "Aucun élève n'est inscrit dans cette classe";
                request.setAttribute("msgClasseVide", message);
                request.setAttribute("eleveVide", eleve);
                rd = request.getRequestDispatcher("Comptable/mensualite.jsp");
            }
            
        } else if (req.equals("payerMensuel")) {
            String login = request.getParameter("login");
            String nomClasse = request.getParameter("nomClasse");
            int montant = daoEleve.verifMensualite(nomClasse);
            listMensuel = daoEleve.listerMensualite(login, anInscr);
            request.setAttribute("listerMois", listMensuel);
            request.setAttribute("montant", montant);
            request.setAttribute("login", login);
            request.setAttribute("nomClasse", nomClasse);
            rd = request.getRequestDispatcher("Comptable/Mensuel.jsp");
        } else if (req.equals("payer")) {
            int montant = Integer.parseInt(request.getParameter("montant"));
            int montantApayer = Integer.parseInt(request.getParameter("montant"));
            String login = request.getParameter("login");
            String nomClasse = request.getParameter("nomClasse");
            String moisMensuel = request.getParameter("mois");
            session.setAttribute("reglement", login);
            session.setAttribute("nomClasse", nomClasse);
            session.setAttribute("montant", montant);
            session.setAttribute("montantApayer", montantApayer);
            session.setAttribute("moisMensuel", moisMensuel);
            request.setAttribute("payer", "payer");
            rd = request.getRequestDispatcher("Comptable/validerMensualite.jsp");
        } else if (req.equals("validerMensuel")) {
            int reliquat = 0;
            int montant = Integer.parseInt(request.getParameter("montant"));
            int montantApayer = Integer.parseInt(request.getParameter("montantApayer"));
            String moisMensuel = request.getParameter("moisMensuel");
            String login = request.getParameter("login");
            if (montant > montantApayer) {
                String msg = "erreur montant";
                request.setAttribute("payer", "payer");
                request.setAttribute("erreurMontantSaisi", msg);
                rd = request.getRequestDispatcher("Comptable/validerMensualite.jsp");
            }

            System.out.println("login " + login);
            Boolean resultat = false;
            if (montant <= montantApayer) {
                reliquat = montantApayer - montant;
                resultat = daoEleve.validerMensualite(login, anInscr, "1", dateToday, moisMensuel, montant, reliquat);
                if (resultat == true) {
                    String msg = "success";
                    request.setAttribute("payementReussit", msg);
                    classePrivee = daoEleve.listerClassePrivee();
                    request.setAttribute("classePrivee", classePrivee);
                    rd = request.getRequestDispatcher("Comptable/mensualite.jsp");
                } else {
                    String msg = "erreur montant";
                    request.setAttribute("payer", "payer");
                    request.setAttribute("erreurPayement", msg);
                    rd = request.getRequestDispatcher("Comptable/validerMensualite.jsp");
                }
            }
        } else if (req.equals("resteApayer")) {
            String login = request.getParameter("login");
            String nomClasse = request.getParameter("nomClasse");
            String mensuel = request.getParameter("mois");
            int montant = daoEleve.verifMontantPayer(login, anInscr, mensuel);
            int reliquat = Integer.parseInt(request.getParameter("reliquat"));

            session.setAttribute("login", login);
            session.setAttribute("montant", montant);
            session.setAttribute("nomClasse", nomClasse);
            session.setAttribute("moisMensuel", mensuel);
            session.setAttribute("montantReliquat", reliquat);
            request.setAttribute("reliquat", "reliquat");
            rd = request.getRequestDispatcher("Comptable/validerMensualite.jsp");
        } else if (req.equals("payerReliquat")) {
            int montantRecu = Integer.parseInt(request.getParameter("montantRecu"));
            int montant = Integer.parseInt(request.getParameter("montant"));
            String login = request.getParameter("login");
            String nomClasse = request.getParameter("nomClasse");
            int montantReliquat = Integer.parseInt(request.getParameter("montantReliquat"));
            String moisMensuel = request.getParameter("moisMensuel");

            if (montantRecu > montantReliquat) {
                String msgErrorReliquat = "erreur";
                request.setAttribute("msgErrorReliquat", msgErrorReliquat);
                request.setAttribute("reliquat", "reliquat");
                rd = request.getRequestDispatcher("Comptable/validerMensualite.jsp");
            }
            Boolean resultat = false;
            if (montantRecu <= montantReliquat) {
                int reliquat = montantReliquat - montantRecu;
                int montantPayer = montant + montantRecu;
                resultat = daoEleve.validerPayementReliquat(login, anInscr, "1", dateToday, moisMensuel, montantPayer, reliquat);
                if (resultat) {
                    String msg = "success";
                    request.setAttribute("payementReussit", msg);                    
                    classePrivee = daoEleve.listerClassePrivee();
                    request.setAttribute("classePrivee", classePrivee);
                    rd = request.getRequestDispatcher("Comptable/mensualite.jsp");
                } else {
                    String msg = "erreur";
                    request.setAttribute("erreurReliquat", msg);
                    request.setAttribute("reliquat", "reliquat");
                    rd = request.getRequestDispatcher("Comptable/validerMensualite.jsp");
                }
            }
        }
        ////Paramètre compte
        else if (req.equals("compte")) {
            String login = request.getParameter("login");
            compte = daoEleve.compteComptable(login);
            for (Utilisateur u : compte) {
                System.out.println("img "+u.getNomImgPers());
            }
            request.setAttribute("compte", compte);
            rd = request.getRequestDispatcher("Comptable/Compte.jsp");
        }else if (req.equals("modifCompte")) {
            String login = request.getParameter("login");
            String ancienMdp = request.getParameter("ancienMdp");
            String nouveauMdp = request.getParameter("nouveauMdp");
            String confirmerMdp = request.getParameter("confirmerMdp");
            if (nouveauMdp.equals(confirmerMdp)) {
                int i = daoEleve.verifMdp(ancienMdp);
                if (i == 0) {
                    daoEleve.modifierCompte(login, nouveauMdp);
                    String mes = "Modification effectuée avec succée";
                    request.setAttribute("message", mes);
                    request.setAttribute("compte", compte);
                    rd = request.getRequestDispatcher("Comptable/Compte.jsp");
                } else {
                    String mes = "L'ancien mot de passe n'est pas conforme";
                    request.setAttribute("message", mes);
                    request.setAttribute("compte", compte);
                    rd = request.getRequestDispatcher("Comptable/Compte.jsp");
                }
            } else {
                String mes = "Les mots de passes ne sont pas conformes";
                request.setAttribute("message", mes);
                request.setAttribute("compte", compte);
                rd = request.getRequestDispatcher("Comptable/Compte.jsp");
            }

        }

        /////////////////////////////////////////////////////////////////////////////////
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
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOFactory;
import model.Eleve;
import model.GestionParamUser;
import model.ListEleve;
import model.Mensuel;
import model.Parent;
import model.Reclamation;
import model.Utilisateur;
import modelPersonne.DAOEleveImpl;
import modelPersonne.DAOParentImpl;
import modelPersonne.DAOPersonneImpl;
import modelTables.Inscription;
import modelTables.Personne;

/**
 *
 * @author Ouzy NDIAYE
 */
public class Comptable extends HttpServlet {

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
    public ArrayList<String> classePublic;
    public ArrayList<String> classePrivee;
    public ArrayList<String> listAnnee;
    public ArrayList<String> listClasse;
    public ArrayList<String> listMatiere2;
    public ArrayList<String> listAnnee2;
    public ArrayList<String> listClasse2;
    public ArrayList<Utilisateur> compte;
    public ArrayList<Mensuel> listMensuel;
    private DAOEleveImpl daoEleve;
    private DAOParentImpl daoParent;
    public ArrayList<Parent> listParent;
    public ArrayList<model.Eleve> listeEleve;
    public ArrayList<Reclamation> reclamation;
    public ArrayList<model.Eleve> rechercheParElev;
    Random rd1;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.daoPersonne = daoFactory.getDAOPersonne();
        personnes = new ArrayList();
        this.daoEleve = daoFactory.getDAOEleve();
        this.daoParent = daoFactory.getDAOParent();
        eleve2 = new ArrayList();
        classePublic = new ArrayList();
        classePrivee = new ArrayList();
        eleve3 = new ArrayList();
        eleve4 = new ArrayList();
        compte = new ArrayList<>();
        listParent = new ArrayList();
        reclamation = new ArrayList<>();
        rechercheParElev = new ArrayList<>();
        listMensuel = new ArrayList<>();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String req = request.getParameter("connect");
        RequestDispatcher rd = null;
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        ////////////////////////////////////////////////////////
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
        String profil = "Eleve";
        ////////////////////////////////////////////////////////
        if (req == null) {
            rd = request.getRequestDispatcher("Comptable/accueilCompta.jsp");
        } else if (req.equals("inscrireEleve")) {
            classePrivee = daoEleve.listerClassePrivee();
            classePublic = daoEleve.listerClassePublic();
            session.setAttribute("classePrivee", classePrivee);
            session.setAttribute("classePublic", classePublic);
            rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
        } else if (req.equals("valider-inscription")) {
            //Infos Eleves
            String prenom = request.getParameter("prenom");
            String nom = request.getParameter("nom");
            String dateNaiss = request.getParameter("dateNaiss");
            String lieuNaiss = request.getParameter("lieuNaiss");
            String nomClassePub = request.getParameter("nomClassePub");
            String nomClassePriv = request.getParameter("nomClassePriv");
            String regime = request.getParameter("regime");
            String adresse = request.getParameter("adresse");
            String sexe = request.getParameter("sexe");
            String numTelElv = request.getParameter("opeTelElv") + request.getParameter("numTelElv");

            int validerInsc = 0;

            if (request.getParameter("numTelElv").equals("")) {
                numTelElv = "";
            }
            //Infos New Parent
            String prenomPar = request.getParameter("prenomPar");
            String nomPar = request.getParameter("nomPar");
            String numTelPar = request.getParameter("opeTelPar") + request.getParameter("numTelPar");
            String email = request.getParameter("email");
            String profilPar = "Parent";
            //Infos Ancien Parent
            String loginAncienPar = request.getParameter("loginPar");

            ////////////////|||||||||||||TEST|||||||||////////////////////
            if (request.getParameter("email") != null && request.getParameter("email").equals("")) {
                email = "";
            }
            //Inscription
            int reliquat = 0;
            String montantInsc = request.getParameter("montantInsc");

            ///Test erreur
            int dateSaisi = Integer.parseInt(dateNaiss.substring(0, 4));
            int dateSco = Integer.parseInt(annee);
            int dateCal = dateSaisi + 10;

            boolean erreur = false;
            //Erreur sur la date
            if (dateCal > dateSco) {
                String message = "Veuillez revoir la date de naissance saisie!!!";
                request.setAttribute("msgeErreurDate", message);
                erreur = true;
                rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
            }
            //Erreur sur le numéro de Tel Eleve
            if (request.getParameter("numTelElv").length() >= 1 && request.getParameter("numTelElv").length() < 7) {
                String message = "erreur numTel eleve";
                request.setAttribute("msgErreurTelElv", message);
                erreur = true;
                rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
            }

            //Erreur sur le numéro de Tel Parent
            if (request.getParameter("numTelPar") != null && request.getParameter("numTelPar").length() < 7) {
                String message = "erreur numTel Parent";
                request.setAttribute("msgErreurTelPar", message);
                erreur = true;
                rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
            }
            if (regime.equals("")) {
                String message = "erreur regime";
                request.setAttribute("msgErreurRegime", message);
                erreur = true;
                rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
            }
            GestionParamUser gpu = new GestionParamUser();
            //Inscription Ecole Privee
            Eleve eleve = null;
            int verifMontantIns = 0;
            if (nomClassePriv != null) {
                verifMontantIns = daoEleve.verifMontantInscription(nomClassePriv, regime);
            }
            if (nomClassePub != null) {
                verifMontantIns = daoEleve.verifMontantInscription(nomClassePub, regime);
            }
            if ((erreur == false)) {

                String loginElv = gpu.genererLogin(nom, prenom);
                String password = gpu.genererMdp();
                String idInscription = gpu.genererIdInscrip();
                if (verifMontantIns > Integer.parseInt(montantInsc)) {
                    reliquat = verifMontantIns - Integer.parseInt(montantInsc);
                }
                if (verifMontantIns < Integer.parseInt(montantInsc)) {
                    String message = "erreur montant";
                    request.setAttribute("msgErreurMontant", message);
                    rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
                }
                if (verifMontantIns >= Integer.parseInt(montantInsc)) {
                    if (prenomPar != null) {
                        String loginPar = gpu.genererLogin(nomPar, prenomPar);
                        String passwordPar = gpu.genererMdp();
                        Personne p = new Personne(nom, prenom, adresse, numTelElv, loginElv, password, profil, 0);
                        //Fin Requete Ajouter Personne
                        //Ajouter Inscription
                        Inscription inscrip = new Inscription(idInscription, dateToday, 1, Integer.parseInt(montantInsc), reliquat);

                        //Parent
                        Parent parent = new Parent(loginPar, nomPar, prenomPar, numTelPar, passwordPar, profilPar, 0, email);

                        //Eleve
                        if (nomClassePriv != null) {
                            eleve = new Eleve(dateNaiss, lieuNaiss, loginPar, loginElv, nomClassePriv, anInscr, sexe,
                                    validerInsc, regime, idInscription);

                            Mensuel mensuel = new Mensuel(loginElv, anInscr, "0", "", 0, 0);
                            boolean result = daoEleve.inscrireElevePrivee(p, inscrip, mensuel, parent, eleve);
                            if (result == true) {
                                request.setAttribute("loginElv", loginElv);
                                request.setAttribute("passwordElv", password);
                                request.setAttribute("loginPar", loginPar);
                                request.setAttribute("passwordPar", passwordPar);
                                request.setAttribute("prenom", prenom);
                                request.setAttribute("nom", nom);
                                request.setAttribute("sexe", sexe);
                                request.setAttribute("montantIns", montantInsc);
                                request.setAttribute("reliquat", reliquat);
                                rd = request.getRequestDispatcher("Comptable/infoInscription.jsp");
                            } else {
                                String message = "erreur enregistrement";
                                request.setAttribute("msgErreurEnreg", message);
                                rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
                            }

                        }
                        if (nomClassePub != null) {
                            eleve = new Eleve(dateNaiss, lieuNaiss, loginPar, loginElv, nomClassePub, anInscr, sexe,
                                    validerInsc, regime, idInscription);
                            boolean resultat = daoEleve.inscrireEleve(p, inscrip, parent, eleve);
                            if (resultat == true) {
                                request.setAttribute("loginElv", loginElv);
                                request.setAttribute("passwordElv", password);
                                request.setAttribute("loginPar", loginPar);
                                request.setAttribute("passwordPar", passwordPar);
                                request.setAttribute("prenom", prenom);
                                request.setAttribute("nom", nom);
                                request.setAttribute("sexe", sexe);
                                request.setAttribute("montantIns", montantInsc);
                                request.setAttribute("reliquat", reliquat);
                                rd = request.getRequestDispatcher("Comptable/infoInscription.jsp");
                            } else {
                                String message = "erreur enregistrement";
                                request.setAttribute("msgErreurEnreg", message);
                                rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
                            }
                        }
                    }
                    /////////Inscrire Eleve avec Parent existant
                    if (loginAncienPar != null) {
                        String loginAncienPar1 = daoEleve.verifloginParent(loginAncienPar);
                        if (loginAncienPar1.equals(loginAncienPar)) {
                            Personne p = new Personne(nom, prenom, adresse, numTelElv, loginElv, password, profil, 0);
                            //Fin Requete Ajouter Personne
                            //Ajouter Inscription
                            Inscription inscrip = new Inscription(idInscription, dateToday, 1, Integer.parseInt(montantInsc), reliquat);

                            //Parent
                            Parent parent = new Parent(loginAncienPar1);
                            //Eleve
                            if (nomClassePriv != null) {
                                eleve = new Eleve(dateNaiss, lieuNaiss, loginAncienPar1, loginElv, nomClassePriv, anInscr, sexe,
                                        validerInsc, regime, idInscription);
                                Mensuel mensuel = new Mensuel(loginElv, anInscr, "0", "", 0, 0);
                                boolean result = daoEleve.inscrireEleve2Privee(p, inscrip, mensuel, parent, eleve);
                                if (result == true) {
                                    request.setAttribute("loginElv", loginElv);
                                    request.setAttribute("passwordElv", password);
                                    request.setAttribute("prenom", prenom);
                                    request.setAttribute("nom", nom);
                                    request.setAttribute("sexe", sexe);
                                    request.setAttribute("montantIns", montantInsc);
                                    request.setAttribute("reliquat", reliquat);
                                    rd = request.getRequestDispatcher("Comptable/infoInscription.jsp");
                                } else {
                                    String message = "erreur enregistrement";
                                    request.setAttribute("msgErreurEnreg", message);
                                    rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
                                }
                            }
                            if (nomClassePub != null) {
                                eleve = new Eleve(dateNaiss, lieuNaiss, loginAncienPar1, loginElv, nomClassePub, anInscr, sexe,
                                        validerInsc, regime, idInscription);
                                boolean resultat = daoEleve.inscrireEleve2(p, inscrip, parent, eleve);
                                if (resultat == true) {
                                    request.setAttribute("loginElv", loginElv);
                                    request.setAttribute("passwordElv", password);
                                    request.setAttribute("prenom", prenom);
                                    request.setAttribute("nom", nom);
                                    request.setAttribute("sexe", sexe);
                                    request.setAttribute("montantIns", montantInsc);
                                    request.setAttribute("reliquat", reliquat);
                                    rd = request.getRequestDispatcher("Comptable/infoInscription.jsp");
                                } else {
                                    String message = "erreur enregistrement";
                                    request.setAttribute("msgErreurEnreg", message);
                                    rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
                                }
                            }

                        } else {
                            String message = "erreur login Parent";
                            request.setAttribute("msgErreurLogAnsPar", message);
                            rd = request.getRequestDispatcher("Comptable/inscrireEleve.jsp");
                        }

                    }
                }
            }
        } ////Fin Inscription Eleve
        /////////////////////////////////------REINSCRIRE ELEVE ------/////////////////////////////////////////////////
        else if (req.equals("reinscrireEleve")) {
            classePrivee = daoEleve.listerClassePrivee();
            classePublic = daoEleve.listerClassePublic();
            session.setAttribute("classePrivee", classePrivee);
            session.setAttribute("classePublic", classePublic);
            rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
        } else if (req.equals("valider-reinscription")) {
            ArrayList<Eleve> infoElv = new ArrayList();
            GestionParamUser gpu = new GestionParamUser();
            //Inscription
            int reliquat = 0;
            String montantInsc = request.getParameter("montantInsc");
            String loginElv = request.getParameter("loginElv");
            String nomClassePub = request.getParameter("nomClassePub");
            String nomClassePriv = request.getParameter("nomClassePriv");
            String regime = request.getParameter("regime");
            String idInscription = gpu.genererIdInscrip();
            infoElv = daoEleve.infoEleve(loginElv);
            if (infoElv.isEmpty()) {
                String msg = "erreur elv";
                request.setAttribute("msgErreurLoginElv", msg);
                rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
            }
            if (!infoElv.isEmpty()) {
                int verifMontantIns = 0;
                Eleve eleve = null;
                if (nomClassePriv != null) {
                    verifMontantIns = daoEleve.verifMontantInscription(nomClassePriv, regime);
                }
                if (nomClassePub != null) {
                    verifMontantIns = daoEleve.verifMontantInscription(nomClassePub, regime);
                }
                if (verifMontantIns > Integer.parseInt(montantInsc)) {
                    reliquat = verifMontantIns - Integer.parseInt(montantInsc);
                }
                if (verifMontantIns < Integer.parseInt(montantInsc)) {
                    String message = "erreur montant";
                    request.setAttribute("msgErreurMontant", message);
                    rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
                }
                if (regime.equals("")) {
                    String message = "erreur regime";
                    request.setAttribute("msgErreurRegime", message);
                    rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
                }
                if (verifMontantIns >= Integer.parseInt(montantInsc)) {
                    //Fin Requete Ajouter Personne
                    //Ajouter Inscription
                    Inscription inscrip = new Inscription(idInscription, dateToday, 1, Integer.parseInt(montantInsc), reliquat);
                    String prenom = "";
                    String nom = "";
                    for (Eleve elv : infoElv) {
                        //Eleve
                        if (nomClassePriv != null) {
                            eleve = new Eleve(elv.getDateNaissance(), elv.getLieuNaissance(), elv.getLoginParent(),
                                    loginElv, nomClassePriv, anInscr, elv.getSexe(), 0, regime, idInscription);
                            prenom = elv.getPrenom();
                            nom = elv.getNom();
                            Mensuel mensuel = new Mensuel(loginElv, anInscr, "0", "", 0, 0);
                            boolean result = daoEleve.reInscrireElevePrivee(inscrip, mensuel, eleve);
                            if (result == true) {
                                String msg = "reinscription reussit";
                                request.setAttribute("msgReussiReinscrip", msg);
                                request.setAttribute("prenom", prenom);
                                request.setAttribute("nom", nom);
                                request.setAttribute("classePriv", nomClassePriv);
                                request.setAttribute("classePub", nomClassePub);
                                rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
                            } else {
                                String msg = "erreur réinscrip";
                                request.setAttribute("erreurReinscrip", msg);
                                rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
                            }
                        }
                        if (nomClassePub != null) {
                            eleve = new Eleve(elv.getDateNaissance(), elv.getLieuNaissance(), elv.getLoginParent(),
                                    loginElv, nomClassePub, anInscr, elv.getSexe(), 0, regime, idInscription);
                            prenom = elv.getPrenom();
                            nom = elv.getNom();
                            boolean resultat = daoEleve.reInscrireEleve(inscrip, eleve);
                            if (resultat == true) {
                                String msg = "reinscription reussit";
                                request.setAttribute("msgReussiReinscrip", msg);
                                request.setAttribute("prenom", prenom);
                                request.setAttribute("nom", nom);
                                request.setAttribute("classePriv", nomClassePriv);
                                request.setAttribute("classePub", nomClassePub);
                                rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
                            } else {
                                String msg = "erreur réinscrip";
                                request.setAttribute("erreurReinscrip", msg);
                                rd = request.getRequestDispatcher("Comptable/reinscrireEleve.jsp");
                            }
                        }
                    }

                }
            }
        } //////////////////////////////////////////////////////////////////////////////
        ////////////////////MENSUALITE/////////////////////////
        else if (req.equals("mensualite")) {
            classePrivee = daoEleve.listerClassePrivee();
            request.setAttribute("classePrivee", classePrivee);
            rd = request.getRequestDispatcher("Comptable/mensualite.jsp");
        } else if (req.equals("choixClasse")) {
            ArrayList<Eleve> eleve = new ArrayList();
            String nomClasse = request.getParameter("nomClasse");
            eleve = daoEleve.listerEleveClasse(nomClasse, anInscr);
            if (!eleve.isEmpty()) {
                request.setAttribute("nomClasse", nomClasse);
                request.setAttribute("eleve", eleve);
            } else {
                request.setAttribute("eleveVide", eleve);
            }
            rd = request.getRequestDispatcher("Comptable/mensualite.jsp");
        } else if (req.equals("payerMensuel")) {
            String login = request.getParameter("login");
            String nomClasse = request.getParameter("nomClasse");
            int montant = daoEleve.verifMensualite(nomClasse);
            listMensuel = daoEleve.listerMensualite(login, anInscr);
            request.setAttribute("listerMois", listMensuel);
            request.setAttribute("montant", montant);
            request.setAttribute("login", login);
            request.setAttribute("nomClasse", nomClasse);
            rd = request.getRequestDispatcher("Comptable/Mensuel.jsp");
        } else if (req.equals("payer")) {
            int montant = Integer.parseInt(request.getParameter("montant"));
            int montantApayer = Integer.parseInt(request.getParameter("montant"));
            String login = request.getParameter("login");
            String nomClasse = request.getParameter("nomClasse");
            String moisMensuel = request.getParameter("mois");
            session.setAttribute("reglement", login);
            session.setAttribute("nomClasse", nomClasse);
            session.setAttribute("montant", montant);
            session.setAttribute("montantApayer", montantApayer);
            session.setAttribute("moisMensuel", moisMensuel);
            request.setAttribute("payer", "payer");
            rd = request.getRequestDispatcher("Comptable/validerMensualite.jsp");
        } else if (req.equals("validerMensuel")) {
            int reliquat = 0;
            int montant = Integer.parseInt(request.getParameter("montant"));
            int montantApayer = Integer.parseInt(request.getParameter("montantApayer"));
            String moisMensuel = request.getParameter("moisMensuel");
            String login = request.getParameter("login");
            if (montant > montantApayer) {
                String msg = "erreur montant";
                request.setAttribute("payer", "payer");
                request.setAttribute("erreurMontantSaisi", msg);
                rd = request.getRequestDispatcher("Comptable/validerMensualite.jsp");
            }

            System.out.println("login " + login);
            Boolean resultat = false;
            if (montant <= montantApayer) {
                reliquat = montantApayer - montant;
                resultat = daoEleve.validerMensualite(login, anInscr, "1", dateToday, moisMensuel, montant, reliquat);
                if (resultat == true) {
                    String msg = "success";
                    request.setAttribute("payementReussit", msg);
                    classePrivee = daoEleve.listerClassePrivee();
                    request.setAttribute("classePrivee", classePrivee);
                    rd = request.getRequestDispatcher("Comptable/mensualite.jsp");
                } else {
                    String msg = "erreur montant";
                    request.setAttribute("payer", "payer");
                    request.setAttribute("erreurPayement", msg);
                    rd = request.getRequestDispatcher("Comptable/validerMensualite.jsp");
                }
            }
        } else if (req.equals("resteApayer")) {
            String login = request.getParameter("login");
            String nomClasse = request.getParameter("nomClasse");
            String mensuel = request.getParameter("mois");
            int montant = daoEleve.verifMontantPayer(login, anInscr, mensuel);
            int reliquat = Integer.parseInt(request.getParameter("reliquat"));

            session.setAttribute("login", login);
            session.setAttribute("montant", montant);
            session.setAttribute("nomClasse", nomClasse);
            session.setAttribute("moisMensuel", mensuel);
            session.setAttribute("montantReliquat", reliquat);
            request.setAttribute("reliquat", "reliquat");
            rd = request.getRequestDispatcher("Comptable/validerMensualite.jsp");
        } else if (req.equals("payerReliquat")) {
            int montantRecu = Integer.parseInt(request.getParameter("montantRecu"));
            int montant = Integer.parseInt(request.getParameter("montant"));
            String login = request.getParameter("login");
            String nomClasse = request.getParameter("nomClasse");
            int montantReliquat = Integer.parseInt(request.getParameter("montantReliquat"));
            String moisMensuel = request.getParameter("moisMensuel");

            if (montantRecu > montantReliquat) {
                String msgErrorReliquat = "erreur";
                request.setAttribute("msgErrorReliquat", msgErrorReliquat);
                request.setAttribute("reliquat", "reliquat");
                rd = request.getRequestDispatcher("Comptable/validerMensualite.jsp");
            }
            Boolean resultat = false;
            if (montantRecu <= montantReliquat) {
                int reliquat = montantReliquat - montantRecu;
                int montantPayer = montant + montantRecu;
                resultat = daoEleve.validerPayementReliquat(login, anInscr, "1", dateToday, moisMensuel, montantPayer, reliquat);
                if (resultat) {
                    String msg = "success";
                    request.setAttribute("payementReussit", msg);                    
                    classePrivee = daoEleve.listerClassePrivee();
                    request.setAttribute("classePrivee", classePrivee);
                    rd = request.getRequestDispatcher("Comptable/mensualite.jsp");
                } else {
                    String msg = "erreur";
                    request.setAttribute("erreurReliquat", msg);
                    request.setAttribute("reliquat", "reliquat");
                    rd = request.getRequestDispatcher("Comptable/validerMensualite.jsp");
                }
            }
        }

        /////////////////////////////////////////////////////////////////////////////////
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
