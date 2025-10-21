# Ejercicio 1
```java
import java.util.Random;
import java.util.Scanner;

/**
 * @autor Elsy Joselun Godinez Juarez
 * 
 * Objetivo:
 * Implementar operaciones básicas sobre una lista enlazada simple de números enteros positivos.
 */
public class Actividad1 {
    static class Nodo {
        int dato;
        Nodo siguiente;
        Nodo(int dato) { this.dato = dato; }
    }

    static class ListaEnlazada {
        Nodo cabeza;

        // Inserta un número al final
        void insertarFinal(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                Nodo actual = cabeza;
                while (actual.siguiente != null) actual = actual.siguiente;
                actual.siguiente = nuevo;
            }
        }

        // Muestra todos los elementos
        void mostrar() {
            Nodo actual = cabeza;
            if (actual == null) {
                System.out.println("La lista está vacía.");
                return;
            }
            System.out.print("Lista: ");
            while (actual != null) {
                System.out.print(actual.dato + " ");
                actual = actual.siguiente;
            }
            System.out.println();
        }

        // Elimina nodos mayores al valor límite
        void eliminarMayores(int limite) {
            while (cabeza != null && cabeza.dato > limite) {
                cabeza = cabeza.siguiente;
            }
            Nodo actual = cabeza;
            while (actual != null && actual.siguiente != null) {
                if (actual.siguiente.dato > limite) {
                    actual.siguiente = actual.siguiente.siguiente;
                } else {
                    actual = actual.siguiente;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaEnlazada lista = new ListaEnlazada();
        Random random = new Random();

        System.out.print("¿Cuántos números desea generar?: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int num = random.nextInt(100) + 1; // 1 a 100
            lista.insertarFinal(num);
        }

        System.out.println("\nLista generada:");
        lista.mostrar();

        System.out.print("\nIngrese el valor límite: ");
        int limite = sc.nextInt();
        lista.eliminarMayores(limite);

        System.out.println("\nLista después de eliminar mayores a " + limite + ":");
        lista.mostrar();
    }
}
```

# Ejecercicio 2
``` java
import java.io.*;
import java.util.Scanner;

/**
 * Actividad 02: Lista Enlazada de Palabras desde Archivo
 * 
 * Objetivo:
 * Leer palabras desde un archivo de texto, almacenarlas en una lista enlazada simple,
 * permitir al usuario añadir o eliminar palabras, y guardar nuevamente el contenido en el archivo.
 * 
 * Autor: Elsy Joselyn Godinez Juarez
 */
public class Actividad2 {

    // Clase Nodo: representa un elemento de la lista enlazada
    static class Nodo {
        String palabra;
        Nodo siguiente;

        Nodo(String palabra) {
            this.palabra = palabra;
            this.siguiente = null;
        }
    }

    // Clase ListaEnlazada: contiene las operaciones básicas
    static class ListaEnlazada {
        private Nodo cabeza;

        // Insertar al final
        public void insertar(String palabra) {
            Nodo nuevo = new Nodo(palabra);
            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                Nodo actual = cabeza;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
            }
        }

        // Mostrar toda la lista
        public void mostrar() {
            if (cabeza == null) {
                System.out.println("La lista está vacía.");
                return;
            }
            Nodo actual = cabeza;
            System.out.println("Palabras en la lista:");
            while (actual != null) {
                System.out.print(actual.palabra + " ");
                actual = actual.siguiente;
            }
            System.out.println();
        }

        // Eliminar una palabra específica
        public void eliminar(String palabra) {
            if (cabeza == null) {
                System.out.println("La lista está vacía.");
                return;
            }

            if (cabeza.palabra.equals(palabra)) {
                cabeza = cabeza.siguiente;
                System.out.println("Palabra eliminada correctamente.");
                return;
            }

            Nodo actual = cabeza;
            while (actual.siguiente != null && !actual.siguiente.palabra.equals(palabra)) {
                actual = actual.siguiente;
            }

            if (actual.siguiente != null) {
                actual.siguiente = actual.siguiente.siguiente;
                System.out.println("Palabra eliminada correctamente.");
            } else {
                System.out.println("Palabra no encontrada en la lista.");
            }
        }

        // Guardar todas las palabras en un archivo
        public void guardarEnArchivo(String nombreArchivo) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
                Nodo actual = cabeza;
                while (actual != null) {
                    bw.write(actual.palabra);
                    bw.write(" "); // separador entre palabras
                    actual = actual.siguiente;
                }
                System.out.println("Archivo actualizado correctamente.");
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaEnlazada lista = new ListaEnlazada();

        System.out.print("Ingrese el nombre del archivo (ejemplo: palabras.txt): ");
        String nombreArchivo = sc.nextLine();

        // 1. Lectura del archivo
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split("\\s+");
                for (String p : palabras) {
                    if (!p.isEmpty()) {
                        lista.insertar(p);
                    }
                }
            }
            System.out.println("Archivo leído correctamente.\n");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        // 2. Mostrar lista inicial
        lista.mostrar();

        // 3. Menú de manipulación dinámica
        int opcion;
        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar palabra");
            System.out.println("2. Eliminar palabra");
            System.out.println("3. Mostrar lista");
            System.out.println("4. Guardar y salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la palabra a agregar: ");
                    String nueva = sc.nextLine();
                    lista.insertar(nueva);
                    System.out.println("Palabra agregada correctamente.");
                    break;

                case 2:
                    System.out.print("Ingrese la palabra a eliminar: ");
                    String eliminar = sc.nextLine();
                    lista.eliminar(eliminar);
                    break;

                case 3:
                    lista.mostrar();
                    break;

                case 4:
                    lista.guardarEnArchivo(nombreArchivo);
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 4);

        sc.close();
    }
}
```

# Ejecicio 3
```java
import java.util.Scanner;

/**
 * Actividad 03: Representación y Evaluación de Polinomios con Listas Enlazadas
 *@autor Elsy Joselyn Godinez Juarez
public class Actividad3 {

    static class Nodo {
        double coeficiente;
        int exponente;
        Nodo siguiente;

        Nodo(double c, int e) {
            coeficiente = c;
            exponente = e;
        }
    }

    static class ListaPolinomio {
        Nodo cabeza;

        void insertar(double c, int e) {
            Nodo nuevo = new Nodo(c, e);
            if (cabeza == null) cabeza = nuevo;
            else {
                Nodo actual = cabeza;
                while (actual.siguiente != null) actual = actual.siguiente;
                actual.siguiente = nuevo;
            }
        }

        double evaluar(double x) {
            double resultado = 0;
            Nodo actual = cabeza;
            while (actual != null) {
                resultado += actual.coeficiente * Math.pow(x, actual.exponente);
                actual = actual.siguiente;
            }
            return resultado;
        }

        void mostrar() {
            Nodo actual = cabeza;
            System.out.print("P(x) = ");
            while (actual != null) {
                System.out.print(actual.coeficiente + "x^" + actual.exponente);
                if (actual.siguiente != null) System.out.print(" + ");
                actual = actual.siguiente;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaPolinomio poli = new ListaPolinomio();

        System.out.println("Ingrese los términos del polinomio (coeficiente exponente).");
        System.out.println("Escriba 'fin' para terminar.");
        while (true) {
            System.out.print("Coeficiente: ");
            String entrada = sc.next();
            if (entrada.equalsIgnoreCase("fin")) break;
            double c = Double.parseDouble(entrada);
            System.out.print("Exponente: ");
            int e = sc.nextInt();
            poli.insertar(c, e);
        }

        poli.mostrar();
        System.out.println("\nTabla de valores:");
        System.out.println("x\tP(x)");
        System.out.println("---------------");
        for (double x = 0.0; x <= 5.0; x += 0.5) {
            System.out.printf("%.1f\t%.2f%n", x, poli.evaluar(x));
        }
    }
}
```
# Ejercicio 4
``` java
import java.util.Scanner;

/**@autor Elsy Joselyn Godinez Juarez
 * Actividad 04: Polinomio con Lista Enlazada Circular
 */
public class Actividad4 {

    static class Nodo {
        double coeficiente;
        int exponente;
        Nodo siguiente;

        Nodo(double c, int e) {
            coeficiente = c;
            exponente = e;
        }
    }

    static class ListaCircular {
        Nodo ultimo;

        void insertar(double c, int e) {
            Nodo nuevo = new Nodo(c, e);
            if (ultimo == null) {
                ultimo = nuevo;
                ultimo.siguiente = nuevo;
            } else {
                nuevo.siguiente = ultimo.siguiente;
                ultimo.siguiente = nuevo;
                ultimo = nuevo;
            }
        }

        void mostrar() {
            if (ultimo == null) {
                System.out.println("Lista vacía.");
                return;
            }
            Nodo actual = ultimo.siguiente; // primero
            System.out.print("P(x) = ");
            do {
                System.out.print(actual.coeficiente + "x^" + actual.exponente);
                actual = actual.siguiente;
                if (actual != ultimo.siguiente) System.out.print(" + ");
            } while (actual != ultimo.siguiente);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaCircular lista = new ListaCircular();

        System.out.println("Ingrese coeficiente y exponente. Escriba 'fin' para terminar.");
        while (true) {
            System.out.print("Coeficiente: ");
            String cstr = sc.next();
            if (cstr.equalsIgnoreCase("fin")) break;
            double c = Double.parseDouble(cstr);
            System.out.print("Exponente: ");
            int e = sc.nextInt();
            lista.insertar(c, e);
        }

        System.out.println("\nRecorrido circular:");
        lista.mostrar();
    }
}
import java.util.Scanner;

/**
 * Actividad 05: Lista Doblemente Enlazada de Caracteres
 */
public class Actividad5 {

    static class Nodo {
        char caracter;
        Nodo anterior, siguiente;
        Nodo(char c) { caracter = c; }
    }

    static class ListaDoble {
        Nodo cabeza, cola;

        void insertar(char c) {
            Nodo nuevo = new Nodo(c);
            if (cabeza == null) {
                cabeza = cola = nuevo;
            } else {
                cola.siguiente = nuevo;
                nuevo.anterior = cola;
                cola = nuevo;
            }
        }

        void mostrar() {
            Nodo actual = cabeza;
            System.out.print("Lista: ");
            while (actual != null) {
                System.out.print(actual.caracter + " ");
                actual = actual.siguiente;
            }
            System.out.println();
        }

        // Ordenamiento burbuja por caracteres
        void ordenar() {
            if (cabeza == null) return;
            boolean cambiado;
            do {
                cambiado = false;
                Nodo actual = cabeza;
                while (actual.siguiente != null) {
                    if (actual.caracter > actual.siguiente.caracter) {
                        char temp = actual.caracter;
                        actual.caracter = actual.siguiente.caracter;
                        actual.siguiente.caracter = temp;
                        cambiado = true;
                    }
                    actual = actual.siguiente;
                }
            } while (cambiado);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaDoble lista = new ListaDoble();

        System.out.print("Ingrese una cadena: ");
        String cadena = sc.nextLine();

        for (char c : cadena.toCharArray()) {
            lista.insertar(c);
        }

        System.out.println("\nLista original:");
        lista.mostrar();

        lista.ordenar();

        System.out.println("\nLista ordenada alfabéticamente:");
        lista.mostrar();
    }
}
```

# Ejercicio 5
```java
import java.util.Scanner;

/**@autor Elsy Joselyn Godinez Juarez
 * Actividad 05: Lista Doblemente Enlazada de Caracteres
 */
public class Actividad5 {

    static class Nodo {
        char caracter;
        Nodo anterior, siguiente;
        Nodo(char c) { caracter = c; }
    }

    static class ListaDoble {
        Nodo cabeza, cola;

        void insertar(char c) {
            Nodo nuevo = new Nodo(c);
            if (cabeza == null) {
                cabeza = cola = nuevo;
            } else {
                cola.siguiente = nuevo;
                nuevo.anterior = cola;
                cola = nuevo;
            }
        }

        void mostrar() {
            Nodo actual = cabeza;
            System.out.print("Lista: ");
            while (actual != null) {
                System.out.print(actual.caracter + " ");
                actual = actual.siguiente;
            }
            System.out.println();
        }

        // Ordenamiento burbuja por caracteres
        void ordenar() {
            if (cabeza == null) return;
            boolean cambiado;
            do {
                cambiado = false;
                Nodo actual = cabeza;
                while (actual.siguiente != null) {
                    if (actual.caracter > actual.siguiente.caracter) {
                        char temp = actual.caracter;
                        actual.caracter = actual.siguiente.caracter;
                        actual.siguiente.caracter = temp;
                        cambiado = true;
                    }
                    actual = actual.siguiente;
                }
            } while (cambiado);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaDoble lista = new ListaDoble();

        System.out.print("Ingrese una cadena: ");
        String cadena = sc.nextLine();

        for (char c : cadena.toCharArray()) {
            lista.insertar(c);
        }

        System.out.println("\nLista original:");
        lista.mostrar();

        lista.ordenar();

        System.out.println("\nLista ordenada alfabéticamente:");
        lista.mostrar();
    }
}
```
