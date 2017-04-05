<%-- 
    Document   : index
    Created on : Mar 6, 2017, 12:05:57 AM
    Author     : DennisFedorchuk
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--Include to use if and for loops and what not--%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
    </head>
    <body>
        <h1>Products</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Description</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody> 
                <c:forEach items="${productList}" var="listItem">
                    <tr>
                        <td>${listItem.code}</td>
                        <td>${listItem.description}</td>
                        <td>${listItem.price}</td>
                        <td><a href="controllers?action=editProduct&amp;productCode=${listItem.code}">Edit</a></td>
                        <td><a href="controllers?action=deleteProduct&amp;productCode=${listItem.code}">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
            <button onclick="window.location.href='controllers?action=addProduct'">Add Product</button>
    </body>
</html>
