/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Identificador extends Expresion {

    private int dimesiones;
    private String id;

    public Identificador(String id, int linea, int columna) {
        super(linea, columna);
        this.dimesiones = 0;
        this.id = id;
    }

    @Override
    public Object getValor(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Simbolo tmp = e.get(getId());
        if(tmp != null){
            return tmp.getValor();
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno e, ArrayList<ErrorC> errores) {
        Simbolo tmp = e.get(getId());
        if(tmp != null){
            return tmp.getTipo();
        }
        return null;
    }

    /**
     * @return the dimesiones
     */
    public int getDimesiones() {
        return dimesiones;
    }

    /**
     * @param dimesiones the dimesiones to set
     */
    public void setDimesiones(int dimesiones) {
        this.dimesiones = dimesiones;
    }

    public void addDimension(){
        this.dimesiones++;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

}
