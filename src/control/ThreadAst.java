/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Rectangle;
import javax.swing.JLabel;
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
    private JLabel pontos;
    private JLabel lifes;

    public ThreadAst(asteroides ast, nave nave, JPanel jPanel1, JLabel pontos, JLabel lifes) {
        this.ast = ast;
        this.nave = nave;
        this.jPanel1 = jPanel1;
        this.pontos = pontos;
        this.lifes = lifes;
        start();
    }

    //@Override
    public void run() {
        try {
            while (true) {
                ast.movAst();

                // remove asteroide apos ele atingir y = 420
                if (astLimite()) {
                    jPanel1.remove(ast);
                    stop();
                }

                // colisao entre nave e asteroide
                if (colisaoNA()) {

                    jPanel1.remove(ast);
                    jPanel1.validate();
                    jPanel1.repaint();

                    Utilidades.lifes--;
                    lifes.setText(String.valueOf(Utilidades.lifes));

                    stop();
                }
                // Para o movimento dos asteroides se a qtd de vidas for igual a 0
                if (Utilidades.lifes == 0) {
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
                        jPanel1.remove(tiro);
                        stop();
                    }

                    // Função de colisão tiro com asteroide funcionando
                    if (colisaoTA()) {
                        jPanel1.remove(ast);
                        jPanel1.remove(tiro);
                        jPanel1.validate();
                        jPanel1.repaint();

                        // Funcao para parar a movimentacao do asteroide ao ser atingido
                        paraAst();

                        Utilidades.pontos += 10;
                        pontos.setText(String.valueOf(Utilidades.pontos));

                        stop();
                    }

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

            }

        }.start();
    }

    // Funcao que retorna V/F se a nave colidir com o asteroide
    public boolean colisaoNA() {
        Rectangle rNave = new Rectangle(nave.getBounds());
        Rectangle rAst = new Rectangle(ast.getBounds());

        return rAst.intersects(rNave);
    }

    // Funcao que retorna V/F se o tiro colidir com o asteroide
    public boolean colisaoTA() {
        Rectangle rTiro = new Rectangle(tiro.getBounds());
        Rectangle rAst = new Rectangle(ast.getBounds());

        return rTiro.intersects(rAst);
    }

    // Funcao que para a movimentacao do asteroide caso o y seja maior que 420
    public boolean astLimite() {
        if (ast.getY() > 420) {
            return true;
        } else {
            return false;
        }
    }

    // Funcao para parar a movimentacao do asteroide ao ser atingido
    public void paraAst() {
        stop();
    }

}
