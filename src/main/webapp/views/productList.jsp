<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product list by CategoryID</title>
    <style>
        table,
        th,
        td {
            border: 0.5px solid black;
        }
        button {
            margin: 2px;
            background-color: azure;
        }
    </style>
</head>
<body>
    <h1>Product list by CategoryID</h1>    
    <table>
        <tr>
            <th>Product's ID</th>
            <th>Product's Name</th>            
            <th>Price</th>
            <th>Description</th>
            <th>Actions</th>
          </tr>
          <c:forEach var="product" items="${products}">
              <tr>
                  <td>${product.getProductID()}</td>
                  <td>${product.getProductName()}</td>                  
                  <td>${product.getFormattedPrice()}</td>
                  <td>${product.getDescription()}</td>                                    
                  <td>
                      <button>
                        <a href="../../products/changeCategory/${product.getProductID()}" style="text-decoration: none">
                          Update this Product
                        </a>
                      </button>
                </td>
              </tr>
          </c:forEach>
    </table>
    <br>
    <button>
        <a href="../../categories" style="text-decoration: none">
            Go back to Category Page
        </a>
    </button>
</body>
</html>