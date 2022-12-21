package com.helison.algafood.domain.exception;

public class RestauranteNaoEcontradoException extends EntidadeNaoEncontradaException {
    public RestauranteNaoEcontradoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEcontradoException(Long restauranteId) {
        this(String.format("Restaurante de id %d não encontrado", restauranteId));
    }
}
