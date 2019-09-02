/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.awt.GridLayout;
import static java.lang.Thread.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sistemas
 */
public class ctrTime extends JPanel {

    Calendar calendario = new GregorianCalendar();
    SimpleDateFormat date;
        JTextField tFolio = new JTextField(10);

    public ctrTime(String sTimeCall,String folio) {
        tFolio.setText(folio);
        RealTime rt = new RealTime();
        JLabel lDate = new JLabel("Fecha:");
        JLabel lTime = new JLabel("Hora:");
        JLabel lTimeCall = new JLabel("Hora de llamada:");
        JLabel lFolio = new JLabel("Folio:");
        JTextField tDate = new JTextField(10);
        JTextField tTime = new JTextField(10);
        rt.settTime(tTime);
        JTextField tTimeCall = new JTextField(10);
        date = new SimpleDateFormat("dd MMMM yyyy");
        tDate.setText(date.format(calendario.getTime()));
        tTime.setText(sTimeCall);
        tTimeCall.setText(sTimeCall);
        tDate.setEditable(false);
        tTime.setEditable(false);
        tTimeCall.setEditable(false);
        tFolio.setEditable(false);
        lDate.setHorizontalAlignment(JTextField.RIGHT);
        lTime.setHorizontalAlignment(JTextField.RIGHT);
        lTimeCall.setHorizontalAlignment(JTextField.RIGHT);
        lFolio.setHorizontalAlignment(JTextField.RIGHT);
        tDate.setHorizontalAlignment(JTextField.CENTER);
        tTime.setHorizontalAlignment(JTextField.CENTER);
        tTimeCall.setHorizontalAlignment(JTextField.CENTER);
        tFolio.setHorizontalAlignment(JTextField.CENTER);
        this.add(lDate);
        this.add(tDate);
        this.add(lTime);
        this.add(tTime);
        this.add(lTimeCall);
        this.add(tTimeCall);
        this.add(lFolio);
        this.add(tFolio);
        setLayout(new GridLayout(1,8));
        rt.start();
    }
}
