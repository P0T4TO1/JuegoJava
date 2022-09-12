/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.awt.Color;
import java.util.Collection;
import javax.swing.ImageIcon;

/**
 *
 * @author Jaret
 */
public abstract class Pieza {
    private final PiezaTipo piezaTipo;
    private final PiezaColor piezaColor;
    private Cuadrado cuadrado;
    private boolean moved;

    public Pieza(PiezaColor color, PiezaTipo tipo) {
        piezaColor = color;
        piezaTipo = tipo;
        moved = false;
    }
    
    public PiezaColor getPiezaColor() {
        return piezaColor;
    }
    
    public void setMoved() {
        moved = true;
    }

    public boolean hasMoved() {
        return moved;
    }

    public int getTypeNumber() {
        return piezaTipo.getTypeNumber();
    }

    public String getColorString() {
        return piezaColor.toString();
    }

    public String getType() {
        return piezaTipo.toString();
    }

    public java.awt.Color getColor() {
        return piezaColor.getColor();
    }

    public Cuadrado getCuadrado() {
        return cuadrado;
    }

    public boolean isWhite() {
        return piezaColor.isWhite();
    }
    
    public Pieza getOpponentKing() {
        return getCuadrado().getTablero().getOpponentKing(this);
    }

    public boolean isOpponent(Pieza pieza) {
        return pieza != null && isWhite() != pieza.isWhite();
    }

    public boolean legalMove(Cuadrado to) {
        return to.getPieza() == null || isOpponent(to.getPieza());
    }

    public abstract Collection<Cuadrado> getMovimientosPosibles();

    public abstract Collection<Cuadrado> generaraMoviemientosPosibles();

    public ImageIcon getIcon() {
        String path = "/images/" + getColorString() + " " + getType().substring(0, 1) + ".png";
        return new ImageIcon(getClass().getResource(path));

    }

    public void putPieceOnSquareFirstTime(Cuadrado cuadrado) {
        this.cuadrado = cuadrado;
        this.cuadrado.setPieza(this);
    }

    public void setSquare(Cuadrado cuadrado) {
        this.cuadrado = cuadrado;
    }

    @Override
    public String toString() {
        return piezaColor.toString().substring(0, 1) + " " + piezaTipo.toString().substring(0, 1);
    }

    public boolean isKing() {
        return this instanceof Rey;
    }

    public void printMoviemientosPosibles() {
        generaraMoviemientosPosibles();
        getMovimientosPosibles().stream().forEach((_item) -> {
            if (isOpponent(_item.getPieza())) {
                _item.setBackground(Color.RED);
            } else {
                _item.setBackground(Color.GREEN);
            }
        });
    }
}
