package br.edu.unoesc.desafiofullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unoesc.desafiofullstack.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
