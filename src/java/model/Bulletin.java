/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Moussa Joseph Sarr
 */
public class Bulletin {

    private String prenom;
    private String nom;
    private String regime;
    private String annee;
    private String semestre;
    private String login;
    private String moyenne;
    private String nbreEleve;
    private String dateNaissance;
    private String lieuNaissance;
    private String nomClasse;
    private ArrayList<Evaluation> evaluation;
    private int totalCoef;
    private float totalMoyenne;
    private float moyenneGenerale;
    private int absences;
    private int retards;
    private int rang;
    private float moySemestre1;
    private float moySemestre2;
    private String appreciation;

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }
    
    

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    
    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(String moyenne) {
        this.moyenne = moyenne;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public ArrayList<Evaluation> getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(ArrayList<Evaluation> evaluation) {
        this.evaluation = evaluation;
    }

    public String getNbreEleve() {
        return nbreEleve;
    }

    public void setNbreEleve(String nbreEleve) {
        this.nbreEleve = nbreEleve;
    }

    public int getTotalCoef() {
        return totalCoef;
    }

    public void setTotalCoef(int totalCoef) {
        this.totalCoef = totalCoef;
    }

    public float getTotalMoyenne() {
        return totalMoyenne;
    }

    public void setTotalMoyenne(float totalMoyenne) {
        this.totalMoyenne = totalMoyenne;
    }

    public float getMoyenneGenerale() {
        return moyenneGenerale;
    }

    public void setMoyenneGenerale(float moyenneGenerale) {
        this.moyenneGenerale = moyenneGenerale;
    }

    public int getAbsences() {
        return absences;
    }

    public void setAbsences(int absences) {
        this.absences = absences;
    }

    public int getRetards() {
        return retards;
    }

    public void setRetards(int retards) {
        this.retards = retards;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public float getMoySemestre1() {
        return moySemestre1;
    }

    public void setMoySemestre1(float moySemestre1) {
        this.moySemestre1 = moySemestre1;
    }

    public float getMoySemestre2() {
        return moySemestre2;
    }

    public void setMoySemestre2(float moySemestre2) {
        this.moySemestre2 = moySemestre2;
    }
    
}
