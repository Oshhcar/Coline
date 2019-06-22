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

    private String id;
    private ArrayList<Expresion> dimensiones;

    public AccesoArreglo(String id, ArrayList<Expresion> dimensiones, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.dimensiones = dimensiones;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        Simbolo s = e.get(getId());
        if (s != null) {
            if (s.getTamaño() > this.getDimensiones().size()) {
                return s.getTipo();
            }
            if (s.getTipo().subtipo != Tipo.type.OBJECT) {
                return new Tipo(s.getTipo().subtipo);
            } else {
                Object val = s.getValor();
                if (val != null) {
                    if (val instanceof Arreglo) {
                        return new Tipo(Tipo.type.OBJECT, ((Arreglo) val).getObjeto());
                    }
                }
            }
        }
        System.err.println("no se econtro el arreglo");
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        Simbolo s = e.get(getId());
        if (s != null) {
            Object val = s.getValor();

            if (val != null) {
                if (val instanceof Arreglo) {
                    Object ret = null;
                    Arreglo aux = (Arreglo) val;

                    int i = 0;
                    while (i < this.getDimensiones().size()) {
                        Expresion exp = this.getDimensiones().get(i++);
                        Tipo tipExp = exp.getTipo(e, salida, this_, errores);
                        if (tipExp.tipo == Tipo.type.INT) {
                            Object valExp = exp.getValor(e, salida, this_, errores);
                            if (valExp != null) {
                                try {
                                    int pos = Integer.valueOf(valExp.toString());
                                    if (pos >= 0) {
                                        ret = aux.get(pos);
                                        if (ret instanceof Arreglo) {
                                            aux = (Arreglo) ret;
                                        }
                                        if (i == this.getDimensiones().size()) {
                                            return ret;
                                        } else {
                                            continue;
                                        }
                                    } else {
                                        System.err.println("Las posiciones deben ser positivas");
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

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the dimensiones
     */
    public ArrayList<Expresion> getDimensiones() {
        return dimensiones;
    }

    /**
     * @param dimensiones the dimensiones to set
     */
    public void setDimensiones(ArrayList<Expresion> dimensiones) {
        this.dimensiones = dimensiones;
    }
}
