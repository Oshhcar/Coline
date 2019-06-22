/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion.condicionales;

import analizador.ErrorC;
import analizador.ast.NodoAst;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Excepcion;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import analizador.ast.expresion.Literal;
import analizador.ast.expresion.Return;
import analizador.ast.instruccion.Bloque;
import analizador.ast.instruccion.Break;
import analizador.ast.instruccion.Instruccion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class SubIf extends Instruccion {

    private final Expresion condicion;
    private final Bloque bloque;
    private final boolean isElse;
    private boolean entra;

    public SubIf(Expresion condicion, Bloque bloque, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.bloque = bloque;
        this.isElse = false;
        this.entra = false;
    }

    public SubIf(Bloque bloque, int linea, int columna) {
        super(linea, columna);
        this.condicion = null;
        this.bloque = bloque;
        this.isElse = true;
        this.entra = true;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        if (this.bloque != null) {
            if (!this.isElse) {
                Tipo tipCond = this.condicion.getTipo(e, salida, this_, errores);
                if (tipCond != null) {
                    if (tipCond.tipo == Tipo.type.BOOLEAN) {
                        Object valCond = this.condicion.getValor(e, salida, this_, errores);
                        if (valCond != null) {
                            this.entra = Boolean.valueOf(valCond.toString());
                        } else {
                            return new Excepcion("Logic");
                        }
                    } else {
                        return new Excepcion("Logic");
                    }
                } else{
                    return new Excepcion("Arithmetic");
                }
            }

            if (this.isElse || isEntra()) {
                this.debug(e, this_, "if");
                Entorno local = new Entorno(e);
                return this.bloque.ejecutar(local, salida, metodo, ciclo, switch_, this_, errores);
            }
        }
        return null;
    }

    /**
     * @return the cumple
     */
    public boolean isEntra() {
        return entra;
    }

}
