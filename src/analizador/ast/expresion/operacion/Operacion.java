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
public class Operacion extends Expresion{
    private final Expresion op1;
    private final Expresion op2;
    private final Operador operador;
    
    public Operacion(Expresion op1, Expresion op2, Operador operador, int linea, int columna) {
        super(linea, columna);
        this.op1 = op1;
        this.op2 = op2;
        this.operador = operador;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        return null;
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        return null;
    }

    public static enum Operador{
        SUMA,
        RESTA,
        MULTIPLICACION, 
        DIVISION,
        POTENCIA,
        MODULO,
        MAYORQUE,
        MENORQUE,
        MAYORIGUAL,
        MENORIGUAL,
        IGUAL,
        DIFERENTE,
        AND,
        OR,
        NOT,
        XOR,
        AUMENTO,
        DECREMENTO, 
        INSTANCEOF
    }
    
    /**
     * @return the op1
     */
    public Expresion getOp1() {
        return op1;
    }

    /**
     * @return the op2
     */
    public Expresion getOp2() {
        return op2;
    }

    /**
     * @return the operador
     */
    public Operador getOperador() {
        return operador;
    } 
}