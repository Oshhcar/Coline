/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.NodoAst;
import analizador.ast.entorno.ClaseSim;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Metodo;
import analizador.ast.entorno.Modificador;
import analizador.ast.entorno.Simbolo;
import analizador.ast.expresion.Expresion;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Clase extends Instruccion {

    private ArrayList<Import> imports;
    private final ArrayList<Modificador> modificadores;
    private final String id;
    private final String extiende;
    private final ArrayList<NodoAst> declaraciones;
    private String dirActual;

    public Clase(ArrayList<Modificador> modificadores, String id, ArrayList<NodoAst> declaraciones, int linea, int columna) {
        super(linea, columna);
        this.imports = null;
        this.modificadores = modificadores;
        this.id = id;
        this.extiende = null;
        this.declaraciones = declaraciones;
        this.dirActual = "";
    }

    public Clase(ArrayList<Modificador> modificadores, String id, String extiende, ArrayList<NodoAst> declaraciones, int linea, int columna) {
        super(linea, columna);
        this.imports = null;
        this.modificadores = modificadores;
        this.id = id;
        this.extiende = extiende;
        this.declaraciones = declaraciones;
        this.dirActual = "";
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        if (!this.id.equals("String") && !this.id.equals("Object")) {

            Entorno global = new Entorno(null);

            ClaseSim clase = new ClaseSim(this.modificadores, this.id);
            global.add(clase);

            if (this.imports != null) {

                for (Import i : this.imports) {
                    i.setDirActual(dirActual);
                    i.ejecutar(global, salida, metodo, ciclo, switch_, this_, errores);
                }
            }

            Entorno local = new Entorno(global);
            local.setGlobal(local);

            Entorno cons = new Entorno(null);
            cons.setGlobal(cons);
            ArrayList<Simbolo> constructores = new ArrayList<>();

            Metodo main = null;

            if (this.declaraciones != null) {

                for (NodoAst inst : this.declaraciones) {
                    if (inst instanceof Instruccion) {
                        if (inst instanceof Constructor) {
                            ((Constructor) inst).ejecutar(cons, salida, metodo, ciclo, switch_, this_, errores);
                        } else {
                            ((Instruccion) inst).ejecutar(local, salida, metodo, ciclo, switch_, this_, errores);
                        }
                    }
                }

                for (Simbolo simbolo : cons.getTabla()) {
                    if (simbolo instanceof Metodo) {
                        Metodo m = (Metodo) simbolo;
                        if (m.getId().equals(this.id)) {
                            constructores.add(simbolo);
                            continue;
                        }
                        ErrorC error = new ErrorC();
                        error.setTipo("Semántico");
                        error.setValor(m.getId());
                        error.setDescripcion("Error, ya se ha declarado un constructor con la misma firma.");
                        error.setLinea(this.getLinea());
                        error.setColumna(this.getColumna());
                        errores.add(error);
                    }
                }

                for (Simbolo simbolo : local.getTabla()) {
                    if (simbolo instanceof Metodo) {
                        Metodo m = (Metodo) simbolo;
                        if (m.getFirma().equals("main")) {
                            main = m;
                            break;
                        }
                    }
                }

            }

            if (!constructores.isEmpty()) {
                clase.setConstructores(constructores);
            }

            clase.setE(local);
            clase.setMain(main);

            if (extiende != null) {
                if (!this.id.equals(extiende)) {
                    ClaseSim ext = local.getClase(extiende);
                    if (ext != null) {
                        clase.setPadre(ext);
                    } else {
                        ErrorC error = new ErrorC();
                        error.setTipo("Semántico");
                        error.setValor(extiende);
                        error.setDescripcion("Error, no se ha importado la clase.");
                        error.setLinea(this.getLinea());
                        error.setColumna(this.getColumna());
                        errores.add(error);
                    }
                } else {
                    ErrorC error = new ErrorC();
                    error.setTipo("Semántico");
                    error.setValor(this.id);
                    error.setDescripcion("No se puede ederar ella misma.");
                    error.setLinea(this.getLinea());
                    error.setColumna(this.getColumna());
                    errores.add(error);
                }
            }

            e.add(clase);
            return null;
        }
        ErrorC error = new ErrorC();
        error.setTipo("Semántico");
        error.setValor(this.id);
        error.setDescripcion("No se puede declarar la clase.");
        error.setLinea(this.getLinea());
        error.setColumna(this.getColumna());
        errores.add(error);
        return null;
    }

    /**
     * @return the imports
     */
    public ArrayList<Import> getImports() {
        return imports;
    }

    /**
     * @param imports the imports to set
     */
    public void setImports(ArrayList<Import> imports) {
        this.imports = imports;
    }

    /**
     * @param dirActual the dirActual to set
     */
    public void setDirActual(String dirActual) {
        this.dirActual = dirActual;
    }
}
