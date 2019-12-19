/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
//import static java.lang.System.in;
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class admin extends JPanel implements ActionListener {

    JFrame window;
    Interface frameMain;
    //int window = 1200;
    ConxDB db;
    JButton bInsert = new JButton("Agregar");
    JButton bEdit = new JButton("Editar");
    JButton bDelete = new JButton("Eliminar");
    JButton bSave = new JButton("Guardar");
    String resultDB = null;
    JMenu mTipeJob = new JMenu("Opción");
    JMenu mOption = new JMenu("Opción");
    JLabel lName = new JLabel("Nombre");
    JLabel lLastName = new JLabel("Apellido Paterno");
    JLabel lLastName2 = new JLabel("Apellido Materno");
    JLabel lNumAmbulance = new JLabel("Numero de Ambulancia");
    JLabel lKmAmbulance = new JLabel("Km de la Ambulancia");
    JLabel lId = new JLabel("ID");
    //JLabel alert = new JLabel("Alert");
    JTextField tName = new JTextField(30);
    JTextField tLastName = new JTextField(30);
    JTextField tLastName2 = new JTextField(30);
    JTextField tNumAmbulance = new JTextField(30);
    JTextField tKmAmbulance = new JTextField(30);
    JTextField tId = new JTextField(10);
    JTextField alert = new JTextField(10);
    JTextArea aConsult = new JTextArea("");
    //JTable tConsult = new JTable(10,4);
    //JScrollPane sConsult = new JScrollPane(tConsult, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    JScrollPane sConsult = new JScrollPane(aConsult);
    String[] employee = new String[4];

    public admin(JFrame window, ConxDB db, Interface frameMain) {
        this.window = window;
        this.db = db;
        this.frameMain = frameMain;
        JMenuBar mBarOption = new JMenuBar();
        JMenuBar mBarTipeJob = new JMenuBar();
        JMenuItem iOption1 = new JMenuItem("Agregar");
        JMenuItem iOption2 = new JMenuItem("Editar");
        JMenuItem iOption3 = new JMenuItem("Eliminar");
        JMenuItem iTipeJob1 = new JMenuItem("Operador");
        JMenuItem iTipeJob2 = new JMenuItem("Paramedico");
        JMenuItem iTipeJob3 = new JMenuItem("Radio Operador");
        JMenuItem iTipeJob4 = new JMenuItem("Ambulancia");
        JMenuItem iTipeJob5 = new JMenuItem("Emergencia");
        JMenuItem iTipeJob6 = new JMenuItem("Paciente");
        mOption.add(iOption1);
        mOption.add(iOption2);
        mOption.add(iOption3);
//        mOption.add(iConsult);
        mBarOption.add(mOption);
        mTipeJob.add(iTipeJob1);
        mTipeJob.add(iTipeJob2);
        mTipeJob.add(iTipeJob3);
        mTipeJob.add(iTipeJob4);
        mTipeJob.add(iTipeJob5);
        mTipeJob.add(iTipeJob6);
        mBarTipeJob.add(mTipeJob);
        mTipeJob.setVisible(true);
        mOption.setVisible(true);
        lName.setVisible(false);
        tName.setVisible(false);
        lLastName.setVisible(false);
        tLastName.setVisible(false);
        lLastName2.setVisible(false);
        tLastName2.setVisible(false);
        lNumAmbulance.setVisible(false);
        tNumAmbulance.setVisible(false);
        lKmAmbulance.setVisible(false);
        tKmAmbulance.setVisible(false);
        lId.setVisible(false);
        tId.setVisible(false);
        sConsult.setVisible(false);
        bInsert.setVisible(false);
        bEdit.setVisible(false);
        bDelete.setVisible(false);
        bSave.setVisible(false);
        alert.setEditable(false);
        iOption1.addActionListener(this);
        iOption2.addActionListener(this);
        iOption3.addActionListener(this);
//        iConsult.addActionListener(this);
        iTipeJob1.addActionListener(this);
        iTipeJob2.addActionListener(this);
        iTipeJob3.addActionListener(this);
        iTipeJob4.addActionListener(this);
        iTipeJob5.addActionListener(this);
        iTipeJob6.addActionListener(this);
        bInsert.addActionListener(this);
        bEdit.addActionListener(this);
        bDelete.addActionListener(this);
        bSave.addActionListener(this);
        mOption.setPreferredSize(new Dimension(330, 30));
        mTipeJob.setPreferredSize(new Dimension(330, 30));
        lId.setPreferredSize(new Dimension(60, 30));
        aConsult.setPreferredSize(new Dimension(window.getWidth() - 50, 300));
        //aConsult.setPreferredSize(new Dimension(window, 300));
        //sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel pAlert = new JPanel(new GridLayout(1, 1));
        JPanel pJobOption = new JPanel(new GridLayout(1, 1));
        //mInsert.setHorizontalAlignment(SwingConstants.CENTER);
        mOption.setHorizontalTextPosition(JTextField.CENTER);
        mTipeJob.setHorizontalTextPosition(JTextField.CENTER);
        alert.setHorizontalAlignment(JTextField.CENTER);
        lId.setHorizontalAlignment(JTextField.RIGHT);

        pJobOption.add(mBarTipeJob);
        window.add(pJobOption, BorderLayout.NORTH);
        this.setLayout(new FlowLayout());
        this.add(mBarOption);
        this.add(lName);
        this.add(tName);
        this.add(lLastName);
        this.add(tLastName);
        this.add(lLastName2);
        this.add(tLastName2);
        this.add(lNumAmbulance);
        this.add(tNumAmbulance);
        this.add(lKmAmbulance);
        this.add(tKmAmbulance);
        this.add(bInsert);
        this.add(lId);
        this.add(tId);
        this.add(bEdit);
        this.add(bDelete);
        this.add(bSave);
        this.add(sConsult, BorderLayout.CENTER);
        pAlert.add(alert);
        window.add(pAlert, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass().getSimpleName().equals("JButton")) {
            System.out.println("event: " + e.getActionCommand());
            switch (e.getActionCommand()) {
                case "Agregar":
                    switch (mTipeJob.getText()) {
                        case "Ambulancia":
                            if (tNumAmbulance.getText().equals("") || tKmAmbulance.getText().equals("")) {
                                alert.setText("Deben llenarse todos los campos");
                                alert.setBackground(Color.RED);
                            } else {
                                resultDB = db.insertAmbulance(Integer.parseInt(tNumAmbulance.getText()), Integer.parseInt(tKmAmbulance.getText()));
                                if (resultDB.toCharArray()[0] == 'A' && resultDB.toCharArray()[1] == 'B') {
                                    alert.setText("La ambulancia ha sido agregada exitosamente");
                                    frameMain.menuAmbulanceUpdate(resultDB + " Numero:" + tNumAmbulance.getText() + "\n");
                                    tNumAmbulance.setText(null);
                                    tKmAmbulance.setText(null);
                                    alert.setBackground(Color.GREEN);
                                    aConsult.setText("Ambulancia:\n" + db.consultAmbulance());
                                } else {
                                    alert.setText(resultDB);
                                    alert.setBackground(Color.RED);
                                }
                            }
                            break;
                        case "Operador":
                            if (tName.getText().equals("") || tLastName.getText().equals("") || tLastName2.getText().equals("")) {
                                alert.setText("Deben llenarse todos los campos");
                                alert.setBackground(Color.RED);
                            } else {
                                resultDB = db.insertOper(tName.getText(), tLastName.getText(), tLastName2.getText());
                                if (resultDB.toCharArray()[0] == 'O' && resultDB.toCharArray()[1] == 'P') {
                                    alert.setText("El operador ha sido agregado exitosamente");
                                    frameMain.menuOperUpdate(resultDB + " " + tName.getText() + " "
                                            + tLastName.getText() + " " + tLastName2.getText() + "\n");
                                    tName.setText(null);
                                    tLastName.setText(null);
                                    tLastName2.setText(null);
                                    alert.setBackground(Color.GREEN);
                                    aConsult.setText("Operador:\n" + db.consultOper());
                                } else {
                                    alert.setText(resultDB);
                                    alert.setBackground(Color.RED);
                                }
                            }
                            break;
                        case "Paramedico":
                            if (tName.getText().equals("") || tLastName.getText().equals("") || tLastName2.getText().equals("")) {
                                alert.setText("Deben llenarse todos los campos");
                                alert.setBackground(Color.RED);
                            } else {
                                resultDB = db.insertParamedic(tName.getText(), tLastName.getText(), tLastName2.getText());
                                if (resultDB.toCharArray()[0] == 'P' && resultDB.toCharArray()[1] == 'M') {
                                    alert.setText("El paramedico ha sido agregado exitosamente");
                                    frameMain.menuParamedicUpdate(resultDB + " " + tName.getText() + " "
                                            + tLastName.getText() + " " + tLastName2.getText() + "\n");
                                    tName.setText(null);
                                    tLastName.setText(null);
                                    tLastName2.setText(null);
                                    alert.setBackground(Color.GREEN);
                                    aConsult.setText("Paramedico:\n" + db.consultParamedic());
                                } else {
                                    alert.setText(resultDB);
                                    alert.setBackground(Color.RED);
                                }
                            }
                            break;
                        case "Radio Operador":
                            if (tName.getText().equals("") || tLastName.getText().equals("") || tLastName2.getText().equals("")) {
                                alert.setText("Deben llenarse todos los campos");
                                alert.setBackground(Color.RED);
                            } else {
                                resultDB = db.insertRadioOper(tName.getText(), tLastName.getText(), tLastName2.getText());
                                if (resultDB.toCharArray()[0] == 'R' && resultDB.toCharArray()[1] == 'O') {
                                    alert.setText("El paramedico ha sido agregado exitosamente");
                                    frameMain.menuRadioOperUpdate(resultDB + " " + tName.getText() + " "
                                            + tLastName.getText() + " " + tLastName2.getText() + "\n");
                                    tName.setText(null);
                                    tLastName.setText(null);
                                    tLastName2.setText(null);
                                    alert.setBackground(Color.GREEN);
                                    aConsult.setText("Radio Operador:\n" + db.consultRadioOper());
                                } else {
                                    alert.setText(resultDB);
                                    alert.setBackground(Color.RED);
                                }
                            }
                            break;
                    }
                    break;
                case "Editar":
                    switch (mTipeJob.getText()) {
                        case "Operador":
                        case "Paramedico":
                        case "Radio Operador":
                            if (tId.getText().equals("")) {
                                alert.setText("Deben ingresar el ID");
                                alert.setBackground(Color.RED);
                            } else {
                                lName.setVisible(true);
                                tName.setVisible(true);
                                lLastName.setVisible(true);
                                tLastName.setVisible(true);
                                lLastName2.setVisible(true);
                                tLastName2.setVisible(true);
                                lNumAmbulance.setVisible(false);
                                tNumAmbulance.setVisible(false);
                                lKmAmbulance.setVisible(false);
                                tKmAmbulance.setVisible(false);
                                bInsert.setVisible(false);
                                lId.setVisible(false);
                                tId.setVisible(false);
                                bDelete.setVisible(false);
                                bEdit.setVisible(false);
                                bSave.setVisible(true);
                                switch (mTipeJob.getText()) {
                                    case "Operador":
                                        employee = db.consultOperArray(Integer.valueOf(tId.getText()));
                                        break;
                                    case "Paramedico":
                                        employee = db.consultParamedicArray(Integer.valueOf(tId.getText()));
                                        break;
                                    case "Radio Operador":
                                        employee = db.consultRadioOperArray(Integer.valueOf(tId.getText()));
                                        break;
                                }
                                tName.setText(employee[1]);
                                tLastName.setText(employee[2]);
                                tLastName2.setText(employee[3]);
                                tId.setText(null);
                            }
                            break;
                        case "Emergencia":
                            if (tId.getText().equals("")) {
                                alert.setText("Deben ingresar el ID");
                                alert.setBackground(Color.RED);
                            } else {
                                JFrame emergency = new JFrame("Emergencia #" + tId.getText());
                                emergency.setSize(1600, 500);
                                emergency.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - emergency.getWidth() / 2,
                                        (Toolkit.getDefaultToolkit().getScreenSize().height / 3) - emergency.getHeight() / 2);
                                emergency.setFocusable(true);
                                emergency.setExtendedState(MAXIMIZED_BOTH);
                                emergency.setVisible(true);
                                emergency.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/cruzroja.png"));
                                SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Calendar calendario = new GregorianCalendar();
                                ctrTime cT = new ctrTime(time.format(calendario.getTime()));
                                tabData tab = new tabData();
                                EditEmergency eM = new EditEmergency(Toolkit.getDefaultToolkit().getScreenSize().width,
                                        tab, db, Integer.valueOf(tId.getText()));
                                emergency.setLayout(new BorderLayout());
                                emergency.add(tab, BorderLayout.NORTH);
                                emergency.add(eM, BorderLayout.CENTER);
                                emergency.add(cT, BorderLayout.SOUTH);
                            }
                            break;
                        case "Paciente":
                            if (tId.getText().equals("")) {
                                alert.setText("Deben ingresar el ID");
                                alert.setBackground(Color.RED);
                            } else {
                                JFrame patient = new JFrame("Paciente #" + tId.getText());
                                patient.setSize(470, 700);
                                patient.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - patient.getWidth() / 2,
                                        (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - patient.getHeight() / 2);
                                patient.setFocusable(true);
                                patient.setVisible(true);
                                patient.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/cruzroja.png"));
                                EditPatient eP = new EditPatient(db, Integer.valueOf(tId.getText()));
                                patient.setLayout(new BorderLayout());
                                patient.add(eP, BorderLayout.CENTER);
                            }
                            break;
                    }
                    break;
                case "Guardar":
                    employee[1] = tName.getText();
                    employee[2] = tLastName.getText();
                    employee[3] = tLastName2.getText();
                    lName.setVisible(false);
                    tName.setVisible(false);
                    lLastName.setVisible(false);
                    tLastName.setVisible(false);
                    lLastName2.setVisible(false);
                    tLastName2.setVisible(false);
                    lNumAmbulance.setVisible(false);
                    tNumAmbulance.setVisible(false);
                    lKmAmbulance.setVisible(false);
                    tKmAmbulance.setVisible(false);
                    bInsert.setVisible(false);
                    lId.setVisible(true);
                    tId.setVisible(true);
                    bDelete.setVisible(false);
                    bEdit.setVisible(true);
                    bSave.setVisible(false);
                    switch (mTipeJob.getText()) {
                        case "Operador":
                            System.out.println(db.editOper(employee));
                            aConsult.setText("Operador:\n" + db.consultOper());
                            break;
                        case "Paramedico":
                            System.out.println(db.editParamedic(employee));
                            aConsult.setText("Paramedico:\n" + db.consultParamedic());
                            break;
                        case "Radio Operador":
                            System.out.println(db.editRadioOper(employee));
                            aConsult.setText("Radio Operador:\n" + db.consultRadioOper());
                            break;
                    }
                    tName.setText(null);
                    tLastName.setText(null);
                    tLastName2.setText(null);
                    for (int i = 0; i < employee.length; i++) {
                        employee[i] = "";
                    }
                    break;
                case "Eliminar":
                    if (tId.getText().equals("")) {
                        alert.setText("Deben ingresar el ID");
                        alert.setBackground(Color.RED);
                    } else {
                        resultDB = "";
                        switch (mTipeJob.getText()) {
                            case "Ambulancia":
                                if (0 == JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar a "
                                        + db.consultAmbulance(Integer.valueOf(tId.getText())) + "?", "Alerta!",
                                        JOptionPane.YES_NO_OPTION)) {
                                    resultDB = db.deleteAmbulance(Integer.parseInt(tId.getText()));
                                }
                                break;
                            case "Operador":
                                if (0 == JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar a "
                                        + db.consultOper(Integer.valueOf(tId.getText())) + "?", "Alerta!",
                                        JOptionPane.YES_NO_OPTION)) {
                                    resultDB = db.deleteOper(Integer.parseInt(tId.getText()));
                                }
                                break;
                            case "Paramedico":
                                if (0 == JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar a "
                                        + db.consultParamedic(Integer.valueOf(tId.getText())) + "?", "Alerta!",
                                        JOptionPane.YES_NO_OPTION)) {
                                    resultDB = db.deleteParamedic(Integer.parseInt(tId.getText()));
                                }
                                break;
                            case "Radio Operador":
                                if (0 == JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar a "
                                        + db.consultRadioOper(Integer.valueOf(tId.getText())) + "?", "Alerta!",
                                        JOptionPane.YES_NO_OPTION)) {
                                    resultDB = db.deleteRadioOper(Integer.parseInt(tId.getText()));
                                }
                                break;
                            case "Emergencia":
                                if (0 == JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminarlo? ", "Alerta!",
                                        JOptionPane.YES_NO_OPTION)) {
                                    resultDB = db.deleteEmergency(Integer.parseInt(tId.getText()));
                                }
                                break;
                        }
                        switch (resultDB) {
                            case "successfully completed":
                                alert.setText("El " + mTipeJob.getText() + " con ID:" + tId.getText()
                                        + " a sido eliminado exitosamente");
                                tId.setText(null);
                                alert.setBackground(Color.GREEN);
                                break;
                            case "not found":
                                alert.setText("El " + mTipeJob.getText() + " con ID:" + tId.getText()
                                        + " no fue encontrado");
                                alert.setBackground(Color.RED);
                                break;
                            case "":
                                break;
                            default:
                                alert.setText(resultDB);
                                alert.setBackground(Color.RED);
                                break;
                        }
                    }
                    break;
            }
        } else {
            tName.setText(null);
            tLastName.setText(null);
            tLastName2.setText(null);
            for (int i = 0; i < employee.length; i++) {
                employee[i] = "";
            }
            switch (e.getActionCommand()) {
                case "Agregar":
                    mOption.setText(e.getActionCommand());
                    switch (mTipeJob.getText()) {
                        case "Ambulancia":
                            lName.setVisible(false);
                            tName.setVisible(false);
                            lLastName.setVisible(false);
                            tLastName.setVisible(false);
                            lLastName2.setVisible(false);
                            tLastName2.setVisible(false);
                            lNumAmbulance.setVisible(true);
                            tNumAmbulance.setVisible(true);
                            lKmAmbulance.setVisible(true);
                            tKmAmbulance.setVisible(true);
                            bInsert.setVisible(true);
                            lId.setVisible(false);
                            tId.setVisible(false);
                            bDelete.setVisible(false);
                            bEdit.setVisible(false);
                            bSave.setVisible(false);
                            break;
                        case "Operador":
                        case "Paramedico":
                        case "Radio Operador":
                            lName.setVisible(true);
                            tName.setVisible(true);
                            lLastName.setVisible(true);
                            tLastName.setVisible(true);
                            lLastName2.setVisible(true);
                            tLastName2.setVisible(true);
                            lNumAmbulance.setVisible(false);
                            tNumAmbulance.setVisible(false);
                            lKmAmbulance.setVisible(false);
                            tKmAmbulance.setVisible(false);
                            bInsert.setVisible(true);
                            lId.setVisible(false);
                            tId.setVisible(false);
                            bDelete.setVisible(false);
                            bEdit.setVisible(false);
                            bSave.setVisible(false);
                            break;
                    }
                    break;
                case "Editar":
                    mOption.setText(e.getActionCommand());
                    lName.setVisible(false);
                    tName.setVisible(false);
                    lLastName.setVisible(false);
                    tLastName.setVisible(false);
                    lLastName2.setVisible(false);
                    tLastName2.setVisible(false);
                    lNumAmbulance.setVisible(false);
                    tNumAmbulance.setVisible(false);
                    lKmAmbulance.setVisible(false);
                    tKmAmbulance.setVisible(false);
                    bInsert.setVisible(false);
                    lId.setVisible(true);
                    tId.setVisible(true);
                    bDelete.setVisible(false);
                    bEdit.setVisible(true);
                    bSave.setVisible(false);
                    break;
                case "Eliminar":
                    mOption.setText(e.getActionCommand());
                    lName.setVisible(false);
                    tName.setVisible(false);
                    lLastName.setVisible(false);
                    tLastName.setVisible(false);
                    lLastName2.setVisible(false);
                    tLastName2.setVisible(false);
                    lNumAmbulance.setVisible(false);
                    tNumAmbulance.setVisible(false);
                    lKmAmbulance.setVisible(false);
                    tKmAmbulance.setVisible(false);
                    bInsert.setVisible(false);
                    lId.setVisible(true);
                    tId.setVisible(true);
                    bDelete.setVisible(true);
                    bEdit.setVisible(false);
                    bSave.setVisible(false);
                    break;
                case "Ambulancia":
                case "Operador":
                case "Paramedico":
                case "Radio Operador":
                case "Emergencia":
                case "Paciente":
                    mTipeJob.setText(e.getActionCommand());
                    mOption.setText("Opción");
                    sConsult.setVisible(true);
                    aConsult.setPreferredSize(new Dimension(window.getWidth() - 50, 300));
                    lName.setVisible(false);
                    tName.setVisible(false);
                    lLastName.setVisible(false);
                    tLastName.setVisible(false);
                    lLastName2.setVisible(false);
                    tLastName2.setVisible(false);
                    lNumAmbulance.setVisible(false);
                    tNumAmbulance.setVisible(false);
                    lKmAmbulance.setVisible(false);
                    tKmAmbulance.setVisible(false);
                    bInsert.setVisible(false);
                    lId.setVisible(false);
                    tId.setVisible(false);
                    bDelete.setVisible(false);
                    bEdit.setVisible(false);
                    bSave.setVisible(false);
                    switch (mTipeJob.getText()) {
                        case "Ambulancia":
                            aConsult.setText("Ambulancia:\n" + db.consultAmbulance());
                            break;
                        case "Operador":
                            aConsult.setText("Operador:\n" + db.consultOper());
                            break;
                        case "Paramedico":
                            aConsult.setText("Paramedico:\n" + db.consultParamedic());
                            break;
                        case "Radio Operador":
                            aConsult.setText("Radio Operador:\n" + db.consultRadioOper());
                            break;
                        case "Emergencia":
                            aConsult.setText(null);
                            break;
                    }
                    break;
            }
        }
    }

}
