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
public class Relacional extends Operacion {

    public Relacional(Expresion op1, Expresion op2, Operador operador, int linea, int columna) {
        super(op1, op2, operador, linea, columna);
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Tipo tipOp1 = this.getOp1().getTipo(e, salida, errores);
        Tipo tipOp2 = this.getOp2().getTipo(e, salida, errores);

        if (tipOp1 != null && tipOp2 != null) {
            if ((tipOp1.tipo.isNumero() || tipOp1.tipo == Tipo.type.CHAR) && (tipOp2.tipo.isNumero() || tipOp2.tipo == Tipo.type.CHAR)) {
                return new Tipo(Tipo.type.BOOLEAN);
            } else if (tipOp1.tipo == Tipo.type.OBJECT && tipOp2.tipo == Tipo.type.OBJECT) {
                return new Tipo(Tipo.type.BOOLEAN);
            } else if (tipOp1.tipo == Tipo.type.BOOLEAN && tipOp2.tipo == Tipo.type.BOOLEAN) {
                if (this.getOperador() == Operacion.Operador.IGUAL || this.getOperador() == Operacion.Operador.DIFERENTE) {
                    return new Tipo(Tipo.type.BOOLEAN);
                }
            }
        }
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Tipo tipOp1 = this.getOp1().getTipo(e, salida, errores);
        Tipo tipOp2 = this.getOp2().getTipo(e, salida, errores);

        if (tipOp1 != null && tipOp2 != null) {
            if ((tipOp1.tipo.isNumero() || tipOp1.tipo == Tipo.type.CHAR) && (tipOp2.tipo.isNumero() || tipOp2.tipo == Tipo.type.CHAR)) {
                Double valOp1 = this.getDouble(tipOp1, this.getOp1().getValor(e, salida, errores));
                Double valOp2 = this.getDouble(tipOp2, this.getOp2().getValor(e, salida, errores));
                if (valOp1 != null && valOp2 != null) {
                    switch (this.getOperador()) {
                        case MAYORQUE:
                            return valOp1 > valOp2;
                        case MENORQUE:
                            return valOp1 < valOp2;
                        case MAYORIGUAL:
                            return valOp1 >= valOp2;
                        case MENORIGUAL:
                            return valOp1 <= valOp2;
                        case IGUAL:
                            return valOp1.doubleValue() == valOp2.doubleValue();
                        case DIFERENTE:
                            return valOp1.doubleValue() != valOp2.doubleValue();
                    }
                }
            } else if (tipOp1.tipo == Tipo.type.OBJECT && tipOp2.tipo == Tipo.type.OBJECT) {
                Integer valOp1 = this.getValorCadena(this.getOp1().getValor(e, salida, errores).toString());
                Integer valOp2 = this.getValorCadena(this.getOp2().getValor(e, salida, errores).toString());

                if (valOp1 != null && valOp2 != null) {
                    switch (this.getOperador()) {
                        case MAYORQUE:
                            return valOp1 > valOp2;
                        case MENORQUE:
                            return valOp1 < valOp2;
                        case MAYORIGUAL:
                            return valOp1 >= valOp2;
                        case MENORIGUAL:
                            return valOp1 <= valOp2;
                        case IGUAL:
                            return valOp1.intValue() == valOp2.intValue();
                        case DIFERENTE:
                            return valOp1.intValue() != valOp2.intValue();
                    }
                }

            } else if (tipOp1.tipo == Tipo.type.BOOLEAN && tipOp2.tipo == Tipo.type.BOOLEAN) {
                Object val1 = this.getOp1().getValor(e, salida, errores).toString();
                Object val2 = this.getOp2().getValor(e, salida, errores).toString();

                if (val1 != null && val2 != null) {
                    Boolean valOp1 = Boolean.valueOf(val1.toString());
                    Boolean valOp2 = Boolean.valueOf(val2.toString());
                    if (this.getOperador() == Operacion.Operador.IGUAL) {
                        return valOp1 == valOp2;
                    } else if (this.getOperador() == Operacion.Operador.DIFERENTE) {
                        return valOp1 != valOp2;
                    }
                }

            }
        }

        return null;
    }

    public Integer getValorCadena(String cad) {
        if (cad != null) {
            int res = 0;
            for (int i = 0; i < cad.length(); i++) {
                res = res + cad.charAt(i);
            }
            return res;
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
}
