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
public class Instancia extends Expresion {

    private final String id;
    private final ArrayList<Expresion> parametros;

    public Instancia(String id, ArrayList<Expresion> parametros, int linea, int columna) {
        super(linea, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        return new Tipo(Tipo.type.OBJECT, id);
    }

    @Override
    public Object getValor(Entorno e, Object salida, Object this_, ArrayList<ErrorC> errores) {
        ClaseSim clase = e.getClase(id);
        if (clase != null) {
            Entorno eNuevo = new Entorno(clase.getE().getPadre());
            eNuevo.setGlobal(eNuevo);

            Objeto obj = new Objeto(clase, eNuevo);
            
            for (Simbolo sim : clase.getE().getTabla()) {
                if (sim instanceof Metodo) {
                    Metodo m = (Metodo) sim;
                    eNuevo.add(new Metodo(m.getTipo(), m.getId(), m.getFirma(), m.getParametros(), m.getBloque()));
                } else {
                    eNuevo.add(new Simbolo(sim.getTipo(), sim.getId(), sim.getValor()));
                }
            }

            if (clase.getConstructores() != null) {
                Entorno local = new Entorno(eNuevo);
                String firma = id;

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

                for (Simbolo sim : clase.getConstructores()) {
                    Metodo m = (Metodo) sim;
                    if (m.getFirma().equals(firma)) {
                        if (this.parametros != null) {
                            for (int i = 0; i <= parm.size() - 1; i++) {
                                local.add(new Simbolo(parm.get(i).getTipo(), m.getParametros().get(i).getId(), parm.get(i).getValor()));
                            }
                        }
                        if(m.getBloque() != null)
                            m.getBloque().ejecutar(local, salida, true, false, false, obj, errores);
                        ejecuto = true;
                        break;
                    }
                }

                if (!ejecuto) {
                    System.err.println("no se econtro el constructor, no se creo el objeto.");
                    return null;
                }

            } else {
                if (this.parametros != null) {
                    System.err.println("Error, no se definio el constructor. ");
                    return null;
                }
            }

            if (clase.getPadre() != null) {
                Entorno papa = new Entorno(eNuevo.getPadre());

                for (Simbolo sim : clase.getPadre().getE().getTabla()) {
                    if (sim instanceof Metodo) {
                        Metodo m = (Metodo) sim;
                        papa.add(new Metodo(m.getTipo(), m.getId(), m.getFirma(), m.getParametros(), m.getBloque()));
                    } else {
                        papa.add(new Simbolo(sim.getTipo(), sim.getId(), sim.getValor()));
                    }
                }

                eNuevo.setPadre(papa);
            }

            //eNuevo.recorrer();
            return obj;
        } else {
            System.err.println("No se ha importado la clase: " + id);
        }
        return null;
    }

}
