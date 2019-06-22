/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion.ciclos;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import analizador.ast.expresion.Return;
import analizador.ast.instruccion.Bloque;
import analizador.ast.instruccion.Break;
import analizador.ast.instruccion.Continue;
import analizador.ast.instruccion.Instruccion;
import coline.Coline;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class DoWhile extends Instruccion {

    private final Bloque bloque;
    private final Expresion condicion;

    public DoWhile(Bloque bloque, Expresion condicion, int linea, int columna) {
        super(linea, columna);
        this.bloque = bloque;
        this.condicion = condicion;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        if (bloque != null) {
            while (true) {
                if (Coline.saltarDebug) {
                    Coline.debugger = false;
                }
                this.debug(e, this_, "do_while");

                Entorno local = new Entorno(e);
                Object obj = this.bloque.ejecutar(local, salida, metodo, true, switch_, this_, errores);

                if (obj != null) {
                    if (obj instanceof Break) {
                        if (Coline.saltarDebug) {
                            Coline.debugger = true;
                            Coline.saltarDebug = false;
                        }
                        return null;
                    } else if (obj instanceof Return) {
                        if (Coline.saltarDebug) {
                            Coline.debugger = true;
                            Coline.saltarDebug = false;
                        }
                        return obj;
                    }
                }

                Tipo tipCondicion = condicion.getTipo(e, salida, this_, errores);
                if (tipCondicion != null) {
                    if (tipCondicion.tipo == Tipo.type.BOOLEAN) {
                        Object valCondicion = condicion.getValor(e, salida, this_, errores);
                        if (valCondicion != null) {
                            if (Boolean.valueOf(valCondicion.toString())) {
                                continue;
                            }
                        }
                    } else {
                        System.err.println("Condicion debe ser booleana. DoWhile");
                    }
                }
                if (Coline.saltarDebug) {
                    Coline.debugger = true;
                    Coline.saltarDebug = false;
                }
                return null;
            }
        }
        return null;
    }

}
