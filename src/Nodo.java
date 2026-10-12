public class Nodo {


    private String token;
    private Nodo siguiente;

    public Nodo(String token) {
        this.token = token;
        this.siguiente = null;
    }

    public String getToken() {
        return token;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

