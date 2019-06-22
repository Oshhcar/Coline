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
public class Continue extends Instruccion{

    public Continue(int linea, int columna) {
        super(linea, columna);
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        this.debug(e, this_, "continue");
        
        return this;
    }
    
}
