package XadrezRMI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author rodrigomfp20011
 */
public class TabelaGUI extends JPanel implements Serializable {

    private SquarePanel[][] board = new SquarePanel[2][8];

    private JPanel chessPanel = new JPanel();

    private JPanel mesa = new JPanel();

    public TabelaGUI(int cor) {

        SquarePanel.loadPieceImages();
        chessPanel.setSize(380, 95);
        chessPanel.setLayout(new GridLayout(2, 8));

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                SquarePanel sqPanel = new SquarePanel(i, j, this,cor);
                board[i][j] = sqPanel;
                chessPanel.add(sqPanel);

            }
        }
        add(chessPanel, BorderLayout.CENTER);
    }
    public void selected(int x, int y,int cor) {
        String[] letras =  {"a","b","c","d","e","f","g","h"};
        System.out.println("Posicao -> " + letras[y] + (8-x));
        System.out.println(cor);
    }

    public void organizaPecas(int cor) {
        limpaTabuleiro();
        board[1][0].setPiece(cor, 3);
        board[1][1].setPiece(cor, 1);
        board[1][2].setPiece(cor, 2);
        board[1][3].setPiece(cor, 4);
        board[1][4].setPiece(cor, 5);
        board[1][5].setPiece(cor, 2);
        board[1][6].setPiece(cor, 1);
        board[1][7].setPiece(cor, 3);
        for (int i = 0; i < 8; i++) {
            board[0][i].setPiece(cor, 0);
        }
    }

    public void limpaTabuleiro() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].removePiece();
            }
        }
    }
    public void removerPeca(int row, int column){
        board[row][column].setPiece(0, -1);
    }
}
