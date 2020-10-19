package model.dao;

import db.DB;
import model.dao.impl.VendedorDaoJDBC;

public class DaoFactory {

	//operações estáticas para implementar o dao.
	
	//a classe vai expor o método que retorna o tipo da interface, mas internamente ela vai instanciar uma implementação. 
	//macete para não expor a implementação.
	
	public static VendedorDao createVendedorDao() {
		//passa uma conexão como argumento.
		return new VendedorDaoJDBC(DB.getConnection());
	}
}
