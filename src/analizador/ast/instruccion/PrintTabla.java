/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Metodo;
import analizador.ast.entorno.Simbolo;
import analizador.ast.entorno.Tipo;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class PrintTabla extends Instruccion {

    public PrintTabla(int linea, int columna) {
        super(linea, columna);
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        String cadena = "digraph G \n{\n";
        cadena += "node[ shape = none, fontname = \"Arial\" ];\n";
        cadena += "rankdir=LR;\n";
        cadena += "edge [color=red, dir=back, style=bold];\n\n\n";

        Entorno actual = e;
        int i = 0;
        while (actual != null) {
            cadena += "entorno" + i + "[ label =<\n";
            cadena += "<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\" CELLPADDING=\"4\">\n";
            cadena += "<TR>\n";
            cadena += "<TD COLSPAN = \"5\"> Entorno " + i + " </TD>\n";
            cadena += "</TR>\n";
            cadena += "<TR>\n";
            cadena += "<TD> Id </TD>\n";
            cadena += "<TD> Tipo </TD>\n";
            cadena += "<TD> Subtipo </TD>\n";
            cadena += "<TD> Tamaño </TD>\n";
            cadena += "<TD> Valor </TD>\n";
            cadena += "</TR>\n";

            for (Simbolo s : actual.getTabla()) {
                cadena += "<TR>\n";
                cadena += "<TD> " + s.getId() + " </TD>\n";
                cadena += "<TD> " + s.getTipo().tipo.toString() + " </TD>\n";

                if (s.getTipo().subtipo != null) {
                    cadena += "<TD> " + s.getTipo().subtipo.toString() + " </TD>\n";
                } else if (s.getTipo().objeto != null) {
                    cadena += "<TD> " + s.getTipo().objeto + " </TD>\n";
                } else if(s instanceof Metodo){
                    cadena += "<TD> " + "METHOD" + " </TD>\n";
                }else {
                    cadena += "<TD> " + " </TD>\n";
                }
                cadena += "<TD> " + s.getTamaño() + " </TD>\n";
                if (s.getValor() != null) {
                    cadena += "<TD> " + s.getValor().toString() + " </TD>\n";
                } else {
                    cadena += "<TD> " + " </TD>\n";
                }

                cadena += "</TR>\n";
            }

            cadena += "</TABLE>>];\n\n\n";

            i++;
            actual = actual.getPadre();
            if (actual != null) {
                cadena += "entorno" + i + " -> " + "entorno" + (i - 1) + ";\n\n";
            }
        }
        cadena += "\n\n}";

        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter("tabla.dot");
            pw = new PrintWriter(archivo);
            pw.println(cadena);
            archivo.close();
        } catch (Exception ex) {
            System.out.println(ex + " 1");
        }
        
        try {
            String cmd = "dot -Tpng tabla.dot -o tabla.png";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe + " 2");
        }
        
        try {
            File objetofile = new File("tabla.png");
            Desktop.getDesktop().open(objetofile);
        } catch (IOException ex) {
            System.out.println(ex + " 3");
        }

        //e.recorrer();
        return null;
    }

}
