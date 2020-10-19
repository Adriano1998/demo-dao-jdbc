package model.dao;

import java.util.List;

import model.entities.Vendedor;

public interface VendedorDao {

	void insert(Vendedor obj);
	void update (Vendedor obj);
	void deleteById(Integer id);
	//essa operação será responsavel por pegar o id e consultar no bd um objeto com esse id, se existir retorna, senão, retornaa nulo.
	Vendedor findById(Integer id);
	List<Vendedor> findAll();
}
