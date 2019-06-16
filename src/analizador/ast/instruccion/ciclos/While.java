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
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class While extends Instruccion {

    private final Expresion condicion;
    private final Bloque bloque;

    public While(Expresion condicion, Bloque bloque, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.bloque = bloque;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, ArrayList<ErrorC> errores) {
        if (bloque != null) {
            while (true) {
                Tipo tipCondicion = condicion.getTipo(e, salida, errores);
                if (tipCondicion != null) {
                    if (tipCondicion == Tipo.BOOLEAN) {
                        Object valCondicion = condicion.getValor(e, salida, errores);
                        if (valCondicion != null) {
                            if (Boolean.valueOf(valCondicion.toString())) {
                                Entorno local = new Entorno(e);
                                Object obj = this.bloque.ejecutar(local, salida, metodo, true, switch_, errores);
                                if (obj != null) {
                                    if (obj instanceof Break) {
                                        return null;
                                    } else if(obj instanceof Return) {
                                        return obj;
                                    }
                                }
                                continue;
                            }
                            return null;
                        }
                    } else {
                        System.err.println("While, condicion no booleana");
                    }
                }
                return null;
            }
        }
        return null;
    }
}
