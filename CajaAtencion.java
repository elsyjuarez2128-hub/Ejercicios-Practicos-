*/
*Simulación de atención al cliente en supermercado
*Esperanza
*@autor Elsy Joselyn Godinez Juarez 
*GTID141

public class CajaAtencion {
    private int tiempoRestante;

    public CajaAtencion() {
        tiempoRestante = 0;
    }

    public boolean estaLibre() {
        return tiempoRestante == 0;
    }

    public void asignarTrabajo(int tiempo) {
        tiempoRestante = tiempo;
    }

    public boolean procesarMinuto() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
            return tiempoRestante == 0; // terminó
        }
        return false;
    }
}
