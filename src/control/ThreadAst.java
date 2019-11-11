/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Rectangle;
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
    private int score = 0;
    private int life = 3;

    public ThreadAst(asteroides ast, nave nave, JPanel jPanel1) {
        this.ast = ast;
        this.nave = nave;
        this.jPanel1 = jPanel1;
        start();
    }

    //@Override
    public void run() {
        try {
            while (true) {
                ast.movAst();
                // remove asteroide apos ele atingir y = 420
                if (paraAst()) {
                    //System.out.println("Ast removido X: " + ast.getX() + " | Y: " + ast.getY());
                    jPanel1.remove(ast);
                    stop();
                }
                // colisao entre nave e asteroide
                if (colisaoNA()) {
                    
                    jPanel1.remove(ast);
                    stop();
                }

                Thread.sleep(10);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    public void getTiro(Bullets tiro) {
        this.tiro = tiro;
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    tiro.movTiro();

                    // Função para matar tiro
                    if (tiro.getY() == -26) {
                        //System.out.println("Stop movTiro funcionando");
                        jPanel1.remove(tiro);
                        stop();
                    }
                    // Função de colisão tiro com asteroide funcionando

                    if (colisaoTA()) {
                        // System.out.println("Tiro colidiu com Asteroide");
                        jPanel1.remove(ast);
                        jPanel1.remove(tiro);
                        stop();
                    }
                    /*
                    while (colisaoTA()) {
                        jPanel1.remove(ast);
                        jPanel1.remove(tiro);
                        stop();
                        score += 10;
                    }
                     */
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

            }

        }.start();
    }

    public int contPt() {
        System.out.println("score : " + score);
        return score;
    }

    /*
    public String retornaScore(){
        return String.valueOf(score);
    }
     */
    public boolean colisaoNA() {
        Rectangle rNave = new Rectangle(nave.getBounds());
        Rectangle rAst = new Rectangle(ast.getBounds());
        life--;
        return rAst.intersects(rNave);
    }

    public boolean colisaoTA() {
        Rectangle rTiro = new Rectangle(tiro.getBounds());
        Rectangle rAst = new Rectangle(ast.getBounds());

        return rTiro.intersects(rAst);
    }

    public boolean paraAst() {
        if (ast.getY() > 420) {
            return true;
        } else {
            return false;
        }
    }
}
