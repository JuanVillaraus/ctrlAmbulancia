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
        String query = "INSERT INTO \"OPERADOR\"(\"NOMBRE_OPERADOR\", \"APELLIDO_PATERNO_OPERADOR\", "
                + "\"APELLIDO_MATERNO_OPERADOR\") VALUES(?, ?, ?)";

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
        String query = "INSERT INTO \"RADIO_OPERADOR\"(\"NOMBRE_RADIO_OPERADOR\", \"APELLIDO_PATERNO_RADIO_OPERADOR\", "
                + "\"APELLIDO_MATERNO_RADIO_OPERADOR\") VALUES(?, ?, ?)";

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
        String query = "INSERT INTO \"PARAMEDICO\"(\"NOMBRE_PARAMEDICO\", \"APELLIDO_PATERNO_PARAMEDICO\", "
                + "\"APELLIDO_MATERNO_PARAMEDICO\") VALUES(?, ?, ?)";

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
                + "\"APELLIDO_MATERNO_PACIENTE\", \"EDAD_PACIENTE\", \"SEXO_PACIENTE\", \"TRAUMA_TIPO_PACIENTE\", "
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
            System.err.println("ConxDB/insertPatient$\t" + ex.getClass().getName() + "\t" + ex.getMessage());
            return (ex.getMessage());
        }
    }

    public String insertEmergencyReport(String dir, String entre, String ref, String col, String del, String nameApplicant,
            String resultado, String transfer, int priorityTransfer, int alive, int deads, int idPatient,
            int idParamedic, int idOper, int idAmbulance, int idRadioOper, String operVoluntary, String paramedicVoluntary, String timeCall,
            String timeDeparture, String timeArrival, String timeTransfer, String timeHospital, String timeComeback, String note) {
        String query = "INSERT INTO \"EMERGENCIA\"(\"DIR_EMERGENCIA\", \"ENTRE_EMERGENCIA\", \"REF_EMERGENCIA\", \"COL_EMERGENCIA\", "
                + "\"DEL_EMERGENCIA\", \"NOMBRE_SOLICITANTE_EMERGENCIA\", \"RESULTADO_EMERGENCIA\", \"TRASLADO_EMERGENCIA\", "
                + "\"PRIORIDAD_TRASLADO_EMERGENCIA\", \"NUMERO_PACIENTES_EMERGENCIA\", \"NUMERO_MUERTOS_EMERGENCIA\", "
                + "\"ID_PACIENTE_EMERGENCIA\", \"ID_PARAMEDICO_EMERGENCIA\", \"ID_OPERADOR_EMERGENCIA\", \"ID_AMBULANCIA_EMERGENCIA\", "
                + "\"ID_RADIO_OPERADOR_EMERGENCIA\", \"OPERADOR_VOLUNTARIO_EMERGENCIA\", \"PARAMEDICO_VOLUNTARIO_EMERGENCIA\", \"HORA_LLAMADA_EMERGENCIA\","
                + "\"HORA_SALIDA_EMERGENCIA\", \"HORA_LLEGADA_EMERGENCIA\", \"HORA_TRASLADO_EMERGENCIA\", \"HORA_HOSPITAL_EMERGECIA\""
                + "\"HORA_BASE_EMERGENCIA\", \"OBSERVACION_EMERGENCIA\") "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            pst.setInt(12, idPatient);
            pst.setInt(13, idParamedic);
            pst.setInt(14, idOper);
            pst.setInt(15, idAmbulance);
            pst.setInt(16, idRadioOper);
            pst.setString(17, operVoluntary);
            pst.setString(18, paramedicVoluntary);
            pst.setTimestamp(19, Timestamp.valueOf(timeCall));
            pst.setTimestamp(20, Timestamp.valueOf(timeDeparture));
            pst.setTimestamp(21, Timestamp.valueOf(timeArrival));
            pst.setTimestamp(22, Timestamp.valueOf(timeTransfer));
            pst.setTimestamp(23, Timestamp.valueOf(timeHospital));
            pst.setTimestamp(24, Timestamp.valueOf(timeComeback));
            pst.setTimestamp(25, Timestamp.valueOf(note));
            pst.executeUpdate();
            return ("successfully completed");
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

    public String consultEmergency() {
        String resp = "";
        String namePatient = "";
        String nameParamedic = "";
        String nameOper = "";
        String nameRadioOper = "";
        String numAmbulance = "";
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
                int idPatient = rs.getInt("ID_PACIENTE_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                try {
                    Statement stPatient = c.createStatement();
                    ResultSet rsPatient = stPatient.executeQuery(""
                            + "SELECT \"NOMBRE_PACIENTE\",\"APELLIDO_PATERNO_PACIENTE\",\"APELLIDO_MATERNO_PACIENTE\" "
                            + "FROM \"EMERGENCIA\", \"PACIENTE\""
                            + "WHERE \"PK_ID_PACIENTE\"= '" + idPatient + "';");
                    while (rsPatient.next()) {
                        namePatient = rsPatient.getString("NOMBRE_PACIENTE") + " "
                                + rsPatient.getString("APELLIDO_PATERNO_PACIENTE") + " "
                                + rsPatient.getString("APELLIDO_MATERNO_PACIENTE");
                    }

                    rsPatient.close();
                    stPatient.close();
                } catch (Exception e) {
                    System.err.println("ConxDB/ConsultaEmergency/patient$\t" + e.getClass().getName() + "\t" + e.getMessage());
                    return e.getMessage();
                }

                try {
                    Statement stParamedic = c.createStatement();
                    ResultSet rsParamedic = stParamedic.executeQuery(""
                            + "SELECT \"NOMBRE_PARAMEDICO\",\"APELLIDO_PATERNO_PARAMEDICO\",\"APELLIDO_MATERNO_PARAMEDICO\" "
                            + "FROM \"EMERGENCIA\", \"PARAMEDICO\""
                            + "WHERE \"PK_ID_PARAMEDICO\"= '" + idParamedic + "';");
                    while (rsParamedic.next()) {
                        nameParamedic = rsParamedic.getString("NOMBRE_PARAMEDICO") + " "
                                + rsParamedic.getString("APELLIDO_PATERNO_PARAMEDICO") + " "
                                + rsParamedic.getString("APELLIDO_MATERNO_PARAMEDICO");
                    }

                    rsParamedic.close();
                    stParamedic.close();
                } catch (Exception e) {
                    System.err.println("ConxDB/ConsultaEmergency/paramedic$\t" + e.getClass().getName() + "\t" + e.getMessage());
                    return e.getMessage();
                }

                try {
                    Statement stOper = c.createStatement();
                    ResultSet rsOper = stOper.executeQuery(""
                            + "SELECT \"NOMBRE_OPERADOR\",\"APELLIDO_PATERNO_OPERADOR\",\"APELLIDO_MATERNO_OPERADOR\" "
                            + "FROM \"EMERGENCIA\", \"OPERADOR\""
                            + "WHERE \"PK_ID_OPERADOR\"= '" + idOper + "';");
                    while (rsOper.next()) {
                        nameOper = rsOper.getString("NOMBRE_OPERADOR") + " "
                                + rsOper.getString("APELLIDO_PATERNO_OPERADOR") + " "
                                + rsOper.getString("APELLIDO_MATERNO_OPERADOR");
                    }

                    rsOper.close();
                    stOper.close();
                } catch (Exception e) {
                    System.err.println("ConxDB/ConsultaEmergency/oper$\t" + e.getClass().getName() + "\t" + e.getMessage());
                    return e.getMessage();
                }

                try {
                    Statement stAmbulance = c.createStatement();
                    ResultSet rsAmbulance = stAmbulance.executeQuery(""
                            + "SELECT \"NUMERO_AMBULANCIA\" "
                            + "FROM \"EMERGENCIA\", \"AMBULANCIA\""
                            + "WHERE \"PK_ID_AMBULANCIA\"= '" + idRadioOper + "';");
                    while (rsAmbulance.next()) {
                        numAmbulance = rsAmbulance.getString("NUMERO_AMBULANCIA");
                    }

                    rsAmbulance.close();
                    stAmbulance.close();
                } catch (Exception e) {
                    System.err.println("ConxDB/ConsultaEmergency/Ambulance$\t" + e.getClass().getName() + "\t" + e.getMessage());
                    return e.getMessage();
                }

                try {
                    Statement stRadioOper = c.createStatement();
                    ResultSet rsRadioOper = stRadioOper.executeQuery(""
                            + "SELECT \"NOMBRE_RADIO_OPERADOR\",\"APELLIDO_PATERNO_RADIO_OPERADOR\",\"APELLIDO_MATERNO_RADIO_OPERADOR\" "
                            + "FROM \"EMERGENCIA\", \"RADIO_OPERADOR\""
                            + "WHERE \"PK_ID_RADIO_OPERADOR\"= '" + idRadioOper + "';");
                    while (rsRadioOper.next()) {
                        nameRadioOper = rsRadioOper.getString("NOMBRE_RADIO_OPERADOR") + " "
                                + rsRadioOper.getString("APELLIDO_PATERNO_RADIO_OPERADOR") + " "
                                + rsRadioOper.getString("APELLIDO_MATERNO_RADIO_OPERADOR");
                    }

                    rsRadioOper.close();
                    stRadioOper.close();
                } catch (Exception e) {
                    System.err.println("ConxDB/ConsultaEmergency/radioOper$\t" + e.getClass().getName() + "\t" + e.getMessage());
                    return e.getMessage();
                }

                resp += ("[EM#" + idEmergency + "]"
                        + "\nDirección: " + dir + " \t\tentre: " + entre + "\n"
                        + "Referencia: " + ref + "\n"
                        + "Colonia: " + col + " \t\tDelegación: " + del + "\n"
                        + "Nombre del solicitante: " + nameApplicant + "\n"
                        + "Resultado: " + resultado + "\n"
                        + "Traslado: " + transfer + "\t\tPrioridad: " + priorityTransfer + "\n"
                        + "Vivos: " + alive + "\t\tMuertos: " + deads + "\n"
                        + "Paciente: #" + idPatient + "\tNombre: " + namePatient + "\n"
                        + "Paramedico: #" + idParamedic + "\tNombre: " + nameParamedic + "\n"
                        + "Paramedico voluntario: : " + paramedicVoluntary + "\n"
                        + "Operador: #" + idOper + "\tNombre: " + nameOper + "\n"
                        + "Operador voluntario: " + operVoluntary + "\n"
                        + "Ambulancia: id:" + idAmbulance + "\tNumero: " + numAmbulance + "\n"
                        + "RadioOperador: #" + idRadioOper + "\tNombre: " + nameRadioOper + "\n"
                        + "hora de la llamada: " + timeCall + "\n"
                        + "hora de la salida: " + timeDeparture + "\n"
                        + "hora de la llegada: " + timeArrival + "\n"
                        + "hora de la traslado: " + timeTransfer + "\n"
                        + "hora de la hospital: " + timeHospital + "\n"
                        + "hora de la base: " + timeComeback + "\n"
                        + "Observaciones: " + note + "\n"
                        + "\n");
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
        String namePatient = "";
        String nameParamedic = "";
        String nameOper = "";
        String nameRadioOper = "";
        String numAmbulance = "";
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
                int idPatient = rs.getInt("ID_PACIENTE_EMERGENCIA");
                int idParamedic = rs.getInt("ID_PARAMEDICO_EMERGENCIA");
                int idOper = rs.getInt("ID_OPERADOR_EMERGENCIA");
                int idAmbulance = rs.getInt("ID_AMBULANCIA_EMERGENCIA");
                int idRadioOper = rs.getInt("ID_RADIO_OPERADOR_EMERGENCIA");
                String operVoluntary = rs.getString("OPERADOR_VOLUNTARIO_EMERGENCIA");
                String paramedicVoluntary = rs.getString("PARAMEDICO_VOLUNTARIO_EMERGENCIA");
                String timeCall = rs.getString("HORA_LLAMADA_EMERGENCIA");
                String timeDeparture = rs.getString("HORA_SALIDA_EMERGENCIA");
                String timeArrival = rs.getString("HORA_LLEGADA_EMERGENCIA");
                String timeTransfer = rs.getString("HORA_TRASLADO_EMERGENCIA");
                String timeHospital = rs.getString("HORA_HOSPITAL_EMERGENCIA");
                String timeComeback = rs.getString("HORA_BASE_EMERGENCIA");
                String note = rs.getString("OBSERVACION_EMERGENCIA");

                try {
                    Statement stPatient = c.createStatement();
                    ResultSet rsPatient = stPatient.executeQuery(""
                            + "SELECT \"NOMBRE_PACIENTE\",\"APELLIDO_PATERNO_PACIENTE\",\"APELLIDO_MATERNO_PACIENTE\" "
                            + "FROM \"EMERGENCIA\", \"PACIENTE\""
                            + "WHERE \"PK_ID_PACIENTE\"= '" + idPatient + "';");
                    while (rsPatient.next()) {
                        namePatient = rsPatient.getString("NOMBRE_PACIENTE") + " "
                                + rsPatient.getString("APELLIDO_PATERNO_PACIENTE") + " "
                                + rsPatient.getString("APELLIDO_MATERNO_PACIENTE");
                    }

                    rsPatient.close();
                    stPatient.close();
                } catch (Exception e) {
                    System.err.println("ConxDB/ConsultaEmergency/patient$\t" + e.getClass().getName() + "\t" + e.getMessage());
                    return e.getMessage();
                }

                try {
                    Statement stParamedic = c.createStatement();
                    ResultSet rsParamedic = stParamedic.executeQuery(""
                            + "SELECT \"NOMBRE_PARAMEDICO\",\"APELLIDO_PATERNO_PARAMEDICO\",\"APELLIDO_MATERNO_PARAMEDICO\" "
                            + "FROM \"EMERGENCIA\", \"PARAMEDICO\""
                            + "WHERE \"PK_ID_PARAMEDICO\"= '" + idParamedic + "';");
                    while (rsParamedic.next()) {
                        nameParamedic = rsParamedic.getString("NOMBRE_PARAMEDICO") + " "
                                + rsParamedic.getString("APELLIDO_PATERNO_PARAMEDICO") + " "
                                + rsParamedic.getString("APELLIDO_MATERNO_PARAMEDICO");
                    }

                    rsParamedic.close();
                    stParamedic.close();
                } catch (Exception e) {
                    System.err.println("ConxDB/ConsultaEmergency/paramedic$\t" + e.getClass().getName() + "\t" + e.getMessage());
                    return e.getMessage();
                }

                try {
                    Statement stOper = c.createStatement();
                    ResultSet rsOper = stOper.executeQuery(""
                            + "SELECT \"NOMBRE_OPERADOR\",\"APELLIDO_PATERNO_OPERADOR\",\"APELLIDO_MATERNO_OPERADOR\" "
                            + "FROM \"EMERGENCIA\", \"OPERADOR\""
                            + "WHERE \"PK_ID_OPERADOR\"= '" + idOper + "';");
                    while (rsOper.next()) {
                        nameOper = rsOper.getString("NOMBRE_OPERADOR") + " "
                                + rsOper.getString("APELLIDO_PATERNO_OPERADOR") + " "
                                + rsOper.getString("APELLIDO_MATERNO_OPERADOR");
                    }

                    rsOper.close();
                    stOper.close();
                } catch (Exception e) {
                    System.err.println("ConxDB/ConsultaEmergency/oper$\t" + e.getClass().getName() + "\t" + e.getMessage());
                    return e.getMessage();
                }

                try {
                    Statement stAmbulance = c.createStatement();
                    ResultSet rsAmbulance = stAmbulance.executeQuery(""
                            + "SELECT \"NUMERO_AMBULANCIA\" "
                            + "FROM \"EMERGENCIA\", \"AMBULANCIA\""
                            + "WHERE \"PK_ID_AMBULANCIA\"= '" + idRadioOper + "';");
                    while (rsAmbulance.next()) {
                        numAmbulance = rsAmbulance.getString("NUMERO_AMBULANCIA");
                    }

                    rsAmbulance.close();
                    stAmbulance.close();
                } catch (Exception e) {
                    System.err.println("ConxDB/ConsultaEmergency/Ambulance$\t" + e.getClass().getName() + "\t" + e.getMessage());
                    return e.getMessage();
                }

                try {
                    Statement stRadioOper = c.createStatement();
                    ResultSet rsRadioOper = stRadioOper.executeQuery(""
                            + "SELECT \"NOMBRE_RADIO_OPERADOR\",\"APELLIDO_PATERNO_RADIO_OPERADOR\",\"APELLIDO_MATERNO_RADIO_OPERADOR\" "
                            + "FROM \"EMERGENCIA\", \"RADIO_OPERADOR\""
                            + "WHERE \"PK_ID_RADIO_OPERADOR\"= '" + idRadioOper + "';");
                    while (rsRadioOper.next()) {
                        nameRadioOper = rsRadioOper.getString("NOMBRE_RADIO_OPERADOR") + " "
                                + rsRadioOper.getString("APELLIDO_PATERNO_RADIO_OPERADOR") + " "
                                + rsRadioOper.getString("APELLIDO_MATERNO_RADIO_OPERADOR");
                    }

                    rsRadioOper.close();
                    stRadioOper.close();
                } catch (Exception e) {
                    System.err.println("ConxDB/ConsultaEmergency/radioOper$\t" + e.getClass().getName() + "\t" + e.getMessage());
                    return e.getMessage();
                }

                resp += ("[EM#" + idEmergency + "]"
                        + "\nDirección: " + dir + " \t\tentre: " + entre + "\n"
                        + "Referencia: " + ref + "\n"
                        + "Colonia: " + col + " \t\tDelegación: " + del + "\n"
                        + "Nombre del solicitante: " + nameApplicant + "\n"
                        + "Resultado: " + resultado + "\n"
                        + "Traslado: " + transfer + "\t\tPrioridad: " + priorityTransfer + "\n"
                        + "Vivos: " + alive + "\t\tMuertos: " + deads + "\n"
                        + "Paciente: #" + idPatient + "\tNombre: " + namePatient + "\n"
                        + "Paramedico: #" + idParamedic + "\tNombre: " + nameParamedic + "\n"
                        + "Paramedico voluntario: : " + paramedicVoluntary + "\n"
                        + "Operador: #" + idOper + "\tNombre: " + nameOper + "\n"
                        + "Operador voluntario: " + operVoluntary + "\n"
                        + "Ambulancia: id:" + idAmbulance + "\tNumero: " + numAmbulance + "\n"
                        + "RadioOperador: #" + idRadioOper + "\tNombre: " + nameRadioOper + "\n"
                        + "hora de la llamada: " + timeCall + "\n"
                        + "hora de la salida: " + timeDeparture + "\n"
                        + "hora de la llegada: " + timeArrival + "\n"
                        + "hora de la traslado: " + timeTransfer + "\n"
                        + "hora de la hospital: " + timeHospital + "\n"
                        + "hora de la base: " + timeComeback + "\n"
                        + "Observaciones: " + note + "\n"
                        + "\n");
            }

            rs.close();
            st.close();
            return resp;
        } catch (Exception e) {
            System.err.println("ConxDB/ConsultaEmergency$\t" + e.getClass().getName() + "\t" + e.getMessage());
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
