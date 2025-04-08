import java.io.*;
import java.util.List;

/**
 *
 * @author Samuel Morante Pioreck
 */
public class FicheroUsuariosEscritura {

    /**
     * Los atributos son el flujo de salida del fichero y los de objetos
     */
    private FileOutputStream fos;
    private ObjectOutputStream f;

    /**
     * Constructor del fichero a partir de la cadena con el nombre del fichero si
     * se producen devuelve al padre
     *
     * @param nombreFichero
     * @throws java.io.FileNotFoundException
     */
    public FicheroUsuariosEscritura(String nombreFichero) throws FileNotFoundException, IOException {
        File archivo = new File(nombreFichero);
        fos = new FileOutputStream(archivo,true); //Modo append

        if (archivo.length() == 0){
            f = new ObjectOutputStream(fos);
        }else {
            f = new MiObjectOutputStream(fos);
        }

    }

    /**
     * Escribe un objeto Resultado al fichero
     *
     * @param u
     * @throws java.io.IOException
     */
    public void write(Usuario u) throws IOException {
        f.writeObject(u);
    }

    public void write(Vuelo v) throws IOException {
        f.writeObject(v);
    }

    //Escribe en la lista resultado
    public void writeListaUsuario(List<Usuario> listaResultados) throws IOException {
        for (Usuario usuario : listaResultados) {
            f.writeObject(usuario);
        }
    }

    //Escribe en la lista resultado
    public void writeListaVuelo(List<Vuelo> listaResultados) throws IOException {
        for (Vuelo vuelo : listaResultados) {
            f.writeObject(vuelo);
        }
    }

    /**
     * Cierra el fichero
     * @throws java.io.IOException
     */
    public void close() throws IOException {
        f.close();
    }
}

    class MiObjectOutputStream extends ObjectOutputStream {
    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset(); // No escribe cabecera
    }
}
