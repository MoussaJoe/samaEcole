<<<<<<< HEAD
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import modelPersonne.DAODirecteurImpl;
import modelPersonne.DAOEleveImpl;
import modelPersonne.DAOParentImpl;
import modelPersonne.DAOPersonneImpl;
import modelPersonne.DAOProfImpl;
import modelPersonne.DAOProfsImpl;

/**
 *
 * @author Moussa Joseph Sarr
 */
public class DAOFactory {

    private String url;
    private String username;
    private String password;

    public DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Erreur de chargement du piolte de la base");
        }
        DAOFactory instance = new DAOFactory("jdbc:mysql://127.0.0.1/samaecolev2", "root", "");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public DAOPersonneImpl getDAOPersonne() {
        return new DAOPersonneImpl(this);
    }

    public DAOEleveImpl getDAOEleve() {
        return new DAOEleveImpl(this);
    }
    public DAOParentImpl getDAOParent() {
        return new DAOParentImpl(this);
    }
    public DAOProfImpl getDAOProf() {
        return new DAOProfImpl(this);
    }
    public DAOProfsImpl getDAOProfs() {
        return new DAOProfsImpl(this);
    }
    public DAODirecteurImpl getDAODirecteur() {
        return new DAODirecteurImpl(this);
    }
}

//Récupération des Utilisateurs

=======
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import modelPersonne.DAODirecteurImpl;
import modelPersonne.DAOEleveImpl;
import modelPersonne.DAOParentImpl;
import modelPersonne.DAOPersonneImpl;
import modelPersonne.DAOProfImpl;
import modelPersonne.DAOProfsImpl;

/**
 *
 * @author Moussa Joseph Sarr
 */
public class DAOFactory {

    private String url;
    private String username;
    private String password;

    public DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Erreur de chargement du piolte de la base");
        }
        DAOFactory instance = new DAOFactory("jdbc:mysql://localhost:8889/samaecole", "root", "root");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public DAOPersonneImpl getDAOPersonne() {
        return new DAOPersonneImpl(this);
    }

    public DAOEleveImpl getDAOEleve() {
        return new DAOEleveImpl(this);
    }
    public DAOParentImpl getDAOParent() {
        return new DAOParentImpl(this);
    }
    public DAOProfImpl getDAOProf() {
        return new DAOProfImpl(this);
    }
    public DAOProfsImpl getDAOProfs() {
        return new DAOProfsImpl(this);
    }
    public DAODirecteurImpl getDAODirecteur() {
        return new DAODirecteurImpl(this);
    }
}

//Récupération des Utilisateurs

>>>>>>> 34f74ff87cfe3a30eb3015758bf80d0e153ca403
