package repositorios;

import java.util.ArrayList;

public interface Cliente {
    void escreveClientes(ArrayList<classes.Cliente> clientes) throws Exception;
    ArrayList<classes.Cliente> leClientes() throws Exception;
}
