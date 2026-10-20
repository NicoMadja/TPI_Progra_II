import Implementaciones.Grafo;

public class Main {
    private static String setDeVecinos(Grafo g, String etiqueta) {
        int[] vecinos = g.vecinos(etiqueta);
        if (vecinos.length == 0) {
            return "{ }";
        }
        StringBuilder sb = new StringBuilder("{ ");
        for (int i = 0; i < vecinos.length; i++) {
            sb.append(g.etiquetaDe(vecinos[i]));
            if (i < vecinos.length - 1) sb.append(", ");
        }
        sb.append(" }");
        return sb.toString();
    }

    public static void main(String[] args) {
        Grafo g = new Grafo(8);

        System.out.println("=== Construccion del grafo (ciudades y rutas directas) ===");
        String[] ciudades = {
                "BuenosAires", "Rosario", "Cordoba", "Mendoza",
                "Salta", "Neuquen", "BahiaBlanca", "Ushuaia"
        };
        for (String c : ciudades) {
            g.agregarVertice(c);
        }
        System.out.println("Vertices agregados: " + g.cantidadVertices());

        g.agregarArista("BuenosAires", "Rosario");
        g.agregarArista("BuenosAires", "BahiaBlanca");
        g.agregarArista("BuenosAires", "Neuquen");
        g.agregarArista("Rosario", "Cordoba");
        g.agregarArista("Cordoba", "Mendoza");
        g.agregarArista("Cordoba", "Salta");
        g.agregarArista("Mendoza", "Neuquen");
        g.agregarArista("BahiaBlanca", "Neuquen");
        // Ushuaia queda sin ninguna arista: vertice aislado, a proposito
        // (se va a usar en la Clase 13 para mostrar componentes conexas).

        System.out.println();
        System.out.println("=== existeArista ===");
        System.out.println("existeArista(BuenosAires, Rosario) = " + g.existeArista("BuenosAires", "Rosario"));
        System.out.println("existeArista(BuenosAires, Cordoba) = " + g.existeArista("BuenosAires", "Cordoba"));
        System.out.println("existeArista(Cordoba, Salta) = " + g.existeArista("Cordoba", "Salta"));

        System.out.println();
        System.out.println("=== vecinos ===");
        System.out.println("vecinos(BuenosAires) = " + setDeVecinos(g, "BuenosAires"));
        System.out.println("vecinos(Cordoba) = " + setDeVecinos(g, "Cordoba"));
        System.out.println("vecinos(Ushuaia) = " + setDeVecinos(g, "Ushuaia"));

        System.out.println();
        System.out.println("=== cantidadVertices ===");
        System.out.println("cantidadVertices(g) = " + g.cantidadVertices());
    }
}