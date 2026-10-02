package TDAs;

public abstract class GrafoTDA {
    // Dominio:
    // Grafo = conjunto de vértices identificados por etiquetas y conectados por aristas sin dirección ni peso.

    // Operaciones:
    // crear(n); --> Lo hace el constructor.
    // pre: n debe ser < 0.
    // post: devuelve un grafo vacío con capacidad para n vértices.

    public abstract void agregarVertice(String etiqueta);
    // pre: no existe un vértice con la misma etiqueta.
    // post: g contiene el nuevo vértice.

    public abstract void agregarArista(String v1, String v2);
    // pre: v1 y v2 existen en g.
    // post: v1 y v2 se encuentran conectados por la nueva arista.

    public abstract boolean existeArista(String v1, String v2);
    // pre: v1 y v2 existen en g.
    // post: devuelve true si existe una arista entre v1 y v2, false en caso contrario.

    public abstract ListaTDA vecinos(String v);
    // pre: v existe en g.
    // post: devuelve una lista con todas las etiquetas de los vértices conectados con v.

    public abstract int cantidadVertices();
    // post: devuelve la cantidad de vértices en g.
}
