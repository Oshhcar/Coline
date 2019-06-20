/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.expresion;

import analizador.ErrorC;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import coline.Coline;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class ReadFile extends Expresion {

    private final Expresion ruta;

    public ReadFile(Expresion ruta, int linea, int columna) {
        super(linea, columna);
        this.ruta = ruta;
    }

    @Override
    public Tipo getTipo(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        return new Tipo(Tipo.type.STRING);
    }

    @Override
    public Object getValor(Entorno e, Object salida, ArrayList<ErrorC> errores) {
        Tipo tipRuta = ruta.getTipo(e, salida, errores);
        if (tipRuta != null) {
            if (tipRuta.tipo == Tipo.type.STRING) {
                Object valValor = ruta.getValor(e, salida, errores);
                if (valValor != null) {
                    String direccion = valValor.toString();

                    File archivo;
                    FileReader fr;
                    BufferedReader br;

                    File actual;

                    try {
                        actual = new File(Coline.dirActual);
                        if (direccion.charAt(0) == '/') {
                            direccion = direccion.replaceFirst("/", "");
                        }
                        direccion = direccion.replaceAll("/", "\\\\");

                        String dirRuta = actual.getParent() + "\\" + direccion;
                        archivo = new File(dirRuta);
                        fr = new FileReader(archivo);
                        br = new BufferedReader(fr);

                        String texto = "";
                        String line;
                        while ((line = br.readLine()) != null) {
                            texto += line + "\n";
                        }
                        
                        return texto;
                        
                    } catch (Exception ex) {
                        System.err.println("Error, intentando abrir el archivo \"" + direccion + "\". Línea: " + this.getLinea());
                    }
                }
            }
        }
        System.err.println("Error en la ruta.");

        return null;
    }
}
