/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelPersonne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.DAOFactory;
import model.Eleve;
import model.EleveRecherche;
import model.GestionDate;
import model.GestionParamUser;
import model.ListEleve;
import model.Mensuel;
import model.Parent;
import model.Prof;
import model.Reclamation;
import model.Utilisateur;
import modelTables.Inscription;
import modelTables.Personne;

/**
 *
 * @author ibrah
 */
public class DAOEleveImpl {

    private DAOFactory daoFactory;
    public Eleve eleve = new Eleve();
    public Personne personne = new Personne();
    public Mensuel mensuel = new Mensuel();
    public ListEleve eleve1 = new ListEleve();

    public DAOEleveImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    Connection con;

    public String verifloginParent(String loginAncienPar) {
        String loginPar = "";
        Connection con;
        Statement st;
        Prof pro = new Prof();
        try {
            con = daoFactory.getConnection();
            String requete = "select Par_login from eleve where Par_login='" + loginAncienPar + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                loginPar = rs.getString("Par_login");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return loginPar;
    }

    public int verifMontantInscription(String nomClasse, String regime) {
        int reliquat = 0;
        Connection con;
        Statement st;
        Prof pro = new Prof();
        try {
            con = daoFactory.getConnection();
            String requete = "select inscription from fichederenseignement where nomClasse='" + nomClasse + "'" + " and regime='" + regime + "' ";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                reliquat = rs.getInt("inscription");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return reliquat;
    }

    public int verifMensualite(String nomClasse) {
        int reliquat = 0;
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select mensualite from fichederenseignement where nomClasse='" + nomClasse + "' and regime='Privee'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                reliquat = rs.getInt("mensualite");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return reliquat;
    }
    //

    //Inscription d'un éleve dont le parent n'est pas dans la base
    public boolean inscrireEleve(Personne pers, Inscription ins, Parent par, Eleve elv) {
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst1;
            PreparedStatement pst2;
            PreparedStatement pst3;
            PreparedStatement pst4;

            String requete1 = "insert into personne (login,prenom,nom,adresse,telephone,motDePasse,etatPers,profil)"
                    + " values(?,?,?,?,?,?,?,?)";
            pst1 = con.prepareStatement(requete1);
            pst1.setString(1, pers.getLogin());
            pst1.setString(2, pers.getPrenom());
            pst1.setString(3, pers.getNom());
            pst1.setString(4, pers.getAdresse());
            pst1.setString(5, pers.getTel());
            pst1.setString(6, pers.getMotDePasse());
            pst1.setInt(7, pers.getEtatPers());
            pst1.setString(8, pers.getProfils());
            int result1 = pst1.executeUpdate();

            if (result1 > 0) {
                System.out.println("Enregistrement réussit de la table Personne");
                String requete2 = "insert into personne (login,prenom,nom,adresse,telephone,motDePasse,etatPers,profil)"
                        + " values(?,?,?,?,?,?,?,?)";
                pst2 = con.prepareStatement(requete2);
                pst2.setString(1, par.getLoginParent());
                pst2.setString(2, par.getPrenom());
                pst2.setString(3, par.getNom());
                pst2.setString(4, pers.getAdresse());
                pst2.setString(5, par.getTel());
                pst2.setString(6, par.getMotDePasse());
                pst2.setInt(7, par.getEtatPers());
                pst2.setString(8, par.getProfil());
                int result2 = pst2.executeUpdate();
                if (result2 > 0) {
                    System.out.println("Parent ajouter avec success");
                    String requete3 = "insert into parent (login,email) values (?,?)";
                    pst3 = con.prepareStatement(requete3);
                    pst3.setString(1, par.getLoginParent());
                    pst3.setString(2, par.getEmail());
                    int result3 = pst3.executeUpdate();
                    if (result3 > 0) {
                        System.out.println("Enregistrement réussit table Parent");
                        String requete4 = "insert into inscription (idInscription,dateInscription,statutInscription,montant,reliquat)"
                                + " values(?,?,?,?,?)";
                        pst4 = con.prepareStatement(requete4);
                        pst4.setInt(1, Integer.parseInt(ins.getIdInscription()));
                        pst4.setString(2, ins.getDateInscription());
                        pst4.setInt(3, ins.getStatus());
                        pst4.setInt(4, ins.getMontant());
                        pst4.setInt(5, ins.getReliquat());
                        int result4 = pst4.executeUpdate();
                        if (result4 > 0) {
                            String requete5 = "insert into eleve (login,anneeScolaire,idInscription,nomClasse,"
                                    + "regime,Par_login,dateNaissance,lieuNaissance,sexe,validerInscrip)"
                                    + " values (?,?,?,?,?,?,?,?,?,?)";
                            pst4 = con.prepareStatement(requete5);
                            pst4.setString(1, pers.getLogin());
                            pst4.setString(2, elv.getAnnee());
                            pst4.setInt(3, Integer.parseInt(ins.getIdInscription()));
                            pst4.setString(4, elv.getClasse());
                            pst4.setString(5, elv.getRegime());
                            pst4.setString(6, par.getLoginParent());
                            pst4.setString(7, elv.getDateNaissance());
                            pst4.setString(8, elv.getLieuNaissance());
                            pst4.setString(9, elv.getSexe());
                            pst4.setInt(10, elv.getValiderIns());
                            int result5 = pst4.executeUpdate();
                            if (result5 > 0) {
                                System.out.println("Enregistrement avec succes de la table Eleve");
                            } else {
                                System.out.println("Erreur d'enregistrement de la table Eleve");
                            }
                            resultat = true;
                        }
                    } else {
                        System.out.println("Erreur enregistrement table Parent");
                    }

                } else {
                    System.out.println("Erreur d'ajout du parent");
                }
            } else {
                resultat = false;
                System.out.println("Erreur d'enregistrement table Personne");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;
    }

    public boolean inscrireElevePrivee(Personne pers, Inscription ins, Mensuel m, Parent par, Eleve elv) {
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst1;
            PreparedStatement pst2;
            PreparedStatement pst3;
            PreparedStatement pst4;

            String requete1 = "insert into personne (login,prenom,nom,adresse,telephone,motDePasse,etatPers,profil)"
                    + " values(?,?,?,?,?,?,?,?)";
            pst1 = con.prepareStatement(requete1);
            pst1.setString(1, pers.getLogin());
            pst1.setString(2, pers.getPrenom());
            pst1.setString(3, pers.getNom());
            pst1.setString(4, pers.getAdresse());
            pst1.setString(5, pers.getTel());
            pst1.setString(6, pers.getMotDePasse());
            pst1.setInt(7, pers.getEtatPers());
            pst1.setString(8, pers.getProfils());
            int result1 = pst1.executeUpdate();

            if (result1 > 0) {
                System.out.println("Enregistrement réussit de la table Personne");
                String requete2 = "insert into personne (login,prenom,nom,adresse,telephone,motDePasse,etatPers,profil)"
                        + " values(?,?,?,?,?,?,?,?)";
                pst2 = con.prepareStatement(requete2);
                pst2.setString(1, par.getLoginParent());
                pst2.setString(2, par.getPrenom());
                pst2.setString(3, par.getNom());
                pst2.setString(4, pers.getAdresse());
                pst2.setString(5, par.getTel());
                pst2.setString(6, par.getMotDePasse());
                pst2.setInt(7, par.getEtatPers());
                pst2.setString(8, par.getProfil());
                int result2 = pst2.executeUpdate();
                if (result2 > 0) {
                    System.out.println("Parent ajouter avec success");
                    String requete3 = "insert into parent (login,email) values (?,?)";
                    pst3 = con.prepareStatement(requete3);
                    pst3.setString(1, par.getLoginParent());
                    pst3.setString(2, par.getEmail());
                    int result3 = pst3.executeUpdate();
                    if (result3 > 0) {
                        System.out.println("Enregistrement réussit table Parent");
                        String requete4 = "insert into inscription (idInscription,dateInscription,statutInscription,montant,reliquat)"
                                + " values(?,?,?,?,?)";
                        pst4 = con.prepareStatement(requete4);
                        pst4.setInt(1, Integer.parseInt(ins.getIdInscription()));
                        pst4.setString(2, ins.getDateInscription());
                        pst4.setInt(3, ins.getStatus());
                        pst4.setInt(4, ins.getMontant());
                        pst4.setInt(5, ins.getReliquat());
                        int result4 = pst4.executeUpdate();
                        if (result4 > 0) {
                            String requete5 = "insert into eleve (login,anneeScolaire,idInscription,nomClasse,"
                                    + "regime,Par_login,dateNaissance,lieuNaissance,sexe,validerInscrip)"
                                    + " values (?,?,?,?,?,?,?,?,?,?)";
                            pst4 = con.prepareStatement(requete5);
                            pst4.setString(1, pers.getLogin());
                            pst4.setString(2, elv.getAnnee());
                            pst4.setInt(3, Integer.parseInt(ins.getIdInscription()));
                            pst4.setString(4, elv.getClasse());
                            pst4.setString(5, elv.getRegime());
                            pst4.setString(6, par.getLoginParent());
                            pst4.setString(7, elv.getDateNaissance());
                            pst4.setString(8, elv.getLieuNaissance());
                            pst4.setString(9, elv.getSexe());
                            pst4.setInt(10, elv.getValiderIns());
                            int result5 = pst4.executeUpdate();
                            if (result5 > 0) {
                                GestionParamUser gpu = new GestionParamUser();
                                System.out.println("Enregistrement avec succes de la table Eleve");
                                gpu.listerMois();
                                for (Mensuel listerMoi : gpu.listerMois()) {
                                    String requete6 = "insert into mensuel (login,anneeScolaire,statutMensuel,dateMensuel,mois,"
                                            + "montant,reliquat) values (?,?,?,?,?,?,?)";
                                    pst4 = con.prepareStatement(requete6);
                                    pst4.setString(1, pers.getLogin());
                                    pst4.setString(2, elv.getAnnee());
                                    pst4.setString(3, m.getStatutMensuel());
                                    pst4.setString(4, m.getDateMensuel());
                                    pst4.setString(5, listerMoi.getMois());
                                    pst4.setInt(6, m.getMontant());
                                    pst4.setInt(7, m.getReliquat());
                                    int result6 = pst4.executeUpdate();
                                    if (result6 > 0) {
                                        System.out.println("Enregistrement avec success de la table mensuel");
                                    } else {
                                        System.out.println("Erreur d'enregistrement de la table mensuel");
                                    }
                                }
                            } else {
                                System.out.println("Erreur d'enregistrement de la table Eleve");
                            }

                        }
                    } else {
                        System.out.println("Erreur enregistrement table Parent");
                    }

                } else {
                    System.out.println("Erreur d'ajout du parent");
                }
                resultat = true;
            } else {
                resultat = false;
                System.out.println("Erreur d'enregistrement table Personne");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;
    }

    //Inscription d'un éleve dont le pant est dans la base
    public boolean inscrireEleve2(Personne pers, Inscription ins, Parent par, Eleve elv) {
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst1;
            PreparedStatement pst4;

            String requete1 = "insert into personne (login,prenom,nom,adresse,telephone,motDePasse,etatPers,profil)"
                    + " values(?,?,?,?,?,?,?,?)";
            pst1 = con.prepareStatement(requete1);
            pst1.setString(1, pers.getLogin());
            pst1.setString(2, pers.getPrenom());
            pst1.setString(3, pers.getNom());
            pst1.setString(4, pers.getAdresse());
            pst1.setString(5, pers.getTel());
            pst1.setString(6, pers.getMotDePasse());
            pst1.setInt(7, pers.getEtatPers());
            pst1.setString(8, pers.getProfils());
            int result1 = pst1.executeUpdate();

            if (result1 > 0) {
                System.out.println("Enregistrement réussit de la table Personne");

                String requete4 = "insert into inscription (idInscription,dateInscription,statutInscription,montant,reliquat)"
                        + " values(?,?,?,?,?)";
                pst4 = con.prepareStatement(requete4);
                pst4.setInt(1, Integer.parseInt(ins.getIdInscription()));
                pst4.setString(2, ins.getDateInscription());
                pst4.setInt(3, ins.getStatus());
                pst4.setInt(4, ins.getMontant());
                pst4.setInt(5, ins.getReliquat());
                int result4 = pst4.executeUpdate();
                if (result4 > 0) {
                    String requete5 = "insert into eleve (login,anneeScolaire,idInscription,nomClasse,"
                            + "regime,Par_login,dateNaissance,lieuNaissance,sexe,validerInscrip)"
                            + " values (?,?,?,?,?,?,?,?,?,?)";
                    pst4 = con.prepareStatement(requete5);
                    pst4.setString(1, pers.getLogin());
                    pst4.setString(2, elv.getAnnee());
                    pst4.setInt(3, Integer.parseInt(ins.getIdInscription()));
                    pst4.setString(4, elv.getClasse());
                    pst4.setString(5, elv.getRegime());
                    pst4.setString(6, par.getLoginParent());
                    pst4.setString(7, elv.getDateNaissance());
                    pst4.setString(8, elv.getLieuNaissance());
                    pst4.setString(9, elv.getSexe());
                    pst4.setInt(10, elv.getValiderIns());
                    int result5 = pst4.executeUpdate();
                    if (result5 > 0) {
                        System.out.println("Enregistrement avec succes de la table Eleve");
                    } else {
                        System.out.println("Erreur d'enregistrement de la table Eleve");
                    }
                    resultat = true;
                }
            } else {
                resultat = false;
                System.out.println("Erreur d'enregistrement table Personne");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;
    }

    public boolean inscrireEleve2Privee(Personne pers, Inscription ins, Mensuel m, Parent par, Eleve elv) {
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst1;
            PreparedStatement pst4;

            String requete1 = "insert into personne (login,prenom,nom,adresse,telephone,motDePasse,etatPers,profil)"
                    + " values(?,?,?,?,?,?,?,?)";
            pst1 = con.prepareStatement(requete1);
            pst1.setString(1, pers.getLogin());
            pst1.setString(2, pers.getPrenom());
            pst1.setString(3, pers.getNom());
            pst1.setString(4, pers.getAdresse());
            pst1.setString(5, pers.getTel());
            pst1.setString(6, pers.getMotDePasse());
            pst1.setInt(7, pers.getEtatPers());
            pst1.setString(8, pers.getProfils());
            int result1 = pst1.executeUpdate();

            if (result1 > 0) {
                System.out.println("Enregistrement réussit de la table Personne");

                String requete4 = "insert into inscription (idInscription,dateInscription,statutInscription,montant,reliquat)"
                        + " values(?,?,?,?,?)";
                pst4 = con.prepareStatement(requete4);
                pst4.setInt(1, Integer.parseInt(ins.getIdInscription()));
                pst4.setString(2, ins.getDateInscription());
                pst4.setInt(3, ins.getStatus());
                pst4.setInt(4, ins.getMontant());
                pst4.setInt(5, ins.getReliquat());
                int result4 = pst4.executeUpdate();
                if (result4 > 0) {
                    String requete5 = "insert into eleve (login,anneeScolaire,idInscription,nomClasse,"
                            + "regime,Par_login,dateNaissance,lieuNaissance,sexe,validerInscrip)"
                            + " values (?,?,?,?,?,?,?,?,?,?)";
                    pst4 = con.prepareStatement(requete5);
                    pst4.setString(1, pers.getLogin());
                    pst4.setString(2, elv.getAnnee());
                    pst4.setInt(3, Integer.parseInt(ins.getIdInscription()));
                    pst4.setString(4, elv.getClasse());
                    pst4.setString(5, elv.getRegime());
                    pst4.setString(6, par.getLoginParent());
                    pst4.setString(7, elv.getDateNaissance());
                    pst4.setString(8, elv.getLieuNaissance());
                    pst4.setString(9, elv.getSexe());
                    pst4.setInt(10, elv.getValiderIns());
                    int result5 = pst4.executeUpdate();
                    if (result5 > 0) {
                        GestionParamUser gpu = new GestionParamUser();
                        System.out.println("Enregistrement avec succes de la table Eleve");
                        gpu.listerMois();
                        for (Mensuel listerMoi : gpu.listerMois()) {
                            String requete6 = "insert into mensuel (login,anneeScolaire,statutMensuel,dateMensuel,mois,"
                                    + "montant,reliquat) values (?,?,?,?,?,?,?)";
                            pst4 = con.prepareStatement(requete6);
                            pst4.setString(1, pers.getLogin());
                            pst4.setString(2, elv.getAnnee());
                            pst4.setString(3, m.getStatutMensuel());
                            pst4.setString(4, m.getDateMensuel());
                            pst4.setString(5, listerMoi.getMois());
                            pst4.setInt(6, m.getMontant());
                            pst4.setInt(7, m.getReliquat());
                            int result6 = pst4.executeUpdate();
                            if (result6 > 0) {
                                System.out.println("Enregistrement avec success de la table mensuel");
                            } else {
                                System.out.println("Erreur d'enregistrement de la table mensuel");
                            }
                        }
                    } else {
                        System.out.println("Erreur d'enregistrement de la table Eleve");
                    }
                    resultat = true;
                }
            } else {
                resultat = false;
                System.out.println("Erreur d'enregistrement table Personne");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;
    }

    /////
    public boolean reInscrireElevePrivee(Inscription ins, Mensuel m, Eleve elv) {
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst4;
            PreparedStatement pst5;
            String requete4 = "insert into inscription (idInscription,dateInscription,statutInscription,montant,reliquat)"
                    + " values(?,?,?,?,?)";
            pst5 = con.prepareStatement(requete4);
            pst5.setInt(1, Integer.parseInt(ins.getIdInscription()));
            pst5.setString(2, ins.getDateInscription());
            pst5.setInt(3, ins.getStatus());
            pst5.setInt(4, ins.getMontant());
            pst5.setInt(5, ins.getReliquat());
            int result4 = pst5.executeUpdate();
            if (result4 > 0) {
                String requete5 = "insert into eleve (login,anneeScolaire,idInscription,nomClasse,"
                        + "regime,Par_login,dateNaissance,lieuNaissance,sexe,validerInscrip)"
                        + " values (?,?,?,?,?,?,?,?,?,?)";
                pst4 = con.prepareStatement(requete5);
                pst4.setString(1, elv.getLogin());
                pst4.setString(2, elv.getAnnee());
                pst4.setInt(3, Integer.parseInt(ins.getIdInscription()));
                pst4.setString(4, elv.getClasse());
                pst4.setString(5, elv.getRegime());
                pst4.setString(6, elv.getLoginParent());
                pst4.setString(7, elv.getDateNaissance());
                pst4.setString(8, elv.getLieuNaissance());
                pst4.setString(9, elv.getSexe());
                pst4.setInt(10, elv.getValiderIns());
                int result5 = pst4.executeUpdate();
                if (result5 > 0) {
                    GestionParamUser gpu = new GestionParamUser();
                    System.out.println("Enregistrement avec succes de la table Eleve");
                    gpu.listerMois();
                    for (Mensuel listerMoi : gpu.listerMois()) {
                        String requete6 = "insert into mensuel (login,anneeScolaire,statutMensuel,dateMensuel,mois,"
                                + "montant,reliquat) values (?,?,?,?,?,?,?)";
                        pst4 = con.prepareStatement(requete6);
                        pst4.setString(1, elv.getLogin());
                        pst4.setString(2, elv.getAnnee());
                        pst4.setString(3, m.getStatutMensuel());
                        pst4.setString(4, m.getDateMensuel());
                        pst4.setString(5, listerMoi.getMois());
                        pst4.setInt(6, m.getMontant());
                        pst4.setInt(7, m.getReliquat());
                        int result6 = pst4.executeUpdate();
                        if (result6 > 0) {
                            System.out.println("Enregistrement avec success de la table mensuel");
                        } else {
                            System.out.println("Erreur d'enregistrement de la table mensuel");
                        }
                    }
                } else {
                    System.out.println("Erreur d'enregistrement de la table Eleve");
                }
                resultat = true;
            } else {
                resultat = false;
                System.out.println("Erreur");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;
    }

    public boolean reInscrireEleve(Inscription ins, Eleve elv) {
        boolean resultat = false;
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst4;
            PreparedStatement pst5;
            String requete4 = "insert into inscription (idInscription,dateInscription,statutInscription,montant,reliquat)"
                    + " values(?,?,?,?,?)";
            pst5 = con.prepareStatement(requete4);
            pst5.setInt(1, Integer.parseInt(ins.getIdInscription()));
            pst5.setString(2, ins.getDateInscription());
            pst5.setInt(3, ins.getStatus());
            pst5.setInt(4, ins.getMontant());
            pst5.setInt(5, ins.getReliquat());
            int result4 = pst5.executeUpdate();
            if (result4 > 0) {
                String requete5 = "insert into eleve (login,anneeScolaire,idInscription,nomClasse,"
                        + "regime,Par_login,dateNaissance,lieuNaissance,sexe,validerInscrip)"
                        + " values (?,?,?,?,?,?,?,?,?,?)";
                pst4 = con.prepareStatement(requete5);
                pst4.setString(1, elv.getLogin());
                pst4.setString(2, elv.getAnnee());
                pst4.setInt(3, Integer.parseInt(ins.getIdInscription()));
                pst4.setString(4, elv.getClasse());
                pst4.setString(5, elv.getRegime());
                pst4.setString(6, elv.getLoginParent());
                pst4.setString(7, elv.getDateNaissance());
                pst4.setString(8, elv.getLieuNaissance());
                pst4.setString(9, elv.getSexe());
                pst4.setInt(10, elv.getValiderIns());
                int result5 = pst4.executeUpdate();
                if (result5 > 0) {
                    resultat = true;
                    System.out.println("Enregistrement avec succes de la table Eleve");
                } else {
                    System.out.println("Erreur d'enregistrement de la table Eleve");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;
    }

    public ArrayList<Eleve> infoEleve(String login) {
        ArrayList<Eleve> infoElv = new ArrayList();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select distinct prenom,nom,Par_login,dateNaissance,lieuNaissance,sexe FROM eleve elv,personne pers"
                    + " where elv.login=pers.login and elv.login='" + login + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                eleve = new Eleve();
                eleve.setNom(rs.getString("nom"));
                eleve.setPrenom(rs.getString("prenom"));
                eleve.setLoginParent(rs.getString("Par_login"));
                eleve.setDateNaissance(rs.getString("dateNaissance"));
                eleve.setLieuNaissance(rs.getString("lieuNaissance"));
                eleve.setSexe(rs.getString("sexe"));
                infoElv.add(eleve);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return infoElv;
    }

    public ArrayList<String> listerClassePublic() {

        ArrayList<String> classes = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomClasse from classe where regime='Public' order by nomClasse";

            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                classes.add(rs.getString("nomClasse"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return classes;
    }

    public ArrayList<String> listerClassePrivee() {
        ArrayList<String> classes = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomClasse from classe where regime='Privee' order by nomClasse";

            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                classes.add(rs.getString("nomClasse"));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return classes;
    }

    public ArrayList<Eleve> listerClasse() {
        ArrayList<Eleve> classes = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomClasse,regime from classe order by nomClasse";

            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                eleve = new Eleve();
                eleve.setNomClasse(rs.getString("nomClasse"));
                eleve.setRegime(rs.getString("regime"));
                classes.add(eleve);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return classes;
    }

    public ArrayList<Eleve> listerEleveClasse(String classe, String annee) {
        ArrayList<Eleve> listEleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select distinct elv.login,prenom,nom,dateNaissance,lieuNaissance FROM eleve elv,personne pers where elv.login=pers.login and nomClasse='" + classe + "' and anneeScolaire='" + annee + "' and regime='Privee' order by nom";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                eleve = new Eleve();
                eleve.setNom(rs.getString("nom"));
                eleve.setPrenom(rs.getString("prenom"));
                eleve.setDateNaissance(rs.getString("dateNaissance"));
                eleve.setLieuNaissance(rs.getString("lieuNaissance"));
                eleve.setLogin(rs.getString("login"));
                listEleve.add(eleve);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listEleve;
    }

    public ArrayList<Mensuel> listerMensualite(String login, String annee) {
        ArrayList<Mensuel> listMois = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select mois,statutMensuel FROM mensuel WHERE login='" + login + "' and anneeScolaire='" + annee + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                mensuel = new Mensuel();
                mensuel.setMois(rs.getString("mois"));
                mensuel.setStatutMensuel(rs.getString("statutMensuel"));
                listMois.add(mensuel);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listMois;
    }

    public void validerMensualite(String login, String anneeScolaire, String statutMensuel, String dateMensuel,
            String mois, int montant, int reliquat) {
        Connection con;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
            String requete = "update mensuel set statutMensuel=?, dateMensuel=?,montant=?,reliquat=? where login=? and anneeScolaire=? and mois=?";
            pst = con.prepareStatement(requete);
            pst.setString(1, statutMensuel);
            pst.setString(2, dateMensuel);
            pst.setInt(3, montant);
            pst.setInt(4, reliquat);
            pst.setString(5, login);
            pst.setString(6, anneeScolaire);
            pst.setString(7, mois);
            int rs = pst.executeUpdate();
            if (rs > 0) {
                System.out.println("la requete est bien exécutée");
            } else {
                System.out.println("Erreur d'exécution");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> listerMatiereClasse(String classe, String regime) {
        ArrayList<String> listEleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomMatiere FROM classeMatiere where nomClasse='" + classe + "' and regime='" + regime + "'order by nomMatiere";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                listEleve.add(rs.getString("nomMatiere"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listEleve;
    }

    public ArrayList<Personne> listerProfClasse(String classe, String regime, String annee) {
        ArrayList<Personne> listEleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select login FROM profClasse where nomClasse='" + classe + "' and regime='" + regime + "' and anneeScolaire='" + annee + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                String requete2 = "select nom from personne where login='" + rs.getString("login") + "'";
                st = con.createStatement();
                ResultSet rs1 = st.executeQuery(requete2);
                while (rs1.next()) {
                    personne = new Personne();
                    personne.setLogin(rs.getString("login"));
                    personne.setNom(rs1.getString("nom"));
                    listEleve.add(personne);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listEleve;
    }

    public ArrayList<String> listerSalleClasse() {
        ArrayList<String> listEleve = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomSalle FROM salle order by nomSalle";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                listEleve.add(rs.getString("nomSalle"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listEleve;
    }

    public boolean ajouterCours(String login, String nomMatiere, String regime, String nomClasse, String nomSalle,
            String jour, String heure) {
        boolean resultat = false;
        Connection con;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
            String requete = "insert into emploidutemps (login,nomMatiere,regime,nomClasse,nomSalle,jour,heureDeb) values (?,?,?,?,?,?,?)";
            pst = con.prepareStatement(requete);
            pst.setString(1, login);
            pst.setString(2, nomMatiere);
            pst.setString(3, regime);
            pst.setString(4, nomClasse);
            pst.setString(5, nomSalle);
            pst.setString(6, jour);
            pst.setString(7, heure);
            int rs = pst.executeUpdate();
            if (rs > 0) {
                resultat = true;
                System.out.println("la requete est bien exécutée");
            } else {
                System.out.println("Erreur d'exécution");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;
    }

    public String[][] afficherEDT(String classe, String regime) {
        String[][] listEleve = new String[4][5];
        GestionDate gd = new GestionDate();
        int l = 0;
        int c = 0;
        int cpt = 0;
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select * FROM emploidutemps where nomClasse='" + classe + "' and regime='" + regime + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                String requete2 = "select nom from personne where login ='" + rs.getString("login") + "'";
                st = con.createStatement();
                ResultSet rs1 = st.executeQuery(requete2);
                while (rs1.next()) {
                    c = gd.numeroJour(rs.getString("jour")); //Colonne
                    l = gd.numeroHeure(rs.getString("heureDeb")); //Ligne
                    listEleve[l][c] = rs.getString("nomMatiere") + "//---//" + rs1.getString("nom") + "//---//" + rs.getString("nomSalle");
                    System.out.println("requete bien executer");
                    System.out.println(rs.getString("nomSalle"));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listEleve;
    }

    public boolean verifDisponibilite(String jour, String heure, String nomClasse, String regime) {
        boolean listEleve = false;
        GestionDate gd = new GestionDate();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select jour,heureDeb FROM emploidutemps where nomClasse='" + nomClasse + "' and regime='" + regime + "' and jour='" + jour + "' and heureDeb='" + heure + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {                
                    if (rs.getString("heureDeb") != null && rs.getString("jour")!= null) {
                        listEleve = true;
                    }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listEleve;
    }

    /////////////////////////////|||||||||||||||Fin OUZY|||||||||||||||||||////////////////////////////////////////////////
    public ArrayList<Eleve> listEleve(String classe, String matiere, String annee) {
        ArrayList<Eleve> listEleve = new ArrayList<>();
        Connection con;
        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
//            String requete = "select distinct eleve.loginEleve,prenom,nom,classematiere.nomMatiere,classe.nomClasse FROM classe,eleve,classematiere,evaluation where classematiere.nomMatiere='" + matiere + "' and classe.nomClasse='" + classe + "' order by nom";
//            String requete = "select distinct eleve.loginEleve,prenom,nom,matiere.nomMatiere,classe.nomClasse,note,evaluation.idEvaluation FROM classe,eleve,matiere,classematiere,evaluation where classematiere.nomMatiere='"+matiere+"' and classe.nomClasse='"+classe+"' and classematiere.nomClasse=eleve.nomClasse and matiere.idEvaluation=evaluation.idEvaluation order by nom";
            String requete = "select distinct eleve.loginEleve,prenom,nom,classematiere.nomMatiere FROM eleve,classematiere where classematiere.nomMatiere='" + matiere + "' and classematiere.nomClasse='" + classe + "' and eleve.nomClasse='" + classe + "' and eleve.annee='" + annee + "' and eleve.active=0 order by nom";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                eleve = new Eleve();
                eleve.setNom(rs.getString("nom"));
                eleve.setPrenom(rs.getString("prenom"));
                eleve.setClasse(classe);
                eleve.setMatiere(matiere);
                eleve.setMatriculeEleve(rs.getString("loginEleve"));
                listEleve.add(eleve);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listEleve;
    }

    public void ajouterNote1(String devoir1, String devoir2, String composition, String matricule, String semestre, String annee, String matiere) {
        Connection con;
        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
            String requete = "update evaluation set noteDevoir1=?,noteDevoir2=?,noteComposition=? where loginEleve=? and nomMatiere=? and semestre=? and annee=?";
            pst = con.prepareStatement(requete);
            pst.setString(1, devoir1);
            pst.setString(2, devoir2);
            pst.setString(3, composition);
            pst.setString(4, matricule);
            pst.setString(5, matiere);
            pst.setString(6, semestre);
            pst.setString(7, annee);
            int rs = pst.executeUpdate();
            if (rs > 0) {
                System.out.println("la requete est bien exécutée");
            } else {
                System.out.println("Erreur d'exécution");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ajouterNote2(String note, String matricule) {
        Connection con;
        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
//            String requete = "UPDATE  elevevaluation SET  note ='" + note + "'" + "WHERE  matriculeEleve='"+matricule+"'";
            String requete = "insert into elevevaluation (loginEleve,idEvaluation,note,libelle) values(?,?,?,?)";
            pst = con.prepareStatement(requete);
            pst.setString(1, matricule);
            pst.setString(2, "2");
            pst.setString(3, note);
            pst.setString(4, "composition");
            pst.execute();
            pst.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<ListEleve> listNote(String classe, String matiere, String annee) {
        ArrayList<ListEleve> list = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select distinct eleve.loginEleve,prenom,nom,evaluation.nomMatiere,classe.nomClasse,evaluation.noteDevoir1,evaluation.noteDevoir2,evaluation.noteComposition,evaluation.semestre,evaluation.annee FROM classe,eleve,evaluation,profclasse p where evaluation.nomMatiere='" + matiere + "' and classe.nomClasse='" + classe + "' and eleve.loginEleve=evaluation.loginEleve and eleve.nomClasse='" + classe + "' and evaluation.annee='" + annee + "' and p.nomClasse='" + classe + "' and p.nomMatiere='" + matiere + "' and p.annee='" + annee + "' and eleve.annee='" + annee + "' and evaluation.annee=eleve.annee and eleve.active=0 order by nom";
//            String requete = "select eleve.loginEleve,prenom,nom,classematiere.nomMatiere,classe.nomClasse,elevevaluation.devoir1,elevevaluation.devoir2,elevevaluation.composition,evaluation.idEvaluation FROM classe,eleve,classematiere,evaluation,elevevaluation where elevevaluation.loginEleve=eleve.loginEleve and classematiere.nomMatiere='" + matiere + "' and classe.nomClasse='" + classe + "' and eleve.nomClasse=classe.nomClasse order by nom ";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                eleve1 = new ListEleve();
                eleve1.setNom(rs.getString("nom"));
                eleve1.setPrenom(rs.getString("prenom"));
                eleve1.setClasse(classe);
                eleve1.setMatiere(matiere);
                eleve1.setDevoir1(rs.getFloat("noteDevoir1"));
                eleve1.setDevoir2(rs.getFloat("noteDevoir2"));
                eleve1.setMatriculeEleve(rs.getString("loginEleve"));
                eleve1.setComposition(rs.getFloat("noteComposition"));
                eleve1.setSemestre(rs.getString("semestre"));
                eleve1.setAnnee(rs.getString("annee"));
                list.add(eleve1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /*joe*/ public ArrayList<String> eleveParent(String loginEleve) {

        ArrayList<String> annee = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select annee from  eleveannesco,eleve where loginEleve ='" + loginEleve + "' and eleve.active=0";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                annee.add(rs.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return annee;
    }

    public ArrayList<Eleve> eleveParent(String annee, String loginEleve) {
        ArrayList<Eleve> elevePar = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete1 = "select  noteDevoir1,noteDevoir2,noteComposition,nomMatiere,semestre from evaluation,eleve where annee='" + annee + "'and loginEleve='" + loginEleve + "' and eleve.active=0";
            st = con.createStatement();
            ResultSet rs1 = st.executeQuery(requete1);
            while (rs1.next()) {
                Eleve eleve = new Eleve();

                eleve.setSemestre(rs1.getString("semestre"));
                eleve.setDevoir1(rs1.getFloat("noteDevoir1"));
                eleve.setDevoir2(rs1.getFloat("noteDevoir2"));
                eleve.setComposition(rs1.getFloat("noteComposition"));
                eleve.setMatiere(rs1.getString("nomMatiere"));
                eleve.setAnnee(annee);
                elevePar.add(eleve);
            }
            for (Eleve e : elevePar) {
                System.out.println(e.getComposition() + " " + e.getDevoir1() + " " + e.getDevoir2());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return elevePar;
    }

    public void modifierNote(String devoir1, String devoir2, String composition, String loginEleve, String matiere, String semestre, String classe, String annee) {
        Connection con;
        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
            String requete = "UPDATE  evaluation SET  noteDevoir1 ='" + devoir1 + "'" + "WHERE  loginEleve='" + loginEleve + "' and nomMatiere='" + matiere + "' and semestre='" + semestre + "' and annee='" + annee + "'";
            st = con.createStatement();
            int rs = st.executeUpdate(requete);

            String requete1 = "UPDATE  evaluation SET  noteDevoir2 ='" + devoir2 + "'" + "WHERE  loginEleve='" + loginEleve + "' and nomMatiere='" + matiere + "' and semestre='" + semestre + "' and annee='" + annee + "'";
            st = con.createStatement();
            int rs1 = st.executeUpdate(requete1);

            String requete2 = "UPDATE  evaluation SET  noteComposition ='" + composition + "'" + "WHERE  loginEleve='" + loginEleve + "' and nomMatiere='" + matiere + "' and semestre='" + semestre + "' and annee='" + annee + "'";
            st = con.createStatement();
            int rs2 = st.executeUpdate(requete2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String verifProf(String matiere, String classe) {
        String loginProf = "";
        Connection con;
        Statement st;
        Prof pro = new Prof();
        try {
            con = daoFactory.getConnection();
            String requete = "select profclasse.loginProf from profclasse,profmatiere,professeur where profclasse.loginProf=profmatiere.loginProf and profmatiere.nomMatiere='" + matiere + "'" + " and profclasse.nomClasse='" + classe + "' and professeur.active=0";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                pro.setLoginProf(rs.getString("loginProf"));
                loginProf = rs.getString("loginProf");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return loginProf;

    }

    public ArrayList<String> selectMatiere(String loginProf) {
        ArrayList<String> listMatiere = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomMatiere from profmatiere where loginProf='" + loginProf + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                listMatiere.add(rs.getString("nomMatiere"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listMatiere;
    }

    public ArrayList<String> selectClasse(String loginProf) {
        ArrayList<String> listClasse = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select distinct nomClasse from profclasse where loginProf='" + loginProf + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                listClasse.add(rs.getString("nomClasse"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listClasse;
    }

    public ArrayList<Utilisateur> compte(String loginProf) {
        Utilisateur uti = new Utilisateur();
        ArrayList<Utilisateur> compteProf = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select personne.idPersonne,personne.adresse,professeur.loginProf,professeur.motDePasse from personne,professeur where professeur.loginProf='" + loginProf + "' and professeur.idPersonne=personne.idPersonne";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                uti = new Utilisateur();
                uti.setLogin(rs.getString("loginProf"));
                uti.setMotDePasse(rs.getString("motDePasse"));
                uti.setAdresse(rs.getString("adresse"));
                uti.setIdPersonne(rs.getString("idPersonne"));
                compteProf.add(uti);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return compteProf;
    }

    public void modifierCompte(String login, String ancienMdp, String idPersonne) {
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete1 = "UPDATE  professeur SET  motDePasse ='" + ancienMdp + "'" + "WHERE  idPersonne='" + idPersonne + "'";
            st = con.createStatement();
            int rs1 = st.executeUpdate(requete1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> selectAnnee() {
        ArrayList<String> listAnnee = new ArrayList<>();
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select * from annee ";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                listAnnee.add(rs.getString("annee"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listAnnee;
    }

    public String verifProf1(String matiere, String classe, String loginProf) {
        String annne = "";
        Connection con;
        Statement st;
        Prof pro = new Prof();
        try {
            con = daoFactory.getConnection();
            String requete = "select annee from profclasse pc where loginProf='" + loginProf + "'" + " and nomClasse='" + classe + "'" + " and nomMatiere='" + matiere + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                pro.setLoginProf(rs.getString("annee"));
                annne = rs.getString("annee");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return annne;
    }

    public int compte2(String ancienMdp) {
        String login = "null";
        Connection con;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select loginProf from professeur where motDePasse='" + ancienMdp + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                login = rs.getString("loginProf");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (!login.equals("null")) {
            return 0;
        } else {
            return 1;
        }
    }

    public String reclamation(String nomClasse, String nomMatiere, String an) {

        String loginProf = "";
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select loginProf from profclasse where nomMatiere='" + nomMatiere + "' and nomClasse='" + nomClasse + "' and annee='" + an + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                loginProf = rs.getString("loginProf");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return loginProf;
    }

    public void insereReclamation(String loginEleve, String loginProf, String message, String enTete, String date) {

        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
            String requete = "insert into reclamation (loginEleve,loginProf,enTete,message,Date,reponse,lue) values(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(requete);
            pst.setString(1, loginEleve);
            pst.setString(2, loginProf);
            pst.setString(3, enTete);
            pst.setString(4, message);
            pst.setString(5, date);
            pst.setInt(6, 0);
            pst.setInt(7, 0);
            int result0 = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Reclamation> selectReclamation(String loginProf) {

        ArrayList<Reclamation> message = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select enTete,message,loginEleve,Date,idReclam from reclamation  where loginProf='" + loginProf + "' and reponse=0 and lue=0";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setEnTete(rs.getString("enTete"));
                rec.setMessage(rs.getString("message"));
                rec.setLoginEleve(rs.getString("loginEleve"));
                rec.setDate(rs.getString("Date"));
                rec.setIdReclamation(rs.getString("idReclam"));
                message.add(rec);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return message;
    }

    public void insereReclamationRep(String loginEleve, String loginProf, String message, String enTete, String date, String idReclamation) {

        Statement st;
        PreparedStatement pst;
        try {
            con = daoFactory.getConnection();
            String requete = "insert into reclamation (loginEleve,loginProf,enTete,message,Date,reponse,lue) values(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(requete);
            pst.setString(1, loginEleve);
            pst.setString(2, loginProf);
            pst.setString(3, enTete);
            pst.setString(4, message);
            pst.setString(5, date);
            pst.setInt(6, 1);
            pst.setInt(7, 1);
            int result0 = pst.executeUpdate();
            String requete1 = "update reclamation set lue=1 where idReclam='" + idReclamation + "'";
            st = con.createStatement();
            int rs1 = st.executeUpdate(requete1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Reclamation> selectReclamationRep(String loginEleve) {

        ArrayList<Reclamation> message = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select enTete,message,LoginProf,Date from reclamation  where loginEleve='" + loginEleve + "' and reponse=1";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation rec = new Reclamation();
                rec.setEnTete(rs.getString("enTete"));
                rec.setMessage(rs.getString("message"));
                rec.setLoginProf(rs.getString("LoginProf"));
                rec.setDate(rs.getString("Date"));
                message.add(rec);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return message;
    }

    public void search(String loginEleve, String mdp) {
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomClasse,annee from eleve  where loginEleve='" + loginEleve + "' and motDePasse='" + mdp + "' and active=0";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                EleveRecherche.setNomClasse(rs.getString("nomClasse"));
                EleveRecherche.setAnnee(rs.getString("annee"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /////////////////////////////////////////////////////joe////////////////////////////////////////////////////////////////// 
    public Boolean inscription(Eleve elev, Parent parent) {

        Boolean resultat = false;

        try {
            con = daoFactory.getConnection();

            PreparedStatement pstp;
            String reqPa = "insert into parent values(?,?,?,?,?)";
            pstp = con.prepareStatement(reqPa);
            pstp.setString(1, parent.getLoginParent());
            pstp.setString(2, parent.getNom());
            pstp.setString(3, parent.getPrenom());
            pstp.setString(4, parent.getTel());
            pstp.setString(5, parent.getMotDePasse());
            int resulta = pstp.executeUpdate();

            PreparedStatement pst;
            String requete = "insert into eleve(nomClasse,nom,prenom,adresse,telephone,dateNaissance,lieuNaissance,loginEleve,motDePasse,loginParent,annee) values(?,?,?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(requete);
            pst.setString(1, elev.getNomClasse());
            pst.setString(2, elev.getNom());
            pst.setString(3, elev.getPrenom());
            pst.setString(4, elev.getAdresse());
            pst.setString(5, elev.getTel());
            pst.setString(6, elev.getDateNaissance());
            pst.setString(7, elev.getLieuNaissance());

            pst.setString(8, elev.getLogin());
            pst.setString(9, elev.getMotDePasse());
            pst.setString(10, parent.getLoginParent());
            pst.setString(11, elev.getAnnee());
            int result = pst.executeUpdate();

            PreparedStatement pst0;
            String req = "insert into eleveannesco values(?,?)";
            pst0 = con.prepareStatement(req);
            pst0.setString(1, elev.getAnnee());
            pst0.setString(2, elev.getLogin());
            int result0 = pst0.executeUpdate();

            Statement st0;
            String requ = "select nomMatiere from classematiere where nomClasse = '" + elev.getNomClasse() + "'";
            st0 = con.createStatement();
            ResultSet rs0 = st0.executeQuery(requ);
            while (rs0.next()) {

                PreparedStatement pst1;

                String requete2 = "insert into evaluation values (?,?,?,?,?,?,?)";
                pst1 = con.prepareStatement(requete2);
                pst1.setString(1, elev.getLogin());
                pst1.setString(2, rs0.getString("nomMatiere"));
                System.out.println(rs0.getString("nomMatiere"));
                pst1.setString(3, "1er_semestre");
                pst1.setString(4, elev.getAnnee());
                System.out.println(elev.getAnnee());

                System.out.println("joe");
                System.out.println(elev.getDevoir1());
                System.out.println("sarr");
                pst1.setFloat(5, elev.getDevoir1());
                pst1.setFloat(6, elev.getDevoir2());
                pst1.setFloat(7, elev.getComposition());
                int result1 = pst1.executeUpdate();

                PreparedStatement pst2;
                String requete3 = "insert into evaluation values (?,?,?,?,?,?,?)";
                pst2 = con.prepareStatement(requete3);
                pst2.setString(1, elev.getLogin());
                pst2.setString(2, rs0.getString("nomMatiere"));
                pst2.setString(3, "2eme_semestre");
                pst2.setString(4, elev.getAnnee());
                pst2.setFloat(5, elev.getDevoir1());
                pst2.setFloat(6, elev.getDevoir2());
                pst2.setFloat(7, elev.getComposition());
                int result2 = pst2.executeUpdate();

            }

            if (result > 0) {
                System.out.println("requete a été bien éxécutée");
                resultat = true;
            } else {

                System.out.println("Erreur de la requete");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;

    }

    public Boolean reinscription(Eleve elev, String annee, String nomClasse) {

        Boolean resultat = false;

        try {
            con = daoFactory.getConnection();

            PreparedStatement pst;
            String requete = "insert into eleve(nomClasse,nom,prenom,adresse,telephone,dateNaissance,lieuNaissance,loginEleve,motDePasse,loginParent,annee) values(?,?,?,?,?,?,?,?,?,?,?)";
            pst = con.prepareStatement(requete);
            pst.setString(1, nomClasse);
            pst.setString(2, elev.getNom());
            pst.setString(3, elev.getPrenom());
            pst.setString(4, elev.getAdresse());
            pst.setString(5, elev.getTel());
            pst.setString(6, elev.getDateNaissance());
            pst.setString(7, elev.getLieuNaissance());

            pst.setString(8, elev.getLogin());
            pst.setString(9, elev.getMotDePasse());
            pst.setString(10, elev.getLoginParent());
            pst.setString(11, annee);
            int result = pst.executeUpdate();

            PreparedStatement pst0;
            String req = "insert into eleveannesco values(?,?)";
            pst0 = con.prepareStatement(req);
            pst0.setString(1, annee);
            pst0.setString(2, elev.getLogin());
            int result0 = pst0.executeUpdate();

            Statement st0;
            String requ = "select nomMatiere from classematiere where nomClasse = '" + nomClasse + "'";
            st0 = con.createStatement();
            ResultSet rs0 = st0.executeQuery(requ);
            while (rs0.next()) {

                PreparedStatement pst1;

                String requete2 = "insert into evaluation values (?,?,?,?,?,?,?)";
                pst1 = con.prepareStatement(requete2);
                pst1.setString(1, elev.getLogin());
                pst1.setString(2, rs0.getString("nomMatiere"));
                pst1.setString(3, "1er_semestre");
                pst1.setString(4, annee);
                pst1.setFloat(5, elev.getDevoir1());
                pst1.setFloat(6, elev.getDevoir2());
                pst1.setFloat(7, elev.getComposition());
                int result1 = pst1.executeUpdate();

                PreparedStatement pst2;
                String requete3 = "insert into evaluation values (?,?,?,?,?,?,?)";
                pst2 = con.prepareStatement(requete3);
                pst2.setString(1, elev.getLogin());
                pst2.setString(2, rs0.getString("nomMatiere"));
                pst2.setString(3, "2eme_semestre");
                pst2.setString(4, annee);
                pst2.setFloat(5, elev.getDevoir1());
                pst2.setFloat(6, elev.getDevoir2());
                pst2.setFloat(7, elev.getComposition());
                int result2 = pst2.executeUpdate();

            }

            if (result > 0) {
                System.out.println("requete a été bien éxécutée");
                resultat = true;
            } else {

                System.out.println("Erreur de la requete");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultat;

    }

    public Eleve reinscriptionEleve(String login) {
        Eleve eleve = null;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select * from eleve where loginEleve = '" + login + "' and active=0";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                eleve = new Eleve(rs.getString("nomClasse"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("dateNaissance"), rs.getString("lieuNaissance"), rs.getString("annee"), rs.getString("loginEleve"), rs.getString("motDePasse"), rs.getString("loginParent"));

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eleve;
    }

    public ArrayList<String> listerClasse(String regime) {

        ArrayList<String> classes = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = null;
            if (regime.equalsIgnoreCase("Public")) {
                requete = "select nomClasse from classe where regime='Public'";
            } else if (regime.equalsIgnoreCase("Privee")) {
                requete = "select nomClasse from classe where regime='Privee'";
            }

            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                classes.add(rs.getString("nomClasse"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return classes;
    }

    public ArrayList<Eleve> listerEleve(String nomCl, String an, String regime, String mois) {

        ArrayList<Eleve> eleves = new ArrayList();
        Statement st;
        Statement st1;
        try {
            con = daoFactory.getConnection();
            String requete = "select eleve.login,nom,prenom,adresse,dateNaissance,lieuNaissance,telephone,moySemestre1,moySemestre2 from eleve,personne where eleve.login=personne.login and nomClasse = '" + nomCl + "' and anneeScolaire='" + an + "' and regime='"+regime+"'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Eleve el = new Eleve(null, rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("dateNaissance"), rs.getString("lieuNaissance"), null, rs.getString("eleve.login"), null, null);
                el.setMoySemestre1(rs.getFloat("moySemestre1"));
                el.setMoySemestre2(rs.getFloat("moySemestre2"));
                String req1 = "select statutMensuel from mensuel where login ='"+rs.getString("eleve.login")+"' and mois ='"+mois+"' and anneeScolaire='"+an+"'";
                st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(req1);
                 if(rs1.next()) {
                     el.setEtatPaiement(rs1.getString("statutMensuel"));
                 }
                eleves.add(el);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eleves;
    }

    public void supprimerEleve(String login) {

        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;
            PreparedStatement pst1;
            String requete = "delete from evaluation where loginEleve=?";
            pst = con.prepareStatement(requete);
            pst.setString(1, login);
            pst.executeUpdate();

            String requete1 = "delete from eleve where loginEleve=?";
            pst1 = con.prepareStatement(requete1);
            pst1.setString(1, login);
            int result1 = pst1.executeUpdate();

            if (result1 > 0) {
                System.out.println("la Suppression a bien été bien effectuée");

            } else {

                System.out.println("Erreur de requete");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public Eleve listerUnEleve(String login) {
        Eleve eleve = null;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select * from eleve where loginEleve = '" + login + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                eleve = new Eleve(rs.getString("nomClasse"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("dateNaissance"), rs.getString("lieuNaissance"), null, rs.getString("loginEleve"), rs.getString("motDePasse"), null);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eleve;
    }

    public void modifierEleve(Eleve eleve, String idLog) {

        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;
            String requete = "update eleve set nomClasse=? , nom=? , prenom=? , adresse=? ,telephone=?, dateNaissance=? , lieuNaissance=? where loginEleve = '" + idLog + "'";
            pst = con.prepareStatement(requete);
            pst.setString(1, eleve.getNomClasse());
            pst.setString(2, eleve.getNom());
            pst.setString(3, eleve.getPrenom());
            pst.setString(4, eleve.getAdresse());
            pst.setString(5, eleve.getTel());
            pst.setString(6, eleve.getDateNaissance());
            pst.setString(7, eleve.getLieuNaissance());

            int result = pst.executeUpdate();

            PreparedStatement pst1;
            String req = "update eleveannesco set annee=? where loginEleve =?";
            pst1 = con.prepareStatement(req);
            pst1.setString(1, eleve.getAnnee());
            pst1.setString(2, idLog);
            int result1 = pst1.executeUpdate();
            if ((result > 0) && (result1 > 0)) {
                System.out.println("requete a été bien éxécutée");

            } else {
                System.out.println("Erreur de la requete");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Eleve> evaluationEleve(String login, String semestre, String an) {

        ArrayList<Eleve> eleves = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomMatiere,noteDevoir1,noteDevoir2,noteComposition,annee from evaluation where loginEleve = '" + login + "' and semestre = '" + semestre + "' and annee='" + an + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                Eleve el = new Eleve(rs.getFloat("noteDevoir1"), rs.getFloat("noteDevoir2"), rs.getFloat("noteComposition"), rs.getString("nomMatiere"));
                eleves.add(el);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eleves;

    }

    public String nomClasse(String login, String an) {

        String nomClasse = "";
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomClasse from eleve where loginEleve = '" + login + "' and annee='" + an + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                nomClasse = rs.getString("nomClasse");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return nomClasse;

    }

    public ArrayList<Eleve> listerclasse(String login, String an) {
        ArrayList<Eleve> eleves = new ArrayList();
        String nomClasse = null;
        String annee = null;
        Statement st;
        Statement st1;
        try {
            con = daoFactory.getConnection();
            String requete = "select nomClasse,anneeScolaire from eleve where login ='" + login + "' "
                    + "and anneeScolaire='" + an + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                nomClasse = rs.getString("nomClasse");
                annee = rs.getString("anneeScolaire");

            }
            String requete1 = "select nom,prenom,adresse,telephone,eleve.login from eleve,personne "
                    + "where nomClasse = '" + nomClasse + "' and anneeScolaire='" + annee + "' "
                    + "and eleve.login=personne.login order by nom";
            st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery(requete1);
            while (rs1.next()) {
                Eleve el = new Eleve(rs1.getString(1), rs1.getString(2), rs1.getString(3), rs1.getString(4), rs1.getString(5));
                eleves.add(el);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eleves;

    }

    public String maClasse(String login) {

        String nomClasse = null;
        Statement st;

        try {
            con = daoFactory.getConnection();
            String requete = "select nomClasse from eleve where login = '" + login + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                nomClasse = rs.getString("nomClasse");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return nomClasse;
    }

    public ArrayList<Personne> listerProf(String login, String an) {
        ArrayList<Personne> profs = new ArrayList();
        String[] matieres;
        int i = 0;
        String nomClasse = maClasse(login);
        Statement st;
        Statement st0;
        Personne prof;
        try {
            con = daoFactory.getConnection();
            String requete = "select distinct pc.login,nom,prenom from personne p, profclasse pc "
                    + "where nomClasse = '" + nomClasse + "' and anneeScolaire='" + an + "'"
                    + " and pc.login = p.login";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                matieres = new String[5];
                String requete0 = "select nomMatiere from profMatiere pm,professeur p, profClasse pc "
                        + "where pm.login='" + rs.getString(1) + "' and pc.nomClasse='" + nomClasse + "' and "
                        + "pc.anneeScolaire='" + an + "' and p.login = pc.login and p.login = pm.login";
                st0 = con.createStatement();
                ResultSet rs0 = st0.executeQuery(requete0);
                while (rs0.next()) {
                    String k = rs0.getString(1);
                    matieres[i] = k;
                    i++;
                }

                prof = new Personne(rs.getString(2), rs.getString(3), matieres);

                profs.add(prof);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return profs;

    }

    public String listerAnnee(String login) {

        String annee = null;
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select anneeScolaire from  eleve where login ='" + login + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                annee = rs.getString(1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return annee;
    }
    /////

    public ArrayList<String> anneeScolaire(String login) {

        ArrayList<String> annee = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select annee from  eleveannesco where loginEleve ='" + login + "'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                annee.add(rs.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return annee;
    }
/////

        public ArrayList<String> listerAnnee() {

        ArrayList<String> annee = new ArrayList();
        Statement st;
        try {
            con = daoFactory.getConnection();
            String requete = "select annee from  annee";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                annee.add(rs.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return annee;
    }

    public void changepswd(String login, String passwd) {
        try {
            con = daoFactory.getConnection();
            PreparedStatement pst;
            String requete1 = "update eleve set motDePasse=? where loginEleve=?";
            pst = con.prepareStatement(requete1);
            pst.setString(1, passwd);
            pst.setString(2, login);
            int result = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public void desactiverEleve(String loginEleve) {
//        int result1 = 0;
//        try {
//            con = daoFactory.getConnection();
//            PreparedStatement pst1;
//            String requete1 = "update eleve set active=? where loginEleve =? ";
//            pst1 = con.prepareStatement(requete1);
//            pst1.setInt(1, 1);
//            pst1.setString(2, loginEleve);
//            pst1.executeUpdate();
//            result1 = pst1.executeUpdate();
//            pst1.close();
//            if (result1 > 0) {
//                System.out.println("les requetes ont été bien éxécutées /t modifiees avec succes");
//            } else {
//                System.out.println("Erreur de requete(s)");
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
}
