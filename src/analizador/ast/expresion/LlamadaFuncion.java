/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion;

import analizador.ErrorC;
import analizador.ast.NodoAst;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Metodo;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import analizador.ast.instruccion.Instruccion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class LlamadaFuncion extends Expresion {

    private final String id;
    private final ArrayList<Expresion> parametros;

    public LlamadaFuncion(String id, ArrayList<Expresion> parametros, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Metodo m = null;
        Entorno local = new Entorno(e); /*verificar que no se pasen todos los entornos*/
        if (this.parametros == null) {
            m = e.getMetodo(this.id, null);
        } else {
            ArrayList<Simbolo> parm = new ArrayList<>();
            for (Expresion parametro : this.parametros) {
                Tipo tipo = parametro.getTipo(e, salida, errores);
                if (tipo != null) {
                    Object valor = parametro.getValor(e, salida, errores);
                    if (valor != null) {
                        parm.add(new Simbolo(tipo, "parm", valor));
                        continue;
                    }
                }
                System.err.println("error en parametros");
                return null;
            }

            m = e.getMetodo(this.id, parm);
        }
        
        if (m != null) {
            if (m.getTipo() != Tipo.VOID) {
                return m.getTipo();
            } else {
                ErrorC error = new ErrorC();
                error.setTipo("Semántico");
                error.setValor(this.id);
                error.setDescripcion("La función es de tipo void.");
                error.setLinea(this.getLinea());
                error.setColumna(this.getColumna());
                errores.add(error);
            }
        } else {
            ErrorC error = new ErrorC();
            error.setTipo("Semántico");
            error.setValor(this.id);
            error.setDescripcion("La función no se ha declarado.");
            error.setLinea(this.getLinea());
            error.setColumna(this.getColumna());
            errores.add(error);
        }
        
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Metodo m = null;
        Entorno local = new Entorno(e); /*verificar que no se pasen todos los entornos*/

        if (this.parametros == null) {
            m = e.getMetodo(this.id, null);
        } else {
            ArrayList<Simbolo> parm = new ArrayList<>();
            for (Expresion parametro : this.parametros) {
                Tipo tipo = parametro.getTipo(e, salida, errores);
                if (tipo != null) {
                    Object valor = parametro.getValor(e, salida, errores);
                    if (valor != null) {
                        parm.add(new Simbolo(tipo, "parm", valor));
                        continue;
                    }
                }
                System.err.println("error en parametros");
                return null;
            }

            m = e.getMetodo(this.id, parm);

            if (m != null) {
                for (int i = 0; i <= parm.size() - 1; i++) {
                    local.add(new Simbolo(parm.get(i).getTipo(), m.getParametros().get(i).getId(), parm.get(i).getValor()));
                }
            }
        }

        if (m != null) {
            if (m.getTipo() != Tipo.VOID) {
                if (m.getBloque().getBloques() != null) {
                    for (NodoAst bloque : m.getBloque().getBloques()) {
                        if (bloque instanceof Instruccion) {
                            Object obj = ((Instruccion) bloque).ejecutar(local, salida, errores);
                            if (obj instanceof Return) {
                                Tipo tipReturn = ((Return) obj).getTipo(e, salida, errores);
                                if (tipReturn != null) {
                                    if (tipReturn == m.getTipo()) {
                                        return ((Return) obj).getValor(e, salida, errores);
                                    }
                                }
                                System.err.println("Error return");
                                return null;
                            }
                        } else {
                            if (bloque instanceof Return) {
                                Tipo tipReturn = ((Return) bloque).getTipo(e, salida, errores);
                                if (tipReturn != null) {
                                    if (tipReturn == m.getTipo()) {
                                        return ((Return) bloque).getValor(e, salida, errores);
                                    }
                                }
                                System.err.println("Error return");
                                return null;
                            }
                        }
                    }
                }
            } else {
                ErrorC error = new ErrorC();
                error.setTipo("Semántico");
                error.setValor(this.id);
                error.setDescripcion("La función es de tipo void.");
                error.setLinea(this.getLinea());
                error.setColumna(this.getColumna());
                errores.add(error);
            }
        } else {
            ErrorC error = new ErrorC();
            error.setTipo("Semántico");
            error.setValor(this.id);
            error.setDescripcion("El metodo no se ha declarado.");
            error.setLinea(this.getLinea());
            error.setColumna(this.getColumna());
            errores.add(error);
        }
        return null;
    }
}
