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
import java.awt.GridLayout;
import java.awt.event.*;
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
    JButton bDelete = new JButton("Eliminar");
    JButton bConsult = new JButton("Consultar");
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
    JTextArea aConsult = new JTextArea("bla bla bla \n bla bla \n bla");
    //JTable tConsult = new JTable(10,4);
    //JScrollPane sConsult = new JScrollPane(tConsult, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    JScrollPane sConsult = new JScrollPane(aConsult);

    public admin(JFrame window, ConxDB db, Interface frameMain) {
        this.window = window;
        this.db = db;
        this.frameMain = frameMain;
        JMenuBar mBarOption = new JMenuBar();
        JMenuBar mBarTipeJob = new JMenuBar();
        JMenuItem iInsert = new JMenuItem("Agregar");
        JMenuItem iDelete = new JMenuItem("Eliminar");
        JMenuItem iConsult = new JMenuItem("Consultar");
        JMenuItem iOper = new JMenuItem("Operador");
        JMenuItem iParamedic = new JMenuItem("Paramedico");
        JMenuItem iRadioOper = new JMenuItem("Radio Operador");
        JMenuItem iAmbulance = new JMenuItem("Ambulancia");
        mOption.add(iInsert);
        mOption.add(iDelete);
        mOption.add(iConsult);
        mBarOption.add(mOption);
        mTipeJob.add(iOper);
        mTipeJob.add(iParamedic);
        mTipeJob.add(iRadioOper);
        mTipeJob.add(iAmbulance);
        mBarTipeJob.add(mTipeJob);
        mTipeJob.setVisible(false);
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
        bDelete.setVisible(false);
        bConsult.setVisible(false);
        alert.setEditable(false);
        iInsert.addActionListener(this);
        iDelete.addActionListener(this);
        iConsult.addActionListener(this);
        iOper.addActionListener(this);
        iParamedic.addActionListener(this);
        iRadioOper.addActionListener(this);
        iAmbulance.addActionListener(this);
        bInsert.addActionListener(this);
        bDelete.addActionListener(this);
        mOption.setPreferredSize(new Dimension(330, 30));
        mTipeJob.setPreferredSize(new Dimension(330, 30));
        lId.setPreferredSize(new Dimension(60, 30));
        aConsult.setPreferredSize(new Dimension(window.getWidth() - 50, 300));
        //aConsult.setPreferredSize(new Dimension(window, 300));
        //sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel pAlert = new JPanel(new GridLayout(1, 1));
        JPanel pOption = new JPanel(new GridLayout(1, 1));
        //mInsert.setHorizontalAlignment(SwingConstants.CENTER);
        mOption.setHorizontalTextPosition(JTextField.CENTER);
        mTipeJob.setHorizontalTextPosition(JTextField.CENTER);
        alert.setHorizontalAlignment(JTextField.CENTER);
        lId.setHorizontalAlignment(JTextField.RIGHT);

        pOption.add(mBarOption);
        window.add(pOption, BorderLayout.NORTH);
        this.setLayout(new FlowLayout());
        this.add(mBarTipeJob);
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
        this.add(bDelete);
        //this.add(aConsult);
        this.add(sConsult, BorderLayout.CENTER);
        //this.add(bConsult);
        pAlert.add(alert);
        window.add(pAlert, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Admin/ActionPerformed$ Event: " + e.getActionCommand());
        if (e.getSource().getClass().getSimpleName().equals("JButton")) {
            switch (mOption.getText()) {
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
                                    System.out.println("result " + resultDB);
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
                                if (resultDB.toCharArray()[0] == 'R' && resultDB.toCharArray()[1] == 'P') {
                                    alert.setText("El paramedico ha sido agregado exitosamente");
                                    frameMain.menuRadioOperUpdate(resultDB + " " + tName.getText() + " " 
                                            + tLastName.getText() + " " + tLastName2.getText() + "\n");
                                    tName.setText(null);
                                    tLastName.setText(null);
                                    tLastName2.setText(null);
                                    alert.setBackground(Color.GREEN);
                                } else {
                                    alert.setText(resultDB);
                                    alert.setBackground(Color.RED);
                                }
                            }
                            break;
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
            mTipeJob.setText(e.getActionCommand());
            switch (e.getActionCommand()) {
                case "Agregar":
                    mOption.setText(e.getActionCommand());
                    mTipeJob.setText("Opción");
                    mTipeJob.setVisible(true);
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
                    sConsult.setVisible(false);
                    bConsult.setVisible(false);
                    break;
                case "Eliminar":
                    mOption.setText(e.getActionCommand());
                    mTipeJob.setText("Opción");
                    mTipeJob.setVisible(true);
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
                    sConsult.setVisible(false);
                    bConsult.setVisible(false);
                    break;
                case "Consultar":
                    mOption.setText(e.getActionCommand());
                    mTipeJob.setText("Opción");
                    mTipeJob.setVisible(true);
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
                    sConsult.setVisible(false);
                    bConsult.setVisible(false);
                    break;
                case "Ambulancia":
                    switch (mOption.getText()) {
                        case "Agregar":
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
                            sConsult.setVisible(false);
                            bConsult.setVisible(false);
                            break;
                        case "Eliminar":
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
                            sConsult.setVisible(false);
                            bConsult.setVisible(false);
                            break;
                        case "Consultar":
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
                            sConsult.setVisible(true);
                            bConsult.setVisible(false);
                            aConsult.setText("Ambulancia:\n" + db.consultAmbulance());
                            aConsult.setPreferredSize(new Dimension(window.getWidth() - 50, 300));
                            //aConsult.setPreferredSize(new Dimension(window, 300));
                            //sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                            break;
                    }
                    break;
                case "Operador":
                case "Paramedico":
                case "Radio Operador":
                    switch (mOption.getText()) {
                        case "Agregar":
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
                            sConsult.setVisible(false);
                            bConsult.setVisible(false);
                            break;
                        case "Eliminar":
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
                            sConsult.setVisible(false);
                            bConsult.setVisible(false);
                            break;
                        case "Consultar":
                            switch (e.getActionCommand()) {
                                case "Operador":
                                    sConsult.setVisible(true);
                                    aConsult.setText("Operador:\n" + db.consultOper());
                                    aConsult.setPreferredSize(new Dimension(window.getWidth() - 50, 300));
                                    //aConsult.setPreferredSize(new Dimension(window, 300));
                                    break;
                                case "Paramedico":
                                    sConsult.setVisible(true);
                                    aConsult.setText("Paramedico:\n" + db.consultParamedic());
                                    aConsult.setPreferredSize(new Dimension(window.getWidth() - 50, 300));
                                    //aConsult.setPreferredSize(new Dimension(window, 300));
                                    break;
                                case "Radio Operador":
                                    sConsult.setVisible(true);
                                    aConsult.setText("Radio Operador:\n" + db.consultRadioOper());
                                    aConsult.setPreferredSize(new Dimension(window.getWidth() - 50, 300));
                                    //aConsult.setPreferredSize(new Dimension(window, 300));
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
    }

}
