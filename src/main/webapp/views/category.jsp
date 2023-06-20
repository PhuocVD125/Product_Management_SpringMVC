<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category list</title>
    <style>
        table,
        th,
        td {
            border: 1px solid black;
        }
        button {
            margin: 2px;
            background-color: azure;
        }
    </style>
</head>
<body>
    <h1>Product Management</h1>
    <h2>Category list</h2>
    <!--for better UI, let's use Bootstrap-->
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Actions</th>
          </tr>
        <c:forEach var = "category" items="${categories}">
          <tr>
            <td>${category.getCategoryID()}</td>
            <td>${category.getCategoryName()}</td>
            <td>${category.getDescription()}</td>
            <td>
                <button>
                    <a href="products/getProductsByCategoryID/${category.getCategoryID()}" style="text-decoration: none">
                        Show Products
                    </a>
                </button>
            </td>
          </tr>
        </c:forEach>
    </table><br>

    <button><a href="products/insertProduct" style="text-decoration: none">Insert new Product</a></button>

</body>
</html>
