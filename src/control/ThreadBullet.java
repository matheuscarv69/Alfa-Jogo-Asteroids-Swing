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
    private boolean estaSuspensa;
    private boolean foiTerminado;

    public ThreadBullet(Bullets tiro, nave nave, JPanel jPanel1) {
        this.tiro = tiro;
        this.jPanel1 = jPanel1;
        this.nave = nave;
        this.estaSuspensa = false;
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
                        tiro.movBullet();
                        System.out.println("Tiro X - " + tiro.getX() + "| Y - " + tiro.getY());
                        //formaTiro = tiro.getBounds();
                        //System.out.println("Tamanho formaTiro X: " + formaTiro.getX() + "Y : " + formaTiro.getY());
                       
                        //pausaTiro(tiro);
                        retiraTiro(tiro);

                        Thread.sleep(5);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    public void retiraTiro(Bullets tiro) {
        para();
        try {
            // mudar para -25
            if (tiro.getY() == -15) {
                synchronized (this) {
                    while (foiTerminado) {
                        //jPanel1.remove(tiro);
                        wait();
                        break;
                    }
                    System.out.println("Thread de mov tiro parada");

                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void pausaTiro(Bullets tiro) {
        try {

            synchronized (this) {
                while (estaSuspensa) {
                    //jPanel1.remove(tiro);
                    wait();

                }
                System.out.println("Thread de mov tiro parada");

            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void suspende() {
        this.estaSuspensa = true;
    }

    public synchronized void resumo() {
        this.estaSuspensa = false;
        notify();
    }

    public synchronized void para() {
        this.foiTerminado = true;
        notify();
    }
}
