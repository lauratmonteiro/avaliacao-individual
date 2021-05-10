package repositorios;

import java.util.ArrayList;

public interface Livro {
    void escreveLivros(ArrayList<classes.Livro> livros) throws Exception;
    ArrayList<classes.Livro> leLivros() throws Exception;
}
