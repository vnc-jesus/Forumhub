package br.com.forumhub.forumhub.controller;

import br.com.forumhub.forumhub.dto.TopicoDTO;
import br.com.forumhub.forumhub.entity.Topico;
import br.com.forumhub.forumhub.service.TopicoServico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topicos")
public class TopicoControlador {
    private final TopicoServico servico;

    public TopicoControlador(TopicoServico servico) {
        this.servico = servico;
    }

    @PostMapping
    public ResponseEntity<Topico> criarTopico(@RequestBody TopicoDTO dto) {
        return ResponseEntity.ok(servico.criarTopico(dto));
    }

    @GetMapping
    public ResponseEntity<List<Topico>> listarTopicos() {
        return ResponseEntity.ok(servico.listarTopicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> buscarTopico(@PathVariable Long id) {
        return ResponseEntity.ok(servico.buscarTopicoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizarTopico(@PathVariable Long id, @RequestBody TopicoDTO dto) {
        return ResponseEntity.ok(servico.atualizarTopico(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTopico(@PathVariable Long id) {
        servico.excluirTopico(id);
        return ResponseEntity.noContent().build();
    }
}