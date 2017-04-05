<%-- 
    Document   : index.jsp
    Created on : Mar 6, 2017, 3:01:19 PM
    Author     : DennisFedorchuk
--%>
<link rel="stylesheet" href="styles/main.css" type="text/css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri = "/WEB-INF/tlds/tagDescriptor.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Product</title>
    </head>
    <body>
        <h1>Product</h1>
        <p><ct:isEmpty color = "blue" field=""/> marks required fields(I did add 
            tags after the input but my previous code is set to not allow empty 
            fields. Thats why you will never see the tags on this page. Go to 
            add page to see them)</p>
        <p>${message}</p>
        <form action="controllers?action=editProduct" method="post">     
            <label class="pad_top">Code:</label>
            <input type="text" size = 4 name="codeInput" value="${product.code}" readonly>
            <ct:isEmpty color = "blue" field="${product.code}"/><br>
            <label class="pad_top">Description:</label>
            <input type="text" name="descriptionInput" value="${product.description}">
            <ct:isEmpty color = "blue" field="${product.code}"/><br>
            <label class="pad_top">Price:</label>
            <input type="text" name="priceInput" value="${product.price}">
            <ct:isEmpty color = "blue" field="${product.code}"/><br>        
            <label>&nbsp;</label>
            <input type="submit" value="Chage Product" name="editProduct" class="margin_left" />
            <input type="submit" value="View Products" name="viewProducts" />
        </form>
    </body>
</html>
