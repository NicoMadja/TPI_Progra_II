package Implementaciones;

public class Grafo {
    private boolean[][] adyacencia;
    private String[] etiquetas;   // etiquetas[i] = etiqueta del vertice de indice i
    private Diccionario indices;  // etiqueta -> indice
    private int capacidad;
    private int cantidadVertices;

    public Grafo(int n) {
        this.capacidad = n;
        this.adyacencia = new boolean[n][n];
        this.etiquetas = new String[n];
        this.indices = new Diccionario();
        this.cantidadVertices = 0;
    }

    public int cantidadVertices() {
        return cantidadVertices;
    }

    public void agregarVertice(String etiqueta) {
        if (cantidadVertices == capacidad) {
            throw new RuntimeException("Grafo lleno");
        }
        if (indices.existeClave(etiqueta)) {
            throw new RuntimeException("La etiqueta ya existe: " + etiqueta);
        }
        indices.insertar(etiqueta, cantidadVertices);
        etiquetas[cantidadVertices] = etiqueta;
        cantidadVertices++;
    }

    public void agregarArista(String v1, String v2) {
        int i = indices.obtener(v1);
        int j = indices.obtener(v2);
        adyacencia[i][j] = true;
        adyacencia[j][i] = true;
    }

    public boolean existeArista(String v1, String v2) {
        int i = indices.obtener(v1);
        int j = indices.obtener(v2);
        return adyacencia[i][j];
    }

    public int[] vecinos(String etiqueta) {
        return vecinosPorIndice(indices.obtener(etiqueta));
    }

    private int[] vecinosPorIndice(int v) {
        int cant = 0;
        for (int j = 0; j < cantidadVertices; j++) {
            if (adyacencia[v][j]) {
                cant++;
            }
        }
        int[] resultado = new int[cant];
        int k = 0;
        for (int j = 0; j < cantidadVertices; j++) {
            if (adyacencia[v][j]) {
                resultado[k] = j;
                k++;
            }
        }
        return resultado;
    }

    private String etiquetaDe(int indice) {
        return etiquetas[indice];
    }

    private int indiceDe(String etiqueta) {
        return indices.obtener(etiqueta);
    }
}
