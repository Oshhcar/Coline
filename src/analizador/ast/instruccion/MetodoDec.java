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
public class MetodoDec extends Instruccion {

    private final ArrayList<Modificador> modificadores;
    private final Tipo tipo;
    private final int dimensiones;
    private final String id;
    public final ArrayList<Simbolo> parametros;
    public final Bloque bloque;

    public MetodoDec(ArrayList<Modificador> modificadores, Tipo tipo, int dimensiones, String id, ArrayList<Simbolo> parametros, Bloque bloque, int linea, int columna) {
        super(linea, columna);
        this.modificadores = modificadores;
        this.tipo = tipo;
        this.dimensiones = dimensiones;
        this.id = id;
        this.parametros = parametros;
        this.bloque = bloque;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        String firma = this.id;

        if (this.parametros != null) {
            for (Simbolo parm : this.parametros) {
                firma += "_" + parm.getTipo().tipo.toString();
            }
        }

        if (e.getMetodo(firma) == null) {
            if(this.dimensiones != 0){
                Tipo t = new Tipo(Tipo.type.ARRAY);
                t.subtipo = this.tipo.tipo;
                Metodo m = new Metodo(t, this.id, firma, this.parametros, this.bloque);
                m.setTamaño(dimensiones);
                e.add(m);
            } else {
                e.add(new Metodo(this.tipo, this.id, firma, this.parametros, this.bloque));
            }
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
