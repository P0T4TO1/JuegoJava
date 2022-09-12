/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Jaret
 */
public enum PiezaTipo {
    PEON(0) {
        @Override
        Pieza create(PiezaColor color) {
            return new Peon(color, this);
        }
    }, TORRE(1) {
        @Override
        Pieza create(PiezaColor color) {
            return new Torre(color, this);
        }
    }, CABALLO(2) {
        @Override
        Pieza create(PiezaColor color) {
            return new Caballo(color, this);
        }
    }, ALFIL(3) {
        @Override
        Pieza create(PiezaColor color) {
            return new Alfil(color, this);
        }
    }, REINA(4) {
        @Override
        Pieza create(PiezaColor color) {
            return new Reina(color, this);
        }
    }, REY(5) {
        @Override
        Pieza create(PiezaColor color) {
            return new Rey(color, this);
        }
    };
    private final int tipo;

    PiezaTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        String str = "";
        switch (tipo) {
            case 0:
                str = "Peon";
                break;
            case 1:
                str = "Torre";
                break;
            case 2:
                str = "Caballo";
                break;
            case 3:
                str = "Alfil";
                break;
            case 4:
                str = "Reina";
                break;
            case 5:
                str = "Rey";
                break;
        }
        return str;
    }

    public int getTypeNumber() {
        return tipo;
    }

    abstract Pieza create(PiezaColor color);
    
}
