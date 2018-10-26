package com.javaee.service;

import java.util.List;

import com.javaee.domain.Veiculo;

public interface IVeiculoService {
	
	List<Veiculo> buscarTodos();
	
	Veiculo buscarPorId(Integer id);
	
	Veiculo salvarVeiculo(Veiculo veiculo);
	
	void deletarPorId(Integer id);

}
