/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Clase extends Instruccion {

    private ArrayList<String> modificadores;
    private final ArrayList<Instruccion> instrucciones;
    private final String id;
    private final String extiende;

    public Clase(String id, ArrayList<Instruccion> instrucciones, int linea, int columna) {
        super(linea, columna);
        this.modificadores = null;
        this.instrucciones = instrucciones;
        this.id = id;
        this.extiende = null;
    }

    public Clase(String id, String extiende, ArrayList<Instruccion> instrucciones, int linea, int columna) {
        super(linea, columna);
        this.modificadores = null;
        this.instrucciones = instrucciones;
        this.id = id;
        this.extiende = extiende;
    }

    public Clase(ArrayList<String> modificadores, ArrayList<Instruccion> instrucciones, String id, int linea, int columna) {
        super(linea, columna);
        this.modificadores = modificadores;
        this.instrucciones = instrucciones;
        this.id = id;
        this.extiende = null;
    }

    public Clase(ArrayList<String> modificadores, ArrayList<Instruccion> instrucciones, String id, String extiende, int linea, int columna) {
        super(linea, columna);
        this.modificadores = modificadores;
        this.instrucciones = instrucciones;
        this.id = id;
        this.extiende = extiende;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, ArrayList<ErrorC> errores) {

        if (this.instrucciones != null) {
            Entorno local = new Entorno(e);
            
            for (Instruccion inst : this.instrucciones) {
                inst.ejecutar(local, salida, errores);
                local.recorrer();
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
