/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import coline.Coline;
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
public class Graph extends Instruccion {

    private final Expresion ruta;
    private final Expresion contenido;

    public Graph(Expresion ruta, Expresion contenido, int linea, int columna) {
        super(linea, columna);
        this.ruta = ruta;
        this.contenido = contenido;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
        this.debug(e, this_, "graph");

        Tipo tipRuta = ruta.getTipo(e, salida, this_, errores);
        if (tipRuta != null) {
            if (tipRuta.tipo == Tipo.type.STRING) {
                Object valValor = ruta.getValor(e, salida, this_, errores);
                if (valValor != null) {
                    String direccion = valValor.toString();

                    Tipo tipContenido = contenido.getTipo(e, salida, this_, errores);
                    if (tipContenido != null) {
                        if (tipContenido.tipo == Tipo.type.STRING) {
                            Object valContenido = contenido.getValor(e, salida, this_, errores);
                            if (valContenido != null) {
                                String texto = valContenido.toString();

                                FileWriter archivo = null;
                                PrintWriter pw = null;

                                File actual;

                                try {

                                    actual = new File(Coline.dirActual);
                                    if (direccion.charAt(0) == '/') {
                                        direccion = direccion.replaceFirst("/", "");
                                    }
                                    direccion = direccion.replaceAll("/", "\\\\");

                                    String rutaImagen = actual.getParent() + "\\" + direccion;
                                    String ext = rutaImagen.substring(rutaImagen.lastIndexOf('.') + 1);
                                    String rutaDot = rutaImagen.substring(0, rutaImagen.lastIndexOf('.')) + ".dot";
                                    archivo = new FileWriter(rutaDot);
                                    pw = new PrintWriter(archivo);

                                    String textoArray[] = texto.split("\n");

                                    for (String t : textoArray) {
                                        pw.println(t);
                                    }

                                    archivo.close();

                                    try {
                                        String cmd = "dot -T" + ext + " " + rutaDot + " -o " + rutaImagen;
                                        Runtime.getRuntime().exec(cmd);

                                        try {
                                            File objetofile = new File(rutaImagen);
                                            Desktop.getDesktop().open(objetofile);
                                        } catch (IOException ex) {
                                            //System.out.println(ex + " 3");
                                        }
                                    } catch (IOException ioe) {
                                        //System.out.println(ioe + " 2");
                                    }

                                } catch (Exception ex) {
                                    //System.out.println(ex + " 1");
                                }
                                return null;
                            }
                        }
                    }
                }
            }
        }

        ErrorC error = new ErrorC();
        error.setTipo("Semántico");
        error.setValor("graph");
        error.setDescripcion("Error en el parametro.");
        error.setLinea(this.getLinea());
        error.setColumna(this.getColumna());
        errores.add(error);

        return null;
    }

}
