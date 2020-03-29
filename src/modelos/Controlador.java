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
    private static InputStream inputStr;
    private static OutputStream outStr;

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
                CopiarArchivo();
                break;
            case 5:
                if(CopiarCarpetas(Utilities.getString("Introduce la ruta de la carpeta a copiar"),Utilities.getString("Introduce la ruta de destino"))){
                    Utilities.P("Carpeta copiada coorectamente.");
                }
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
            while ((linea = mbBuRe.readLine()) != null) {
                Utilities.P(linea);
            }
            mFiRe.close();
            mbBuRe.close();
        } catch (IOException ex) {
            Utilities.P("Error: " + ex);
        }

    }

    private static void CrearArchivo() {
        String nombrearchivo;
        String extension;
        String ruta;
        String contenido = "";
        nombrearchivo = Utilities.getString("Introduce el nombre del archivo");
        extension = Utilities.getString("Introduce la extension del archivo");
        ruta = Utilities.getString("Introduce la ruta del archivo");
        char cara = 92;
        String path = ruta + Character.toString(cara) + nombrearchivo + "." + extension;
        mF = new File(path);
        if (!mF.exists()) {
            try {
                mbBuWr = new BufferedWriter(new FileWriter(mF));
                Utilities.P("Introduce el contenido del archivo.(FIN para acabar)");
                do {
                    Utilities.p("-->> ");
                    contenido = Utilities.getString();
                    if (!contenido.equals("FIN")) {
                        mbBuWr.write(contenido);
                        mbBuWr.newLine();
                    }
                } while (!contenido.equals("FIN"));

                mbBuWr.close();
                Utilities.P("Archivo creado correctamente.");
            } catch (IOException ex) {
                Utilities.P("Error: " + ex);
            }
        } else {
            Utilities.P("Ya existe un archivo con esas caracteríasticas.");
            boolean salir = false;
            do {
                int opcion = Utilities.MenuCrearArchivo();
                salir = ControladorSobreescribir(opcion, mF);

            } while (!salir);
        }

    }

    private static boolean ControladorSobreescribir(int op, File f) {
        boolean salir = false;
        String contenido = "";
        switch (op) {
            case 1:

                try {
                    mbBuWr = mbBuWr = new BufferedWriter(new FileWriter(f, true));
                } catch (IOException ex) {
                    Utilities.P("Error: " + ex);
                }

                salir = true;
                break;
            case 2:
                try {
                    mbBuWr = mbBuWr = new BufferedWriter(new FileWriter(f));
                } catch (IOException ex) {
                    Utilities.P("Error: " + ex);
                }

                salir = true;
                break;
            case 3:
                salir = true;
                break;
            default:
                Utilities.P("Opción incorrecta.");
        }
        if (op == 1 || op == 2) {
            try {

                Utilities.P("Introduce el contenido al archivo.(FIN para acabar)");
                do {
                    Utilities.p("-->> ");
                    contenido = Utilities.getString();
                    if (!contenido.equals("FIN")) {
                        mbBuWr.write(contenido);
                        mbBuWr.newLine();
                    }
                } while (!contenido.equals("FIN"));

                mbBuWr.close();
                Utilities.P("Archivo modificado correctamente.");
            } catch (IOException ex) {
                Utilities.P("Error: " + ex);
            }
        }
        return salir;
    }

    private static void CopiarArchivo() {
        String path;
        path = Utilities.getString("Introduce la ruta completa del archivo a copiar");
        mF = new File(path);
        path = Utilities.getString("Introduce la ruta completa donde copiar el archivo");
        File copia = new File(path);

        if (mF.exists()) {
            try {
                inputStr = new FileInputStream(mF);
                outStr = new FileOutputStream(copia);
                int n = 0;
                while ((n = inputStr.read()) > 0) {
                    outStr.write(n);
                }
                inputStr.close();
                outStr.close();
            } catch (IOException ex) {
                Utilities.P("Error: " + ex);
            }

        } else {
            Utilities.P("No existe el archivo a copiar.");
        }
    }

    private static boolean CopiarCarpetas(String pathorigen, String pathdestino) {
        mF = new File(pathorigen);
        boolean creada=false;
        File n;
        if (mF.exists()) {
            if (mF.isDirectory()) {
                comprobarCrearDirectorio(pathdestino);
                String[] datos = mF.list();

                for (String d : datos) {
                    n = new File(pathorigen + File.separator + d);
                    if (n.isDirectory()) {
                        comprobarCrearDirectorio(pathdestino + File.separator + d + File.separator);
                        CopiarCarpetas(pathorigen + File.separator + d + File.separator, pathdestino + File.separator + d + File.separator);

                    } else {
                        try {
                            inputStr = new FileInputStream(new File(pathorigen + File.separator + d));
                            outStr = new FileOutputStream(new File(pathdestino + File.separator + d));
                            int nume = 0;
                            byte[] buf = new byte[1024];
                            while ((nume = inputStr.read(buf)) > 0) {
                                outStr.write(buf, 0, nume);
                            }
                            inputStr.close();
                            outStr.close();
                        } catch (IOException ex) {
                            Utilities.P("Error: " + ex);
                        }
                    }
                }

            }
            creada=true;
        }else{
            Utilities.P("La ruta no coincide con ninguna carpeta.");
            creada=false;
        }
        return creada;
    }

    private static void comprobarCrearDirectorio(String ruta) {
        File directorio = new File(ruta);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }
}
