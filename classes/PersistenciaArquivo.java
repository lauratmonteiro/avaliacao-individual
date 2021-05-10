package classes;

import repositorios.Aluguel;
import repositorios.Autor;
import repositorios.Cliente;
import repositorios.Editora;
import repositorios.Livro;

import java.io.*;
import java.util.ArrayList;

public class PersistenciaArquivo implements Aluguel, Autor, Cliente, Editora, Livro {
    private static final String nomeArquivoCliente = "dados/clientes.txt";
    private static final String nomeArquivoAluguel = "dados/alugueis.txt";
    private static final String nomeArquivoAutor = "dados/autores.txt";
    private static final String nomeArquivoEditora = "dados/editoras.txt";
    private static final String nomeArquivoLivro = "dados/livros.txt";

    public ArrayList<classes.Aluguel> leAlugueis() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(nomeArquivoAluguel), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String linha = "";
        linha = br.readLine();

        ArrayList<classes.Aluguel> alugueis = new ArrayList();

        while(linha != null){
            String[] dados = linha.split(";", 3);
            classes.Aluguel aluguel = new classes.Aluguel(
                    Livraria.buscaLivro(Integer.valueOf(dados[0])), //id Livro
                    dados[2], //data aluguel
                    Livraria.buscaCliente(dados[1]) //cpf Cliente
            );
            alugueis.add(aluguel);
            linha = br.readLine();
        }
        br.close();
        return alugueis;
    }

    public void armazenaAluguel(FileWriter fw, classes.Aluguel aluguel) {

        PrintWriter pw = new PrintWriter(fw);

        pw.printf("%1$s;", aluguel.getLivro().getId());

        pw.printf("%1$s;", aluguel.getCliente().getCpf());

        pw.printf("%1$s%n", aluguel.getDataAluguel());

        pw.flush();

    }

    public void escreveAlugueis(ArrayList<classes.Aluguel> alugueis) throws IOException {

        try {
            FileWriter fw = new FileWriter(nomeArquivoAluguel);

            for (classes.Aluguel a : alugueis) {
                armazenaAluguel(fw, a);
            }

            fw.close();

        } catch (IOException e) {
            throw new IOException("Arquivo não encontrado. Por favor, tente novamente.");
        }
    }

    public ArrayList<classes.Autor> leAutores() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream(nomeArquivoAutor), "UTF-8"));
        String linha = br.readLine();

        ArrayList<classes.Autor> autores = new ArrayList();

        while(linha != null){
            String[] dados = linha.split(";", 5);
            classes.Autor autor = new classes.Autor(
                    Integer.valueOf(dados[0]), // id
                    dados[1], // nome
                    dados[2], // nacionalidade
                    Integer.valueOf(dados[3]), // ano de nascimento
                    Double.valueOf(dados[4])); // avaliacao
            linha = br.readLine();
            autores.add(autor);
        }
        br.close();

        return autores;
    }

    public void escreveAutores(ArrayList<classes.Autor> autores) throws IOException {

        try {
            FileWriter fw = new FileWriter(nomeArquivoAutor);
            PrintWriter pw = new PrintWriter(fw);

            for (classes.Autor a : autores) {
                pw.printf("%1$s;", a.getId());
                pw.printf("%1$s;", a.getNome());
                pw.printf("%1$s;", a.getNacionalidade());
                pw.printf("%1$s;", a.getAnoNascimento());
                pw.printf("%1$s%n", a.getAvaliacao());
                pw.flush();
            }

            fw.close();

        } catch (IOException e) {
            throw new IOException("Arquivo não encontrado. Por favor, tente novamente.");
        }

    }

    public ArrayList<classes.Cliente> leClientes() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(nomeArquivoCliente), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String linha = "";
        linha = br.readLine();

        ArrayList<classes.Cliente> clientes = new ArrayList();

        while(linha != null){
            String[] dados = linha.split(";", 6);
            classes.Cliente cliente = new classes.Cliente(
                    dados[0], //nome
                    dados[1], //nacionalidade
                    Integer.valueOf(dados[2]), //anoNascimento
                    dados[3], //cpf
                    Boolean.valueOf(dados[4]), //assinante
                    Integer.valueOf(dados[5])); //qtdLivrosAlugados
            clientes.add(cliente);
            linha = br.readLine();
        }
        br.close();

        return clientes;

    }

    public void armazenaCliente(FileWriter fw, classes.Cliente cliente) throws IOException {

        PrintWriter pw = new PrintWriter(fw);

        pw.printf("%1$s;", cliente.getNome());

        pw.printf("%1$s;", cliente.getNacionalidade());

        pw.printf("%1$s;", cliente.getAnoNascimento());

        pw.printf("%1$s;", cliente.getCpf());

        /* Quando o cliente faz o cadastro já obtêm a assinatura da livraria */
        pw.printf("true;");

        /* Ao se cadastrar a quantidade de livros alugados é nula */
        pw.printf("%1$s%n", cliente.getQtdLivrosAlugados());

        pw.flush();
    }

    public void escreveClientes(ArrayList<classes.Cliente> clientes) throws IOException {

        try {
            FileWriter fw = new FileWriter(nomeArquivoCliente);


            for (classes.Cliente c : clientes) {
                armazenaCliente(fw, c);
            }

            fw.close();
        } catch (IOException e) {
            throw new IOException("Arquivo não encontrado. Por favor, tente novamente.");
        }
    }

    public ArrayList<classes.Editora> leEditoras() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream(nomeArquivoEditora), "UTF-8"));        String linha = "";
        linha = br.readLine();

        ArrayList<classes.Editora> editoras = new ArrayList();

        while(linha != null){
            String[] dados = linha.split(";", 3);
            classes.Editora editora = new classes.Editora(
                    Integer.valueOf(dados[0]), // id
                    dados[1], // nome
                    dados[2]); // cnpj
            linha = br.readLine();
            editoras.add(editora);
        }
        br.close();

        return editoras;
    }

    public ArrayList<classes.Livro> leLivros() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream(nomeArquivoLivro), "UTF-8"));
        String linha = br.readLine();

        ArrayList<classes.Livro> livros = new ArrayList();

        while(linha != null){
            String[] dados = linha.split(";", 12);
            classes.Livro livro = new classes.Livro(
                    Integer.valueOf(dados[0]), // id
                    dados[1], // titulo
                    Integer.valueOf(dados[2]), // idAutor
                    Integer.valueOf(dados[3]), // idEditora
                    Genero.values()[Integer.valueOf(dados[4])], // genero
                    Integer.valueOf(dados[5]), // anoPublicacao
                    Integer.valueOf(dados[6]), // edicao
                    Integer.valueOf(dados[7]), // numPaginas
                    dados[8], // sinopse
                    Integer.valueOf(dados[9]), // qtdExemplares
                    Integer.valueOf(dados[10]), // qtdAlugados
                    Double.valueOf(dados[11])); // avaliacao
            linha = br.readLine();
            livros.add(livro);
        }
        br.close();

        return livros;
    }

    public void escreveLivros(ArrayList<classes.Livro> livros) throws IOException {

        try {
            FileWriter fw = new FileWriter(nomeArquivoLivro);
            PrintWriter pw = new PrintWriter(fw);

            for (classes.Livro l : livros) {
                pw.printf("%1$s;", l.getId());
                pw.printf("%1$s;", l.getTitulo());
                pw.printf("%1$s;", l.getIdAutor());
                pw.printf("%1$s;", l.getIdEditora());
                pw.printf("%1$s;", l.getGenero().ordinal());
                pw.printf("%1$s;", l.getAnoPublicacao());
                pw.printf("%1$s;", l.getEdicao());
                pw.printf("%1$s;", l.getNumPaginas());
                pw.printf("%1$s;", l.getSinopse());
                pw.printf("%1$s;", l.getQtdExemplares());
                pw.printf("%1$s;", l.getQtdAlugados());
                pw.printf("%1$s%n", l.getAvaliacao());
                pw.flush();
            }

            fw.close();

        } catch (IOException e) {
            throw new IOException("Arquivo não encontrado. Por favor, tente novamente.");
        }

    }
}
