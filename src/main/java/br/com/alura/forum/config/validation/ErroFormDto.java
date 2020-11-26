package br.com.alura.forum.config.validation;

public class ErroFormDto {
    private  String nome;
    private  String error;

    public ErroFormDto(String nome, String error) {
        this.nome = nome;
        this.error = error;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
