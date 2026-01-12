package com.emergencias.model;
// Clase que almacena datos basicos del usuario
public class UserData {
    private String nombre;// Nombre de usuario
    private String telefono;// Telefono de contacto
    private PerfilMedico perfilMedico; // Perfil medico
    public  UserData(String nombre, String telefono){ // Constructor para inicializar los datos del usuario
        this.nombre = nombre;
        this.telefono= telefono;
        this.perfilMedico = new PerfilMedico(); // Inicializar perfil medico
    }

    // Getter para el mombre y el telefono
    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public PerfilMedico getPerfilMedico() {
        return perfilMedico;
    }

    @Override
    public String toString() {  // Devuelve representación en texto de los datos del usuario
        return nombre +" ("+ telefono +")";

    }
}
