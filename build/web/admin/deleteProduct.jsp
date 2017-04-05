<%-- 
    Document   : deleteProduct
    Created on : Mar 6, 2017, 7:13:18 PM
    Author     : DennisFedorchuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Product</title>
    </head>
    <body>
        <h1>Are you sure you want to delete this?</h1>
        <form action="controllers?action=deleteProduct&amp;productCode=${product.code}" method="post">     
            <label>Product Code: ${product.code}</label><br>
            <label>Product Description: ${product.description}</label><br>
            <label>Product Price: ${product.price}</label><br>
            <input type="submit" value="Yes" name="yesButton" class="margin_left" />
            <input type="submit" value="No" name="viewProducts" />
        </form>
    </body>
</html>
