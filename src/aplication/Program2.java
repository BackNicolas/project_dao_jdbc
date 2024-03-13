package aplication;


import java.util.List;
import java.util.Scanner;

import entities.Department;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TEST 1: seller FindById ===");
		Department dep = departmentDao.findId(3);
		System.out.println(dep);
		

		System.out.println("\n === TEST 3: seller FindAll ===");
		List<Department> list = departmentDao.findAll();
		
		for(Department obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n === TEST 4: seller Insert ===");
		Department newDepartment = new Department(5, "Greg");
		departmentDao.insert(newDepartment);
		System.out.println("New Id: " + newDepartment.getId());
		
		
		System.out.println("\n === TEST 5: seller Update ===");
		dep = departmentDao.findId(1);
		dep.setName("Pele Junior");
		departmentDao.update(dep);
		System.out.println("Update Complete!");
		
		System.out.println("\n === TEST 6: seller Delete ===");
		System.out.println("Enter with the ID to delete: ");
		int id = sc.nextInt();
		
		departmentDao.deleteById(id);
		
		System.out.println("Delete complete!");
		sc.close();
	}

}
