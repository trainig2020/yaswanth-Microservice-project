package com.springboot.microservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.microservice.model.Department;
import com.springboot.microservice.model.Employee;
import com.springboot.microservice.model.ListOfDepartments;
import com.springboot.microservice.model.ListOfEmployees;

@RestController
public class DeptEmpViewController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(DeptEmpViewController.class);
    
	//@SuppressWarnings("unchecked")
	@RequestMapping(value = "/DeptList",method = RequestMethod.GET)
	 public ModelAndView  getAllDepartments(HttpServletRequest request,HttpServletResponse response,@RequestParam(required = false) Integer page) {
		 System.out.println("In Controller");
		 logger.info("Log : In Controller Getting all the Departments");
		 ListOfDepartments deptlist  =  getListOfDepartments();
		 System.out.println(deptlist.getDeptList().get(0));
		 List<Department> lstdept = new ArrayList<>();
		 
		 for(int i = 0; i < deptlist.getDeptList().size(); i++) {
			 lstdept.add(deptlist.getDeptList().get(i));
		 }
		 for (Department department : lstdept) {
			System.out.println(department.getDeptid()+department.getDepthead());
		}
		 
		 HttpSession session = request.getSession();
		 session.setAttribute("DeptList", lstdept);
		 session.setAttribute("page", page);
		 ModelAndView modelAndView = new ModelAndView("home1");
		 //modelAndView.addObject("DeptList", lstdept);
		 
		 PagedListHolder<Department> pagedListHolder = new PagedListHolder<Department>(lstdept);
		 
	     pagedListHolder.setPageSize(3);
	        
	     modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
	     
	     System.out.println( pagedListHolder.getPageCount());
	     
	     if (page == null || page < 1 || page > pagedListHolder.getPageCount())
	    	 page = 1;
	        modelAndView.addObject("page", page);       
	     if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
	    	    
	            pagedListHolder.setPage(0);
	            modelAndView.addObject("DeptList", pagedListHolder.getPageList());
	            
	     } else if (page <= pagedListHolder.getPageCount()) {
	            pagedListHolder.setPage(page - 1);
	            modelAndView.addObject("DeptList", pagedListHolder.getPageList());
	     }
		 
//		 PagedListHolder<Department> pageList;
//	        if(page == null) {
//	            pageList = new PagedListHolder<Department>();
//	            // Setting the source for PagedListHolder
//	            pageList.setSource(lstdept);
//	            pageList.setPageSize(2);
//	            // Setting PagedListHolder instance to session
//	            request.getSession().setAttribute("pageList", pageList);
//	        }else if(page.equals("prev")) {
//	            // get the user list from session
//	        	pageList = (PagedListHolder<Department>)request.getSession().getAttribute("pageList");
//	            // switch to previous page
//	        	pageList.previousPage();
//	        }else if(page.equals("next")) {
//	        	pageList = (PagedListHolder<Department>)request.getSession().getAttribute("pageList");
//	            // switch to next page
//	        	pageList.nextPage();
//	        }else {
//	            int pageNum = Integer.parseInt(page);
//	            pageList = (PagedListHolder<Department>)request.getSession().getAttribute("pageList");
//	            // set the current page number
//	            // page number starts from zero in PagedListHolder that's why subtracting 1
//	            pageList.setPage(pageNum - 1);
//	        }
		 modelAndView.addObject("homepage", "main");
		 return modelAndView;
	 }
	
	
	public ListOfDepartments getListOfDepartments() {
		return restTemplate.getForObject("http://gateway-service/Department/GetAll", ListOfDepartments.class);
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/NewDepartment" ,method =RequestMethod.GET )
	 public ModelAndView newDepartment(HttpServletRequest request,HttpServletResponse response) {
		 String Register = "newform";
		 HttpSession session1 = request.getSession();
		 List<Department> lst = (List<Department>) session1.getAttribute("DeptList");
		 session1.setAttribute("DeptList", lst);
		 Integer page = (Integer) session1.getAttribute("page");
		 System.out.println("page"+page);
		 ModelAndView modelAndView = new ModelAndView();
          PagedListHolder<Department> pagedListHolder = new PagedListHolder<Department>(lst);
		 
	     pagedListHolder.setPageSize(3);
	        
	     modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
	     
	     System.out.println( pagedListHolder.getPageCount());
	     
	     if (page == null || page < 1 || page > pagedListHolder.getPageCount())
	    	 page = 1;
	        modelAndView.addObject("page", page);       
	     if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
	    	    
	            pagedListHolder.setPage(0);
	            modelAndView.addObject("DeptList", pagedListHolder.getPageList());
	            
	     } else if (page <= pagedListHolder.getPageCount()) {
	            pagedListHolder.setPage(page - 1);
	            modelAndView.addObject("DeptList", pagedListHolder.getPageList());
	     }
	     //modelAndView.addObject("DeptList", lst);
		 modelAndView.addObject("Register", Register);
		 modelAndView.addObject("createdept", "newdept");
		 modelAndView.setViewName("home1");
		 Department department = new Department();
		 modelAndView.addObject("department", department);
		 return modelAndView;
	 }
	
	 
	 @RequestMapping(value = "/CreateDepartment", method = RequestMethod.POST)
	 public ModelAndView insertDepartment(@ModelAttribute Department department) {
		 logger.info("Adding a new department into the database");
	     addDepartment(department);
		 //restTemplate.postForObject("http://gateway-service/Department/AddDepartment",department,Department.class);
		 return new ModelAndView("redirect:/DeptList");
	 }
	 
	 
	public Department addDepartment(Department department) {
		return restTemplate.postForObject("http://gateway-service/Department/AddDepartment",department,Department.class);
	}
	
	
	 
	 @RequestMapping(value = "/UpdateDepartment")
	 public ModelAndView updateDepartment(@ModelAttribute Department department, HttpServletRequest request,HttpServletResponse response) {
		 int deptid =Integer.parseInt(request.getParameter("deptid"));
		 logger.info("Updating adepartment in the database");
	     upadateDept(department, deptid);
		 //restTemplate.put("http://gateway-service/Department/updateDepartment/"+deptid,department);
		 return new ModelAndView("redirect:/DeptList");
	 }
	 
	 
	public void upadateDept(Department department, int deptid) {
	  restTemplate.put("http://gateway-service/Department/updateDepartment/"+deptid,department);
	}	 
	 
	
	
	 @RequestMapping(value = "/DeleteDepartment")
	 public ModelAndView deleteDepartment(HttpServletRequest request,HttpServletResponse response) {
		 int deptid =Integer.parseInt(request.getParameter("deptid"));
		 logger.info("Deleting a department in the database");
		 deleteDept(deptid);
		 //restTemplate.delete("http://gateway-service/Department/DeleteDepartment/"+deptid);
		 return new ModelAndView("redirect:/DeptList");
	 }
	 
	 
	public void deleteDept(int deptid) {
		restTemplate.delete("http://gateway-service/Department/DeleteDepartment/"+deptid);
	}
	
	 
	 @SuppressWarnings("unchecked")
	@RequestMapping(value = "/GetDepartment",method = RequestMethod.GET)
	 public ModelAndView getDepartmentId(HttpServletRequest request,HttpServletResponse response) {
		int deptid =  Integer.parseInt(request.getParameter("deptid"));
		HttpSession session2 = request.getSession();
		List<Department> lst = (List<Department>) session2.getAttribute("DeptList");
		ModelAndView modelAndView = new ModelAndView("home1");
		//modelAndView.addObject("DeptList", lst);
		Integer page = (Integer) session2.getAttribute("page");
        PagedListHolder<Department> pagedListHolder = new PagedListHolder<Department>(lst);
		 
	     pagedListHolder.setPageSize(3);
	        
	     modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
	     
	     System.out.println( pagedListHolder.getPageCount());
	     
	     if (page == null || page < 1 || page > pagedListHolder.getPageCount())
	    	 page = 1;
	        modelAndView.addObject("page", page);       
	     if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
	    	    
	            pagedListHolder.setPage(0);
	            modelAndView.addObject("DeptList", pagedListHolder.getPageList());
	            
	     } else if (page <= pagedListHolder.getPageCount()) {
	            pagedListHolder.setPage(page - 1);
	            modelAndView.addObject("DeptList", pagedListHolder.getPageList());
	     }
		modelAndView.addObject("departmentid", deptid);
		return  modelAndView;		
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	@RequestMapping(value = "/showdepartments",method = RequestMethod.GET)
	 public ModelAndView showDepartments(HttpServletRequest request,HttpServletResponse response,@RequestParam(required = false) Integer page) {
		 HttpSession session3 = request.getSession();
		 List<Department> lstdept1 = (List<Department>) session3.getAttribute("DeptList");
		 session3.setAttribute("DeptListemp", lstdept1);
		 session3.setAttribute("pageEmp", page);
		 ModelAndView modelAndView = new ModelAndView("home1");
   	 modelAndView.addObject("DeptListemp", lstdept1);
		 int deptid =  lstdept1.get(0).getDeptid();
		 modelAndView.addObject("name", "names");
		 if (page != null) {
			 return new ModelAndView("redirect:/emplist?deptid="+deptid+"&page="+page);
		 }
		 return new ModelAndView("redirect:/emplist?deptid="+deptid);
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	@RequestMapping(value = "/emplist")
		public ModelAndView getAllEmployees(HttpServletRequest request,HttpServletResponse response,@RequestParam(required = false) Integer page) {
			int deptid =Integer.parseInt(request.getParameter("deptid"));
			List<Employee> lstemp = new ArrayList<>();
			logger.info("Log : In Controller Getting all the Employees");
			ListOfEmployees  lst = getAllDepartments(deptid);
			for (int i = 0; i < lst.getListOfEmployee().size(); i++) {
				lstemp.add(lst.getListOfEmployee().get(i));
			}
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("EmpList", lstemp);
			
			//Integer page = (Integer) httpSession.getAttribute("page");
			
			List<Department> lstdept1 = (List<Department>) httpSession.getAttribute("DeptList");
			ModelAndView modelAndView = new ModelAndView("home1");
			modelAndView.addObject("DeptListemp", lstdept1);
			//modelAndView.addObject("EmpList", lstemp);
			
			PagedListHolder<Employee> pagedListHolder = new PagedListHolder<Employee>(lstemp);
			 
		     pagedListHolder.setPageSize(2);
		        
		     modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
		     
		     System.out.println( pagedListHolder.getPageCount());
		     
		     if (page == null || page < 1 || page > pagedListHolder.getPageCount())
		    	 page = 1;
		        modelAndView.addObject("page", page);       
		     if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
		    	    
		            pagedListHolder.setPage(0);
		            modelAndView.addObject("EmpList", pagedListHolder.getPageList());
		            
		     } else if (page <= pagedListHolder.getPageCount()) {
		            pagedListHolder.setPage(page - 1);
		            modelAndView.addObject("EmpList", pagedListHolder.getPageList());
		     }
			modelAndView.addObject("homepage", "emppage");
			modelAndView.addObject("DeptId", deptid);
			modelAndView.addObject("name", "names");		
			return modelAndView;
			}

	 
	public ListOfEmployees getAllDepartments(int deptid) {
		return restTemplate.getForObject("http://gateway-service/Department/"+deptid+"/employees", ListOfEmployees.class);
	}
		
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
		public ModelAndView newContact(HttpServletRequest request,HttpServletResponse response) {
			String Register  = "NewForm";
			HttpSession session1 = request.getSession();
			List<Employee> lst =(List<Employee>)session1.getAttribute("EmpList");
			ModelAndView model = new ModelAndView("home1");
			Integer page = (Integer) session1.getAttribute("page");
			PagedListHolder<Employee> pagedListHolder = new PagedListHolder<Employee>(lst);
			 
		     pagedListHolder.setPageSize(2);
		        
		     model.addObject("maxPages", pagedListHolder.getPageCount());
		     
		     System.out.println( pagedListHolder.getPageCount());
		     
		     if (page == null || page < 1 || page > pagedListHolder.getPageCount())
		    	 page = 1;
		     model.addObject("page", page);       
		     if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
		    	    
		            pagedListHolder.setPage(0);
		            model.addObject("EmpList", pagedListHolder.getPageList());
		            
		     } else if (page <= pagedListHolder.getPageCount()) {
		            pagedListHolder.setPage(page - 1);
		            model.addObject("EmpList", pagedListHolder.getPageList());
		     }
			
			//model.addObject("EmpList", lst);
			model.addObject("Register", Register);
			model.addObject("insertEmployee", "newemployee");
			model.addObject("homepage", "emppage");		
			return model;	
		}

		
		@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
		public ModelAndView saveEmployee(@ModelAttribute Employee employee,HttpServletRequest request,HttpServletResponse response) {
			int edid =  Integer.parseInt(request.getParameter("edid"));
			logger.info("Log : In Controller Adding  new employee to the database");
			addEmployee(employee, edid);	
			 //restTemplate.postForObject("http://gateway-service/Department/employees/"+edid+"/addEmployee", employee, Employee.class);
			return new ModelAndView("redirect:/emplist?deptid="+edid);		
		}
		

		public Employee addEmployee(Employee employee, int edid) {
			return restTemplate.postForObject("http://gateway-service/Department/employees/"+edid+"/addEmployee", employee, Employee.class);
		}

		
		@RequestMapping(value = "/deleteEmployee")
		public ModelAndView deleteEmployee(HttpServletRequest request,HttpServletResponse response) {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			int edid =  Integer.parseInt(request.getParameter("did"));
			logger.info("Log : In Controller Deleting  a employee in the database");
			deleteEmp(employeeId, edid);
			//restTemplate.delete("http://gateway-service/Department/employees/"+edid+"/deleteEmployee/"+employeeId);
			return new ModelAndView("redirect:/emplist?deptid="+edid);	
		}
		

		public void deleteEmp(int employeeId, int edid) {
			restTemplate.delete("http://gateway-service/Department/employees/"+edid+"/deleteEmployee/"+employeeId);
		}

		
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
		public ModelAndView editContact(HttpServletRequest request,HttpServletResponse response) {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			int did =  Integer.parseInt(request.getParameter("did"));
			HttpSession session2 = request.getSession();
			List<Employee> lst =(List<Employee>) session2.getAttribute("EmpList");
			session2.setAttribute("EmpList", lst);
			ModelAndView model = new ModelAndView("home1");
			
			Integer page = (Integer) session2.getAttribute("page");
			PagedListHolder<Employee> pagedListHolder = new PagedListHolder<Employee>(lst);
			 
		     pagedListHolder.setPageSize(3);
		        
		     model.addObject("maxPages", pagedListHolder.getPageCount());
		     
		     System.out.println( pagedListHolder.getPageCount());
		     
		     if (page == null || page < 1 || page > pagedListHolder.getPageCount())
		    	 page = 1;
		     model.addObject("page", page);       
		     if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
		    	    
		            pagedListHolder.setPage(0);
		            model.addObject("EmpList", pagedListHolder.getPageList());
		            
		     } else if (page <= pagedListHolder.getPageCount()) {
		            pagedListHolder.setPage(page - 1);
		            model.addObject("EmpList", pagedListHolder.getPageList());
		     }
			model.addObject("homepage", "emppage");
			//model.addObject("EmpList", lst);
			model.addObject("employeeid", employeeId);
			model.addObject("Did", did);
			return model;
		}
		
		
		@RequestMapping(value = "/updateEmployee")
		public ModelAndView updateEmployee(@ModelAttribute Employee employee,HttpServletRequest request,HttpServletResponse response) {
			int employeeId = Integer.parseInt(request.getParameter("empid"));
			int did =  Integer.parseInt(request.getParameter("edid"));
			System.out.println("In Method Update");
			logger.info("Log : In Controller Updating  a employee in the database");
			updateEmp(employee, employeeId, did);
			//restTemplate.put("http://gateway-service/Department/employees/"+did+"/updateEmployee/"+employeeId, employee);
			System.out.println("In Method Update");
			return new ModelAndView("redirect:/emplist?deptid="+did);
		}

		
		public void updateEmp(Employee employee, int employeeId, int did) {
			System.out.println("From Test Case");
			restTemplate.put("http://gateway-service/Department/employees/"+did+"/updateEmployee/"+employeeId, employee);
		}
	
	
}
