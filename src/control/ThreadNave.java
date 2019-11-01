/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.JPanel;
import model.nave;

/**
 *
 * @author mathe
 */
public class ThreadNave extends Thread {

    private nave nave;
    private JPanel jPanel1;

    private int x;
    private int y;
    private static int andar = 25;
    private static int width = 85;
    private static int height = 72;

    public ThreadNave(nave nave, JPanel jPanel1) {
        this.nave = nave;
        this.jPanel1 = jPanel1;
        start();
    }

    public void run() {
        //gera a nave
        nave = new nave(312, 330);

        jPanel1.add(nave);
        System.out.println("Classe Thread Nave gerada");
        //System.out.println("Nave - x = " + nave.getX() + "|| y = " + nave.getY());

        try {
            Thread.sleep(0);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        jPanel1.updateUI();
    }

    public void andarCima() {
        this.x = nave.getX();
        this.y = nave.getY();
        this.y -= andar;
        nave.setBounds(this.x, this.y, width, height);
    }

    public void andarBaixo() {
        this.x = nave.getX();
        this.y = nave.getY();
        this.y += andar;

        nave.setBounds(this.x, this.y, width, height);
    }

    public void andarDireita() {
        this.x = nave.getX();
        this.y = nave.getY();
        this.x += andar;
        //System.out.println("Direita: X - " + getX() + " || " + "Y - " + getY());
        nave.setBounds(this.x, this.y, width, height);
    }

    public void andarEsquerda() {
        this.x = nave.getX();
        this.y = nave.getY();
        this.x -= andar;
        //System.out.println("Direita: X - " + getX() + " || "+ "Y - "+getY());
        nave.setBounds(this.x, this.y, width, height);
    }

    public void verExtDireita() {
        if (nave.getX() > 580) {
            this.x = -40;
            this.y = nave.getY();
            // System.out.println("Verificação Direita");
            nave.setBounds(this.x, this.y, width, height);
        }

    }

    public void verExtEsquerda() {
        if (nave.getX() < -60) {
            this.x = 580;
            this.y = nave.getY();
            //System.out.println("Verificação Esquerda");
            nave.setBounds(this.x, this.y, width, height);
        }
    }
    
    public nave retornaNave(){
        return nave;
    }
    

}
