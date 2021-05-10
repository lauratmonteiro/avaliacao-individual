package classes;

import java.io.*;
import java.util.ArrayList;

import java.util.Collections;

public class Catalogo {

    /* Atributos */

    private static repositorios.Livro dadosLivro = new PersistenciaArquivo();
    private static repositorios.Autor dadosAutor = new PersistenciaArquivo();
    private static repositorios.Editora dadosEditora = new PersistenciaArquivo();

    private static ArrayList<Livro> livros = new ArrayList<Livro>();
    private static ArrayList<Editora> editoras = new ArrayList<Editora>();
    private static ArrayList<Autor> autores = new ArrayList<Autor>();

    /* getters */

    public static ArrayList<Livro> getLivros() {
        Collections.sort(livros);
        return livros;
    }

    public static ArrayList<Autor> getAutores() {
        Collections.sort(autores);
        return autores;
    }

    public static ArrayList<Editora> getEditoras() {
        Collections.sort(editoras);
        return editoras;
    }

    public static void leLivro() throws Exception {
        try{
            livros = dadosLivro.leLivros();
        } catch (Exception e){
            throw e;
        }
    }

    public static void leAutor() throws Exception {
        try{
            autores = dadosAutor.leAutores();
        } catch (Exception e){
            throw e;
        }
    }

    public static void leEditora() throws Exception {
        try{
            editoras = dadosEditora.leEditoras();
        } catch (Exception e){
            throw e;
        }
    }

    public static void escreveLivro() throws Exception {
        try{
            dadosLivro.escreveLivros(livros);
        } catch (Exception e){
            throw e;
        }
    }

    public static void escreveAutor() throws Exception {
        try{
            dadosAutor.escreveAutores(autores);
        } catch (Exception e){
            throw e;
        }
    }


}