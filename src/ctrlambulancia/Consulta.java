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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import com.mxrck.autocompleter.TextAutoCompleter;

/**
 *
 * @author Sistemas
 */
public class Consulta extends JPanel implements ActionListener {

    ConxDB db;
    archivo a = new archivo();
    MonitorActivity ma = new MonitorActivity();
    SimpleDateFormat month = new SimpleDateFormat("yyyy-MM");
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendario = new GregorianCalendar();
    JMenu mOption = new JMenu("Opción");
    JMenu mOptionSearch = new JMenu("Opción para Buscar");
    JTextField tDateOpen = new JTextField(6);
    JTextField tDateClose = new JTextField(6);
    JTextField tDir = new JTextField(30);
    JTextField tFrap = new JTextField(8);
    JTextField tName = new JTextField(20);
    JTextField tPatient = new JTextField(30);
    JTextArea eMain = new JTextArea(46, 140);
//    JEditorPane eMain = new JEditorPane();
    JLabel lDateOpen = new JLabel("Fecha de:");
    JLabel lDateClose = new JLabel("hasta:");
    JLabel lFrap = new JLabel("ID:");
    JLabel lDir = new JLabel("Ubicación:");
    JMenu mResultado = new JMenu("seleccione una opción");
    JMenu mPriorityTransfer = new JMenu("seleccione una opción");
    JMenu mTransfer = new JMenu("seleccione una opción");
    JMenu mOper = new JMenu("seleccione una opción");
    JMenu mRadioOper = new JMenu("seleccione una opción");
    JMenu mParamedic = new JMenu("seleccione una opción");
    JMenu mAmbulance = new JMenu("seleccione una opción");
    TextAutoCompleter autoPatient;

    public Consulta(JFrame window, ConxDB db) {
        this.db = db;
        this.db.setMa(ma);
        this.a.setMa(ma);
        JButton bSearch = new JButton("Buscar");
        JButton bReportAll = new JButton("Reporte Historial");
        JButton bReport = new JButton("Reporte Mensual");
        JMenuBar mBarOption = new JMenuBar();
        JMenuBar mBarOptionSearch = new JMenuBar();
        JMenuItem iOption1 = new JMenuItem("Reporte Emergencia");
        JMenuItem iOption2 = new JMenuItem("Paciente");
        JMenuItem iOptionSearch0 = new JMenuItem("Emergencia");
        JMenuItem iOptionSearch1 = new JMenuItem("Emergencia sin FRAP");
        JMenuItem iOptionSearch2 = new JMenuItem("FRAP");
        JMenuItem iOptionSearch3 = new JMenuItem("Ubicación");
        JMenuItem iOptionSearch4 = new JMenuItem("Resultado");
        JMenuItem iOptionSearch5 = new JMenuItem("Traslado");
        JMenuItem iOptionSearch6 = new JMenuItem("Prioridad del traslado");
        JMenuItem iOptionSearch7 = new JMenuItem("Operador");
        JMenuItem iOptionSearch8 = new JMenuItem("Paramedico");
        JMenuItem iOptionSearch9 = new JMenuItem("Operador Voluntario");
        JMenuItem iOptionSearch10 = new JMenuItem("Paramedico Voluntario");
        JMenuItem iOptionSearch11 = new JMenuItem("Radio Operador");
        JMenuItem iOptionSearch12 = new JMenuItem("Ambulancia");
        JMenuItem iOptionSearch13 = new JMenuItem("Paciente X Nombre");
        JMenuItem iOptionSearch14 = new JMenuItem("Paciente X Fecha");
        JMenuItem iOptionSearch15 = new JMenuItem("Folios diarios");

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
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(eMain);
        eMain.setLineWrap(true);
        eMain.setWrapStyleWord(true);

        iOption1.addActionListener(this);
        iOption2.addActionListener(this);
        iOptionSearch0.addActionListener(this);
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
        iOptionSearch13.addActionListener(this);
        iOptionSearch14.addActionListener(this);
        iOptionSearch15.addActionListener(this);
        bSearch.addActionListener(this);
        bReport.addActionListener(this);
        bReportAll.addActionListener(this);
        mOption.add(iOption1);
        mOption.add(iOption2);
        mOptionSearch.add(iOptionSearch0);
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
        mOptionSearch.add(iOptionSearch13);
        mOptionSearch.add(iOptionSearch14);
        mOptionSearch.add(iOptionSearch15);
        mBarOption.add(mOption);
        mBarOptionSearch.add(mOptionSearch);
        mOption.setPreferredSize(new Dimension(150, 30));
        mOptionSearch.setPreferredSize(new Dimension(150, 30));
        lFrap.setVisible(false);
        tFrap.setVisible(false);
        tName.setVisible(false);
        tPatient.setVisible(false);
        mResultado.setVisible(false);
        mPriorityTransfer.setVisible(false);
        mTransfer.setVisible(false);
        mOper.setVisible(false);
        mRadioOper.setVisible(false);
        mParamedic.setVisible(false);
        mAmbulance.setVisible(false);
        lDir.setVisible(false);
        tDir.setVisible(false);
        autoPatient = new TextAutoCompleter(tPatient);
        autoPatient.setMode(0);
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
//        eMain.setPreferredSize(new Dimension(1500, 750));
        mResultado.setHorizontalAlignment(SwingConstants.CENTER);
        mResultado.setHorizontalTextPosition(JTextField.CENTER);

        top.add(mBarOptionSearch);
        top.add(lFrap);
        top.add(tFrap);
        top.add(tName);
        top.add(tPatient);
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
        top.add(bReport);
        top.add(bReportAll);
        this.add(top, BorderLayout.NORTH);
        this.add(scroll, BorderLayout.CENTER);
//        this.add(ma, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("event: " + e.getActionCommand());
        if (e.getSource().getClass().getSimpleName().equals("JButton")) {
            String sEmergency;
            eMain.setText("");
            switch (e.getActionCommand()) {
                case "Reporte Historial":
                    try {
                        a.writeExcelData("C:/CtrlAmb/reporte historial.xlsx",
                                "hoja1", db.reportEmergency());
                        Runtime.getRuntime().exec("cmd /c start C:\\CtrlAmb\\reporte\" historial\".xlsx");
                    } catch (IOException ex) {
                        Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("ERROR: " + ex);
                        eMain.setText("error: " + ex);
                    }
                    break;
                case "Reporte Mensual":
                    if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                        try {
                            a.writeExcelData("C:/CtrlAmb/reporte mensual.xlsx",
                                    "hoja1", db.reportEmergency(month.format(calendario.getTime()) + "-01 00:00:00",
                                            month.format(calendario.getTime()) + "-31 23:59:59"));
                            Runtime.getRuntime().exec("cmd /c start C:\\CtrlAmb\\reporte\" mensual\".xlsx");
                        } catch (IOException ex) {
                            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("ERROR: " + ex);
                            eMain.setText("error: " + ex);
                        }
                    } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                        if (tDateOpen.getText().equals("")) {
                            try {
                                a.writeExcelData("C:/CtrlAmb/reporte mensual.xlsx",
                                        "hoja1", db.reportEmergency(tDateClose.getText()
                                                + " 00:00:00", tDateClose.getText() + " 23:59:59"));
                                Runtime.getRuntime().exec("cmd /c start C:\\CtrlAmb\\reporte\" mensual\".xlsx");
                            } catch (IOException ex) {
                                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println("ERROR: " + ex);
                                eMain.setText("error: " + ex);
                            }
                        } else {
                            try {
                                a.writeExcelData("C:/CtrlAmb/reporte mensual.xlsx",
                                        "hoja1", db.reportEmergency(tDateOpen.getText()
                                                + " 00:00:00", tDateOpen.getText() + " 23:59:59"));
                                Runtime.getRuntime().exec("cmd /c start C:\\CtrlAmb\\reporte\" mensual\".xlsx");
                            } catch (IOException ex) {
                                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println("ERROR: " + ex);
                                eMain.setText("error: " + ex);
                            }
                        }
                    } else {
                        try {
                            a.writeExcelData("C:/CtrlAmb/reporte mensual.xlsx",
                                    "hoja1", db.reportEmergency(tDateOpen.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59"));
                            Runtime.getRuntime().exec("cmd /c start C:\\CtrlAmb\\reporte\" mensual\".xlsx");
                        } catch (IOException ex) {
                            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("ERROR: " + ex);
                            eMain.setText("error: " + ex);
                        }
                    }
                    break;
                default:
                    switch (mOptionSearch.getText()) {
                        case "Opción para Buscar":
                        case "Emergencia":
                            if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                sEmergency = db.consultEmergency(month.format(calendario.getTime()) + "-01 00:00:00",
                                        month.format(calendario.getTime()) + "-31 23:59:59");
                                eMain.setText(sEmergency);
                            } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                if (tDateOpen.getText().equals("")) {
                                    sEmergency = db.consultEmergency(tDateClose.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                } else {
                                    sEmergency = db.consultEmergency(tDateOpen.getText()
                                            + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            } else {
                                sEmergency = db.consultEmergency(tDateOpen.getText() + " 00:00:00",
                                        tDateClose.getText() + " 23:59:59");
                                eMain.setText(sEmergency);
                            }
                            break;
                        case "Emergencia sin FRAP":
                            if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                try {
                                    a.writeExcelData("C:/CtrlAmb/reporte historial sin FRAP.xlsx",
                                            "hoja1", db.reportEmergencyAll());
                                    Runtime.getRuntime().exec("cmd /c start C:\\CtrlAmb\\reporte\" historial sin FRAP\".xlsx");
                                } catch (IOException ex) {
                                    Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
                                    System.out.println("ERROR: " + ex);
                                    eMain.setText("error: " + ex);
                                }
                            }
                            break;
                        case "FRAP":
                            sEmergency = db.consultFRAP(tFrap.getText());
                            eMain.setText(sEmergency);
                            break;
                        case "Ubicación":
                            if (tDir.getText() == null || tDir.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Debe ingresar la dirección que desea buscar",
                                        "ERROR", JOptionPane.WARNING_MESSAGE);
                            } else {
                                if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                    sEmergency = db.consultEmergencyDir(tDir.getText());
                                    eMain.setText(sEmergency);
                                } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                    if (tDateOpen.getText().equals("")) {
                                        sEmergency = db.consultEmergencyDir(tDir.getText(), tDateClose.getText()
                                                + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    } else {
                                        sEmergency = db.consultEmergencyDir(tDir.getText(), tDateOpen.getText()
                                                + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    }
                                } else {
                                    sEmergency = db.consultEmergencyDir(tDir.getText(), tDateOpen.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            }
                            break;
                        case "Resultado":
                            if (mResultado.getText().equals("seleccione una opción")) {
                                JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar",
                                        "ERROR", JOptionPane.WARNING_MESSAGE);
                            } else {
                                String resultado = mResultado.getText();
                                if (resultado == "traslado") {
                                    resultado = "Traslado";
                                }
                                if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                    sEmergency = db.consultEmergencyResultado(resultado);
                                    eMain.setText(sEmergency);
                                } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                    if (tDateOpen.getText().equals("")) {
                                        sEmergency = db.consultEmergencyResultado(resultado, tDateClose.getText()
                                                + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    } else {
                                        sEmergency = db.consultEmergencyResultado(resultado, tDateOpen.getText()
                                                + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    }
                                } else {
                                    sEmergency = db.consultEmergencyResultado(resultado, tDateOpen.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            }
                            break;
                        case "Traslado":
                            if (mTransfer.getText().equals("seleccione una opción")) {
                                JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar",
                                        "ERROR", JOptionPane.WARNING_MESSAGE);
                            } else {
                                if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                    sEmergency = db.consultEmergencyTransfer(mTransfer.getText());
                                    eMain.setText(sEmergency);
                                } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                    if (tDateOpen.getText().equals("")) {
                                        sEmergency = db.consultEmergencyTransfer(mTransfer.getText(), tDateClose.getText()
                                                + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    } else {
                                        sEmergency = db.consultEmergencyTransfer(mTransfer.getText(), tDateOpen.getText()
                                                + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    }
                                } else {
                                    sEmergency = db.consultEmergencyTransfer(mTransfer.getText(), tDateOpen.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            }
                            break;
                        case "Prioridad del traslado":
                            if (mPriorityTransfer.getText().equals("seleccione una opción")) {
                                JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar",
                                        "ERROR", JOptionPane.WARNING_MESSAGE);
                            } else {
                                if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                    sEmergency = db.consultEmergencyPriorityTransfer(Integer.valueOf("" + mPriorityTransfer.getText().toCharArray()[10]));
                                    eMain.setText(sEmergency);
                                } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                    if (tDateOpen.getText().equals("")) {
                                        sEmergency = db.consultEmergencyPriorityTransfer(Integer.valueOf(""
                                                + mPriorityTransfer.getText().toCharArray()[10]), tDateClose.getText()
                                                + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    } else {
                                        sEmergency = db.consultEmergencyPriorityTransfer(Integer.valueOf(""
                                                + mPriorityTransfer.getText().toCharArray()[10]), tDateOpen.getText()
                                                + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    }
                                } else {
                                    sEmergency = db.consultEmergencyPriorityTransfer(Integer.valueOf(""
                                            + mPriorityTransfer.getText().toCharArray()[10]), tDateOpen.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            }
                            break;
                        case "Operador":
                            if (mOper.getText().equals("seleccione una opción")) {
                                JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar",
                                        "ERROR", JOptionPane.WARNING_MESSAGE);
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
                                if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                    sEmergency = db.consultEmergencyOper(idOper);
                                    eMain.setText(sEmergency);
                                } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                    if (tDateOpen.getText().equals("")) {
                                        sEmergency = db.consultEmergencyOper(idOper, tDateClose.getText()
                                                + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    } else {
                                        sEmergency = db.consultEmergencyOper(idOper, tDateOpen.getText()
                                                + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    }
                                } else {
                                    sEmergency = db.consultEmergencyOper(idOper, tDateOpen.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            }
                            break;
                        case "Paramedico":
                            if (mParamedic.getText().equals("seleccione una opción")) {
                                JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar",
                                        "ERROR", JOptionPane.WARNING_MESSAGE);
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
                                if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                    sEmergency = db.consultEmergencyParamedic(idParamedic);
                                    eMain.setText(sEmergency);
                                } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                    if (tDateOpen.getText().equals("")) {
                                        sEmergency = db.consultEmergencyParamedic(idParamedic, tDateClose.getText()
                                                + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    } else {
                                        sEmergency = db.consultEmergencyParamedic(idParamedic, tDateOpen.getText()
                                                + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    }
                                } else {
                                    sEmergency = db.consultEmergencyParamedic(idParamedic, tDateOpen.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            }
                            break;
                        case "Operador Voluntario":
                            if (tName.getText() == null || tName.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Debe ingresar la dirección que desea buscar",
                                        "ERROR", JOptionPane.WARNING_MESSAGE);
                            } else {
                                if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                    sEmergency = db.consultEmergencyOperVoluntary(tName.getText());
                                    eMain.setText(sEmergency);
                                } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                    if (tDateOpen.getText().equals("")) {
                                        sEmergency = db.consultEmergencyOperVoluntary(tName.getText(), tDateClose.getText()
                                                + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    } else {
                                        sEmergency = db.consultEmergencyOperVoluntary(tName.getText(), tDateOpen.getText()
                                                + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    }
                                } else {
                                    sEmergency = db.consultEmergencyOperVoluntary(tName.getText(), tDateOpen.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            }
                            break;
                        case "Paramedico Voluntario":
                            if (tName.getText() == null || tName.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Debe ingresar la dirección que desea buscar",
                                        "ERROR", JOptionPane.WARNING_MESSAGE);
                            } else {
                                if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                    sEmergency = db.consultEmergencyParamedicVoluntary(tName.getText());
                                    eMain.setText(sEmergency);
                                } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                    if (tDateOpen.getText().equals("")) {
                                        sEmergency = db.consultEmergencyParamedicVoluntary(tName.getText(),
                                                tDateClose.getText() + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    } else {
                                        sEmergency = db.consultEmergencyParamedicVoluntary(tName.getText(),
                                                tDateOpen.getText() + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    }
                                } else {
                                    sEmergency = db.consultEmergencyParamedicVoluntary(tName.getText(), tDateOpen.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            }
                            break;
                        case "Radio Operador":
                            if (mRadioOper.getText().equals("seleccione una opción")) {
                                JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar",
                                        "ERROR", JOptionPane.WARNING_MESSAGE);
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
                                if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                    sEmergency = db.consultEmergencyRadioOper(idRadioOper);
                                    eMain.setText(sEmergency);
                                } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                    if (tDateOpen.getText().equals("")) {
                                        sEmergency = db.consultEmergencyRadioOper(idRadioOper, tDateClose.getText()
                                                + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    } else {
                                        sEmergency = db.consultEmergencyRadioOper(idRadioOper, tDateOpen.getText()
                                                + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    }
                                } else {
                                    sEmergency = db.consultEmergencyRadioOper(idRadioOper, tDateOpen.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            }
                            break;
                        case "Ambulancia":
                            if (mAmbulance.getText().equals("seleccione una opción")) {
                                JOptionPane.showMessageDialog(null, "Debe seleccionar una opción para buscar",
                                        "ERROR", JOptionPane.WARNING_MESSAGE);
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
                                if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                    sEmergency = db.consultEmergencyAmbulance(idAmbulance);
                                    eMain.setText(sEmergency);
                                } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                    if (tDateOpen.getText().equals("")) {
                                        sEmergency = db.consultEmergencyAmbulance(idAmbulance, tDateClose.getText()
                                                + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    } else {
                                        sEmergency = db.consultEmergencyAmbulance(idAmbulance, tDateOpen.getText()
                                                + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                        eMain.setText(sEmergency);
                                    }
                                } else {
                                    sEmergency = db.consultEmergencyAmbulance(idAmbulance, tDateOpen.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            }
                            break;
                        case "Paciente X Nombre":
                            if (tPatient.getText().equals("") || tPatient.getText() == null) {
                                sEmergency = db.consultPatientAll();
                            } else {
                                String word = "";
                                int idPatient = 0;
                                char[] cadena = tPatient.getText().toCharArray();
                                for (int i = 3; i < cadena.length; i++) {
                                    if (cadena[i] == ' ') {
                                        idPatient = Integer.valueOf(word);
                                        i = cadena.length;
                                    } else {
                                        word += cadena[i];
                                    }
                                }
                                System.out.println("id: " + idPatient);
                                sEmergency = db.consultPatientAll(idPatient);
                            }
                            eMain.setText(sEmergency);
                            break;
                        case "Paciente X Fecha":
                            if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                sEmergency = db.consultPatientAll();
                                eMain.setText(sEmergency);
                            } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                if (tDateOpen.getText().equals("")) {
                                    sEmergency = db.consultPatientAll(tDateClose.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                } else {
                                    sEmergency = db.consultPatientAll(tDateOpen.getText()
                                            + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            } else {
                                sEmergency = db.consultPatientAll(tDateOpen.getText()
                                        + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                eMain.setText(sEmergency);
                            }
                            break;
                        case "Folios diarios":
                            if (tDateOpen.getText().equals("") && tDateClose.getText().equals("")) {
                                System.out.println(date.format(calendario.getTime()) + " 00:00:00"
                                        + date.format(calendario.getTime()) + " 23:59:59");
                                sEmergency = db.consultFoliosDiario(date.format(calendario.getTime()) + " 00:00:00",
                                        date.format(calendario.getTime()) + " 23:59:59");
                                eMain.setText(sEmergency);
                            } else if (tDateOpen.getText().equals("") || tDateClose.getText().equals("")) {
                                if (tDateOpen.getText().equals("")) {
                                    sEmergency = db.consultFoliosDiario(tDateClose.getText()
                                            + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                } else {
                                    sEmergency = db.consultFoliosDiario(tDateOpen.getText()
                                            + " 00:00:00", tDateOpen.getText() + " 23:59:59");
                                    eMain.setText(sEmergency);
                                }
                            } else {
                                sEmergency = db.consultFoliosDiario(tDateOpen.getText()
                                        + " 00:00:00", tDateClose.getText() + " 23:59:59");
                                eMain.setText(sEmergency);
                            }
                            break;

//                    sEmergency = db.consultPatientAll();
//                            String[][] sPatient = db.reportPatient();
//                            sEmergency = "<HTML>";
//                            for (int i = 1; i < sPatient.length; i++) {
//                                sEmergency += "<b>[PC#" + sPatient[i][0] + "]<br>Nombre:</b> " + sPatient[i][3] + "<br>"
//                                        + "<b>Edad:</b> " + sPatient[i][4] + " años \t\t<b>Sexo:</b> " + sPatient[i][5] + "<br>"
//                                        + "<b>Estado:</b> " + sPatient[i][13] + " <b>FRAP:</b> " + sPatient[i][2] + " <b>idEmergencia:</b> " + sPatient[i][1];
//                                if (!sPatient[i][6].equals("") && sPatient[i][6] != null) {
//                                    sEmergency += "<b>trauma:</b> " + sPatient[i][6] + "<br>";
//                                }
//                                if ((!sPatient[i][7].equals("") && sPatient[i][7] != null) || (!sPatient[i][8].equals("") && sPatient[i][8] != null)
//                                        || (!sPatient[i][9].equals("") && sPatient[i][9] != null) || (!sPatient[i][10].equals("") && sPatient[i][10] != null)) {
//                                    sEmergency += "<b>ENFERMO motivo:</b> " + sPatient[i][7] + "\t\t<b>padecimiento:</b> " + sPatient[i][8] + "<br>"
//                                            + "<b>medicamento:</b> " + sPatient[i][9] + "\t\t<b>evento previo:</b> " + sPatient[i][10] + "<br>";
//                                }
//                                if ((!sPatient[i][11].equals("") && sPatient[i][11] != null) || (!sPatient[i][12].equals("0"))) {
//                                    sEmergency += "<b>OBTETRICO tipo:</b> " + sPatient[i][11] + "\t\t<b>meses:</b> " + sPatient[i][12] + "<br>";
//                                }
//                                sEmergency += "<br>";
//                            }
//                            sEmergency += "</HTML>";
//                            eMain.setText(sEmergency);
//                            try {
//                                a.escribirTxt("resource/Emergencias Paramedico Voluntario " + tName.getText() + ".txt", sEmergency);
//                                if (tName.getText().equals("") || tName.getText() == null) {
//                                    a.writeExcelData("C:/CtrlAmb/reporte Paciente.xlsx", "hoja1", db.reportPatient());
//                                    a.writeExcelData("C:/CtrlAmb/reporte Paciente.xlsx", "hoja1", sPatient);
//                                    Runtime.getRuntime().exec("cmd /c start C:\\CtrlAmb\\reporte\" Paciente\".xlsx");
//                                } else {
//                                    a.writeExcelData("C:/CtrlAmb/reporte Paciente " + tName.getText() + ".xlsx", "hoja1", db.reportPatient(tName.getText()));
//                                    Runtime.getRuntime().exec("cmd /c start C:\\CtrlAmb\\reporte\" Paciente " + tName.getText() + "\".xlsx");
//                                }
//                            } catch (IOException ex) {
//                                Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
//                            }
                    }
            }
//            eMain.setLineWrap(true);
//            eMain.setWrapStyleWord(true);
        } else {
            switch (e.getActionCommand()) {
                case "Reporte Emergencia":
                    mOption.setText(e.getActionCommand());
                    break;
                case "Emergencia":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(false);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    break;
                case "FRAP":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(true);
                    tName.setVisible(false);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(false);
                    tDateOpen.setVisible(false);
                    lDateClose.setVisible(false);
                    tDateClose.setVisible(false);
                    break;
                case "Ubicación":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(false);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(true);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    break;
                case "Resultado":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(false);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(true);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    break;
                case "Traslado":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(false);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(true);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    break;
                case "Prioridad del traslado":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(false);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(true);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    break;
                case "Operador":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(false);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(true);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    break;
                case "Paramedico":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(false);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(true);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    break;
                case "Operador Voluntario":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(true);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    break;
                case "Paramedico Voluntario":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(true);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    break;
                case "Radio Operador":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(false);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(true);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    break;
                case "Ambulancia":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(false);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(true);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    break;
                case "Paciente X Nombre":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(false);
                    tPatient.setVisible(true);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(false);
                    tDateOpen.setVisible(false);
                    lDateClose.setVisible(false);
                    tDateClose.setVisible(false);
                    break;
                case "Paciente X Fecha":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(false);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
                    break;
                case "Folios diarios":
                    mOptionSearch.setText(e.getActionCommand());
                    lFrap.setVisible(false);
                    tFrap.setVisible(false);
                    tName.setVisible(false);
                    tPatient.setVisible(false);
                    lDir.setVisible(false);
                    tDir.setVisible(false);
                    mResultado.setVisible(false);
                    mPriorityTransfer.setVisible(false);
                    mTransfer.setVisible(false);
                    mOper.setVisible(false);
                    mRadioOper.setVisible(false);
                    mParamedic.setVisible(false);
                    mAmbulance.setVisible(false);
                    lDateOpen.setVisible(true);
                    tDateOpen.setVisible(true);
                    lDateClose.setVisible(true);
                    tDateClose.setVisible(true);
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
        repaint();
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
        employees = db.consultPatientName();
        cadena = employees.toCharArray();
        word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                autoPatient.addItem(word);
                word = "";
            } else {
                word += cadena[i];
            }
        }
    }

    public void updateAutoPatient() {
        char[] cadena;
        String employees = db.consultPatient();
        cadena = employees.toCharArray();
        String word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                autoPatient.addItem(word);
                word = "";
            } else {
                word += cadena[i];
            }
        }
    }

    public String format(String[][] data) {
        String text = "<HTML>";
        for (int i = 0; i < data.length; i++) {
            text += "<b>[EM#" + data[i][0] + "]</b><br>"
                    + "<b>Dirección:</b> " + data[i][1] + " \t\t<b>entre:</b> " + data[i][2] + "<br>"
                    + "<b>Referencia:</b> " + data[i][3] + "<br>"
                    + "<b>Colonia:</b> " + data[i][4] + " \t\t<b>Delegación:</b> " + data[i][5] + "<br>"
                    + "<b>Nombre del solicitante:</b> " + data[i][6] + "<br>"
                    + "<b>Resultado:</b> " + data[i][7] + "<br>"
                    + "<b>Traslado:</b> " + data[i][8] + "\t\t<b>Prioridad:</b> " + data[i][9] + "<br>"
                    + "<b>Vivos:</b> " + data[i][10] + "\t\t<b>Muertos:</b> " + data[i][11] + "<br>"
                    + db.consultInfoPatient(Integer.valueOf(data[i][0]))
                    + "<b>Paramedico:</b> #" + data[i][12] + "\t<b>Nombre:</b> "
                    + db.consultNameParamedic(Integer.valueOf(data[i][12])) + "<br>"
                    + "<b>Paramedico voluntario:</b> " + data[i][19] + "<br>"
                    + "<b>Operador:</b> #" + data[i][13] + "\t<b>Nombre:</b> "
                    + db.consultNameOper(Integer.valueOf(data[i][13])) + "<br>"
                    + "<b>Operador voluntario:</b> " + data[i][18] + "<br>"
                    + "<b>RadioOperador:</b> #" + data[i][14] + "\t<b>Nombre:</b> "
                    + db.consultNameRadioOper(Integer.valueOf(data[i][14])) + "<br>"
                    + "<b>Ambulancia:</b> id:" + data[i][15] + "\t<b>Numero:</b> "
                    + db.consultNumAmbulance(Integer.valueOf(data[i][15])) + "\t<b>Km recorridas:</b> " + data[i][16]
                    + "\t<b>Base:</b> " + data[i][17] + "<br>"
                    + "<b>hora de la llamada:</b> " + data[i][20] + "<br>"
                    + "<b>hora de la salida:</b> " + data[i][21] + "<br>"
                    + "<b>hora de la llegada:</b> " + data[i][22] + "<br>"
                    + "<b>hora de la traslado:</b> " + data[i][23] + "<br>"
                    + "<b>hora de la hospital:</b> " + data[i][24] + "<br>"
                    + "<b>hora de la base:</b> " + data[i][25] + "<br>"
                    + "<b>Observaciones:</b> " + data[i][26] + "<br>"
                    + "<b>llamada recibida:</b> " + data[i][27] + " " + data[i][28] + "<br>"
                    + "<b>llamada recibida:</b> " + data[i][27] + " " + data[i][28] + "<br>";
//            if (!data[i][29].equals("") && data[i][29] != null) {
            text += "<b>Folio </b> " + data[i][29] + ": desde" + data[i][30] + " hasta " + data[i][31] + "<br>";
//            }
            text += "<br>";
        }
        text += "</HTML>";
        return text;
    }
}
