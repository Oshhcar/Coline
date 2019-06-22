/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.entorno;

/**
 *
 * @author oscar
 */
public class Simbolo {

    private Tipo tipo;
    private final String id;
    private Object valor;
    private int tamaño;
    
    public Simbolo(Tipo tipo, String id) {
        this.tipo = tipo;
        this.id = id;
        this.valor = null;
        this.tamaño = 0;
    }

    public Simbolo(Tipo tipo, String id, Object valor) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.tamaño = 0;
    }

    public Simbolo(Tipo tipo, String id, int tamaño) {
        this.tipo = tipo;
        this.id = id;
        this.valor = null;
        this.tamaño = tamaño;
    }
    
    
    
    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the valor
     */
    public Object getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }

    /**
     * @return the tamaño
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * @param tamaño the tamaño to set
     */
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
