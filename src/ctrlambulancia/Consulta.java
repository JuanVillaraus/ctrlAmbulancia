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
    JTextField tDateOpen = new JTextField(6);
    JTextField tDateClose = new JTextField(6);
    JTextField tId = new JTextField(8);
    JTextArea tMain = new JTextArea();

    public Consulta(JFrame window, ConxDB db) {
        this.db = db;
        JLabel lDate = new JLabel("Fecha");
        JLabel lId = new JLabel("ID");
        JButton bSearch = new JButton("Buscar");
        JMenuBar mBarOption = new JMenuBar();
        JMenuItem iOption1 = new JMenuItem("Reporte Emergencia");
        JMenuItem iOption2 = new JMenuItem("Paciente");
        JPanel top = new JPanel();
        JScrollPane scMain = new JScrollPane(tMain);//, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        iOption1.addActionListener(this);
        iOption2.addActionListener(this);
        bSearch.addActionListener(this);
        mOption.add(iOption1);
        mOption.add(iOption2);
        mBarOption.add(mOption);
        mOption.setPreferredSize(new Dimension(150, 30));
        tMain.setPreferredSize(new Dimension(window.getWidth() - 50, 600));
        top.add(mBarOption);
        top.add(lDate);
        top.add(tDateOpen);
        top.add(tDateClose);
        top.add(lId);
        top.add(tId);
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
            }
        } else {
            mOption.setText(e.getActionCommand());
        }
    }
}
