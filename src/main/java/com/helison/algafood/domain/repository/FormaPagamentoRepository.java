package com.helison.algafood.domain.repository;

import java.util.List;

import com.helison.algafood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {
  
  public FormaPagamento obter(Long formaPagamentoId);
  public List<FormaPagamento> listar();
  public FormaPagamento salvar(FormaPagamento formaPagamento);
  public void excluir(FormaPagamento formaPagamento);

}
