<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
h1 {
	color: red;
}
#header {
	background-color: red;
	width: 100%;
	height: 50px;
	text-align: center;
}
 #sidebar-left {
	float: left;
	width: 15%;
	background-color: #83A0FF;
}
#main {
	float: left;
	width: 85%;
	background-color: lightgray;
}
#footer {
	clear: both;
	height: 50px;
	width: 100%;
	text-align: center;
	background-color: black;
}
#sidebar-left {
	min-height: 600px
}
table.d, td {
	table-layout: fixed;
	width: 45%;
	height: 40px;
	padding: 8px;
}
</style>
<title>Getting All</title>
</head>
<body>
	 <div id="header">
		<h2>
			DEPARTMENT AND EMPLOYEE APPLICATION
		</h2>
	</div>
	
	<div id="sidebar-left">
        <a href="showdepartments">[+]</a>departments
        <c:if test="${name eq 'names'}">
				<c:forEach var="dept" items="${DeptListemp}">
					<c:if test="${not empty dept}">

						<input type="hidden" name="deptid" value="${dept.deptid}">

						<br>
				             &nbsp <a href="DeptList"><b>=></b></a>&nbsp <button type="button"
							class="btn btn-success" style="width:100px;">
							<a href="emplist?deptid=${dept.deptid}"><font color="lightgreen">${dept.deptname}
							</font></a>
						    </button>
						<br>

					</c:if>
				</c:forEach>
			</c:if>
	</div>

<div id =main>
 <div align="center">
 <c:if test="${homepage ne 'emppage'}">
	<c:if test="${Register eq 'newform'}">
		<form action="CreateDepartment" method="post">
	</c:if>
	<c:if test="${Register ne 'newform'}">
		<form action="UpdateDepartment" method="post">
	</c:if>
	<table class="t"  border="1 px solid black" align ="center"  >
		<thead>
			<tr>
				<th colspan="2"  style="text-align: center"><h1>Department Details</th>
				<th colspan="2" style="text-align: center"><a href="NewDepartment"><h2>Add New Department</h2></a></th>
			</tr>
			<tr>
				<th>DeptID</th>
				<th>DeptName</th>
				<th>DeptHead</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${DeptList}" var="dept">

				<c:if test="${departmentid eq dept.deptid}">
					<tr>
						<td><input type="text" name="deptid" value="${dept.deptid}"
							readonly="readonly" /></td>
						<td><input type="text" name="deptname"
							value="${dept.deptname}" /></td>
					    <td><input type="text" name="depthead"
							value="${dept.depthead}" /></td>
						<td><input type="submit"
							value="Update" /></td>
					</tr>
				</c:if>
				<c:if test="${departmentid ne dept.deptid}">
					<tr>
						<td><c:out value="${dept.deptid}" /></td>
						<td><c:out value="${dept.deptname}" /></td>
						<td><c:out value="${dept.depthead}" /></td>
						<td><a href="GetDepartment?deptid=${dept.deptid}">Update</a></td>
						<td><a href="DeleteDepartment?deptid=${dept.deptid}">Delete</a></td>
					</tr>
				</c:if>
			</c:forEach>
			<c:if test="${createdept eq 'newdept'}">
				<tr>
					<td><input type="text" name="deptid" /></td>
					<td><input type="text" name="deptname" /></td>
					<td><input type="text" name="depthead" /></td>
					<td colspan="2"><input type="submit"value="Save"/></td>
				</tr>
			</c:if>
		</tbody>
	</table>
</form>
</c:if>
</div> 
<c:if test="${homepage eq 'emppage'}">
    
	
	<c:if test="${Register eq 'NewForm'}">
		<form action="saveEmployee" method="post">
	</c:if>
	<c:if test="${Register ne 'NewForm'}">
		<form action="updateEmployee" method="post">
	</c:if>

	<table border="2" style="width: 100%"background-color:#eee;>
		<thead>
		    <tr>
				<th colspan="4"  style="text-align: center"><h1>Employee Details</h1></th>
				<th colspan="2" style="text-align: center"> <a href="newEmployee"><h2>Insert New Employee</h2></a></th>
			</tr>
			<tr>
				<th>EMPID</th>
				<th>EDID</th>
				<th>EMPNAME</th>
				<th>EmpLoc</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${EmpList}" var="emp">  		
				<c:if test="${employeeid eq emp.empid}">
				   <c:if test="${Did eq emp.edid}">
					<tr>
						<td><input type="text" name="empid"
							value="${emp.empid}" readonly="readonly" /></td>
						<td><input type="text" name="edid"
							value="${emp.edid}" readonly="readonly" /></td>
						<td><input type="text" name="empname"
							value="${emp.empname}" /></td>
						<td><input type="text" name="emploc"
							value="${emp.emploc}" /></td>
						<td><input type="submit" value="update" /></td>
					</tr>
				</c:if>
				</c:if>
				<c:if test="${employeeid ne emp.empid}">
					<tr>
						
                    <td>${emp.empid}</td>
                    <td>${emp.edid}</td>
                    <td>${emp.empname}</td>
                    <td>${emp.emploc}</td>
                    <td><a href="getEmployee?id=${emp.empid}&did=${emp.edid}">Update</a></td>
                     <td><a href="deleteEmployee?id=${emp.empid}&did=${emp.edid}">Delete</a></td>
					</tr>
				</c:if>
				</c:forEach>
					<c:if test="${insertEmployee eq 'newemployee'}">
						<tr>
							<td><input type="text" name="empid" /></td>
							<td><input type="text" name="edid"  /></td>
							<td><input type="text" name="empname" /></td>
							<td><input type="text" name="emploc" /></td>
							<td colspan="2"><input type="submit" value="save"/></td>
						</tr>
					</c:if>	
		</tbody>
	</table>
</form>
</c:if>
</div>
</body>
</html>