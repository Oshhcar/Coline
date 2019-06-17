/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion.ciclos;

import analizador.ErrorC;
import analizador.ast.NodoAst;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import analizador.ast.expresion.Return;
import analizador.ast.instruccion.Bloque;
import analizador.ast.instruccion.Break;
import analizador.ast.instruccion.Continue;
import analizador.ast.instruccion.Instruccion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class For extends Instruccion{
    
    private final Instruccion for_init;
    private final Expresion condicion;
    private final NodoAst update;
    private final Bloque bloque;

    public For(Instruccion for_init, Expresion condicion, NodoAst update, Bloque bloque, int linea, int columna) {
        super(linea, columna);
        this.for_init = for_init;
        this.condicion = condicion;
        this.update = update;
        this.bloque = bloque;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, ArrayList<ErrorC> errores) {
        if(bloque != null){
            Entorno local = new Entorno(e);
            for_init.ejecutar(local, salida, metodo, ciclo, switch_, errores);

            while(true){
                Tipo tipCondicion = condicion.getTipo(local, salida, errores);
                if(tipCondicion != null){
                    if(tipCondicion.tipo == Tipo.type.BOOLEAN){
                        Object valCondicion = condicion.getValor(local, salida, errores);
                        if(valCondicion != null){
                            if(Boolean.valueOf(valCondicion.toString())){
                                Entorno local2 = new Entorno(local);
                                Object obj = bloque.ejecutar(local2, salida, metodo, true, switch_, errores);
                                
                                if(obj != null){
                                    if(obj instanceof Break){
                                        return null;
                                    } else if(obj instanceof Return){
                                        return obj;
                                    }
                                }
                                
                                if(update instanceof Instruccion){
                                    ((Instruccion) update).ejecutar(local, salida, metodo, ciclo, switch_, errores);
                                } else {
                                    ((Expresion) update).getValor(local, salida, errores);
                                }
                                continue;
                            }
                        }
                    } else {
                        System.err.println("Condicion debe ser booleana. for");
                    }
                }
                return null;
            }
        }
        return null;
    }
}
