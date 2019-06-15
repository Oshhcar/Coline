/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion.condicionales;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import analizador.ast.instruccion.Bloque;
import analizador.ast.instruccion.Instruccion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Case extends Instruccion {

    private final Expresion expresion;
    private final Bloque bloque;
    private Tipo tipExp;
    private Object valorExp;
    private final boolean isDefault;
    private boolean continuar;

    public Case(Expresion expresion, Bloque bloque, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
        this.bloque = bloque;
        this.tipExp = null;
        this.valorExp = null;
        this.isDefault = false;
        this.continuar = false;
    }

    public Case(Bloque bloque, int linea, int columna) {
        super(linea, columna);
        this.expresion = null;
        this.bloque = bloque;
        this.tipExp = null;
        this.valorExp = null;
        this.isDefault = true;
        this.continuar = false;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, ArrayList<ErrorC> errores) {
        if (!isDefault) {
            if (!isContinuar()) {
                Tipo tipExp = expresion.getTipo(e, salida, errores);
                if (tipExp != null) {
                    Object valExp = expresion.getValor(e, salida, errores);
                    if (valExp != null) {
                        if (tipExp == this.tipExp) {
                            if (valExp.toString().equals(this.valorExp.toString())) {
                                setContinuar(true);
                            }
                        }
                    } else {
                        System.err.println("Error valor en case.");
                    }
                }
            }
        } else {
            setContinuar(true);
        }

        if (isContinuar()) {
            if (bloque != null) {
                return bloque.ejecutar(e, salida, metodo, ciclo, switch_, errores);
            }
        }

        return null;
    }

    /**
     * @param tipExp the tipExp to set
     * @param valExp the valExp to set
     */
    public void setExpSwitch(Tipo tipExp, Object valExp) {
        this.tipExp = tipExp;
        this.valorExp = valExp;
    }

    /**
     * @return the continuar
     */
    public boolean isContinuar() {
        return continuar;
    }

    /**
     * @param continuar the continuar to set
     */
    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }

}
