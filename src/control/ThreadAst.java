/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JPanel;
import model.asteroides;
import model.nave;

/**
 *
 * @author mathe
 */
public class ThreadAst extends Thread {

    private asteroides ast;
    private JPanel jPanel1;
    private nave nave;
    private boolean estaSuspensa = false;
    private boolean foiTerminado = false;

    private Rectangle formaAst;
    private Rectangle formaNave;

    public ThreadAst(asteroides ast, JPanel jPanel1) {
        this.ast = ast;
        this.jPanel1 = jPanel1;
        start();
    }

    public ThreadAst(Rectangle fNave) {
        this.formaNave = fNave;
        System.out.println("Retagulo nave : X - " + formaNave.getBounds());
    }

    public void run() {
        //gera o asteroidetry 

        //while (true) {
        Random random = new Random();
        int low = -40; // alterar para -30
        int hight = 0;
        int right = 580;
        int left = -10;
        int x = random.nextInt(right - left) + left;
        int y = random.nextInt(hight - low) + low;

        //asteroides 
        ast = new asteroides(x, y);
        jPanel1.add(ast);

        // movimentação
        movAst(ast);

        //colideNave();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        jPanel1.updateUI();
        //}
    }

    public void movAst(asteroides ast) {
        new Thread() {

            @Override
            public void run() {
                try {
                    while (true) {
                        ast.andarBaixo();
                        ast.verExtBaixo();
                        formaAst = ast.getBounds();
                        //System.out.println("Retagulo ast : X - " + formaAst.getBounds());

                        /*
                            if (formaNave.intersects(formaAst)) {
                                System.out.println("colidu");
                                synchronized (ThreadAst.this) {
                                    while (foiTerminado) {
                                        wait();
                                        break;
                                    }
                                    System.out.println("Nave colidiu");
                                }
                            }
                         */
                        Thread.sleep(20);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    public void colideNave() {
        /*
        para();
        try {
            if (formaAst.intersects(formaNave)) {
                synchronized (this) {
                    while (foiTerminado) {
                        wait();
                        break;
                    }
                    System.out.println("Nave colidiu");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
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
