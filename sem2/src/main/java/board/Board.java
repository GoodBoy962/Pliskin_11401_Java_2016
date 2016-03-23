package board;

import enums.Color;
import enums.Figure;
import enums.FigureColor;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Board extends JFrame {

    private final Integer SIZE = 8;
    private int buttonSize = 65;
    private FigureColor[][] field;
    private boolean whiteKingInCheck;
    private boolean blackKingInCheck;
    private byte turn;
    private JButton selected;
    private int selectedI;
    private int selectedJ;
    private boolean whiteKingMotion;
    private boolean blackKingMotion;
    private boolean whiteLeftCastleMotion;
    private boolean whiteRightCastleMotion;
    private boolean blackLeftCastleMotion;
    private boolean blackRightCastleMotion;
    private BufferedReader is;
    private PrintWriter pw;
    private int yourTurn;
    private int kingI;
    private int kingJ;
    private List<String> opponentFigures;

    public Board() throws IOException {

    }

    public void goGame(PrintWriter pw, BufferedReader is, int yourTurn) {
        this.yourTurn = yourTurn;
        if (yourTurn()) {
            setTitle("WHITE");
        } else {
            setTitle("BLACK");
        }
        this.is = is;
        this.pw = pw;
        setBounds(50, 50, buttonSize * SIZE + 100, buttonSize * SIZE + 100);
        setLayout(new GroupLayout(getContentPane()));
        generateField();
        whiteKingInCheck = false;
        blackKingInCheck = false;
        whiteLeftCastleMotion = false;
        whiteRightCastleMotion = false;
        blackLeftCastleMotion = false;
        blackRightCastleMotion = false;
        turn = 0;
        kingJ = 4;
        kingI = (7 + yourTurn) % 8;
        opponentFigures = new ArrayList<>();
        if (!yourTurn()) {
            for (int i = 0; i < 7; i++) {
                opponentFigures.add("6" + i);
                opponentFigures.add("7" + i);
            }

        } else {
            for (int i = 0; i < 7; i++) {
                opponentFigures.add("1" + i);
                opponentFigures.add("0" + i);
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JButton jb = new JButton();
                jb.setBounds(j * buttonSize, i * buttonSize, buttonSize, buttonSize);
                if ((i + j) % 2 == 1) {
                    jb.setBackground(java.awt.Color.lightGray);
                } else {
                    jb.setBackground(java.awt.Color.white);
                }
                jb.setBorderPainted(true);
                jb.repaint();
                setImageForButton(jb, i, j);
                jb.setContentAreaFilled(false);
                jb.setOpaque(true);
                jb.addActionListener(e -> {
                    JButton button = (JButton) e.getSource();
                    int i1 = button.getY() / buttonSize;
                    int j1 = button.getX() / buttonSize;
                    if (selected == null) {
                        if (field[i1][j1].color == Color.WHITE && turn == 0 || field[i1][j1].color == Color.BLACK && turn == 1) {
                            selected = button;
                            selectedI = i1;
                            selectedJ = j1;
                            selected.setFocusPainted(true);
                        }
                    } else {
                        try {
                            // TODO Логика пешки
                            if (field[selectedI][selectedJ].figure == Figure.PONE) {
                                if (field[selectedI][selectedJ].color == Color.WHITE) {
                                    if (j1 == selectedJ && i1 == selectedI - 1 && field[i1][j1].figure == Figure.NON ||
                                            j1 == selectedJ && selectedI == 6 && i1 == selectedI - 2 && field[i1][j1].figure == Figure.NON && field[i1 + 1][j1].figure == Figure.NON ||
                                            ((j1 == selectedJ + 1 || j1 == selectedJ - 1) && i1 == selectedI - 1 && field[i1][j1].figure != Figure.NON &&
                                                    field[i1][j1].color == Color.BLACK)) {
                                        try {
                                            doChanges(i1, j1, button, Figure.PONE, 0);
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                } else if (j1 == selectedJ && i1 == selectedI + 1 && field[i1][j1].figure == Figure.NON ||
                                        j1 == selectedJ && selectedI == 1 && i1 == selectedI + 2 && field[i1][j1].figure == Figure.NON && field[i1 - 1][j1].figure == Figure.NON ||
                                        ((j1 == selectedJ + 1 || j1 == selectedJ - 1) && i1 == selectedI + 1 && field[i1][j1].figure != Figure.NON &&
                                                field[i1][j1].color == Color.WHITE)) {
                                    try {
                                        doChanges(i1, j1, button, Figure.PONE, 0);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }

                            }

                            //TODO логика ладьи
                            else if (field[selectedI][selectedJ].figure == Figure.CASTLE) {
                                if (field[selectedI][selectedJ].color == Color.WHITE) {
                                    if (((j1 == selectedJ && i1 != selectedI && noObstacleForCastleI(i1, j1)) || (j1 != selectedJ && i1 == selectedI && noObstacleForCastleJ(i1, j1)))
                                            && field[i1][j1].color != Color.WHITE) {
                                        doChanges(i1, j1, button, Figure.CASTLE, 0);
                                        if (i1 == 0 && j1 == 7) {
                                            whiteLeftCastleMotion = true;
                                        } else if (i1 == 7 && j1 == 7) {
                                            whiteRightCastleMotion = true;
                                        }
                                    }
                                } else {
                                    if (((j1 == selectedJ && i1 != selectedI && noObstacleForCastleI(i1, j1)) || (j1 != selectedJ && i1 == selectedI && noObstacleForCastleJ(i1, j1)))
                                            && field[i1][j1].color != Color.BLACK) {
                                        doChanges(i1, j1, button, Figure.CASTLE, 0);
                                        if (i1 == 0 && j1 == 0) {
                                            blackLeftCastleMotion = true;
                                        } else if (i1 == 7 && j1 == 0) {
                                            blackRightCastleMotion = true;
                                        }
                                    }

                                }

                            }

                            //TODO логика коня
                            else if (field[selectedI][selectedJ].figure == Figure.KNIGHT) {
                                if (field[selectedI][selectedJ].color == Color.WHITE) {
                                    if ((j1 == selectedJ + 1 || j1 == selectedJ - 1) && (i1 == selectedI + 2 || i1 == selectedI - 2) ||
                                            (j1 == selectedJ + 2 || j1 == selectedJ - 2) && (i1 == selectedI + 1 || i1 == selectedI - 1) &&
                                                    field[i1][j1].color != Color.WHITE) {
                                        doChanges(i1, j1, button, Figure.KNIGHT, 0);
                                    }
                                } else if ((j1 == selectedJ + 1 || j1 == selectedJ - 1) && (i1 == selectedI + 2 || i1 == selectedI - 2) ||
                                        (j1 == selectedJ + 2 || j1 == selectedJ - 2) && (i1 == selectedI + 1 || i1 == selectedI - 1) &&
                                                field[i1][j1].color != Color.BLACK) {
                                    doChanges(i1, j1, button, Figure.KNIGHT, 0);
                                }
                            }

                            //TODO логика офицера
                            else if (field[selectedI][selectedJ].figure == Figure.BISHOP) {
                                if (field[selectedI][selectedJ].color == Color.WHITE) {
                                    if (Math.abs(selectedI - i1) == Math.abs(selectedJ - j1) && field[i1][j1].color != Color.WHITE && noObstacleForBishop(i1, j1)) {
                                        doChanges(i1, j1, button, Figure.BISHOP, 0);
                                    }
                                } else if (Math.abs(selectedI - i1) == Math.abs(selectedJ - j1) && field[i1][j1].color != Color.BLACK && noObstacleForBishop(i1, j1)) {
                                    doChanges(i1, j1, button, Figure.BISHOP, 0);

                                }
                            }
                            //TODO логика ферзя
                            else if (field[selectedI][selectedJ].figure == Figure.QUEEN) {
                                if (field[selectedI][selectedJ].color == Color.WHITE) {
                                    if (((j1 == selectedJ && i1 != selectedI && noObstacleForCastleI(i1, j1)) || (j1 != selectedJ && i1 == selectedI && noObstacleForCastleJ(i1, j1)))
                                            && field[i1][j1].color != Color.WHITE) {
                                        doChanges(i1, j1, button, Figure.QUEEN, 0);
                                    } else if (Math.abs(selectedI - i1) == Math.abs(selectedJ - j1) && field[i1][j1].color != Color.WHITE && noObstacleForBishop(i1, j1)) {
                                        doChanges(i1, j1, button, Figure.QUEEN, 0);
                                    }
                                } else if (((j1 == selectedJ && i1 != selectedI && noObstacleForCastleI(i1, j1)) || (j1 != selectedJ && i1 == selectedI && noObstacleForCastleJ(i1, j1)))
                                        && field[i1][j1].color != Color.BLACK) {
                                    doChanges(i1, j1, button, Figure.QUEEN, 0);
                                } else if (Math.abs(selectedI - i1) == Math.abs(selectedJ - j1) && field[i1][j1].color != Color.BLACK && noObstacleForBishop(i1, j1)) {
                                    doChanges(i1, j1, button, Figure.QUEEN, 0);

                                }
                            }

                            //TODO локига короля
                            else if (field[selectedI][selectedJ].figure == Figure.KING) {
                                if (field[selectedI][selectedJ].color == Color.WHITE) {
                                    if (!whiteKingMotion && i1 == selectedI && j1 == selectedJ + 2 && !whiteRightCastleMotion && field[i1][j1].figure == Figure.NON && selectedI == 7 && selectedJ == 4) {
                                        doChanges(i1, j1, button, Figure.NON, -1);
                                        whiteKingMotion = true;
                                        whiteRightCastleMotion = true;
                                    } else if (!whiteKingMotion && i1 == selectedI && j1 == selectedJ - 2 && !whiteLeftCastleMotion && field[i1][j1].figure == Figure.NON && selectedI == 7 && selectedJ == 4) {
                                        doChanges(i1, j1, button, Figure.NON, 2);
                                        whiteKingMotion = true;
                                        whiteLeftCastleMotion = true;
                                    } else if ((Math.abs(i1 - selectedI) == 1 && Math.abs(j1 - selectedJ) == 1 || Math.abs(i1 - selectedI) == 0 && Math.abs(j1 - selectedJ) == 1 || Math.abs(i1 - selectedI) == 1 && Math.abs(j1 - selectedJ) == 0)) {
                                        if (((j1 == selectedJ && i1 != selectedI && noObstacleForCastleI(i1, j1)) || (j1 != selectedJ && i1 == selectedI && noObstacleForCastleJ(i1, j1)))
                                                && field[i1][j1].color != Color.WHITE) {
                                            whiteKingMotion = true;
                                            doChanges(i1, j1, button, Figure.KING, 0);

                                        } else if (Math.abs(selectedI - i1) == Math.abs(selectedJ - j1) && field[i1][j1].color != Color.WHITE && noObstacleForBishop(i1, j1)) {
                                            whiteKingMotion = true;
                                            doChanges(i1, j1, button, Figure.KING, 0);

                                        }
                                    }
                                } else if (!blackKingMotion && i1 == selectedI && j1 == selectedJ + 2 && !blackRightCastleMotion && field[i1][j1].figure == Figure.NON && selectedI == 0 && selectedJ == 4) {
                                    doChanges(i1, j1, button, Figure.NON, -1);
                                    blackKingMotion = true;
                                    blackRightCastleMotion = true;
                                } else if (!blackKingMotion && i1 == selectedI && j1 == selectedJ - 2 && !blackLeftCastleMotion && field[i1][j1].figure == Figure.NON && selectedI == 0 && selectedJ == 4) {
                                    doChanges(i1, j1, button, Figure.NON, 2);
                                    blackKingMotion = true;
                                    blackLeftCastleMotion = true;
                                } else if ((Math.abs(i1 - selectedI) == 1 && Math.abs(j1 - selectedJ) == 1 || Math.abs(i1 - selectedI) == 0 && Math.abs(j1 - selectedJ) == 1 || Math.abs(i1 - selectedI) == 1 && Math.abs(j1 - selectedJ) == 0))
                                    if (((j1 == selectedJ && i1 != selectedI && noObstacleForCastleI(i1, j1)) || (j1 != selectedJ && i1 == selectedI && noObstacleForCastleJ(i1, j1)))
                                            && field[i1][j1].color != Color.BLACK) {
                                        blackKingMotion = true;
                                        doChanges(i1, j1, button, Figure.KING, 0);
                                    } else if (Math.abs(selectedI - i1) == Math.abs(selectedJ - j1) && field[i1][j1].color != Color.BLACK && noObstacleForBishop(i1, j1)) {
                                        blackKingMotion = true;
                                        doChanges(i1, j1, button, Figure.KING, 0);
                                    }
                            }
                            selected = null;
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                add(jb);
            }
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        if (!yourTurn()) {
            Integer a = null;
            try {
                a = is.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            while (a / 1000 < 1) {
//                a = is.read();
////                if (a == -1) {
////                    this.setVisible(false);
////                    this.dispose();
////                }
//            }
            System.out.println("begin black" + a);
            if (a == -1 || a == 111 || a == 100) {
//                setName("NO SERVER CONNECTION");
//                getDefaultCloseOperation();
                dispose();
            } else {
                String s = a.toString();
                int i1 = Character.getNumericValue(s.charAt(0));
                if (i1 == 9) {
                    i1 = 0;
                }
                int j1 = Character.getNumericValue(s.charAt(1));
                int selectedI1 = Character.getNumericValue(s.charAt(2));
                int selectedJ1 = Character.getNumericValue(s.charAt(3));
                field[i1][j1].figure = field[selectedI1][selectedJ1].figure;
                field[selectedI1][selectedJ1].figure = Figure.NON;
                field[i1][j1].color = field[selectedI1][selectedJ1].color;
                field[selectedI1][selectedJ1].color = Color.NON;
                setImageForButton(getButton(selectedI1, selectedJ1), selectedI1, selectedJ1);
                setImageForButton(getButton(i1, j1), i1, j1);
                turn ^= 1;
            }
        }
    }

    private boolean yourTurn() {
        return yourTurn == (int) turn;
    }

    private void generateField() {
        field = new FigureColor[SIZE][SIZE];
        for (int j = 0; j < SIZE; j++) {
            field[1][j] = new FigureColor(Figure.PONE, Color.BLACK);
            field[6][j] = new FigureColor(Figure.PONE, Color.WHITE);
        }
        field[0][0] = new FigureColor(Figure.CASTLE, Color.BLACK);
        field[0][7] = new FigureColor(Figure.CASTLE, Color.BLACK);
        field[7][0] = new FigureColor(Figure.CASTLE, Color.WHITE);
        field[7][7] = new FigureColor(Figure.CASTLE, Color.WHITE);
        field[0][1] = new FigureColor(Figure.KNIGHT, Color.BLACK);
        field[0][6] = new FigureColor(Figure.KNIGHT, Color.BLACK);
        field[7][1] = new FigureColor(Figure.KNIGHT, Color.WHITE);
        field[7][6] = new FigureColor(Figure.KNIGHT, Color.WHITE);
        field[0][2] = new FigureColor(Figure.BISHOP, Color.BLACK);
        field[0][5] = new FigureColor(Figure.BISHOP, Color.BLACK);
        field[7][2] = new FigureColor(Figure.BISHOP, Color.WHITE);
        field[7][5] = new FigureColor(Figure.BISHOP, Color.WHITE);
        field[0][3] = new FigureColor(Figure.QUEEN, Color.BLACK);
        field[7][3] = new FigureColor(Figure.QUEEN, Color.WHITE);
        field[0][4] = new FigureColor(Figure.KING, Color.BLACK);
        field[7][4] = new FigureColor(Figure.KING, Color.WHITE);
        for (int i = 2; i < SIZE - 2; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = new FigureColor(Figure.NON, Color.NON);
            }
        }
    }

    private void setImageForButton(JButton jb, int i, int j) {
        String figure = "/icons/";
        if (field[i][j].figure == Figure.PONE) {
            figure += "Pone";
        } else if (field[i][j].figure == Figure.KNIGHT) {
            figure += "Knight";
        } else if (field[i][j].figure == Figure.CASTLE) {
            figure += "Castle";
        } else if (field[i][j].figure == Figure.BISHOP) {
            figure += "Bishop";
        } else if (field[i][j].figure == Figure.QUEEN) {
            figure += "Queen";
        } else if (field[i][j].figure == Figure.KING) {
            figure += "King";
        }
        if (field[i][j].color == Color.WHITE) {
            figure += "-white.png";
        } else {
            figure += "-black.png";
        }
        if (figure.length() >= 20) {
            jb.setIcon(new ImageIcon(this.getClass().getResource(figure)));
        } else {
            jb.setIcon(null);
        }
    }

    private String sendAndGet(Integer s) throws IOException {
//        System.out.println(s.);
        System.out.println("Int value " + s.intValue());
        pw.write(s.intValue());
        pw.flush();
        turn ^= 1;
        System.out.println(yourTurn + " waiting opponet");
        Integer res = is.read();
        System.out.println(res);
        if (res == 111) {
            dispose();
            getDefaultCloseOperation();
        }
        if (res < 100) {
            res = is.read();
            System.out.println("[smth  ] :" + res);
        }
        if (res == 8888 || res == 111 || res == -1 || res == 10 || res == 100) {
            getDefaultCloseOperation();
            System.out.println("I am here");
            dispose();
        }
        while (res.toString().length() < 4) {
            res = is.read();
        }
        System.out.println("[****]res" + res);
        if (res == -1) {
            setName("NO SERVER CONNECTION");
            getDefaultCloseOperation();
            return "end";
        }
        return res.toString();
    }

    private synchronized void doChanges(int i, int j, JButton button, Figure figure, int side) throws IOException {
        Integer s1;
        if (side == 0) {
            s1 = doStep(i, j, button, figure);
        } else {
            s1 = doRockerovka(i, j, button, side);
        }
        System.out.println("here" + s1);
//        System.out.println(yourTurn + " waiting opponet");
        String s = sendAndGet(s1);
        if (s.equals("end")) {
            this.setVisible(false);
            this.dispose();
//            this.exit(0);
//            this.setDefaultCloseOperation(0);
        }
        System.out.println("i've got " + s);
        if (s.length() < 5) {
            int i1 = Character.getNumericValue(s.charAt(0));
            if (i1 == 9) {
                i1 = 0;
            }
            int j1 = Character.getNumericValue(s.charAt(1));
            selectedI = Character.getNumericValue(s.charAt(2));
            selectedJ = Character.getNumericValue(s.charAt(3));
            selected = getButton(selectedI, selectedJ);
            doStep(i1, j1, getButton(i1, j1), field[selectedI][selectedJ].figure);
        } else {
            int i1 = Character.getNumericValue(s.charAt(1));
            if (i1 == 9) {
                i1 = 0;
            }
            int j1 = Character.getNumericValue(s.charAt(2));
            selectedI = Character.getNumericValue(s.charAt(3));
            selectedJ = Character.getNumericValue(s.charAt(4));
            selected = getButton(selectedI, selectedJ);
            int sideG = Character.getNumericValue(s.charAt(0));
            if (sideG == 1) {
                sideG *= -1;
            }
            System.out.println("получил и делаю рокеровочку " + j1 + "  " + sideG);
            doRockerovka(i1, j1, getButton(i1, j1), sideG);
        }
        turn ^= 1;

    }

    private JButton getButton(int i, int j) {
        return (JButton) getContentPane().getComponent((i) * 8 + j);
    }

//    Boolean noObstacleForCastlePar(int par, int z, int parK) {
//        Boolean res = true;
//        if (par < parK) {
//            for (int k = par + 1; k < parK; k++) {
//                if (field[k][z].figure != Figure.NON) {
//                    res = false;
//                }
//            }
//        } else {
//            for (int k = parK + 1; k < par; k++) {
//                if (field[k][z].figure != Figure.NON) {
//                    res = false;
//                }
//            }
//        }
//        return res;
//    }

    private Boolean noObstacleForCastleI(int i, int j) {
        Boolean res = true;
        if (i < selectedI) {
            for (int k = i + 1; k < selectedI; k++) {
                if (field[k][j].figure != Figure.NON) {
                    res = false;
                }
            }
        } else {
            for (int k = selectedI + 1; k < i; k++) {
                if (field[k][j].figure != Figure.NON) {
                    res = false;
                }
            }
        }
        return res;
//        return noObstacleForCastlePar(i,j,selectedI);
    }

    private Boolean noObstacleForCastleJ(int i, int j) {
        Boolean res = true;
        if (j < selectedJ) {
            for (int k = j + 1; k < selectedJ; k++) {
                if (field[i][k].figure != Figure.NON) {
                    res = false;
                }
            }
        } else {
            for (int k = selectedJ + 1; k < j; k++) {
                if (field[i][k].figure != Figure.NON) {
                    res = false;
                }
            }
        }
        return res;
//        return noObstacleForCastlePar(j,i,selectedJ);
    }

    private Boolean noObstacleForBishop(int i, int j) {
        Boolean res = true;
        if (i - selectedI == j - selectedJ) {
            if (i > selectedI) {
                for (int k = 1; k < Math.abs(selectedI - i); k++) {
                    if (field[(i - k)][(j - k)].figure != Figure.NON) {
                        res = false;
                    }
                }
            } else {
                for (int k = 1; k < Math.abs(i - selectedI); k++) {
                    if (field[(i + k)][(j + k)].figure != Figure.NON) {
                        res = false;
                    }
                }
            }
        } else {
            if (i > selectedI) {
                for (int k = 1; k < Math.abs(selectedI - i); k++) {
                    if (field[(i - k)][(j + k)].figure != Figure.NON) {
                        res = false;
                    }
                }
            } else {
                for (int k = 1; k < Math.abs(i - selectedI); k++) {
                    if (field[(i + k)][(j - k)].figure != Figure.NON) {
                        res = false;
                    }
                }
            }
        }
        return res;
    }

    private synchronized Integer doRockerovka(int i, int j, JButton button, int side) {
        System.out.println(i + " " + j + " " + side);
        field[selectedI][selectedJ].figure = Figure.NON;
        field[i][j].color = field[selectedI][selectedJ].color;
        field[selectedI][selectedJ].color = Color.NON;
        field[i][j].figure = Figure.KING;
        setImageForButton(selected, selectedI, selectedJ);
        setImageForButton(button, i, j);
        field[i][j - side].figure = Figure.NON;
        field[i][j + (int) Math.signum(side)].color = field[i][j - side].color;
        field[i][j + (int) Math.signum(side)].figure = Figure.CASTLE;
        field[i][j - side].color = Color.NON;
        setImageForButton(((JButton) getContentPane().getComponent((i) * 8 + (j + (int) Math.signum(side)))), i, j + (int) Math.signum(side));
        setImageForButton(((JButton) getContentPane().getComponent((i) * 8 + j - side)), i, j - side);
        Integer s1 = Math.abs(side) * 10000 + (i * 1000 + j * 100 + selectedI * 10 + selectedJ);
        System.out.println(s1);
        return s1;
    }

    private synchronized Integer doStep(int i, int j, JButton button, Figure figure) {
        field[i][j].figure = figure;
        field[selectedI][selectedJ].figure = Figure.NON;
        field[i][j].color = field[selectedI][selectedJ].color;
        field[selectedI][selectedJ].color = Color.NON;
        setImageForButton(selected, selectedI, selectedJ);
        setImageForButton(button, i, j);
        if (i == 0) {
            i = 9;
        }
        return i * 1000 + j * 100 + selectedI * 10 + selectedJ;
    }

}
