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
            Entorno local = new Entorno(e);
            for (NodoAst bloque : this.bloques) {
                Object obj;
                if (bloque instanceof Instruccion) {
                    obj = ((Instruccion) bloque).ejecutar(local, salida, metodo, ciclo, switch_, errores);

                    if (obj != null) {
                        if (obj instanceof Return) {
                            if (metodo) {
                                return obj;
                            }
                            System.err.println("no esta dentro de metodo");
                        } else if (obj instanceof Break) {
                            if (ciclo || switch_) {
                                return obj;
                            }
                            System.err.println("No esta dentro de un ciclo o swittch, BREAK.");
                        } else if (obj instanceof Continue) {
                            if (ciclo) {
                                return obj;
                            }
                            System.err.println("No esta dentro de un ciclo1, CONTINUE.");
                        }
                    }
                } else {
                    if (bloque instanceof Return) {
                        Return ret = (Return) bloque;
                        if (metodo) {
                            if (ret.getToReturn() != null) {
                                Tipo tipRet = ret.getTipo(local, salida, errores);
                                if (tipRet != null) {
                                    Object valRet = ret.getValor(local, salida, errores);
                                    if (valRet != null) {
                                        return new Return(new Literal(tipRet, valRet, ret.getLinea(), ret.getColumna()), ret.getLinea(), ret.getColumna());
                                    }
                                }
                                System.err.println("Error resolviendo return");
                                continue;
                            }
                            return ret;
                        }
                        System.err.println("No esta dentro de un metodo return");
                    } else if (bloque instanceof Break) {
                        if (ciclo || switch_) {
                            return bloque;
                        }
                        System.err.println("No esta dentro de un ciclo o swittch, BREAK.");
                    } else if (bloque instanceof Continue) {
                        if (ciclo) {
                            return bloque;
                        }
                        System.err.println("No esta dentro de un ciclo2, CONTINUE.");
                    } else {
                        ((Expresion) bloque).getValor(local, salida, errores);
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
