package com.helison.algafood.api.controller;

import java.util.List;

import com.helison.algafood.domain.exception.EntidadeEmUsoException;
import com.helison.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.helison.algafood.domain.model.Estado;
import com.helison.algafood.domain.repository.EstadoRepository;
import com.helison.algafood.domain.service.CadastroEstadoService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estados")
public class EstadoController {

  @Autowired
  private EstadoRepository estadoRepository;

  @Autowired
  private CadastroEstadoService cadastroEstado;

  @GetMapping
  public List<Estado> listar() {
    return estadoRepository.listar();
  }

  @GetMapping("/{estadoId}")
  public ResponseEntity<Estado> obter(@PathVariable Long estadoId) {
    Estado estado = estadoRepository.obter(estadoId);

    if (estado == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(estado);
  }

  @PostMapping
  public ResponseEntity<Estado> salvar(@RequestBody Estado estado) {
    Estado estadoSalvo = cadastroEstado.salvar(estado);

    return ResponseEntity.status(HttpStatus.CREATED).body(estadoSalvo);
  }

  @PutMapping("/{estadoId}")
  public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {

    Estado estadoAtual = estadoRepository.obter(estadoId);

    if (estadoAtual == null) {
      return ResponseEntity.notFound().build();
    }

    try {
      BeanUtils.copyProperties(estado, estadoAtual, "id");
      estadoAtual = cadastroEstado.salvar(estadoAtual);

      return ResponseEntity.ok(estadoAtual);

    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{estadoId}")
  public ResponseEntity<?> excluir(@PathVariable Long estadoId) {
    try {
      cadastroEstado.excluir(estadoId);
      return ResponseEntity.noContent().build();
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.notFound().build();

    } catch (EntidadeEmUsoException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

  }

}
