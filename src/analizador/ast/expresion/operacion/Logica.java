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
public class Logica extends Operacion {

    public Logica(Expresion op1, Expresion op2, Operador operador, int linea, int columna) {
        super(op1, op2, operador, linea, columna);
    }

    public Logica(Expresion op1, Operador operador, int linea, int columna) {
        super(op1, null, operador, linea, columna);
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        if (this.getOp2() != null) {
            Tipo tipOp1 = this.getOp1().getTipo(e, salida, this_, errores);
            Tipo tipOp2 = this.getOp2().getTipo(e, salida, this_, errores);

            if (tipOp1 != null && tipOp2 != null) {
                if (tipOp1.tipo == Tipo.type.BOOLEAN && tipOp2.tipo == Tipo.type.BOOLEAN) {
                    return new Tipo(Tipo.type.BOOLEAN);
                }
            }
        } else {
            Tipo tipOp1 = this.getOp1().getTipo(e, salida, this_, errores);
            if (tipOp1 != null) {
                if (tipOp1.tipo == Tipo.type.BOOLEAN) {
                    return new Tipo(Tipo.type.BOOLEAN);
                }
            }
        }
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        Tipo tipRes = this.getTipo(e, salida, this_, errores);
        if (tipRes != null) {
            if (tipRes.tipo == Tipo.type.BOOLEAN) {
                if (this.getOp2() != null) {
                    Object val1 = this.getOp1().getValor(e, salida, this_, errores);
                    Object val2 = this.getOp2().getValor(e, salida, this_, errores);

                    if (val1 != null && val2 != null) {
                        boolean valOp1 = Boolean.valueOf(val1.toString());
                        boolean valOp2 = Boolean.valueOf(val2.toString());

                        switch (this.getOperador()) {
                            case AND:
                                return valOp1 && valOp2;
                            case OR:
                                return valOp1 || valOp2;
                            case XOR:
                                return valOp1 ^ valOp2;
                        }

                    }
                } else {
                    Object val1 = this.getOp1().getValor(e, salida, this_, errores);

                    if (val1 != null) {
                        boolean valOp1 = Boolean.valueOf(val1.toString());
                        if (this.getOperador() == Operacion.Operador.NOT) {
                            return !valOp1;
                        }
                    }
                }
            }
        }
        return null;
    }

}
