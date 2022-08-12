package br.com.cod3r.exerciciossb.controllers;

import br.com.cod3r.exerciciossb.Model.entities.Produto;
import br.com.cod3r.exerciciossb.Model.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Produto novoProduto(@Valid Produto produto){
        produtoRepository.save(produto);
        return produto;
    }
    @GetMapping
    public Iterable<Produto> obterProdutos(){
        return produtoRepository.findAll();
    }
    @GetMapping(path = "/nome/{nome}")
    public Iterable<Produto> obterProdutosPorNome(@PathVariable String nome){
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }
    @GetMapping(path = "/{id}")
    public Optional<Produto> obterProdutoPorId(@PathVariable int id){
        return produtoRepository.findById(id);
    }
    @GetMapping(path = "/pagina/{numeroPagina}")
    public Iterable<Produto> obterProdutoPorPagina(@PathVariable int numeroPagina){
        Pageable pageable = PageRequest.of(numeroPagina,2);
        return produtoRepository.findAll(pageable);
    }
    @DeleteMapping(path = "{id}")
    public void deleteProduto(@PathVariable int id){
        produtoRepository.deleteById(id);
    }
}
