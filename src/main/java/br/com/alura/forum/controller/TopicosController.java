package br.com.alura.forum.controller;

import br.com.alura.forum.DTO.DetailsTopicoDto;
import br.com.alura.forum.DTO.TopicoDto;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.controller.form.UpdateTopicoForm;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> list(String nomeCurso) {

        List<Topico> topicos;
        if (nomeCurso == null) {
            topicos = this.topicoRepository.findAll();
        } else {
            topicos = this.topicoRepository.findByCursoNome(nomeCurso);
        }
        return TopicoDto.converter(topicos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> create(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = form.converter(this.cursoRepository);
        this.topicoRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsTopicoDto> details(@PathVariable Long id) {
        Optional<Topico> topico = this.topicoRepository.findById(id);
        if(topico.isPresent()) {
            return ResponseEntity.ok(new DetailsTopicoDto(topico.get()));
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicoForm form) {
        Topico topico = form.update(id, topicoRepository);
        return ResponseEntity.ok(new TopicoDto(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity remove(@PathVariable  Long id) {
        this.topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
