package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
					//primeira interrogação, passando o id como argumento.
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
					//não existia nenhum vendedor com esse id que foi passado por parametro.
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
	//associação de objetos. Colocou o dep instanciado la de cima
	obj.setDepartamento(dep);
	return obj;
	}

	//propagou a exceção atraves desse throws, porque a exceção ja esta sendo tratada la em cima.
	private Departamento instantiateDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepName"));
		
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name");
	
					//para executar
					rs = st.executeQuery();
					
					//como são vários valores vai criar uma lista de resultado.
					List<Vendedor> list = new ArrayList<>();
					
					//chave - integer e o valor - departamento
					//criou um map vazio
					Map<Integer, Departamento> map = new HashMap<>();
					
					
					//while ao inves do if, porque pode retornar mais de um resultado.
					while(rs.next()) {
						//vai guardar dentro desse map qualquer departamento que instanciar
						//a cada vez que passar no while testa se o departamento ja existe - vai no map e tenta buscar com o metodo get
						// um departamento com esse id
						//se não existir esse rs.get retorna nulo e ai sim instancia o departamento
						Departamento dep = map.get(rs.getInt("DepartmentId"));
						
						//salva o departamento no dep
						if(dep == null) {
							dep = instantiateDepartamento(rs);
							//vai salvar esse departamento dentro do map para que na proxima vez possa verificar e ver que ja existe
							map.put(rs.getInt("DepartmentId"), dep);
						}
			
						Vendedor obj = instantiateVendedor(rs, dep);
						list.add(obj);
					}
					
					return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Vendedor> findByDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
					
					//primeira interrogação, passando o id como argumento do departamento, por isso o get.
					st.setInt(1, departamento.getId());	
					//para executar
					rs = st.executeQuery();
					
					//como são vários valores vai criar uma lista de resultado.
					List<Vendedor> list = new ArrayList<>();
					
					//chave - integer e o valor - departamento
					//criou um map vazio
					Map<Integer, Departamento> map = new HashMap<>();
					
					
					//while ao inves do if, porque pode retornar mais de um resultado.
					while(rs.next()) {
						//vai guardar dentro desse map qualquer departamento que instanciar
						//a cada vez que passar no while testa se o departamento ja existe - vai no map e tenta buscar com o metodo get
						// um departamento com esse id
						//se não existir esse rs.get retorna nulo e ai sim instancia o departamento
						Departamento dep = map.get(rs.getInt("DepartmentId"));
						
						//salva o departamento no dep
						if(dep == null) {
							dep = instantiateDepartamento(rs);
							//vai salvar esse departamento dentro do map para que na proxima vez possa verificar e ver que ja existe
							map.put(rs.getInt("DepartmentId"), dep);
						}
			
						Vendedor obj = instantiateVendedor(rs, dep);
						list.add(obj);
					}
					
					return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	
}
