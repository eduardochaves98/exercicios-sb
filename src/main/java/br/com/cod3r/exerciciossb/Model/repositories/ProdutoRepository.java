package br.com.cod3r.exerciciossb.Model.repositories;

import br.com.cod3r.exerciciossb.Model.entities.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer> {
     Iterable<Produto> findByNomeContainingIgnoreCase(String nome);
}
