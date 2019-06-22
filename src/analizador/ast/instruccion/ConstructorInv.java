/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Metodo;
import analizador.ast.entorno.Objeto;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class ConstructorInv extends Instruccion {

    private final String id;
    private final ArrayList<Expresion> parametros;

    public ConstructorInv(String id, ArrayList<Expresion> parametros, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        this.debug(e, this_, "Invocacion Constructor");
        
        if (this_ != null) {
            if (this_ instanceof Objeto) {
                Objeto obj = (Objeto) this_;

                if (this.id.equals("this")) {
                    if (obj.getClase().getConstructores() != null) {
                        Entorno local = new Entorno(obj.getE());
                        String firma = obj.getClase().getId();

                        ArrayList<Simbolo> parm = new ArrayList<>();
                        if (this.parametros != null) {
                            for (Expresion parametro : this.parametros) {
                                Tipo tipo = parametro.getTipo(e, salida, this_, errores);
                                if (tipo != null) {
                                    Object valor = parametro.getValor(e, salida, this_, errores);
                                    if (valor != null) {
                                        firma += "_" + tipo.tipo.toString();
                                        parm.add(new Simbolo(tipo, "parm", valor));
                                        continue;
                                    }
                                }
                                System.err.println("error en parametros");
                                return null;
                            }
                        }

                        boolean ejecuto = false;

                        for (Simbolo sim : obj.getClase().getConstructores()) {
                            Metodo m = (Metodo) sim;
                            if (m.getFirma().equals(firma)) {
                                if (this.parametros != null) {
                                    for (int i = 0; i <= parm.size() - 1; i++) {
                                        local.add(new Simbolo(parm.get(i).getTipo(), m.getParametros().get(i).getId(), parm.get(i).getValor()));
                                    }
                                }
                                if (m.getBloque() != null) {
                                    m.getBloque().ejecutar(local, salida, true, false, false, this_, errores);
                                }
                                ejecuto = true;
                                break;
                            }
                        }

                        if (!ejecuto) {
                            System.err.println("Error, no se definio el constructor. 2");
                            return null;
                        }

                    } else {
                        if (this.parametros != null) {
                            System.err.println("Error, no se definio el constructor. 1");
                            return null;
                        }
                    }
                    return null;
                } else {
                    /*SUPER*/
                    if (obj.getClase().getPadre() != null) {
                        
                        if (obj.getClase().getPadre().getConstructores() != null) {
                            Entorno local = new Entorno(obj.getE().getPadre());//no sé aún
                            String firma = obj.getClase().getPadre().getId();

                            ArrayList<Simbolo> parm = new ArrayList<>();
                            if (this.parametros != null) {
                                for (Expresion parametro : this.parametros) {
                                    Tipo tipo = parametro.getTipo(e, salida, this_, errores);
                                    if (tipo != null) {
                                        Object valor = parametro.getValor(e, salida, this_, errores);
                                        if (valor != null) {
                                            firma += "_" + tipo.tipo.toString();
                                            parm.add(new Simbolo(tipo, "parm", valor));
                                            continue;
                                        }
                                    }
                                    System.err.println("error en parametros");
                                    return null;
                                }
                            }

                            boolean ejecuto = false;

                            for (Simbolo sim : obj.getClase().getPadre().getConstructores()) {
                                Metodo m = (Metodo) sim;
                                if (m.getFirma().equals(firma)) {
                                    if (this.parametros != null) {
                                        for (int i = 0; i <= parm.size() - 1; i++) {
                                            local.add(new Simbolo(parm.get(i).getTipo(), m.getParametros().get(i).getId(), parm.get(i).getValor()));
                                        }
                                    }
                                    if (m.getBloque() != null) {
                                        m.getBloque().ejecutar(local, salida, true, false, false, this_, errores);
                                    }
                                    ejecuto = true;
                                    break;
                                }
                            }

                            if (!ejecuto) {
                                System.err.println("Error, no se definio el constructor. 2");
                                return null;
                            }

                        } else {
                            if (this.parametros != null) {
                                System.err.println("Error, no se definio el constructor. 1");
                                return null;
                            }
                        }
                        return null;
                    }
                    System.err.println("No tine padre. ");
                    return null;
                }
            }
        }
        System.err.println("No se ha instanciado el objeto. ");
        return null;
    }

}
