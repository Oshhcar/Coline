/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.entorno;

import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class ClaseSim extends Simbolo{
    
    private final ArrayList<Modificador> modificadores;
    private Entorno e;
    private Metodo main;/*Agregar constructores*/
    private ClaseSim padre;
    private ArrayList<ClaseSim> padres; 
    
    
    public ClaseSim(ArrayList<Modificador> modificadores,String id, Entorno e) {
        super(new Tipo(Tipo.type.CLASS), id);
        this.modificadores = modificadores;
        this.e = e;
        this.main = null;
    }

    /**
     * @return the main
     */
    public Metodo getMain() {
        return main;
    }

    /**
     * @param main the main to set
     */
    public void setMain(Metodo main) {
        this.main = main;
    }

    /**
     * @return the padre
     */
    public ClaseSim getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(ClaseSim padre) {
        this.padre = padre;
    }

    /**
     * @return the padres
     */
    public ArrayList<ClaseSim> getPadres() {
        return padres;
    }

    /**
     * @param padres the padres to set
     */
    public void setPadres(ArrayList<ClaseSim> padres) {
        this.padres = padres;
    }

    /**
     * @return the e
     */
    public Entorno getE() {
        return e;
    }

    /**
     * @param e the e to set
     */
    public void setE(Entorno e) {
        this.e = e;
    }
    
}
