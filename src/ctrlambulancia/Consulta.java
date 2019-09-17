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
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class Consulta extends JPanel implements ActionListener {
    
    ConxDB db;
    JMenu mOption = new JMenu("Opción");
    JTextField tId = new JTextField(8);
    JTextArea tMain = new JTextArea();
    
    public Consulta(JFrame window, ConxDB db) {
        this.db = db;
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
                    if (tId.getText().equals("")) {
                        tMain.setText(db.consultEmergency());
                    } else {
                        tMain.setText(db.consultEmergency(Integer.valueOf(tId.getText())));
                    }
                    break;
                case "Paciente":
                    if (tId.getText().equals("")) {
                        tMain.setText(db.consultPatientAll());
                    } else {
                        tMain.setText(db.consultPatient(Integer.valueOf(tId.getText())));
                    }
            }
        } else {
            mOption.setText(e.getActionCommand());
        }
    }
    
}
