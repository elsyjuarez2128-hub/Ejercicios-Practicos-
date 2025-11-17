*/
*Simulación de atención al cliente en supermercado
Esperanza
*@autor Elsy Joselyn Godinez Juarez
*GTID141
  
public class Cliente {
    private int id;
    private int tiempoLlegada;

    public Cliente(int id, int tiempoLlegada) {
        this.id = id;
        this.tiempoLlegada = tiempoLlegada;
    }

    public int getId() {
        return id;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }
}
