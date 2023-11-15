package br.edu.unoesc.desafiofullstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.unoesc.desafiofullstack.model.Contato;
import br.edu.unoesc.desafiofullstack.service.ContatoService;
import jakarta.validation.Valid;

@Controller
public class ContatoController {

	@Autowired
	private ContatoService contatoService;

	@GetMapping("/contatos/list/{codigoPessoa}")
	public String showContatoList(@PathVariable("codigoPessoa") Long codigoPessoa, Model model) {
		List<Contato> contatos = contatoService.findAllByPessoa(codigoPessoa);
		model.addAttribute("codigoPessoa", codigoPessoa);
		model.addAttribute("contatos", contatos);
		model.addAttribute("contato", new Contato());
		return "list-contato";
	}

	@PostMapping("/contatos/save")
	public String save(@Valid Contato contato, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "list-contato";
		}

		contatoService.save(contato);
		return "redirect:/contatos/list/" + contato.getPessoa().getCodigo();
	}

	@GetMapping("/contatos/edit/{codigoPessoa}/{codigoContato}")
	public String showContatoEditForm(@PathVariable("codigoPessoa") Long codigoPessoa,
			@PathVariable("codigoContato") Long codigoContato, Model model) {

		Contato contato = contatoService.findById(codigoContato)
				.orElseThrow(() -> new IllegalArgumentException("Código do contato inválido:" + codigoContato));
		
		List<Contato> contatos = contatoService.findAllByPessoa(codigoPessoa);
		model.addAttribute("codigoPessoa", codigoPessoa);
		model.addAttribute("contatos", contatos);
		model.addAttribute("contato", contato);
		return "list-contato";
	}

	@GetMapping("/contatos/delete/{codigoPessoa}/{codigoContato}")
	public String deleteContato(@PathVariable("codigoPessoa") Long codigoPessoa,
			@PathVariable("codigoContato") Long codigoContato, Model model) {
		contatoService.deleteById(codigoContato);
		return "redirect:/contatos/list/" + codigoPessoa;
	}
}
