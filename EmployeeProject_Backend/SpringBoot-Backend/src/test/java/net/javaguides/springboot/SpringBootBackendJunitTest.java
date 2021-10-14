package net.javaguides.springboot;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.employeemanagerapp.EmployeeManagementApplication;
import com.mindtree.employeemanagerapp.model.Employee;
import com.mindtree.employeemanagerapp.repository.EmployeeRepository;


@SpringBootTest(classes=EmployeeManagementApplication.class)
@RunWith(SpringRunner.class)
class SpringBootUnitTest
{

	    @Autowired
	    private EmployeeRepository employeeRepository;
	        

	    // JUnit test for saveEmployee
	    @Test
	    @Rollback(value = false)
	    @DirtiesContext 
	    public void saveEmployeeTest(){
            Employee employee=new Employee("Manaswi","Ketha","manu@gmail.com");
	        employeeRepository.save(employee);

	        Assertions.assertThat(employee.getId()).isGreaterThan(0);
	    }

	    @Test
	    @DirtiesContext 
	    public void getEmployeeTest(){

	        Employee employee = employeeRepository.findById(3L).get();

	        Assertions.assertThat(employee.getId()).isEqualTo(3L);

	    }

	    @Test
	    public void getListOfEmployeesTest(){

	        List<Employee> employees = employeeRepository.findAll();

	        Assertions.assertThat(employees.size()).isGreaterThan(0);

	    }

	    @Test
	    @Rollback(value = false)
	    public void updateEmployeeTest(){

	        Employee employee = employeeRepository.findById(12L).get();

	        employee.setEmailId("manaswi@gmail.com");

	        Employee employeeUpdated =  employeeRepository.save(employee);

	        Assertions.assertThat(employeeUpdated.getEmailId()).isEqualTo("manu@gmail.com");

	    }

	    @Test
	    @Rollback(value = false)
	    public void deleteEmployeeTest(){

	        Employee employee = employeeRepository.findById(11L).get();

	        employeeRepository.delete(employee);
	        Employee employee1 = null;

	        Optional<Employee> optionalEmployee = employeeRepository.findByEmailId("div@gmail.com");

	        if(optionalEmployee.isPresent()){
	            employee1 = optionalEmployee.get();
	        }

	        Assertions.assertThat(employee1).isNull();
	    }


	

}