package com.emergencias.model;
// Clase que representa la ubicación geografica con coordenadas
public class Ubicacion {
    private  double latitud; // latitud de la ubicación
    private  double longitud;// longitud de la ubicación

    public Ubicacion(double latitud, double longitud) { // Constructor que inicializa la ubicación
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {  // getter para la latitud
        return latitud;
    }

    public double getLongitud() { // getter para la longitud
        return longitud;
    }

    @Override
    public String toString() {  // Devuelve representación  en texto de la ubicacion
        return "Lat:" + latitud +
                ",Long:" + longitud;
    }
}

