/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast;

import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Objeto;
import analizador.ast.instruccion.PrintTabla;
import coline.Coline;
import javax.swing.JOptionPane;

/**
 *
 * @author oscar
 */
public class NodoAst {

    private final int linea;
    private final int columna;

    public NodoAst(int linea, int columna) {
        this.linea = linea;
        this.columna = columna;
    }
    
    public void debug(Entorno e, Object this_, String inst) {
        if (Coline.debugger) {
            if (!Coline.empezoDebug) {
                if (this_ != null) {
                    Objeto objDebug = (Objeto) this_;
                    if (objDebug != null) {
                        if (Coline.claseDebug.equals(objDebug.getClase().getId())) {
                            if (this.getLinea() > Coline.lineaDebug) {
                                Coline.empezoDebug = true;

                            }
                        }
                    }
                }
            }

            if (Coline.empezoDebug) {
                String[] opciones = new String[4];
                opciones[0] = "Si y mostrar entorno";
                opciones[1] = "Si";
                opciones[2] = "Si, pero saltar ciclo";
                opciones[3] = "No";

                String archivo = "";
                if (this_ != null) {
                    Objeto arch = (Objeto) this_;
                    archivo += "\nClase: " + arch.getClase().getId();
                }

                int seleccion = JOptionPane.showOptionDialog(null,
                        "¿Desea continuar debugueando? \n Linea: " + this.getLinea()
                        + " Columna: " + this.getColumna()
                        + "\nInstruccion: "+inst + archivo,
                        "Debugger",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        opciones, //Títulos de los botones.
                        opciones[0]); //El título del botón por default.
                switch (seleccion) {
                    case 0:
                        PrintTabla printTabla = new PrintTabla(this.getLinea(), this.getColumna());
                        printTabla.ejecutar(e, null, false, false, false, null, null);
                        break;
                    case 1:
                        break;
                    case 2:
                        Coline.saltarDebug = true;
                        break;
                    case 3:
                        Coline.debugger = false;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * @return the linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

}
