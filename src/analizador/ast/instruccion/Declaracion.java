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
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Declaracion extends Instruccion {

    private ArrayList<String> modificadores;
    private final Tipo tipo;
    private final ArrayList<Asignacion> asignaciones;

    public Declaracion(Tipo tipo, ArrayList<Asignacion> asignaciones, int linea, int columna) {
        super(linea, columna);
        this.modificadores = null;
        this.tipo = tipo;
        this.asignaciones = asignaciones;
    }

    public Declaracion(ArrayList<String> modificadores, Tipo tipo, ArrayList<Asignacion> asignaciones, int linea, int columna) {
        super(linea, columna);
        this.modificadores = modificadores;
        this.tipo = tipo;
        this.asignaciones = asignaciones;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        System.out.println("entra");
        for (Asignacion asigna : this.asignaciones) {
            if (e.getLocal(asigna.getId().getId()) == null) {
                if (asigna.getValor() != null) {
                    Tipo tipValor = asigna.getValor().getTipo(e, errores);
                    if (this.tipo == tipValor) {
                        Object valor = asigna.getValor().getValor(e, salida, errores);
                        if (valor != null) {
                            e.add(asigna.getId().getId(), new Simbolo(this.tipo, asigna.getId().getId(), valor));
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
                } else {
                    e.add(asigna.getId().getId(), new Simbolo(this.tipo, asigna.getId().getId()));
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

    /**
     * @param modificadores the modificadores to set
     */
    public void setModificadores(ArrayList<String> modificadores) {
        this.modificadores = modificadores;
    }

}
