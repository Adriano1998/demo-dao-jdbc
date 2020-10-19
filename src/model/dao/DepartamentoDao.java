package model.dao;

import java.util.List;

import model.entities.Departamento;

public interface DepartamentoDao {

	//operação responsável por inserir esse objeto no banco de dados que está no parametro de entrada
	void insert(Departamento obj);
	void update (Departamento obj);
	void deleteById(Integer id);
	//essa operação será responsavel por pegar o id e consultar no bd um objeto com esse id, se existir retorna, senão, retornaa nulo.
	Departamento findById(Integer id);
	List<Departamento> findAll();
}
