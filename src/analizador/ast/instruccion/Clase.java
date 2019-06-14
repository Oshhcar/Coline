/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.NodoAst;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Metodo;
import analizador.ast.entorno.Modificador;
import analizador.ast.expresion.Expresion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Clase extends Instruccion {

    private final ArrayList<Modificador> modificadores;
    private final String id;
    private final String extiende;
    private final ArrayList<NodoAst> declaraciones;

    public Clase(ArrayList<Modificador> modificadores, String id, ArrayList<NodoAst> declaraciones, int linea, int columna) {
        super(linea, columna);
        this.modificadores = modificadores;
        this.id = id;
        this.extiende = null;
        this.declaraciones = declaraciones;
    }

    public Clase(ArrayList<Modificador> modificadores, String id, String extiende, ArrayList<NodoAst> declaraciones, int linea, int columna) {
        super(linea, columna);
        this.modificadores = modificadores;
        this.id = id;
        this.extiende = extiende;
        this.declaraciones = declaraciones;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, ArrayList<ErrorC> errores) {
        if (this.declaraciones != null) {
            Entorno local = new Entorno(e);

            for (NodoAst inst : this.declaraciones) {
                if (inst instanceof Instruccion) {
                    ((Instruccion) inst).ejecutar(local, salida, metodo, ciclo, switch_, errores);
                }
            }
            
            Metodo main = local.getMetodo("main", null);
            if(main != null){
                for(NodoAst bloque: main.getBloque().getBloques()){
                    if(bloque instanceof Instruccion){
                        ((Instruccion) bloque).ejecutar(local, salida, metodo, ciclo, switch_, errores);
                    } else {
                        ((Expresion) bloque).getValor(local, salida, errores);
                    }
                }
            }
            
            
        }
        return null;
    }
}
