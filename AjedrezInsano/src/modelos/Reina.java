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
class Reina extends Pieza {
    
    Collection<Cuadrado> movimientosPosibles;

    public Reina(PiezaColor color, PiezaTipo tipo) {
        super(color, tipo);
        movimientosPosibles = new ArrayList<>();
    }
    
    @Override
    public ImageIcon getIcon() {
        String path = "/images/" + getColorString() + " E.png";
        return new ImageIcon(getClass().getResource(path));
    }
    
    @Override
    public String toString() {
        return super.getColorString().substring(0, 1) + " E";
    }

    @Override
    public Collection<Cuadrado> generaraMoviemientosPosibles() {
        movimientosPosibles.clear();
        Pieza[] piezas = {
            PiezaTipo.TORRE.create(getPiezaColor()),
            PiezaTipo.ALFIL.create(getPiezaColor())
        };
        for (Pieza pieza : piezas) {
            pieza.setSquare(getCuadrado());
            movimientosPosibles.addAll(pieza.generaraMoviemientosPosibles());
        }
        return movimientosPosibles;
    }

    @Override
    public Collection<Cuadrado> getMovimientosPosibles() {
        return movimientosPosibles;
    }
}
