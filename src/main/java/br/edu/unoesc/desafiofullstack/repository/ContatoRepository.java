package br.edu.unoesc.desafiofullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.unoesc.desafiofullstack.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	@Query("SELECT contato from Contato as contato where contato.pessoa.codigo = :codigoPessoa")
	List<Contato> findAllByPessoa(Long codigoPessoa);
}
