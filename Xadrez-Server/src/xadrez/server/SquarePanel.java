package xadrez.server;


import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class SquarePanel extends JPanel {

    private int row, column, color, type;
    private ChessGUI cg;
    private TabelaGUI tg;
    private JLabel imageLabel;

    private static Image pieceImage[][] = new Image[2][6];
    private static String imageFilename[][] = {
        {"wp.gif", "wn.gif", "wb.gif", "wr.gif", "wq.gif", "wk.gif"},
        {"bp.gif", "bn.gif", "bb.gif", "br.gif", "bq.gif", "bk.gif"}};

    public SquarePanel(){
        
    }
    //colors: 0 - white; 1 - black;
    //pieces: 0 - pawn(peao); 1 - knight(cavalo); 2 - bishop(bispo)
    //        3 - rook(torre); 4 - queen(rainha); 5 - king(rei)
    public SquarePanel(int x, int y, ChessGUI c) {
        row = x;
        column = y;
        cg = c;
        setPreferredSize(new Dimension(42, 42));
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(32, 32));
        add(imageLabel);
        //loadPieceImages();
        addMouseListener(new SquareMouseListener());
    }
    
    public SquarePanel(int x, int y, TabelaGUI g) {
        row = x;
        column = y;
        tg = g;
        setPreferredSize(new Dimension(42, 42));
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(32, 32));
        add(imageLabel);
        //loadPieceImages();
        addMouseListener(new SquareMouseListener());
    }

    public static void loadPieceImages() {
        URL iconURL;
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                iconURL = ClassLoader.getSystemResource("images/" + imageFilename[i][j]);
                System.out.println(iconURL);
                pieceImage[i][j] = Toolkit.getDefaultToolkit().getImage(iconURL);
                
            }
        }
    }

    public void setBackColor(int color) {
        if (color == 0) {
            setBackground(Color.white);
        } else {
            setBackground(Color.gray);
        }
    }

    public void setPiece(int color, int type) {
        this.color = color;
        this.type = type;
        imageLabel.setIcon(new ImageIcon(pieceImage[color][type]));
    }
    public int getColor(){
        System.out.println("ola cor");
        return color;
    }
    public int getType(){
        System.out.println("ola tipo");
        return type;
    }

    public void removePiece() {
        imageLabel.setIcon(null);
    }

    class SquareMouseListener extends MouseAdapter {

        public void mouseEntered(MouseEvent e) {
            setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            //repaint();
        }

        public void mouseExited(MouseEvent e) {
            setBorder(null);
            //repaint();
        }

        public void mousePressed(MouseEvent e) {
            cg.selected(row, column);
            setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }

    }

}
