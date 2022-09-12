/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ImageIcon;

/**
 *
 * @author Jaret
 */
class Caballo extends Pieza {
    
    Collection<Cuadrado> movimientosPosibles;

    public Caballo(PiezaColor color, PiezaTipo tipo) {
        super(color, tipo);
        movimientosPosibles = new ArrayList<>();
    }

    @Override
    public Collection<Cuadrado> generaraMoviemientosPosibles() {
        movimientosPosibles.clear();
        int[][] offsets = {
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1}
        };
        for (int[] o : offsets) {
            Cuadrado cuadrado = super.getCuadrado().vecino(o[0], o[1]);
            if (cuadrado != null && (cuadrado.getPieza() == null || isOpponent(cuadrado.getPieza()))) {
                movimientosPosibles.add(cuadrado);
            }
        }
        return movimientosPosibles;
    }

    @Override
    public Collection<Cuadrado> getMovimientosPosibles() {
        return movimientosPosibles;
    }
}
