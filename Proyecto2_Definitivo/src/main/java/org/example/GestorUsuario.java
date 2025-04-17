package org.example;

import java.io.IOException;
import java.util.Arrays;

public class GestorUsuario {
    private Usuario[] usuarios;

    public GestorUsuario() throws IOException {
        FicheroObjetosLeer ficheroUsuarios = new FicheroObjetosLeer("usuarios.dat");
        usuarios = ficheroUsuarios.cargarUsuarios();
    }

    public void guardarUsuarios() throws IOException {
        FicheroObjetosEscribir fue = new FicheroObjetosEscribir("usuarios.dat");
        fue.writeArrayUsuario(usuarios);
        fue.close();
    }

    public Usuario crearUsuario() throws IOException {
        //Texto
        System.out.println("Nombre: ");
        String nombre = Main.pedirString();
        System.out.println("Apellidos: ");
        String apellido = Main.pedirString();
        System.out.println("DNI: ");
        String dni = Main.pedirString();
        System.out.println("¿Es usted residente en Baleares? s/n");
        boolean residente = Main.opcion(Main.pedirCar());
        System.out.println("Contraseña: ");
        String password = Main.pedirString();
        System.out.println("¿Eres administrador de SkyPauScanner? s/n");
        boolean administrador = Main.opcion(Main.pedirCar());


        Usuario usuario = new Usuario(nombre,apellido,dni,password,residente, administrador);
        if (comprobarUsuario(nombre,password) != null) {
            System.out.println("YA EXISTE EL USUARIO");
            return null;
        }else {
            addUsuario(usuario);
            return usuario;
        }
    }

    public Usuario iniciarSesion() throws IOException {
        //Texto
        System.out.println("Nombre: ");
        String nombre = Main.pedirString();
        System.out.println("Password: ");
        String password = Main.pedirString();


        Usuario usuario = comprobarUsuario(nombre, password);
        if (usuario != null ) {
            return usuario;
        }else {
            System.out.println("EL NOMBRE O LA CONTRASEÑA SON INCORRECTAS");
            return null;
        }
    }

    public Usuario comprobarUsuario(String nombre, String password) throws IOException {
        for (int i=0; i<usuarios.length; i++){
            if (usuarios[i].getNombre().equals(nombre)){
                if (usuarios[i].getPassword().equals(password)){return usuarios[i];}
            }
        }
        return null;
    }

    public void addUsuario(Usuario usuario) {
        usuarios = Arrays.copyOf(usuarios, usuarios.length + 1);
        usuarios[usuarios.length - 1] = usuario;
    }





}
