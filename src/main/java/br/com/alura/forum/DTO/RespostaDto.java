package br.com.alura.forum.DTO;

import br.com.alura.forum.models.Resposta;

import java.time.LocalDateTime;

public class RespostaDto {
    private  Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaDto(Resposta respota) {
        this.id = respota.getId();
        this.mensagem = respota.getMensagem();
        this.dataCriacao = respota.getDataCriacao();
        this.nomeAutor = respota.getAutor().getNome();
    }
}
