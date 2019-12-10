<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
    <%@include file="/WEB-INF/css/styleView.css" %>
</style>

<div class="container">
<h1>Aktualna lista pracowników</h1>
<table border="2" width="70%" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Imię</th>
        <th>Nazwisko</th>
        <th>E-mail</th>
        <th>Wynagrodzenie</th>
        <th>Adres</th>
        <th>Wiek</th>
        <th>Miasto</th>
        <th>Benefity</th>
        <th>Drukarki</th>
        <th>Akcja</th>
    </tr>
    <c:forEach var="emp" items="${list}">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.firstName}</td>
            <td>${emp.lastName}</td>
            <td>${emp.email}</td>
            <td>${emp.salary}</td>

            <td>${emp.address}</td>
            <td>${emp.age}</td>
            <td>${emp.city}</td>
            <td>${emp.benefit}</td>
            <td><c:if test ="${fn:length(emp.printers) != 0}">
                <select>
                    <c:forEach var="printers" items="${emp.printers}">
                		    <option>NAME: ${printers.name} MODEL: ${printers.model}</option>
                    </c:forEach>
                 </select>
            </c:if>
            </td>
            <td>
                <form:form method="post" action="delete">
                    <input type="hidden" id="id" name="id" value="${emp.id}"/>
                    <input type="submit" class="button" name="Delete" value="delete"/>
                </form:form>
                <form:form method="post" action="edit">
                    <input type="hidden" id="id" name="id" value="${emp.id}"/>
                    <input type="submit" class="button" name="Edit" value="edit"/>
                </form:form>
            </td>
        </tr>
    </c:forEach>

    <td>
        <form:form method="post" action="test">
            <input type="submit" class="button" name="test" value="test"/>
        </form:form>
    </td>
</table>
</div>



