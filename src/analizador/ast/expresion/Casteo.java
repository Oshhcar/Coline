/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion;

import analizador.ErrorC;
import analizador.ast.entorno.ClaseSim;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Metodo;
import analizador.ast.entorno.Objeto;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Casteo extends Expresion {

    private final Tipo tipo;
    private final Expresion expresion;

    public Casteo(Tipo tipo, Expresion expresion, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.expresion = expresion;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        return tipo;
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        if (this.tipo.tipo != Tipo.type.BOOLEAN) {
            Tipo tipExp = this.expresion.getTipo(e, salida, this_, errores);
            if (tipExp != null) {
                if (tipExp.tipo != Tipo.type.BOOLEAN) {
                    if (this.tipo.tipo == Tipo.type.DOUBLE) {
                        return getDouble(tipExp, this.expresion.getValor(e, salida, this_, errores));
                    } else if (this.tipo.tipo == Tipo.type.INT) {
                        return getInt(tipExp, this.expresion.getValor(e, salida, this_, errores));
                    } else if (this.tipo.tipo == Tipo.type.CHAR) {
                        return getChar(tipExp, this.expresion.getValor(e, salida, this_, errores));
                    } else if (this.tipo.tipo == Tipo.type.OBJECT) {
                        //System.out.println("Casteara "+tipExp.objeto +" a "+this.tipo.objeto);
                        if (tipExp.tipo == Tipo.type.OBJECT) {
                            Object valor = this.expresion.getValor(e, salida, this_, errores);
                            if (valor instanceof Objeto) {
                                Objeto obj = (Objeto) valor;

                                Objeto cast = null;

                                for (Objeto h : obj.getHeredadas()) {
                                    //System.out.println(h.getClase().getId()+" == "+this.tipo.objeto);
                                    if (h.getClase().getId().equals(this.tipo.objeto)) {
                                        cast = h;
                                        break;
                                    }
                                }

                                if (cast != null) {
                                    Entorno eNuevo = new Entorno(cast.getClase().getE().getPadre());
                                    eNuevo.setGlobal(eNuevo);
                                    
                                    /*Preparo el entorno con los simbolos de la clase*/
                                    for (Simbolo sim : cast.getClase().getE().getTabla()) {
                                        if (sim instanceof Metodo) {
                                            Metodo m = (Metodo) sim;
                                            Metodo nuevo = new Metodo(m.getTipo(), m.getId(), m.getFirma(), m.getParametros(), m.getBloque());
                                            nuevo.setTamaño(m.getTamaño());
                                            eNuevo.add(nuevo);
                                        } else if (sim instanceof ClaseSim) {
                                            ClaseSim c = (ClaseSim) sim;
                                            ClaseSim nuevo = new ClaseSim(c.getModificadores(), c.getConstructores(), c.getId(), c.getE());
                                            nuevo.setMain(c.getMain());
                                            nuevo.setPadre(c.getPadre());
                                            eNuevo.add(nuevo);
                                        } else {
                                            Simbolo nuevo = new Simbolo(sim.getTipo(), sim.getId(), sim.getValor());
                                            nuevo.setTamaño(sim.getTamaño());
                                            eNuevo.add(nuevo);
                                        }
                                    }

                                    if (cast.getClase().getPadre() != null) {
                                        ClaseSim clasePadre = cast.getClase().getPadre();

                                        Entorno ePadre = new Entorno(null);
                                        Entorno aux = null;

                                        while (clasePadre != null) {

                                            if (aux != null) {
                                                Entorno nuevo = new Entorno(null);

                                                for (Simbolo sim : clasePadre.getE().getTabla()) {
                                                    if (sim instanceof Metodo) {
                                                        Metodo m = (Metodo) sim;
                                                        Metodo mNuevo = new Metodo(m.getTipo(), m.getId(), m.getFirma(), m.getParametros(), m.getBloque());
                                                        mNuevo.setTamaño(m.getTamaño());
                                                        nuevo.add(mNuevo);
                                                    } else if (sim instanceof ClaseSim) {
                                                        ClaseSim c = (ClaseSim) sim;
                                                        ClaseSim cNuevo = new ClaseSim(c.getModificadores(), c.getConstructores(), c.getId(), c.getE());
                                                        cNuevo.setMain(c.getMain());
                                                        cNuevo.setPadre(c.getPadre());
                                                        nuevo.add(cNuevo);
                                                    } else {
                                                        Simbolo simNuevo = new Simbolo(sim.getTipo(), sim.getId(), sim.getValor());
                                                        simNuevo.setTamaño(sim.getTamaño());
                                                        nuevo.add(simNuevo);
                                                    }
                                                }

                                                nuevo.setPadre(aux.getPadre());
                                                aux.setPadre(nuevo);
                                                aux = nuevo;
                                            } else {

                                                for (Simbolo sim : clasePadre.getE().getTabla()) {
                                                    if (sim instanceof Metodo) {
                                                        Metodo m = (Metodo) sim;
                                                        Metodo mNuevo = new Metodo(m.getTipo(), m.getId(), m.getFirma(), m.getParametros(), m.getBloque());
                                                        mNuevo.setTamaño(m.getTamaño());
                                                        ePadre.add(mNuevo);
                                                    } else if (sim instanceof ClaseSim) {
                                                        ClaseSim c = (ClaseSim) sim;
                                                        ClaseSim cNuevo = new ClaseSim(c.getModificadores(), c.getConstructores(), c.getId(), c.getE());
                                                        cNuevo.setMain(c.getMain());
                                                        cNuevo.setPadre(c.getPadre());
                                                        ePadre.add(cNuevo);
                                                    } else {
                                                        Simbolo simNuevo = new Simbolo(sim.getTipo(), sim.getId(), sim.getValor());
                                                        simNuevo.setTamaño(sim.getTamaño());
                                                        ePadre.add(simNuevo);
                                                    }
                                                }

                                                ePadre.setPadre(eNuevo.getPadre());
                                                aux = ePadre;
                                            }
                                            clasePadre = clasePadre.getPadre();
                                        }

                                        eNuevo.setPadre(ePadre);
                                    }
                                    Objeto oNuevo = new Objeto(cast.getClase(), eNuevo);
                                    oNuevo.setHeredadas(obj.getHeredadas());
                                    return oNuevo;
                                } else {
                                    System.err.println("No se puede castear a " + this.tipo.objeto +" Linea: "+this.getLinea());
                                }

                            } else {
                                System.err.println("no se ha instanciado el objeto " + "Linea: "+this.getLinea());
                            }
                        }
                    }
                }
            }
        }
        System.err.println("no se puede castear");
        return null;
    }

    private Double getDouble(Tipo tipo, Object valor) {
        if (tipo != null && valor != null) {
            switch (tipo.tipo) {
                case INT:
                case DOUBLE:
                    return new Double(valor.toString());
                case CHAR:
                    return new Double(valor.toString().charAt(0));
            }
        }
        return null;
    }

    private Integer getInt(Tipo tipo, Object valor) {
        if (tipo != null && valor != null) {
            switch (tipo.tipo) {
                case INT:
                    return new Integer(valor.toString());
                case DOUBLE:
                    Double d = new Double(valor.toString());
                    return d.intValue();
                case CHAR:
                    return new Integer(valor.toString().charAt(0));
            }
        }
        return null;
    }

    private char getChar(Tipo tipo, Object valor) {
        if (tipo != null && valor != null) {
            switch (tipo.tipo) {
                case INT:
                    int num = new Integer(valor.toString());
                    return (char) num;
                case DOUBLE:
                    Double d = new Double(valor.toString());
                    return (char) d.intValue();
                case CHAR:
                    return valor.toString().charAt(0);
            }
        }
        return '\0';
    }

}
