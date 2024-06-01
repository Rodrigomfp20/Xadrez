package XadrezRMI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

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

    private Mesa mesaJogo;

    public TabelaGUI(int cor,Mesa mesaJogo) {
        this.mesaJogo = mesaJogo;
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
    
    public ArrayList<Peca> getBoard(){
        ArrayList<Peca> pecas = new ArrayList();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                pecas.add(board[i][j].getPeca());
            }
        }
        return pecas;
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
        System.out.println(board.length);
        for (int i = 0; i < 8; i++) {
            board[0][i].setPiece(cor, 0);
        }
    }
    public void adicionaPeca(int row,int collumn,int cor, int tipo){
        String[] letras =  {"a","b","c","d","e","f","g","h"};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println("A percorrer as pecas da tabela...");
                if(board[i][j].getType() == -1){
                    board[i][j].setPiece(cor, tipo);
                    j=10;
                    i=3;
                }
            }
        }
        if(row != -1 && collumn != -1){
            mesaJogo.removePecaDentro(row,collumn);
            atualizaServidor(letras[row] + (8-collumn),"Fora");
        }
        else{
            atualizaServidor("","Fora");
        }
        
    }
    public void setBoard(ArrayList<Peca> pecas){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j].setPiece(pecas.get(i*8+j).getColor(),pecas.get(i*8+j).getType());
            }
        }
    }
    
    public void atualizaServidor(String posInicial, String posFinal){
        ArrayList<Peca> pecasTabuleiro = mesaJogo.getPiecesPosition();
        ArrayList<Peca> pecasForaBrancas = mesaJogo.getPecasBrancas();
        ArrayList<Peca> pecasForaPretas = mesaJogo.getPecasPretas();
        try {
            mesaJogo.getRefServidor().moverPecaServidor(pecasTabuleiro,pecasForaBrancas,pecasForaPretas,posInicial,posFinal);
        } catch (RemoteException ex) {
            Logger.getLogger(ChessGUI.class.getName()).log(Level.SEVERE, null, ex);
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
