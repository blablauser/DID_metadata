package didproject;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author balusesa
 */
public class DBaccess {

    int n;
    //private TreeMap<String,String> dictionary; // the dictionary
//    String tblName = "Dic1";   // 
    // Settings for SCS mysql 5.1  -  Dec 2008
    private static Connection conn;
    private static Statement state;
    /**/

    /**
     * Create a new C0Dictionary with a maximum number of 
     * entries given by inMax
     */
    public static void connect(String host, String port, String user, String passw, String dbname) {
        dbname = "other_balusesa";     // Name of your MySQL DB
        user = "balusesa";         // Your MySQL user name
        passw = "Bsbone123";         // Your MySQL password
        host = "ramen.cs.man.ac.uk";
        port = "3306"; //normally 3306 in MySQL
        try {
            // Owner-dependent Strings

            //  System.err.println( "TS_Before calling Class.for.name");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //  System.err.println( "TS_Gone past Class.for.name");

            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbname
                    + "?user=" + user + "&password=" + passw + "");
            System.err.println("TS_Gone past connection setting");
        } catch (Exception e) {
            System.err.println("TS_Gone nuuuuuuullllll"+e.getMessage());
            conn = null;
        }
    }

    public static ResultSet retrieve(String q) {
        try {
            state = conn.createStatement();
             System.err.println("retrive: "+q);
            return (state.executeQuery(q));
        } catch (SQLException ex) {
            System.err.println(q);
            Logger.getLogger(DBaccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void update(String updt) {
        try {
            state = conn.createStatement();
            state.executeUpdate(updt);
        } catch (SQLException ex) {
             System.err.println(updt);
            Logger.getLogger(DBaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void create(String create) {
        try {
            state = conn.createStatement();
            state.execute(create);
        } catch (SQLException ex) {
            System.err.println(create);
            Logger.getLogger(DBaccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void createTable(String create) {
        try {
            state = conn.createStatement();
            state.execute(create);
        } catch (SQLException ex) {
            System.err.println(create);
        }
    }

    public static void disconnect() {
        //  System.err.println( "TS_destroy() called" );
        try {
            conn.close();
        } catch (Exception e) {
            //      System.err.println( "TS_Problem closing the database connection" );
        }
    }
}
