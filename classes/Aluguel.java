package classes;

public class Aluguel{

    /* Atributos */
    private Livro livro;
    private String dataAluguel;
    private Cliente cliente;

    /* Construtor */
    public Aluguel(Livro livro, String dataAluguel, Cliente cliente){ // a data deve ser passada no formato dd/mm/aaaa
        this.livro = livro;
        this.dataAluguel = dataAluguel;
        this.cliente = cliente;
    }

    /* getters e setters */
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void setDataAluguel(String dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public String getDataAluguel() {
        return dataAluguel;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    /* Outros métodos */

}