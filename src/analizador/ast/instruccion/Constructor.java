/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
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
public class Constructor extends Instruccion{

    private final ArrayList<Modificador> modificadores;
    private final String id;
    public final ArrayList<Simbolo> parametros;
    public final Bloque bloque;

    public Constructor(ArrayList<Modificador> modificadores, String id, ArrayList<Simbolo> parametros, Bloque bloque, int linea, int columna) {
        super(linea, columna);
        this.modificadores = modificadores;
        this.id = id;
        this.parametros = parametros;
        this.bloque = bloque;
    }

    
    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, ArrayList<ErrorC> errores) {
        String firma = this.id;
        
        if (this.parametros != null) {
            for (Simbolo parm : this.parametros) {
                firma += "_" + parm.getTipo().tipo.toString();
            }
        }
        
        if (e.getMetodo(firma) == null) {
            e.add(new Metodo(new Tipo(Tipo.type.CONSTRUCTOR), this.id, firma, this.parametros, this.bloque));
        } else {
            ErrorC error = new ErrorC();
            error.setTipo("Semántico");
            error.setValor(this.id);
            error.setDescripcion("Ya se declaró un constructor con la misma firma.");
            error.setLinea(this.getLinea());
            error.setColumna(this.getColumna());
            errores.add(error);
        }
        
        return null;
    }
    
}
