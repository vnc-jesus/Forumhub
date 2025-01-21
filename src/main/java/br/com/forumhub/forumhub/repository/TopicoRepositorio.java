package br.com.forumhub.forumhub.repository;

import br.com.forumhub.forumhub.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepositorio extends JpaRepository<Topico, Long> {
}
