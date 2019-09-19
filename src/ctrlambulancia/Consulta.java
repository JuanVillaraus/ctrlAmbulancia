/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class Consulta extends JPanel implements ActionListener {

    ConxDB db;
    archivo a = new archivo();
    JMenu mOption = new JMenu("Opción");
    JMenu mOptionSearch = new JMenu("Opción para Buscar");
    JTextField tDateOpen = new JTextField(6);
    JTextField tDateClose = new JTextField(6);
    JTextField tDir = new JTextField(30);
    JTextField tId = new JTextField(8);
    JTextField tName = new JTextField(20);
    JTextArea tMain = new JTextArea();
    JLabel lDateOpen = new JLabel("De:");
    JLabel lDateClose = new JLabel("hasta:");
    JLabel lId = new JLabel("ID:");
    JLabel lDir = new JLabel("Ubicación:");
    JButton bSearch = new JButton("Buscar");

    public Consulta(JFrame window, ConxDB db) {
        this.db = db;
        JMenuBar mBarOption = new JMenuBar();
        JMenuBar mBarOptionSearch = new JMenuBar();
        JMenuItem iOption1 = new JMenuItem("Reporte Emergencia");
        JMenuItem iOption2 = new JMenuItem("Paciente");
        JMenuItem iOptionSearch1 = new JMenuItem("ID");
        JMenuItem iOptionSearch2 = new JMenuItem("Fecha");
        JMenuItem iOptionSearch3 = new JMenuItem("ID por Fecha");
        JMenuItem iOptionSearch4 = new JMenuItem("Ubicación");
        JMenuItem iOptionSearch5 = new JMenuItem("Resultado");
        JMenuItem iOptionSearch6 = new JMenuItem("Traslado");
        JMenuItem iOptionSearch7 = new JMenuItem("ID Operador");
        JMenuItem iOptionSearch8 = new JMenuItem("Nombre Operador");
        JMenuItem iOptionSearch9 = new JMenuItem("ID Paramedico");
        JMenuItem iOptionSearch10 = new JMenuItem("Nombre Paramedico");
        JMenuItem iOptionSearch11 = new JMenuItem("Operador Voluntario");
        JMenuItem iOptionSearch12 = new JMenuItem("Paramedico Voluntario");
        JPanel top = new JPanel();
        JScrollPane scMain = new JScrollPane(tMain);//, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        iOption1.addActionListener(this);
        iOption2.addActionListener(this);
        iOptionSearch1.addActionListener(this);
        iOptionSearch2.addActionListener(this);
        iOptionSearch3.addActionListener(this);
        iOptionSearch4.addActionListener(this);
        iOptionSearch5.addActionListener(this);
        iOptionSearch6.addActionListener(this);
        iOptionSearch7.addActionListener(this);
        iOptionSearch8.addActionListener(this);
        iOptionSearch9.addActionListener(this);
        iOptionSearch10.addActionListener(this);
        iOptionSearch11.addActionListener(this);
        iOptionSearch12.addActionListener(this);
        bSearch.addActionListener(this);
        mOption.add(iOption1);
        mOption.add(iOption2);
        mOptionSearch.add(iOptionSearch1);
        mOptionSearch.add(iOptionSearch2);
        mOptionSearch.add(iOptionSearch3);
        mOptionSearch.add(iOptionSearch4);
        mOptionSearch.add(iOptionSearch5);
        mOptionSearch.add(iOptionSearch6);
        mOptionSearch.add(iOptionSearch7);
        mOptionSearch.add(iOptionSearch8);
        mOptionSearch.add(iOptionSearch9);
        mOptionSearch.add(iOptionSearch10);
        mOptionSearch.add(iOptionSearch11);
        mOptionSearch.add(iOptionSearch12);
        mBarOption.add(mOption);
        mBarOptionSearch.add(mOptionSearch);
        mOption.setPreferredSize(new Dimension(150, 30));
        mOptionSearch.setPreferredSize(new Dimension(150, 30));
        tMain.setPreferredSize(new Dimension(window.getWidth() - 50, 600));
        lDateOpen.setVisible(false);
        tDateOpen.setVisible(false);
        lDateClose.setVisible(false);
        tDateClose.setVisible(false);
        lId.setVisible(false);
        tId.setVisible(false);
        tName.setVisible(false);
        lDir.setVisible(false);
        tDir.setVisible(false);
        bSearch.setVisible(false);
        top.add(mBarOption);
        top.add(mBarOptionSearch);
        top.add(lDateOpen);
        top.add(tDateOpen);
        top.add(lDateClose);
        top.add(tDateClose);
        top.add(lId);
        top.add(tId);
        top.add(tName);
        top.add(lDir);
        top.add(tDir);
        top.add(bSearch);
        this.add(top, BorderLayout.NORTH);
        this.add(scMain);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass().getSimpleName().equals("JButton")) {
            switch (mOption.getText()) {
                case "Reporte Emergencia":
                    String sEmergency;
                    if (tId.getText().equals("")) {
                        if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                            sEmergency = db.consultEmergency();
                            tMain.setText(sEmergency);
                            try {
                                a.escribirTxt("resource/Emergencia.txt", sEmergency);
                            } catch (IOException ex) {
                                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                            System.out.println("pendiente");
                        } else {
                            sEmergency = db.consultEmergency(tDateOpen.getText() + " 00:00:00", tDateClose.getText() + " 23:59:59");
                            tMain.setText(sEmergency);
                            try {
                                a.escribirTxt("resource/Emergencia " + tDateOpen.getText() + " " + tDateClose.getText() + ".txt", sEmergency);
                            } catch (IOException ex) {
                                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        tMain.setText(db.consultEmergency(Integer.valueOf(tId.getText())));
                    }
                    break;
                case "Paciente":
                    String sPatient;
                    if (tId.getText().equals("")) {
                        if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                            sPatient = db.consultPatientAll();
                            tMain.setText(sPatient);
                            try {
                                a.escribirTxt("resource/Paciente.txt", sPatient);
                            } catch (IOException ex) {
                                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                            if (tDateClose.getText().equals("")) {
                                sPatient = db.consultPatient(tDateOpen.getText() + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                tMain.setText(sPatient);
                                try {
                                    a.escribirTxt("resource/Paciente " + tDateOpen.getText() + ".txt", sPatient);
                                } catch (IOException ex) {
                                    Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } else {
                            sPatient = db.consultPatient(tDateOpen.getText() + " 00:00:00", tDateClose.getText() + " 23:59:59");
                            tMain.setText(sPatient);
                            try {
                                a.escribirTxt("resource/Paciente " + tDateOpen.getText() + " " + tDateClose.getText() + ".txt", sPatient);
                            } catch (IOException ex) {
                                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        tMain.setText(db.consultPatient(Integer.valueOf(tId.getText())));
                    }
                    break;
            }
        } else {
            switch (e.getActionCommand()) {
                case "Reporte Emergencia":
                case "Paciente":
                    mOption.setText(e.getActionCommand());
                    break;
                case "ID":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(true);
                    tName.setVisible(false);
                    lDateOpen.setVisible(false);
                    tDateOpen.setVisible(false);
                    lDateClose.setVisible(false);
                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Fecha":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "ID por Fecha":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(true);
                    tId.setVisible(true);
                    tName.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Ubicación":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
                    lDateOpen.setVisible(false);
                    tDateOpen.setVisible(false);
                    lDateClose.setVisible(false);
                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(true);
                    bSearch.setVisible(true);
                    break;
                case "Resultado":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
                    lDateOpen.setVisible(false);
                    tDateOpen.setVisible(false);
                    lDateClose.setVisible(false);
                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Traslado":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
                    lDateOpen.setVisible(false);
                    tDateOpen.setVisible(false);
                    lDateClose.setVisible(false);
                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "ID Operador":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
                    lDateOpen.setVisible(false);
                    tDateOpen.setVisible(false);
                    lDateClose.setVisible(false);
                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Nombre Operador":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
                    lDateOpen.setVisible(false);
                    tDateOpen.setVisible(false);
                    lDateClose.setVisible(false);
                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "ID Paramedico":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
                    lDateOpen.setVisible(false);
                    tDateOpen.setVisible(false);
                    lDateClose.setVisible(false);
                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    bSearch.setVisible(true);
                    break;

                case "Nombre Paramedico":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
                    lDateOpen.setVisible(false);
                    tDateOpen.setVisible(false);
                    lDateClose.setVisible(false);
                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Operador Voluntario":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
                    lDateOpen.setVisible(false);
                    tDateOpen.setVisible(false);
                    lDateClose.setVisible(false);
                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Paramedico Voluntario":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
                    lDateOpen.setVisible(false);
                    tDateOpen.setVisible(false);
                    lDateClose.setVisible(false);
                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    bSearch.setVisible(true);
                    break;
            }
        }
    }
}
