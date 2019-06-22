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
import analizador.ast.expresion.Casteo;
import analizador.ast.expresion.Expresion;
import analizador.ast.expresion.Identificador;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class AsignacionArreglo extends Instruccion {

    private final String id;
    private final ArrayList<Expresion> dimensiones;
    private final Expresion valor;

    public AsignacionArreglo(String id, ArrayList<Expresion> dimensiones, Expresion valor, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.dimensiones = dimensiones;
        this.valor = valor;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        this.debug(e, this_, "Asignacion");

        Simbolo sim = e.get(id);
        if (sim != null) {
            if (sim.getTipo().tipo == Tipo.type.ARRAY) {
                Arreglo aux = (Arreglo) sim.getValor();

                int i = 0;
                while (i < this.dimensiones.size()) {
                    Expresion exp = this.dimensiones.get(i++);
                    Tipo tipExp = exp.getTipo(e, salida, this_, errores);
                    if (tipExp != null) {
                        if (tipExp.tipo == Tipo.type.INT) {
                            Object valExp = exp.getValor(e, salida, this_, errores);
                            if (valExp != null) {
                                int dim = Integer.valueOf(valExp.toString());

                                if (dim >= 0) {
                                    if (i != this.dimensiones.size()) {
                                        Object posDim = aux.get(dim);
                                        if (posDim instanceof Arreglo) {
                                            aux = (Arreglo) posDim;
                                            continue;
                                        }
                                        System.err.println("Error, ya no hay mas dimensiones");
                                        return null;
                                    } else {
                                        Tipo tipValor = this.valor.getTipo(e, salida, this_, errores);
                                        if (tipValor != null) {
                                            if (tipValor.tipo != Tipo.type.ARRAY) {
                                                if (tipValor.tipo == aux.getTipo().subtipo) {
                                                    Object valor1 = this.valor.getValor(e, salida, this_, errores);
                                                    if (valor1 != null) {
                                                        aux.setValor(dim, valor1);
                                                        return null;
                                                    }
                                                    System.err.println("Error en valor");
                                                    return null;
                                                } else {
                                                    Casteo cast = new Casteo(new Tipo(aux.getTipo().subtipo), valor, this.getLinea(), this.getColumna());
                                                    Object valCast = cast.getValor(e, salida, this_, errores);
                                                    if (valCast != null) {
                                                        aux.setValor(dim, valCast);
                                                        return null;
                                                    }
                                                }
                                                System.err.println("No son del mismo tipo");
                                                return null;
                                            } else {
                                                if (tipValor.subtipo == tipValor.subtipo) {
                                                    Object valor1 = this.valor.getValor(e, salida, this_, errores);
                                                    if (valor1 != null) {
                                                        /*verificar tamaños*/
                                                        aux.setValor(dim, valor1);
                                                        return null;
                                                    }
                                                    System.err.println("error en valor");
                                                    return null;
                                                }
                                            }
                                        }
                                        System.err.println("Error en valor");
                                        return null;
                                    }
                                } else {
                                    System.err.println("Las posiciones deben ser positivas");
                                    return null;
                                }
                            }
                        }
                    }
                    return null;
                }
            } else {
                System.err.println("No es arreglo");
                return null;
            }
        }
        System.err.println("no se econtro el arreglo");
        return null;
    }

}
