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
        //System.out.println("ConxDB");
        try {
            Class.forName("org.postgresql.Driver");
            this.c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CtrlAmbDB", "postgres", "admin");
            System.out.println("Opened database successfully");
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            System.err.println("ConxDB/ConxDB$\t" + e.getClass().getName() + "\t" + e.getMessage());
            frameInsert.dispose();
        }
    }

    public ConxDB() {
        //System.out.println("ConxDB");
        try {
            Class.forName("org.postgresql.Driver");
            this.c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CtrlAmbDB", "postgres", "admin");
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

    public String insertAdmin(String user, String pass) {
        String query = "INSERT INTO \"ADMIN\"(\"USER\", \"PASS\") VALUES(?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query)) {

            pst.setString(1, user);
            pst.setString(2, pass);
            pst.executeUpdate();
            return ("done");
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertOper$\n" + ex.getClass().getName() + "\n" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertAmbulance(int num, int km) {
        String query = "INSERT INTO \"AMBULANCIA\"(\"NUMERO_AMBULANCIA\", \"KM_AMBULANCIA\") VALUES(?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query)) {

            pst.setInt(1, num);
            pst.setInt(2, km);
            pst.executeUpdate();
            return ("successfully completed");
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertOper$\n" + ex.getClass().getName() + "\n" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertOper(String name, String lastName, String lastName2) {
        String query = "INSERT INTO \"OPERADOR\"(\"NOMBRE_OPERADOR\", \"APELLIDO_PATERNO_OPERADOR\", \"APELLIDO_MATERNO_OPERADOR\") VALUES(?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query)) {

            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setString(3, lastName2);
            pst.executeUpdate();
            return ("successfully completed");
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertOper$\n" + ex.getClass().getName() + "\n" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertRadioOper(String name, String lastName, String lastName2) {
        String query = "INSERT INTO \"RADIO_OPERADOR\"(\"NOMBRE_RADIO_OPERADOR\", \"APELLIDO_PATERNO_RADIO_OPERADOR\", \"APELLIDO_MATERNO_RADIO_OPERADOR\") VALUES(?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query)) {

            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setString(3, lastName2);
            pst.executeUpdate();
            return ("successfully completed");
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertRadioOper$\t" + ex.getClass().getName() + "\t" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertParamedic(String name, String lastName, String lastName2) {
        String query = "INSERT INTO \"PARAMEDICO\"(\"NOMBRE_PARAMEDICO\", \"APELLIDO_PATERNO_PARAMEDICO\", \"APELLIDO_MATERNO_PARAMEDICO\") VALUES(?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query)) {

            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setString(3, lastName2);
            pst.executeUpdate();
            return ("successfully completed");
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertParamedic$\t" + ex.getClass().getName() + "\t" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertPatient(String name, String lastName, String lastName2, int ageOld, String sex) {
        String query = "INSERT INTO \"PACIENTE\"(\"NOMBRE_PACIENTE\", \"APELLIDO_PATERNO_PACIENTE\", "
                + "\"APELLIDO_MATERNO_PACIENTE\", \"EDAD_PACIENTE\", \"SEXO_PACIENTE\") VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setString(3, lastName2);
            pst.setInt(4, ageOld);
            pst.setString(5, sex);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("PC#" + id);
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertParamedic$\t" + ex.getClass().getName() + "\t" + ex.getMessage());
            return (ex.getMessage());
        }
    }
    
    public String insertPatient(String name, String lastName, String lastName2, int ageOld, 
            String sex, String trauma, String motivo, String padecimiento, String medicamento, 
            String eventoPrevio, String obstetricoTipe, int obstetricoMonthes) {
        String query = "INSERT INTO \"PACIENTE\"(\"NOMBRE_PACIENTE\", \"APELLIDO_PATERNO_PACIENTE\", "
                + "\"APELLIDO_MATERNO_PACIENTE\", \"EDAD_PACIENTE\", \"SEXO_PACIENTE\", \"TIPO_TRAUMA_PACIENTE\", "
                + "\"MOTIVO_ENFERMO_PACIENTE\", \"PADECIMIENTO_ENFERMO_PACIENTE\", \"MEDICAMENTO_ENFERMO_PACIENTE\", "
                + "\"EVENTO_PREVIO_ENFERMO_PACIENTE\", \"TIPO_OBSTETRICO_PACIENTE\", \"MESES_OBSTETRICO_PACIENTE\") "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setString(3, lastName2);
            pst.setInt(4, ageOld);
            pst.setString(5, sex);
            pst.setString(6, trauma);
            pst.setString(7, motivo);
            pst.setString(8, padecimiento);
            pst.setString(9, medicamento);
            pst.setString(10, eventoPrevio);
            pst.setString(11, obstetricoTipe);
            pst.setInt(12, obstetricoMonthes);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("PC#" + id);
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertParamedic$\t" + ex.getClass().getName() + "\t" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String consultAdmin() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"ADMIN\" "
                    + "ORDER BY \"USER\" ASC;");
            while (rs.next()) {
                String user = rs.getString("USER");

                resp += (user + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultAdmin(String user) {
        String pass = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"ADMIN\" "
                    + "WHERE \"USER\"= '" + user + "';");
            while (rs.next()) {
                pass = rs.getString("PASS");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        return pass;
    }

    public String consultAmbulance() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"AMBULANCIA\" "
                    + "ORDER BY \"PK_ID_AMBULANCIA\" ASC;");
            while (rs.next()) {
                int id = rs.getInt("PK_ID_AMBULANCIA");
                String num = rs.getString("NUMERO_AMBULANCIA");
                String kmAmbulance = rs.getString("KM_AMBULANCIA");

                resp += ("AB#" + id + " Numero:" + num + "  con " + kmAmbulance + "km recorridos\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultAmbulance(int id) {
        String resp = "Error";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"AMBULANCIA\" "
                    + "WHERE \"PK_ID_AMBULANCIA\"= '" + id + "';");
            while (rs.next()) {
                String num = rs.getString("NUMERO_AMBULANCIA");
                String kmAmbulance = rs.getString("KM_AMBULANCIA");

                resp = ("ID #" + id + " numero de la ambulancia:" + num + " Km de la ambulancia:" + kmAmbulance);
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultAmbulanceNum() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"AMBULANCIA\" "
                    + "ORDER BY \"PK_ID_AMBULANCIA\" ASC;");
            while (rs.next()) {
                int id = rs.getInt("PK_ID_AMBULANCIA");
                String num = rs.getString("NUMERO_AMBULANCIA");

                resp += ("AB#" + id + " Numero:" + num + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public int consultAmbulanceKm(int id) {
        int kmAmbulance = 0;
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"AMBULANCIA\" "
                    + "WHERE \"PK_ID_AMBULANCIA\"= '" + id + "';");
            while (rs.next()) {
                kmAmbulance = rs.getInt("KM_AMBULANCIA");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        return kmAmbulance;
    }

    public String consultOper() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"OPERADOR\" "
                    + "ORDER BY \"PK_ID_OPERADOR\" ASC;");
            while (rs.next()) {
                int id = rs.getInt("PK_ID_OPERADOR");
                String nom = rs.getString("NOMBRE_OPERADOR");
                String lastName = rs.getString("APELLIDO_PATERNO_OPERADOR");
                String lastName2 = rs.getString("APELLIDO_MATERNO_OPERADOR");

                resp += ("OP#" + id + " " + nom + " " + lastName + " " + lastName2 + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultOper(int id) {
        String resp = "Error";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"OPERADOR\" "
                    + "WHERE \"PK_ID_OPERADOR\"= '" + id + "';");
            while (rs.next()) {
                //id = rs.getInt("PK_ID_OPERADOR");
                String nom = rs.getString("NOMBRE_OPERADOR");
                String lastName = rs.getString("APELLIDO_PATERNO_OPERADOR");
                String lastName2 = rs.getString("APELLIDO_MATERNO_OPERADOR");

                resp = (nom + " " + lastName + " " + lastName2);
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultRadioOper() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"RADIO_OPERADOR\" "
                    + "ORDER BY \"PK_ID_RADIO_OPERADOR\" ASC;");
            while (rs.next()) {
                int id = rs.getInt("PK_ID_RADIO_OPERADOR");
                String nom = rs.getString("NOMBRE_RADIO_OPERADOR");
                String lastName = rs.getString("APELLIDO_PATERNO_RADIO_OPERADOR");
                String lastName2 = rs.getString("APELLIDO_MATERNO_RADIO_OPERADOR");

                resp += ("RO#" + id + " " + nom + " " + lastName + " " + lastName2 + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultRadioOper(int id) {
        String resp = "Error";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"RADIO_OPERADOR\" "
                    + "WHERE \"PK_ID_RADIO_OPERADOR\"= '" + id + "';");
            while (rs.next()) {
                //id = rs.getInt("PK_ID_OPERADOR");
                String nom = rs.getString("NOMBRE_RADIO_OPERADOR");
                String lastName = rs.getString("APELLIDO_PATERNO_RADIO_OPERADOR");
                String lastName2 = rs.getString("APELLIDO_MATERNO_RADIO_OPERADOR");

                resp = (nom + " " + lastName + " " + lastName2);
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultParamedic() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PARAMEDICO\" "
                    + "ORDER BY \"PK_ID_PARAMEDICO\" ASC;");
            while (rs.next()) {
                int id = rs.getInt("PK_ID_PARAMEDICO");
                String nom = rs.getString("NOMBRE_PARAMEDICO");
                String lastName = rs.getString("APELLIDO_PATERNO_PARAMEDICO");
                String lastName2 = rs.getString("APELLIDO_MATERNO_PARAMEDICO");

                resp += ("PM#" + id + " " + nom + " " + lastName + " " + lastName2 + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultParamedic(int id) {
        String resp = "Error";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PARAMEDICO\" "
                    + "WHERE \"PK_ID_PARAMEDICO\"= '" + id + "';");
            while (rs.next()) {
                //id = rs.getInt("PK_ID_OPERADOR");
                String nom = rs.getString("NOMBRE_PARAMEDICO");
                String lastName = rs.getString("APELLIDO_PATERNO_PARAMEDICO");
                String lastName2 = rs.getString("APELLIDO_MATERNO_PARAMEDICO");

                resp = (nom + " " + lastName + " " + lastName2);
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultPatient() {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PACIENTE\" "
                    + "ORDER BY \"PK_ID_PACIENTE\" ASC;");
            while (rs.next()) {
                int id = rs.getInt("PK_ID_PACIENTE");
                String nom = rs.getString("NOMBRE_PACIENTE");
                String lastName = rs.getString("APELLIDO_PATERNO_PACIENTE");
                String lastName2 = rs.getString("APELLIDO_MATERNO_PACIENTE");
                int ageOld = rs.getInt("EDAD_PACIENTE");
                String sex = rs.getString("SEXO_PACIENTE");

                resp += ("PC#" + id + " " + nom + " " + lastName + " " + lastName2 + " " + ageOld + "años " + sex + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String deleteAmbulance(int id) {
        String SQL = "DELETE FROM \"AMBULANCIA\" WHERE \"PK_ID_AMBULANCIA\" = ?";
        try (PreparedStatement pstmt = c.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 1) {
                return ("successfully completed");
            } else {
                return ("not found");
            }
        } catch (SQLException ex) {
            return (ex.getMessage());
        }
    }

    public String deleteOper(int id) {
        String SQL = "DELETE FROM \"OPERADOR\" WHERE \"PK_ID_OPERADOR\" = ?";
        try (PreparedStatement pstmt = c.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 1) {
                return ("successfully completed");
            } else {
                return ("not found");
            }
        } catch (SQLException ex) {
            return (ex.getMessage());
        }
    }

    public String deleteRadioOper(int id) {
        String SQL = "DELETE FROM \"RADIO_OPERADOR\" WHERE \"PK_ID_RADIO_OPERADOR\" = ?";
        try (PreparedStatement pstmt = c.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 1) {
                return ("successfully completed");
            } else {
                return ("not found");
            }
        } catch (SQLException ex) {
            return (ex.getMessage());
        }
    }

    public String deleteParamedic(int id) {
        String SQL = "DELETE FROM \"PARAMEDICO\" WHERE \"PK_ID_PARAMEDICO\" = ?";
        try (PreparedStatement pstmt = c.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 1) {
                return ("successfully completed");
            } else {
                return ("not found");
            }
        } catch (SQLException ex) {
            return (ex.getMessage());
        }
    }
    
    public String deletePatient(int id) {
        String SQL = "DELETE FROM \"PACIENTE\" WHERE \"PK_ID_PACIENTE\" = ?";
        try (PreparedStatement pstmt = c.prepareStatement(SQL)) {
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() == 1) {
                return ("successfully completed");
            } else {
                return ("not found");
            }
        } catch (SQLException ex) {
            return (ex.getMessage());
        }
    }
}
