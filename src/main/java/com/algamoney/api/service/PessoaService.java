package com.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoa(codigo);
		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		
		return this.pessoaRepository.save(pessoaSalva);
		
	}


	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoa(codigo);

		pessoaSalva.setAtivo(ativo);
		
		this.pessoaRepository.save(pessoaSalva);		
	}

	private Pessoa buscarPessoa(Long codigo) {
		Pessoa pessoaSalva = this.pessoaRepository.findOne(codigo);
		
		if(pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
}
