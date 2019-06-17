/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador.ast.instruccion;

import analizador.Lexico;
import analizador.Sintactico;
import analizador.ErrorC;
import analizador.ast.Ast;
import analizador.ast.entorno.Entorno;
import analizador.ast.entorno.Tipo;
import analizador.ast.expresion.Expresion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author oscar
 */
public class Import extends Instruccion {

    private final Expresion direccion;

    private String dirActual;
    private Lexico lexico;
    private Sintactico sintactico;

    public Import(Expresion direccion, int linea, int columna) {
        super(linea, columna);
        this.direccion = direccion;
        this.dirActual = "";
    }

    @Override
    public Object ejecutar(Entorno e, Object salida, boolean metodo, boolean ciclo, boolean switch_, ArrayList<ErrorC> errores) {
        Tipo tipDir = direccion.getTipo(e, salida, errores);
        if (tipDir != null) {
            if (tipDir.tipo == Tipo.type.OBJECT) {
                if (tipDir.objeto.equals("String")) {
                    Object valDir = direccion.getValor(e, salida, errores);
                    if (valDir != null) {
                        String rutaImport = valDir.toString();
                        String ext = rutaImport.substring(rutaImport.lastIndexOf('.'));

                        if (ext.toLowerCase().equals(".coline")) {
                            File archivo;
                            FileReader fr;
                            BufferedReader br;

                            File actual;

                            try {
                                actual = new File(dirActual);
                                if (rutaImport.charAt(0) == '/') {
                                    rutaImport = rutaImport.replaceFirst("/", "");
                                }
                                rutaImport = rutaImport.replaceAll("/", "\\\\");

                                String dirImport = actual.getParent() + "\\" + rutaImport;

                                archivo = new File(dirImport);
                                fr = new FileReader(archivo);
                                br = new BufferedReader(fr);

                                String texto = "";
                                String line;
                                while ((line = br.readLine()) != null) {
                                    texto += line + "\n";
                                }

                                lexico = new Lexico(new BufferedReader(new StringReader(texto)));
                                sintactico = new Sintactico(lexico);

                                Ast ast = null;

                                try {
                                    sintactico.parse();
                                    ast = sintactico.getAST();

                                    lexico.addError();
                                    if (!lexico.getErrores().isEmpty()) {
                                        errores.addAll(lexico.getErrores());
                                    }

                                    if (ast != null) {
                                        //ast.ejecutar(salida, errores, dirActual);
                                        //System.out.println("Genera ast");
                                        Clase clase = ast.getClase();

                                        clase.setDirActual(dirActual);
                                        clase.ejecutar(e, salida, metodo, ciclo, switch_, errores);
                                    }
                                } catch (Exception ex) {
                                    System.err.println("El archivo importado contiene errores.");
                                }

                            } catch (Exception ex) {
                                System.err.println("Error, intentando abrir el archivo \"" + rutaImport + "\". Línea: " + this.getLinea());
                            }
                        } else {
                            System.err.println("No es .coline import");
                        }
                        return null;
                    }
                }
            }
        }
        System.err.println("Error en valor, import.");
        return null;
    }

    /**
     * @param dirActual the dirActual to set
     */
    public void setDirActual(String dirActual) {
        this.dirActual = dirActual;
    }

}
