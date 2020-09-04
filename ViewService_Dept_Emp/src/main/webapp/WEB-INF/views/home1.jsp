<%@page import="java.util.ArrayList"%>
<%-- <%@page import="com.yaswanth.dto.Department"%> --%>
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
	padding: 4px;
}
</style>
<title><spring:message code="label.getall"></spring:message></title>
</head>
<body>

	<form:errors path="department.*"></form:errors>
	<div id="header">
		<h2>
			<a href="/DeptList?siteLanguage=en">English</a>|<a
				href="/DeptList?siteLanguage=jp">Japenese</a>
		</h2>
	</div>

	<div id="sidebar-left">
		<a href="showdepartments">[+]</a>departments
		<c:if test="${name eq 'names'}">
			<c:forEach var="dept" items="${DeptListemp}">
				<c:if test="${not empty dept}">

					<input type="hidden" name="deptid" value="${dept.deptid}">

					<br>
				             &nbsp <a href="DeptList"><b>=></b></a>&nbsp <button
						type="button" class="btn btn-success" style="width: 100px;">
						<a href="emplist?deptid=${dept.deptid}"><font
							color="lightgreen">${dept.deptname} </font></a>
					</button>
					<br>

				</c:if>
			</c:forEach>
		</c:if>
	</div>

	<div id=main>
		<div align="center">
			<c:if test="${homepage ne 'emppage'}">
				<c:if test="${Register eq 'newform'}">
					<form action="CreateDepartment" method="post">
				</c:if>
				<c:if test="${Register ne 'newform'}">
					<form action="UpdateDepartment" method="post">
				</c:if>

				<table class="t" border="1 px solid black" align="center">
					<thead>
						<tr>
							<th colspan="3" style="text-align: center"><h1>
									<spring:message code="label.details"></spring:message>
								</h1></th>
							<th colspan="2" style="text-align: center"><a
								href="NewDepartment"><h2>
										<spring:message code="label.insertnew"></spring:message>
									</h2></a></th>
						</tr>
						<tr>
							<th style="text-align: center"><spring:message
									code="label.DeptID"></spring:message></th>
							<th style="text-align: center"><spring:message
									code="label.DeptName"></spring:message></th>
							<th style="text-align: center"><spring:message
									code="label.DeptHead"></spring:message></th>
							<th style="text-align: center"><spring:message
									code="label.Update"></spring:message></th>
							<th style="text-align: center"><spring:message
									code="label.Delete"></spring:message></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${DeptList}" var="dept">

							<c:if test="${departmentid eq dept.deptid}">
								<tr>
									<td><input type="text" name="deptid"
										value="${dept.deptid}" readonly="readonly" /></td>
									<td><input type="text" name="deptname"
										value="${dept.deptname}" /></td>
									<td><input type="text" name="depthead"
										value="${dept.depthead}" /></td>
									<td><input type="submit"
										value="<spring:message code="label.Update"></spring:message>" /></td>
								</tr>
							</c:if>
							<c:if test="${departmentid ne dept.deptid}">
								<tr>
									<td><c:out value="${dept.deptid}" /></td>
									<td><c:out value="${dept.deptname}" /></td>
									<td><c:out value="${dept.depthead}" /></td>
									<td><a href="GetDepartment?deptid=${dept.deptid}"><spring:message
												code="label.Update"></spring:message></a></td>
									<td><a href="DeleteDepartment?deptid=${dept.deptid}"><h3>
												<spring:message code="label.Delete"></spring:message>
											</h3></a></td>
								</tr>
							</c:if>
						</c:forEach>
						<c:if test="${createdept eq 'newdept'}">
							<tr>
								<td><input type="text" name="deptid" /></td>
								<td><input type="text" name="deptname" /></td>
								<td><input type="text" name="depthead" /></td>
								<td colspan="2"><input type="submit"
									value="<spring:message code="label.S"></spring:message>" /></td>
							</tr>
						</c:if>
					</tbody>
				</table>	  
				</form>
			
			<div id="pagination">
			            <c:url value="/DeptList" var="prev">
                            <c:param name="page" value="${page-1}" />
                        </c:url>
                        <c:if test="${page > 1}">
                            <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
                        </c:if>

 

                        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
                            <c:choose>
                                <c:when test="${page == i.index}">
                                    <span>${i.index}</span>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="/DeptList" var="url">
                                        <c:param name="page" value="${i.index}" />
                                    </c:url>
                                    <a href='<c:out value="${url}" />'>${i.index}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:url value="/DeptList" var="next">
                            <c:param name="page" value="${page + 1}" />
                        </c:url>
                        <c:if test="${page + 1 <= maxPages}">
                            <a href='<c:out value="${next}" />' class="pn next">Next</a>
                        </c:if>
               </div>
             </c:if>
		</div>
        <div>
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
						<th colspan="4" style="text-align: center"><h1>
								<spring:message code="label.Empdetails"></spring:message>
							</h1></th>
						<th colspan="2" style="text-align: center"><a
							href="newEmployee">
								<h2>
									<spring:message code="label.insertnewEmp"></spring:message>
								</h2>
						</a></th>
					</tr>
					<tr>
						<th style="text-align: center"><spring:message
								code="label.EmpID"></spring:message></th>
						<th style="text-align: center"><spring:message
								code="label.EDID"></spring:message></th>
						<th style="text-align: center"><spring:message
								code="label.EmpName"></spring:message></th>
						<th style="text-align: center"><spring:message
								code="label.EmpLoc"></spring:message></th>
						<th style="text-align: center"><spring:message
								code="label.Update"></spring:message></th>
						<th style="text-align: center"><spring:message
								code="label.Delete"></spring:message></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${EmpList}" var="emp">
						<c:if test="${employeeid eq emp.empid}">
							<c:if test="${Did eq emp.edid}">
								<tr>
									<td><input type="text" name="empid" value="${emp.empid}"
										readonly="readonly" /></td>
									<td><input type="text" name="edid" value="${emp.edid}"
										readonly="readonly" /></td>
									<td><input type="text" name="empname"
										value="${emp.empname}" /></td>
									<td><input type="text" name="emploc" value="${emp.emploc}" /></td>
									<td><input type="submit"
										value="<spring:message code="label.Update"></spring:message>" /></td>
								</tr>
							</c:if>
						</c:if>
						<c:if test="${employeeid ne emp.empid}">
							<tr>

								<td>${emp.empid}</td>
								<td>${emp.edid}</td>
								<td>${emp.empname}</td>
								<td>${emp.emploc}</td>
								<td><a href="getEmployee?id=${emp.empid}&did=${emp.edid}"><spring:message
											code="label.Update"></spring:message></a></td>
								<td><a
									href="deleteEmployee?id=${emp.empid}&did=${emp.edid}"> <spring:message
											code="label.Delete"></spring:message></a></td>
							</tr>
						</c:if>
					</c:forEach>
					<c:if test="${insertEmployee eq 'newemployee'}">
						<tr>
							<td><input type="text" name="empid" /></td>
							<td><input type="text" name="edid" /></td>
							<td><input type="text" name="empname" /></td>
							<td><input type="text" name="emploc" /></td>
							<td colspan="2"><input type="submit"
								value="<spring:message code="label.S"></spring:message>" /></td>
						</tr>
					</c:if>
				</tbody>
			</table>
			</form>
			<div id="pagination" >
		      <c:url value="/emplist" var="prev">
		                    <c:param name="deptid" value="${DeptId}"></c:param>
                            <c:param name="page" value="${page-1}" />
                        </c:url>
                        <c:if test="${page > 1}">
                            <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
                        </c:if>

 

                        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
                            <c:choose>
                                <c:when test="${page == i.index}">
                                    <span>${i.index}</span>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="/emplist" var="url">
                                        <c:param name="deptid" value="${DeptId}"></c:param>
                                        <c:param name="page" value="${i.index}" />
                                    </c:url>
                                    <a href='<c:out value="${url}" />'>${i.index}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:url value="/emplist" var="next">
                              <c:param name="deptid" value="${DeptId}"></c:param>
                            <c:param name="page" value="${page + 1}" />
                        </c:url>
                        <c:if test="${page + 1 <= maxPages}">
                            <a href='<c:out value="${next}" />' class="pn next">Next</a>
                        </c:if>
		</div>
		</c:if>
		</div>
	</div>
</body>
</html>