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
    private int vidas = 3;

    public ThreadAst(asteroides ast, nave nave, JPanel jPanel1) {
        this.ast = ast;
        this.nave = nave;
        this.jPanel1 = jPanel1;
        start();
    }

    public void run() {
        //gera o asteroide 

        while (true) {
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
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            jPanel1.updateUI();
        }
    }

    public void movAst(asteroides ast) {
        new Thread() {

            @Override
            public void run() {
                try {
                    while (true) {
                        ast.andarBaixo();
                        ast.verExtBaixo();

                        if (colisaoNA()) {
                            System.out.println("Colidiu");
                            stop();

                        }

                        Thread.sleep(10);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    

    public boolean colisaoNA() {
        Rectangle rNave = new Rectangle(nave.getBounds());
        Rectangle rAst = new Rectangle(ast.getBounds());

        return rAst.intersects(rNave);
    }

}
