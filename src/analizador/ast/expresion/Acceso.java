/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import analizador.ast.instruccion.AccesoObjeto;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Acceso extends Expresion {

    private final AccesoObjeto acceso;
    private Tipo tipo;
    private Object valor;

    public Acceso(AccesoObjeto acceso) {
        super(acceso.getLinea(), acceso.getColumna());
        this.acceso = acceso;
        this.tipo = null;
        this.valor = null;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Object obj = acceso.ejecutar(e, salida, false, false, false, errores);
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
        
        Object obj = acceso.ejecutar(e, salida, false, false, false, errores);
        if (obj != null) {
            tipo = ((Expresion) obj).getTipo(e, salida, errores);
            return ((Expresion) obj).getValor(e, salida, errores);
        }
        return null;    }

}
