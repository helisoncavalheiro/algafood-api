package com.helison.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import com.helison.algafood.domain.model.Cozinha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

  List<Cozinha> findTodasByNomeContaining(String nome);

  Optional<Cozinha> findByNome(String nome);
}
