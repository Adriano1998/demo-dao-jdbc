package application;

import java.util.Date;

import model.entities.Departamento;
import model.entities.Vendedor;

public class Principal {

	public static void main(String[] args) {
		
		Departamento obj = new Departamento(1, "Livros");
		
		Vendedor vendedor = new Vendedor(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		
		System.out.println(vendedor);

		
	}

}
