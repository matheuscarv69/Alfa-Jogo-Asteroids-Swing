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
public class nave extends JLabel {

    private int x;
    private int y;
    private static int andar = 25;
    private static int width = 85;
    private static int height = 72;

    public nave(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, width, height);
        setIcon(new javax.swing.ImageIcon(getClass().getResource("../images/Nave.png")));
    }

    public void andarCima() {
        x = getX();
        y = getY();
        y -= andar;
        setBounds(x, y, width, height);
    }

    public void andarBaixo() {
        x = getX();
        y = getY();
        y += andar;

        setBounds(x, y, width, height);
    }

    public void andarDireita() {
        x = getX();
        y = getY();
        x += andar;
        //System.out.println("Direita: X - " + getX() + " || " + "Y - " + getY());
        setBounds(x, y, width, height);
    }

    public void andarEsquerda() {
        x = getX();
        y = getY();
        x -= andar;
        //System.out.println("Direita: X - " + getX() + " || "+ "Y - "+getY());
        setBounds(x, y, width, height);
    }
    
    public void verExtDireita(){
        if(getX() > 485){
            int a = -60;
            int b = getY();
           // System.out.println("Verificação Direita");
            setBounds(a, b, width,height);
        }
        
    }
    public void verExtEsquerda(){
        if(getX() < -60){
            int a = 485;
            int b = getY();
            //System.out.println("Verificação Esquerda");
            setBounds(a, b, width,height);
        }
        
    }
    
}
