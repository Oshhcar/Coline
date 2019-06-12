/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Modificador;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Declaracion extends Instruccion {

    private ArrayList<Modificador> modificadores;

    public Declaracion(int linea, int columna) {
        super(linea, columna);
        this.modificadores = null;
    }
    
    @Override
    public Object ejecutar(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        return null;
    }

    /**
     * @return the modificadores
     */
    public ArrayList<Modificador> getModificadores() {
        return modificadores;
    }

    /**
     * @param modificadores the modificadores to set
     */
    public void setModificadores(ArrayList<Modificador> modificadores) {
        this.modificadores = modificadores;
    }
}
