package repositorios;

import java.util.ArrayList;

public interface Autor {
    void escreveAutores(ArrayList<classes.Autor> autores) throws Exception;
    ArrayList<classes.Autor> leAutores() throws Exception;
}
