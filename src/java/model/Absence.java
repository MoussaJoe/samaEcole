/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Moussa Joseph D Sarr
 */
public class Absence {
    private String nom;
    private String prenom;
    private int totalAbsence;
    private int totalRetard;
    private ArrayList<Matiere_Absence> matAbs;
    private String login;
    private String semestre;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ArrayList<Matiere_Absence> getMatAbs() {
        return matAbs;
    }

    public void setMatAbs(ArrayList<Matiere_Absence> matAbs) {
        this.matAbs = matAbs;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getTotalAbsence() {
        return totalAbsence;
    }

    public void setTotalAbsence(int totalAbsence) {
        this.totalAbsence = totalAbsence;
    }

    public int getTotalRetard() {
        return totalRetard;
    }

    public void setTotalRetard(int totalRetard) {
        this.totalRetard = totalRetard;
    }

   
    
}
