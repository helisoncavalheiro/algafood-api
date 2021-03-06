package com.helison.algafood.infrastructure.repository.spec;

import java.math.BigDecimal;

import com.helison.algafood.domain.model.Restaurante;

import org.springframework.data.jpa.domain.Specification;

public class RestauranteSpecs {

  private RestauranteSpecs() {
    throw new IllegalStateException("Utility class");
  }

  public static Specification<Restaurante> comFreteGratis() {

    return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
  }

  public static Specification<Restaurante> comNomeSemelhante(String nome) {
    return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
  }
}
