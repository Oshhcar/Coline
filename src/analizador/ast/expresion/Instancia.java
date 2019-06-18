/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion;

import analizador.ErrorC;
import analizador.ast.entorno.ClaseSim;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Metodo;
import analizador.ast.entorno.Objeto;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Instancia extends Expresion {

    private final String id;
    private final ArrayList<Expresion> parametros;

    public Instancia(String id, ArrayList<Expresion> parametros, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        return new Tipo(Tipo.type.OBJECT, id);
    }

    @Override
    public Object getValor(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        ClaseSim clase = e.getClase(id);
        if (clase != null) {
            Entorno eNuevo = new Entorno(clase.getE().getPadre());
            eNuevo.setGlobal(eNuevo);
            
            for(Simbolo sim: clase.getE().getTabla()){
                if(sim instanceof Metodo){
                    Metodo m = (Metodo)sim;
                    eNuevo.add(new Metodo(m.getTipo(), m.getId(), m.getFirma(), m.getParametros(), m.getBloque()));
                } else {
                    eNuevo.add(new Simbolo(sim.getTipo(), sim.getId(), sim.getValor()));
                }
            }
            
            //eNuevo.recorrer();
            
            Objeto obj = new Objeto(id, eNuevo);
            return obj;
        } else {
            System.err.println("No se ha importado la clase: " + id);
        }
        return null;
    }

}
