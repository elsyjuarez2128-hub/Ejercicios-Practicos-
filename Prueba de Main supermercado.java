*/
*@autor Elsy Joselyn Godinez Juarez
*Simulación del supermercado con carritos y cajas
*GTID141
public class Main2 {
    public static void main(String[] args) {
        Supermercado sup = new Supermercado(25, 3);
        int id = 1;

        for (int minuto = 0; minuto < 10; minuto++) {
            sup.llegadaCliente(id++);
            sup.asignarCarritosYCajas();
            sup.atenderClientes();
        }
    }
}
