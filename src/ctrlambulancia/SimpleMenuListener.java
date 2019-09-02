/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.awt.event.*;
import javax.swing.event.*;

/**
 *
 * @author Sistemas
 */
public class SimpleMenuListener implements MenuListener{
    
    public void SimpleMenuListener(){
        
    }
    
    @Override
    public void menuSelected(MenuEvent e) {
        System.out.println("menuSelected");
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        System.out.println("menuDeselected");
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        System.out.println("menuCanceled");
    }
    
    
}
