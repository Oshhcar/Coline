/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Arreglo;
import analizador.ast.entorno.Entorno;
import analizador.ast.expresion.Expresion;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author oscar
 */
public class Println extends Instruccion {

    private final Expresion toPrint;

    public Println(Expresion toPrint, int linea, int columna) {
        super(linea, columna);
        this.toPrint = toPrint;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        this.debug(e, this_, "Println");

        Object valor = toPrint.getValor(e, salida, this_, errores);
        if (valor != null) {
            if (valor instanceof Arreglo) {
                Arreglo arr = (Arreglo) valor;
                ((JTextArea) salida).append(arr.print() + "\n");
            } else {
                ((JTextArea) salida).append(valor.toString() + "\n");
            }//e.recorrer();
        } else {
            ErrorC error = new ErrorC();
            error.setTipo("Semántico");
            //error.setValor("import");
            error.setDescripcion("Erroes en los parametros.");
            error.setLinea(this.getLinea());
            error.setColumna(this.getColumna());
            errores.add(error);
        }
        return null;
    }

}
