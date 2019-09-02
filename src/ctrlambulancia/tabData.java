/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

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

    public tabData() {
        JPanel pTrauma = new JPanel();
        JPanel pEnfermo = new JPanel();
        JPanel pObstetrico = new JPanel();
        //JButton b1 = new JButton();

        //JMenuBar mServiceTrauma = new JMenuBar();
        JMenuBar mBarTrauma = new JMenuBar();
        JMenuBar mBarObstetrico = new JMenuBar();

        JMenuItem i5p = new JMenuItem("5P");
        JMenuItem i5b = new JMenuItem("5B");
        JMenuItem i27 = new JMenuItem("27");
        JMenuItem i2c = new JMenuItem("2C");
        JMenuItem i2a = new JMenuItem("2A");
        JMenuItem i40 = new JMenuItem("40");
        JMenuItem i5 = new JMenuItem("5");
        JMenuItem i5r = new JMenuItem("5R");
        JMenuItem i27a = new JMenuItem("27A");
        JMenuItem i5c = new JMenuItem("5C");
        JMenuItem i5q = new JMenuItem("5Q");
        JMenuItem i5e = new JMenuItem("5E");
        JMenuItem i5a = new JMenuItem("5A");
        JMenuItem i13a = new JMenuItem("13A");
        JMenuItem iint14 = new JMenuItem("INT 14");
        JMenuItem iPregnat = new JMenuItem("Embarazada");
        JMenuItem iAbortion = new JMenuItem("Aborto");
        JMenuItem iHemorrhage = new JMenuItem("Sangrando");
        i5p.addActionListener(this);
        i5b.addActionListener(this);
        i27.addActionListener(this);
        i2c.addActionListener(this);
        i2a.addActionListener(this);
        i40.addActionListener(this);
        i5.addActionListener(this);
        i5r.addActionListener(this);
        i27a.addActionListener(this);
        i5c.addActionListener(this);
        i5q.addActionListener(this);
        i5e.addActionListener(this);
        i5a.addActionListener(this);
        i13a.addActionListener(this);
        iint14.addActionListener(this);
        iPregnat.addActionListener(this);
        iAbortion.addActionListener(this);
        iHemorrhage.addActionListener(this);
        nVictimas.addActionListener(this);
        mTrauma.add(i5p);
        mTrauma.add(i5b);
        mTrauma.add(i27);
        mTrauma.add(i2c);
        mTrauma.add(i2a);
        mTrauma.add(i40);
        mTrauma.add(i5);
        mTrauma.add(i5r);
        mTrauma.add(i5q);
        mTrauma.add(i5e);
        mTrauma.add(i5a);
        mTrauma.add(i13a);
        mTrauma.add(iint14);
        mBarTrauma.add(mTrauma);
        mObstetrico.add(iPregnat);
        mObstetrico.add(iAbortion);
        mObstetrico.add(iHemorrhage);
        mBarObstetrico.add(mObstetrico);
        
        mTrauma.setPreferredSize(new Dimension(150, 30));
        mObstetrico.setPreferredSize(new Dimension(150, 30));

        JLabel lMotivo = new JLabel("Motivo");
        JLabel lPadecimiento = new JLabel("Padecimiento");
        JLabel lMedicamento = new JLabel("Medicamento");
        JLabel lEventoPrevio = new JLabel("Evento previo");
        JLabel lObstetricoMonthes = new JLabel("Meses");
        pTrauma.add(mBarTrauma);
        pTrauma.add(nVictimas);
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

        this.addTab("trauma", pTrauma);
        this.addTab("Enfermo", pEnfermo);
        this.addTab("Obstetrico", pObstetrico);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("event: "+e.getActionCommand() +" \t summoner: " +e.getSource().getClass().getSimpleName());
        if (e.getSource().getClass().getSimpleName().toString().equals("JMenuItem")) {
            switch (e.getActionCommand().toCharArray()[0]) {
                case 'E':
                    mObstetrico.setText(e.getActionCommand());
                    break;
                case 'A':
                    mObstetrico.setText(e.getActionCommand());
                    break;
                case 'S':
                    mObstetrico.setText(e.getActionCommand());
                    break;
                default:
                    mTrauma.setText(e.getActionCommand());
                    break;
            }
        }
    }
}
