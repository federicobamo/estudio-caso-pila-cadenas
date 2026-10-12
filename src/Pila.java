

public class Pila {

    private Nodo cima;
    private int tamanio;

    public Pila() {
        cima = null;
        tamanio = 0;
    }

    // Agrega un token en la cima
    public void push(String token) {
        Nodo nuevo = new Nodo(token);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tamanio++;

    }

    // Saca y devuelve el token de la cima
    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("La pila está vacía.");
        }
        String token = cima.getToken();
        cima = cima.getSiguiente();
        tamanio--;
        return token;
    }

    // Devuelve el token de la cima sin sacarlo
    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("La pila está vacía.");
        }
        return cima.getToken();
    }

    // Saca los dos tokens de arriba, los une y apila el resultado.
    // El primer pop es el operando derecho y el segundo es el izquierdo.
    public void concatenar() {
        if (tamanio < 2) {
            throw new RuntimeException("Se necesitan dos tokens para concatenar.");
        }
        String derecho = pop();
        String izquierdo = pop();
        push(izquierdo + derecho);
    }

    public boolean isEmpty() {
        return cima == null;
    }

    public int size() {
        return tamanio;
    }

    // Vacía la pila
    public void clear() {
        cima = null;
        tamanio = 0;
    }

    // Muestra la pila desde la cima hasta el fondo
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("[ pila vacía ]");
            return;
        }
        Nodo actual = cima;
        while (actual != null) {
            System.out.println("| \"" + actual.getToken() + "\" |");
            actual = actual.getSiguiente();
        }
        System.out.println("+-----------+");
    }

    // Concatena todos los tokens de la pila hasta que quede uno solo
    public String concatenarTodo() {
        if (isEmpty()) {
            throw new RuntimeException("La pila está vacía.");
        }
        while (tamanio > 1) {
            concatenar();
        }
        return peek();
    }
}
