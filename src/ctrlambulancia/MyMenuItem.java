/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class MyMenuItem extends JMenuItem implements ActionListener {

    JMenu menu = new JMenu();

    public JMenu getMenu() {
        return menu;
    }

    public void setMenu(JMenu menu) {
        this.menu = menu;
    }
    
    public MyMenuItem(String text) {
        this.menu=menu;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Item clicked: " + e.getActionCommand());
        //setsExample(e.getActionCommand());
        //super(e.getActionCommand());
    }
    
}
