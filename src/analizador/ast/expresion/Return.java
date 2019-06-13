/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Return extends Expresion{
    private final Expresion toReturn;

    public Return(Expresion toReturn, int linea, int columna) {
        super(linea, columna);
        this.toReturn = toReturn;
    }

    public Return(int linea, int columna) {
        super(linea, columna);
        this.toReturn = null;
    }

    
    @Override
    public Tipo getTipo(Entorno e,  Object salida, ArrayList<ErrorC> errores) {
        if(toReturn != null){
            return toReturn.getTipo(e, salida, errores);
        }
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        if(toReturn != null){
            return toReturn.getValor(e, salida, errores);
        }
        return null;
    }
    
}
