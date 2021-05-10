package classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Livraria { // controller

    public static final int MAX_LIVROS = 5;
    private static repositorios.Aluguel dadosAluguel = new PersistenciaArquivo();
    private static repositorios.Cliente dadosCliente = new PersistenciaArquivo();
    private static ArrayList<Aluguel> alugueis = new ArrayList<Aluguel>();
    private static ArrayList<Cliente> clientes = new ArrayList<Cliente>();

    public Livraria(){

    }

    /* getters */

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static ArrayList<Aluguel> getAlugueis() {
        return alugueis;
    }

    /* métodos para inicializar e finalizar o programa */

    public static void inicializaPrograma() throws Exception{
        try {
            Catalogo.leLivro();
            Catalogo.leAutor();
            Catalogo.leEditora();
            clientes = dadosCliente.leClientes();
            alugueis = dadosAluguel.leAlugueis();
        } catch (Exception e) {
            throw e;
        }
    }

    public static void finalizaPrograma() throws Exception {
        try {
            dadosCliente.escreveClientes(clientes);
            dadosAluguel.escreveAlugueis(alugueis);
            Catalogo.escreveLivro();
            Catalogo.escreveAutor();
        } catch (IOException e) {
            throw e;
        }
    }

    /* métodos para manipular alugueis */

    public static void salvaAluguel(Livro livro, String dataAluguel, Cliente cliente) {
        Aluguel novoAluguel = new Aluguel(livro, dataAluguel, cliente); // cria um obj da classe Aluguel com os dados
        int qtd = novoAluguel.getLivro().getQtdAlugados();
        novoAluguel.getLivro().setQtdAlugados(qtd+1);
        cliente.setQtdLivrosAlugados(cliente.getQtdLivrosAlugados()+1);
        alugueis.add(novoAluguel);
    }

    public static void removeAluguel(Aluguel aluguel) {
        alugueis.remove(aluguel);
        int qtd = aluguel.getLivro().getQtdAlugados();
        aluguel.getLivro().setQtdAlugados(qtd-1);
        aluguel.getCliente().setQtdLivrosAlugados(aluguel.getCliente().getQtdLivrosAlugados()-1);
    }

    /* métodos para manipular clientes */

    // TESTAR
    // cria e salva um novo cliente (será escrito no arquivo assim que o programa for encerrado)
    public static void salvaCliente(String nome, String nacionalidade, Integer anoNascimento, String cpf) {
        Cliente clienteNovo = new Cliente(nome, nacionalidade, anoNascimento, cpf, true, 0);
        clientes.add(clienteNovo);
    }


    // remove um cliente do array (será removido do arquivo assim que o programa for encerrado)
    public static void removeCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    // TESTAR
    // busca um determinado cliente pelo seu cpf 
    public static Cliente buscaCliente(String cpfBuscado){
        for (Cliente c : clientes) {
            if (cpfBuscado.equals(c.getCpf())) {
                return c;
            }
        }
        return null; //caso não encontre o cliente
    }

    /* outros métodos */

    public static void avaliarLivro(Livro livro, Integer nota) {
        livro.avaliar(nota);
        buscaAutor(livro.getIdAutor()).avaliar(nota);
    }

    // verifica se o cliente pode alugar novos livros
    public static Boolean verificaLimite(Cliente c) {
        if (c.getQtdLivrosAlugados() == MAX_LIVROS)
            return false; // retorna falso se tiver o maximo
        return true; // retorna verdadeiro se ainda houver limite disponivel
    }

    // verifica se ainda ha exemplares desse livro disponiveis para alugar
    public static Boolean verificaDisponibilidade(Livro l) {
        if (l.getQtdExemplares() == l.getQtdAlugados())
            return false; // retorna falso caso o livro não esteja disponível
        return true;
    }


    /* -------------------- MÉTODOS DE BUSCA -------------------- */

    /* métodos para mostrar os livros, autores e editoras disponíveis */

    // Mostra todos os livros em ordem alfabética
    public static List<Livro> livrosCatalogo() {
        return Catalogo.getLivros();
    }

    // Mostra os autores em ordem alfabética
    public static List<Autor> autoresCatalogo() {
        return Catalogo.getAutores();
    }

    // Mostra as editoras em ordem alfabética
    public static List<Editora> editorasCatalogo() {
        return Catalogo.getEditoras();
    }

    /* métodos buscarLivro: usados para encontrar uma lista de livros com as mesmas caracteristicas */

    // retorna uma lista com os livros de um determinado autor em ordem alfabética
    public static ArrayList<Livro> buscarLivros(Autor autor) {
        if(autor == null) return null;

        ArrayList<Livro> livros = new ArrayList<Livro>();
        for (Livro l : Catalogo.getLivros()) {
            if (l.getIdAutor() == autor.getId()) {
                livros.add(l);
            }
        }
        return livros;
    }

    // retorna uma lista com os livros de uma determinada editora em ordem alfabética
    public static ArrayList<Livro> buscarLivros(Editora editora) {
        if(editora == null) return null;

        ArrayList<Livro> livros = new ArrayList<Livro>();
        for (Livro l : Catalogo.getLivros()) {
            if (l.getIdEditora() == editora.getId()) {
                livros.add(l);
            }
        }
        return livros;
    }

    // retorna uma lista com os livros de um determinado genero em ordem alfabética
    public static ArrayList<Livro> buscarLivros(String genero) {
        if(genero == null) return null;

        ArrayList<Livro> livros = new ArrayList<Livro>();
        for (Livro l : Catalogo.getLivros()) {
            if (genero.toLowerCase().equals(l.getNomeGenero().toLowerCase())){
                livros.add(l);
            }
        }
        return livros;
    }

    /* métodos para encontrar um objeto Livro, Autor ou Livraria a partir de seu nome ou id */

    public static Livro buscaLivro(Integer id) {
        for (Livro l : Catalogo.getLivros()) {
            if (id == l.getId()) {
                return l;
            }
        }
        return null;
    }

    public static Livro buscaLivro(String titulo) {
        for (Livro l : Catalogo.getLivros()) {
            if (titulo.toLowerCase().equals(l.getTitulo().toLowerCase())) {
                return l;
            }
        }
        return null;
    }

    public static Autor buscaAutor(Integer id) {
        for (Autor a : Catalogo.getAutores()) {
            if (id == a.getId()) {
                return a;
            }
        }
        return null;
    }

    public static Autor buscaAutor(String nome) {
        for (Autor a : Catalogo.getAutores()) {
            if (nome.toLowerCase().equals(a.getNome().toLowerCase())) {
                return a;
            }
        }
        return null;
    }

    public static Editora buscaEditora(Integer id) {
        for (Editora e : Catalogo.getEditoras()) {
            if (id == e.getId()) {
                return e;
            }
        }
        return null;
    }

    public static Editora buscaEditora(String nome) {
        for (Editora e : Catalogo.getEditoras()) {
            if (nome.toLowerCase().equals(e.getNome().toLowerCase())) {
                return e;
            }
        }
        return null;
    }

}

