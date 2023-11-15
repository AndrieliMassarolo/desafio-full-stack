package br.edu.unoesc.desafiofullstack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.desafiofullstack.model.Contato;
import br.edu.unoesc.desafiofullstack.repository.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	public List<Contato> findAllByPessoa(Long codigoPessoa) {
        return contatoRepository.findAllByPessoa(codigoPessoa);
    }

    public Optional<Contato> findById(Long id) {
        return contatoRepository.findById(id);
    }

    public Contato save(Contato contato) {
        return contatoRepository.save(contato);
    }

    public void deleteById(Long id) {
    	contatoRepository.deleteById(id);
    }

}
