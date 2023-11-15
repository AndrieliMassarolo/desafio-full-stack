package br.edu.unoesc.desafiofullstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.unoesc.desafiofullstack.model.Pessoa;
import br.edu.unoesc.desafiofullstack.service.PessoaService;
import jakarta.validation.Valid;

@Controller
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping("/index")
	public String showPessoaList(Model model) {
		List<Pessoa> pessoas = pessoaService.findAll();
		model.addAttribute("pessoas", pessoas);
		return "index";
	}

	@GetMapping("/create")
	public String showCreateForm(Pessoa pessoa) {
		return "create-pessoa";
	}

	@PostMapping("/create")
	public String create(@Valid Pessoa pessoa, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "create-pessoa";
		}

		pessoaService.save(pessoa);
		return "redirect:/index";
	}

	@GetMapping("/edit/{codigo}")
	public String showUpdateForm(@PathVariable("codigo") Long codigo, Model model) {
		Pessoa pessoa = pessoaService.findById(codigo)
				.orElseThrow(() -> new IllegalArgumentException("Código de pessoa inválido:" + codigo));

		model.addAttribute("pessoa", pessoa);
		return "update-pessoa";
	}

	@PostMapping("/update/{codigo}")
	public String updatePessoa(@PathVariable("codigo") Long codigo, @Valid Pessoa pessoa, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			pessoa.setCodigo(codigo);
			return "update-pessoa";
		}

		pessoaService.save(pessoa);
		return "redirect:/index";
	}

	@GetMapping("/delete/{codigo}")
	public String deletePessoa(@PathVariable("codigo") Long codigo, Model model) {
		pessoaService.deleteById(codigo);
		return "redirect:/index";
	}

}
