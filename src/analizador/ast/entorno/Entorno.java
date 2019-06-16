/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.entorno;

import java.util.ArrayList;
/**
 *
 * @author oscar
 */
public class Entorno {

    private final Entorno padre;
    private Entorno global;
    private final ArrayList<Simbolo> tabla;

    public Entorno(Entorno padre) {
        this.padre = padre;
        if(padre != null){
            global = padre.global;
        }
        this.tabla = new ArrayList<>();
    }

    public void add(Simbolo sim) {
        this.getTabla().add(sim);
    }

    public Simbolo getLocal(String id) {
        for (int i = this.getTabla().size() - 1; i >= 0; i--) {
            Simbolo s = this.getTabla().get(i);
            if (s.getId().equals(id)) {
                if (!(s instanceof Metodo)) {
                    return s;
                }
            }
        }
        return null;
    }

    public Simbolo get(String id) {
        Entorno actual = this;

        while (actual != null) {
            for (int i = actual.getTabla().size() - 1; i >= 0; i--) {
                Simbolo s = actual.getTabla().get(i);
                if (s.getId().equals(id)) {
                    if (!(s instanceof Metodo)) {
                        return s;
                    }
                }
            }
            actual = actual.padre;
        }
        return null;
    }

    public Metodo getMetodo(String firma) {
        for (int i = getGlobal().getTabla().size() - 1; i >= 0; i--) {
            Simbolo s = getGlobal().getTabla().get(i);
            if(s instanceof Metodo){
                Metodo m = (Metodo) s;
                if(m.getFirma().equals(firma)){
                    return m;
                }
            }
        }
        return null;
    }

    public void recorrer() {
        Entorno actual = this;
        int i = 0;
        while (actual != null) {
            System.out.println("Entorno " + i++);
            actual.getTabla().forEach(s -> {
                System.out.print(s.getId() + " : " + s.getTipo().toString());
                if (s.getTipo() == Tipo.OBJECT) {
                    System.out.print(":" + s.getTipo().getObject());
                }
                if (s.getValor() != null) {
                    System.out.println(" -> " + s.getValor());
                } else {
                    System.out.println("");
                }
            });
            actual = actual.padre;
        }
    }

    /**
     * @return the tabla
     */
    public ArrayList<Simbolo> getTabla() {
        return tabla;
    }

    /**
     * @param global the global to set
     */
    public void setGlobal(Entorno global) {
        this.global = global;
    }

    /**
     * @return the global
     */
    public Entorno getGlobal() {
        return global;
    }

}
