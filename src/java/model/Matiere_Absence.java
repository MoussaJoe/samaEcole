/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Moussa Joseph D Sarr
 */
public class Matiere_Absence {
    private String matiere;
    private int heureAbsence;
    private int heureRetard;
    
    
     public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public int getHeureAbsence() {
        return heureAbsence;
    }

    public void setHeureAbsence(int heureAbsence) {
        this.heureAbsence = heureAbsence;
    }

    public int getHeureRetard() {
        return heureRetard;
    }

    public void setHeureRetard(int heureRetard) {
        this.heureRetard = heureRetard;
    }
    
}
