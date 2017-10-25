<%-- 
    Document   : ShoppingList
    Created on : Oct 11, 2017, 3:44:13 PM
    Author     : 679918
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        Hello ${username}!<br>
        <a href="ShoppingList?action=logout">Logout</a> 
        <hr>
        <h1>List</h1>
            <form action="shoppingList" method="Post">
                <input type="hidden" name="action" value="add">
                Add item: <input type="text" name="addItem">
                <input type="submit" value="Add">
            </form>

            <form action="shoppingList" method="Post">
                <table>
                    <c:forEach items="${items}" var="list" varStatus="status">
                        <tr>
                            <td>
                              <input type="radio" name="name" value="${list}">
                              ${list}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <input type="hidden" name="action" value="delete">
                <input type="submit" value="Delete">
            </form>
    </body>
</html>
