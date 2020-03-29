/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import utilidades.Utilities;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manueh
 */
public class Controlador {

    private static File mF;
    private static FileReader mFiRe;
    private static BufferedReader mbBuRe;
    private static BufferedWriter mbBuWr;

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
                MostrarArchivo();
                break;
            case 3:
                CrearArchivo();
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

    private static void MostrarCarpeta() {
        mF = new File(Utilities.getString("Introduce la dirección de la carpeta"));
        String[] cosas = mF.list();

        if (cosas != null) {
            if (cosas.length == 0) {
                Utilities.P("Carpeta vacía.");
            } else {
                for (String s : cosas) {
                    Utilities.P(s);
                }
            }
        } else {
            Utilities.P("No existe la carpeta.");
        }

    }

    private static void MostrarArchivo() {
        mF = new File(Utilities.getString("Introduce la dirección completa del archivo a mostrar"));
        
        try {
            String linea;
            mFiRe = new FileReader(mF);
            mbBuRe = new BufferedReader(mFiRe);
            while((linea=mbBuRe.readLine())!=null){
                Utilities.P(linea);
            }
            mFiRe.close();
            mbBuRe.close();
        } catch (IOException ex) {
            Utilities.P("Error: "+ex);
        }

    }
    
    private static void CrearArchivo(){
        String nombrearchivo;
        String extension;
        String ruta;
        String contenido="";
        nombrearchivo=Utilities.getString("Introduce el nombre del archivo");
        extension=Utilities.getString("Introduce la extension del archivo");
        ruta=Utilities.getString("Introduce la ruta del archivo");
        char cara=92;
        String path=ruta+Character.toString(cara)+nombrearchivo+"."+extension;
        mF=new File(path);
        if(!mF.exists()){
            try {
                mbBuWr=new BufferedWriter(new FileWriter(mF));
                Utilities.P("Introduce el contenido del archivo.(FIN para acabar)");
                do{
                    Utilities.p("-->> ");
                    contenido=Utilities.getString();
                    if(!contenido.equals("FIN")){
                        mbBuWr.write(contenido);
                        mbBuWr.newLine();
                    } 
                }while(!contenido.equals("FIN"));
                
                mbBuWr.close();
                Utilities.P("Archivo creado correctamente.");
            } catch (IOException ex) {
                Utilities.P("Error: "+ex);
            }
        }else{
            Utilities.P("Ya existe un archivo con esas caracteríasticas.");
        }
        
    }
}
 