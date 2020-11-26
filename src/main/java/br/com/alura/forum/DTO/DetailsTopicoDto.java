package br.com.alura.forum.DTO;

import br.com.alura.forum.models.Resposta;
import br.com.alura.forum.models.StatusTopico;
import br.com.alura.forum.models.Topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetailsTopicoDto {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String autor;
    private StatusTopico status;
    private List<RespostaDto> respostas;

    public DetailsTopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.autor = topico.getAutor().getNome();
        this.status  = topico.getStatus();
        this.respostas = new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
    }
}
