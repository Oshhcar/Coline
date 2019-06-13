/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion.condicionales;

import analizador.ErrorC;
import analizador.ast.NodoAst;
import analizador.ast.entorno.Entorno;
import analizador.ast.instruccion.Instruccion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class If extends Instruccion {

    private final ArrayList<SubIf> subIfs;

    public If(ArrayList<SubIf> subIfs, int linea, int columna) {
        super(linea, columna);
        this.subIfs = subIfs;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        for (SubIf if_ : this.subIfs) {
            Object r = if_.ejecutar(e, salida, errores);
            if (r != null)
                return r;
            
            if(if_.isEntra())
                return null;
        }
        return null;
    }

}
