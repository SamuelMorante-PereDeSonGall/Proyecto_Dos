package org.example;

import java.io.*;

/**
 *
 * @author Samuel Morante Pioreck
 */
public class FicheroObjetosEscribir {

    /**
     * Los atributos son el flujo de salida del fichero y los de objetos
     */
    private FileOutputStream fos;
    private ObjectOutputStream us;

    /**
     * Constructor del fichero a partir de la cadena con el nombre del fichero si
     * se producen devuelve al padre
     *
     * @param nombreFichero
     * @throws java.io.FileNotFoundException
     */
    public FicheroObjetosEscribir(String nombreFichero) throws FileNotFoundException, IOException {
        File archivo = new File(nombreFichero);
        if (archivo.exists()) {
            archivo.delete();
        }
        fos = new FileOutputStream(archivo); //Modo append
        us = new ObjectOutputStream(fos);
    }

    /**
     * Escribe un objeto Resultado al fichero
     *
     * @param u
     * @throws java.io.IOException
     */
    public void write(Usuario u) throws IOException {
        us.writeObject(u);
    }

    public void write(Vuelo v) throws IOException {
        us.writeObject(v);
    }

    //Escribe en la lista resultado
    public void writeArrayUsuario(Usuario[] usuarios) throws IOException {
        for (Usuario usuario : usuarios) {
            us.writeObject(usuario);
        }
    }

    //Escribe en la lista resultado
    public void writeArrayVuelo(Vuelo[] vuelos) throws IOException {
        for (Vuelo vuelo : vuelos) {
            us.writeObject(vuelo);
        }
    }

    /**
     * Cierra el fichero
     * @throws java.io.IOException
     */
    public void close() throws IOException {
        us.close();
    }

}

