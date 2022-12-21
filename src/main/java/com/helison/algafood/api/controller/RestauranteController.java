package com.helison.algafood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helison.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.helison.algafood.domain.exception.NegocioException;
import com.helison.algafood.domain.model.Restaurante;
import com.helison.algafood.domain.repository.RestauranteRepository;
import com.helison.algafood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> obter(@PathVariable Long restauranteId) {
        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return ResponseEntity.ok(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante) {
        try {
            return cadastroRestaurante.salvar(restaurante);
        } catch (CozinhaNaoEncontradaException e) {

            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizar(@RequestBody Restaurante restaurante, @PathVariable Long restauranteId) {
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "restaurantes");

        try {
            Restaurante restauranteSalvo = cadastroRestaurante.salvar(restauranteAtual);

            return ResponseEntity.ok(restauranteSalvo);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long restauranteId) {
        cadastroRestaurante.excluir(restauranteId);
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

        merge(campos, restauranteAtual);

        return atualizar(restauranteAtual, restauranteId);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

        ObjectMapper objectMapper = new ObjectMapper();

        // Converte o map recebido com os valores para um objeto do tipo restaurante
        // Isso é feito para garantir que os tipos de dados não são diferentes
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((campo, valor) -> {
            // Cria uma representação do atributo da classe que estamos manipulando baseado
            // na chave recebida no Map
            Field field = ReflectionUtils.findField(Restaurante.class, campo);
            // torna o atributo público
            field.setAccessible(true);

            // Usa a API de reflections para pegar o valor do atributo atual no objeto
            // convertido acima
            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            // atribui o valor recuperado ao objeto de destino
            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

}
