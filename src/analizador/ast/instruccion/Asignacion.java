/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Arreglo;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import analizador.ast.expresion.Identificador;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Asignacion extends Instruccion {

    private final Identificador id;
    private final Expresion valor;

    public Asignacion(Identificador id) {
        super(id.getLinea(), id.getColumna());
        this.id = id;
        this.valor = null;
    }

    public Asignacion(Identificador id, Expresion valor) {
        super(id.getLinea(), id.getColumna());
        this.id = id;
        this.valor = valor;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        Simbolo tmp = e.get(this.id.getId());
        if (tmp != null) {
            Tipo tipValor = this.valor.getTipo(e, salida, this_, errores);
            if (tipValor != null) {
                if (tmp.getTipo().tipo == tipValor.tipo) {
                    Object valValor = this.valor.getValor(e, salida, this_, errores);
                    if (valValor != null) {
                        if (tmp.getTipo().tipo == Tipo.type.ARRAY) {
                            if (valValor instanceof Arreglo) {
                                Arreglo a = (Arreglo) valValor;

                                if (tmp.getTamaño() != a.getDimensiones()) {
                                    System.err.println("Arreglos no son de las mismas dimensiones");
                                    return null;
                                }
                                if (tmp.getTipo().subtipo != tipValor.subtipo) {
                                    System.err.println("Arreglos no son del mismo tipo");
                                    return null;
                                }

                            } else {
                                System.err.println("no se está asignando arreglo");
                                return null;
                            }
                        }
                        tmp.setValor(valValor);
                        return null;
                    }
                }
            }
            System.err.println("*Error Semántico, no se puede asignar el valor. ");

        } else {
            System.err.println("*Error Semántico, no se ha declarado la variable: " + this.id.getId() + ". ");
        }
        return null;
    }

    /**
     * @return the id
     */
    public Identificador getId() {
        return id;
    }

    /**
     * @return the valor
     */
    public Expresion getValor() {
        return valor;
    }

}
