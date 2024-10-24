package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement ts = null;

		try {
			ts = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			ts.setString(1, obj.getName());

			int rowsLine = ts.executeUpdate();

			if (rowsLine > 0) {
				ResultSet rs = ts.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				} else {
					throw new DbException("Erro ao obter o ID gerado para o departamento.");
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Problema inesperado, não foi salvo!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ts);
		}

	}

	@Override
	public void update(Department obj) {
		PreparedStatement ts = null;
		
		 try {
			 ts = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
			 ts.setString(1, obj.getName());
			 ts.setInt(2, obj.getId());
			 ts.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ts);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ts = null;
		
		try {
			
			ts= conn.prepareStatement("DELETE from department WHERE Id = ?");
			ts.setInt(1, id);
			
			Department ids = findById(id);
			
			if (ids != null) {
				ts.executeUpdate();
			}else {
				throw new DbException("Não foi possível excluir");
			}
				
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ts);
		}
	

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement ts = null;
		ResultSet rs = null;

		try {

			ts = conn.prepareStatement("SELECT * FROM department where Id = ?");
			ts.setInt(1, id);
			rs = ts.executeQuery();

			if (rs.next()) {
				Department department = instanciaDepartment(rs);
				return department;
			} else {
				throw new DbException("Não encontrado!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ts);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement ts = null;
		ResultSet rs = null;

		List<Department> listDepartment = new ArrayList<>();

		try {
			ts = conn.prepareStatement("SELECT * FROM department;");
			rs = ts.executeQuery();

			while (rs.next()) {
				Department department = instanciaDepartment(rs);
				listDepartment.add(department);
			}
			return listDepartment;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ts);
			DB.closeResultSet(rs);
		}

	}

	// instancias de obj

	private Department instanciaDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("Id"));
		department.setName(rs.getString("Name"));
		return department;
	}
}
