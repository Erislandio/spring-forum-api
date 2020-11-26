package br.com.alura.forum.controller.form;

import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repository.TopicoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateTopicoForm {

    @NotNull
    @NotEmpty
    private String titulo;

    @NotEmpty
    @NotNull
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico update(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getOne(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }
}
