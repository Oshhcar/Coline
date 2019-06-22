/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion.operacion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Acceso;
import analizador.ast.expresion.Expresion;
import analizador.ast.expresion.Identificador;
import analizador.ast.instruccion.AccesoObjeto;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class PostFijo extends Operacion {

    public PostFijo(Expresion op1, Operador operador, int linea, int columna) {
        super(op1, null, operador, linea, columna);
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        if (this.getOp1() instanceof Identificador) {
            Tipo tip = this.getOp1().getTipo(e, salida, this_, errores);
            if (tip != null) {
                if (tip.tipo.isNumero() || tip.tipo == Tipo.type.CHAR) {
                    return tip;
                }
            }
        } else if (this.getOp1() instanceof Acceso) {
            AccesoObjeto acceso = ((Acceso) this.getOp1()).getAcceso();
            acceso.setRetSimbolo(true);
            Object var = acceso.ejecutar(e, salida, true, true, true, this_, errores);

            if (var != null) {
                if (var instanceof Simbolo) {
                    Simbolo sim = (Simbolo) var;
                    Tipo tip = sim.getTipo();
                    if (tip != null) {
                        if (tip.tipo.isNumero() || tip.tipo == Tipo.type.CHAR) {
                            return tip;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        if (this.getOp1() instanceof Identificador) {
            Tipo tip = this.getOp1().getTipo(e, salida, this_, errores);
            if (tip != null) {
                if (tip.tipo.isNumero() || tip.tipo == Tipo.type.CHAR) {
                    Object valor = this.getOp1().getValor(e, salida, this_, errores);
                    if (valor != null) {
                        Simbolo tmp = e.get(((Identificador) this.getOp1()).getId());
                        switch (tip.tipo) {
                            case INT:
                                if (this.getOperador() == Operacion.Operador.AUMENTO) {
                                    tmp.setValor(Integer.valueOf(valor.toString()) + 1);
                                } else {
                                    tmp.setValor(Integer.valueOf(valor.toString()) - 1);
                                }
                                break;
                            case DOUBLE:
                                if (this.getOperador() == Operacion.Operador.AUMENTO) {
                                    tmp.setValor(Double.valueOf(valor.toString()) + 1);
                                } else {
                                    tmp.setValor(Double.valueOf(valor.toString()) - 1);
                                }
                                break;
                            case CHAR:
                                if (this.getOperador() == Operacion.Operador.AUMENTO) {
                                    int a = Integer.valueOf(valor.toString().charAt(0)) + 1;
                                    tmp.setValor((char) a);
                                } else {
                                    int a = Integer.valueOf(valor.toString().charAt(0)) - 1;
                                    tmp.setValor((char) a);
                                }
                        }
                        return valor;
                    }
                }
            }
        } else if (this.getOp1() instanceof Acceso) {
            AccesoObjeto acceso = ((Acceso) this.getOp1()).getAcceso();
            acceso.setRetSimbolo(true);
            Object var = acceso.ejecutar(e, salida, true, true, true, this_, errores);

            if (var != null) {
                if (var instanceof Simbolo) {
                    Simbolo sim = (Simbolo) var;

                    Tipo tip = sim.getTipo();
                    if (tip != null) {
                        if (tip.tipo.isNumero() || tip.tipo == Tipo.type.CHAR) {
                            Object valor = sim.getValor();
                            if (valor != null) {
                                switch (tip.tipo) {
                                    case INT:
                                        if (this.getOperador() == Operacion.Operador.AUMENTO) {
                                            sim.setValor(Integer.valueOf(valor.toString()) + 1);
                                        } else {
                                            sim.setValor(Integer.valueOf(valor.toString()) - 1);
                                        }
                                        break;
                                    case DOUBLE:
                                        if (this.getOperador() == Operacion.Operador.AUMENTO) {
                                            sim.setValor(Double.valueOf(valor.toString()) + 1);
                                        } else {
                                            sim.setValor(Double.valueOf(valor.toString()) - 1);
                                        }
                                        break;
                                    case CHAR:
                                        if (this.getOperador() == Operacion.Operador.AUMENTO) {
                                            int a = Integer.valueOf(valor.toString().charAt(0)) + 1;
                                            sim.setValor((char) a);
                                        } else {
                                            int a = Integer.valueOf(valor.toString().charAt(0)) - 1;
                                            sim.setValor((char) a);
                                        }
                                }
                                return valor;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
