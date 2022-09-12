/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.awt.Color;

/**
 *
 * @author Jaret
 */
public enum PiezaColor {
    BLACK(Color.BLACK), WHITE(Color.WHITE);

    private final Color color;

    PiezaColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isWhite() {
        return name().charAt(0) != (char) 66;
    }

    @Override
    public String toString() {
        return name().charAt(0) == (char) 66 ? "Black" : "White";
    }
}
