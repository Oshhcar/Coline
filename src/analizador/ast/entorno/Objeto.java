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
public class Objeto {

    private ClaseSim clase; 
    private Entorno e;
    private ArrayList<Objeto> heredadas;
    
    public Objeto(ClaseSim clase, Entorno e) {
        this.clase = clase;
        this.e = e;
        this.heredadas = new ArrayList<>();
    }
    
    /**
     * @return the clase
     */
    public ClaseSim getClase() {
        return clase;
    }

    /**
     * @param clase the clase to set
     */
    public void setClase(ClaseSim clase) {
        this.clase = clase;
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

    /**
     * @return the heredadas
     */
    public ArrayList<Objeto> getHeredadas() {
        return heredadas;
    }

    /**
     * @param heredadas the heredadas to set
     */
    public void setHeredadas(ArrayList<Objeto> heredadas) {
        this.heredadas = heredadas;
    }

}
