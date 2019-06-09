/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast;

/**
 *
 * @author oscar
 */
public class NodoAst {

    private final int linea;
    private final int columna;

    public NodoAst(int linea, int columna) {
        this.linea = linea;
        this.columna = columna;
    }

    /**
     * @return the linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

}
