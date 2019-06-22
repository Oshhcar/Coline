/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coline;

/**
 *
 * @author oscar
 */
public class Coline {
    
    public static String dirActual = "";
    public static boolean debugger = false;
    public static boolean empezoDebug = false;
    public static boolean saltarDebug = false;
    public static int lineaDebug = 0;
    public static String claseDebug = "";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Editor editor = new Editor();
        editor.show();
    }
    
}
