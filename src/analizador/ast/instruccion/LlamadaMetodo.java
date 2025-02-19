/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.NodoAst;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Metodo;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import analizador.ast.expresion.Return;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class LlamadaMetodo extends Instruccion {

    private final String id;
    private ArrayList<Expresion> parametros;
    private boolean funcion;

    public LlamadaMetodo(String id, ArrayList<Expresion> parametros, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.parametros = parametros;
        this.funcion = false;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        this.debug(e, this_, "LlamadaMetodo");

        Metodo m = null;
        Entorno local = new Entorno(e.getGlobal());
        String firma = this.getId();

        if (this.getParametros() == null) {
            m = e.getMetodo(firma);
        } else {
            ArrayList<Simbolo> parm = new ArrayList<>();
            for (Expresion parametro : this.getParametros()) {
                Tipo tipo = parametro.getTipo(e, salida, this_, errores);
                if (tipo != null) {
                    Object valor = parametro.getValor(e, salida, this_, errores);
                    if (valor != null) {
                        if (tipo.tipo == Tipo.type.ARRAY) {
                            firma += "_ARRAY-" + tipo.subtipo;
                        } else {
                            firma += "_" + tipo.tipo.toString();
                        }
                        parm.add(new Simbolo(tipo, "parm", valor));
                        continue;
                    }
                }
                ErrorC error = new ErrorC();
                error.setTipo("Semántico");
                //error.setValor("import");
                error.setDescripcion("Erroes en los parametros.");
                error.setLinea(this.getLinea());
                error.setColumna(this.getColumna());
                errores.add(error);
                return null;
            }

            m = e.getMetodo(firma);

            if (m != null) {
                for (int i = 0; i <= parm.size() - 1; i++) {
                    Simbolo simNuevo = new Simbolo(parm.get(i).getTipo(), m.getParametros().get(i).getId(), parm.get(i).getValor());
                    simNuevo.setTamaño(m.getParametros().get(i).getTamaño());
                    local.add(simNuevo);
                }
            }
        }

        if (m != null) {
            Object obj = null;
            if (m.getBloque() != null) {
                obj = m.getBloque().ejecutar(local, salida, true, false, false, this_, errores);
            }

            if (obj != null) {
                if (obj instanceof Return) {
                    if (isFuncion()) {
                        if (m.getTipo().tipo == ((Return) obj).getTipo(local, salida, this_, errores).tipo) {
                            return obj;
                        } else {
                            ErrorC error = new ErrorC();
                            error.setTipo("Semántico");
                            //error.setValor("import");
                            error.setDescripcion("No son del mismo tipo.");
                            error.setLinea(this.getLinea());
                            error.setColumna(this.getColumna());
                            errores.add(error);
                        }

                    } else if (((Return) obj).getToReturn() != null && m.getTipo().tipo == Tipo.type.VOID) {
                        ErrorC error = new ErrorC();
                        error.setTipo("Semántico");
                        //error.setValor("import");
                        error.setDescripcion("Erro, no debe retornar valor.");
                        error.setLinea(this.getLinea());
                        error.setColumna(this.getColumna());
                        errores.add(error);
                    }
                }
            }
        } else {
            ErrorC error = new ErrorC();
            error.setTipo("Semántico");
            error.setValor(this.getId());
            error.setDescripcion("El metodo no se ha declarado.");
            error.setLinea(this.getLinea());
            error.setColumna(this.getColumna());
            errores.add(error);
        }
        return null;
    }

    /**
     * @return the funcion
     */
    public boolean isFuncion() {
        return funcion;
    }

    /**
     * @param funcion the funcion to set
     */
    public void setFuncion(boolean funcion) {
        this.funcion = funcion;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the parametros
     */
    public ArrayList<Expresion> getParametros() {
        return parametros;
    }

    /**
     * @param parametros the parametros to set
     */
    public void setParametros(ArrayList<Expresion> parametros) {
        this.parametros = parametros;
    }

}
