package com.javaee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.javaee.domain.Veiculo;

public class VeiculoServiceImpl implements IVeiculoService {
	
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	private Integer idAtual = 10;
	
	public VeiculoServiceImpl() {
		for (int i = 0; i < 10; i++) {
			Veiculo veiculo = new Veiculo();
			veiculo.setId(i);
			veiculo.setNome("Subaru" + i);
			veiculo.setAno(2018);
			veiculos.add(veiculo);
		}
	}

	public List<Veiculo> buscarTodos() {
		return veiculos;
	}

	public Veiculo salvarVeiculo(Veiculo veiculo) {
		if(veiculo.getId() != null) {
    		this.deletarPorId(veiculo.getId());
    	} else {
    		idAtual++;
    		veiculo.setId(idAtual);
    	}
    	this.veiculos.add(veiculo);
    	return veiculo;
	}

	public void deletarPorId(Integer id) {
		this.veiculos.removeIf(vehicle -> vehicle.getId().equals(id));
	}

	public Veiculo buscarPorId(Integer id) {
		Optional<Veiculo> vehicleOptional = veiculos.stream()
        		.filter(Veiculo -> Veiculo.getId().equals(id)).findFirst();

		return vehicleOptional.orElse(null);
	}
	
}
