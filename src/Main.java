import Implementaciones.Cola;
import Implementaciones.Grafo;
import Implementaciones.Pila;
import java.util.Arrays;

public class Main {
    // # Aclaración: utilizamos -Arrays.toString- para imprimir los nombres de los vértices en consola.

    // --- BUSQUEDAS ---
    // Traductor de números enteros a nombres de ciudades
    private static String[] traducir(Grafo g, int[] orden, int cantidad) {
        String[] resultado = new String[cantidad];
        for (int i = 0; i < cantidad; i++) {
            resultado[i] = g.etiquetaDe(orden[i]);
        }
        return resultado;
    }

    // BFS
    public static String[] BFS(Grafo g, String origen) {
        int n = g.cantidadVertices();
        int[] orden = new int[n];
        int cantOrden = 0;
        boolean[] visitado = new boolean[n];
        Cola cola = new Cola(n);

        int inicio = g.indiceDe(origen);
        cola.encolar(inicio);
        visitado[inicio] = true;

        while (!cola.esVacia()) {
            int actual = cola.primero();
            cola.desencolar();
            orden[cantOrden] = actual;
            cantOrden++;

            int[] vecinos = g.vecinosPorIndice(actual);
            for (int i = 0; i < vecinos.length; i++) {
                int w = vecinos[i];
                if (!visitado[w]) {
                    visitado[w] = true;
                    cola.encolar(w);
                }
            }
        }
        return traducir(g, orden, cantOrden);
    }

    // DFS (Recursivo)
    public static String[] DFS(Grafo g, String origen) {
        int n = g.cantidadVertices();
        int[] orden = new int[n];
        int[] cantOrden = new int[] { 0 };
        boolean[] visitado = new boolean[n];
        DFSAux(g, g.indiceDe(origen), visitado, orden, cantOrden);
        return traducir(g, orden, cantOrden[0]);
    }
    private static void DFSAux(Grafo g, int v, boolean[] visitado, int[] orden, int[] cantOrden) {
        visitado[v] = true;
        orden[cantOrden[0]] = v;
        cantOrden[0]++;

        int[] vecinos = g.vecinosPorIndice(v);
        for (int i = 0; i < vecinos.length; i++) {
            int w = vecinos[i];
            if (!visitado[w]) {
                DFSAux(g, w, visitado, orden, cantOrden);
            }
        }
    }

    // DFSI (Iterativo)
    public static String[] DFSI(Grafo g, String origen) {
        int n = g.cantidadVertices();
        int[] orden = new int[n];
        int cantOrden = 0;
        boolean[] visitado = new boolean[n];
        Pila pila = new Pila(n * n + 1);

        pila.apilar(g.indiceDe(origen));
        while (!pila.esVacia()) {
            int actual = pila.tope();
            pila.desapilar();
            if (!visitado[actual]) {
                visitado[actual] = true;
                orden[cantOrden] = actual;
                cantOrden++;

                int[] vecinos = g.vecinosPorIndice(actual);
                for (int i = 0; i < vecinos.length; i++) {
                    if (!visitado[vecinos[i]]) {
                        pila.apilar(vecinos[i]);
                    }
                }
            }
        }
        return traducir(g, orden, cantOrden);
    }

    // --- CONSTRUCCION DEL GRAFO, LLAMADAS DE BUSQUEDAS Y DE MÉTODOS ---
    public static void main(String[] args) {
        Metodos m = new Metodos();
        Grafo g = new Grafo(8);
        System.out.println("=== Construyendo grafo de ciudades.");
        System.out.println("    Construyendo grafo de ciudades..");
        System.out.println("    Construyendo grafo de ciudades...");
        String[] ciudades = {"BuenosAires", "Rosario", "Cordoba", "Mendoza", "Salta", "Neuquen", "BahiaBlanca", "Ushuaia"};
        for (String c : ciudades) g.agregarVertice(c);
        g.agregarArista("BuenosAires", "Rosario");
        g.agregarArista("BuenosAires", "BahiaBlanca");
        g.agregarArista("BuenosAires", "Neuquen");
        g.agregarArista("Rosario", "Cordoba");
        g.agregarArista("Cordoba", "Mendoza");
        g.agregarArista("Cordoba", "Salta");
        g.agregarArista("Mendoza", "Neuquen");
        g.agregarArista("BahiaBlanca", "Neuquen");
        // Ushuaia queda sin ninguna arista a propósito. Es un vertice aislado.
        System.out.println();
        System.out.println("=== Grafo construído con éxito. Vértices agregados: " + g.cantidadVertices());
        System.out.println();
        System.out.println("=== Búsqueda BFS partiendo desde BuenosAires:");
        System.out.println(Arrays.toString(BFS(g, "BuenosAires")));
        System.out.println();
        System.out.println("=== Búsqueda DFS Recursivo partiendo desde BuenosAires:");
        System.out.println(Arrays.toString(DFS(g, "BuenosAires")));
        System.out.println();
        System.out.println("=== Búsqueda DFS Iterativo partiendo desde BuenosAires:");
        System.out.println(Arrays.toString(DFSI(g, "BuenosAires")));
        System.out.println();

        System.out.println("=== Métodos:");
        System.out.println("=== Componentes conexas:");
        System.out.println(m.componentesConexas(g));
        System.out.println();
        System.out.println("=== Estan conectados:");
        System.out.println("BuenosAires - Mendoza: " + m.estanConectados(g, "BuenosAires", "Mendoza")); // true
        System.out.println("Rosario - BahiaBlanca: " + m.estanConectados(g, "Rosario", "BahiaBlanca")); // true
        System.out.println("BuenosAires - Ushuaia: " + m.estanConectados(g, "BuenosAires", "Ushuaia")); // false
        System.out.println("Salta - Cordoba: " + m.estanConectados(g, "Salta", "Cordoba"));
    }
}