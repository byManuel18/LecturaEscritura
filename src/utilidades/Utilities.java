/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Manueh
 */
public class Utilities {
    private static Scanner keyboard = new Scanner(System.in);

    /**
     * Escribe un texto en consola sin retorno de carro
     *
     * @param f texto a imprimir
     */
    public static void p(String f) {
        System.out.print(f);
    }

    /**
     * Escribe un texto en consola con retorno de carro
     *
     * @param f texto a imprimir
     */
    public static void P(String f) {
        Utilities.p(f + "\n");
    }

    /**
     * Lee un entero de teclado
     *
     * @return devuelve el entero leído
     */
    public static int getInt() {
        int result = 0;
        boolean valid = false;
        do {
            try {
                result = Integer.parseInt(keyboard.nextLine());
                valid = true;

            } catch (IllegalStateException ex) {
                keyboard = new Scanner(System.in);
                Utilities.P("Error in keyboard. Please, try it again: ");
            } catch (NumberFormatException ex) {
                Utilities.P("Error reading integer type. Please, try it again: ");
            } catch (Exception ex) {
                ex.printStackTrace();
                Utilities.P("Error unknown. Please, try it again: ");
            }
        } while (!valid);
        return result;
    }

    /**
     * Lee un entero de teclado
     *
     * @param f Mensaje a imprimir al usuario antes de solicitar el entero
     * @return devuelve el entero leído
     */
    public static int getInt(String f) {
        Utilities.p(f + " : ");
        return Utilities.getInt();
    }

    /**
     * Lee un float de teclado
     *
     * @return devuelve el float leído
     */
    public static double getDouble() {
        double result = 0;
        boolean valid = false;
        do {
            try {
                result = Double.parseDouble(keyboard.nextLine());
                valid = true;

            } catch (IllegalStateException ex) {
                keyboard = new Scanner(System.in);
                Utilities.P("Error in keyboard. Please, try it again: ");
            } catch (NumberFormatException ex) {
                Utilities.P("Error reading decimal number. Please, try it again: ");
            } catch (Exception ex) {
                Utilities.P("Error unknown. Please, try it again: ");
            }
        } while (!valid);
        return result;
    }

    /**
     * Lee un float del teclado, imprimiendo previamente un mensaje al usuario
     *
     * @param f mensaje a imprimir antes de solicitar el float
     * @return float insertado por el usuario
     */
    public static double getFloat(String f) {
        Utilities.p(f + " : ");
        return Utilities.getDouble();
    }

    /**
     * Lee un string de teclado
     *
     * @return strint insertado por el usuario
     */
    public static String getString() {
        String result = "";
        boolean valid = false;
        do {
            try {
                result = keyboard.nextLine();
                valid = true;

            } catch (Exception ex) {
                Utilities.P("Error unknown. Please, try it again: ");
            }
        } while (!valid);
        return result;
    }
    public static String getStringSinModicar() {
        String result = "";
        boolean valid = false;
        do {
            try {
                result = keyboard.nextLine();
                valid = true;

            } catch (Exception ex) {
                Utilities.P("Error unknown. Please, try it again: ");
            }
        } while (!valid);
        return result;
    }

    /**
     * Lee un string de teclado, imprimiendo previamente un mensaje
     *
     * @param f mensaje a mostrar antes de solicitar el string
     * @return string insertado por el usuario
     */
    public static String getString(String f) {
        Utilities.p(f + " : ");
        return Utilities.getString();
    }
    
    public static int Menu(){
        int reultado=0;
        P("1) Listar carpeta ");
        P("2) Mostrar el contenido de un archivo por pantalla");
        P("3) Crear archivo");
        P("4) Copiar un archivo");
        P("5) Copiar una carpeta entera");
        P("6) Borrar un archivo con confirmación");
        P("7) Borrar una carpeta con confirmación");
        P("8) Comprimir un archivo");
        P("9) Comprimir una carpeta");
        P("10) Cifrar un archivo");
        P("11) Cifrar una carpeta");
        P("12) Salir");
        p("-->>>");
        reultado=getInt();
        
        return reultado;
    }
    
    public static int MenuCrearArchivo(){
        int resultado=0;
        P("1) Añadir datos al archivo");
        P("2) Sobreescribir el archivo");
        P("3) Salir sin modificar");
        p("-->>>");
        resultado=getInt();
        return resultado;
    }
}
