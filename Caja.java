import java.util.LinkedList;
import java.util.Queue;

*/
*@autor Elsy Joselyn Godinez Juarez
*GTID141
*Simulación del supermercado con carritos y cajas

public class Caja {
    private Queue<Integer> colaClientes;

    public Caja() {
        colaClientes = new LinkedList<>();
    }

    public void agregarCliente(int idCliente) {
        colaClientes.offer(idCliente);
    }

    public int atender() {
        if (colaClientes.isEmpty()) return -1;
        return colaClientes.poll();
    }

    public int getCantidadClientes() {
        return colaClientes.size();
    }
}
