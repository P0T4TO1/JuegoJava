/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Jaret
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Cuadrado extends JButton{
    public final int Fila;
    public final int Columna;
    private Pieza pieza;
    private final Tablero tablero;
    private boolean seleccionado;
    
    
    public Cuadrado(int fila, int columna, Tablero tablero) {
        this.tablero = tablero;
        Fila = fila;
        Columna = columna;
        seleccionado = false;
        setFocusPainted(false);
        setBackground(getColor());
        setPreferredSize(new Dimension(80, 80));
        addActionListener((ActionEvent e) -> {
            select();
        });
    }
    
    public Color getColor() {
        return (Fila + Columna) % 2 == 0 ? Color.BLACK : Color.WHITE;
    }

    private void select() {
        if (pieza != null) {
            if ((tablero.getTurn() && !pieza.isWhite()) || (!tablero.getTurn() && pieza.isWhite()) && !seleccionado) {
                if (pieza.getTypeNumber() == 5 || !tablero.kingInCheck()) {
                    if (tablero.getSelected() != null && !tablero.getSelected().isEmpty()) {
                        tablero.getSelected().getPieza().getMovimientosPosibles().stream().forEach((_item) -> {
                            _item.deselect();
                        });
                    }
                    tablero.deselect();
                    tablero.setSelected(this);
                    seleccionado = true;
                    setBackground(Color.YELLOW);
                    pieza.printMoviemientosPosibles();
                } else {
                    if (tablero.getReminder() > 1) {
                        JOptionPane.showMessageDialog(this, "El rey esta en hacke");
                        tablero.resetReminder();
                    }
                }
            } else if ((tablero.getTurn() && pieza.isWhite()) || (!tablero.getTurn() && !pieza.isWhite())) {
                if (tablero.getSelected() != null && !tablero.getSelected().isEmpty()) {
                    if (tablero.getSelected().getPieza().getMovimientosPosibles().contains(this)) {
                        //Capture move
                        Cuadrado from = tablero.getSelected();
                        Cuadrado to = this;
                        if (from.getPieza() != null) {
                            from.getPieza().getMovimientosPosibles().stream().forEach((square) -> {
                                square.deselect();
                            });
                        }
                        tablero.move(from, to);
                        from.deselect();
                        to.deselect();
                    }
                }
            }
        } else {
            if (tablero.getSelected() != null && !tablero.getSelected().isEmpty()) {
                if (tablero.getSelected().getPieza().getMovimientosPosibles().contains(this)) {
                    //Natural move
                    Cuadrado from = tablero.getSelected();
                    Cuadrado to = this;
                    from.getPieza().getMovimientosPosibles().stream().forEach((square) -> {
                        square.deselect();
                    });
                    tablero.move(from, to);
                    tablero.deselect();
                    tablero.setSelected(null);
                }
            }
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Cuadrado vecino(int fila, int columna) {
        return tablero.getCuadrado(Fila + fila, Columna + columna);
    }

    public Cuadrado getCuadroDelTablero(int fila, int columna) {
        return tablero.getCuadrado(fila, columna);
    }

    public void deselect() {
        setBackground(getColor());
        seleccionado = false;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
        setIcon(this.pieza.getIcon());
    }

    public void moverPieza(Pieza pieza) {
        this.pieza = pieza;
        this.pieza.setMoved();
        setIcon(this.pieza.getIcon());
        this.pieza.setSquare(this);
        this.pieza.generaraMoviemientosPosibles();
    }

    public void removePieza() {
        pieza = null;
        setIcon(null);
    }

    public Pieza getPieza() {
        return pieza;
    }

    public boolean isEmpty() {
        return pieza == null;
    }

    @Override
    public boolean isSelected() {
        return seleccionado;
    }

    @Override
    public String toString() {
        return "Fila: " + Tablero.printRow(Fila) + " Columna: " + Tablero.printColumn(Columna) + " - (" + Fila + "," + Columna + ")";
    }
}
