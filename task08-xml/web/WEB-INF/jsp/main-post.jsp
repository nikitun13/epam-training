<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <title>Main page</title>
</head>
<body>
<h1>Gems list:</h1>
<table border="1">
    <caption>Gems from XML file</caption>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>preciousness</th>
        <th>origin</th>
        <th>color</th>
        <th>transparency</th>
        <th>facets-number</th>
        <th>value</th>
        <th>date</th>
        <th>laboratory</th>
    </tr>
    <c:forEach var="gem" items="${requestScope.gems}">
        <tr>
            <td>${gem.id}</td>
            <td>${gem.name}</td>
            <td>${gem.preciousness.name().toLowerCase()}</td>
            <td>${gem.origin}</td>
            <td>${gem.parameters.color.name().toLowerCase()}</td>
            <td>${gem.parameters.transparency}</td>
            <td>${gem.parameters.facetsNumber}</td>
            <td>${gem.value}</td>
            <td>${gem.date.toString()}</td>
            <td>${requestScope.laboratoriesMap[gem.id]}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
