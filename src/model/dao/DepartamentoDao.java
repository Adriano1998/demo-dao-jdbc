package model.dao;

import java.util.List;

import model.entities.Departamento;

public interface DepartamentoDao {

	//opera��o respons�vel por inserir esse objeto no banco de dados que est� no parametro de entrada
	void insert(Departamento obj);
	void update (Departamento obj);
	void deleteById(Integer id);
	//essa opera��o ser� responsavel por pegar o id e consultar no bd um objeto com esse id, se existir retorna, sen�o, retornaa nulo.
	Departamento findById(Integer id);
	List<Departamento> findAll();
}
