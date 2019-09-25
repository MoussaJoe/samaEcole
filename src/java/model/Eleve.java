<<<<<<< HEAD
package model;

/**
 *
 * @author ibrah
 */
public class Eleve implements Comparable<Eleve>{

    private String matriculeEleve;
    private String nom;
    private String prenom;
    private String nomClasse;
    private String adresse;
    private String niveau;
    private String dateNaissance;
    private String lieuNaissance;
    private String loginParent;
    private String login;
    private String motDePasse;
    private String matiere;
    private String classe;
    private float devoir1;
    private float devoir;
    private float devoir2;
    private String idEvaluation;
    private float composition;
    private float noteComposition;
    private String semestre;
    private String annee;
    private float moyenne;
    private float moySemestre1;
    private float moySemestre2;
    private String tel;
    private String sexe;
    private int validerIns;
    private String regime;
    private int idInscription;
    private String heure;
    private String nomSalle;
    private String jour;
    private String nomMatiere;
    private String etatPaiement;
    
    
    public Eleve() {
    }

    public Eleve(String dateNaissance, String lieuNaissance, String loginParent, String login, String classe, String annee, String sexe, int validerIns, String regime, int idInscription) {
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.loginParent = loginParent;
        this.login = login;
        this.classe = classe;
        this.annee = annee;
        this.sexe = sexe;
        this.validerIns = validerIns;
        this.regime = regime;
        this.idInscription = idInscription;
    }
    
    
    

    public Eleve(String nomClasse, String nom, String prenom, String adresse, String tel, String dateNaissance, String lieuNaissance, String annee, String login, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.annee = annee;
        this.login = login;
        this.motDePasse = motDePasse;
        this.nomClasse = nomClasse;
        this.tel = tel;
    }
    public Eleve(String nomClasse, String nom, String prenom, String adresse, String tel, String dateNaissance, String lieuNaissance, String annee, String login, String motDePasse, String loginParent) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.annee = annee;
        this.login = login;
        this.motDePasse = motDePasse;
        this.nomClasse = nomClasse;
        this.tel = tel;
        this.loginParent= loginParent;
    }

    public Eleve(float devoir1, float devoir2, float composition, String matiere) {
        this.devoir1 = devoir1;
        this.devoir2 = devoir2;
        this.composition = composition;
        this.matiere = matiere;
        // this.annee= annee;
    }
    public Eleve(float devoir, String matiere) {
        this.devoir = devoir;
        this.matiere = matiere;
        // this.annee= annee;
    }
    public Eleve(float noteComposition, String matiere,String semestre) {
        this.noteComposition = noteComposition;
        this.matiere = matiere;
        // this.annee= annee;
    }

    public Eleve(String nom, String prenom, String adresse, String tel, String login) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.login = login;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }
    
    

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    
    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    
    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
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

    public String getMatriculeEleve() {
        return matriculeEleve;
    }

    public void setMatriculeEleve(String matriculeEleve) {
        this.matriculeEleve = matriculeEleve;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
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

    public int getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(int idInscription) {
        this.idInscription = idInscription;
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
    
    

    public String getMatiere() {
        
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(String idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public float getDevoir1() {
        return devoir1;
    }

    public void setDevoir1(float devoir1) {
        this.devoir1 = devoir1;
    }

    public float getDevoir2() {
        return devoir2;
    }

    public void setDevoir2(float devoir2) {
        this.devoir2 = devoir2;
    }

    public float getComposition() {
        return composition;
    }

    public void setComposition(float composition) {
        this.composition = composition;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public float getMoyenne() {
        return (((getDevoir1() + getDevoir2()) / 2) + getComposition()) / 2;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLoginParent() {
        return loginParent;
    }

    public void setLoginParent(String loginParent) {
        this.loginParent = loginParent;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getValiderIns() {
        return validerIns;
    }

    public void setValiderIns(int validerIns) {
        this.validerIns = validerIns;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(String etatPaiement) {
        this.etatPaiement = etatPaiement;
    }
    
    

    @Override
    public int compareTo(Eleve o) {
        float prix = this.getMoySemestre1();
        float sonPrix = o.getMoySemestre1();
        return (new Float(prix)).compareTo(new Float(sonPrix));
    }

    public float getDevoir() {
        return devoir;
    }

    public void setDevoir(float devoir) {
        this.devoir = devoir;
    }

    public float getNoteComposition() {
        return noteComposition;
    }

    public void setNoteComposition(float noteComposition) {
        this.noteComposition = noteComposition;
    }
    

}
=======

package model;

/**
 *
 * @author ibrah
 */
public class Eleve implements Comparable<Eleve>{

    private String matriculeEleve;
    private String nom;
    private String prenom;
    private String nomClasse;
    private String adresse;
    private String niveau;
    private String dateNaissance;
    private String lieuNaissance;
    private String loginParent;
    private String login;
    private String motDePasse;
    private String matiere;
    private String classe;
    private float devoir1;
    private float devoir;
    private float devoir2;
    private String idEvaluation;
    private float composition;
    private float noteComposition;
    private String semestre;
    private String annee;
    private float moyenne;
    private float moySemestre1;
    private float moySemestre2;
    private String tel;
    private String sexe;
    private int validerIns;
    private String regime;
    private String idInscription;
    private String heure;
    private String nomSalle;
    private String jour;
    private String nomMatiere;
    private String etatPaiement;
    
    
    public Eleve() {
    }

    public Eleve(String dateNaissance, String lieuNaissance, String loginParent, String login, String classe, String annee, String sexe, int validerIns, String regime, String idInscription) {
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.loginParent = loginParent;
        this.login = login;
        this.classe = classe;
        this.annee = annee;
        this.sexe = sexe;
        this.validerIns = validerIns;
        this.regime = regime;
        this.idInscription = idInscription;
    }
    
    
    

    public Eleve(String nomClasse, String nom, String prenom, String adresse, String tel, String dateNaissance, String lieuNaissance, String annee, String login, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.annee = annee;
        this.login = login;
        this.motDePasse = motDePasse;
        this.nomClasse = nomClasse;
        this.tel = tel;
    }
    public Eleve(String nomClasse, String nom, String prenom, String adresse, String tel, String dateNaissance, String lieuNaissance, String annee, String login, String motDePasse, String loginParent) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.annee = annee;
        this.login = login;
        this.motDePasse = motDePasse;
        this.nomClasse = nomClasse;
        this.tel = tel;
        this.loginParent= loginParent;
    }

    public Eleve(float devoir1, float devoir2, float composition, String matiere) {
        this.devoir1 = devoir1;
        this.devoir2 = devoir2;
        this.composition = composition;
        this.matiere = matiere;
        // this.annee= annee;
    }
    public Eleve(float devoir, String matiere) {
        this.devoir = devoir;
        this.matiere = matiere;
        // this.annee= annee;
    }
    public Eleve(float noteComposition, String matiere,String semestre) {
        this.noteComposition = noteComposition;
        this.matiere = matiere;
        // this.annee= annee;
    }

    public Eleve(String nom, String prenom, String adresse, String tel, String login) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.login = login;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }
    
    

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    
    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    
    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
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

    public String getMatriculeEleve() {
        return matriculeEleve;
    }

    public void setMatriculeEleve(String matriculeEleve) {
        this.matriculeEleve = matriculeEleve;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
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

    public String getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(String idInscription) {
        this.idInscription = idInscription;
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
    
    

    public String getMatiere() {
        
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(String idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public float getDevoir1() {
        return devoir1;
    }

    public void setDevoir1(float devoir1) {
        this.devoir1 = devoir1;
    }

    public float getDevoir2() {
        return devoir2;
    }

    public void setDevoir2(float devoir2) {
        this.devoir2 = devoir2;
    }

    public float getComposition() {
        return composition;
    }

    public void setComposition(float composition) {
        this.composition = composition;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public float getMoyenne() {
        return (((getDevoir1() + getDevoir2()) / 2) + getComposition()) / 2;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLoginParent() {
        return loginParent;
    }

    public void setLoginParent(String loginParent) {
        this.loginParent = loginParent;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getValiderIns() {
        return validerIns;
    }

    public void setValiderIns(int validerIns) {
        this.validerIns = validerIns;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(String etatPaiement) {
        this.etatPaiement = etatPaiement;
    }
    
    

    @Override
    public int compareTo(Eleve o) {
        float prix = this.getMoySemestre1();
        float sonPrix = o.getMoySemestre1();
        return (new Float(prix)).compareTo(new Float(sonPrix));
    }

    public float getDevoir() {
        return devoir;
    }

    public void setDevoir(float devoir) {
        this.devoir = devoir;
    }

    public float getNoteComposition() {
        return noteComposition;
    }

    public void setNoteComposition(float noteComposition) {
        this.noteComposition = noteComposition;
    }
    

}
>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403
