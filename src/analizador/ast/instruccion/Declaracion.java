/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Arreglo;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Modificador;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Declaracion extends Instruccion {

    private final ArrayList<Modificador> modificadores;
    private final Tipo tipo;
    private final ArrayList<Asignacion> asignaciones;

    public Declaracion(ArrayList<Modificador> modificadores, Tipo tipo, ArrayList<Asignacion> asignaciones, int linea, int columna) {
        super(linea, columna);
        this.modificadores = modificadores;
        this.tipo = tipo;
        this.asignaciones = asignaciones;
    }

    public Declaracion(Tipo tipo, ArrayList<Asignacion> asignaciones, int linea, int columna) {
        super(linea, columna);
        this.modificadores = null;
        this.tipo = tipo;
        this.asignaciones = asignaciones;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, ArrayList<ErrorC> errores) {
        for (Asignacion asigna : this.asignaciones) {
            String id = asigna.getId().getId();
            Simbolo tmp = e.getLocal(id);
            if (tmp == null) {
                Expresion valor = asigna.getValor();
                if (valor != null) {
                    Tipo tipValor = valor.getTipo(e, salida, errores);
                    if (tipValor != null) {
                        Object valValor = valor.getValor(e, salida, errores);
                        if (valValor != null) {
                            if (asigna.getId().getDim() == 0) {
                                if (!(valValor instanceof Arreglo)) {
                                    if (this.tipo.tipo == tipValor.tipo) {
                                        if (this.tipo.tipo == Tipo.type.OBJECT) {
                                            if (!this.tipo.objeto.equals(tipValor.objeto)) {
                                                System.err.println("objetos de diferente tipo");
                                                continue;
                                            }
                                        }
                                        tmp = new Simbolo(this.tipo, id, valValor);
                                    } else {
                                        ErrorC error = new ErrorC();
                                        error.setTipo("Semántico");
                                        error.setValor(asigna.getId().getId());
                                        error.setDescripcion("El valor no corresponde al tipo declarado.");
                                        error.setLinea(this.getLinea());
                                        error.setColumna(this.getColumna());
                                        errores.add(error);
                                        continue;
                                    }
                                } else {
                                    System.err.println("se esta intentando asignar un arreglo");
                                    continue;
                                }
                            } else {/*ARREGLO*/
                                if (valValor instanceof Arreglo) {
                                   // System.err.println(this.tipo.tipo +" == " +tipValor.subtipo);
                                    if (this.tipo.tipo == tipValor.subtipo) {
                                        if (asigna.getId().getDim() == ((Arreglo) valValor).getDimensiones()) {
                                            Tipo t = new Tipo(Tipo.type.ARRAY);
                                            t.subtipo = this.tipo.tipo;
                                            tmp = new Simbolo(t, id, valValor);
                                            tmp.setTamaño(asigna.getId().getDim());
                                        } else {
                                            System.err.println("las dimensiones no son iguales");
                                            continue;
                                        }
                                    } else {
                                        System.err.println("Arreglos de diferente tipo");
                                        continue;
                                    }
                                } else {
                                    System.err.println("No se esta asignando arreglo");
                                    continue;
                                }
                            }
                            e.add(tmp);
                            continue;
                        }
                    }
                    System.err.println("error calculando el valor");
                } else {
                    if (asigna.getId().getDim() != 0) {
                        Tipo t = new Tipo(Tipo.type.ARRAY);
                        t.subtipo = this.tipo.tipo;

                        tmp = new Simbolo(t, id);
                        tmp.setTamaño(asigna.getId().getDim());
                    } else {
                        if (this.tipo.tipo != Tipo.type.OBJECT) {
                            tmp = new Simbolo(this.tipo, id);
                        } else {
                            if (e.getClase(this.tipo.objeto) != null) {
                                tmp = new Simbolo(this.tipo, id);
                            } else {
                                System.err.println("Error, no se ha importado la clase: " + this.tipo.objeto);
                                continue;
                            }
                        }
                    }
                    e.add(tmp);
                }
            } else {
                ErrorC error = new ErrorC();
                error.setTipo("Semántico");
                error.setValor(asigna.getId().getId());
                error.setDescripcion("Ya se declaró una variable con el mismo id.");
                error.setLinea(asigna.getLinea());
                error.setColumna(asigna.getColumna());
                errores.add(error);
            }
        }
        return null;
    }

}
