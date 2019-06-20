/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Arreglo;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Metodo;
import analizador.ast.entorno.Objeto;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import analizador.ast.expresion.Identificador;
import analizador.ast.expresion.LlamadaFuncion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class AccesoObjeto extends Instruccion {

    private final String id;
    private final ArrayList<Expresion> accesos;

    public AccesoObjeto(String id, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.accesos = new ArrayList<>();
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, ArrayList<ErrorC> errores) {
        Simbolo sim = e.get(id);
        if (sim != null) {
            if (sim.getTipo().tipo == Tipo.type.OBJECT) {
                Object obj = sim.getValor();
                if (obj != null) {
                    if (obj instanceof Objeto) {
                        Objeto objeto = (Objeto) obj;

                        Entorno tmp = new Entorno(null);
                        tmp.setPadre(tmp);
                        tmp.setTabla(e.getTabla());

                        tmp.setPadre(e.getPadre());
                        tmp.setGlobal(objeto.getE());

                        for (Expresion acceso : this.accesos) {
                            Tipo tipAcceso = acceso.getTipo(tmp, salida, errores);
                            if (tipAcceso != null) {
                                acceso.getValor(tmp, salida, errores);
                            }
                        }
                    } else {
                        System.err.println("Es arreglo o string o objeto");
                        if (sim.getTipo().objeto.equals("String")) {
                            for (Expresion acceso : this.accesos) {
                                if (acceso instanceof LlamadaFuncion) {
                                    LlamadaFuncion llamada = (LlamadaFuncion) acceso;

                                    switch (llamada.getId()) {
                                        case "toString":
                                            System.out.println("" + obj.toString());
                                    }
                                }
                            }
                        } else {
                            System.err.println("Error fatal acceso objeto.");
                        }
                    }
                } else {
                    System.err.println("No se ha inicializado la variable " + id);
                }
            } else if (sim.getTipo().tipo == Tipo.type.ARRAY) {
                Object obj = sim.getValor();
                if (obj != null) {
                    Arreglo o = (Arreglo) obj;
                    System.out.println("1lehgth es " + o.getTamaño());
                    Object pos0 = o.get(0);
                    if (pos0 instanceof Arreglo) {
                        Arreglo arr = (Arreglo) pos0;
                        System.out.println("2length es " + arr.getTamaño());
                        Object pos1 = arr.get(0);
                        if (pos1 instanceof Arreglo) {
                            Arreglo arr1 = (Arreglo) pos1;
                            System.out.println("3length es " + arr1.getTamaño());
                        }
                    }

                }

            } else {
                System.err.println("La variable no es un objeto ni arreglo");
            }
        } else {
            System.err.println("no se ha declarado una variable " + id);
        }
        return null;
    }

    /**
     * @return the accesos
     */
    public ArrayList<Expresion> getAccesos() {
        return accesos;
    }

}
