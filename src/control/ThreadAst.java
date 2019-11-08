/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Rectangle;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import model.Bullets;
import model.asteroides;
import model.nave;

/**
 *
 * @author mathe
 */
public class ThreadAst extends Thread {

    private nave nave;
    private Bullets tiro;
    private asteroides ast;
    private JPanel jPanel1;

    private int x;
    private int y;
    private static int width = 87;
    private static int height = 65;

    public ThreadAst(asteroides ast, nave nave, JPanel jPanel1) {
        this.ast = ast;
        this.nave = nave;
        this.jPanel1 = jPanel1;
        start();
    }
    
    public ThreadAst(Bullets tiro){
        this.tiro = tiro;
    }

    //@Override
    public void run() {
        try {
            while (true) {
                ast.movAst();

                if (paraAst()) {
                   // System.out.println("Ast removido X: " + ast.getX() + " | Y: " + ast.getY());
                    jPanel1.remove(ast);
                    stop();
                }
                if (colisaoNA()) {
                    jPanel1.remove(ast);
                    stop();
                }
                /*
                if(colisaoTA()){
                    System.out.println("Tiro matou ast");
                }
                */
                Thread.sleep(10);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }
    
    public void getTiro(Bullets tiro) {
        this.tiro = tiro;
        System.out.println("Get Tiro X: " + tiro.getX() + " | Y: " + tiro.getY());
    }

    public void getAst(asteroides ast) {
        this.ast = ast;
        System.out.println("Ast X: " + ast.getX() + " | Y: " + ast.getY());
    }
    

    public boolean colisaoNA() {
        Rectangle rNave = new Rectangle(nave.getBounds());
        Rectangle rAst = new Rectangle(ast.getBounds());

        return rAst.intersects(rNave);
    }

    public boolean colisaoTA() {
        Rectangle rTiro = new Rectangle(tiro.getBounds());
        Rectangle rAst = new Rectangle(ast.getBounds());

        return rAst.intersects(rTiro);
    }

    public boolean paraAst() {
        //trocar para 420
        if (ast.getY() > 420) {
            return true;
        } else {
            return false;
        }
    }
}

/*
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
                            jPanel1.remove(ast);
                            stop();
                        }
                        if (colisaoTA()) {
                            jPanel1.remove(tiro);
                            jPanel1.remove(ast);
                            System.out.println("tiro");

                            //tiro.setVisible(false);
                            //ast.setVisible(false);
                        }

                        Thread.sleep(10);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                jPanel1.updateUI();
            }
        }.start();
    }

 */
