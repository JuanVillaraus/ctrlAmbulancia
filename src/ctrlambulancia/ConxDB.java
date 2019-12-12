/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.sql.*;
import static java.sql.Types.NULL;
import java.util.*;
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
        try {
            Class.forName("org.postgresql.Driver");
            this.c = DriverManager.getConnection("jdbc:postgresql://192.168.1.101:5432/CtrlAmbDB", "admincrm", "CRM*1975*dvb");
//            this.c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CtrlAmbDB", "postgres", "admin");
            System.out.println("Opened database successfully");
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            System.err.println("ConxDB/ConxDB$\t" + e.getClass().getName() + "\t" + e.getMessage());
            frameInsert.dispose();
        }
    }

    public ConxDB() {
        try {
            Class.forName("org.postgresql.Driver");
            this.c = DriverManager.getConnection("jdbc:postgresql://192.168.1.101:5432/CtrlAmbDB", "admincrm", "CRM*1975*dvb");
//            this.c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CtrlAmbDB", "postgres", "admin");
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

        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, num);
            pst.setInt(2, km);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("AB#" + id);
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertOper$\n" + ex.getClass().getName() + "\n" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertOper(String name, String lastName, String lastName2) {
        String query = "INSERT INTO \"OPERADOR\"(\"NOMBRE_OPERADOR\", \"APELLIDO_PATERNO_OPERADOR\", "
                + "\"APELLIDO_MATERNO_OPERADOR\") VALUES(?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setString(3, lastName2);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("OP#" + id);
//            return ("successfully completed");
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertOper$\n" + ex.getClass().getName() + "\n" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertRadioOper(String name, String lastName, String lastName2) {
        String query = "INSERT INTO \"RADIO_OPERADOR\"(\"NOMBRE_RADIO_OPERADOR\", \"APELLIDO_PATERNO_RADIO_OPERADOR\", "
                + "\"APELLIDO_MATERNO_RADIO_OPERADOR\") VALUES(?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setString(3, lastName2);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("RO#" + id);
//            return ("successfully completed");
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertRadioOper$\t" + ex.getClass().getName() + "\t" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertParamedic(String name, String lastName, String lastName2) {
        String query = "INSERT INTO \"PARAMEDICO\"(\"NOMBRE_PARAMEDICO\", \"APELLIDO_PATERNO_PARAMEDICO\", "
                + "\"APELLIDO_MATERNO_PARAMEDICO\") VALUES(?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setString(3, lastName2);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("PM#" + id);
//            return ("successfully completed");
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

    public String insertPatient(String name, String lastName, String lastName2, int ageOld, String sex,
            String status, String numFrap, int idEmergency, String trauma, String motivo, String padecimiento,
            String medicamento, String eventoPrevio, String obstetricoTipe, int obstetricoMonthes) {
        String query = "INSERT INTO \"PACIENTE\"(\"NOMBRE_PACIENTE\", \"APELLIDO_PATERNO_PACIENTE\", "
                + "\"APELLIDO_MATERNO_PACIENTE\", \"EDAD_PACIENTE\", \"SEXO_PACIENTE\", \"ESTADO_PACIENTE\", "
                + "\"NUM_FRAP_PACIENTE\", \"ID_EMERGENCIA\", \"TRAUMA_TIPO_PACIENTE\", \"MOTIVO_ENFERMO_PACIENTE\", "
                + "\"PADECIMIENTO_ENFERMO_PACIENTE\", \"MEDICAMENTO_ENFERMO_PACIENTE\", "
                + "\"EVENTO_PREVIO_ENFERMO_PACIENTE\", \"TIPO_OBSTETRICO_PACIENTE\", \"MESES_OBSTETRICO_PACIENTE\") "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setString(3, lastName2);
            pst.setInt(4, ageOld);
            pst.setString(5, sex);
            pst.setString(6, status);
            pst.setString(7, numFrap);
            pst.setInt(8, idEmergency);
            pst.setString(9, trauma);
            pst.setString(10, motivo);
            pst.setString(11, padecimiento);
            pst.setString(12, medicamento);
            pst.setString(13, eventoPrevio);
            pst.setString(14, obstetricoTipe);
            pst.setInt(15, obstetricoMonthes);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("PC#" + id);
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertPatient$\t" + ex.getClass().getName() + "\t" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertEmergency(String dir, String entre, String ref, String col, String del, String nameApplicant,
            String resultado, String transfer, int priorityTransfer, int alive, int deads, int idParamedic, int idOper,
            int idRadioOper, int idAmbulance, String base, String operVoluntary, String paramedicVoluntary, String timeCall,
            String timeDeparture, String timeArrival, String timeTransfer, String timeHospital, String timeComeback, String note,
            String tipeCallmain, String callmain, int kmTraveled, String folio) {
        String query = "INSERT INTO \"EMERGENCIA\"(\"DIR_EMERGENCIA\", \"ENTRE_EMERGENCIA\", \"REF_EMERGENCIA\", \"COL_EMERGENCIA\", "
                + "\"DEL_EMERGENCIA\", \"NOMBRE_SOLICITANTE_EMERGENCIA\", \"RESULTADO_EMERGENCIA\", \"TRASLADO_EMERGENCIA\", "
                + "\"PRIORIDAD_TRASLADO_EMERGENCIA\", \"NUMERO_PACIENTES_EMERGENCIA\", \"NUMERO_MUERTOS_EMERGENCIA\", "
                + "\"ID_PARAMEDICO_EMERGENCIA\", \"ID_OPERADOR_EMERGENCIA\", \"ID_RADIO_OPERADOR_EMERGENCIA\", "
                + "\"ID_AMBULANCIA_EMERGENCIA\", \"BASE_EMERGENCIA\", \"OPERADOR_VOLUNTARIO_EMERGENCIA\", \"PARAMEDICO_VOLUNTARIO_EMERGENCIA\", "
                + "\"HORA_LLAMADA_EMERGENCIA\", \"HORA_SALIDA_EMERGENCIA\", \"HORA_LLEGADA_EMERGENCIA\", \"HORA_TRASLADO_EMERGENCIA\", "
                + "\"HORA_HOSPITAL_EMERGENCIA\", \"HORA_BASE_EMERGENCIA\", \"OBSERVACION_EMERGENCIA\", \"LLAMADA_RECIBIDA_EMERGENCIA\", "
                + "\"NUMERO_LLAMADA_RECIBIDA_EMERGENCIA\", \"KM_RECORRIDO_EMERGENCIA\", \"FOLIO_EMERGENCIA\") "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pst = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, dir);
            pst.setString(2, entre);
            pst.setString(3, ref);
            pst.setString(4, col);
            pst.setString(5, del);
            pst.setString(6, nameApplicant);
            pst.setString(7, resultado);
            pst.setString(8, transfer);
            pst.setInt(9, priorityTransfer);
            pst.setInt(10, alive);
            pst.setInt(11, deads);
            pst.setInt(12, idParamedic);
            pst.setInt(13, idOper);
            pst.setInt(14, idRadioOper);
            pst.setInt(15, idAmbulance);
            pst.setString(16, base);
            pst.setString(17, operVoluntary);
            pst.setString(18, paramedicVoluntary);
            pst.setTimestamp(19, Timestamp.valueOf(timeCall));
            pst.setTimestamp(20, Timestamp.valueOf(timeDeparture));
            pst.setTimestamp(21, Timestamp.valueOf(timeArrival));
            pst.setTimestamp(22, Timestamp.valueOf(timeTransfer));
            pst.setTimestamp(23, Timestamp.valueOf(timeHospital));
            pst.setTimestamp(24, Timestamp.valueOf(timeComeback));
            pst.setString(25, note);
            pst.setString(26, tipeCallmain);
            pst.setString(27, callmain);
            pst.setInt(28, kmTraveled);
            pst.setString(29, folio);
            pst.executeUpdate();
            Long id = null;
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            return ("EM#" + id);
        } catch (SQLException ex) {
            System.err.println("ConxDB/insertEmergencyReport$\t" + ex.getClass().getName() + "\t" + ex.getMessage());
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
        String resp = "";
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

    public String consultAmbulanceNum(int id) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"AMBULANCIA\" "
                    + "WHERE \"PK_ID_AMBULANCIA\"= '" + id + "';");
            while (rs.next()) {
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
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"OPERADOR\" "
                    + "WHERE \"PK_ID_OPERADOR\"= '" + id + "';");
            while (rs.next()) {
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

    public String[] consultOperArray(int id) {
        String[] employee = new String[4];
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"OPERADOR\" "
                    + "WHERE \"PK_ID_OPERADOR\"= '" + id + "';");
            while (rs.next()) {
                employee[0] = "" + id;
                employee[1] = rs.getString("NOMBRE_OPERADOR");
                employee[2] = rs.getString("APELLIDO_PATERNO_OPERADOR");
                employee[3] = rs.getString("APELLIDO_MATERNO_OPERADOR");
            }

            rs.close();
            st.close();
            return employee;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            employee[0] = "Error";
            employee[1] = "ConxDB/Consulta$\t" + e.getClass().getName();
            employee[2] = e.getMessage();
            return employee;
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
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"RADIO_OPERADOR\" "
                    + "WHERE \"PK_ID_RADIO_OPERADOR\"= '" + id + "';");
            while (rs.next()) {
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

    public String[] consultRadioOperArray(int id) {
        String[] employee = new String[4];
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"RADIO_OPERADOR\" "
                    + "WHERE \"PK_ID_RADIO_OPERADOR\"= '" + id + "';");
            while (rs.next()) {
                employee[0] = "" + id;
                employee[1] = rs.getString("NOMBRE_RADIO_OPERADOR");
                employee[2] = rs.getString("APELLIDO_PATERNO_RADIO_OPERADOR");
                employee[3] = rs.getString("APELLIDO_MATERNO_RADIO_OPERADOR");

            }

            rs.close();
            st.close();
            return employee;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            employee[0] = "Error";
            employee[1] = "ConxDB/Consulta$\t" + e.getClass().getName();
            employee[2] = e.getMessage();
            return employee;
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
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PARAMEDICO\" "
                    + "WHERE \"PK_ID_PARAMEDICO\"= '" + id + "';");
            while (rs.next()) {
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

    public String[] consultParamedicArray(int id) {
        String[] employee = new String[4];
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PARAMEDICO\" "
                    + "WHERE \"PK_ID_PARAMEDICO\"= '" + id + "';");
            while (rs.next()) {
                employee[0] = "" + id;
                employee[1] = rs.getString("NOMBRE_PARAMEDICO");
                employee[2] = rs.getString("APELLIDO_PATERNO_PARAMEDICO");
                employee[3] = rs.getString("APELLIDO_MATERNO_PARAMEDICO");
            }

            rs.close();
            st.close();
            return employee;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            employee[0] = "Error";
            employee[1] = "ConxDB/Consulta$\t" + e.getClass().getName();
            employee[2] = e.getMessage();
            return employee;
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

    public String consultPatientAll() {
        String resp = "<HTML>";
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
                String trauma = rs.getString("TRAUMA_TIPO_PACIENTE");
                String motivo = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                String padecimiento = rs.getString("PADECIMIENTO_ENFERMO_PACIENTE");
                String medicamento = rs.getString("MEDICAMENTO_ENFERMO_PACIENTE");
                String eventoPrevio = rs.getString("EVENTO_PREVIO_ENFERMO_PACIENTE");
                String obstetrico = rs.getString("TIPO_OBSTETRICO_PACIENTE");
                String obstetricoMonthes = rs.getString("MESES_OBSTETRICO_PACIENTE");
                String status = rs.getString("ESTADO_PACIENTE");
                String frap = rs.getString("NUM_FRAP_PACIENTE");
                int idEmergency = rs.getInt("ID_EMERGENCIA");

                resp += "<b>[PC#" + id + "]<br>Nombre:</b> " + nom + " " + lastName + " " + lastName2 + "<br>"
                        + "<b>Edad:</b> " + ageOld + " años \t\t<b>Sexo:</b> " + sex + "<br>"
                        + "<b>Estado:</b> " + status + " <b>FRAP:</b> " + frap + " <b>idEmergencia:</b> " + idEmergency;
                if (!trauma.equals("") && trauma != null) {
                    resp += "<b>trauma:</b> " + trauma + "<br>";
                }
                if ((!motivo.equals("") && motivo != null) || (!padecimiento.equals("") && padecimiento != null)
                        || (!medicamento.equals("") && medicamento != null) || (!eventoPrevio.equals("") && eventoPrevio != null)) {
                    resp += "<b>ENFERMO motivo:</b> " + motivo + "\t\t<b>padecimiento:</b> " + padecimiento + "<br>"
                            + "<b>medicamento:</b> " + medicamento + "\t\t<b>evento previo:</b> " + eventoPrevio + "<br>";
                }
                if ((!obstetrico.equals("") && obstetrico != null) || (!obstetricoMonthes.equals("0"))) {
                    resp += "<b>OBTETRICO tipo:</b> " + obstetrico + "\t\t<b>meses:</b> " + obstetricoMonthes + "<br>";
                }
                resp += "<br>";
            }

            resp += "</HTML>";
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }
    
    public String consultPatientAll(String search) {
        String resp = "<HTML>";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PACIENTE\" "
                    + "WHERE \"NOMBRE_PACIENTE\" = '" + search + "' "
                    + "OR \"APELLIDO_PATERNO_PACIENTE\" = '" + search + "' "
                    + "OR \"APELLIDO_MATERNO_PACIENTE\" = '" + search + "' ;");
            while (rs.next()) {
                int id = rs.getInt("PK_ID_PACIENTE");
                String name = rs.getString("NOMBRE_PACIENTE");
                String lastName = rs.getString("APELLIDO_PATERNO_PACIENTE");
                String lastName2 = rs.getString("APELLIDO_MATERNO_PACIENTE");
                int ageOld = rs.getInt("EDAD_PACIENTE");
                String sex = rs.getString("SEXO_PACIENTE");
                String trauma = rs.getString("TRAUMA_TIPO_PACIENTE");
                String motivo = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                String padecimiento = rs.getString("PADECIMIENTO_ENFERMO_PACIENTE");
                String medicamento = rs.getString("MEDICAMENTO_ENFERMO_PACIENTE");
                String eventoPrevio = rs.getString("EVENTO_PREVIO_ENFERMO_PACIENTE");
                String obstetrico = rs.getString("TIPO_OBSTETRICO_PACIENTE");
                String obstetricoMonthes = rs.getString("MESES_OBSTETRICO_PACIENTE");
                String status = rs.getString("ESTADO_PACIENTE");
                String frap = rs.getString("NUM_FRAP_PACIENTE");
                int idEmergency = rs.getInt("ID_EMERGENCIA");

                resp += "<b>[PC#" + id + "]<br>Nombre:</b> " + name + " " + lastName + " " + lastName2 + "<br>"
                        + "<b>Edad:</b> " + ageOld + " años \t\t<b>Sexo:</b> " + sex + "<br>"
                        + "<b>Estado:</b> " + status + " <b>FRAP:</b> " + frap + " <b>idEmergencia:</b> " + idEmergency;
                if (!trauma.equals("") && trauma != null) {
                    resp += "<b>trauma:</b> " + trauma + "<br>";
                }
                if ((!motivo.equals("") && motivo != null) || (!padecimiento.equals("") && padecimiento != null)
                        || (!medicamento.equals("") && medicamento != null) || (!eventoPrevio.equals("") && eventoPrevio != null)) {
                    resp += "<b>ENFERMO motivo:</b> " + motivo + "\t\t<b>padecimiento:</b> " + padecimiento + "<br>"
                            + "<b>medicamento:</b> " + medicamento + "\t\t<b>evento previo:</b> " + eventoPrevio + "<br>";
                }
                if ((!obstetrico.equals("") && obstetrico != null) || (!obstetricoMonthes.equals("0"))) {
                    resp += "<b>OBTETRICO tipo:</b> " + obstetrico + "\t\t<b>meses:</b> " + obstetricoMonthes + "<br>";
                }
                resp += "<br>";
            }

            resp += "</HTML>";
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultPatient(int id) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PACIENTE\" "
                    + "WHERE \"PK_ID_PACIENTE\"= '" + id + "';");
            while (rs.next()) {
                String nom = rs.getString("NOMBRE_PACIENTE");
                String lastName = rs.getString("APELLIDO_PATERNO_PACIENTE");
                String lastName2 = rs.getString("APELLIDO_MATERNO_PACIENTE");
                int ageOld = rs.getInt("EDAD_PACIENTE");
                String sex = rs.getString("SEXO_PACIENTE");
                String trauma = rs.getString("TRAUMA_TIPO_PACIENTE");
                String motivo = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                String padecimiento = rs.getString("PADECIMIENTO_ENFERMO_PACIENTE");
                String medicamento = rs.getString("MEDICAMENTO_ENFERMO_PACIENTE");
                String eventoPrevio = rs.getString("EVENTO_PREVIO_ENFERMO_PACIENTE");
                String obstetrico = rs.getString("TIPO_OBSTETRICO_PACIENTE");
                String obstetricoMonthes = rs.getString("MESES_OBSTETRICO_PACIENTE");

                resp += ("[PC#" + id + "]\nNombre: " + nom + " " + lastName + " " + lastName2 + "\n"
                        + "Edad: " + ageOld + " años \t\tSexo: " + sex + "\n"
                        + "trauma: " + trauma + "\n"
                        + "ENFERMO motivo: " + motivo + "\t\tpadecimiento: " + padecimiento + "\n"
                        + "medicamento: " + medicamento + "\t\tevento previo: " + eventoPrevio + "\n"
                        + "OBTETRICO tipo: " + obstetrico + "\t\tmeses: " + obstetricoMonthes + "\n\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }
    
    public String consultFRAP(String frap) {
        String resp = "<HTML>";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PACIENTE\" "
                    + "WHERE \"NUM_FRAP_PACIENTE\"= '" + frap + "';");
            while (rs.next()) {
                int id = rs.getInt("PK_ID_PACIENTE");
                String nom = rs.getString("NOMBRE_PACIENTE");
                String lastName = rs.getString("APELLIDO_PATERNO_PACIENTE");
                String lastName2 = rs.getString("APELLIDO_MATERNO_PACIENTE");
                int ageOld = rs.getInt("EDAD_PACIENTE");
                String sex = rs.getString("SEXO_PACIENTE");
                String trauma = rs.getString("TRAUMA_TIPO_PACIENTE");
                String motivo = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                String padecimiento = rs.getString("PADECIMIENTO_ENFERMO_PACIENTE");
                String medicamento = rs.getString("MEDICAMENTO_ENFERMO_PACIENTE");
                String eventoPrevio = rs.getString("EVENTO_PREVIO_ENFERMO_PACIENTE");
                String obstetrico = rs.getString("TIPO_OBSTETRICO_PACIENTE");
                String obstetricoMonthes = rs.getString("MESES_OBSTETRICO_PACIENTE");
                String status = rs.getString("ESTADO_PACIENTE");
//                String frap = rs.getString("NUM_FRAP_PACIENTE");
                int idEmergency = rs.getInt("ID_EMERGENCIA");

                resp += "<b>[PC#" + id + "]<br>Nombre:</b> " + nom + " " + lastName + " " + lastName2 + "<br>"
                        + "<b>Edad:</b> " + ageOld + " años \t\t<b>Sexo:</b> " + sex + "<br>"
                        + "<b>Estado:</b> " + status + " <b>FRAP:</b> " + frap + " <b>idEmergencia:</b> " + idEmergency+"</br>";
                if (!trauma.equals("") && trauma != null) {
                    resp += "<b>trauma:</b> " + trauma + "<br>";
                }
                if ((!motivo.equals("") && motivo != null) || (!padecimiento.equals("") && padecimiento != null)
                        || (!medicamento.equals("") && medicamento != null) || (!eventoPrevio.equals("") && eventoPrevio != null)) {
                    resp += "<b>ENFERMO motivo:</b> " + motivo + "\t\t<b>padecimiento:</b> " + padecimiento + "<br>"
                            + "<b>medicamento:</b> " + medicamento + "\t\t<b>evento previo:</b> " + eventoPrevio + "<br>";
                }
                if ((!obstetrico.equals("") && obstetrico != null) || (!obstetricoMonthes.equals("0"))) {
                    resp += "<b>OBTETRICO tipo:</b> " + obstetrico + "\t\t<b>meses:</b> " + obstetricoMonthes + "<br>";
                }
                resp += "<br>";
            }

            resp += "</HTML>";
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String[][] consultPatientIdEmergncy(int idEmergency) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];

        String[][] patient = new String[list.size()][row.length];
        for (int i = 0;
                i < patient.length;
                i++) {
            for (int j = 0; j < patient[0].length; j++) {
                patient[i][j] = list.get(i)[j];
            }
        }
        return patient;
    }

    public String consultPatient(int idOpen, int idClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PACIENTE\" "
                    + "WHERE \"PK_ID_PACIENTE\" BETWEEN '" + idOpen + "' AND '" + idClose + "'");
            while (rs.next()) {
                int id = rs.getInt("PK_ID_PACIENTE");
                String nom = rs.getString("NOMBRE_PACIENTE");
                String lastName = rs.getString("APELLIDO_PATERNO_PACIENTE");
                String lastName2 = rs.getString("APELLIDO_MATERNO_PACIENTE");
                int ageOld = rs.getInt("EDAD_PACIENTE");
                String sex = rs.getString("SEXO_PACIENTE");
                String trauma = rs.getString("TRAUMA_TIPO_PACIENTE");
                String motivo = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                String padecimiento = rs.getString("PADECIMIENTO_ENFERMO_PACIENTE");
                String medicamento = rs.getString("MEDICAMENTO_ENFERMO_PACIENTE");
                String eventoPrevio = rs.getString("EVENTO_PREVIO_ENFERMO_PACIENTE");
                String obstetrico = rs.getString("TIPO_OBSTETRICO_PACIENTE");
                String obstetricoMonthes = rs.getString("MESES_OBSTETRICO_PACIENTE");

                resp += ("[PC#" + id + "]\nNombre: " + nom + " " + lastName + " " + lastName2 + "\n"
                        + "Edad: " + ageOld + " años \t\tSexo: " + sex + "\n"
                        + "trauma: " + trauma + "\n"
                        + "ENFERMO motivo: " + motivo + "\t\tpadecimiento: " + padecimiento + "\n"
                        + "medicamento: " + medicamento + "\t\tevento previo: " + eventoPrevio + "\n"
                        + "OBTETRICO tipo: " + obstetrico + "\t\tmeses: " + obstetricoMonthes + "\n\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/Consulta$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultPatient(String dateOpen, String dateClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT \"ID_PACIENTE_EMERGENCIA\" "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' ");
            while (rs.next()) {
                int idPatient = rs.getInt("ID_PACIENTE_EMERGENCIA");

                try {
                    Statement stPatient = c.createStatement();
                    ResultSet rsPatient = stPatient.executeQuery(""
                            + "SELECT * "
                            + "FROM \"PACIENTE\""
                            + "WHERE \"PK_ID_PACIENTE\"= '" + idPatient + "';");
                    while (rsPatient.next()) {
                        String nom = rsPatient.getString("NOMBRE_PACIENTE");
                        String lastName = rsPatient.getString("APELLIDO_PATERNO_PACIENTE");
                        String lastName2 = rsPatient.getString("APELLIDO_MATERNO_PACIENTE");
                        int ageOld = rsPatient.getInt("EDAD_PACIENTE");
                        String sex = rsPatient.getString("SEXO_PACIENTE");
                        String trauma = rsPatient.getString("TRAUMA_TIPO_PACIENTE");
                        String motivo = rsPatient.getString("MOTIVO_ENFERMO_PACIENTE");
                        String padecimiento = rsPatient.getString("PADECIMIENTO_ENFERMO_PACIENTE");
                        String medicamento = rsPatient.getString("MEDICAMENTO_ENFERMO_PACIENTE");
                        String eventoPrevio = rsPatient.getString("EVENTO_PREVIO_ENFERMO_PACIENTE");
                        String obstetrico = rsPatient.getString("TIPO_OBSTETRICO_PACIENTE");
                        String obstetricoMonthes = rsPatient.getString("MESES_OBSTETRICO_PACIENTE");

                        resp += ("[PC#" + idPatient + "]\nNombre: " + nom + " " + lastName + " " + lastName2 + "\n"
                                + "Edad: " + ageOld + " años \t\tSexo: " + sex + "\n"
                                + "trauma: " + trauma + "\n"
                                + "ENFERMO motivo: " + motivo + "\t\tpadecimiento: " + padecimiento + "\n"
                                + "medicamento: " + medicamento + "\t\tevento previo: " + eventoPrevio + "\n"
                                + "OBTETRICO tipo: " + obstetrico + "\t\tmeses: " + obstetricoMonthes + "\n\n");
                    }

                    rsPatient.close();
                    stPatient.close();
                } catch (Exception er) {
                    System.err.println("ConxDB/consultPatientDate/patient$\t" + er.getClass().getName() + "\t" + er.getMessage());
                    return er.getMessage();
                }
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/consultPatientDate$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergency() {
        String resp = "<HTML>";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "ORDER BY \"PK_ID_EMERGENCIA\" ASC;");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }

            resp += "</HTML>";
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String[][] consultEmergencyAll() {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[32];
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "ORDER BY \"PK_ID_EMERGENCIA\" ASC;");
            while (rs.next()) {
                row = new String[32];
                row[0] = "" + rs.getInt("PK_ID_EMERGENCIA");
                row[1] = rs.getString("DIR_EMERGENCIA");
                row[2] = rs.getString("ENTRE_EMERGENCIA");
                row[3] = rs.getString("REF_EMERGENCIA");
                row[4] = rs.getString("COL_EMERGENCIA");
                row[5] = rs.getString("DEL_EMERGENCIA");
                row[6] = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                row[7] = rs.getString("RESULTADO_EMERGENCIA");
                row[8] = rs.getString("TRASLADO_EMERGENCIA");
                row[9] = "" + rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                row[10] = "" + rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                row[11] = "" + rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                row[12] = "" + rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                row[13] = "" + rs.getInt("ID_OPERADOR_EMERGENCIA");
                row[14] = "" + rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                row[15] = "" + rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                row[16] = "" + rs.getInt("KM_RECORRIDO_EMERGENCIA");
                row[17] = rs.getString("BASE_EMERGENCIA");
                row[18] = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                row[19] = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                row[20] = rs.getString("HORA_LLAMADA_EMERGENCIA");
                row[21] = rs.getString("HORA_SALIDA_EMERGENCIA");
                row[22] = rs.getString("HORA_LLEGADA_EMERGENCIA");
                row[23] = rs.getString("HORA_TRASLADO_EMERGENCIA");
                row[24] = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                row[25] = rs.getString("HORA_BASE_EMERGENCIA");
                row[26] = rs.getString("OBSERVACION_EMERGENCIA");
                row[27] = rs.getString("LLAMADA_RECIBIDA_EMERGENCIA");
                row[28] = rs.getString("NUMERO_LLAMADA_RECIBIDA_EMERGENCIA");
                row[29] = rs.getString("FOLIO_EMERGENCIA");
                row[30] = rs.getString("TRASLADO_PAGADO_SALIDA_EMERGENCIA");
                row[31] = rs.getString("TRASLADO_PAGADO_DESTINO_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergencyAll/\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(list.get(i)[j] + "\t");
                data[i][j] = list.get(i)[j];
            }
            System.out.println();
        }
        return data;
    }
    
    public String[][] consultEmergencyAll(String dateOpen, String dateClose) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' "
                    + "ORDER BY \"PK_ID_EMERGENCIA\" ASC;");
            while (rs.next()) {
                row = new String[32];
                row[0] = "" + rs.getInt("PK_ID_EMERGENCIA");
                row[1] = rs.getString("DIR_EMERGENCIA");
                row[2] = rs.getString("ENTRE_EMERGENCIA");
                row[3] = rs.getString("REF_EMERGENCIA");
                row[4] = rs.getString("COL_EMERGENCIA");
                row[5] = rs.getString("DEL_EMERGENCIA");
                row[6] = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                row[7] = rs.getString("RESULTADO_EMERGENCIA");
                row[8] = rs.getString("TRASLADO_EMERGENCIA");
                row[9] = "" + rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                row[10] = "" + rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                row[11] = "" + rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                row[12] = "" + rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                row[13] = "" + rs.getInt("ID_OPERADOR_EMERGENCIA");
                row[14] = "" + rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                row[15] = "" + rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                row[16] = "" + rs.getInt("KM_RECORRIDO_EMERGENCIA");
                row[17] = rs.getString("BASE_EMERGENCIA");
                row[18] = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                row[19] = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                row[20] = rs.getString("HORA_LLAMADA_EMERGENCIA");
                row[21] = rs.getString("HORA_SALIDA_EMERGENCIA");
                row[22] = rs.getString("HORA_LLEGADA_EMERGENCIA");
                row[23] = rs.getString("HORA_TRASLADO_EMERGENCIA");
                row[24] = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                row[25] = rs.getString("HORA_BASE_EMERGENCIA");
                row[26] = rs.getString("OBSERVACION_EMERGENCIA");
                row[27] = rs.getString("LLAMADA_RECIBIDA_EMERGENCIA");
                row[28] = rs.getString("NUMERO_LLAMADA_RECIBIDA_EMERGENCIA");
                row[29] = rs.getString("FOLIO_EMERGENCIA");
                row[30] = rs.getString("TRASLADO_PAGADO_SALIDA_EMERGENCIA");
                row[31] = rs.getString("TRASLADO_PAGADO_DESTINO_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergencyAll/\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
//                System.out.print(list.get(i)[j] + "\t");
                data[i][j] = list.get(i)[j];
            }
//            System.out.println();
        }
        return data;
    }

    public String consultEmergency(String dateOpen, String dateClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' ");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergency(int idEmergency) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"PK_ID_EMERGENCIA\"= '" + idEmergency + "';");
            while (rs.next()) {
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String[] consultEditEmergency(int idEmergency) {
        String[] resp = new String[30];
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"PK_ID_EMERGENCIA\"= '" + idEmergency + "';");
            while (rs.next()) {
                resp[0] = rs.getString("DIR_EMERGENCIA");
                resp[1] = rs.getString("ENTRE_EMERGENCIA");
                resp[2] = rs.getString("REF_EMERGENCIA");
                resp[3] = rs.getString("COL_EMERGENCIA");
                resp[4] = rs.getString("DEL_EMERGENCIA");
                resp[5] = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                resp[6] = rs.getString("RESULTADO_EMERGENCIA");
                resp[7] = rs.getString("TRASLADO_EMERGENCIA");
                resp[8] = String.valueOf(rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA"));
                resp[9] = String.valueOf(rs.getInt("NUMERO_PACIENTES_EMERGENCIA"));
                resp[10] = String.valueOf(rs.getInt("NUMERO_MUERTOS_EMERGENCIA"));
                resp[11] = String.valueOf(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                resp[12] = String.valueOf(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                resp[13] = String.valueOf(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                resp[14] = String.valueOf(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                resp[15] = String.valueOf(rs.getInt("KM_RECORRIDO_EMERGENCIA"));
                resp[16] = rs.getString("BASE_EMERGENCIA");
                resp[17] = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                resp[18] = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                resp[19] = rs.getString("HORA_LLAMADA_EMERGENCIA");
                resp[20] = rs.getString("HORA_SALIDA_EMERGENCIA");
                resp[21] = rs.getString("HORA_LLEGADA_EMERGENCIA");
                resp[22] = rs.getString("HORA_TRASLADO_EMERGENCIA");
                resp[23] = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                resp[24] = rs.getString("HORA_BASE_EMERGENCIA");
                resp[25] = rs.getString("OBSERVACION_EMERGENCIA");
                resp[26] = rs.getString("LLAMADA_RECIBIDA_EMERGENCIA");
                resp[27] = rs.getString("NUMERO_LLAMADA_RECIBIDA_EMERGENCIA");
                resp[28] = rs.getString("KM_RECORRIDO_EMERGENCIA");
                resp[29] = rs.getString("FOLIO_EMERGENCIA");

            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return resp;
        }
    }

    public String consultEmergencyFrap(int frap) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"PK_ID_EMERGENCIA\"= '" + frap + "';");//Error------------------------------------------------------------------------------
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyResultado(String resultado) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"RESULTADO_EMERGENCIA\"= '" + resultado + "';");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyResultado(String resultado, String dateOpen, String dateClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"RESULTADO_EMERGENCIA\"= '" + resultado + "' "
                    + "AND  \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' ");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyTransfer(String transfer) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"TRASLADO_EMERGENCIA\"= '" + transfer + "';");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyTransfer(String transfer, String dateOpen, String dateClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"TRASLADO_EMERGENCIA\"= '" + transfer + "' "
                    + "AND  \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' ");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyPriorityTransfer(int priorityTransfer) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"PRIORIDAD_TRASLADO_EMERGENCIA\"= '" + priorityTransfer + "';");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyPriorityTransfer(int priorityTransfer, String dateOpen, String dateClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"PRIORIDAD_TRASLADO_EMERGENCIA\"= '" + priorityTransfer + "' "
                    + "AND  \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' ");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyOper(int idOper) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"ID_OPERADOR_EMERGENCIA\"= '" + idOper + "';");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyOper(int idOper, String dateOpen, String dateClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"ID_OPERADOR_EMERGENCIA\"= '" + idOper + "' "
                    + "AND  \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' ");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyRadioOper(int idRadioOper) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"ID_RADIO_OPERADOR_EMERGENCIA\"= '" + idRadioOper + "';");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyRadioOper(int idRadioOper, String dateOpen, String dateClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"ID_RADIO_OPERADOR_EMERGENCIA\"= '" + idRadioOper + "' "
                    + "AND  \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' ");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyParamedic(int idParamedic) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"ID_PARAMEDICO_EMERGENCIA\"= '" + idParamedic + "';");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
//                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyParamedic(int idParamedic, String dateOpen, String dateClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"ID_PARAMEDICO_EMERGENCIA\"= '" + idParamedic + "' "
                    + "AND  \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' ");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
//                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyAmbulance(int idAmbulance) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"ID_AMBULANCIA_EMERGENCIA\"= '" + idAmbulance + "';");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
//                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyAmbulance(int idAmbulance, String dateOpen, String dateClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"ID_AMBULANCIA_EMERGENCIA\"= '" + idAmbulance + "' "
                    + "AND  \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' ");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
//                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyDir(String dir) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"COL_EMERGENCIA\"= '" + dir + "';");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
//                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyDir(String dir, String dateOpen, String dateClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"DIR_EMERGENCIA\"= '" + dir + "' "
                    + "AND  \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' ");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
//                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyOperVoluntary(String operVoluntary) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"OPERADOR_VOLUNTARIO_EMERGENCIA\"= '" + operVoluntary + "';");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
//                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyOperVoluntary(String operVoluntary, String dateOpen, String dateClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"OPERADOR_VOLUNTARIO_EMERGENCIA\"= '" + operVoluntary + "' "
                    + "AND  \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' ");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
//                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyParamedicVoluntary(String paramedicVoluntary) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"PARAMEDICO_VOLUNTARIO_EMERGENCIA\"= '" + paramedicVoluntary + "';");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
//                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String consultEmergencyParamedicVoluntary(String paramedicVoluntary, String dateOpen, String dateClose) {
        String resp = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "WHERE \"PARAMEDICO_VOLUNTARIO_EMERGENCIA\"= '" + paramedicVoluntary + "' "
                    + "AND  \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' ");
            while (rs.next()) {
                int idEmergency = rs.getInt("PK_ID_EMERGENCIA");
                String dir = rs.getString("DIR_EMERGENCIA");
                String entre = rs.getString("ENTRE_EMERGENCIA");
                String ref = rs.getString("REF_EMERGENCIA");
                String col = rs.getString("COL_EMERGENCIA");
                String del = rs.getString("DEL_EMERGENCIA");
                String nameApplicant = rs.getString("NOMBRE_SOLICITANTE_EMERGENCIA");
                String transfer = rs.getString("TRASLADO_EMERGENCIA");
                String resultado = rs.getString("RESULTADO_EMERGENCIA");
                int priorityTransfer = rs.getInt("PRIORIDAD_TRASLADO_EMERGENCIA");
                int alive = rs.getInt("NUMERO_PACIENTES_EMERGENCIA");
                int deads = rs.getInt("NUMERO_MUERTOS_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int kmTraveled = rs.getInt("KM_RECORRIDO_EMERGENCIA");
                String base = rs.getString("BASE_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
//                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                resp += format(idEmergency, dir, entre, ref, col, del, nameApplicant,
                        resultado, transfer, priorityTransfer, alive, deads, idParamedic,
                        idOper, idRadioOper, idAmbulance, kmTraveled, base, paramedicVoluntary,
                        operVoluntary, timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital,
                        timeComeback, note);
            }
            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
    }

    public String format(int idEmergency, String dir, String entre, String ref, String col, String del, String nameApplicant,
            String resultado, String transfer, int priorityTransfer, int alive, int deads, int idParamedic,
            int idOper, int idRadioOper, int idAmbulance, int kmTraveled, String base, String paramedicVoluntary, String operVoluntary, String timeCall,
            String timeDeparture, String timeArrival, String timeTransfer, String timeHospital, String timeComeback, String note) {
        return ("<b>[EM#" + idEmergency + "]</b><br>"
                + "<b>Dirección:</b> " + dir + " \t\t<b>entre:</b> " + entre + "<br>"
                + "<b>Referencia:</b> " + ref + "<br>"
                + "<b>Colonia:</b> " + col + " \t\t<b>Delegación:</b> " + del + "<br>"
                + "<b>Nombre del solicitante:</b> " + nameApplicant + "<br>"
                + "<b>Resultado:</b> " + resultado + "<br>"
                + "<b>Traslado:</b> " + transfer + "\t\t<b>Prioridad:</b> " + priorityTransfer + "<br>"
                + "<b>Vivos:</b> " + alive + "\t\t<b>Muertos:</b> " + deads + "<br>"
                + consultInfoPatient(idEmergency)
                + "<b>Paramedico:</b> #" + idParamedic + "\t<b>Nombre:</b> " + consultNameParamedic(idParamedic) + "<br>"
                + "<b>Paramedico voluntario:</b> " + paramedicVoluntary + "<br>"
                + "<b>Operador:</b> #" + idOper + "\t<b>Nombre:</b> " + consultNameOper(idOper) + "<br>"
                + "<b>Operador voluntario:</b> " + operVoluntary + "<br>"
                + "<b>RadioOperador:</b> #" + idRadioOper + "\t<b>Nombre:</b> " + consultNameRadioOper(idRadioOper) + "<br>"
                + "<b>Ambulancia:</b> id:" + idAmbulance + "\t<b>Numero:</b> " + consultNumAmbulance(idAmbulance) + "\t<b>Km recorridas:</b> " + kmTraveled
                + "\t<b>Base:</b> " + base + "<br>"
                + "<b>hora de la llamada:</b> " + timeCall + "<br>"
                + "<b>hora de la salida:</b> " + timeDeparture + "<br>"
                + "<b>hora de la llegada:</b> " + timeArrival + "<br>"
                + "<b>hora de la traslado:</b> " + timeTransfer + "<br>"
                + "<b>hora de la hospital:</b> " + timeHospital + "<br>"
                + "<b>hora de la base:</b> " + timeComeback + "<br>"
                + "<b>Observaciones:</b> " + note + "<br>"
                + "<br>");
    }

    public String consultNamePatient(int idEmergency) {
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PACIENTE\""
                    + "WHERE \"PK_ID_EMERGENCIA\"= '" + idEmergency + "';");
            while (rs.next()) {
                return "Nombre: " + rs.getString("NOMBRE_PACIENTE") + " "
                        + rs.getString("APELLIDO_PATERNO_PACIENTE") + " "
                        + rs.getString("APELLIDO_MATERNO_PACIENTE");
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/patient$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
        return null;
    }

    public String consultInfoPatient(int idEmergency) {
        String infoPatient = "";
        String data = "";
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PACIENTE\""
                    + "WHERE \"ID_EMERGENCIA\"= '" + idEmergency + "';");
            while (rs.next()) {
                infoPatient += "<b>Paciente[" + rs.getInt("PK_ID_PACIENTE") + "]:</b> "
                        + rs.getString("NOMBRE_PACIENTE") + " "
                        + rs.getString("APELLIDO_PATERNO_PACIENTE") + " "
                        + rs.getString("APELLIDO_MATERNO_PACIENTE") + "</span><br>";
                data = "" + rs.getInt("EDAD_PACIENTE");
                if (data != null && !data.equals("")) {
                    infoPatient += "<b>Edad:</b> " + data + "<br>";
                }
                data = "" + rs.getString("SEXO_PACIENTE");
                if (data != null && !data.equals("")) {
                    infoPatient += "<b>Sexo:</b> " + data + "<br>";
                }
                data = "" + rs.getString("ESTADO_PACIENTE");
                if (data != null && !data.equals("")) {
                    infoPatient += "<b>Estado:</b> " + data + "<br>";
                }
                data = "" + rs.getString("NUM_FRAP_PACIENTE");
                if (data != null && !data.equals("")) {
                    infoPatient += "<b>FRAP:</b> " + data + "<br>";
                }
                data = rs.getString("TRAUMA_TIPO_PACIENTE");
                if (data != null && !data.equals("")) {
                    infoPatient += "<b>Trauma:</b> " + data + "<br>";
                }
                data = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                if (data != null && !data.equals("")) {
                    infoPatient += "<b>Motivo:</b> " + data + "<br>";
                }
                data = rs.getString("PADECIMIENTO_ENFERMO_PACIENTE");
                if (data != null && !data.equals("")) {
                    infoPatient += "<b>Padecimiento:</b> " + data + "<br>";
                }
                data = rs.getString("MEDICAMENTO_ENFERMO_PACIENTE");
                if (data != null && !data.equals("")) {
                    infoPatient += "<b>Medicamento:</b> " + data + "<br>";
                }
                data = rs.getString("EVENTO_PREVIO_ENFERMO_PACIENTE");
                if (data != null && !data.equals("")) {
                    infoPatient += "<b>Evento previo:</b> " + data + "<br>";
                }
                data = rs.getString("TIPO_OBSTETRICO_PACIENTE");
                if (data != null && !data.equals("")) {
                    infoPatient += "<b>Tipo Obstetrico:</b> " + rs.getString("TIPO_OBSTETRICO_PACIENTE") + "\t"
                            + rs.getInt("MESES_OBSTETRICO_PACIENTE") + " meses<br>";
                }
            }
            rs.close();
            st.close();
            return infoPatient;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/patient$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
//        return null;
    }

    public String consultNameParamedic(int idParamedic) {
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT \"NOMBRE_PARAMEDICO\",\"APELLIDO_PATERNO_PARAMEDICO\",\"APELLIDO_MATERNO_PARAMEDICO\" "
                    + "FROM \"EMERGENCIA\", \"PARAMEDICO\""
                    + "WHERE \"PK_ID_PARAMEDICO\"= '" + idParamedic + "';");
            while (rs.next()) {
                return rs.getString("NOMBRE_PARAMEDICO") + " "
                        + rs.getString("APELLIDO_PATERNO_PARAMEDICO") + " "
                        + rs.getString("APELLIDO_MATERNO_PARAMEDICO");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/paramedic$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
        return null;
    }

    public String consultNameOper(int idOper) {
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT \"NOMBRE_OPERADOR\",\"APELLIDO_PATERNO_OPERADOR\",\"APELLIDO_MATERNO_OPERADOR\" "
                    + "FROM \"EMERGENCIA\", \"OPERADOR\""
                    + "WHERE \"PK_ID_OPERADOR\"= '" + idOper + "';");
            while (rs.next()) {
                return rs.getString("NOMBRE_OPERADOR") + " "
                        + rs.getString("APELLIDO_PATERNO_OPERADOR") + " "
                        + rs.getString("APELLIDO_MATERNO_OPERADOR");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/oper$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
        return null;
    }

    public String consultNumAmbulance(int idAmbulance) {
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT \"NUMERO_AMBULANCIA\" "
                    + "FROM \"EMERGENCIA\", \"AMBULANCIA\""
                    + "WHERE \"PK_ID_AMBULANCIA\"= '" + idAmbulance + "';");
            while (rs.next()) {
                return rs.getString("NUMERO_AMBULANCIA");
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/Ambulance$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
        return null;
    }

    public String consultNameRadioOper(int idRadioOper) {
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT \"NOMBRE_RADIO_OPERADOR\",\"APELLIDO_PATERNO_RADIO_OPERADOR\",\"APELLIDO_MATERNO_RADIO_OPERADOR\" "
                    + "FROM \"EMERGENCIA\", \"RADIO_OPERADOR\""
                    + "WHERE \"PK_ID_RADIO_OPERADOR\"= '" + idRadioOper + "';");
            while (rs.next()) {
                return rs.getString("NOMBRE_RADIO_OPERADOR") + " "
                        + rs.getString("APELLIDO_PATERNO_RADIO_OPERADOR") + " "
                        + rs.getString("APELLIDO_MATERNO_RADIO_OPERADOR");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/radioOper$\t" + e.getClass().getName() + "\t" + e.getMessage());
            return e.getMessage();
        }
        return null;
    }

    public String[][] reportIdEmergency(int id) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
//        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"PK_ID_EMERGENCIA\" = '" + id + "' ");
            while (rs.next()) {
                row = new String[21];
                System.out.println("id: " + id);
                row[0] = Integer.toString(id);
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                System.out.println("pull done");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(list.get(i)[j] + "\t");
                data[i][j] = list.get(i)[j];
            }
            System.out.println();
        }
        return data;
    }

    public String[][] reportEmergency() {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "ORDER BY \"PK_ID_EMERGENCIA\" ASC;");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportEmergencyAll() {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "ORDER BY \"PK_ID_EMERGENCIA\" ASC;");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = "";
                row[7] = "";
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = "";
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = "";
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportEmergency(String dateOpen, String dateClose) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' "
                    + "ORDER BY \"PK_ID_EMERGENCIA\" ASC;");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportAmbulance(int idAmbulance) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"ID_AMBULANCIA_EMERGENCIA\" = '" + idAmbulance + "' "
                    + "ORDER BY \"PK_ID_EMERGENCIA\" ASC;");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportAmbulance(int idAmbulance, String dateOpen, String dateClose) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' "
                    + "AND \"ID_AMBULANCIA_EMERGENCIA\" = '" + idAmbulance + "' "
                    + "ORDER BY \"PK_ID_EMERGENCIA\" ASC;");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                System.out.println("id: " + id);
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportFrap(int frap) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"NUM_FRAP_PACIENTE\" = '" + frap + "' "
                    + "ORDER BY \"PK_ID_EMERGENCIA\" ASC;");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportOper(int idOper) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"ID_OPERADOR_EMERGENCIA\" = '" + idOper + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportOper(int idOper, String dateOpen, String dateClose) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' "
                    + "AND \"ID_OPERADOR_EMERGENCIA\" = '" + idOper + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportRadioOper(int idRadioOper) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"ID_RADIO_OPERADOR_EMERGENCIA\" = '" + idRadioOper + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportRadioOper(int idRadioOper, String dateOpen, String dateClose) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' "
                    + "AND \"ID_RADIO_OPERADOR_EMERGENCIA\" = '" + idRadioOper + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportParamedic(int idParamedic) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"ID_PARAMEDICO_EMERGENCIA\" = '" + idParamedic + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportParamedic(int idParamedic, String dateOpen, String dateClose) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' "
                    + "AND \"ID_PARAMEDICO_EMERGENCIA\" = '" + idParamedic + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportParamedicVoluntary(String paramedicVoluntary) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"PARAMEDICO_VOLUNTARIO_EMERGENCIA\" = '" + paramedicVoluntary + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportParamedicVoluntary(String paramedicVoluntary, String dateOpen, String dateClose) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' "
                    + "AND \"PARAMEDICO_VOLUNTARIO_EMERGENCIA\" = '" + paramedicVoluntary + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportOperVoluntary(String operVoluntary) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"OPERADOR_VOLUNTARIO_EMERGENCIA\" = '" + operVoluntary + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportOperVoluntary(String operVoluntary, String dateOpen, String dateClose) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' "
                    + "AND \"OPERADOR_VOLUNTARIO_EMERGENCIA\" = '" + operVoluntary + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportPriorityTransfer(int priorityTransfer) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"PRIORIDAD_TRASLADO_EMERGENCIA\" = '" + priorityTransfer + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportPriorityTransfer(int priorityTransfer, String dateOpen, String dateClose) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' "
                    + "AND \"PRIORIDAD_TRASLADO_EMERGENCIA\" = '" + priorityTransfer + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportTransfer(String transfer) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"TRASLADO_EMERGENCIA\" = '" + transfer + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportTransfer(String transfer, String dateOpen, String dateClose) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' "
                    + "AND \"TRASLADO_EMERGENCIA\" = '" + transfer + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportResultado(String resultado) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"RESULTADO_EMERGENCIA\" = '" + resultado + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportResultado(String resultado, String dateOpen, String dateClose) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' "
                    + "AND \"RESULTADO_EMERGENCIA\" = '" + resultado + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0;
                i < data.length;
                i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportDir(String dir) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"COL_EMERGENCIA\" = '" + dir + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportDir(String dir, String dateOpen, String dateClose) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"HORA_LLAMADA_EMERGENCIA\" BETWEEN '%" + dateOpen + "%' AND '%" + dateClose + "%' "
                    + "AND \"COL_EMERGENCIA\" = '" + dir + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportPatient() {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[14];
        row[0] = "No PACIENTE";
        row[1] = "No EMERGENCIA";
        row[2] = "FRAP";
        row[3] = "NOMBRE";
        row[4] = "EDAD";
        row[5] = "SEXO";
        row[6] = "TRAUMA";
        row[7] = "MOTIVO ENFERMO";
        row[8] = "PADECIMIENTO";
        row[9] = "MEDICAMENTO";
        row[10] = "EVENTO PREVIO";
        row[11] = "TIPO OBSTETRICO";
        row[12] = "MESES OBSTETRICO";
        row[13] = "ESTADO DEL PACIENTE";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"PACIENTE\" "
                    + "ORDER BY \"PK_ID_PACIENTE\" ASC;");
            while (rs.next()) {
                row = new String[14];
                row[0] = rs.getInt("PK_ID_PACIENTE") + "";
                row[1] = rs.getInt("ID_EMERGENCIA") + "";
                row[2] = rs.getString("NUM_FRAP_PACIENTE");
                row[3] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " "
                        + rs.getString("APELLIDO_MATERNO_PACIENTE") + " ";
                row[4] = rs.getInt("EDAD_PACIENTE") + "";
                row[5] = rs.getString("SEXO_PACIENTE");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = rs.getString("PADECIMIENTO_ENFERMO_PACIENTE");
                row[9] = rs.getString("MEDICAMENTO_ENFERMO_PACIENTE");
                row[10] = rs.getString("EVENTO_PREVIO_ENFERMO_PACIENTE");
                row[11] = rs.getString("TIPO_OBSTETRICO_PACIENTE");
                row[12] = rs.getInt("MESES_OBSTETRICO_PACIENTE") + "";
                row[13] = rs.getString("ESTADO_PACIENTE");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        System.out.println("data:\t" + list.size() + "\t" + row.length);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String[][] reportPatient(String name) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        String[] row = new String[21];
        String id;
        row[0] = "No";
        row[1] = "FECHA";
        row[2] = "AMB";
        row[3] = "SALIDA";
        row[4] = "REGRESO";
        row[5] = "ASIGNACIÓN";
        row[6] = "TRAUMA";
        row[7] = "CLINICO";
        row[8] = "RESCATE";
        row[9] = "RESULTADO";
        row[10] = "HOSPITAL";
        row[11] = "FOLIO PAGADO";
        row[12] = "UBICACIÓN";
        row[13] = "OPERADOR";
        row[14] = "PARAMEDICO";
        row[15] = "T. DE RESPUESTA";
        row[16] = "FRAP";
        row[17] = "ENTREGO";
        row[18] = "RADIO OPERADOR";
        row[19] = "PACIENTE";
        row[20] = "OBSERVACIONES";
        list.add(row);
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(""
                    + "SELECT * "
                    + "FROM \"EMERGENCIA\" "
                    + "INNER JOIN \"PACIENTE\" ON \"ID_EMERGENCIA\" = \"PK_ID_EMERGENCIA\" "
                    + "WHERE \"NOMBRE_PACIENTE\" = '" + name + "' "
                    + "OR \"APELLIDO_PATERNO_PACIENTE\" = '" + name + "' "
                    + "OR \"APELLIDO_MATERNO_PACIENTE\" = '" + name + "' ");
            while (rs.next()) {
                row = new String[21];
                id = rs.getInt("PK_ID_EMERGENCIA") + "";
                row[0] = id;
                row[1] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), false);
                row[2] = consultNumAmbulance(rs.getInt("ID_AMBULANCIA_EMERGENCIA"));
                row[3] = divTimeFull(rs.getString("HORA_LLAMADA_EMERGENCIA"), true);
                row[4] = divTimeFull(rs.getString("HORA_BASE_EMERGENCIA"), true);
                row[5] = rs.getString("BASE_EMERGENCIA");
                row[6] = rs.getString("TRAUMA_TIPO_PACIENTE");
                row[7] = rs.getString("MOTIVO_ENFERMO_PACIENTE");
                row[8] = "";
                row[9] = rs.getString("RESULTADO_EMERGENCIA");
                row[10] = rs.getString("TRASLADO_EMERGENCIA");
                row[11] = rs.getString("FOLIO_EMERGENCIA");
                row[12] = rs.getString("COL_EMERGENCIA");
                row[13] = consultNameOper(rs.getInt("ID_OPERADOR_EMERGENCIA"));
                row[14] = consultNameParamedic(rs.getInt("ID_PARAMEDICO_EMERGENCIA"));
                row[15] = subTime(divTimeFull(rs.getString("HORA_SALIDA_EMERGENCIA"), true), row[4]);
                row[16] = rs.getString("NUM_FRAP_PACIENTE");
                row[17] = "";
                row[18] = consultNameRadioOper(rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA"));
                row[19] = rs.getString("NOMBRE_PACIENTE") + " " + rs.getString("APELLIDO_PATERNO_PACIENTE") + " " + rs.getString("APELLIDO_MATERNO_PACIENTE");
                row[20] = rs.getString("OBSERVACION_EMERGENCIA");
                list.add(row);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency/reportExcel$\t" + e.getClass().getName() + "\t" + e.getMessage());
        }
        String[][] data = new String[list.size()][row.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = list.get(i)[j];
            }
        }
        return data;
    }

    public String editAmbulanceKm(int id, int km) {
        String query = "UPDATE \"AMBULANCIA\" "
                + "SET \"KM_AMBULANCIA\" = ? "
                + "WHERE \"PK_ID_AMBULANCIA\" = ?";
        try (PreparedStatement pst = c.prepareStatement(query)) {
            pst.setInt(1, km);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            return ex.getMessage();
        }
        return "done";
    }

    public String editOper(String[] employee) {
        String query = "UPDATE \"OPERADOR\" "
                + "SET \"NOMBRE_OPERADOR\" = ?, "
                + " \"APELLIDO_PATERNO_OPERADOR\" = ?, "
                + " \"APELLIDO_MATERNO_OPERADOR\" = ? "
                + "WHERE \"PK_ID_OPERADOR\" = ?";
        try (PreparedStatement pst = c.prepareStatement(query)) {
            pst.setString(1, employee[1]);
            pst.setString(2, employee[2]);
            pst.setString(3, employee[3]);
            pst.setInt(4, Integer.valueOf(employee[0]));
            pst.executeUpdate();
            return "done update OP";
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    public String editParamedic(String[] employee) {
        String query = "UPDATE \"PARAMEDICO\" "
                + "SET \"NOMBRE_PARAMEDICO\" = ?, "
                + " \"APELLIDO_PATERNO_PARAMEDICO\" = ?, "
                + " \"APELLIDO_MATERNO_PARAMEDICO\" = ? "
                + "WHERE \"PK_ID_PARAMEDICO\" = ?";
        try (PreparedStatement pst = c.prepareStatement(query)) {
            pst.setString(1, employee[1]);
            pst.setString(2, employee[2]);
            pst.setString(3, employee[3]);
            pst.setInt(4, Integer.valueOf(employee[0]));
            pst.executeUpdate();
            return "done update PM";
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    public String editRadioOper(String[] employee) {
        String query = "UPDATE \"RADIO_OPERADOR\" "
                + "SET \"NOMBRE_RADIO_OPERADOR\" = ?, "
                + " \"APELLIDO_PATERNO_RADIO_OPERADOR\" = ?, "
                + " \"APELLIDO_MATERNO_RADIO_OPERADOR\" = ? "
                + "WHERE \"PK_ID_RADIO_OPERADOR\" = ?";
        try (PreparedStatement pst = c.prepareStatement(query)) {
            pst.setString(1, employee[1]);
            pst.setString(2, employee[2]);
            pst.setString(3, employee[3]);
            pst.setInt(4, Integer.valueOf(employee[0]));
            pst.executeUpdate();
            return "done update RO";
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    public String editEmergency(int id, String dir, String entre, String ref, String col, String del, String nameApplicant,
            String resultado, String transfer, int priorityTransfer, int alive, int deads, int idParamedic, int idOper,
            int idRadioOper, int idAmbulance, String base, String operVoluntary, String paramedicVoluntary, String timeCall,
            String timeDeparture, String timeArrival, String timeTransfer, String timeHospital, String timeComeback, String note,
            String tipeCallmain, String callmain, int kmTraveled, String folio) {
        String query = "UPDATE \"EMERGENCIA\" "
                + "SET \"DIR_EMERGENCIA\" = ?, "
                + " \"ENTRE_EMERGENCIA\" = ?, "
                + " \"REF_EMERGENCIA\" = ?, "
                + " \"COL_EMERGENCIA\" = ?, "
                + " \"DEL_EMERGENCIA\" = ?, "
                + " \"NOMBRE_SOLICITANTE_EMERGENCIA\" = ?, "
                + " \"RESULTADO_EMERGENCIA\" = ?, "
                + " \"TRASLADO_EMERGENCIA\" = ?, "
                + " \"PRIORIDAD_TRASLADO_EMERGENCIA\" = ?, "
                + " \"NUMERO_PACIENTES_EMERGENCIA\" = ?, "
                + " \"NUMERO_MUERTOS_EMERGENCIA\" = ?, "
                + " \"ID_PARAMEDICO_EMERGENCIA\" = ?, "
                + " \"ID_OPERADOR_EMERGENCIA\" = ?, "
                + " \"ID_RADIO_OPERADOR_EMERGENCIA\" = ?, "
                + " \"ID_AMBULANCIA_EMERGENCIA\" = ?, "
                + " \"BASE_EMERGENCIA\" = ?, "
                + " \"OPERADOR_VOLUNTARIO_EMERGENCIA\" = ?, "
                + " \"PARAMEDICO_VOLUNTARIO_EMERGENCIA\" = ?, "
                + " \"HORA_LLAMADA_EMERGENCIA\" = ?, "
                + " \"HORA_SALIDA_EMERGENCIA\" = ?, "
                + " \"HORA_LLEGADA_EMERGENCIA\" = ?, "
                + " \"HORA_TRASLADO_EMERGENCIA\" = ?, "
                + " \"HORA_HOSPITAL_EMERGENCIA\" = ?, "
                + " \"HORA_BASE_EMERGENCIA\" = ?, "
                + " \"OBSERVACION_EMERGENCIA\" = ?, "
                + " \"LLAMADA_RECIBIDA_EMERGENCIA\" = ?, "
                + " \"NUMERO_LLAMADA_RECIBIDA_EMERGENCIA\" = ?, "
                + " \"KM_RECORRIDO_EMERGENCIA\" = ?, "
                + " \"FOLIO_EMERGENCIA\" = ? "
                + "WHERE \"PK_ID_EMERGENCIA\" = ?";
        try (PreparedStatement pst = c.prepareStatement(query)) {
            pst.setString(1, dir);
            pst.setString(2, entre);
            pst.setString(3, ref);
            pst.setString(4, col);
            pst.setString(5, del);
            pst.setString(6, nameApplicant);
            pst.setString(7, resultado);
            pst.setString(8, transfer);
            pst.setInt(9, priorityTransfer);
            pst.setInt(10, alive);
            pst.setInt(11, deads);
            pst.setInt(12, idParamedic);
            pst.setInt(13, idOper);
            pst.setInt(14, idRadioOper);
            pst.setInt(15, idAmbulance);
            pst.setString(16, base);
            pst.setString(17, operVoluntary);
            pst.setString(18, paramedicVoluntary);
            pst.setTimestamp(19, Timestamp.valueOf(timeCall));
            pst.setTimestamp(20, Timestamp.valueOf(timeDeparture));
            pst.setTimestamp(21, Timestamp.valueOf(timeArrival));
            pst.setTimestamp(22, Timestamp.valueOf(timeTransfer));
            pst.setTimestamp(23, Timestamp.valueOf(timeHospital));
            pst.setTimestamp(24, Timestamp.valueOf(timeComeback));
            pst.setString(25, note);
            pst.setString(26, tipeCallmain);
            pst.setString(27, callmain);
            pst.setInt(28, kmTraveled);
            pst.setString(29, folio);
            pst.setInt(30, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            return ex.getMessage();
        }
        return "done";
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

    public String deleteEmergency(int id) {
        String SQL = "DELETE FROM \"EMERGENCIA\" WHERE \"PK_ID_EMERGENCIA\" = ?";
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

    public String divTimeFull(String timeFull, Boolean isTime) {//metodo para dividir la hora de la fecha, su segundo parametro espera un boolean donde pregunta si lo que quieres es la hora
        boolean isDate;
        String time = "";
        String date = "";
        char[] arrayChar = timeFull.toCharArray();
        isDate = true;
        for (int i = 0; i < arrayChar.length; i++) {
            if (arrayChar[i] == ' ') {
                isDate = false;
            } else {
                if (isDate) {
                    date += arrayChar[i];
                } else {
                    time += arrayChar[i];
                }
            }
        }
        if (isTime) {
            return time;
        } else {
            return date;
        }
    }

    public String subTime(String time1, String time2) {
//        System.out.println("time: " + time2 + "-" + time1);
        String time = "";
        String catchTime;
        int seg = 0, min = 0, hor = 0, tipeTime;
        char[] arrayChar;

        tipeTime = 0;
        catchTime = "";
        arrayChar = time2.toCharArray();
        for (int i = 0; i < arrayChar.length; i++) {
            if (arrayChar[i] == ':') {
                switch (tipeTime) {
                    case 0:
                        hor = Integer.valueOf(catchTime);
                        break;
                    case 1:
                        min = Integer.valueOf(catchTime);
                        break;
                }
                catchTime = "";
                tipeTime++;
            } else {
                switch (tipeTime) {
                    case 0:
                        catchTime += arrayChar[i];
                        break;
                    case 1:
                        catchTime += arrayChar[i];
                        break;
                    case 2:
                        catchTime += arrayChar[i];
                        break;
                }
            }
        }
//        System.out.println("catch: " + catchTime);
        seg = Integer.valueOf(catchTime);
//        System.out.println("time2: " + hor + ":" + min + ":" + seg);

        tipeTime = 0;
        catchTime = "";
        arrayChar = time1.toCharArray();
        for (int i = 0; i < arrayChar.length; i++) {
            if (arrayChar[i] == ':') {
                switch (tipeTime) {
                    case 0:
//                        System.out.print("time1: " + catchTime);
                        hor -= Integer.valueOf(catchTime);
                        break;
                    case 1:
//                        System.out.print(":" + catchTime);
                        min -= Integer.valueOf(catchTime);
                        break;
                }
                catchTime = "";
                tipeTime++;
            } else {
                switch (tipeTime) {
                    case 0:
                        catchTime += arrayChar[i];
                        break;
                    case 1:
                        catchTime += arrayChar[i];
                        break;
                    case 2:
                        catchTime += arrayChar[i];
                        break;
                }
            }
        }
//        System.out.print(":" + catchTime);
        seg -= Integer.valueOf(catchTime);
        if (seg < 0) {
            seg += 60;
            min--;
        }
        if (min < 0) {
            min += 60;
            hor--;
        }
        if (hor < 0) {
            hor += 24;
        }
        if (hor < 10 && hor >= 0) {
            time += "0";
        }
        time += hor + ":";
        if (min < 10) {
            time += "0";
        }
        time += min + ":";
        if (seg < 10) {
            time += "0";
        }
        time += seg;
//        System.out.println("\nsubTime: " + time);
        return time;
    }
}
