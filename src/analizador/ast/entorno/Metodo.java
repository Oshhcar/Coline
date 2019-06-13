/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.entorno;

import analizador.ast.NodoAst;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Metodo extends Simbolo{
    private final ArrayList<Simbolo> parametros;
    private final ArrayList<NodoAst> bloques;

    public Metodo(Tipo tipo, String id, ArrayList<Simbolo> parametros, ArrayList<NodoAst> bloques) {
        super(tipo, id);
        this.parametros = parametros;
        this.bloques = bloques;
    }

    /**
     * @return the bloques
     */
    public ArrayList<NodoAst> getBloques() {
        return bloques;
    }

    /**
     * @return the parametros
     */
    public ArrayList<Simbolo> getParametros() {
        return parametros;
    }
    
}
