/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.expresion.Expresion;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author oscar
 */
public class Println extends Instruccion{
    private final Expresion toPrint;
    
    public Println(Expresion toPrint, int linea, int columna) {
        super(linea, columna);
        this.toPrint = toPrint;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Object valor = toPrint.getValor(e, salida, errores);
        if(valor != null){
            ((JTextArea) salida).append(valor.toString()+"\n");
        }
        return null;
    }
    
}
