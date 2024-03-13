package model.dao;

import db.DB;
import model.dao.imtl.DepartimentDaoJDBC;
import model.dao.imtl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartimentDaoJDBC(DB.getConnection());
	}
}