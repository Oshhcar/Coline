/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.NodoAst;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import analizador.ast.expresion.Literal;
import analizador.ast.expresion.Return;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Bloque extends Instruccion {

    private final ArrayList<NodoAst> bloques;

    public Bloque(ArrayList<NodoAst> bloques, int linea, int columna) {
        super(linea, columna);
        this.bloques = bloques;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, ArrayList<ErrorC> errores) {
        if (this.bloques != null) {
            for (NodoAst bloque : this.bloques) {
                if (bloque instanceof Break) {
                    return bloque;
                }
                if (bloque instanceof Continue) {
                    return bloque;
                } else if (bloque instanceof Return) {
                    Tipo tipReturn = ((Return) bloque).getTipo(e, salida, errores);
                    if (tipReturn != null) {
                        Object valReturn = ((Return) bloque).getValor(e, salida, errores);
                        if (valReturn != null) {
                            return new Return(new Literal(tipReturn, valReturn, bloque.getLinea(), bloque.getColumna()), bloque.getLinea(), bloque.getColumna());
                        }
                    }
                    System.err.println("Erro obteniendo return");
                    return null;
                } else {
                    if (bloque instanceof Instruccion) {
                        Object obj = ((Instruccion) bloque).ejecutar(e, salida, metodo, ciclo, switch_, errores);
                        if (obj != null) {
                            if (obj instanceof Break) {
                                return obj;
                            } else if (bloque instanceof Continue) {
                                return obj;
                            } else if (obj instanceof Return) {
                                Tipo tipReturn = ((Return) bloque).getTipo(e, salida, errores);
                                if (tipReturn != null) {
                                    Object valReturn = ((Return) bloque).getValor(e, salida, errores);
                                    if (valReturn != null) {
                                        return new Return(new Literal(tipReturn, valReturn, bloque.getLinea(), bloque.getColumna()), bloque.getLinea(), bloque.getColumna());
                                    }
                                }
                                System.err.println("Erro obteniendo return");
                                return null;
                            }
                        }
                    } else {
                        ((Expresion) bloque).getValor(e, salida, errores);
                    }
                }
            }
        }
        return null;
    }

    /**
     * @return the bloques
     */
    public ArrayList<NodoAst> getBloques() {
        return bloques;
    }

}
