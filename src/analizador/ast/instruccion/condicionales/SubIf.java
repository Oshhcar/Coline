/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion.condicionales;

import analizador.ErrorC;
import analizador.ast.NodoAst;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import analizador.ast.expresion.Literal;
import analizador.ast.expresion.Return;
import analizador.ast.instruccion.Break;
import analizador.ast.instruccion.Instruccion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class SubIf extends Instruccion {

    private final Expresion condicion;
    private final ArrayList<NodoAst> bloques;
    private final boolean isElse;

    public SubIf(Expresion condicion, ArrayList<NodoAst> bloques, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.bloques = bloques;
        this.isElse = false;
    }

    public SubIf(ArrayList<NodoAst> bloques, int linea, int columna) {
        super(linea, columna);
        this.condicion = null;
        this.bloques = bloques;
        this.isElse = true;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        boolean cumple = false;

        if (!this.isElse) {
            Tipo tipCond = this.condicion.getTipo(e, salida, errores);
            if (tipCond != null) {
                if (tipCond == Tipo.BOOLEAN) {
                    Object valCond = this.condicion.getValor(e, salida, errores);
                    if (valCond != null) {
                        cumple = Boolean.valueOf(valCond.toString());
                    }
                }
            }
        }

        if (this.isElse || cumple) {
            Entorno local = new Entorno(e);
            if (this.bloques != null) {
                for (NodoAst bloque : this.bloques) {
                    if (bloque instanceof Instruccion) {
                        if (bloque instanceof Break) {
                            return bloque;
                        } else if (bloque instanceof Return) {
                            /*Ejecutar Return*/
                            Tipo tipReturn = ((Return) bloque).getTipo(local, salida, errores);
                            if (tipReturn != null) {
                                Object valReturn = ((Return) bloque).getValor(local, salida, errores);
                                if (valReturn != null) {
                                    return new Return(new Literal(tipReturn, valReturn, bloque.getLinea(), bloque.getColumna()), bloque.getLinea(), bloque.getColumna());
                                }
                            }
                            return null;
                        } else {
                            Object obj = ((Instruccion) bloque).ejecutar(local, salida, errores);
                            if (obj != null) {
                                if (obj instanceof Break) {
                                    return obj;
                                } else if (obj instanceof Return) {
                                    /*Ejecutar Return*/
                                    Tipo tipReturn = ((Return) bloque).getTipo(local, salida, errores);
                                    if (tipReturn != null) {
                                        Object valReturn = ((Return) bloque).getValor(local, salida, errores);
                                        if (valReturn != null) {
                                            return new Return(new Literal(tipReturn, valReturn, bloque.getLinea(), bloque.getColumna()), bloque.getLinea(), bloque.getColumna());
                                        }
                                    }
                                    return null;
                                }
                            }
                        }
                    } else {
                        if (bloque instanceof Return) {
                            /*Ejecutar Return*/
                            Tipo tipReturn = ((Return) bloque).getTipo(local, salida, errores);
                            if(tipReturn != null){
                                Object valReturn = ((Return) bloque).getValor(local, salida, errores);
                                if(valReturn != null){
                                    return new Return(new Literal(tipReturn, valReturn, bloque.getLinea(), bloque.getColumna()), bloque.getLinea(), bloque.getColumna());
                                }
                            }
                            return null;
                        }
                        ((Expresion) bloque).getValor(local, salida, errores);
                        /*RETURNS*/
                    }
                }
            }
            return true;
        }

        return false;
    }

}
