package br.com.triersistemas.loja.tenis.controller;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.triersistemas.loja.tenis.armazenamento.SalvaDados;
import br.com.triersistemas.loja.tenis.model.PessoaModel;


public class PessoaController {

	public void checarListaVeiculos() {
		if(Objects.isNull(SalvaDados.pessoa) || SalvaDados.pessoa.size() < 1) {
			throw new RuntimeException("Não há nenhum veiculo cadastrado!");
		}
	}
	
	public PessoaModel procurarVeiculo(Long id) {
		checarListaPessoas();
		for(PessoaModel p: SalvaDados.pessoa) {
			if (id.equals(p.getId())) {
				return p;
			}
		}
		
		throw new RuntimeException("Veiculo inexistente!");
	}
	
	@GetMapping("/validar-documento")
	public String validarCPF(@RequestParam(value = "placa") String placa) {
		String placaValida = "A placa é válida";
		PessoaModel p = procurarPessoa(placa);
		
		if (placa.length() > 0) {
	        if (placa.length() < 7) {
	            placaValida = "Placa Invalida";
	        } else {
	            if (!placa.matches("[A-Z]{3}[0-9][A-Z][0-9]{2}")) {
	                placaValida = "Placa Invalida";
	            }
	        }
	    }

	    return placaValida;
	}
