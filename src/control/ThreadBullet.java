/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Bullets;
import model.nave;

/**
 *
 * @author mathe
 */
public class ThreadBullet extends Thread {

    private Bullets tiro;
    private nave nave;
    private JPanel jPanel1;

    public ThreadBullet(Bullets tiro, nave nave, JPanel jPanel1) {
        this.tiro = tiro;
        this.jPanel1 = jPanel1;
        this.nave = nave;
        start();
    }

    public void run() {
        int a = nave.getX() + 36;
        // Acréscimo de +36 para a bala sair do bico da nave
        int b = nave.getY() - 13;
        // Decremento de -13 para a bala sair mais alto do bico da nave

        //Bullets 
        tiro = new Bullets(a, b);
        jPanel1.add(tiro);

        //movimentação
        movTiro(tiro);
        //retiraTiro(tiro);

    }

    public void movTiro(Bullets tiro) {

        new Thread() {

            @Override
            public void run() {
                try {
                    while (true) {
                        //tiro.movBullet();
                        //System.out.println("Tiro X - " + tiro.getX() + "| Y - " + tiro.getY());
                        
                        if (paraTiro(tiro)) {
                            stop();
                        }

                        Thread.sleep(5);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    public boolean paraTiro(Bullets tiro) {
        if (tiro.getY() == -15) {
            return true;
        } else {
            return false;
        }
    }

}
