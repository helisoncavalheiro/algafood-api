package com.helison.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helison.algafood.domain.exception.EntidadeEmUsoException;
import com.helison.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.helison.algafood.domain.model.Restaurante;
import com.helison.algafood.domain.repository.RestauranteRepository;
import com.helison.algafood.domain.service.CadastroRestauranteService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
  
  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CadastroRestauranteService cadastroRestaurante;

  @GetMapping
  public List<Restaurante> listar(){
    return restauranteRepository.findAll();
  }

  @GetMapping("/{restauranteId}")
  public ResponseEntity<Restaurante> obter(@PathVariable Long restauranteId){
    Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
  
    if (restaurante.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(restaurante.get());
  }

  @PostMapping
  public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
    try {
      Restaurante restauranteSalvo = cadastroRestaurante.salvar(restaurante);
      return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);
      
    } catch (EntidadeNaoEncontradaException e) {
      
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{restauranteId}")
  public ResponseEntity<?> atualizar(@RequestBody Restaurante restaurante, @PathVariable Long restauranteId){
    Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

    if (restauranteAtual.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    try {      
      BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");
  
      Restaurante restauranteSalvo = cadastroRestaurante.salvar(restauranteAtual.get());

      return ResponseEntity.ok(restauranteSalvo);

    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{restauranteId}")
  public ResponseEntity<?> remover(@PathVariable Long restauranteId){
    try {
      cadastroRestaurante.excluir(restauranteId);
      return ResponseEntity.noContent().build();

    } catch (EntidadeEmUsoException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

    }catch(EntidadeNaoEncontradaException e){
      return ResponseEntity.notFound().build();
    }
  }

  @PatchMapping("/{restauranteId}")
  public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos){
    Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

    if (restauranteAtual.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    merge(campos, restauranteAtual.get());

    return atualizar(restauranteAtual.get(), restauranteId);
  }

  private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

    ObjectMapper objectMapper = new ObjectMapper();

    //Converte o map recebido com os valores para um objeto do tipo restaurante
    //Isso é feito para garantir que os tipos de dados não são diferentes
    Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
    
    dadosOrigem.forEach((campo, valor) -> {
      //Cria uma representação do atributo da classe que estamos manipulando baseado na chave recebida no Map
      Field field = ReflectionUtils.findField(Restaurante.class, campo);
      //torna o atributo público
      field.setAccessible(true);

      //Usa a API de reflections para pegar o valor do atributo atual no objeto convertido acima
      Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

      //atribui o valor recuperado ao objeto de destino
      ReflectionUtils.setField(field, restauranteDestino, novoValor);
    });
  }

}
