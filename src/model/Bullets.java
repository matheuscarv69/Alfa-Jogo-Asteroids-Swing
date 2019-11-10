/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mathe
 */
public class Bullets extends JLabel {

    private int x;
    private int y;
    private static int width = 10;
    private static int height = 26;
    private JPanel jpanel1;

    public Bullets(int x, int y) {
        this.x = x;
        this.y = y;
        // this.jpanel1 = jpanel1;
        setBounds(x, y, width, height);
        setIcon(new javax.swing.ImageIcon(getClass().getResource("../images/projetil.png")));
    }

    // teste de movimentação do tiro dentro da ThreadASt
    public void movTiro() {
        x = getX();
        y = getY();
        y--;
        setBounds(x, y, width, height);
    }

    /*
    public void movBullet() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    x = getX();
                    y = getY();
                    y--;
                    //System.out.println("Tiro posição X: " + x + "| Y: " + y);
                    setBounds(x, y, width, height);
                    if (paraTiro()) {
                        setVisible(false);
                        stop();
                    }
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

            }

        }.start();
    }

    public boolean paraTiro() {
        if (getY() == -26) {
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
