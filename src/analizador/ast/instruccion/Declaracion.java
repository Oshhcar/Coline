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
import analizador.ast.entorno.Null;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Casteo;
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
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        this.debug(e, this_, "Declaracion Variables");

        for (Asignacion asigna : this.asignaciones) {
            String id = asigna.getId().getId();
            Simbolo tmp = e.getLocal(id);
            if (tmp == null) {
                Expresion valor = asigna.getValor();
                if (valor != null) {
                    Tipo tipValor = valor.getTipo(e, salida, this_, errores);
                    if (tipValor != null) {
                        Object valValor = valor.getValor(e, salida, this_, errores);
                        if (valValor != null) {
                            if (asigna.getId().getDim() == 0) {
                                if (!(valValor instanceof Arreglo)) {
                                    if (!(valValor instanceof Null)) {
                                        if (this.tipo.tipo == tipValor.tipo) {
                                            if (this.tipo.tipo == Tipo.type.OBJECT) {
                                                if (!this.tipo.objeto.equals(tipValor.objeto)) {
                                                    ErrorC error = new ErrorC();
                                                    error.setTipo("Semántico");
                                                    error.setValor(asigna.getId().getId());
                                                    error.setDescripcion("Objetos de diferente tipo: " + this.tipo.objeto);
                                                    error.setLinea(asigna.getLinea());
                                                    error.setColumna(asigna.getColumna());
                                                    errores.add(error);
                                                    continue;
                                                }
                                            }
                                            tmp = new Simbolo(this.tipo, id, valValor);
                                        } else {
                                            if (this.tipo.tipo != Tipo.type.OBJECT) {
                                                Casteo cast = new Casteo(this.tipo, valor, this.getLinea(), this.getColumna());
                                                Object valCast = cast.getValor(e, salida, this_, errores);
                                                if (valCast != null) {
                                                    tmp = new Simbolo(this.tipo, id, valCast);
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
                                                /*CASTEO OBJETO*/
                                            }
                                        }
                                    } else {
                                        /*ASIGNACION NULL*/
                                        tmp = new Simbolo(this.tipo, id, valValor);
                                    }
                                } else {
                                    ErrorC error = new ErrorC();
                                    error.setTipo("Semántico");
                                    error.setValor(asigna.getId().getId());
                                    error.setDescripcion("Error asignando a arreglo.");
                                    error.setLinea(asigna.getLinea());
                                    error.setColumna(asigna.getColumna());
                                    errores.add(error);
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
                                            ErrorC error = new ErrorC();
                                            error.setTipo("Semántico");
                                            error.setValor(asigna.getId().getId());
                                            error.setDescripcion("Las dimensiones del arreglo no son las mismas. ");
                                            error.setLinea(asigna.getLinea());
                                            error.setColumna(asigna.getColumna());
                                            errores.add(error);
                                            continue;
                                        }
                                    } else {
                                        ErrorC error = new ErrorC();
                                        error.setTipo("Semántico");
                                        error.setValor(asigna.getId().getId());
                                        error.setDescripcion("Los arreglos son de diferente tipo.  ");
                                        error.setLinea(asigna.getLinea());
                                        error.setColumna(asigna.getColumna());
                                        errores.add(error);
                                        continue;
                                    }
                                } else {
                                    ErrorC error = new ErrorC();
                                    error.setTipo("Semántico");
                                    error.setValor(asigna.getId().getId());
                                    error.setDescripcion("El valor no es un arreglo.");
                                    error.setLinea(asigna.getLinea());
                                    error.setColumna(asigna.getColumna());
                                    errores.add(error);
                                    continue;
                                }
                            }
                            e.add(tmp);
                            continue;
                        }
                    }
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
                                ErrorC error = new ErrorC();
                                error.setTipo("Semántico");
                                error.setValor(asigna.getId().getId());
                                error.setDescripcion("Error, no se ha importado la clase: " + this.tipo.objeto);
                                error.setLinea(asigna.getLinea());
                                error.setColumna(asigna.getColumna());
                                errores.add(error);
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
