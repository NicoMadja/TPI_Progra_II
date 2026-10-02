package TDAs;

public abstract class GrafoTDA {
    // Dominio:
    // Grafo = .

    // Operaciones:
    // crear(n); --> Lo hace el constructor.
    // post: devuelve un grafo vacío con capacidad para n vértices.

    public abstract void agregarVertice(String etiqueta);
    // pre:
    // post:

    public abstract void agregarArista(String v1, String v2);
    // pre:
    // post:

    public abstract boolean existeArista(String v1, String v2);
    // pre:
    // post:

    public abstract ListaTDA vecinos(String v);
    // pre:
    // post:

    public abstract int cantidadVertices();
    // pre:
    // post:
}
