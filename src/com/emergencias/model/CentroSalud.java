package com.emergencias.model;

public class CentroSalud {
    private String codigo;
    private String nombre;
    private String direccion;
    private String codigoPostal;
    private String municipio;
    private String pedania;
    private String telefono;
    private String email;
    private double latitud;
    private double longitud;

    // Constructor vacio
    public CentroSalud(){

    }

    // Constructor completo
    public CentroSalud(String codigo, String nombre, String direccion, String codigoPostal,
                       String municipio, String pedania, String telefono, String email,
                       double latitud, double longitud){

        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.municipio = municipio;
        this.pedania = pedania;
        this.telefono = telefono;
        this.email = email;
        this.latitud = latitud;
        this.longitud = longitud;


    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getPedania() {
        return pedania;
    }

    public void setPedania(String pedania) {
        this.pedania = pedania;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    // Calcular la distancia aproximada a este centro desde una ubicación
    // Usar la formula de Haversine (distancia en km)


    public double calcularDistancia(Ubicacion ubicacion){
        final int RADIO_TIERRA_KM = 6371;

        double lat1Rad = Math.toRadians(ubicacion.getLatitud());
        double lat2Rad = Math.toRadians(this.latitud);
        double deltaLat = Math.toRadians(this.latitud - ubicacion.getLatitud());
        double deltaLon = Math.toRadians(this.longitud - ubicacion.getLongitud());

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIO_TIERRA_KM * c;
    }

    // Mostrar información del centro de salud
    public void mostrarInfo() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println(" " + nombre);
        System.out.println("─".repeat(60));
        System.out.println(" Dirección: " + direccion);
        System.out.println("🏘  Municipio: " + municipio +
                (pedania != null && !pedania.isEmpty() ? " - " + pedania : ""));
        System.out.println(" C.P.: " + (codigoPostal != null ? codigoPostal : "N/A"));
        System.out.println(" Teléfono: " + (telefono != null ? telefono : "N/A"));
        System.out.println(" Email: " + (email != null && !email.isEmpty() ? email : "N/A"));
        System.out.println("  Coordenadas: Lat " + latitud + ", Lon " + longitud);
        System.out.println("─".repeat(60));
    }

    @Override
    public String toString() {
        return nombre + " - " + municipio + " (" + telefono + ")";
    }


}
