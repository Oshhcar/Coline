/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.instruccion.Clase;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Ast {
    private final Clase clase;
    
    public Ast(Clase clase) {
        this.clase = clase;
    }
    
    public void ejecutar(Object salida, ArrayList<ErrorC> errores) {
        Entorno global = new Entorno(null);
        this.clase.ejecutar(global, salida, errores);
    }
    
}
