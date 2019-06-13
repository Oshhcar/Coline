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
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class MetodoDec extends Instruccion{
    private final ArrayList<Modificador> modificadores;
    private final Tipo tipo;
    private final String id;
    public final ArrayList<Simbolo> parametros;
    public final Bloque bloque;

    public MetodoDec(ArrayList<Modificador> modificadores, Tipo tipo, String id, ArrayList<Simbolo> parametros, Bloque bloque, int linea, int columna) {
        super(linea, columna);
        this.modificadores = modificadores;
        this.tipo = tipo;
        this.id = id;
        this.parametros = parametros;
        this.bloque = bloque;
    }
    
    @Override
    public Object ejecutar(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        if(e.getMetodo(this.id, this.parametros) == null){
            e.add(new Metodo(this.tipo, this.id, this.parametros, this.bloque));
        } else {
            ErrorC error = new ErrorC();
            error.setTipo("Semántico");
            error.setValor(this.id);
            error.setDescripcion("Ya se declaró un metodo con el mismo id.");
            error.setLinea(this.getLinea());
            error.setColumna(this.getColumna());
            errores.add(error);
        }
        
        return null;
    }

}
