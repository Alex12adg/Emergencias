package com.emergencias.model;
// Clase que almacena datos basicos del usuario
public class UserData {
    private String nombre;// Nombre de usuario
    private String telefono; // Telefono de contacto
    public  UserData(String nombre, String telefono){ // Constructor para inicializar los datos del usuario
        this.nombre = nombre;
        this.telefono= telefono;
    }

    // Getter para el mombre y el telefono
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {  // Devuelve representación en texto de los datos del usuario
        return nombre +" ("+ telefono +")";

    }
}
