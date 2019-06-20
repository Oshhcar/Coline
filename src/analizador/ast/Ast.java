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
        this.getClase().ejecutar(global, salida, false, false, false, null, errores);
        
        ClaseSim claseMain = null;
        
        for(int i = global.getTabla().size()-1; i >= 0; i--){
            Simbolo clase = global.getTabla().get(i);
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

        if(claseMain != null){
            if(claseMain.getMain().getBloque() != null){
                claseMain.getMain().getBloque().ejecutar(claseMain.getE(), salida, true, false, false, null, errores);
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
