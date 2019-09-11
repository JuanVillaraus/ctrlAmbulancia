/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

//import javax.swing.JComponent;
//import static java.awt.SystemColor.window;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author Sistemas
 */
public class Interface extends JFrame implements ActionListener {

    //ConxDB db;
    SimpleDateFormat date, time;
    ctrData cD;
    int n = 0;
    JFrame emergency;
    JFrame admin;
    JTextField tAmbulance = new JTextField(30);
    JTextField tKmAmbulance = new JTextField(30);
    JTextField tOperVoluntary = new JTextField(30);
    JTextField tParamedicVoluntary = new JTextField(30);
    JTextField tOper = new JTextField(30);
    JTextField tRadioOper = new JTextField(30);
    JTextField tParamedic = new JTextField(30);
    JTextField tApplicant = new JTextField(30);
    JTextField tTime = new JTextField(10);
    JMenu mOper = new JMenu("Elegir opción");
    JMenu mRadioOper = new JMenu("Elegir opción");
    JMenu mParamedic = new JMenu("Elegir opción");
    JMenu mAmbulance = new JMenu("Elegir opción");
    int folio = 0;
    ConxDB db = new ConxDB();
    //String[] data;

    public Interface() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/cruzroja.png"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        //this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - this.getWidth() / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height / 3) - this.getHeight() / 2);
        this.setTitle("Sistemas de Control de Ambulancias");

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

        menuUpdate();
        mBarOper.add(mOper);
        mBarRadioOper.add(mRadioOper);
        mBarParamedic.add(mParamedic);
        mBarAmbulance.add(mAmbulance);
        mOper.setPreferredSize(new Dimension(330, 20));
        mRadioOper.setPreferredSize(new Dimension(330, 20));
        mParamedic.setPreferredSize(new Dimension(330, 20));
        mAmbulance.setPreferredSize(new Dimension(330, 20));
        tKmAmbulance.setEditable(false);
        tKmAmbulance.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);

        bEmergency.addActionListener(this);
        bReport.addActionListener(this);
        bAdmin.addActionListener(this);
        mainPanel.add(lAmbulance);
        //mainPanel.add(tAmbulance);
        mainPanel.add(mBarAmbulance);
        mainPanel.add(lKmAmbulance);
        mainPanel.add(tKmAmbulance);
        mainPanel.add(lOperVoluntary);
        mainPanel.add(tOperVoluntary);
        mainPanel.add(lParamedicVoluntary);
        mainPanel.add(tParamedicVoluntary);
        mainPanel.add(lOper);
        //mainPanel.add(tOper);
        mainPanel.add(mBarOper);
        mainPanel.add(lParamedic);
        //mainPanel.add(tParamedic);
        mainPanel.add(mBarParamedic);
        mainPanel.add(lRadioOper);
        //mainPanel.add(tRadioOper);
        mainPanel.add(mBarRadioOper);
        mainPanel.add(lApplicant);
        mainPanel.add(tApplicant);
        mainPanel.add(bEmergency);
        mainPanel.add(bReport);
        mainPanel.add(bAdmin);
        pTime.add(tTime);
        this.add(mainPanel);
        this.add(pTime, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("event " + e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Emergencia":
                folio++;
                String[] data = {String.valueOf(folio), mAmbulance.getText(), tKmAmbulance.getText(), tOperVoluntary.getText(),
                    tParamedicVoluntary.getText(), mOper.getText(), mParamedic.getText(), mRadioOper.getText(), tApplicant.getText()};
                //System.out.println("Interface/ActionPerformed$data:\t" + data[0] + "\t" + data[1] + "\t" + data[2] + "\t" + data[3] + "\t" + data[4]);
                emergency = new JFrame("Emergencia Folio:" + data[0]);
                emergency.setSize(1600, 500);
                emergency.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - emergency.getWidth() / 2,
                        (Toolkit.getDefaultToolkit().getScreenSize().height / 3) - emergency.getHeight() / 2);
                emergency.setFocusable(true);
                emergency.setExtendedState(MAXIMIZED_BOTH);
                emergency.setVisible(true);
                emergency.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/cruzroja.png"));
                time = new SimpleDateFormat("hh:mm:ss");
                Calendar calendario = new GregorianCalendar();
                ctrTime cT = new ctrTime(time.format(calendario.getTime()), data[0]);
                tabData tab = new tabData();
                cD = new ctrData(time.format(calendario.getTime()), Toolkit.getDefaultToolkit().getScreenSize().width, emergency, data, tab, db);
                emergency.setLayout(new BorderLayout());
                emergency.add(tab, BorderLayout.NORTH);
                emergency.add(cD, BorderLayout.CENTER);
                emergency.add(cT, BorderLayout.SOUTH);
                break;
            case "Admin":
                JPanel pPass = new JPanel();
                JTextField tUser = new JTextField(8);
                JPasswordField tPass = new JPasswordField(8);
                pPass.add(new JLabel("User:"));
                pPass.add(tUser);
                pPass.add(new JLabel("Pass:"));
                pPass.add(tPass);
                if (0 == JOptionPane.showConfirmDialog(null, pPass, "login", JOptionPane.DEFAULT_OPTION)) {
                    if(String.valueOf(tUser.getText()).equals(db.consultAdmin(String.valueOf(tPass.getPassword())))){
                    admin = new JFrame("Administrador");
                    admin.setSize(400, 500);
                    admin.setVisible(true);
                    admin.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - this.getWidth() / 2) + this.getWidth(),
                            (Toolkit.getDefaultToolkit().getScreenSize().height / 3) - this.getHeight() / 2);
                    admin.add(new admin(admin, db));
                    //int p = JOptionPane.showConfirmDialog(null, new admin(db), "Admin",JOptionPane.DEFAULT_OPTION);
                    }else{
                        JOptionPane.showMessageDialog(null, "El user o pass son incorrectos", "ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            case "Reporte":
                System.out.println("report");
                break;
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
                word = "";
            } else {
                word += cadena[i];
            }
        }
    }
}
