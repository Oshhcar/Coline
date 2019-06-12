/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.NodoAst;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Modificador;
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
    public final ArrayList<NodoAst> bloques;

    public MetodoDec(ArrayList<Modificador> modificadores, Tipo tipo, String id, ArrayList<NodoAst> bloques, int linea, int columna) {
        super(linea, columna);
        this.modificadores = modificadores;
        this.tipo = tipo;
        this.id = id;
        this.bloques = bloques;
    }
    
    @Override
    public Object ejecutar(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        return null;
    }

}
