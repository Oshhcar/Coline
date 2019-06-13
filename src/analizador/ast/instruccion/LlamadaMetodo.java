/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.NodoAst;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Metodo;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class LlamadaMetodo extends Instruccion {

    private final String id;
    private final ArrayList<Expresion> parametros;

    public LlamadaMetodo(String id, ArrayList<Expresion> parametros, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        if (this.parametros == null) {
            Metodo m = e.getMetodo(this.id, null);
            if (m != null) {
                if (m.getBloques() != null) {
                    Entorno local = new Entorno(e.getGlobal());
                    for (NodoAst bloque : m.getBloques()) {
                        if (bloque instanceof Instruccion) {
                            ((Instruccion) bloque).ejecutar(local, salida, errores);
                        } else {
                            ((Expresion) bloque).getValor(e, salida, errores);
                        }
                    }
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
        } else {
            Entorno local = new Entorno(e.getGlobal());

            ArrayList<Simbolo> parm = new ArrayList<>();
            for (Expresion parametro : this.parametros) {
                Tipo tipo = parametro.getTipo(e, errores);
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

            Metodo m = e.getMetodo(this.id, parm);

            if (m != null) {
                for (int i = 0; i <= parm.size() - 1; i++) {
                    local.add(new Simbolo(parm.get(i).getTipo(), m.getParametros().get(i).getId(), parm.get(i).getValor()));
                }
                if (m.getBloques() != null) {
                    for (NodoAst bloque : m.getBloques()) {
                        if (bloque instanceof Instruccion) {
                            ((Instruccion) bloque).ejecutar(local, salida, errores);
                        } else {
                            ((Expresion) bloque).getValor(e, salida, errores);
                        }
                    }
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

        }
        return null;
    }

}
