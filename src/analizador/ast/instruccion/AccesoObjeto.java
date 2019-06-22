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
import analizador.ast.expresion.Literal;
import analizador.ast.expresion.LlamadaFuncion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class AccesoObjeto extends Instruccion {

    private final String id;
    private final ArrayList<Expresion> accesos;
    private final ArrayList<Expresion> dimensiones;
    private boolean retSimbolo;

    public AccesoObjeto(String id, ArrayList<Expresion> dimensiones, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.dimensiones = dimensiones;
        this.accesos = new ArrayList<>();
        this.retSimbolo = false;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        this.debug(e, this_, "Acceso");
        
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
                        
                        /*arreglar todo esto*/
                        
                        for (Expresion acceso : this.accesos) {
                            Tipo tipAcceso = acceso.getTipo(objeto.getE(), salida, objeto, errores);
                            if (tipAcceso != null) {
                                Object valor = acceso.getValor(objeto.getE(), salida, objeto, errores);
                                if(valor != null)
                                    return new Literal(tipAcceso, valor, this.getLinea(), this.getColumna());
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
                    if (obj instanceof Arreglo) {

                        Object ret = null;
                        Arreglo aux = (Arreglo) obj;

                        if (this.dimensiones != null) {
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
                                                if (ret instanceof Arreglo) {
                                                    return new Literal(new Tipo(Tipo.type.INT), ((Arreglo) ret).getTamaño(), this.getLinea(), this.getColumna());
                                                }
                                                System.err.println("error dimensiones");
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
                        }
                        return new Literal(new Tipo(Tipo.type.INT), aux.getTamaño(), this.getLinea(), this.getColumna());
                    } else {
                        System.err.println("No es un arreglo. ");
                    }
                }

            } else {
                System.err.println("La variable no es un objeto ni arreglo");
            }
        } else {
            if (this.id.equals("this")) {
                if (this_ != null) {
                    if (this_ instanceof Objeto) {
                        Objeto obj = (Objeto) this_;

                        for (Expresion acceso : this.accesos) {
                            if (!retSimbolo) {
                                if (acceso instanceof Identificador) {
                                    Identificador ident = (Identificador) acceso;
                                    Tipo identTipo = ident.getTipo(obj.getE(), salida, this_, errores);
                                    if (identTipo != null) {
                                        Object valTipo = ident.getValor(obj.getE(), salida, this_, errores);
                                        if (valTipo != null) {
                                            return new Literal(identTipo, valTipo, this.getLinea(), this.getColumna());
                                        }
                                    }
                                }
                            } else {
                                if(acceso instanceof Identificador){
                                    Simbolo ret = obj.getE().get(((Identificador) acceso).getId());
                                    if(ret != null){
                                        return ret;
                                    }
                                    System.err.println("no se encontro el simbolo en this");
                                    return null;
                                }
                            }
                        }
                        System.err.println("ocurrioi un problema this");
                        return null;
                    }
                }
            }
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

    /**
     * @param retSimbolo the retSimbolo to set
     */
    public void setRetSimbolo(boolean retSimbolo) {
        this.retSimbolo = retSimbolo;
    }

}
