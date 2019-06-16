/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.entorno;

import analizador.ast.instruccion.Bloque;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Metodo extends Simbolo{
    
    private final String firma;
    private final ArrayList<Simbolo> parametros;
    private final Bloque bloque;

    public Metodo(Tipo tipo, String id, String firma, ArrayList<Simbolo> parametros, Bloque bloque) {
        super(tipo, id);
        this.firma = firma;
        this.parametros = parametros;
        this.bloque = bloque;
    }

    /**
     * @return the bloques
     */
    public Bloque getBloque() {
        return bloque;
    }

    /**
     * @return the parametros
     */
    public ArrayList<Simbolo> getParametros() {
        return parametros;
    }

    /**
     * @return the firma
     */
    public String getFirma() {
        return firma;
    }
    
}
