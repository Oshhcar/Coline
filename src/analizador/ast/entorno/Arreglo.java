/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.entorno;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author oscar
 */
public class Arreglo {
    private final Tipo tipo;
    private int tamaño;
    private int dimensiones;
    private Map<Integer, Object> arreglo;

    public Arreglo(Tipo tipo) {
        this.tipo = tipo;
        this.tamaño = 0;
        this.arreglo = new HashMap<>();
        this.dimensiones = 1;
    }

    public Object get(int posicion) {
        /*verificar tamaño*/
        return this.arreglo.get(posicion);
    }

    /**
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @return the tamaño
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * @param tamaño the tamaño to set
     */
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public void inicializar() {
        Object valor = new Object();

        if (this.tipo.subtipo == Tipo.type.INT) {
            valor = 0;
        } else if (this.tipo.subtipo == Tipo.type.DOUBLE) {
            valor = 0.0;
        } else if(this.tipo.subtipo == Tipo.type.CHAR){
            valor = '\0';
        } else {
            System.err.println("no es ninguno iniciaizar, arreglo");
        }

        for (int i = 0; i < tamaño; i++) {
            arreglo.put(i, valor);
        }
    }

    public void inicializar(Object valor) {
        for (int i = 0; i < tamaño; i++) {
            if (valor instanceof Arreglo) {
                Arreglo vArreglo = (Arreglo) valor;
                Arreglo nuevo = new Arreglo(this.tipo);
                nuevo.arreglo.putAll(vArreglo.arreglo);
                nuevo.dimensiones = vArreglo.dimensiones;
                nuevo.tamaño = vArreglo.tamaño;
                
                arreglo.put(i, nuevo);
            } else {
                arreglo.put(i, valor);
            }
        }
    }

    public void addValor(int i, Object valor) {
        arreglo.put(i, valor);
    }

    public void setValor(int i, Object valor) {
        arreglo.replace(i, valor);
    }

    public String print() {
        String cad = "";

        if (this.tamaño > 0) {
            cad += "{";
            for (int i = 0; i < this.tamaño; i++) {
                Object tmp = this.arreglo.get(i);
                if (tmp instanceof Arreglo) {
                    cad += ((Arreglo) tmp).print();
                } else {
                    cad += tmp.toString();
                }
                if (i + 1 != this.tamaño) {
                    cad += ",";
                }
            }
            cad += "}";
        }

        return cad;
    }

    /**
     * @return the dimensiones
     */
    public int getDimensiones() {
        return dimensiones;
    }

    /**
     * @param dimensiones the dimensiones to set
     */
    public void setDimensiones(int dimensiones) {
        this.dimensiones = dimensiones;
    }
}
