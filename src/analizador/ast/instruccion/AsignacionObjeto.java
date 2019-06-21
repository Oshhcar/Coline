/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class AsignacionObjeto extends Instruccion {

    private final AccesoObjeto acceso;
    private final Expresion valor;

    public AsignacionObjeto(AccesoObjeto acceso, Expresion valor, int linea, int columna) {
        super(linea, columna);
        this.acceso = acceso;
        this.valor = valor;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        acceso.setRetSimbolo(true);
        Object var = acceso.ejecutar(e, salida, metodo, ciclo, switch_, this_, errores);

        if (var != null) {
            Simbolo sim = (Simbolo) var;

            Tipo tipValor = valor.getTipo(e, salida, this_, errores);
            if (tipValor != null) {
                if (sim.getTipo().tipo == tipValor.tipo) {
                    if (sim.getTipo().tipo == Tipo.type.OBJECT) {
                        if (sim.getTipo().subtipo != null && tipValor.subtipo != null) {
                            if (sim.getTipo().subtipo.equals(tipValor.subtipo)) {
                                Object valValor = valor.getValor(e, salida, this_, errores);
                                if (valValor != null) {
                                    sim.setValor(valValor);
                                    return null;
                                }
                                System.err.println("error en valor");
                                return null;
                            }
                        }
                        System.err.println("error asignar objetos");
                        return null;
                    } else {
                        Object valValor = valor.getValor(e, salida, this_, errores);
                        if (valValor != null) {
                            sim.setValor(valValor);
                            return null;
                        }
                        System.err.println("Error en valor");
                        return null;
                    }
                }
            }
        }
        System.err.println("Ocurrio un error en la asignación." + this.getLinea());
        return null;
    }

}
