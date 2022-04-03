package com.helison.algafood.domain.repository;

import java.util.List;

import com.helison.algafood.domain.model.Estado;

public interface EstadoRepository {
  
  public Estado obter(Long estadoId);
  public List<Estado> listar();
  public Estado salvar(Estado estado);
  public void excluir(Long estadoId);

}