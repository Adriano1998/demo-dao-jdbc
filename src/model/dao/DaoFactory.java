package model.dao;

import model.dao.impl.VendedorDaoJDBC;

public class DaoFactory {

	//opera��es est�ticas para implementar o dao.
	
	//a classe vai expor o m�todo que retorna o tipo da interface, mas internamente ela vai instanciar uma implementa��o. 
	//macete para n�o expor a implementa��o.
	
	public static VendedorDao createVendedorDao() {
		return new VendedorDaoJDBC();
	}
}