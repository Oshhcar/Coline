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
public class Pow extends Operacion {

    public Pow(Expresion op1, Expresion op2, int linea, int columna) {
        super(op1, op2, Operacion.Operador.POTENCIA, linea, columna);
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        Tipo tipOp1 = this.getOp1().getTipo(e, salida, this_, errores);
        Tipo tipOp2 = this.getOp2().getTipo(e, salida, this_, errores);
        
        if(tipOp1 != null && tipOp2 != null){
            if((tipOp1.tipo.isNumero() || tipOp1.tipo == Tipo.type.CHAR) && (tipOp2.tipo.isNumero() || tipOp2.tipo == Tipo.type.CHAR)){
                return new Tipo(Tipo.type.DOUBLE);
            }
        }
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        if(this.getTipo(e, salida, this_, errores) != null){
            Tipo tipOp1 = this.getOp1().getTipo(e, salida, this_, errores);
            Tipo tipOp2 = this.getOp2().getTipo(e, salida, this_, errores);
        
            if(tipOp1 != null && tipOp2 != null){
                Double valOp1 = this.getDouble(tipOp1, this.getOp1().getValor(e, salida, this_, errores));
                Double valOp2 = this.getDouble(tipOp2, this.getOp2().getValor(e, salida, this_, errores));
                
                if(valOp1 != null && valOp2 != null){
                    return (Double)Math.pow(valOp1, valOp2);
                }
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
}
