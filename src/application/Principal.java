package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Principal {

	public static void main(String[] args) {
		
		Departamento obj = new Departamento(1, "Livros");
		
		
		
	

		//dessa forma o programa n�o conhece a implementa��o, somente a interface. Tipo inje��o de dependencia
		VendedorDao vendedordao = DaoFactory.createVendedorDao();
	
		Vendedor vendedor = vendedordao.findById(3);
		System.out.println(vendedor);
	}

}
