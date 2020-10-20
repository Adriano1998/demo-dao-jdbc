package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Principal {

	public static void main(String[] args) {
		
		Departamento objd = new Departamento(1, "Livros");
		
		//dessa forma o programa não conhece a implementação, somente a interface. Tipo injeção de dependencia
		VendedorDao vendedordao = DaoFactory.createVendedorDao();
		
		System.out.println("=== TEST 1: Vendedor findbyid ===");
	
		Vendedor vendedor = vendedordao.findById(3);
		System.out.println(vendedor);
		
		System.out.println("\n === TEST 2: Vendedor findbyDepartment ====");
		Departamento departamento = new Departamento(2, null);
		
		List<Vendedor> list = vendedordao.findByDepartamento(departamento);
		
		//testar ver se funciona
		for(Vendedor obj : list) {
			System.out.println(obj);
		}
	}

}
