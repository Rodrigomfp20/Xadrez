package XadrezRMI;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.net.URL;
import javax.swing.*;

public class SquarePanel extends JPanel implements Serializable {

    private int row, column;
    private Peca peca;
    private static int tipoAtual, corAtual;
    private ChessGUI cg;
    private TabelaGUI tg;
    private int cor;
    private static boolean lastTabela;
    private JLabel imageLabel;
    private static int selectedRow, selectedCollumn;
    private boolean playable;

    private static Image pieceImage[][] = new Image[2][6];
    private static String imageFilename[][] = {
        {"wp.gif", "wn.gif", "wb.gif", "wr.gif", "wq.gif", "wk.gif"},
        {"bp.gif", "bn.gif", "bb.gif", "br.gif", "bq.gif", "bk.gif"}};

    public SquarePanel() {

    }

    //colors: 0 - white; 1 - black;
    //pieces: 0 - pawn(peao); 1 - knight(cavalo); 2 - bishop(bispo)
    //        3 - rook(torre); 4 - queen(rainha); 5 - king(rei)
    public SquarePanel(int x, int y, ChessGUI c) {
        row = x;
        column = y;
        cg = c;
        peca = new Peca(0);
        tipoAtual = -1;
        setPreferredSize(new Dimension(42, 42));
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(32, 32));
        playable = true;
        add(imageLabel);
        //loadPieceImages();
        addMouseListener(new SquareMouseListener());
    }

    public SquarePanel(int x, int y, TabelaGUI g, int cor) {
        row = x;
        column = y;
        tg = g;
        this.cor = cor;
        peca = new Peca(0);
        tipoAtual = -1;
        setPreferredSize(new Dimension(42, 42));
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(32, 32));
        playable = true;
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

    public void setPlayable(boolean estado) {
        this.playable = estado;
    }

    public void setPiece(int color, int type) {
        peca.setColor(color);
        peca.setType(type);
        if (type != -1) {
            imageLabel.setIcon(new ImageIcon(pieceImage[color][type]));
        } else {
            imageLabel.setIcon(null);
        }
    }

    public int getColor() {
        return peca.getColor();
    }

    public int getType() {
        return peca.getType();
    }

    public boolean verificaPeca() {
        if (imageLabel == null) {
            return false;
        }
        return true;
    }

    public int getSelectedX() {
        return selectedRow;
    }

    public int getSelectedY() {
        return selectedCollumn;
    }

    public void removePiece() {
        peca.setType(-1);
        imageLabel.setIcon(null);
    }

    public Peca getPeca() {
        return peca;
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
            if (playable) {
                if (cg != null) {
                    cg.selected(row, column);
                    if (peca.getType() != -1) {
                        if (tipoAtual != -1 && corAtual != peca.getColor() && !lastTabela) {
                            int tipoAux = tipoAtual;
                            tipoAtual = -1;
                            cg.moverPeca(selectedRow, selectedCollumn, row, column, tipoAux, corAtual);
                        } else {
                            selectedRow = row;
                            selectedCollumn = column;
                            tipoAtual = peca.getType();
                            corAtual = peca.getColor();
                            System.out.println(peca.getType());
                        }
                    } else {
                        if (tipoAtual != -1) {
                            if (lastTabela) {
                                cg.adicionarPeca(selectedRow, selectedCollumn, row, column, tipoAtual, corAtual);
                            } else {
                                cg.moverPeca(selectedRow, selectedCollumn, row, column, tipoAtual, corAtual);
                            }
                            tipoAtual = -1;
                            corAtual = 0;
                        }
                    }
                    lastTabela = false;
                    setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                } else {
                    tg.selected(row, column, cor);
                    if (peca.getType() != -1) {
                        selectedRow = row;
                        selectedCollumn = column;
                        tipoAtual = peca.getType();
                        corAtual = peca.getColor();
                        System.out.println(peca.getType());
                    } else {
                        if (tipoAtual != -1) {
                            if (!lastTabela) {
                                if (cor == corAtual) {
                                    tg.adicionaPeca(selectedRow, selectedCollumn, corAtual, tipoAtual);
                                }
                            }
                            tipoAtual = -1;
                            corAtual = 0;
                        }
                    }
                    lastTabela = true;
                }
            }

        }

    }

}
