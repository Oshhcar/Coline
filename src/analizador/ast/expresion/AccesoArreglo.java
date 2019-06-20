/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion;

import analizador.ErrorC;
import analizador.ast.entorno.Arreglo;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class AccesoArreglo extends Expresion {

    private final String id;
    private final ArrayList<Expresion> dimensiones;

    public AccesoArreglo(String id, ArrayList<Expresion> dimensiones, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.dimensiones = dimensiones;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        Simbolo s = e.get(id);
        if (s != null) {
            if(s.getTamaño() > this.dimensiones.size()){
                return s.getTipo();
            }
            return new Tipo(s.getTipo().subtipo);
        }
        System.err.println("no se econtro el arreglo");
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        Simbolo s = e.get(id);
        if (s != null) {
            Object val = s.getValor();

            if (val != null) {
                if (val instanceof Arreglo) {
                    Object ret = null;
                    Arreglo aux = (Arreglo) val;

                    int i = 0;
                    while (i < this.dimensiones.size()) {
                        Expresion exp = this.dimensiones.get(i++);
                        Tipo tipExp = exp.getTipo(e, salida, this_, errores);
                        if (tipExp.tipo == Tipo.type.INT) {
                            Object valExp = exp.getValor(e, salida, this_, errores);
                            if (valExp != null) {
                                try {
                                    int pos = Integer.valueOf(valExp.toString());
                                    ret = aux.get(pos);
                                    if (ret instanceof Arreglo) {
                                        aux = (Arreglo) ret;
                                    }
                                    if (i == this.dimensiones.size()) {
                                        return ret;
                                    } else {
                                        continue;
                                    }
                                } catch (Exception ex) {
                                    System.err.println("" + ex);
                                }
                            }
                        }
                        return null;
                    }
                } else {
                    System.err.println("no es un arreglo");
                }
            }
            System.err.println("no se ha inicializado el arreglo");
            return null;
        }
        System.err.println("no se encontro el simbolo arreglo");
        return null;
    }
}
