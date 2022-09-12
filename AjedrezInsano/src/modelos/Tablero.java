/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jaret
 */
public final class Tablero extends JPanel {

    private ArrayList<Pieza> piezas;
    private ArrayList<Pieza> whitePieces;
    private ArrayList<Pieza> blackPieces;
    private Cuadrado[][] tablero;
    private Cuadrado selectedSquare;
    private JPanel boardPanel;
    private boolean turn;
    private boolean check;
    private int reminder;
    public static final int TAMAÑO = 8;
    private static final int GAP = 5;
    private Rey whiteKingPiece;
    private Rey blackKingPiece;

    public Tablero() {
        create();
    }

    private JPanel createFilePanel() {
        JPanel filePanel = new JPanel(new GridLayout(1, 0));
        for (int i = 0; i < TAMAÑO; i++) {
            char fileChar = (char) ('A' + i);
            filePanel.add(new JLabel(String.valueOf(fileChar), SwingConstants.CENTER));
        }
        return filePanel;
    }

    public static String printRow(int fila) {
        return String.valueOf(TAMAÑO - fila);
    }

    public static String printColumn(int columna) {
        return String.valueOf((char) ('A' + columna));
    }

    private JPanel createRankPanel() {
        JPanel rankPanel = new JPanel(new GridLayout(0, 1));
        for (int i = 0; i < TAMAÑO; i++) {
            int fila = TAMAÑO - i;
            rankPanel.add(new JLabel(String.valueOf(fila)));
        }
        return rankPanel;
    }

    public void create() {
        removeAll();
        setLayout(new GridBagLayout());
        boardPanel = new JPanel(new GridLayout(TAMAÑO, TAMAÑO));
        piezas = new ArrayList<>();
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        turn = false;
        check = false;
        reminder = 0;
        tablero = new Cuadrado[TAMAÑO][TAMAÑO];
        for (int row = 0; row < TAMAÑO; row++) {
            for (int column = 0; column < TAMAÑO; column++) {
                tablero[row][column] = new Cuadrado(row, column, this);
                boardPanel.add(tablero[row][column]);
            }
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 2 * GAP, 0, 2 * GAP);
        add(createRankPanel(), gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(createRankPanel(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(GAP, 0, GAP, 0);
        add(createFilePanel(), gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        add(createFilePanel(), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(GAP, 0, GAP, GAP);
        JButton newGame = new JButton("Juego nuevo");
        newGame.addActionListener((ActionEvent e) -> {
            create();
        });

        add(newGame, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(boardPanel, gbc);
        add(boardPanel,gbc);
        createStandardPieceSet();
    }

    public Pieza getOpponentKing(Pieza pieza) {
        Pieza returnPieza = pieza.isOpponent(blackKingPiece) ? blackKingPiece : whiteKingPiece;
        return returnPieza;
    }

    public void createStandardPieceSet() {
        PiezaColor whitePieceColor = PiezaColor.WHITE;
        Pieza[] whitePiecesFirstRow = new Pieza[]{
            PiezaTipo.TORRE.create(whitePieceColor),
            PiezaTipo.CABALLO.create(whitePieceColor),
            PiezaTipo.ALFIL.create(whitePieceColor),
            PiezaTipo.REINA.create(whitePieceColor),
            PiezaTipo.REY.create(whitePieceColor),
            PiezaTipo.ALFIL.create(whitePieceColor),
            PiezaTipo.CABALLO.create(whitePieceColor),
            PiezaTipo.TORRE.create(whitePieceColor)};
        Pieza[] whitePiecesSecondRow = new Pieza[TAMAÑO];
        for (int i = 0; i < whitePiecesSecondRow.length; i++) {
            whitePiecesSecondRow[i] = PiezaTipo.PEON.create(whitePieceColor);
        }
        int count = 0;
        for (Pieza pieza : whitePiecesFirstRow) {
            if (pieza != null) {
                pieza.putPieceOnSquareFirstTime(tablero[7][count]);
                piezas.add(pieza);
                whitePieces.add(pieza);
                if (pieza.isKing()) {
                    whiteKingPiece = (Rey) pieza;
                }
                count++;
            }
        }
        count = 0;
        for (Pieza pieza : whitePiecesSecondRow) {
            if (pieza != null) {
                pieza.putPieceOnSquareFirstTime(tablero[6][count]);
                piezas.add(pieza);
                whitePieces.add(pieza);
                count++;
            }
        }
        PiezaColor blackPieceColor = PiezaColor.BLACK;
        Pieza[] blackPiecesFirstRow = new Pieza[]{
            PiezaTipo.TORRE.create(blackPieceColor),
            PiezaTipo.CABALLO.create(blackPieceColor),
            PiezaTipo.ALFIL.create(blackPieceColor),
            PiezaTipo.REINA.create(blackPieceColor),
            PiezaTipo.REY.create(blackPieceColor),
            PiezaTipo.ALFIL.create(blackPieceColor),
            PiezaTipo.CABALLO.create(blackPieceColor),
            PiezaTipo.TORRE.create(blackPieceColor)};
        Pieza[] blackPiecesSecondRow = new Pieza[TAMAÑO];
        for (int i = 0; i < blackPiecesSecondRow.length; i++) {
            blackPiecesSecondRow[i] = PiezaTipo.PEON.create(blackPieceColor);
        }
        count = 0;
        for (Pieza pieza : blackPiecesFirstRow) {
            if (pieza != null) {
                pieza.putPieceOnSquareFirstTime(tablero[0][count]);
                piezas.add(pieza);
                blackPieces.add(pieza);
                if (pieza.isKing()) {
                    blackKingPiece = (Rey) pieza;
                }
                count++;
            }
        }
        count = 0;
        for (Pieza pieza : blackPiecesSecondRow) {
            if (pieza != null) {
                pieza.putPieceOnSquareFirstTime(tablero[1][count]);
                piezas.add(pieza);
                blackPieces.add(pieza);
                count++;
            }
        }
    }

    public void resetReminder() {
        reminder = 0;
    }

    public int getReminder() {
        return reminder++;
    }

    public boolean getTurn() {
        return turn;
    }

    public Cuadrado[][] getTablero() {
        return tablero;
    }

    public void move(Cuadrado from, Cuadrado to) {
        to.moverPieza(from.getPieza());
        from.removePieza();
        turn = !turn;
    }

    public Cuadrado getCuadrado(int row, int column) {
        Cuadrado cuadrado = null;
        if (row < TAMAÑO && column < TAMAÑO && row >= 0 && column >= 0) {
            cuadrado = tablero[row][column];
        }
        return cuadrado;
    }

    public boolean kingInCheck() {
        if (turn) {
            return blackKingPiece.inCheck;
        } else {
            return whiteKingPiece.inCheck;
        }
    }

    public Cuadrado getSelected() {
        return selectedSquare;
    }

    public void setSelected(Cuadrado cuadrado) {
        selectedSquare = cuadrado;
    }

    public void deselect() {
        if (selectedSquare != null) {
            selectedSquare.deselect();
            for (Cuadrado[] squares : tablero) {
                for (Cuadrado square : squares) {
                    square.setBackground(square.getColor());
                }
            }
        }
    }

    public ArrayList<Pieza> getWhitePieces() {
        return whitePieces;
    }

    public ArrayList<Pieza> getBlackPieces() {
        return blackPieces;
    }
    
}
