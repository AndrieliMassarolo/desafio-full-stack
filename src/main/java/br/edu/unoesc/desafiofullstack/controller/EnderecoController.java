package br.edu.unoesc.desafiofullstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.unoesc.desafiofullstack.model.Endereco;
import br.edu.unoesc.desafiofullstack.service.EnderecoService;
import jakarta.validation.Valid;

@Controller
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping("/enderecos/list/{codigoPessoa}")
	public String showEnderecoList(@PathVariable("codigoPessoa") Long codigoPessoa, Model model) {
		List<Endereco> enderecos = enderecoService.findAllByPessoa(codigoPessoa);
		model.addAttribute("codigoPessoa", codigoPessoa);
		model.addAttribute("enderecos", enderecos);
		model.addAttribute("endereco", new Endereco());
		return "list-endereco";
	}

	@PostMapping("/enderecos/save")
	public String save(@Valid Endereco endereco, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "list-endereco";
		}

		enderecoService.save(endereco);
		return "redirect:/enderecos/list/" + endereco.getPessoa().getCodigo();
	}

	@GetMapping("/enderecos/edit/{codigoPessoa}/{codigoEndereco}")
	public String showEnderecoEditForm(@PathVariable("codigoPessoa") Long codigoPessoa,
			@PathVariable("codigoEndereco") Long codigoEndereco, Model model) {

		Endereco endereco = enderecoService.findById(codigoEndereco)
				.orElseThrow(() -> new IllegalArgumentException("Código do endereco inválido:" + codigoEndereco));

		List<Endereco> enderecos = enderecoService.findAllByPessoa(codigoPessoa);
		model.addAttribute("codigoPessoa", codigoPessoa);
		model.addAttribute("enderecos", enderecos);
		model.addAttribute("endereco", endereco);
		return "list-endereco";
	}

	@GetMapping("/enderecos/delete/{codigoPessoa}/{codigoEndereco}")
	public String deleteEndereco(@PathVariable("codigoPessoa") Long codigoPessoa,
			@PathVariable("codigoEndereco") Long codigoEndereco, Model model) {
		enderecoService.deleteById(codigoEndereco);
		return "redirect:/enderecos/list/" + codigoPessoa;
	}
}
