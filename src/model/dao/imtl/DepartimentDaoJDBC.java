package model.dao.imtl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import entities.Department;
import model.dao.DepartmentDao;

public class DepartimentDaoJDBC implements DepartmentDao{
	
	private Connection conn;
	
	public DepartimentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department dep) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO Department "
					+ "(Name, Id) "
					+ "VALUES "
					+ "(?, ?) ", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, dep.getName());
			st.setInt(2, dep.getId());
			
			int rowsAfected = st.executeUpdate();
			
			if(rowsAfected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					dep.setId(id);
				}
				
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Invalid Input");
			}
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department dep) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE Department "
					+ "SET Name = ? "
					+ "WHERE Id = ? ");
			
			st.setString(1, dep.getName());
			st.setInt(2, dep.getId());
			
			st.executeUpdate();
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM Department "
					+ "WHERE Id = ? ");
			
			st.setInt(1, id);
			
			st.executeUpdate();
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}


	@Override
	public Department findId(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * FROM department WHERE Id = ?");
				st.setInt(1, id);
				rs = st.executeQuery();
				if (rs.next()) {
					Department obj = new Department();
					obj.setId(rs.getInt("Id"));
					obj.setName(rs.getString("Name"));
					return obj;
			}
			return null;
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	

	@Override
	public List<Department> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM department ORDER BY Name ");
			
			rs = st.executeQuery();
			List<Department> list = new ArrayList<>();

			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				list.add(dep);
			}
			return list;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}


	
	
}
