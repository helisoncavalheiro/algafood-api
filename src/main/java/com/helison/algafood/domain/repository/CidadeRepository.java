package com.helison.algafood.domain.repository;

import java.util.List;

import com.helison.algafood.domain.model.Cidade;

public interface CidadeRepository {

  public Cidade obter(Long cidadeId);
  public List<Cidade> listar();
  public Cidade salvar(Cidade cidade);
  public void excluir(Long cidadeId);
  
}
