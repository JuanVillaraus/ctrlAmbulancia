/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class EditPatient extends JPanel implements ActionListener {

    ConxDB db;
    JTextField tAgeOld = new JTextField(20);
    JTextField tNumFrap = new JTextField(20);
    JTextField tIdEmergency = new JTextField(20);
    JTextField tNamePatient = new JTextField(20);
    JTextField tLastNamePatient = new JTextField(20);
    JTextField tLastName2Patient = new JTextField(20);
    JTextField tMotivo = new JTextField(20);
    JTextField tPadecimiento = new JTextField(20);
    JTextField tMedicamento = new JTextField(20);
    JTextField tEventoPrevio = new JTextField(20);
    JTextField tObstetricoMonthes = new JTextField(8);
    JTextField tOther = new JTextField(10);
    JRadioButton sexM = new JRadioButton("Mas", false);
    JRadioButton sexF = new JRadioButton("Fem", false);
    JRadioButton rAlive = new JRadioButton("Vivo", false);
    JRadioButton rDeads = new JRadioButton("Muerto", false);
    ButtonGroup groupSex = new ButtonGroup();
    ButtonGroup groupStatus = new ButtonGroup();
    JButton bSave = new JButton("Actualizar");
    String sex = "";
    String status = "";
    JMenu mTrauma = new JMenu("Tipo de Trauma");
    JMenu mObstetrico = new JMenu("Tipo de obstetrico");

    EditPatient(ConxDB db, int idPatient) {
        this.db = db;
        String[] patient = db.consultEditPatient(idPatient);
        tNamePatient.setText(patient[0]);
        tLastNamePatient.setText(patient[1]);
        tLastName2Patient.setText(patient[2]);
        tAgeOld.setText(patient[3]);
        switch(patient[4]){
            case "Mas":
                sexM.setSelected(true);
                break;
            case "Fem":
                sexF.setSelected(true);
        }
        mTrauma.setText(patient[5]);
        tMotivo.setText(patient[6]);
        tPadecimiento.setText(patient[7]);
        tMedicamento.setText(patient[8]);
        tEventoPrevio.setText(patient[9]);
        mObstetrico.setText(patient[10]);
        tObstetricoMonthes.setText(patient[11]);
        switch(patient[12]){
            case "Mas":
                rAlive.setSelected(true);
                break;
            case "Fem":
                rDeads.setSelected(true);
        }
        tNumFrap.setText(patient[13]);
        tIdEmergency.setText(patient[14]);
        JLabel lSex = new JLabel("Sexo:");
        JLabel lAgeOld = new JLabel("Edad:");
        JLabel lStatus = new JLabel("Estado:");
        JLabel lNumFrap = new JLabel("Num frap:");
        JLabel lIdEmergency = new JLabel("ID Emergencia:");
        JLabel lNamePatient = new JLabel("Nombre:");
        JLabel lLastNamePatient = new JLabel("Apellido paterno:");
        JLabel lLastName2Patient = new JLabel("Apellido materno:");
        JLabel lMotivo = new JLabel("Motivo");
        JLabel lPadecimiento = new JLabel("Padecimiento");
        JLabel lMedicamento = new JLabel("Medicamento");
        JLabel lEventoPrevio = new JLabel("Evento previo");
        JLabel lObstetricoMonthes = new JLabel("Meses");
        JMenuBar mBarTrauma = new JMenuBar();
        JMenuBar mBarObstetrico = new JMenuBar();
        JMenuItem iTrauma1 = new JMenuItem("5P");
        JMenuItem iTrauma2 = new JMenuItem("5B");
        JMenuItem iTrauma3 = new JMenuItem("27");
        JMenuItem iTrauma4 = new JMenuItem("2C");
        JMenuItem iTrauma5 = new JMenuItem("2A");
        JMenuItem iTrauma6 = new JMenuItem("40");
        JMenuItem iTrauma7 = new JMenuItem("5");
        JMenuItem iTrauma8 = new JMenuItem("5R");
        JMenuItem iTrauma9 = new JMenuItem("27A");
        JMenuItem iTrauma10 = new JMenuItem("5C");
        JMenuItem iTrauma11 = new JMenuItem("5Q");
        JMenuItem iTrauma12 = new JMenuItem("5E");
        JMenuItem iTrauma13 = new JMenuItem("5A");
        JMenuItem iTrauma14 = new JMenuItem("13A");
        JMenuItem iTrauma15 = new JMenuItem("INT 14");
        JMenuItem iTrauma16 = new JMenuItem("Otro");
        JMenuItem iObstetrico1 = new JMenuItem("Embarazada");
        JMenuItem iObstetrico2 = new JMenuItem("Aborto");
        JMenuItem iObstetrico3 = new JMenuItem("Sangrando");
        iTrauma1.addActionListener(this);
        iTrauma2.addActionListener(this);
        iTrauma3.addActionListener(this);
        iTrauma4.addActionListener(this);
        iTrauma5.addActionListener(this);
        iTrauma6.addActionListener(this);
        iTrauma7.addActionListener(this);
        iTrauma8.addActionListener(this);
        iTrauma9.addActionListener(this);
        iTrauma10.addActionListener(this);
        iTrauma11.addActionListener(this);
        iTrauma12.addActionListener(this);
        iTrauma13.addActionListener(this);
        iTrauma14.addActionListener(this);
        iTrauma15.addActionListener(this);
        iTrauma16.addActionListener(this);
        iObstetrico1.addActionListener(this);
        iObstetrico2.addActionListener(this);
        iObstetrico3.addActionListener(this);        
        groupSex.add(sexM);
        groupSex.add(sexF);
        groupStatus.add(rAlive);
        groupStatus.add(rDeads);
        mTrauma.add(iTrauma1);
        mTrauma.add(iTrauma2);
        mTrauma.add(iTrauma3);
        mTrauma.add(iTrauma4);
        mTrauma.add(iTrauma5);
        mTrauma.add(iTrauma6);
        mTrauma.add(iTrauma7);
        mTrauma.add(iTrauma8);
        mTrauma.add(iTrauma11);
        mTrauma.add(iTrauma12);
        mTrauma.add(iTrauma13);
        mTrauma.add(iTrauma14);
        mTrauma.add(iTrauma15);
        mTrauma.add(iTrauma16);
        mBarTrauma.add(mTrauma);
        mObstetrico.add(iObstetrico1);
        mObstetrico.add(iObstetrico2);
        mObstetrico.add(iObstetrico3);
        mBarObstetrico.add(mObstetrico);
        lNamePatient.setPreferredSize(new Dimension(200, 20));
        lLastNamePatient.setPreferredSize(new Dimension(200, 20));
        lLastName2Patient.setPreferredSize(new Dimension(200, 20));
        lSex.setPreferredSize(new Dimension(280, 20));
        lAgeOld.setPreferredSize(new Dimension(200, 20));
        lStatus.setPreferredSize(new Dimension(280, 20));
        lNumFrap.setPreferredSize(new Dimension(200, 20));
        lIdEmergency.setPreferredSize(new Dimension(200, 20));
        mBarTrauma.setPreferredSize(new Dimension(200, 20));
        lMotivo.setPreferredSize(new Dimension(200, 20));
        lPadecimiento.setPreferredSize(new Dimension(200, 20));
        lMedicamento.setPreferredSize(new Dimension(200, 20));
        lEventoPrevio.setPreferredSize(new Dimension(200, 20));
        mBarObstetrico.setPreferredSize(new Dimension(200, 20));
        lObstetricoMonthes.setPreferredSize(new Dimension(100, 20));
        mTrauma.setPreferredSize(new Dimension(200, 30));
        iTrauma1.setPreferredSize(new Dimension(200, 20));
        iTrauma2.setPreferredSize(new Dimension(200, 20));
        iTrauma3.setPreferredSize(new Dimension(200, 20));
        iTrauma4.setPreferredSize(new Dimension(200, 20));
        iTrauma5.setPreferredSize(new Dimension(200, 20));
        iTrauma6.setPreferredSize(new Dimension(200, 20));
        iTrauma7.setPreferredSize(new Dimension(200, 20));
        iTrauma8.setPreferredSize(new Dimension(200, 20));
        iTrauma9.setPreferredSize(new Dimension(200, 20));
        iTrauma10.setPreferredSize(new Dimension(200, 20));
        iTrauma11.setPreferredSize(new Dimension(200, 20));
        iTrauma12.setPreferredSize(new Dimension(200, 20));
        iTrauma13.setPreferredSize(new Dimension(200, 20));
        iTrauma14.setPreferredSize(new Dimension(200, 20));
        iTrauma15.setPreferredSize(new Dimension(200, 20));
        iTrauma16.setPreferredSize(new Dimension(200, 20));
        mObstetrico.setPreferredSize(new Dimension(200, 30));
        iObstetrico1.setPreferredSize(new Dimension(200, 20));
        iObstetrico2.setPreferredSize(new Dimension(200, 20));
        iObstetrico3.setPreferredSize(new Dimension(200, 20));
        lSex.setHorizontalAlignment(SwingConstants.CENTER);
        lStatus.setHorizontalAlignment(SwingConstants.CENTER);
        tOther.setEnabled(false);
        
        this.add(lNumFrap);
        this.add(tNumFrap);
        this.add(lNamePatient);
        this.add(tNamePatient);
        this.add(lLastNamePatient);
        this.add(tLastNamePatient);
        this.add(lLastName2Patient);
        this.add(tLastName2Patient);
        this.add(lAgeOld);
        this.add(tAgeOld);
        this.add(lSex);
        this.add(sexM);
        this.add(sexF);
        this.add(lStatus);
        this.add(rAlive);
        this.add(rDeads);
        this.add(mBarTrauma);
        this.add(tOther);
        this.add(lMotivo);
        this.add(tMotivo);
        this.add(lPadecimiento);
        this.add(tPadecimiento);
        this.add(lMedicamento);
        this.add(tMedicamento);
        this.add(lEventoPrevio);
        this.add(tEventoPrevio);
        this.add(mBarObstetrico);
        this.add(lObstetricoMonthes);
        this.add(tObstetricoMonthes);
        this.add(lIdEmergency);
        this.add(tIdEmergency);
        this.add(bSave);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
