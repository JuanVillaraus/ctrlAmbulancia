/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class tabData extends JTabbedPane implements ActionListener {

    JMenu mTrauma = new JMenu("Tipo de Trauma");
    JMenu mObstetrico = new JMenu("Tipo de obstetrico");
    JTextField nVictimas = new JTextField(10);
    JTextField tMotivo = new JTextField(10);
    JTextField tPadecimiento = new JTextField(10);
    JTextField tMedicamento = new JTextField(10);
    JTextField tEventoPrevio = new JTextField(10);
    JTextField tObstetricoMonthes = new JTextField(5);
    JTextField tOther = new JTextField(10);
    JTextField tTransferFrom = new JTextField(30);
    JTextField tTransferTo = new JTextField(30);

    public tabData() {
        JPanel pTrauma = new JPanel();
        JPanel pEnfermo = new JPanel();
        JPanel pObstetrico = new JPanel();
        JPanel pPaidTransfer = new JPanel();
        //JButton b1 = new JButton();

        //JMenuBar mServiceTrauma = new JMenuBar();
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
        nVictimas.addActionListener(this);
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
        
        mTrauma.setPreferredSize(new Dimension(150, 30));
        iTrauma1.setPreferredSize(new Dimension(150, 20));
        iTrauma2.setPreferredSize(new Dimension(150, 20));
        iTrauma3.setPreferredSize(new Dimension(150, 20));
        iTrauma4.setPreferredSize(new Dimension(150, 20));
        iTrauma5.setPreferredSize(new Dimension(150, 20));
        iTrauma6.setPreferredSize(new Dimension(150, 20));
        iTrauma7.setPreferredSize(new Dimension(150, 20));
        iTrauma8.setPreferredSize(new Dimension(150, 20));
        iTrauma9.setPreferredSize(new Dimension(150, 20));
        iTrauma10.setPreferredSize(new Dimension(150, 20));
        iTrauma11.setPreferredSize(new Dimension(150, 20));
        iTrauma12.setPreferredSize(new Dimension(150, 20));
        iTrauma13.setPreferredSize(new Dimension(150, 20));
        iTrauma14.setPreferredSize(new Dimension(150, 20));
        iTrauma15.setPreferredSize(new Dimension(150, 20));
        iTrauma16.setPreferredSize(new Dimension(150, 20));
        mObstetrico.setPreferredSize(new Dimension(150, 30));
        tOther.setVisible(false);

        JLabel lMotivo = new JLabel("Motivo");
        JLabel lPadecimiento = new JLabel("Padecimiento");
        JLabel lMedicamento = new JLabel("Medicamento");
        JLabel lEventoPrevio = new JLabel("Evento previo");
        JLabel lObstetricoMonthes = new JLabel("Meses");
        JLabel lTransferFrom = new JLabel("Desde:");
        JLabel lTransferTo = new JLabel("Hasta:");
        pTrauma.add(mBarTrauma);
        pTrauma.add(tOther);
        pEnfermo.add(lMotivo);
        pEnfermo.add(tMotivo);
        pEnfermo.add(lPadecimiento);
        pEnfermo.add(tPadecimiento);
        pEnfermo.add(lMedicamento);
        pEnfermo.add(tMedicamento);
        pEnfermo.add(lEventoPrevio);
        pEnfermo.add(tEventoPrevio);
        pObstetrico.add(mBarObstetrico);
        pObstetrico.add(lObstetricoMonthes);
        pObstetrico.add(tObstetricoMonthes);
        pPaidTransfer.add(lTransferFrom);
        pPaidTransfer.add(tTransferFrom);
        pPaidTransfer.add(lTransferTo);
        pPaidTransfer.add(tTransferTo);

        this.addTab("trauma", pTrauma);
        this.addTab("Enfermo", pEnfermo);
        this.addTab("Obstetrico", pObstetrico);
        this.addTab("Traslado Pagado", pPaidTransfer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("event: "+e.getActionCommand() +" \t summoner: " +e.getSource().getClass().getSimpleName());
        if (e.getSource().getClass().getSimpleName().toString().equals("JMenuItem")) {
            switch (e.getActionCommand()) {
                case "Embarazada":
                case "Aborto":
                case "Sangrando":
                    mObstetrico.setText(e.getActionCommand());
                    break;
                case "Otro":
                    mTrauma.setText(e.getActionCommand());
                    tOther.setVisible(true);
                    break;
                default:
                    mTrauma.setText(e.getActionCommand());
                    tOther.setVisible(false);
                    tOther.setText("");
                    break;
            }
        }
    }
}
