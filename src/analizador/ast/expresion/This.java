/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Objeto;
import analizador.ast.entorno.Tipo;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class This extends Expresion{

    public This(int linea, int columna) {
        super(linea, columna);
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        if(this_ != null){
            if(this_ instanceof Objeto){
                Objeto t = (Objeto) this_;
                Tipo tipo = new Tipo(Tipo.type.OBJECT);
                tipo.objeto = t.getClase().getId();
                return tipo;
            }
        }
        System.err.println("No se ha instanciado el objeto. ");
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        if(this_ != null){
            if(this_ instanceof Objeto){
                return this_;
            }
        }
        System.err.println("No se ha instanciado el objeto. ");
        return null;
    }
    
}
