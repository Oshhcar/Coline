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
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, ArrayList<ErrorC> errores) {
        Entorno global = new Entorno(null);

        if (this.imports != null) {

            for (Import i : this.imports) {
                i.setDirActual(dirActual);
                i.ejecutar(global, salida, metodo, ciclo, switch_, errores);
            }
        }

        Entorno local = new Entorno(global);
        local.setGlobal(local);

        ArrayList<Simbolo> simbolos = new ArrayList<>();
        Metodo main = null;

        if (this.declaraciones != null) {

            for (NodoAst inst : this.declaraciones) {
                if (inst instanceof Instruccion) {
                    ((Instruccion) inst).ejecutar(local, salida, metodo, ciclo, switch_, errores);
                }
            }

            for (Simbolo simbolo : local.getTabla()) {
                if (simbolo instanceof Metodo) {
                    Metodo m = (Metodo) simbolo;
                    if (m.getFirma().equals("main")) {
                        main = m;
                        continue;
                    }
                }
                simbolos.add(simbolo);
            }

        }

        if (!this.id.equals("String") && !this.id.equals("Object")) {
            if (local.getClase(this.id) == null) {
                ClaseSim clase = new ClaseSim(this.modificadores, this.id, local);
                clase.setMain(main);
                if (extiende != null) {
                    if (!this.id.equals(extiende)) {
                        ClaseSim ext = local.getClase(extiende);
                        if (ext != null) {
                            clase.setPadre(ext);
                        } else {
                            System.err.println("Error, no se ha importado la clase " + extiende);
                        }
                    } else {
                        System.err.println("No puede heredar de ella misma");
                    }
                }
                global.add(clase);
                e.add(clase);
                return null;
            }
        }
        System.err.println("Error, ya se definió una clase con el id: " + this.id);

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
