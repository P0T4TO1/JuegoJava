/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Jaret
 */
class Rey extends Pieza {
    
    Collection<Cuadrado> movimientosPosibles;
    boolean inCheck;

    public Rey(PiezaColor color, PiezaTipo tipo) {
        super(color, tipo);
        movimientosPosibles = new ArrayList<>();
        inCheck = false;
    }

    @Override
    public Collection<Cuadrado> generaraMoviemientosPosibles() {
        movimientosPosibles.clear();
        List<Cuadrado> moves = new ArrayList<>();
        int[][] offsets = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 1},
            {-1, 1},
            {-1, -1},
            {1, -1}
        };
        for (int[] o : offsets) {
            Cuadrado cuadrado = super.getCuadrado().vecino(o[0], o[1]);
            if (cuadrado != null && (cuadrado.getPieza() == null || isOpponent(cuadrado.getPieza()))) {
                moves.add(cuadrado);
            }
        }
        movimientosPosibles.addAll(moves);
        if (getCuadrado().isSelected()) {
            inCheck = false;
            Pieza[] piezas = {
                PiezaTipo.PEON.create(getPiezaColor()),
                PiezaTipo.TORRE.create(getPiezaColor()),
                PiezaTipo.ALFIL.create(getPiezaColor()),
                PiezaTipo.CABALLO.create(getPiezaColor()),
                PiezaTipo.REINA.create(getPiezaColor()),
                PiezaTipo.REY.create(getPiezaColor())};
            Pieza oldKing = this;
            getCuadrado().removePieza();
            moves.stream().forEach((kingMove) -> {
                if (kingMove.isEmpty()) {
                    for (Pieza pieza : piezas) {
                        pieza.putPieceOnSquareFirstTime(kingMove);
                        pieza.generaraMoviemientosPosibles();
                        pieza.getMovimientosPosibles().stream().filter((enemy)
                                -> (movimientosPosibles.contains(kingMove) && !enemy.isEmpty()
                                && enemy.getPieza().isOpponent(pieza)
                                && enemy.getPieza().getTypeNumber() == pieza.getTypeNumber())).forEach((_item) -> {
                                    movimientosPosibles.remove(kingMove);
                                    if (!inCheck) {
                                        inCheck = _item.getPieza().getMovimientosPosibles().contains(getCuadrado());
                                    }
                                });
                    }
                    kingMove.removePieza();
                } else if (isOpponent(kingMove.getPieza())) {
                    Pieza oldPiece = kingMove.getPieza();
                    for (Pieza piece : piezas) {
                        kingMove.removePieza();
                        piece.putPieceOnSquareFirstTime(kingMove);
                        piece.generaraMoviemientosPosibles();
                        piece.getMovimientosPosibles().stream().filter((enemy)
                                -> (movimientosPosibles.contains(kingMove) && !enemy.isEmpty()
                                && enemy.getPieza().isOpponent(piece)
                                && enemy.getPieza().getTypeNumber() == piece.getTypeNumber())).forEach((_item) -> {
                                    movimientosPosibles.remove(kingMove);
                                    if (!inCheck) {
                                        inCheck = _item.getPieza().generaraMoviemientosPosibles().contains(getCuadrado());
                                    }
                                });
                    }
                    kingMove.removePieza();
                    oldPiece.putPieceOnSquareFirstTime(kingMove);
                }
            });
            oldKing.putPieceOnSquareFirstTime(getCuadrado());
        }
        return movimientosPosibles;
    }

    @Override
    public Collection<Cuadrado> getMovimientosPosibles() {
        return movimientosPosibles;
    }
}
