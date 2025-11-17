import java.util.*;
*/
*@autor Elsy Joselyn Godinez Juarez
*GTID141
*Simulación del supermercado con carritos y cajas
  
public class Supermercado {

    private Queue<Carrito> carritos;
    private Caja[] cajas;
    private Queue<Integer> clientesEsperando;

    public Supermercado(int numCarritos, int numCajas) {
        carritos = new LinkedList<>();
        for (int i = 1; i <= numCarritos; i++) {
            carritos.offer(new Carrito(i));
        }

        cajas = new Caja[numCajas];
        for (int i = 0; i < numCajas; i++) {
            cajas[i] = new Caja();
        }

        clientesEsperando = new LinkedList<>();
    }

    public void llegadaCliente(int idCliente) {
        clientesEsperando.offer(idCliente);
    }

    public void asignarCarritosYCajas() {
        while (!clientesEsperando.isEmpty() && !carritos.isEmpty()) {
            int cliente = clientesEsperando.poll();
            Carrito c = carritos.poll();

            // elegir caja con menos clientes
            int idx = cajaConMenosClientes();
            cajas[idx].agregarCliente(cliente);

            System.out.println("Cliente " + cliente +
                    " toma carrito " + c.getId() +
                    " y va a caja " + (idx + 1));
        }
    }

    private int cajaConMenosClientes() {
        int min = 0;
        for (int i = 1; i < cajas.length; i++) {
            if (cajas[i].getCantidadClientes() < cajas[min].getCantidadClientes())
                min = i;
        }
        return min;
    }

    public void atenderClientes() {
        for (int i = 0; i < cajas.length; i++) {
            int cli = cajas[i].atender();
            if (cli != -1) {
                carritos.offer(new Carrito(cli)); // libera carrito
                System.out.println("Caja " + (i + 1) + " atendió al cliente " + cli);
            }
        }
    }
}
