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
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        Tipo tipCond = this.condicion.getTipo(e, salida, this_, errores);

        if (tipCond != null) {
            if (tipCond.tipo == Tipo.type.BOOLEAN) {
                Object valCond = this.condicion.getValor(e, salida, this_, errores);
                if (valCond != null) {
                    if (Boolean.valueOf(valCond.toString())) {
                        return this.verdadera.getTipo(e, salida, this_, errores);
                    } else {
                        return this.falsa.getTipo(e, salida, this_, errores);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        Tipo tipCond = this.condicion.getTipo(e, salida, this_, errores);

        if (tipCond != null) {
            if (tipCond.tipo == Tipo.type.BOOLEAN) {
                Object valCond = this.condicion.getValor(e, salida, this_, errores);
                if (valCond != null) {
                    if (Boolean.valueOf(valCond.toString())) {
                        return this.verdadera.getValor(e, salida, this_, errores);
                    } else {
                        return this.falsa.getValor(e, salida, this_, errores);
                    }
                }
            }
        }
        return null;
    }

}
