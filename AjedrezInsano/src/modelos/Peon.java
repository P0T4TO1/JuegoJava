/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Jaret
 */
class Peon extends Pieza{

    Collection<Cuadrado> movimientosPosibles;

    public Peon(PiezaColor color, PiezaTipo tipo) {
        super(color, tipo);
        movimientosPosibles = new ArrayList<>();
    }

    @Override
    public Collection<Cuadrado> generaraMoviemientosPosibles() {
        movimientosPosibles.clear();
        boolean color = super.isWhite();
        int dx = color ? -1 : 1;

        Cuadrado ahead = super.getCuadrado().vecino(dx, 0);
        if (ahead.getPieza() == null) {
            movimientosPosibles.add(ahead);
            if (super.getCuadrado().Fila == 6 && color) {
                Cuadrado aheadsecond = super.getCuadrado().vecino(dx - 1, 0);
                if (aheadsecond.getPieza() == null) {
                    movimientosPosibles.add(aheadsecond);
                }
            } else if (super.getCuadrado().Fila == 1 && !color) {
                Cuadrado aheadsecond = super.getCuadrado().vecino(dx + 1, 0);
                if (aheadsecond.getPieza() == null) {
                    movimientosPosibles.add(aheadsecond);
                }
            }
        }
        Cuadrado aheadLeft = super.getCuadrado().vecino(dx, -1);
        if (aheadLeft != null && aheadLeft.getPieza() != null && isOpponent(aheadLeft.getPieza())) {
            movimientosPosibles.add(aheadLeft);
        }
        Cuadrado aheadRight = super.getCuadrado().vecino(dx, 1);
        if (aheadRight != null && aheadRight.getPieza() != null && isOpponent(aheadRight.getPieza())) {
            movimientosPosibles.add(aheadRight);
        }
        return movimientosPosibles;
    }

    @Override
    public Collection<Cuadrado> getMovimientosPosibles() {
        return movimientosPosibles;
    }
    
}
