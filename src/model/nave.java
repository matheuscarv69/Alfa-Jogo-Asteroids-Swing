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
public class nave {

    private JLabel nave;
    private int x;
    private int y;
    private static int andar = 25;
    private static int width = 85;
    private static int height = 72;

    public nave(JLabel nave) {
        this.nave = nave;
        nave.setIcon(new javax.swing.ImageIcon(getClass().getResource("../images/Nave.png")));
    }

    public void andarCima() {
        x = nave.getX();
        y = nave.getY();
        y -= andar;
        nave.setBounds(x, y, width, height);
    }
    public void andarBaixo(){
        x = nave.getX();
        y = nave.getY();
        y += andar;
        nave.setBounds(x, y, width, height);
    }
    public void andarDireita(){
        x = nave.getX();
        y = nave.getY();
        x += andar;
        nave.setBounds(x, y, width, height);
    }
    public void andarEsquerda(){
        x = nave.getX();
        y = nave.getY();
        x -= andar;
        nave.setBounds(x, y, width, height);
    }
    
}
