package br.edu.unoesc.desafiofullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unoesc.desafiofullstack.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
