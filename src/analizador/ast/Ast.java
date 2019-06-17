/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast;

import analizador.ErrorC;
import analizador.ast.entorno.ClaseSim;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Metodo;
import analizador.ast.entorno.Simbolo;
import analizador.ast.instruccion.Clase;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Ast {
    private final Clase clase;
    
    public Ast(Clase clase) {
        this.clase = clase;
    }
    
    public void ejecutar(Object salida, ArrayList<ErrorC> errores, String dirActual) {
        Entorno global = new Entorno(null);
        this.getClase().setDirActual(dirActual);
        this.getClase().ejecutar(global, salida, false, false, false, errores);
        
        ClaseSim claseMain = null;
        
        for(Simbolo clase: global.getTabla()){
            if(clase instanceof ClaseSim){
                ClaseSim c = (ClaseSim) clase;
                if(c.getMain() != null){
                    if(claseMain == null){
                        claseMain = c;
                        continue;
                    }
                    System.err.println("Error, se ha encontrado mas de un main.");
                }
            }
        }
        //global.recorrer();
        if(claseMain != null){
            if(claseMain.getMain().getBloque() != null){
                Entorno local = new Entorno(global);
                local.setGlobal(local);
                local.getTabla().addAll(claseMain.getSimbolos());
                
                claseMain.getMain().getBloque().ejecutar(local, salida, true, false, false, errores);
            }
        }
    }

    /**
     * @return the clase
     */
    public Clase getClase() {
        return clase;
    }
    
}
