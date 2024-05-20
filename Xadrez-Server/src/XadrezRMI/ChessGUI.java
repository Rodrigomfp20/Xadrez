package XadrezRMI;




import java.awt.*;
import javax.swing.*;

public class ChessGUI extends JPanel {

    private SquarePanel[][] board = new SquarePanel[8][8];
    

    private JPanel chessPanel = new JPanel();
    
    private JPanel mesa = new JPanel();

    public ChessGUI() {
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
        

        board[7][0].setPiece(0, 3);
        board[7][1].setPiece(0, 1);
        board[7][2].setPiece(0, 2);
        board[7][3].setPiece(0, 4);
        board[7][4].setPiece(0, 5);
        board[6][0].setPiece(0, 0);
        board[0][0].setPiece(1, 3);
        board[0][1].setPiece(1, 1);
        board[0][2].setPiece(1, 2);
        board[0][3].setPiece(1, 4);
        board[0][4].setPiece(1, 5);
        board[1][0].setPiece(1, 0);

        //board[7][3].removePiece();
        //board[0][0].setBackColor(1);
    }

    public void selected(int x, int y) {
        String[] letras =  {"a","b","c","d","e","f","g","h"};
        System.out.println("Posicao -> " + letras[y]+x);
        
        //board[0][4].setPiece(board[x][y].getColor(),board[x][y].getType());
        
    }
    
    public void setboard(SquarePanel[][] board){
        this.board = board;
    }

}
