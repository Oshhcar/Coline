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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class WriteFile extends Instruccion {

    private final Expresion ruta;
    private final Expresion contenido;

    public WriteFile(Expresion ruta, Expresion contenido, int linea, int columna) {
        super(linea, columna);
        this.ruta = ruta;
        this.contenido = contenido;
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, Object this_, ArrayList<ErrorC> errores) {
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

                                    String dirRuta = actual.getParent() + "\\" + direccion;

                                    archivo = new FileWriter(dirRuta);
                                    pw = new PrintWriter(archivo);
                                    
                                    
                                    String textoArray[] = texto.split("\n");
                                    
                                    for(String t: textoArray){
                                        pw.println(t);
                                    }
                                    
                                    archivo.close();
                                    return null;

                                } catch (Exception ex) {
                                    System.err.println("Error, intentando abrir el archivo \"" + direccion + "\". Línea: " + this.getLinea());
                                }
                            }
                        }
                    }
                }
            }
        }
        System.err.println("Error en parametros.");

        return null;
    }

}
