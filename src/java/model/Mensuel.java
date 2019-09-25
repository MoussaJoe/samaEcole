/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ouzy NDIAYE
 */
public class Mensuel {
    private String loginElv;
    private String anneeScolaire;
    private String statutMensuel;
    private String dateMensuel;
    private String mois;
    private int montant;
    private int reliquat;

    public Mensuel() {
    }

    public Mensuel(String mois) {
        this.mois = mois;
    }

    
    
    public Mensuel(String loginElv, String anneeScolaire, String statutMensuel, String dateMensuel,  int montant, int reliquat) {
        this.loginElv = loginElv;
        this.anneeScolaire = anneeScolaire;
        this.statutMensuel = statutMensuel;
        this.dateMensuel = dateMensuel;
        this.montant = montant;
        this.reliquat = reliquat;
    }

    
    
    
    public String getLoginElv() {
        return loginElv;
    }

    public void setLoginElv(String loginElv) {
        this.loginElv = loginElv;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    
    
    public String getStatutMensuel() {
        return statutMensuel;
    }

    public void setStatutMensuel(String statutMensuel) {
        this.statutMensuel = statutMensuel;
    }

    public String getDateMensuel() {
        return dateMensuel;
    }

    public void setDateMensuel(String dateMensuel) {
        this.dateMensuel = dateMensuel;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getReliquat() {
        return reliquat;
    }

    public void setReliquat(int reliquat) {
        this.reliquat = reliquat;
    }
    
    
}
