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
public class Casteo extends Expresion {

    private final Tipo tipo;
    private final Expresion expresion;

    public Casteo(Tipo tipo, Expresion expresion, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.expresion = expresion;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        return tipo;
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        if (this.tipo.tipo != Tipo.type.BOOLEAN) {
            Tipo tipExp = this.expresion.getTipo(e, salida, this_, errores);
            if (tipExp != null) {
                if (tipExp.tipo != Tipo.type.BOOLEAN) {
                    if (this.tipo.tipo == Tipo.type.DOUBLE) {
                        return getDouble(tipExp, this.expresion.getValor(e, salida, this_, errores));
                    } else if (this.tipo.tipo == Tipo.type.INT) {
                        return getInt(tipExp, this.expresion.getValor(e, salida, this_, errores));
                    } else if (this.tipo.tipo == Tipo.type.CHAR) {
                        return getChar(tipExp, this.expresion.getValor(e, salida, this_, errores));
                    }
                }
            }
        }
        System.err.println("no se puede castear");
        return null;
    }

    private Double getDouble(Tipo tipo, Object valor) {
        if (tipo != null && valor != null) {
            switch (tipo.tipo) {
                case INT:
                case DOUBLE:
                    return new Double(valor.toString());
                case CHAR:
                    return new Double(valor.toString().charAt(0));
            }
        }
        return null;
    }

    private Integer getInt(Tipo tipo, Object valor) {
        if (tipo != null && valor != null) {
            switch (tipo.tipo) {
                case INT:
                    return new Integer(valor.toString());
                case DOUBLE:
                    Double d = new Double(valor.toString());
                    return d.intValue();
                case CHAR:
                    return new Integer(valor.toString().charAt(0));
            }
        }
        return null;
    }
    
    private char getChar(Tipo tipo, Object valor) {
        if (tipo != null && valor != null) {
            switch (tipo.tipo) {
                case INT:
                    int num = new Integer(valor.toString());
                    return (char)num;
                case DOUBLE:
                    Double d = new Double(valor.toString());
                    return (char)d.intValue();
                case CHAR:
                    return valor.toString().charAt(0);
            }
        }
        return '\0';
    }

}
