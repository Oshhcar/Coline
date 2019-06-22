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
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        if (this.bloques != null) {
            for (NodoAst bloque : this.bloques) {
                Object obj;
                if (bloque instanceof Instruccion) {
                    obj = ((Instruccion) bloque).ejecutar(e, salida, metodo, ciclo, switch_, this_, errores);

                    if (obj != null) {
                        if (obj instanceof Return) {
                            if (metodo) {
                                return obj;
                            }
                            ErrorC error = new ErrorC();
                            error.setTipo("Semántico");
                            error.setValor("return");
                            error.setDescripcion("No esta dentro de un metodo.");
                            error.setLinea(this.getLinea());
                            error.setColumna(this.getColumna());
                            errores.add(error);
                        } else if (obj instanceof Break) {
                            if (ciclo || switch_) {
                                return obj;
                            }
                            ErrorC error = new ErrorC();
                            error.setTipo("Semántico");
                            error.setValor("break");
                            error.setDescripcion("No esta dentro de un ciclo o swittch.");
                            error.setLinea(this.getLinea());
                            error.setColumna(this.getColumna());
                            errores.add(error);
                        } else if (obj instanceof Continue) {
                            if (ciclo) {
                                return obj;
                            }
                            ErrorC error = new ErrorC();
                            error.setTipo("Semántico");
                            error.setValor("continue");
                            error.setDescripcion("No esta dentro de un ciclo.");
                            error.setLinea(this.getLinea());
                            error.setColumna(this.getColumna());
                            errores.add(error);
                        }
                    }
                } else {
                    if (bloque instanceof Return) {
                        Return ret = (Return) bloque;
                        if (metodo) {
                            if (ret.getToReturn() != null) {
                                Tipo tipRet = ret.getTipo(e, salida, this_, errores);
                                if (tipRet != null) {
                                    Object valRet = ret.getValor(e, salida, this_, errores);
                                    if (valRet != null) {
                                        return new Return(new Literal(tipRet, valRet, ret.getLinea(), ret.getColumna()), ret.getLinea(), ret.getColumna());
                                    }
                                }
                                ErrorC error = new ErrorC();
                                error.setTipo("Semántico");
                                error.setValor("return");
                                error.setDescripcion("Error en el return.");
                                error.setLinea(this.getLinea());
                                error.setColumna(this.getColumna());
                                errores.add(error);
                                continue;
                            }
                            return ret;
                        }
                        ErrorC error = new ErrorC();
                        error.setTipo("Semántico");
                        error.setValor("return");
                        error.setDescripcion("No esta dentro de un metodo.");
                        error.setLinea(this.getLinea());
                        error.setColumna(this.getColumna());
                        errores.add(error);
                    } else if (bloque instanceof Break) {
                        if (ciclo || switch_) {
                            return bloque;
                        }
                        ErrorC error = new ErrorC();
                        error.setTipo("Semántico");
                        error.setValor("break");
                        error.setDescripcion("No esta dentro de un ciclo o swittch.");
                        error.setLinea(this.getLinea());
                        error.setColumna(this.getColumna());
                        errores.add(error);
                    } else if (bloque instanceof Continue) {
                        if (ciclo) {
                            return bloque;
                        }
                        ErrorC error = new ErrorC();
                        error.setTipo("Semántico");
                        error.setValor("continue");
                        error.setDescripcion("No esta dentro de un ciclo.");
                        error.setLinea(this.getLinea());
                        error.setColumna(this.getColumna());
                        errores.add(error);
                    } else {
                        ((Expresion) bloque).getValor(e, salida, this_, errores);
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
