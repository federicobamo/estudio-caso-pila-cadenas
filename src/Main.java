import java.util.HashMap;
import java.util.Scanner;

/**
 * Programa principal: menú para usar la pila y analizar cadenas de impresión.
 */

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Pila pila = new Pila();
    static HashMap<String, String> variables = new HashMap<>();

    public static void main(String[] args) {
        variables.put("nombre", "Federico");
        variables.put("apellido", "Barón");

        int opcion;
        do {
            opcion = menu();
            try {
                switch (opcion) {
                    case 1: analizar();
                    break;
                    case 2:
                        System.out.print("Token a agregar: ");
                        pila.push(sc.nextLine());
                        pila.mostrar();
                        break;
                    case 3: System.out.println("Se sacó: " + pila.pop());
                    break;
                    case 4: System.out.println("Cima: " + pila.peek());
                    break;
                    case 5: pila.mostrar();
                    break;
                    case 6: System.out.println("Salida en pantalla: " + pila.concatenarTodo());
                    break;
                    case 0: System.out.println("¡Hasta luego!");
                    break;
                    default: System.out.println("Opción inválida.");
                }
            } catch (RuntimeException e) {
                // Muestra el error sin cerrar el programa
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    // Muestra el menú y devuelve la opción elegida
    public static int menu() {
        System.out.println("\n===== ANALIZADOR DE CADENAS DE IMPRESIÓN =====");
        System.out.println("1. Analizar instrucción de impresión");
        System.out.println("2. Push (agregar token)");
        System.out.println("3. Pop (sacar cima)");
        System.out.println("4. Peek (ver cima)");
        System.out.println("5. Mostrar pila");
        System.out.println("6. Concatenar toda la pila (imprimir)");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Si no es un número, cuenta como opción inválida
        }
    }

    // Analiza una expresión como: "Hola, " + nombre + "!"
    public static void analizar() {
        System.out.print("Expresión (ej: \"Hola, \" + nombre + \"!\"): ");
        String[] tokens = sc.nextLine().split("\\+", -1); // Separa por el operador +
        Pila p = new Pila();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i].trim();

            if (token.startsWith("\"") && token.endsWith("\"") && token.length() >= 2) {
                p.push(token.substring(1, token.length() - 1)); // Cadena literal sin comillas
            } else if (variables.containsKey(token)) {
                p.push(variables.get(token));                   // Valor de la variable
            } else {
                throw new RuntimeException("Token inválido: '" + token + "'");
            }

            System.out.println("push(\"" + p.peek() + "\")");

            if (i > 0) {
                p.concatenar(); // Cada token después del primero viene después de un +
            }
            p.mostrar();
        }

        System.out.println("Salida en pantalla: " + p.pop());
    }
}