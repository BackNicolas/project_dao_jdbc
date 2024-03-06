package aplication;

import entities.Seller;
import model.dao.FactoryDao;
import model.dao.SellerDao;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = FactoryDao.createSellerDao();
		
		System.out.println("=== TEST 1: seller FindById ===");
		Seller seller = sellerDao.findId(3);
		System.out.println(seller);
	}

}
