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
import control.ThreadAst;
import control.ThreadBullet;
import control.ThreadNave;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathe
 */
public class Fase1 extends javax.swing.JFrame {

    /**
     * Creates new form Fase1
     */
    nave nave;
    Bullets tiro;
    asteroides ast;
    ThreadAst thAst;

    public Fase1() {
        initComponents();

        gerarNave();
        gerarAst();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFase1 = new javax.swing.JPanel();
        jLabelScore = new javax.swing.JLabel();
        jLabelNumScore = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asteroides");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jPanelFase1.setForeground(new java.awt.Color(255, 51, 255));
        jPanelFase1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanelFase1KeyPressed(evt);
            }
        });

        jLabelScore.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabelScore.setText("Score:");

        jLabelNumScore.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabelNumScore.setText("0");

        javax.swing.GroupLayout jPanelFase1Layout = new javax.swing.GroupLayout(jPanelFase1);
        jPanelFase1.setLayout(jPanelFase1Layout);
        jPanelFase1Layout.setHorizontalGroup(
            jPanelFase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFase1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelScore, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNumScore)
                .addContainerGap(539, Short.MAX_VALUE))
        );
        jPanelFase1Layout.setVerticalGroup(
            jPanelFase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFase1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelFase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelScore, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNumScore, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(381, Short.MAX_VALUE))
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
        nave = new nave(312, 330);
        jPanelFase1.add(nave);
    }

    public void gerarTiro() {
        int a = nave.getX() + 36;
        int b = nave.getY() - 13;

        tiro = new Bullets(a, b);
        jPanelFase1.add(tiro);
        
        // Movimentacao de tiro feita na ThreadAst no metodo getTiro
        thAst.getTiro(tiro);
    }

    public void gerarAst() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    Random random = new Random();
                    int low = -30;
                    int hight = 0;
                    int right = 540;
                    int left = -10;
                    int x = random.nextInt(right - left) + left;
                    int y = random.nextInt(hight - low) + low;

                    ast = new asteroides(x, y, jPanelFase1);
                    jPanelFase1.add(ast);
                    
                    // Movimentação de tiro dentro da ThreadAst
                    thAst = new ThreadAst(ast, nave, jPanelFase1);

                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    jPanelFase1.updateUI();
                }
            }
        }.start();

    }


    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:

        if (evt.getKeyChar() == 'a') {
            //andar esquerda 
            nave.andarEsquerda();
            nave.verExtEsquerda();
        }

        if (evt.getKeyChar() == 'd') {
            //andar esquerda 
            nave.andarDireita();
            nave.verExtDireita();
        }

        if (evt.getKeyChar() == 'k') {
            //tiro
            gerarTiro();
            int score = 0;
            
            //jLabelNumScore.setText(String.valueOf(thAst.contPt()));
        }
        // menu de pausa
        if (evt.getKeyChar() == 'p') {
            //pausa

        }
        if (evt.getKeyChar() == 'ç') {
            //pausa

        }

    }//GEN-LAST:event_formKeyTyped

    private void jPanelFase1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanelFase1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelFase1KeyPressed

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
    private javax.swing.JLabel jLabelNumScore;
    private javax.swing.JLabel jLabelScore;
    private javax.swing.JPanel jPanelFase1;
    // End of variables declaration//GEN-END:variables
}
