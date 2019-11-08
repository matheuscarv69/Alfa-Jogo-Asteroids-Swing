/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mathe
 */
public class asteroides extends JLabel {

    private int x;
    private int y;
    private JPanel jpanel1;
    private static int width = 87;
    private static int height = 65;

    public asteroides(int x, int y, JPanel jpanel1) {
        this.x = x;
        this.y = y;
        this.jpanel1 = jpanel1;
        setBounds(x, y, width, height);
        setIcon(new javax.swing.ImageIcon(getClass().getResource("../images/a.png")));
    }

    public void movAst() {
        x = getX();
        y = getY();
        y++;
        setBounds(x, y, width, height);
    
    }
/*
    public boolean paraAst() {
        //trocar para 420
        if (getY() > 330) {
            return true;
        } else {
            return false;
        }
    }
     */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
