/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelTables;

/**
 *
 * @author Moussa Joseph Sarr
 */
public class Personne {
    
    private int idPersonne;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String[] matiere; 
    private int idProf;
    private String idParent;
    private String login;
    private String motDePasse;
    private String profils;
    private String classe;
    private String nomImgPers;
    private int etatPers;
    private String nomMatiere;

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }
    
    
    

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Personne(String nom, String prenom, String adresse, String tel, String login, String motDePasse, String profils, int etatPers) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.login = login;
        this.motDePasse = motDePasse;
        this.profils = profils;
        this.etatPers = etatPers;
    }
    
    
    

    public Personne() {
    }

    
    
    public Personne(int idPersonne,String nom, String prenom, String adresse, String tel) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        
    }

    public Personne(String nom, String prenom, String[] matiere) {
        this.nom = nom;
        this.prenom = prenom;        
        this.matiere = matiere;
    }

    public String getNomImgPers() {
        return nomImgPers;
    }

    public void setNomImgPers(String nomImgPers) {
        this.nomImgPers = nomImgPers;
    }

    public int getEtatPers() {
        return etatPers;
    }

    public void setEtatPers(int etatPers) {
        this.etatPers = etatPers;
    }
    
    
    public String getProfils() {
        return profils;
    }

    public void setProfils(String profils) {
        this.profils = profils;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }


    public int getIdProf() {
        return idProf;
    }

    public String getIdParent() {
        return idParent;
    }

    public void setIdPresonne(int idPresonne) {
        this.idPersonne = idPresonne;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    public String[] getMatiere() {
        return matiere;
    }

    public void setMatiere(String[] matiere) {
        this.matiere = matiere;
    }
    
    
    
}
