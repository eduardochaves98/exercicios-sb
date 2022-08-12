package br.com.cod3r.exerciciossb.Model.repositories;

import br.com.cod3r.exerciciossb.Model.entities.ChaveObjeto;
import org.springframework.data.repository.CrudRepository;

public interface ChaveRepository extends CrudRepository<ChaveObjeto, Integer> {
     ChaveObjeto findByNOMEEquals(String nome);
     ChaveObjeto findByNOMEEqualsAndCLIENTESEqualsAndURAEquals(String nome, String Clientes, String URA);
}
