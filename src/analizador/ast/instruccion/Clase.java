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
public class Clase extends Declaracion {
    private final ArrayList<Declaracion> declaraciones;
    private final String id;
    private final String extiende;

    public Clase(String id, ArrayList<Declaracion> declaraciones, int linea, int columna) {
        super(linea, columna);
        this.declaraciones = declaraciones;
        this.id = id;
        this.extiende = null;
    }

    public Clase(String id, String extiende, ArrayList<Declaracion> declaraciones, int linea, int columna) {
        super(linea, columna);
        this.declaraciones = declaraciones;
        this.id = id;
        this.extiende = extiende;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        if (this.declaraciones != null) {
            Entorno local = new Entorno(e);
            
            for (Declaracion inst : this.declaraciones) {
                inst.ejecutar(local, salida, errores);
            }local.recorrer();
        }
        return null;
    }
}
