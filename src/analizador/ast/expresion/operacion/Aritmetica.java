/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion.operacion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Aritmetica extends Operacion {

    public Aritmetica(Expresion op1, Expresion op2, Operador operador, int linea, int columna) {
        super(op1, op2, operador, linea, columna);
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Tipo tipOp1 = this.getOp1().getTipo(e, salida, errores);
        Tipo tipOp2 = this.getOp2().getTipo(e, salida, errores);

        if (tipOp1 != null && tipOp2 != null) {
            if (tipOp1.tipo == Tipo.type.STRING || tipOp2.tipo == Tipo.type.STRING) {
                if (this.getOperador() == Operacion.Operador.SUMA) {
                    return new Tipo(Tipo.type.STRING);
                }
            } else if (tipOp1.tipo != Tipo.type.BOOLEAN && tipOp2.tipo != Tipo.type.BOOLEAN) {
                if (tipOp1.tipo == Tipo.type.DOUBLE || tipOp2.tipo == Tipo.type.DOUBLE) {
                    return new Tipo(Tipo.type.DOUBLE);
                } else if (tipOp1.tipo == Tipo.type.INT || tipOp2.tipo == Tipo.type.INT) {
                    return new Tipo(Tipo.type.INT);
                } else if (tipOp1.tipo == Tipo.type.CHAR && tipOp2.tipo == Tipo.type.CHAR) {
                    return new Tipo(Tipo.type.INT);
                }
            }
        }
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Tipo tipDominante = this.getTipo(e, salida, errores);

        if (tipDominante != null) {
            switch (tipDominante.tipo) {
                case STRING:
                    return this.getOp1().getValor(e, salida, errores).toString() + this.getOp2().getValor(e, salida, errores).toString();
                case DOUBLE: {
                    Double valOp1 = this.getDouble(this.getOp1().getTipo(e, salida, errores), this.getOp1().getValor(e, salida, errores));
                    Double valOp2 = this.getDouble(this.getOp2().getTipo(e, salida, errores), this.getOp2().getValor(e, salida, errores));
                    if (valOp1 != null && valOp2 != null) {
                        switch (this.getOperador()) {
                            case SUMA:
                                return valOp1 + valOp2;
                            case RESTA:
                                return valOp1 - valOp2;
                            case MULTIPLICACION:
                                return valOp1 * valOp2;
                            case DIVISION:
                                return valOp1 / valOp2;
                            case MODULO:
                                return valOp1 % valOp2;
                        }
                    }
                    break;
                }
                case INT: {
                    Integer valOp1 = this.getInt(this.getOp1().getTipo(e, salida, errores), this.getOp1().getValor(e, salida, errores));
                    Integer valOp2 = this.getInt(this.getOp2().getTipo(e, salida, errores), this.getOp2().getValor(e, salida, errores));
                    switch (this.getOperador()) {
                        case SUMA:
                            return valOp1 + valOp2;
                        case RESTA:
                            return valOp1 - valOp2;
                        case MULTIPLICACION:
                            return valOp1 * valOp2;
                        case DIVISION:
                            return valOp1 / valOp2;
                        case MODULO:
                            return valOp1 % valOp2;
                    }
                    break;
                }
                default:
                    break;
            }
        }
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
                case DOUBLE:
                    return new Integer(valor.toString());
                case CHAR:
                    return new Integer(valor.toString().charAt(0));
            }
        }
        return null;
    }

}
