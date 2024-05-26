package XadrezRMI;




import java.awt.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ChessGUI extends JPanel implements Serializable {
    
    private int xInicial,yInicial,xFinal,yFinal;

    private SquarePanel[][] board = new SquarePanel[8][8];

    private JPanel chessPanel = new JPanel();
    
    private Mesa mesaJogo;

    public ChessGUI(Mesa mesaJogo) {
        this.mesaJogo = mesaJogo;
        chessPanel.setBackground(Color.black);
        SquarePanel.loadPieceImages();
        chessPanel.setSize(380, 380);
        chessPanel.setLayout(new GridLayout(8, 8));
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                SquarePanel sqPanel = new SquarePanel(i, j, this);
                sqPanel.setBackColor((i + j) % 2);
                board[i][j] = sqPanel;
                chessPanel.add(sqPanel);

            }
        }
        add(chessPanel,BorderLayout.CENTER);
        organizaPecas();
        
        //board[7][3].removePiece();
        //board[0][0].setBackColor(1);
    }
    public ArrayList<Peca> getBoard(){
        ArrayList<Peca> pecas = new ArrayList();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pecas.add(board[i][j].getPeca());
            }
        }
        return pecas;
    }

    public void selected(int x, int y) {
        String[] letras =  {"a","b","c","d","e","f","g","h"};
        System.out.println("Posicao -> " + letras[y] + (8-x)); 
    }
    public void moverPeca(int inicialX, int inicialY,int finalX, int finalY, int tipo , int cor){
        if(board[inicialX][inicialY].getPeca().getColor() == cor && board[inicialX][inicialY].getPeca().getType() != -1){
            if(board[finalX][finalY].getType() != -1){
                mesaJogo.adicionaPecaFora(board[finalX][finalY].getColor(), board[finalX][finalY].getType());
            }
            board[finalX][finalY].setPiece(cor,tipo);
            board[inicialX][inicialY].removePiece();
            System.out.println("moverPeca");
            atualizaServidor();
        }
    }
    public void atualizaServidor(){
        ArrayList<Peca> pecasTabuleiro = mesaJogo.getPiecesPosition();
        ArrayList<Peca> pecasForaBrancas = mesaJogo.getPecasBrancas();
        ArrayList<Peca> pecasForaPretas = mesaJogo.getPecasPretas();
        try {
            mesaJogo.getRefServidor().moverPecaServidor(pecasTabuleiro,pecasForaBrancas,pecasForaPretas);
        } catch (RemoteException ex) {
            Logger.getLogger(ChessGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void adicionarPeca(int inicialX, int inicialY,int finalX, int finalY, int tipo , int cor){
            mesaJogo.removePecaFora(cor,inicialX,inicialY);
            board[finalX][finalY].setPiece(cor,tipo);
            atualizaServidor();
    }
    public void moverPecaLocal(int inicialX, int inicialY,int finalX, int finalY, int tipo , int cor){
        board[finalX][finalY].setPiece(cor,tipo);
        board[inicialX][inicialY].removePiece();
        System.out.println("moverPeca");
    }
    public void removerPeca(int row, int collumn){
        board[row][collumn].setPiece(0, -1);
    }
    
    
    //colors: 0 - white; 1 - black;
    //pieces: 0 - pawn(peao); 1 - knight(cavalo); 2 - bishop(bispo)
    //        3 - rook(torre); 4 - queen(rainha); 5 - king(rei)
    public void organizaPecas(){
        limpaTabuleiro();
        board[7][0].setPiece(0, 3);
        board[7][1].setPiece(0, 1);
        board[7][2].setPiece(0, 2);
        board[7][3].setPiece(0, 4);
        board[7][4].setPiece(0, 5);
        board[7][5].setPiece(0, 2);
        board[7][6].setPiece(0, 1);
        board[7][7].setPiece(0, 3);

        for(int i=0;i<8;i++){
            board[6][i].setPiece(0, 0); 
        }
        board[0][0].setPiece(1, 3);
        board[0][1].setPiece(1, 1);
        board[0][2].setPiece(1, 2);
        board[0][3].setPiece(1, 4);
        board[0][4].setPiece(1, 5);
        board[0][5].setPiece(1, 2);
        board[0][6].setPiece(1, 1);
        board[0][7].setPiece(1, 3);
        for(int i=0;i<8;i++){
            board[1][i].setPiece(1, 0); 
        }
        
    }
    public void limpaTabuleiro(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].removePiece();
            }
        }
    }
    
    public void setBoard(ArrayList<Peca> pecas){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j].setPiece(pecas.get(i*8+j).getColor(),pecas.get(i*8+j).getType());
            }
        }
    }
    public void setPlayable(boolean estado){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j].setPlayable(estado);
            }
        }
    }

}
