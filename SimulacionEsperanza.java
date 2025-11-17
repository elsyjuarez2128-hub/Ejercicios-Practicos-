import java.util.*;
*/
*@autor Elsy Joselyn Godinez Juarez
*GTID141
*Ejercicio 3: Simulación de atención al cliente en supermercado
*Esperanza
  
public class SimulacionEsperanza {

    private Queue<Cliente> fila;
    private CajaAtencion[] cajas;
    private boolean caja4Activa;

    private int totalAtendidos;
    private int maxFila;
    private int sumaFila;
    private int maxEspera;
    private int minutosCaja4Activa;

    private Random r = new Random();

    public SimulacionEsperanza() {
        fila = new LinkedList<>();
        cajas = new CajaAtencion[]{ new CajaAtencion(), new CajaAtencion(), new CajaAtencion(), new CajaAtencion() };
        caja4Activa = false;
    }

    public void simular() {
        int id = 1;

        for (int minuto = 0; minuto < 420; minuto++) {

            fila.offer(new Cliente(id, minuto));
            id++;

            sumaFila += fila.size();
            maxFila = Math.max(maxFila, fila.size());

            if (fila.size() > 20) caja4Activa = true;
            if (caja4Activa) minutosCaja4Activa++;

            int cajasActivas = caja4Activa ? 4 : 3;

            for (int i = 0; i < cajasActivas; i++) {
                if (cajas[i].estaLibre() && !fila.isEmpty()) {
                    Cliente c = fila.poll();
                    int espera = minuto - c.getTiempoLlegada();
                    maxEspera = Math.max(maxEspera, espera);

                    int tiempo = r.nextInt(4) + 2; // 2 a 5 minutos
                    cajas[i].asignarTrabajo(tiempo);
                }

                if (cajas[i].procesarMinuto()) {
                    totalAtendidos++;
                }
            }
        }

        mostrarResultados();
    }

    private void mostrarResultados() {
        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Total atendidos: " + totalAtendidos);
        System.out.println("Fila máxima: " + maxFila);
        System.out.println("Fila promedio: " + (sumaFila / 420.0));
        System.out.println("Máxima espera: " + maxEspera);
        System.out.println("Minutos caja 4 activa: " + minutosCaja4Activa);
    }
}
