/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Sistemas
 */
public class MonitorActivity extends JPanel{

    JTextField messenger = new JTextField(100);

    public MonitorActivity() {
        messenger.setEditable(false);
        messenger.setHorizontalAlignment(JTextField.CENTER);
        this.add(messenger);
    }
}
