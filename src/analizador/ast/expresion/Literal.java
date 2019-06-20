/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion;

import analizador.ErrorC;
import analizador.ast.entorno.Arreglo;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Literal extends Expresion {

    private Tipo tipo;
    private final Object valor;
    private final ArrayList<Expresion> dimensiones;
    private final ArrayList<Expresion> valores;

    public Literal(Tipo tipo, Object valor, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.valor = valor;
        this.dimensiones = null;
        this.valores = null;
    }

    public Literal(Tipo tipo, ArrayList<Expresion> dimensiones, int linea, int columna) {
        super(linea, columna);
        Tipo t = new Tipo(Tipo.type.ARRAY);
        t.subtipo = tipo.tipo;
        this.tipo = t;
        this.valor = null;
        this.dimensiones = dimensiones;
        this.valores = null;
    }

    public Literal(ArrayList<Expresion> valores, int linea, int columna) {
        super(linea, columna);
        this.tipo = new Tipo(Tipo.type.ARRAY);
        this.valor = null;
        this.dimensiones = null;
        this.valores = valores;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        return this.tipo;
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        if (this.dimensiones != null) {
            /*new TYPE[num]*/
           
            Arreglo aux = null;

            int i = this.dimensiones.size() - 1;
            while (i >= 0) {
                Expresion exp = this.dimensiones.get(i);
                Tipo tipExp = exp.getTipo(e, salida, this_, errores);
                if (tipExp != null) {
                    if (tipExp.tipo == Tipo.type.INT) {
                        Object valExp = exp.getValor(e, salida, this_, errores);
                        if (valExp != null) {
                            try {
                                int tamExp = Integer.valueOf(valExp.toString());
                                if (i == this.dimensiones.size() - 1) {
                                    aux = new Arreglo(this.tipo);
                                    aux.setTamaño(tamExp);
                                    aux.inicializar();
                                } else {
                                    Arreglo padre = new Arreglo(this.tipo);
                                    padre.setTamaño(tamExp);
                                    padre.inicializar(aux);
                                    aux = padre;
                                }
                                i--;
                                continue;
                            } catch (Exception ex) {
                                System.err.println("" + ex.toString());
                                return null;
                            }
                        }
                    }
                    System.err.println("las dimensiones deben ser enteras;");
                }
                return null;
            }
            if (aux != null) {
                aux.setDimensiones(this.dimensiones.size());
            }
            return aux;
        } else if (this.valores != null) {
            Tipo tmp = null;
            Arreglo array = new Arreglo(this.tipo);
            //this.valores.size() tamaño de primera dimension;
            array.setTamaño(this.valores.size());
            boolean multi = false;
            int dimension = 1;
            
            for (int i = 0; i <= this.valores.size() - 1; i++) {
                Expresion exp = this.valores.get(i);
                Tipo tipExp = exp.getTipo(e, salida, this_, errores);
                if (tipExp != null) {
                    
                    if(this.tipo.subtipo == null){
                        this.tipo.subtipo = tipExp.tipo;
                        tmp = tipExp;
                    }
                    
                    if (tipExp.tipo == this.tipo.subtipo) {
                        Object valExp = exp.getValor(e, salida, this_, errores);
                        if (valExp != null) {
                            if (valExp instanceof Arreglo) {
                                multi = true;
                                dimension = ((Arreglo) valExp).getDimensiones();
                                tmp = ((Arreglo) valExp).getTipo();
                            }
                            array.addValor(i, valExp);
                            continue;
                        }
                        System.err.println("Error en valores de array");
                        return null;
                    } else {
                        System.err.println("los valores no son del mismo tipo");
                        return null;
                    }
                }
                System.err.println("error en valores de dimensiones");
                return null;
            }

            if (multi) {
                dimension++;
            }
            array.setDimensiones(dimension);
            if(tmp.subtipo != null)
                this.tipo.subtipo = tmp.subtipo;
            array.getTipo().subtipo = this.tipo.subtipo;
            return array;
            
            /*array vacio {}*/
        } else {
            return this.valor;
        }
    }

    /**
     * @return the dimensiones
     */
    public ArrayList<Expresion> getDimensiones() {
        return dimensiones;
    }

}
