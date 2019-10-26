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
public class asteroides extends JLabel {

    private int x;
    private int y;
    private static int width = 87;
    private static int height = 65;

    public asteroides(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, width, height);
        setIcon(new javax.swing.ImageIcon(getClass().getResource("../images/a.png")));
    }

    public void andarBaixo() {
        x = getX();
        y = getY();
        y++;
        setBounds(x, y, width, height);
        System.out.println("Asteroide movido x = " + getX() + " y = " + getY());
    }

    public void verExtBaixo() {
        // Seta asteroide para cima caso ele chegue a borda de baixo
        if (getY() > 420) {
            int a = getX();
            this.y = -40;
            System.out.println("Função - POSIÇÃO X " + a + " | " + "Y " + y);
            setBounds(a, y, width, height);
        }
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
