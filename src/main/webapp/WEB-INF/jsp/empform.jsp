<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    <%@include file="/WEB-INF/css/style.css" %>
</style>

    <div class="formClass">
        <h1>Wprowadź dane pracownika:</h1>
        <form:form method="post" action="save">
            <table>
                <form:hidden path="id"/>
                <tr>
                    <td>Imię :</td>
                    <td><form:input path="firstName"/></td>
                </tr>
                <tr>
                    <td>Nazwisko :</td>
                    <td><form:input path="lastName"/></td>
                </tr>
                <tr>
                    <td>E - mail :</td>
                    <td><form:input path="email"/></td>
                </tr>
                <tr>
                    <td>Wynagrodzenie :</td>
                    <td><form:input path="salary"/></td>
                </tr>

                <tr>
                    <td>Adres :</td>
                    <td><form:input path="address"/></td>
                </tr>
                <tr>
                    <td>Wiek :</td>
                    <td><form:input path="age"/></td>
                </tr>
                <tr>
                    <td>Miasto :</td>
                    <td><form:input path="city"/></td>
                </tr>
                <tr>
                    <td>Benefity :</td>
                    <td><form:input path="benefit"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Zapisz"/></td>
                </tr>
            </table>
        </form:form>
    </div>
