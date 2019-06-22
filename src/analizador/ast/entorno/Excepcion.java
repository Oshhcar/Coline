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
public class Excepcion {
    
    private String excepcion;

    public Excepcion(String exception) {
        this.excepcion = exception;
    }
    
    
     /**
     * @return the exception
     */
    public String getExcepcion() {
        return excepcion;
    }

    /**
     * @param exception the exception to set
     */
    public void setExcepcion(String exception) {
        this.excepcion = exception;
    }
    
}
