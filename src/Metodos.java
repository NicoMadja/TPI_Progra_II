import Implementaciones.Cola;
import Implementaciones.Grafo;

public class Metodos {


    public static int componentesConexas(Grafo g) {
        int n = g.cantidadVertices();
        boolean[] visitado = new boolean[n];
        int componentes = 0;

        for (int inicio = 0; inicio < n; inicio++) {
            if (!visitado[inicio]) {
                componentes++;
                Cola cola = new Cola(n);
                cola.encolar(inicio);
                visitado[inicio] = true;

                while (!cola.esVacia()) {
                    int actual = cola.primero();
                    cola.desencolar();
                    int[] vecinos = g.vecinosPorIndice(actual);
                    for (int i = 0; i < vecinos.length; i++) {
                        int w = vecinos[i];
                        if (!visitado[w]) {
                            visitado[w] = true;
                            cola.encolar(w);
                        }
                    }
                }
            }
        }
        return componentes;

    }
}
