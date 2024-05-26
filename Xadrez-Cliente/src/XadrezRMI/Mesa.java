package XadrezRMI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author raulm
 */
public class Mesa extends javax.swing.JFrame {

    private InterfaceJogo refServidor;

    /**
     * Creates new form Mesa
     */
    public Mesa(InterfaceJogo refServidor) {
        this.refServidor = refServidor;
        initComponents();
    }

    public Mesa() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        chessBoard = new ChessGUI(this);
        nome1 = new javax.swing.JLabel();
        nome2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        pecasPretas = new TabelaGUI(0,this);
        pecasBrancas = new TabelaGUI(1,this);
        jScrollPane5 = new javax.swing.JScrollPane();
        observadores = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1140, 700));
        setSize(new java.awt.Dimension(1140, 700));

        chessBoard.setBackground(new java.awt.Color(255, 255, 30));
        chessBoard.setPreferredSize(new java.awt.Dimension(380, 380));

        javax.swing.GroupLayout chessBoardLayout = new javax.swing.GroupLayout(chessBoard);
        chessBoard.setLayout(chessBoardLayout);
        chessBoardLayout.setHorizontalGroup(
            chessBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        chessBoardLayout.setVerticalGroup(
            chessBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        nome1.setText("posicao1");

        nome2.setText("posicao2");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel4.setText("Chat");

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setLabel("Passar a Observador");

        jLabel5.setText("Log");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jButton3.setLabel("Arrumar Pecas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setLabel("Remover Todas as Pecas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setLabel("Sair do Jogo");

        pecasPretas.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout pecasPretasLayout = new javax.swing.GroupLayout(pecasPretas);
        pecasPretas.setLayout(pecasPretasLayout);
        pecasPretasLayout.setHorizontalGroup(
            pecasPretasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pecasPretasLayout.setVerticalGroup(
            pecasPretasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pecasBrancas.setPreferredSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout pecasBrancasLayout = new javax.swing.GroupLayout(pecasBrancas);
        pecasBrancas.setLayout(pecasBrancasLayout);
        pecasBrancasLayout.setHorizontalGroup(
            pecasBrancasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pecasBrancasLayout.setVerticalGroup(
            pecasBrancasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        observadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Observadores"
            }
        ));
        jScrollPane5.setViewportView(observadores);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nome1)
                            .addComponent(nome2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chessBoard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pecasPretas, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .addComponent(pecasBrancas, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                        .addGap(125, 125, 125)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jLabel5)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(167, 167, 167)
                                        .addComponent(jLabel4))))
                            .addComponent(jScrollPane3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nome2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chessBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome1)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pecasPretas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(pecasBrancas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(121, 121, 121))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.organizaPecas();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.limpaTabuleiro();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public void setNome1(String nome) {
        this.nome1.setText(nome);
    }

    public void setNome2(String nome) {
        this.nome2.setText(nome);
    }

    public void addObservador(String nome) {
        DefaultTableModel model = (DefaultTableModel) this.observadores.getModel();
        model.addRow(new Object[]{nome});
    }

    public void setPecas(int inicialX, int inicialY, int finalX, int finalY, int tipo, int cor) {
        chessBoard.moverPecaLocal(inicialX, inicialY, finalX, finalY, tipo, cor);
    }

    public InterfaceJogo getRefServidor() {
        return this.refServidor;
    }

    public void removePecaFora(int cor, int row, int column) {
        if (cor == 1) {
            pecasBrancas.removerPeca(row, column);
            System.out.println("coluna: " + row + "linha -> " + column);
        } else {
            System.out.println("coluna: " + row + "linha -> " + column);
            pecasPretas.removerPeca(row, column);
        }

    }

    public void removePecaDentro(int row, int column) {
        chessBoard.removerPeca(row, column);
    }
    public ArrayList<Peca> getPecasBrancas() {
        return pecasBrancas.getBoard();
    }
    public ArrayList<Peca> getPecasPretas() {
        return pecasPretas.getBoard();
    }

    public ArrayList<Peca> getPiecesPosition() {
        return chessBoard.getBoard();
    }

    public void setPiecesPosition(ArrayList<Peca> pecas) {
        chessBoard.setBoard(pecas);
    }
    
    public void setPecasFora(ArrayList<Peca> pecasBrancas,ArrayList<Peca> pecasPretas){
        this.pecasBrancas.setBoard(pecasBrancas);
        this.pecasPretas.setBoard(pecasPretas);
    }

    public void organizaPecas() {
        chessBoard.organizaPecas();
        pecasBrancas.limpaTabuleiro();
        pecasPretas.limpaTabuleiro();
        chessBoard.atualizaServidor();
    }

    ;
public void limpaTabuleiro() {
        chessBoard.limpaTabuleiro();
        pecasBrancas.organizaPecas(1);
        pecasPretas.organizaPecas(0);
        chessBoard.atualizaServidor();
    }
public void adicionaPecaFora(int cor, int tipo){
    if (cor == 1) {
            pecasBrancas.adicionaPeca(-1,-1,cor,tipo);
        } else {
            pecasPretas.adicionaPeca(-1,-1,cor,tipo);
        }
}
public void setPlayable(boolean estado){
    this.chessBoard.setPlayable(estado);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ChessGUI chessBoard;/*
    private javax.swing.JPanel chessBoard;
    */
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel nome1;
    private javax.swing.JLabel nome2;
    private javax.swing.JTable observadores;
    /*
    private javax.swing.JPanel pecasBrancas;
    */
    private TabelaGUI pecasBrancas;
    /*
    private javax.swing.JPanel pecasPretas;
    */
    private TabelaGUI pecasPretas;
    // End of variables declaration//GEN-END:variables
}
