/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class Interface extends JFrame implements ActionListener {

    SimpleDateFormat date, time;
    ctrData cD;
    EditEmergency eM;
    Consulta con;
    int n = 0;
    JFrame emergency;
    JFrame admin;
    JFrame consult;
    JTextField tKmAmbulance = new JTextField(30);//Kilometros recorridos de la ambulancia
    JTextField tOperVoluntary = new JTextField(30);//Operador voluntario
    JTextField tParamedicVoluntary = new JTextField(30);//Paramedico voluntario
    JTextField tApplicant = new JTextField(30);//Solicitante
    JTextField tTipeCallmain = new JTextField(30);//llamada del solicitante
    JTextField tCallmain = new JTextField(30);//llamada del solicitante
    JTextField tTime = new JTextField(10);//hora en tiempo real
    JMenu mOper = new JMenu("Elegir opción");//menu operadores
    JMenu mRadioOper = new JMenu("Elegir opción");//menu radio operadores
    JMenu mParamedic = new JMenu("Elegir opción");//menu paramedicos
    JMenu mAmbulance = new JMenu("Elegir opción");//menu ambulancias
    JMenu mBase = new JMenu("Base de salida");
    JMenu mCallmain = new JMenu("llamada recibida");
    ConxDB db = new ConxDB();
    //String[] data;

    public Interface() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/cruzroja.png"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 560);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - this.getWidth() / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height / 3) - this.getHeight() / 2);
        this.setTitle("Sist. de Control de Ambulancias Beta2.4");

        RealTime rt = new RealTime();
        rt.settTime(tTime);
        rt.start();
        JPanel mainPanel = new JPanel();
        JPanel pTime = new JPanel();

        JLabel lAmbulance = new JLabel("Ambulancia");
        JLabel lKmAmbulance = new JLabel("Km de la Ambulancia");
        JLabel lOperVoluntary = new JLabel("Operador Voluntario");
        JLabel lParamedicVoluntary = new JLabel("Paramedico Voluntario");
        JLabel lOper = new JLabel("Operador");
        JLabel lRadioOper = new JLabel("Radio Operador");
        JLabel lParamedic = new JLabel("Paramedico");
        JLabel lApplicant = new JLabel("Solicitante");
        JButton bEmergency = new JButton("Emergencia");
        JButton bReport = new JButton("Reporte");
        JButton bAdmin = new JButton("Admin");
        tTime.setEditable(false);
        tTime.setHorizontalAlignment(JTextField.CENTER);
        JMenuBar mBarOper = new JMenuBar();
        JMenuBar mBarRadioOper = new JMenuBar();
        JMenuBar mBarParamedic = new JMenuBar();
        JMenuBar mBarAmbulance = new JMenuBar();
        JMenuBar mBarBase = new JMenuBar();
        JMenuBar mBarCallmain = new JMenuBar();
        JMenuItem iBase1 = new JMenuItem("Centro");
        JMenuItem iBase2 = new JMenuItem("Norte");
        JMenuItem iBase3 = new JMenuItem("Boca");
        JMenuItem iCallmain1 = new JMenuItem("Tel particular");
        JMenuItem iCallmain2 = new JMenuItem("Tel celular");
        JMenuItem iCallmain3 = new JMenuItem("Presentado");
        JMenuItem iCallmain4 = new JMenuItem("Quien hizo la llamada");
        JMenuItem iCallmain5 = new JMenuItem("Casetar");
        JMenuItem iCallmain6 = new JMenuItem("Radio");
        JMenuItem iCallmain7 = new JMenuItem("Pre base");
        JMenuItem iCallmain8 = new JMenuItem("otros");
        iBase1.addActionListener(this);
        iBase2.addActionListener(this);
        iBase3.addActionListener(this);
        iCallmain1.addActionListener(this);
        iCallmain2.addActionListener(this);
        iCallmain3.addActionListener(this);
        iCallmain4.addActionListener(this);
        iCallmain5.addActionListener(this);
        iCallmain6.addActionListener(this);
        iCallmain7.addActionListener(this);
        iCallmain8.addActionListener(this);
        mBase.add(iBase1);
        mBase.add(iBase2);
        mBase.add(iBase3);
        mBarBase.add(mBase);
        mCallmain.add(iCallmain1);
        mCallmain.add(iCallmain2);
        mCallmain.add(iCallmain3);
        mCallmain.add(iCallmain4);
        mCallmain.add(iCallmain5);
        mCallmain.add(iCallmain6);
        mCallmain.add(iCallmain7);
        mCallmain.add(iCallmain8);
        mBarCallmain.add(mCallmain);

        menuUpdate();
        mBarOper.add(mOper);
        mBarRadioOper.add(mRadioOper);
        mBarParamedic.add(mParamedic);
        mBarAmbulance.add(mAmbulance);
        mOper.setPreferredSize(new Dimension(330, 20));
        mRadioOper.setPreferredSize(new Dimension(330, 20));
        mParamedic.setPreferredSize(new Dimension(330, 20));
        mAmbulance.setPreferredSize(new Dimension(330, 20));
        mBase.setPreferredSize(new Dimension(330, 20));
        iBase1.setPreferredSize(new Dimension(330, 20));
        iBase2.setPreferredSize(new Dimension(330, 20));
        iBase3.setPreferredSize(new Dimension(330, 20));
        mCallmain.setPreferredSize(new Dimension(330, 20));
        iCallmain1.setPreferredSize(new Dimension(330, 20));
        iCallmain2.setPreferredSize(new Dimension(330, 20));
        iCallmain3.setPreferredSize(new Dimension(330, 20));
        iCallmain4.setPreferredSize(new Dimension(330, 20));
        iCallmain5.setPreferredSize(new Dimension(330, 20));
        iCallmain6.setPreferredSize(new Dimension(330, 20));
        iCallmain7.setPreferredSize(new Dimension(330, 20));
        iCallmain8.setPreferredSize(new Dimension(330, 20));
        tKmAmbulance.setEditable(false);
        tKmAmbulance.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
        tTipeCallmain.setVisible(false);

        bEmergency.addActionListener(this);
        bReport.addActionListener(this);
        bAdmin.addActionListener(this);
        mainPanel.add(lAmbulance);
        //mainPanel.add(tAmbulance);
        mainPanel.add(mBarAmbulance);
        mainPanel.add(lKmAmbulance);
        mainPanel.add(tKmAmbulance);
        mainPanel.add(mBarBase);
        mainPanel.add(lOperVoluntary);
        mainPanel.add(tOperVoluntary);
        mainPanel.add(lParamedicVoluntary);
        mainPanel.add(tParamedicVoluntary);
        mainPanel.add(lOper);
        mainPanel.add(mBarOper);
        mainPanel.add(lParamedic);
        mainPanel.add(mBarParamedic);
        mainPanel.add(lRadioOper);
        mainPanel.add(mBarRadioOper);
        mainPanel.add(lApplicant);
        mainPanel.add(tApplicant);
        mainPanel.add(mBarCallmain);
        mainPanel.add(tCallmain);
        mainPanel.add(bEmergency);
        mainPanel.add(bReport);
        mainPanel.add(bAdmin);
        pTime.add(tTime);
        this.add(mainPanel);
        this.add(pTime, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Emergencia":
                if (mAmbulance.getText().equals("Elegir opción")) {
                    JOptionPane.showMessageDialog(null, "Debe elegir una ambulancia", "ERROR", JOptionPane.WARNING_MESSAGE);
                } else {
                    emergency = new JFrame("Emergencia " + mAmbulance.getText());
                    emergency.setSize(1600, 500);
                    emergency.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - emergency.getWidth() / 2,
                            (Toolkit.getDefaultToolkit().getScreenSize().height / 3) - emergency.getHeight() / 2);
                    emergency.setFocusable(true);
                    emergency.setExtendedState(MAXIMIZED_BOTH);
                    emergency.setVisible(true);
                    emergency.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/cruzroja.png"));
                    time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar calendario = new GregorianCalendar();
                    ctrTime cT = new ctrTime(time.format(calendario.getTime()));
                    tabData tab = new tabData();
                    cD = new ctrData(time.format(calendario.getTime()), Toolkit.getDefaultToolkit().getScreenSize().width, emergency, tab, db,
                            mAmbulance.getText(), tKmAmbulance.getText(), mBase.getText(), tOperVoluntary.getText(), tParamedicVoluntary.getText(),
                            mOper.getText(), mParamedic.getText(), mRadioOper.getText(), tApplicant.getText(), mCallmain.getText(), tCallmain.getText());
                    emergency.setLayout(new BorderLayout());
                    emergency.add(tab, BorderLayout.NORTH);
                    emergency.add(cD, BorderLayout.CENTER);
                    emergency.add(cT, BorderLayout.SOUTH);

                    mAmbulance.setText("Elegir opción");
                    mOper.setText("Elegir opción");
                    mRadioOper.setText("Elegir opción");
                    mParamedic.setText("Elegir opción");
                    mBase.setText("Base de salida");
                    mCallmain.setText("llamada recibida");
                    tKmAmbulance.setText(null);
                    tOperVoluntary.setText(null);
                    tParamedicVoluntary.setText(null);
                    tApplicant.setText(null);
                    tTipeCallmain.setText(null);
                    tCallmain.setText(null);
                }
                break;
            case "Admin":
//                JPanel pPass = new JPanel();
//                JTextField tUser = new JTextField(8);
//                JPasswordField tPass = new JPasswordField(8);
//                pPass.add(new JLabel("User:"));
//                pPass.add(tUser);
//                pPass.add(new JLabel("Pass:"));
//                pPass.add(tPass);
//                if (0 == JOptionPane.showConfirmDialog(null, pPass, "login", JOptionPane.DEFAULT_OPTION)) {
//                    if (String.valueOf(tUser.getText()).equals(db.consultAdmin(String.valueOf(tPass.getPassword())))) {
                        admin = new JFrame("Administrador");
                        admin.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/cruzroja.png"));
                        admin.setSize(400, 500);
                        admin.setVisible(true);
                        admin.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - this.getWidth() / 2) + this.getWidth(),
                                (Toolkit.getDefaultToolkit().getScreenSize().height / 3) - this.getHeight() / 2);
                        admin.add(new admin(admin, db, this));
//                    } else {
//                        JOptionPane.showMessageDialog(null, "El user o pass son incorrectos", "ERROR", JOptionPane.WARNING_MESSAGE);
//                    }
//                }
                break;
            case "Reporte":
                consult = new JFrame("Reporte");
                consult.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/cruzroja.png"));
                consult.setSize(1600, 500);
                consult.setVisible(true);
                consult.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - consult.getWidth() / 2,
                        (Toolkit.getDefaultToolkit().getScreenSize().height / 3) - consult.getHeight() / 2);
                consult.setFocusable(true);
                consult.setExtendedState(MAXIMIZED_BOTH);
                con = new Consulta(consult, db);
                consult.add(con);
                break;
            case "Centro":
            case "Norte":
            case "Boca":
                mBase.setText(e.getActionCommand());
                break;
            case "Tel particular":
            case "Tel celular":
            case "Presentado":
            case "Quien hizo la llamada":
            case "Caseta":
            case "Radio":
            case "Pres base":
                mCallmain.setText(e.getActionCommand());
                tTipeCallmain.setVisible(false);
            case "Otro":
                mCallmain.setText(e.getActionCommand());
                tTipeCallmain.setVisible(true);
            default:
                char[] cadena = e.getActionCommand().toCharArray();
                String command = "" + cadena[0] + cadena[1];
                switch (command) {
                    case "OP":
                        mOper.setText(e.getActionCommand());
                        break;
                    case "RO":
                        mRadioOper.setText(e.getActionCommand());
                        break;
                    case "PM":
                        mParamedic.setText(e.getActionCommand());
                        break;
                    case "AB":
                        String word = "";
                        for (int i = 3; i < cadena.length; i++) {
                            if (cadena[i] == ' ') {
                                tKmAmbulance.setText(String.valueOf(db.consultAmbulanceKm(Integer.valueOf(word))));
                                i = cadena.length;
                            } else {
                                word += cadena[i];
                            }
                        }
                        mAmbulance.setText(e.getActionCommand());
                        break;
                }
        }
    }

    public void menuUpdate() {
        char[] cadena;
        String employees = db.consultOper();
        cadena = employees.toCharArray();
        String word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iEmployees = new JMenuItem(word);
                mOper.add(iEmployees);
                iEmployees.addActionListener(this);
                iEmployees.setPreferredSize(new Dimension(330, 20));
                word = "";
            } else {
                word += cadena[i];
            }
        }
        employees = db.consultRadioOper();
        cadena = employees.toCharArray();
        word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iEmployees = new JMenuItem(word);
                mRadioOper.add(iEmployees);
                iEmployees.addActionListener(this);
                iEmployees.setPreferredSize(new Dimension(330, 20));
                word = "";
            } else {
                word += cadena[i];
            }
        }
        employees = db.consultParamedic();
        cadena = employees.toCharArray();
        word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iEmployees = new JMenuItem(word);
                mParamedic.add(iEmployees);
                iEmployees.addActionListener(this);
                iEmployees.setPreferredSize(new Dimension(330, 20));
                word = "";
            } else {
                word += cadena[i];
            }
        }
        employees = db.consultAmbulanceNum();
        cadena = employees.toCharArray();
        word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iEmployees = new JMenuItem(word);
                mAmbulance.add(iEmployees);
                iEmployees.addActionListener(this);
                iEmployees.setPreferredSize(new Dimension(330, 20));
                word = "";
            } else {
                word += cadena[i];
            }
        }
    }

    public void menuAmbulanceUpdate(String employees) {
        char[] cadena = employees.toCharArray();
        String word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iEmployees = new JMenuItem(word);
                mAmbulance.add(iEmployees);
                iEmployees.addActionListener(this);
                iEmployees.setPreferredSize(new Dimension(330, 20));
                word = "";
            } else {
                word += cadena[i];
            }
        }
    }

    public void menuOperUpdate(String employees) {
        char[] cadena = employees.toCharArray();
        String word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iEmployees = new JMenuItem(word);
                mOper.add(iEmployees);
                iEmployees.addActionListener(this);
                iEmployees.setPreferredSize(new Dimension(330, 20));
                word = "";
            } else {
                word += cadena[i];
            }
        }
    }

    public void menuRadioOperUpdate(String employees) {
        char[] cadena = employees.toCharArray();
        String word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iEmployees = new JMenuItem(word);
                mRadioOper.add(iEmployees);
                iEmployees.addActionListener(this);
                iEmployees.setPreferredSize(new Dimension(330, 20));
                word = "";
            } else {
                word += cadena[i];
            }
        }
    }

    public void menuParamedicUpdate(String employees) {
        char[] cadena = employees.toCharArray();
        String word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iEmployees = new JMenuItem(word);
                mParamedic.add(iEmployees);
                iEmployees.addActionListener(this);
                iEmployees.setPreferredSize(new Dimension(330, 20));
                word = "";
            } else {
                word += cadena[i];
            }
        }
    }
}
