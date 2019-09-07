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
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class Insert extends JPanel implements ActionListener {

    ConxDB db;
    int idOper = 1;
    int idRadioOper = 1;
    int idParamedic = 1;
    JButton bInsert = new JButton("Agregar");
    JButton bDelete = new JButton("Eliminar");
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

    public Insert(JFrame window, ConxDB db) {
        this.db = db;
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
        bInsert.setVisible(false);
        bDelete.setVisible(false);
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

        JPanel pAlert = new JPanel(new GridLayout(1, 1));
        JPanel pOption = new JPanel(new GridLayout(1, 1));
        //mInsert.setHorizontalAlignment(SwingConstants.CENTER);
        mOption.setHorizontalTextPosition(JTextField.CENTER);
        mTipeJob.setHorizontalTextPosition(JTextField.CENTER);
        alert.setHorizontalAlignment(JTextField.CENTER);

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
        pAlert.add(alert);
        window.add(pAlert, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Insert/ActionPerformed$ Event: " + e.getActionCommand());
        if (e.getSource().getClass().getSimpleName().equals("JButton")) {
            switch (mTipeJob.getText()) {
                case "Ambulancia":
                    break;
                case "Operador":
                    if (tName.getText().equals("") || tLastName.getText().equals("") || tLastName2.getText().equals("")) {
                        alert.setText("Deben llenarse todos los campos");
                        alert.setBackground(Color.RED);
                    } else {
                        resultDB = db.insertOper(idOper, tName.getText(), tLastName.getText(), tLastName2.getText());
                        if (resultDB.equals("successfully completed")) {
                            alert.setText("El operador con id:" + idOper + " ha sido guardado exitosamente");
                            idOper++;
                            tName.setText(null);
                            tLastName.setText(null);
                            tLastName2.setText(null);
                            alert.setBackground(Color.GREEN);
                        } else {
                            System.out.println(resultDB);
                            alert.setBackground(Color.RED);
                        }
                    }
                    break;
                case "Paramedico":
                    if (tName.getText().equals("") || tLastName.getText().equals("") || tLastName2.getText().equals("")) {
                        alert.setText("Deben llenarse todos los campos");
                        alert.setBackground(Color.RED);
                    } else {
                        resultDB = db.insertParamedic(idParamedic, tName.getText(), tLastName.getText(), tLastName2.getText());
                        if (resultDB.equals("successfully completed")) {
                            alert.setText("El paramedico con id:" + idParamedic + " ha sido guardado exitosamente");
                            idParamedic++;
                            tName.setText(null);
                            tLastName.setText(null);
                            tLastName2.setText(null);
                            alert.setBackground(Color.GREEN);
                        } else {
                            System.out.println(resultDB);
                            alert.setBackground(Color.RED);
                        }
                    }
                    break;
                case "Radio Operador":
                    if (tName.getText().equals("") || tLastName.getText().equals("") || tLastName2.getText().equals("")) {
                        alert.setText("Deben llenarse todos los campos");
                        alert.setBackground(Color.RED);
                    } else {
                        resultDB = db.insertRadioOper(idRadioOper, tName.getText(), tLastName.getText(), tLastName2.getText());
                        if (resultDB.equals("successfully completed")) {
                            alert.setText("El paramedico con id:" + idRadioOper + " ha sido guardado exitosamente");
                            idRadioOper++;
                            tName.setText(null);
                            tLastName.setText(null);
                            tLastName2.setText(null);
                            alert.setBackground(Color.GREEN);
                        } else {
                            System.out.println(resultDB);
                            alert.setBackground(Color.RED);
                        }
                    }
                    break;
            }
        } else {
            mTipeJob.setText(e.getActionCommand());
            switch (e.getActionCommand()) {
                case "Agregar":
                    mOption.setText(e.getActionCommand());
                    mTipeJob.setText("Opición");
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
                    break;
                case "Eliminar":
                    mOption.setText(e.getActionCommand());
                    mTipeJob.setText("Opición");
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
                    break;
                case "Consultar":
                    mOption.setText(e.getActionCommand());
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
                    bInsert.setVisible(false);
                    lId.setVisible(false);
                    tId.setVisible(false);
                    bDelete.setVisible(false);
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
                            break;
                    }
                    break;
            }
        }
    }

}
