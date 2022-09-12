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
class Alfil extends Pieza {
    Collection<Cuadrado> movimientosPosibles;

    public Alfil(PiezaColor color, PiezaTipo tipo) {
        super(color, tipo);
        movimientosPosibles = new ArrayList<>();
    }
    
    @Override
    public Collection<Cuadrado> generaraMoviemientosPosibles() {
        int fila = super.getCuadrado().Fila;
        int columna = super.getCuadrado().Columna;
        movimientosPosibles.clear();
        //todos los movimientos posibles en la diagonal positiva hacia abajo
        for (int j = columna + 1, i = fila + 1; j < Tablero.TAMAÑO && i < Tablero.TAMAÑO; j++, i++) {
            Cuadrado cuadrado = super.getCuadrado().getCuadroDelTablero(i, j);
            if (cuadrado.getPieza() == null) {
                movimientosPosibles.add(cuadrado);
            } else if (isOpponent(cuadrado.getPieza())) {
                movimientosPosibles.add(cuadrado);
                break;
            } else {
                break;
            }
        }
        //todos los movimientos posibles en la diagonal positiva hacia arriba
        for (int j = columna - 1, i = fila + 1; j > -1 && i < Tablero.TAMAÑO; j--, i++) {
            Cuadrado cuadrado = super.getCuadrado().getCuadroDelTablero(i, j);
            if (cuadrado.getPieza() == null) {
                movimientosPosibles.add(cuadrado);
            } else if (isOpponent(cuadrado.getPieza())) {
                movimientosPosibles.add(cuadrado);
                break;
            } else {
                break;
            }
        }
        //todos los movimientos posibles en la diagonal negativa hacia arriba
        for (int j = columna - 1, i = fila - 1; j > -1 && i > -1; j--, i--) {
            Cuadrado cuadrado = super.getCuadrado().getCuadroDelTablero(i, j);
            if (cuadrado.getPieza() == null) {
                movimientosPosibles.add(cuadrado);
            } else if (isOpponent(cuadrado.getPieza())) {
                movimientosPosibles.add(cuadrado);
                break;
            } else {
                break;
            }
        }
        //todos los movimientos posibles en la diagonal negativa hacia abajo
        for (int j = columna + 1, i = fila - 1; j < Tablero.TAMAÑO && i > -1; j++, i--) {
            Cuadrado cuadrado = super.getCuadrado().getCuadroDelTablero(i, j);
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
