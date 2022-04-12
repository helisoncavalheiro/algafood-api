package com.helison.algafood.domain.service;

import com.helison.algafood.domain.exception.EntidadeEmUsoException;
import com.helison.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.helison.algafood.domain.model.Cozinha;
import com.helison.algafood.domain.model.Restaurante;
import com.helison.algafood.domain.repository.CozinhaRepository;
import com.helison.algafood.domain.repository.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CozinhaRepository cozinhaRepository;

  public Restaurante salvar(Restaurante restaurante) {
    Long cozinhaId = restaurante.getCozinha().getId();

    Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(
        String.format("Cozinha %d não existe", cozinhaId)));

    restaurante.setCozinha(cozinha);

    return restauranteRepository.save(restaurante);
  }

  public void excluir(Long restauranteId) {
    try {
      restauranteRepository.deleteById(restauranteId);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format("Restaurante de id %d não encontrada", restauranteId));
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format("Restaurante %d não pode ser excluído pois está em uso por outra entidade", restauranteId));
    }
  }

}
