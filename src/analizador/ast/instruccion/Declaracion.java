/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Modificador;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
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
            if (e.getLocal(asigna.getId().getId()) == null) {
                if (asigna.getValor() != null) {
                    Tipo tipValor = asigna.getValor().getTipo(e, salida, errores);
                    if (tipValor != null) {
                        if (this.tipo.tipo == tipValor.tipo) {
                            if (this.tipo.tipo == Tipo.type.OBJECT) {
                                if (!this.tipo.objeto.equals("String")) {

                                    Object valor = asigna.getValor().getValor(e, salida, errores);
                                    if (valor != null) {
                                        e.add(new Simbolo(this.tipo, asigna.getId().getId(), valor));
                                    }
                                } else {
                                    Object valor = asigna.getValor().getValor(e, salida, errores);

                                    if (valor != null) {
                                        e.add(new Simbolo(this.tipo, asigna.getId().getId(), valor));
                                    }
                                }
                            } else {

                                Object valor = asigna.getValor().getValor(e, salida, errores);

                                if (valor != null) {
                                    e.add(new Simbolo(this.tipo, asigna.getId().getId(), valor));
                                }
                            }
                        } else {
                            ErrorC error = new ErrorC();
                            error.setTipo("Semántico");
                            error.setValor(asigna.getId().getId());
                            error.setDescripcion("El valor no corresponde al tipo declarado.");
                            error.setLinea(this.getLinea());
                            error.setColumna(this.getColumna());
                            errores.add(error);
                        }
                    }
                } else {
                    if (this.tipo.tipo != Tipo.type.OBJECT) {
                        e.add(new Simbolo(this.tipo, asigna.getId().getId()));
                    } else {
                        if (!this.tipo.objeto.equals("String")) {
                            if (e.getClase(this.tipo.objeto) != null) {
                                e.add(new Simbolo(this.tipo, asigna.getId().getId()));
                            } else {
                                System.err.println("Error, no se ha importado la clase: " + this.tipo.objeto);
                            }
                        } else {
                            e.add(new Simbolo(this.tipo, asigna.getId().getId()));
                        }
                    }
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
