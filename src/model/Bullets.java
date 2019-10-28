/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JLabel;

/**
 *
 * @author mathe
 */
public class Bullets extends JLabel {

    private int x;
    private int y;
    private static int width = 10;
    private static int height = 26;

    public Bullets(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, width, height);
        setIcon(new javax.swing.ImageIcon(getClass().getResource("../images/projetil.png")));
    }

    public void movBullet() {
        x = getX();
        y = getY();
        y--;
        System.out.println("Tiro posição X: " + x + "| Y: " + y);
        setBounds(x, y, width, height);
        //System.out.println("Bullet movimentando");
    }

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
