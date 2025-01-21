package br.com.forumhub.forumhub.service;

import br.com.forumhub.forumhub.dto.TopicoDTO;
import br.com.forumhub.forumhub.entity.Topico;
import br.com.forumhub.forumhub.repository.TopicoRepositorio;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoServico {

    private final TopicoRepositorio repositorio;

    public TopicoServico(TopicoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Topico criarTopico(TopicoDTO dto) {
        String usuarioAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();
        Topico topico = new Topico();
        topico.setTitulo(dto.titulo());
        topico.setMensagem(dto.mensagem());
        topico.setAutor(usuarioAutenticado);
        topico.setEstado(dto.estado());
        return repositorio.save(topico);
    }

    public List<Topico> listarTopicos() {
        return repositorio.findAll();
    }

    public Topico buscarTopicoPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado."));
    }

    public Topico atualizarTopico(Long id, TopicoDTO dto) {
        Topico topico = buscarTopicoPorId(id);
        String usuarioAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!topico.getAutor().equals(usuarioAutenticado)) {
            throw new RuntimeException("Você não tem permissão para atualizar este tópico.");
        }
        topico.setTitulo(dto.titulo());
        topico.setMensagem(dto.mensagem());
        topico.setEstado(dto.estado());
        return repositorio.save(topico);
    }

    public void excluirTopico(Long id) {
        Topico topico = buscarTopicoPorId(id);
        String usuarioAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!topico.getAutor().equals(usuarioAutenticado)) {
            throw new RuntimeException("Você não tem permissão para excluir este tópico.");
        }
        repositorio.deleteById(id);
    }
}