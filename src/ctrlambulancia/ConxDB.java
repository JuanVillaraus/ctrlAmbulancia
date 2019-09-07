/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Sistemas
 */
public class ConxDB {

    Connection c = null;

    public ConxDB(JFrame frameInsert) {
        System.out.println("ConxDB");
        try {
            Class.forName("org.postgresql.Driver");
            this.c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBCtrlAmb", "postgres", "admin");
            System.out.println("Opened database successfully");
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            System.err.println("ConxDB/ConxDB$\t" + e.getClass().getName() + "\t" + e.getMessage());
            frameInsert.dispose();
        }
    }

    public ConxDB() {
        System.out.println("ConxDB");
        try {
            Class.forName("org.postgresql.Driver");
            this.c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBCtrlAmb", "postgres", "admin");
            System.out.println("Opened database successfully");
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            System.err.println("ConxDB/ConxDB$\t" + e.getClass().getName() + "\t" + e.getMessage());
            Logger.getLogger(ConxDB.class.getName()).log(Level.SEVERE, null, e);
            System.exit(0);
        }
    }

    public void close() {
        try {
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger("ConxDB/Close$\t" + ConxDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String insertOper(int idOper, String name, String lastName, String lastName2) {
        String query = "INSERT INTO \"OPERADOR\"(\"PK_ID_OPERADOR\", \"NOMBRE_OPERADOR\", \"APELLIDO_PATERNO_OPERADOR\", \"APELLIDO_MATERNO_OPERADOR\") VALUES(?, ?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query)) {

            pst.setInt(1, idOper);
            pst.setString(2, name);
            pst.setString(3, lastName);
            pst.setString(4, lastName2);
            pst.executeUpdate();
            return ("successfully completed");
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertOper$\t" + ex.getClass().getName() + "\t" + ex.getMessage());
            return (ex.getMessage());
        }
    }
    
    public String insertRadioOper(int idRadioOper, String name, String lastName, String lastName2) {
        String query = "INSERT INTO \"RADIO_OPERADOR\"(\"PK_ID_RADIO_OPERADOR\", \"NOMBRE_RADIO_OPERADOR\", \"APELLIDO_PATERNO_RADIO_OPERADOR\", \"APELLIDO_MATERNO_RADIO_OPERADOR\") VALUES(?, ?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query)) {

            pst.setInt(1, idRadioOper);
            pst.setString(2, name);
            pst.setString(3, lastName);
            pst.setString(4, lastName2);
            pst.executeUpdate();
            return ("successfully completed");
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertRadioOper$\t" + ex.getClass().getName() + "\t" + ex.getMessage());
            return (ex.getMessage());
        }
    }
    
    public String insertParamedic(int idParamedic, String name, String lastName, String lastName2) {
        String query = "INSERT INTO \"PARAMEDICO\"(\"PK_ID_PARAMEDICO\", \"NOMBRE_PARAMEDICO\", \"APELLIDO_PATERNO_PARAMEDICO\", \"APELLIDO_MATERNO_PARAMEDICO\") VALUES(?, ?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query)) {

            pst.setInt(1, idParamedic);
            pst.setString(2, name);
            pst.setString(3, lastName);
            pst.setString(4, lastName2);
            pst.executeUpdate();
            return ("successfully completed");
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertParamedic$\t" + ex.getClass().getName() + "\t" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public void consultOper() {
        try {
            // Se hara una consulta  de la tabla CDS y Cantante, y se mandara a imprimir.
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"OPERADOR\" "
                    + "ORDER BY \"PK_ID_OPERADOR\" ASC;");
            System.out.println("");
            while (rs.next()) {
                int id = rs.getInt("PK_ID_OPERADOR");
                String nom = rs.getString("NOMBRE_OPERADOR");
                String lastName = rs.getString("APELLIDO_PATERNO_OPERADOR");
                String lastName2 = rs.getString("APELLIDO_MATERNO_OPERADOR");

                System.out.println("[ FILA #" + id + " ]\n"
                        + "Nombre del operador: " + nom + "\n"
                        + "Apellido paterno: " + lastName + "\n"
                        + "Apellido materno: " + lastName2 + "\n");
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
    }
    
    public void consultRadioOper() {
        try {
            // Se hara una consulta  de la tabla CDS y Cantante, y se mandara a imprimir.
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"RADIO_OPERADOR\" "
                    + "ORDER BY \"PK_ID_RADIO_OPERADOR\" ASC;");
            System.out.println("");
            while (rs.next()) {
                int id = rs.getInt("PK_ID_RADIO_OPERADOR");
                String nom = rs.getString("NOMBRE_RADIO_OPERADOR");
                String lastName = rs.getString("APELLIDO_PATERNO_RADIO_OPERADOR");
                String lastName2 = rs.getString("APELLIDO_MATERNO_RADIO_OPERADOR");

                System.out.println("[ FILA #" + id + " ]\n"
                        + "Nombre del radio operador: " + nom + "\n"
                        + "Apellido paterno: " + lastName + "\n"
                        + "Apellido materno: " + lastName2 + "\n");
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
    }
    
    public void consultParamedic() {
        try {
            // Se hara una consulta  de la tabla CDS y Cantante, y se mandara a imprimir.
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PARAMEDICO\" "
                    + "ORDER BY \"PK_ID_PARAMEDICO\" ASC;");
            System.out.println("");
            while (rs.next()) {
                int id = rs.getInt("PK_ID_PARAMEDICO");
                String nom = rs.getString("NOMBRE_PARAMEDICO");
                String lastName = rs.getString("APELLIDO_PATERNO_PARAMEDICO");
                String lastName2 = rs.getString("APELLIDO_MATERNO_PARAMEDICO");

                System.out.println("[ FILA #" + id + " ]\n"
                        + "Nombre del paramedico: " + nom + "\n"
                        + "Apellido paterno: " + lastName + "\n"
                        + "Apellido materno: " + lastName2 + "\n");
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
    }
}
