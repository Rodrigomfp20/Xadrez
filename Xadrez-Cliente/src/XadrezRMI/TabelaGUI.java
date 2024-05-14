package XadrezRMI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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
public class TabelaGUI extends JPanel{
    
    private SquarePanel[][] board = new SquarePanel[2][8];
    

    private JPanel chessPanel = new JPanel();
    
    private JPanel mesa = new JPanel();

    public TabelaGUI() {
        
        SquarePanel.loadPieceImages();
        chessPanel.setSize(380, 95);
        chessPanel.setLayout(new GridLayout(2, 8));
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                SquarePanel sqPanel = new SquarePanel(i, j, this);
                board[i][j] = sqPanel;
                chessPanel.add(sqPanel);

            }
        }
        add(chessPanel,BorderLayout.CENTER);
       
    }

    static public void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ChessGUI mainFrame = new ChessGUI();
                mainFrame.setVisible(true);

            }
        });

    }
}
