/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import utilidades.Utilities;
import java.io.*;

/**
 *
 * @author Manueh
 */
public class Controlador {
private static File mF;
    public static void run() {
        Menu();
    }

    private static void Menu() {
        int op = 0;
        do {
            op = Utilities.Menu();
            ControladorMenu(op);
        } while (op != 12);

    }

    private static void ControladorMenu(int opcion) {

        switch (opcion) {
            case 1:
                MostrarCarpeta();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                Utilities.P("Gracias por usar nuestro gestor.");
                break;
            default:
                Utilities.P("Opción no válida.");
        }

    }
    
    private static void MostrarCarpeta(){
        mF=new File(Utilities.getString("Introduce la dirección de la carpeta"));
        String[] cosas=mF.list();
        
        if(cosas!=null){
            if(cosas.length==0){
            Utilities.P("Carpeta vacía.");
        }else{
            for(String s:cosas){
                Utilities.P(s);
            }
        }
        }else{
            Utilities.P("No existe la carpeta.");
        }

    }
}
