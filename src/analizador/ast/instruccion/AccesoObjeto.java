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
import analizador.ast.expresion.AccesoArreglo;
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

        Tipo tipoObjeto = null;
        Object valorObjeto = null;
        Object simObjeto = null;

        if (this.id.equals("this") || this.id.equals("super")) {
            valorObjeto = this_;
        } else {
            simObjeto = e.get(id);
            if (simObjeto != null) {
                valorObjeto = ((Simbolo) simObjeto).getValor();

                if (((Simbolo) simObjeto).getTipo().tipo == Tipo.type.ARRAY) {
                    if (this.dimensiones != null) {
                        if (valorObjeto != null) {
                            Object ret = null;
                            Arreglo aux = (Arreglo) valorObjeto;

                            int i = 0;
                            while (i < this.dimensiones.size()) {
                                Expresion exp = this.dimensiones.get(i++);
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
                                                if (i == this.dimensiones.size()) {
                                                    if (!(ret instanceof Arreglo)) {
                                                        valorObjeto = ret;
                                                    } else {
                                                        System.err.println("error, no es un objeto");
                                                        return null;
                                                    }
                                                }
                                            } else {
                                                System.err.println("Error, dimension incorrecta");
                                                return null;
                                            }
                                        } catch (Exception ex) {
                                            System.err.println("" + ex);
                                            return null;
                                        }
                                    }
                                }
                            }

                        } else {
                            System.err.println("error, vacio no está inicializado");
                        }
                    } else {
                        System.err.println("Error, es un arreglo no objeto.");
                    }
                }
            }
        }

        try {
            for (Expresion acceso_0 : this.accesos) {
                if (valorObjeto instanceof Objeto) {
                    Objeto obj = (Objeto) valorObjeto;

                    if (acceso_0 instanceof Identificador) {
                        Identificador thisId = (Identificador) acceso_0;
                        if (!this.id.equals("super")) {
                            tipoObjeto = thisId.getTipo(obj.getE(), salida, obj, errores);
                            valorObjeto = thisId.getValor(obj.getE(), salida, obj, errores);
                            simObjeto = obj.getE().get(thisId.getId());
                        } else {
                            tipoObjeto = thisId.getTipo(obj.getE().getPadre(), salida, obj, errores);
                            valorObjeto = thisId.getValor(obj.getE().getPadre(), salida, obj, errores);
                            simObjeto = obj.getE().getPadre().get(thisId.getId());
                        }
                    } else if (acceso_0 instanceof AccesoArreglo) {
                        AccesoArreglo thisAcceso = (AccesoArreglo) acceso_0;

                        if (thisAcceso.getDimensiones() != null) {
                            ArrayList<Expresion> dimForm = new ArrayList<>();
                            
                            int i = 0;
                            while (i < thisAcceso.getDimensiones().size()) {
                                Expresion exp = thisAcceso.getDimensiones().get(i++);
                                Tipo tipExp = exp.getTipo(e, salida, this_, errores);
                                if (tipExp.tipo == Tipo.type.INT) {
                                    Object valExp = exp.getValor(e, salida, this_, errores);
                                    if (valExp != null) {
                                        dimForm.add(new Literal(tipExp, valExp, this.getLinea(), this.getColumna()));
                                        continue;
                                    }
                                }
                                System.err.println("error en posisiciones dim");
                                return null;
                            }
                            thisAcceso.setDimensiones(dimForm);
                        }

                        if (!this.id.equals("super")) {
                            tipoObjeto = thisAcceso.getTipo(obj.getE(), salida, obj, errores);
                            valorObjeto = thisAcceso.getValor(obj.getE(), salida, obj, errores);
                            simObjeto = new AsignacionArreglo(thisAcceso.getId(), thisAcceso.getDimensiones(), obj.getE(), this.getLinea(), this.getColumna());
                        } else {
                            tipoObjeto = thisAcceso.getTipo(obj.getE().getPadre(), salida, obj, errores);
                            valorObjeto = thisAcceso.getValor(obj.getE().getPadre(), salida, obj, errores);
                            simObjeto = new AsignacionArreglo(thisAcceso.getId(), thisAcceso.getDimensiones(), obj.getE().getPadre(), this.getLinea(), this.getColumna());
                        }

                    } else {
                        /*LLamada metodo*/
                        LlamadaFuncion thisFuncion = ((LlamadaFuncion) acceso_0);

                        if (thisFuncion.getParametros() != null) {
                            ArrayList<Expresion> parmForm = new ArrayList<>();
                            for (Expresion parametro : thisFuncion.getParametros()) {
                                Tipo tipoParm = parametro.getTipo(e, salida, this_, errores);
                                if (tipoParm != null) {
                                    Object valorParm = parametro.getValor(e, salida, this_, errores);
                                    if (valorParm != null) {
                                        parmForm.add(new Literal(tipoParm, valorParm, this.getLinea(), this.getColumna()));
                                        continue;
                                    }
                                }
                                System.err.println("error en parametros");
                                return null;
                            }
                            thisFuncion.setParametros(parmForm);
                        }

                        if (!this.id.equals("super")) {
                            tipoObjeto = thisFuncion.getTipo(obj.getE(), salida, obj, errores);
                            valorObjeto = thisFuncion.getValor(obj.getE(), salida, obj, errores);
                        } else {
                            Entorno tmp = obj.getE().getGlobal();
                            obj.getE().setGlobal(obj.getE().getPadre());
                            tipoObjeto = thisFuncion.getTipo(obj.getE(), salida, obj, errores);
                            valorObjeto = thisFuncion.getValor(obj.getE(), salida, obj, errores);
                            obj.getE().setGlobal(tmp);
                        }

                    }
                } else {
                    System.err.println("no se ha instanciado el objeto " + id);
                    return null;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error fatal acceso arreglos");
        }

        if (retSimbolo) {
            return simObjeto;
        } else {
            return new Literal(tipoObjeto, valorObjeto, this.getLinea(), this.getColumna());
        }
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
