package repositorios;

import java.io.IOException;
import java.util.ArrayList;

public interface Aluguel {
    void escreveAlugueis(ArrayList<classes.Aluguel> alugueis) throws Exception;
    ArrayList<classes.Aluguel> leAlugueis() throws Exception;
}
