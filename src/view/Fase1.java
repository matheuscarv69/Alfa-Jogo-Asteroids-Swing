/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Random;
import model.asteroides;
import model.nave;
import model.Bullets;
import control.ThreadAst;
import control.Utilidades;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
        jLabelNumScore = new javax.swing.JLabel();
        jLabelScore = new javax.swing.JLabel();
        jLabelLife = new javax.swing.JLabel();
        jLabelNumLife = new javax.swing.JLabel();

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

        jLabelNumScore.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabelNumScore.setText("0");

        jLabelScore.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabelScore.setText("Score:");

        jLabelLife.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabelLife.setText("Lifes:");

        jLabelNumLife.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabelNumLife.setText("3");

        javax.swing.GroupLayout jPanelFase1Layout = new javax.swing.GroupLayout(jPanelFase1);
        jPanelFase1.setLayout(jPanelFase1Layout);
        jPanelFase1Layout.setHorizontalGroup(
            jPanelFase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFase1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabelLife, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabelNumLife, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabelScore, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabelNumScore, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelFase1Layout.setVerticalGroup(
            jPanelFase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFase1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanelFase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLife, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNumLife, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelScore, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNumScore, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        setSize(new java.awt.Dimension(641, 456));
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
                    thAst = new ThreadAst(ast, nave, jPanelFase1, jLabelNumScore, jLabelNumLife);

                    if (Utilidades.lifes == 0) {
                        // Mostra a quantidade de pontos ao finalizar o jogo
                        Icon figura = new ImageIcon(getToolkit().createImage(getClass().getResource("../images/Nave.png")));
                        JOptionPane.showMessageDialog(null, "Você fez " + Utilidades.pontos + " pontos!", "Asteroides", JOptionPane.PLAIN_MESSAGE, figura);
                        stop();

                    }

                    try {
                        Thread.sleep(2300);
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

        //andar Esquerda 
        if (evt.getKeyChar() == 'a' || evt.getKeyChar() == 'A') {
            nave.andarEsquerda();
            nave.verExtEsquerda();

        }
        //andar Direita 
        if (evt.getKeyChar() == 'd' || evt.getKeyChar() == 'D') {
            nave.andarDireita();
            nave.verExtDireita();
        }
        //tiro
        if (evt.getKeyChar() == 'k' || evt.getKeyChar() == 'K') {
            gerarTiro();
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
    private javax.swing.JLabel jLabelLife;
    private javax.swing.JLabel jLabelNumLife;
    private javax.swing.JLabel jLabelNumScore;
    private javax.swing.JLabel jLabelScore;
    private javax.swing.JPanel jPanelFase1;
    // End of variables declaration//GEN-END:variables

}
