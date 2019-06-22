/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Arreglo;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Null;
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
        this.debug(e, this_, "Asignacion");

        Simbolo tmp = e.get(this.id.getId());
        if (tmp != null) {
            Tipo tipValor = this.valor.getTipo(e, salida, this_, errores);
            if (tipValor != null) {
                if (tipValor.tipo != Tipo.type.NULL) {
                    if (tmp.getTipo().tipo == tipValor.tipo) {
                        Object valValor = this.valor.getValor(e, salida, this_, errores);
                        if (valValor != null) {
                            if (tmp.getTipo().tipo == Tipo.type.ARRAY) {
                                if (valValor instanceof Arreglo) {
                                    Arreglo a = (Arreglo) valValor;

                                    if (tmp.getTamaño() != a.getDimensiones()) {
                                        ErrorC error = new ErrorC();
                                        error.setTipo("Semántico");
                                        //error.setValor(thisAcceso.getId());
                                        error.setDescripcion("Los arreglos son de diferentes dimensiones.");
                                        error.setLinea(this.getLinea());
                                        error.setColumna(this.getColumna());
                                        errores.add(error);
                                        return null;
                                    }
                                    if (tmp.getTipo().subtipo != tipValor.subtipo) {
                                        ErrorC error = new ErrorC();
                                        error.setTipo("Semántico");
                                        //error.setValor(thisAcceso.getId());
                                        error.setDescripcion("Los arreglos no son del mismo tipo.");
                                        error.setLinea(this.getLinea());
                                        error.setColumna(this.getColumna());
                                        errores.add(error);
                                        return null;
                                    }

                                } else {
                                    ErrorC error = new ErrorC();
                                    error.setTipo("Semántico");
                                    //error.setValor(thisAcceso.getId());
                                    error.setDescripcion("No es un arreglo lo que se está asignando.");
                                    error.setLinea(this.getLinea());
                                    error.setColumna(this.getColumna());
                                    errores.add(error);
                                    return null;
                                }
                            }
                            tmp.setValor(valValor);
                            return null;
                        }
                    } else {
                        Casteo cast = new Casteo(tmp.getTipo(), valor, this.getLinea(), this.getColumna());
                        Object valCast = cast.getValor(e, salida, this_, errores);
                        if (valCast != null) {
                            tmp.setValor(valCast);
                            return null;
                        }
                    }
                    ErrorC error = new ErrorC();
                    error.setTipo("Semántico");
                    //error.setValor(thisAcceso.getId());
                    error.setDescripcion("No se puede asignar el valor.");
                    error.setLinea(this.getLinea());
                    error.setColumna(this.getColumna());
                    errores.add(error);
                } else {
                    tmp.setValor(new Null());
                    return null;
                }
            }
        } else {
            ErrorC error = new ErrorC();
            error.setTipo("Semántico");
            error.setValor(this.id.getId());
            error.setDescripcion("La variable no se ha declarado.");
            error.setLinea(this.getLinea());
            error.setColumna(this.getColumna());
            errores.add(error);
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
