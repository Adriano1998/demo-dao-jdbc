package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao{

	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor findById(Integer id) {
		// TODO Auto-generated method stub
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					 + "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
					//primeira interroga��o, passando o id como argumento.
					st.setInt(1, id);	
					//para executar
					rs = st.executeQuery();
					
					//testar se veio algum resultado, se retornou algum registro.
					if(rs.next()) {
						//dando verdadeiro - retornou a consulta
						//instanciamos um departamento e setamos os valores dele.
						Departamento dep = instantiateDepartamento(rs);
						//criar o objeto seller e apontando para o departamento
						Vendedor obj = instantiateVendedor(rs, dep);
						return obj;
					}
					//n�o existia nenhum vendedor com esse id que foi passado por parametro.
					return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
private Vendedor instantiateVendedor(ResultSet rs, Departamento dep) throws SQLException {
	Vendedor obj = new Vendedor();
	obj.setId(rs.getInt("Id"));
	obj.setNome(rs.getString("Name"));
	obj.setEmail(rs.getString("Email"));
	obj.setSalario(rs.getDouble("BaseSalary"));
	obj.setData_aniver(rs.getDate("BirthDate"));
	//associa��o de objetos. Colocou o dep instanciado la de cima
	obj.setDepartamento(dep);
	return obj;
	}

	//propagou a exce��o atraves desse throws, porque a exce��o ja esta sendo tratada la em cima.
	private Departamento instantiateDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
