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
    JLabel lDateOpen = new JLabel("Fecha de:");
    JLabel lDateClose = new JLabel("hasta:");
    JLabel lId = new JLabel("ID:");
    JLabel lDir = new JLabel("Ubicación:");
    JButton bSearch = new JButton("Buscar");
    JMenu mResultado = new JMenu("seleccione una opción");
    JMenu mPriorityTransfer = new JMenu("seleccione una opción");
    JMenu mTransfer = new JMenu("seleccione una opción");
    JMenu mOper = new JMenu("seleccione una opción");
    JMenu mRadioOper = new JMenu("seleccione una opción");
    JMenu mParamedic = new JMenu("seleccione una opción");
    JMenu mAmbulance = new JMenu("seleccione una opción");

    public Consulta(JFrame window, ConxDB db) {
        this.db = db;
        JMenuBar mBarOption = new JMenuBar();
        JMenuBar mBarOptionSearch = new JMenuBar();
        JMenuItem iOption1 = new JMenuItem("Reporte Emergencia");
        JMenuItem iOption2 = new JMenuItem("Paciente");
        JMenuItem iOptionSearch1 = new JMenuItem("ID");
        JMenuItem iOptionSearch2 = new JMenuItem("Ubicación");
        JMenuItem iOptionSearch3 = new JMenuItem("Resultado");
        JMenuItem iOptionSearch4 = new JMenuItem("Traslado");
        JMenuItem iOptionSearch5 = new JMenuItem("Prioridad del traslado");
        JMenuItem iOptionSearch6 = new JMenuItem("Operador");
        JMenuItem iOptionSearch7 = new JMenuItem("Paramedico");
        JMenuItem iOptionSearch8 = new JMenuItem("Operador Voluntario");
        JMenuItem iOptionSearch9 = new JMenuItem("Paramedico Voluntario");
        JMenuItem iOptionSearch10 = new JMenuItem("Radio Operador");
        JMenuItem iOptionSearch11 = new JMenuItem("Ambulancia");

        JMenuBar mBarResultado = new JMenuBar();
        JMenuBar mBarPriorityTransfer = new JMenuBar();
        JMenuBar mBarTransfer = new JMenuBar();
        JMenuBar mBarOper = new JMenuBar();
        JMenuBar mBarRadioOper = new JMenuBar();
        JMenuBar mBarParamedic = new JMenuBar();
        JMenuBar mBarAmbulance = new JMenuBar();
        JMenuItem iResultado1 = new JMenuItem("Falsa alarma");
        JMenuItem iResultado2 = new JMenuItem("No encontrado");
        JMenuItem iResultado3 = new JMenuItem("Tranf por tercero");
        JMenuItem iResultado4 = new JMenuItem("No necesita tranf");
        JMenuItem iResultado5 = new JMenuItem("No quiere tranf");
        JMenuItem iResultado6 = new JMenuItem("traslado");
        JMenuItem iResultado7 = new JMenuItem("Fallecido");
        JMenuItem iPriority1 = new JMenuItem("Prioridad 1");
        JMenuItem iPriority2 = new JMenuItem("Prioridad 2");
        JMenuItem iPriority3 = new JMenuItem("Prioridad 3");
        JMenuItem iTransfer1 = new JMenuItem("CRUZ ROJA");
        JMenuItem iTransfer2 = new JMenuItem("SM DDF");
        JMenuItem iTransfer3 = new JMenuItem("ISSSTE");
        JMenuItem iTransfer4 = new JMenuItem("IMSS");
        JMenuItem iTransfer5 = new JMenuItem("SSA");
        JMenuItem iTransfer6 = new JMenuItem("PEMEX");
        JMenuItem iTransfer7 = new JMenuItem("MILITAR");
        JMenuItem iTransfer8 = new JMenuItem("NAVAL");
        JMenuItem iTransfer9 = new JMenuItem("PRIVADO");
        JMenuItem iTransfer10 = new JMenuItem("OTRO");
        iResultado1.addActionListener(this);
        iResultado2.addActionListener(this);
        iResultado3.addActionListener(this);
        iResultado4.addActionListener(this);
        iResultado5.addActionListener(this);
        iResultado6.addActionListener(this);
        iResultado7.addActionListener(this);
        iPriority1.addActionListener(this);
        iPriority2.addActionListener(this);
        iPriority3.addActionListener(this);
        iTransfer1.addActionListener(this);
        iTransfer2.addActionListener(this);
        iTransfer3.addActionListener(this);
        iTransfer4.addActionListener(this);
        iTransfer5.addActionListener(this);
        iTransfer6.addActionListener(this);
        iTransfer7.addActionListener(this);
        iTransfer8.addActionListener(this);
        iTransfer9.addActionListener(this);
        iTransfer10.addActionListener(this);
        mResultado.add(iResultado1);
        mResultado.add(iResultado2);
        mResultado.add(iResultado3);
        mResultado.add(iResultado4);
        mResultado.add(iResultado5);
        mResultado.add(iResultado6);
        mResultado.add(iResultado7);
        mBarResultado.add(mResultado);
        mPriorityTransfer.add(iPriority1);
        mPriorityTransfer.add(iPriority2);
        mPriorityTransfer.add(iPriority3);
        mBarPriorityTransfer.add(mPriorityTransfer);
        mTransfer.add(iTransfer1);
        mTransfer.add(iTransfer2);
        mTransfer.add(iTransfer3);
        mTransfer.add(iTransfer4);
        mTransfer.add(iTransfer5);
        mTransfer.add(iTransfer6);
        mTransfer.add(iTransfer7);
        mTransfer.add(iTransfer8);
        mTransfer.add(iTransfer9);
        mTransfer.add(iTransfer10);
        mBarTransfer.add(mTransfer);

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
        mBarOption.add(mOption);
        mBarOptionSearch.add(mOptionSearch);
        mOption.setPreferredSize(new Dimension(150, 30));
        mOptionSearch.setPreferredSize(new Dimension(150, 30));
        tMain.setPreferredSize(new Dimension(window.getWidth() - 50, 600));
//        lDateOpen.setVisible(false);
//        tDateOpen.setVisible(false);
//        lDateClose.setVisible(false);
//        tDateClose.setVisible(false);
        lId.setVisible(false);
        tId.setVisible(false);
        tName.setVisible(false);
        mResultado.setVisible(false);
        mPriorityTransfer.setVisible(false);
        mTransfer.setVisible(false);
        mOper.setVisible(false);
        mRadioOper.setVisible(false);
        mParamedic.setVisible(false);
        mAmbulance.setVisible(false);
        lDir.setVisible(false);
        tDir.setVisible(false);
//        bSearch.setVisible(false);
        menuUpdate();
        mBarOper.add(mOper);
        mBarRadioOper.add(mRadioOper);
        mBarParamedic.add(mParamedic);
        mBarAmbulance.add(mAmbulance);
        mOper.setPreferredSize(new Dimension(250, 20));
        mRadioOper.setPreferredSize(new Dimension(250, 20));
        mParamedic.setPreferredSize(new Dimension(250, 20));
        mAmbulance.setPreferredSize(new Dimension(150, 20));
        mResultado.setPreferredSize(new Dimension(150, 30));
        mPriorityTransfer.setPreferredSize(new Dimension(150, 30));
        mTransfer.setPreferredSize(new Dimension(150, 30));
        mResultado.setHorizontalAlignment(SwingConstants.CENTER);
        mResultado.setHorizontalTextPosition(JTextField.CENTER);

        //top.add(mBarOption);
        top.add(mBarOptionSearch);
        top.add(lId);
        top.add(tId);
        top.add(tName);
        top.add(mBarResultado);
        top.add(mBarPriorityTransfer);
        top.add(mBarTransfer);
        top.add(mBarOper);
        top.add(mBarRadioOper);
        top.add(mBarParamedic);
        top.add(mBarAmbulance);
        top.add(lDir);
        top.add(tDir);
        top.add(lDateOpen);
        top.add(tDateOpen);
        top.add(lDateClose);
        top.add(tDateClose);
        top.add(bSearch);
        this.add(top, BorderLayout.NORTH);
        this.add(scMain);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass().getSimpleName().equals("JButton")) {
            String sEmergency;
            switch (mOptionSearch.getText()) {
                case "Opción para Buscar":
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
                    break;
                case "ID":
//                    tMain.setText(db.consultEmergency(Integer.valueOf(tId.getText())));
                    tMain.setText("pendiente");
                    break;
                case "Ubicación":
                case "Resultado":
                    if (mResultado.getText().equals("seleccione una opción")) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar", "ERROR", JOptionPane.WARNING_MESSAGE);
                    } else {
                        sEmergency = db.consultEmergencyResultado(mResultado.getText());
                        tMain.setText(sEmergency);
                        try {
                            a.escribirTxt("resource/Emergencias " + mResultado.getText() + ".txt", sEmergency);
                        } catch (IOException ex) {
                            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case "Traslado":
                    if (mTransfer.getText().equals("seleccione una opción")) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar", "ERROR", JOptionPane.WARNING_MESSAGE);
                    } else {
                        sEmergency = db.consultEmergencyTransfer(mTransfer.getText());
                        tMain.setText(sEmergency);
                        try {
                            a.escribirTxt("resource/Emergencias a " + mTransfer.getText() + ".txt", sEmergency);
                        } catch (IOException ex) {
                            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case "Prioridad del traslado":
                    if (mPriorityTransfer.getText().equals("seleccione una opción")) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar", "ERROR", JOptionPane.WARNING_MESSAGE);
                    } else {
                        sEmergency = db.consultEmergencyPriorityTransfer(Integer.valueOf("" + mPriorityTransfer.getText().toCharArray()[10]));
                        tMain.setText(sEmergency);
                        try {
                            a.escribirTxt("resource/Emergencias con " + mPriorityTransfer.getText() + " del Traslado.txt", sEmergency);
                        } catch (IOException ex) {
                            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case "Operador":
                    if (mOper.getText().equals("seleccione una opción")) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar", "ERROR", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int idOper = 0;
                        String word = "";
                        char[] cadena = mOper.getText().toCharArray();
                        for (int i = 3; i < cadena.length; i++) {
                            if (cadena[i] == ' ') {
                                idOper = Integer.valueOf(word);
                                i = cadena.length;
                            } else {
                                word += cadena[i];
                            }
                        }
                        sEmergency = db.consultEmergencyOper(idOper);
                        tMain.setText(sEmergency);
                        try {
                            a.escribirTxt("resource/Emergencias con " + mOper.getText() + ".txt", sEmergency);
                        } catch (IOException ex) {
                            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case "Paramedico":
                    if (mParamedic.getText().equals("seleccione una opción")) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar", "ERROR", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int idParamedic = 0;
                        String word = "";
                        char[] cadena = mParamedic.getText().toCharArray();
                        for (int i = 3; i < cadena.length; i++) {
                            if (cadena[i] == ' ') {
                                idParamedic = Integer.valueOf(word);
                                i = cadena.length;
                            } else {
                                word += cadena[i];
                            }
                        }
                        sEmergency = db.consultEmergencyParamedic(idParamedic);
                        tMain.setText(sEmergency);
                        try {
                            a.escribirTxt("resource/Emergencias con " + mParamedic.getText() + ".txt", sEmergency);
                        } catch (IOException ex) {
                            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case "Operador Voluntario":
                case "Paramedico Voluntario":
                case "Radio Operador":
                    if (mRadioOper.getText().equals("seleccione una opción")) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar", "ERROR", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int idRadioOper = 0;
                        String word = "";
                        char[] cadena = mRadioOper.getText().toCharArray();
                        for (int i = 3; i < cadena.length; i++) {
                            if (cadena[i] == ' ') {
                                idRadioOper = Integer.valueOf(word);
                                i = cadena.length;
                            } else {
                                word += cadena[i];
                            }
                        }
                        sEmergency = db.consultEmergencyRadioOper(idRadioOper);
                        tMain.setText(sEmergency);
                        try {
                            a.escribirTxt("resource/Emergencias con " + mRadioOper.getText() + ".txt", sEmergency);
                        } catch (IOException ex) {
                            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                case "Ambulancia":
                    if (mAmbulance.getText().equals("seleccione una opción")) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar", "ERROR", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int idAmbulance = 0;
                        String word = "";
                        char[] cadena = mAmbulance.getText().toCharArray();
                        for (int i = 3; i < cadena.length; i++) {
                            if (cadena[i] == ' ') {
                                idAmbulance = Integer.valueOf(word);
                                i = cadena.length;
                            } else {
                                word += cadena[i];
                            }
                        }
                        sEmergency = db.consultEmergencyAmbulance(idAmbulance);
                        tMain.setText(sEmergency);
                        try {
                            a.escribirTxt("resource/Emergencias con AB#" + idAmbulance + ".txt", sEmergency);
                        } catch (IOException ex) {
                            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
//                    lDateOpen.setVisible(false);
//                    tDateOpen.setVisible(false);
//                    lDateClose.setVisible(false);
//                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    bSearch.setVisible(true);
                    break;
//                case "Fecha":
//                    mOptionSearch.setText(e.getActionCommand());
//                    lId.setVisible(false);
//                    tId.setVisible(false);
//                    tName.setVisible(false);
////                    lDateOpen.setVisible(true);
////                    tDateOpen.setVisible(true);
////                    lDateClose.setVisible(true);
////                    tDateClose.setVisible(true);
//                    lDir.setVisible(false);
//                    tDir.setVisible(false);
//                    mResultado.setVisible(false);
//                    mPriorityTransfer.setVisible(false);
//                    mTransfer.setVisible(false);
//                    mOper.setVisible(false);
//                    mRadioOper.setVisible(false);
//                    mParamedic.setVisible(false);
//                    mAmbulance.setVisible(false);
//                    bSearch.setVisible(true);
//                    break;
                case "Ubicación":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
//                    lDateOpen.setVisible(false);
//                    tDateOpen.setVisible(false);
//                    lDateClose.setVisible(false);
//                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(true);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Resultado":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
//                    lDateOpen.setVisible(false);
//                    tDateOpen.setVisible(false);
//                    lDateClose.setVisible(false);
//                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(true);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Traslado":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
//                    lDateOpen.setVisible(false);
//                    tDateOpen.setVisible(false);
//                    lDateClose.setVisible(false);
//                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(true);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Prioridad del traslado":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
//                    lDateOpen.setVisible(false);
//                    tDateOpen.setVisible(false);
//                    lDateClose.setVisible(false);
//                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(true);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Operador":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
//                    lDateOpen.setVisible(false);
//                    tDateOpen.setVisible(false);
//                    lDateClose.setVisible(false);
//                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(true);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Paramedico":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
//                    lDateOpen.setVisible(false);
//                    tDateOpen.setVisible(false);
//                    lDateClose.setVisible(false);
//                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(true);
                    mAmbulance.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Operador Voluntario":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(true);
//                    lDateOpen.setVisible(false);
//                    tDateOpen.setVisible(false);
//                    lDateClose.setVisible(false);
//                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Paramedico Voluntario":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(true);
//                    lDateOpen.setVisible(false);
//                    tDateOpen.setVisible(false);
//                    lDateClose.setVisible(false);
//                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Radio Operador":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
//                    lDateOpen.setVisible(false);
//                    tDateOpen.setVisible(false);
//                    lDateClose.setVisible(false);
//                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(true);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    bSearch.setVisible(true);
                    break;
                case "Ambulancia":
                    mOptionSearch.setText(e.getActionCommand());
                    lId.setVisible(false);
                    tId.setVisible(false);
                    tName.setVisible(false);
//                    lDateOpen.setVisible(false);
//                    tDateOpen.setVisible(false);
//                    lDateClose.setVisible(false);
//                    tDateClose.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(true);
                    bSearch.setVisible(true);
                    break;
                case "Falsa alarma":
                case "No encontrado":
                case "Tranf por tercero":
                case "No necesita tranf":
                case "No quiere tranf":
                case "traslado":
                case "Fallecido":
                    mResultado.setText(e.getActionCommand());
                    break;
                case "Prioridad 1":
                case "Prioridad 2":
                case "Prioridad 3":
                    mPriorityTransfer.setText(e.getActionCommand());
                case "CRUZ ROJA":
                case "SM DDF":
                case "ISSSTE":
                case "IMSS":
                case "SSA":
                case "PEMEX":
                case "MILITAR":
                case "NAVAL":
                case "PRIVADO":
                case "OTRO":
                    mTransfer.setText(e.getActionCommand());
                    break;
                default:
                    String cmd = "" + e.getActionCommand().toCharArray()[0] + e.getActionCommand().toCharArray()[1];
                    switch (cmd) {
                        case "PM":
                            mParamedic.setText(e.getActionCommand());
                            break;
                        case "OP":
                            mOper.setText(e.getActionCommand());
                            break;
                        case "RO":
                            mRadioOper.setText(e.getActionCommand());
                            break;
                        case "AB":
                            mAmbulance.setText(e.getActionCommand());
                            break;
                    }
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
