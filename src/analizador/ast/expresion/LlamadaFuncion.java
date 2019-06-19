/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion;

import analizador.ErrorC;
import analizador.ast.NodoAst;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Metodo;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import analizador.ast.instruccion.Instruccion;
import analizador.ast.instruccion.LlamadaMetodo;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class LlamadaFuncion extends Expresion {

    private final LlamadaMetodo funcion;
    private Object valor;
    private Tipo tipo;
    
    public LlamadaFuncion(LlamadaMetodo funcion) {
        super(funcion.getLinea(), funcion.getColumna());
        this.funcion = funcion;
        this.funcion.setFuncion(true);
        this.valor = null;
        this.tipo = null;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Object obj = getFuncion().ejecutar(e, salida, true, false, false, errores);
        if (obj != null) {
            valor = ((Expresion) obj).getValor(e, salida, errores);
            return ((Expresion) obj).getTipo(e, salida, errores);
        }
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        if(valor != null)
            return valor;
        
        Object obj = getFuncion().ejecutar(e, salida, true, false, false, errores);
        if (obj != null) {
            tipo = ((Expresion) obj).getTipo(e, salida, errores);
            return ((Expresion) obj).getValor(e, salida, errores);
        }
        return null;
        
    }

    /**
     * @return the funcion
     */
    public LlamadaMetodo getFuncion() {
        return funcion;
    }
    
        /**
     * @return the id
     */
    public String getId() {
        return funcion.getId();
    }

    /**
     * @return the parametros
     */
    public ArrayList<Expresion> getParametros() {
        return funcion.getParametros();
    }
}
