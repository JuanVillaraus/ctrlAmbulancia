/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Sistemas
 */
public class RealTime extends Thread {

    JTextField tTime;

    public JTextField gettTime() {
        return tTime;
    }

    public void settTime(JTextField tTime) {
        this.tTime = tTime;
    }
    SimpleDateFormat date, time;

    public void run() {
        time = new SimpleDateFormat("hh:mm:ss");
        while (true) {
            Calendar calendario = new GregorianCalendar();
            tTime.setText(time.format(calendario.getTime()));
            try {
                sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(ctrTime.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("\t Error Time: " + ex);
            }
        }
    }

}
