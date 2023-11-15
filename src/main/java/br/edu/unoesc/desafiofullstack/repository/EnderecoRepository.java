package br.edu.unoesc.desafiofullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.unoesc.desafiofullstack.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	@Query("SELECT endereco from Endereco as endereco where endereco.pessoa.codigo = :codigoPessoa")
	List<Endereco> findAllByPessoa(Long codigoPessoa);
}
