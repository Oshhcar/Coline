/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Ternario extends Expresion {

    private final Expresion condicion;
    private final Expresion verdadera;
    private final Expresion falsa;

    public Ternario(Expresion condicion, Expresion verdadera, Expresion falsa, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.verdadera = verdadera;
        this.falsa = falsa;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Tipo tipCond = this.condicion.getTipo(e, salida, errores);

        if (tipCond != null) {
            if (tipCond == Tipo.BOOLEAN) {
                Object valCond = this.condicion.getValor(e, salida, errores);
                if (valCond != null) {
                    if (Boolean.valueOf(valCond.toString())) {
                        return this.verdadera.getTipo(e, salida, errores);
                    } else {
                        return this.falsa.getTipo(e, salida, errores);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Tipo tipCond = this.condicion.getTipo(e, salida, errores);

        if (tipCond != null) {
            if (tipCond == Tipo.BOOLEAN) {
                Object valCond = this.condicion.getValor(e, salida, errores);
                if (valCond != null) {
                    if (Boolean.valueOf(valCond.toString())) {
                        return this.verdadera.getValor(e, salida, errores);
                    } else {
                        return this.falsa.getValor(e, salida, errores);
                    }
                }
            }
        }
        return null;
    }

}
