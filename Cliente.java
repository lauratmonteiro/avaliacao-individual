public class Cliente implements Avaliacao { // Tem que implementar Avaliação, mas não consegue por causa do package
    
    private String nome;
    private String cpf;
    private Boolean assinante;
    private Integer qtdLivrosAlugados;

    /* Construtor */

    public Cliente (String nome, String cpf, Boolean assinante) {
        this.nome = nome;
        this.cpf = cpf;
        this.assinante = assinante;
    }

    /* Setters e getters */
    //TODO: fazer validação dos setters
    public void setNome(String nome) {
        this.nome = validacaoSet(nome);
    }
 
    public String getNome() {
        return nome;
    }

    public void setCpf(String cpf) {
        this.cpf = validacaoSet(cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setAssinante(Boolean assinante) {
        this.assinante = assinante;
    }

    public Boolean getAssinante() {
        return assinante;
    }

    public void setQtdLivrosAlugados(Integer qtdLivrosAlugados) {
        this.qtdLivrosAlugados = qtdLivrosAlugados;
    }

    public Integer getQtdLivrosAlugados() {
        return qtdLivrosAlugados;
    }

    /* Outros métodos */

    public void comprarAssinatura() {
        this.setAssinante(true);
    }

    public void cancelarAssinatura() {
        this.setAssinante(false);
    }

    @Override
    public void avaliar(Livro livro, Integer nota) {
        livro.setAvaliacao(nota);
    }

    @Override
    public String toString() {
        return  "Nome do cliente: " + this.nome + "\n" +
                "CPF do cliente: " + this.cpf + "\n";
    }

}
