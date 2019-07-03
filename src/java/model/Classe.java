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
public class Classe {
    
    String nomClasse;
    String regime;
    ArrayList<String> matieres;

    public Classe(String nomClasse, String regime,ArrayList<String> matieres) {
        this.nomClasse = nomClasse;
        this.regime = regime;
        this.matieres = matieres;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public ArrayList<String> getMatieres() {
        return matieres;
    }

    public void setMatieres(ArrayList<String> matieres) {
        this.matieres = matieres;
    }

    @Override
    public String toString() {
        return "Classe{" + "nomClasse=" + nomClasse + ", regime=" + regime + ", matieres=" + matieres + '}';
    }
    
    
}
