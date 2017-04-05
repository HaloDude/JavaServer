<%-- 
    Document   : addProduct
    Created on : Mar 6, 2017, 6:40:42 PM
    Author     : DennisFedorchuk
--%>
<link rel="stylesheet" href="styles/main.css" type="text/css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="ct" uri = "/WEB-INF/tlds/tagDescriptor.tld" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <body>
        <h1>Add Product</h1>
        <p><ct:isEmpty color = "blue" field=""/> marks required fields</p>
        <p>${message}</p>
        <form action="controllers?action=addProduct" method="post">     
            <label class="pad_top">Code:</label>
            <input type="text" size = 4 name="codeInput" value = "${tempCode}"> 
            <ct:isEmpty color = "blue" field="${tempCode}"/><br>
            <label class="pad_top">Description:</label>
            <input type="text" name="descriptionInput" value = "${tempDescription}">
            <ct:isEmpty color = "blue" field="${tempDescription}"/><br>
            <label class="pad_top">Price:</label>
            <input type="text" name="priceInput" value = "${tempPrice}">
            <ct:isEmpty color = "blue" field="${tempPrice}"/><br>
            <label>&nbsp;</label>
            <input type="submit" value="Add Product" name="addProduct" class="margin_left" />
            <input type="submit" value="View Products" name="viewProducts" />
        </form>
    </body>
</html>
