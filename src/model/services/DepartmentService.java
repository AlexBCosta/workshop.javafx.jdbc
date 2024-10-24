package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {

	private DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

	public List<Department> findAll() { /*
										 * dados mock, para testes List<Department> list = new ArrayList<>();
										 * list.add(new Department(1, "Books")); list.add(new Department(2,
										 * "Computers")); list.add(new Department(3, "Eletronics")); return list;
										 */
		return departmentDao.findAll();
	}
}
