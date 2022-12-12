package com.cesur.examenaddicc22;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Ejercicio1 {

    /**
     * Enunciado:
     *
     * Completar el método estadísticasDeArchivo de manera que lea el archivo de
     * texto que se le pasa como parámetro, lo analice y muestre por consola el
     * número de caracteres, palabras y líneas total.
     *
     * Modificar solo el código del método.
     *
     */
    static void solucion() {

        estadísticasDeArchivo("pom.xml");
    }

    private static void estadísticasDeArchivo(String archivo) {

        int contPalabras = 0;
        int contCaracteres = 0;
        int contLineas = 0;
        try ( var fr = new BufferedReader(new FileReader(archivo));) {

            String contenidoTexto;

            while ((contenidoTexto = fr.readLine()) != null) {

                String[] palabras = contenidoTexto.split("[\\s.:()?!¿¡-]");

                //contar caracteres, sumamos al contador el numero de caracteres por palabra, que despues sumo al total global (ConCaracteres)
                for (String p : palabras) {
                    int cont = 0;
                    cont = p.length();
                    contCaracteres += cont;

                }

                //contar palabras, una vez separadas las palabras, cuento cada una de ellas y si es un espacio no lo cuento
                for (String p : palabras) {

                    if (" ".equals(p)) {
                        contPalabras += 0;
                    } else {
                        contPalabras += 1;
                    }
                }
            }

            //contar lineas
            FileReader fr1 = new FileReader(archivo);
            BufferedReader bf = new BufferedReader(fr1);
            contLineas = (int) bf.lines().count();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("El numero de palabras es de: " + contPalabras + "\n"
                + "El numero de caracteres es de: " + contCaracteres + "\n"
                + "El numero de lineas es de: " + contLineas);
    }

}
