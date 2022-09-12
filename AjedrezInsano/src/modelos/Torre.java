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
class Torre extends Pieza{
    
    Collection<Cuadrado> movimientosPosibles;

    public Torre(PiezaColor color, PiezaTipo tipo) {
        super(color, tipo);
        movimientosPosibles = new ArrayList<>();
    }

    @Override
    public Collection<Cuadrado> generaraMoviemientosPosibles() {
        int fila = super.getCuadrado().Fila;
        int columna = super.getCuadrado().Columna;
        movimientosPosibles.clear();
        //all possible moves in the up
        for (int i = fila + 1; i < Tablero.TAMAÑO; i++) {
            Cuadrado cuadrado = super.getCuadrado().getCuadroDelTablero(i, columna);
            if (cuadrado.getPieza() == null) {
                movimientosPosibles.add(cuadrado);
            } else if (isOpponent(cuadrado.getPieza())) {
                movimientosPosibles.add(cuadrado);
                break;
            } else {
                break;
            }
        }
        //all possible moves in the down
        for (int i = fila - 1; i > -1; i--) {
            Cuadrado cuadrado = super.getCuadrado().getCuadroDelTablero(i, columna);
            if (cuadrado.getPieza() == null) {
                movimientosPosibles.add(cuadrado);
            } else if (isOpponent(cuadrado.getPieza())) {
                movimientosPosibles.add(cuadrado);
                break;
            } else {
                break;
            }
        }
        //all possible moves to the right
        for (int i = columna + 1; i < Tablero.TAMAÑO; i++) {
            Cuadrado cuadrado = super.getCuadrado().getCuadroDelTablero(fila, i);
            if (cuadrado.getPieza() == null) {
                movimientosPosibles.add(cuadrado);
            } else if (isOpponent(cuadrado.getPieza())) {
                movimientosPosibles.add(cuadrado);
                break;
            } else {
                break;
            }
        }
        //all possible moves to the left
        for (int i = columna - 1; i > -1; i--) {
            Cuadrado cuadrado = super.getCuadrado().getCuadroDelTablero(fila, i);
            if (cuadrado.getPieza() == null) {
                movimientosPosibles.add(cuadrado);
            } else if (isOpponent(cuadrado.getPieza())) {
                movimientosPosibles.add(cuadrado);
                break;
            } else {
                break;
            }
        }
        return movimientosPosibles;
    }

    @Override
    public Collection<Cuadrado> getMovimientosPosibles() {
        return movimientosPosibles;
    }
    
}
