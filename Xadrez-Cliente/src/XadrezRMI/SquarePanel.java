package XadrezRMI;


import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.net.URL;
import javax.swing.*;

public class SquarePanel extends JPanel implements Serializable {

    private int row, column, color, type;
    private static int tipoAtual, corAtual;
    private ChessGUI cg;
    private TabelaGUI tg;
    private JLabel imageLabel;
    private static int selectedRow,selectedCollumn;

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
        type = -1;
        tipoAtual = -1;
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
        type = -1;
        tipoAtual = -1;
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
        return color;
    }
    public int getType(){
        return type;
    }
    public int getSelectedX(){
        return selectedRow;
    }
    public int getSelectedY(){
        return selectedCollumn;
    }

    public void removePiece() {
        type= -1;
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
            if(type != -1){
                selectedRow = row;
                selectedCollumn = column;
                tipoAtual = type;
                corAtual = color;
                System.out.println(type);
            }
            else{
                if(tipoAtual != -1 ){
                    cg.moverPeca(selectedRow,selectedCollumn,row,column,tipoAtual, corAtual);
                    tipoAtual = -1;
                    corAtual = 0;
                }
            }
            setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }

    }

}
