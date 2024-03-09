package aplication;


import java.util.Date;
import java.util.List;

import entities.Department;
import entities.Seller;
import model.dao.FactoryDao;
import model.dao.SellerDao;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = FactoryDao.createSellerDao();
		
		System.out.println("=== TEST 1: seller FindById ===");
		Seller seller = sellerDao.findId(3);
		System.out.println(seller);
		
		System.out.println("\n === TEST 2: seller FindByDepartment ===");
		Department department = new Department(2,"");
		List<Seller> list = sellerDao.findBYDepartment(department);
		
		for(Seller obj : list) {
			System.out.println(obj);
		}
		

		System.out.println("\n === TEST 3: seller FindAll ===");
		list = sellerDao.findAll();
		
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n === TEST 4: seller Insert ===");
		Seller newSeller = new Seller(null, "Greg", "Greg@gmail.com", new Date(), 3000.00, department);
		sellerDao.insert(newSeller);
		System.out.println("New Id: " + newSeller.getId());
		
		
	}

}
