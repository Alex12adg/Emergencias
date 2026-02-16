package com.emergencias.services;


import com.emergencias.model.CentroSalud;
import com.emergencias.model.Ubicacion;
import jdk.internal.icu.text.UnicodeSet;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



// Gestor para cargar y buscar centros de salud desde un archivo JSON
public class GestorCentrosSalud {
    private ArrayList<CentroSalud> centros;

    public GestorCentrosSalud() {
        this.centros = new ArrayList<>();
    }





// Cargar los centros de salud desde un archivo JSON

    public boolean cargarDesdeJSON(String rutaArchivo) {

        // Leer el contenido del archivo
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));

            //"./src/com/emergencias/data/centros_salud.json"

            // Parsear JSON
            JSONArray jsonArray = new JSONArray(contenido);

            // Procesar cada centro de salud
            this.centros.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                CentroSalud centro = new CentroSalud();
                centro.setCodigo(obj.optString("Código", ""));
                centro.setNombre(obj.optString("Nombre", ""));
                centro.setDireccion(obj.optString("Dirección", ""));
                centro.setCodigoPostal(obj.optString("C.P.", ""));
                centro.setMunicipio(obj.optString("Municipio", ""));
                centro.setPedania(obj.optString("Pedanía", ""));
                centro.setTelefono(obj.optString("Teléfono", ""));
                centro.setEmail(obj.optString("Email", ""));

                // Parsear coordenadas (pueden venir en diferentes formatos)
                try {
                    String latStr = obj.optString("Latitud", "0");
                    String lonStr = obj.optString("Longitud", "0");

                    // Convertir coordenadas
                    double lat = parseCoordinate(latStr);
                    double lon = parseCoordinate(lonStr);

                    centro.setLatitud(lat);
                    centro.setLongitud(lon);
                } catch (Exception e) {
                    centro.setLatitud(0.0);
                    centro.setLongitud(0.0);
                }

                this.centros.add(centro);
            }

            System.out.println("✓ Se cargaron " + centros.size() + " centros de salud");
            return true;

        } catch (IOException e) {
            System.out.println(" Error al leer el archivo: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(" Error al procesar JSON: " + e.getMessage());
            return false;
        }
    }

    // Convierte las coordenadas en diferentes formatos
    private double parseCoordinate(String coord) {
        if (coord == null || coord.isEmpty()) return 0.0;

        try {
            // Si empieza con "-", es formato decimal
            if (coord.startsWith("-")) {
                return Double.parseDouble(coord);
            }

            // Si es un número grande (formato UTM), convertir
            double value = Double.parseDouble(coord);
            if (value > 360) {
                // Coordenadas UTM - conversión aproximada
                return value / 100000.0;
            }

            return value;
        } catch (Exception e) {
            return 0.0;
        }


    }

    // Busca centros de Salud por municipio
    public ArrayList<CentroSalud> buscarPorMunicipio(String municipio) {
        ArrayList<CentroSalud> resultados = new ArrayList<>();

        for (CentroSalud centro : centros) {
            if (centro.getMunicipio().toLowerCase().contains(municipio.toLowerCase())) {
                resultados.add(centro);
            }
        }
        return resultados;
    }

    // Buscar centro de salud por nombre
    public ArrayList<CentroSalud> buscarPorNombre(String nombre) {
        ArrayList<CentroSalud> resultados = new ArrayList<>();

        for (CentroSalud centro : centros) {
            if (centro.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(centro);
            }
        }

        return resultados;
    }

// Encuentra el centro de salud más cercano por ubicacion

    public CentroSalud encontrarMasCercano(Ubicacion ubicacion) {
        if (centros.isEmpty()) return null;

        CentroSalud masCercano = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (CentroSalud centro : centros) {
            // Saltar centros sin coordenadas válidas
            if (centro.getLatitud() == 0.0 && centro.getLongitud() == 0.0) {
                continue;
            }

            double distancia = centro.calcularDistancia(ubicacion);

            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                masCercano = centro;
            }
        }

        return masCercano;
    }
// Obten los N centros mas cercanos a una ubicación

    public ArrayList<CentroSalud> obtenerCentrosCercanos(Ubicacion ubicacion, int cantidad) {
        ArrayList<CentroSalud> centrosConDistancia = new ArrayList<>();

        // Filtrar centros con coordenadas válidas
        for (CentroSalud centro : centros) {
            if (centro.getLatitud() != 0.0 || centro.getLongitud() != 0.0) {
                centrosConDistancia.add(centro);
            }
        }

        // Ordenar por distancia
        centrosConDistancia.sort((c1, c2) ->
                Double.compare(c1.calcularDistancia(ubicacion),
                        c2.calcularDistancia(ubicacion))
        );

        // Retornar solo los primeros N
        int limite = Math.min(cantidad, centrosConDistancia.size());
        return new ArrayList<>(centrosConDistancia.subList(0, limite));
    }

//Muestra todos los centro cargados

    public void mostrarTodos() {
        if (centros.isEmpty()) {
            System.out.println(" No hay centros de salud cargados");
            return;
        }

        System.out.println("\n CENTROS DE SALUD DISPONIBLES (" + centros.size() + ")");
        System.out.println("=".repeat(80));

        for (int i = 0; i < centros.size(); i++) {
            CentroSalud centro = centros.get(i);
            System.out.printf("%3d. %-40s %s\n",
                    (i + 1),
                    centro.getNombre(),
                    centro.getMunicipio());
        }

    }

// Obtiene el total de centros cargados

    public int getTotalCentros() {
        return centros.size();
    }

    // Obtiene el centro por su indice
    public CentroSalud getCentro(int indice) {
        if (indice >= 0 && indice < centros.size()) {
            return centros.get(indice);
        }
        return null;
    }
}




