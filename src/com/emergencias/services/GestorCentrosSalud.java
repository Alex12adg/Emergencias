package com.emergencias.services;

import com.emergencias.model.CentroSalud;
import com.emergencias.model.Ubicacion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Gestor para cargar y buscar centros de salud desde un archivo JSON
 */
public class GestorCentrosSalud {

    private ArrayList<CentroSalud> centros;

    public GestorCentrosSalud() {
        this.centros = new ArrayList<>();
    }

    // Cargar los centros de salud desde un archivo JSON
    public boolean cargarDesdeJSON(String rutaArchivo) {

        try {

            File archivo = new File(rutaArchivo);

            if (!archivo.exists()) {
                System.out.println("No se encontró el archivo: " + rutaArchivo);
                return false;
            }

            // Leer todo el archivo como texto
            StringBuilder contenido = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(archivo));

            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea);
            }

            br.close();

            // Convertir texto a JSONArray
            JSONArray jsonArray = new JSONArray(contenido.toString());

            // Vaciar lista anterior por seguridad
            centros.clear();

            // Recorrer cada objeto del array
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);

                // Crear objeto CentroSalud manualmente
                CentroSalud centro = new CentroSalud();  // usar constructor vacío

                centro.setNombre(obj.getString("nombre"));
                centro.setMunicipio(obj.getString("municipio"));
                centro.setDireccion(obj.getString("direccion"));
                centro.setTelefono(obj.getString("telefono"));
                centro.setLatitud(obj.getDouble("latitud"));
                centro.setLongitud(obj.getDouble("longitud"));

                centros.add(centro);
            }

            System.out.println("Se cargaron " + centros.size() + " centros de salud");
            return true;

        } catch (Exception e) {
            System.out.println("Error al cargar el JSON: " + e.getMessage());
            return false;
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

    // Obtiene los N centros más cercanos a una ubicación
    public ArrayList<CentroSalud> obtenerCentrosCercanos(Ubicacion ubicacion, int cantidad) {

        ArrayList<CentroSalud> copia = new ArrayList<>(centros);

        // Ordenar por distancia (método simple compatible)
        copia.sort((c1, c2) -> {
            double d1 = c1.calcularDistancia(ubicacion);
            double d2 = c2.calcularDistancia(ubicacion);
            return Double.compare(d1, d2);
        });

        ArrayList<CentroSalud> resultado = new ArrayList<>();

        for (int i = 0; i < copia.size() && i < cantidad; i++) {
            resultado.add(copia.get(i));
        }

        return resultado;
    }

    // Muestra todos los centros cargados
    public void mostrarTodos() {

        if (centros.isEmpty()) {
            System.out.println("No hay centros de salud cargados");
            return;
        }

        System.out.println("\nCENTROS DE SALUD DISPONIBLES (" + centros.size() + ")");
        System.out.println("=".repeat(80));

        for (int i = 0; i < centros.size(); i++) {
            CentroSalud centro = centros.get(i);
            System.out.printf("%3d. %-40s %s\n",
                    (i + 1),
                    centro.getNombre(),
                    centro.getMunicipio());
        }
    }

    public int getTotalCentros() {
        return centros.size();
    }
}




