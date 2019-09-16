/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.tables;

/**
 *
 * @author Moussa Joseph Sarr
 */
public class ProfClasse {

    private int idPerson;
    private String loginProf;
    private String nomClasse;
    private String nomMatiere;
    private String annee;
    private String regime;

    public ProfClasse(int idPerson, String loginProf, String nomClasse, String nomMatiere, String annee,  String regime) {
        this.idPerson = idPerson;
        this.loginProf = loginProf;
        this.nomClasse = nomClasse;
        this.nomMatiere = nomMatiere;
        this.annee = annee;
        this.regime = regime;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }
    

    public String getLoginProf() {
        return loginProf;
    }

    public void setLoginProf(String loginProf) {
        this.loginProf = loginProf;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
    
    
    
    
    

}
