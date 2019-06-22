/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion.condicionales;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import analizador.ast.instruccion.Break;
import analizador.ast.instruccion.Instruccion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Switch extends Instruccion {

    private final Expresion expSwitch;
    private final ArrayList<Case> cases;

    public Switch(Expresion expSwitch, ArrayList<Case> cases, int linea, int columna) {
        super(linea, columna);
        this.expSwitch = expSwitch;
        this.cases = cases;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        Tipo tipExp = this.expSwitch.getTipo(e, salida, this_, errores);
        if (tipExp != null) {
            Object valExp = this.expSwitch.getValor(e, salida, this_, errores);
            if (valExp != null) {
                if (cases != null) {
                    Entorno local = new Entorno(e);
                    boolean isContinuar = false;
                    
                    for (Case caso : cases) {
                        caso.setExpSwitch(tipExp, valExp);
                        caso.setContinuar(isContinuar);
                        
                        Object obj = caso.ejecutar(e, salida, metodo, ciclo, true, this_, errores);
                        
                        if(obj != null){
                            if(obj instanceof Break){
                                return null;
                            } else {
                                return obj;
                            }
                        }
                        
                        isContinuar = caso.isContinuar();
                    }
                }
            } else {
                System.err.println("Error valor. Switch");
            }
        }
        return null;
    }

}
