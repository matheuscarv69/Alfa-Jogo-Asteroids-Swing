/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Rectangle;
import java.util.Random;
import model.asteroides;
import model.nave;
import model.Bullets;

/**
 *
 * @author mathe
 */
public class Fase1 extends javax.swing.JFrame {

    /**
     * Creates new form Fase1
     */
    nave nave;
    Rectangle formaTiro;
    Rectangle formaAst;
    Bullets tiro;
    asteroides ast;

    public Fase1() {
        initComponents();

        gerarNave();
        gerarAst();
        //verTiro();
        //System.out.println("Tamanho Jpanel: X - " + jPanelFase1.getWidth() + " | Y" + jPanelFase1.getHeight());
        //System.out.println("Jlabel 1 - position x = " + ast.getX() + "|| y = " + ast.getY());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFase1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asteroides");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jPanelFase1.setForeground(new java.awt.Color(255, 51, 255));

        javax.swing.GroupLayout jPanelFase1Layout = new javax.swing.GroupLayout(jPanelFase1);
        jPanelFase1.setLayout(jPanelFase1Layout);
        jPanelFase1Layout.setHorizontalGroup(
            jPanelFase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );
        jPanelFase1Layout.setVerticalGroup(
            jPanelFase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFase1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFase1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void gerarNave() {
        new Thread() {
            @Override
            public void run() {

                nave = new nave(312, 330);
                jPanelFase1.add(nave);

                System.out.println("Nave - x = " + nave.getX() + "|| y = " + nave.getY());

                try {
                    Thread.sleep(0);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                jPanelFase1.updateUI();

            }

        }.start();
    }

    public void gerarBullet(nave nave) {

        new Thread() {

            @Override
            public void run() {
                int a = nave.getX() + 36;
                // Acréscimo de +36 para a bala sair do bico da nave
                int b = nave.getY() - 13;
                // Decremento de -13 para a bala sair mais alto do bico da nave

                //Bullets 
                tiro = new Bullets(a, b);
                jPanelFase1.add(tiro);
                
                //movimentação
                movTiro(tiro);
               
            }
        }.start();

    }

    public void movTiro(Bullets tiro) {
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    tiro.movBullet();
                    tiro.verLimite(jPanelFase1);
                    formaTiro = tiro.getBounds();
                    checaColisao();
                    System.out.println("Tamanho formaTiro X: " + formaTiro.getX() + "Y : " + formaTiro.getY());
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }.start();
    }

    public void checaColisao() {
        if (formaAst.intersects(formaTiro)) {
            ast.setVisible(false);
            //tiro.setVisible(false);
        }
    }

    public void verTiro() {
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    checaColisao();
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    jPanelFase1.updateUI();
                }
            }

        }.start();

    }

    public void gerarAst() {
        new Thread() {

            @Override
            public void run() {
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
                    jPanelFase1.add(ast);

                    // movimentação
                    movAst(ast);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    jPanelFase1.updateUI();
                }
            }
        }.start();
    }

    public void movAst(asteroides ast) {
        new Thread() {

            @Override
            public void run() {
                while (true) {
                    ast.andarBaixo();
                    ast.verExtBaixo();
                    formaAst = ast.getBounds();

                    System.out.println("Tamanho formaAst X: " + ast.getX() + "Y : " + ast.getY());
                    try {

                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }.start();
    }


    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:

        if (evt.getKeyChar() == 'w') {
            //andar cima    
            //nave.andarCima();
        }

        if (evt.getKeyChar() == 'a') {
            //andar esquerda 
            nave.andarEsquerda();
            nave.verExtEsquerda();
        }

        if (evt.getKeyChar() == 'd') {
            //andar direita
            nave.andarDireita();
            nave.verExtDireita();
        }
        if (evt.getKeyChar() == 'k') {
            //tiro
            gerarBullet(nave);

        }
        if (evt.getKeyChar() == 'p') {
            //pausa
        }

    }//GEN-LAST:event_formKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Fase1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fase1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fase1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fase1.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fase1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelFase1;
    // End of variables declaration//GEN-END:variables
}
