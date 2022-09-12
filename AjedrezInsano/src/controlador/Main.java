/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controlador;

import javax.swing.JFrame;
import modelos.Tablero;

/**
 *
 * @author Jaret
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        JFrame frame = new JFrame("Ajedrez Insano");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(tablero);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
}
