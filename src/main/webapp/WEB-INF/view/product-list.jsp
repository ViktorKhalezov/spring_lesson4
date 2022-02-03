<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Product List</title>
    </head>
    <body>
        <h1>Product List</h1>
        <ul>
           <c:forEach var="product" items="${products}">
            <c:url var="viewUrl" value="/products/${product.id}"/>
            <li> <a href="${viewUrl}">View</a>
            ${product.toString()}</li>
           <br>
           </c:forEach>
        </ul>
        <br>
        <c:url var="addProductUrl" value="/products/form"/>
        <a href="${addProductUrl}">Add Product</a>
    </body>
</html>