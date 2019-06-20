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

    private int dim;
    private String id;

    public Identificador(String id, int linea, int columna) {
        super(linea, columna);
        this.dim = 0;
        this.id = id;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        Simbolo tmp = e.get(getId());
        if (tmp != null) {
            return tmp.getTipo();
        }
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        Simbolo tmp = e.get(getId());
        if (tmp != null) {
            return tmp.getValor();
        }
        return null;
    }

    /**
     * @return the dim
     */
    public int getDim() {
        return dim;
    }

    /**
     * @param dim the dimesiones to set
     */
    public void setDim(int dim) {
        this.dim = dim;
    }

    public void addDim() {
        this.dim++;
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
