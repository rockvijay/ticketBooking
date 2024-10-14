<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Booking Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f9;
            color: #333;
        }

        h2, h3 {
            color: #4CAF50;
            text-align: center;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center; /* Center-align all text inside the table */
        }

        table, th, td {
            border: 1px solid #dddddd;
        }

        th, td {
            padding: 10px;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }

        .button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
            display: inline-block;
            cursor: pointer;
        }

        .button:hover {
            background-color: #45a049;
        }

        .total-price {
            font-size: 18px;
            color: #333;
            margin: 20px auto;
            text-align: center;
        }
    </style>
</head>
<body>
    <h2>Booking Confirmed!</h2>

    <p style="text-align: center;">Your booking has been successfully confirmed.</p>

    <h3>Booking Details</h3>
    <table>
        <thead>
            <tr>
                <th>Train ID</th>
                <th>Train Name</th>
                <th>Passenger Name</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Booked Date</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="passenger" items="${passengers}">
                <tr>
                    <td><c:out value="${passenger.trainId}" /></td>
                    <td><c:out value="${passenger.name}" /></td>
                    <td><c:out value="${passenger.passengerName}" /></td>
                    <td><c:out value="${passenger.age}" /></td>
                    <td><c:out value="${passenger.gender == 1 ? 'Male' : 'Female'}" /></td>
                    <td><c:out value="${passenger.bookingDate}" /></td>
                    <td><c:out value="${passenger.price}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="total-price">
        <h3>Total Price: ${totalPrice}</h3>
    </div>

    <div class="button-container">
        <a href="/dabd/dashboard" class="button">Back to Dashboard</a>
    </div>

    <p style="text-align: center;">Thank you for booking with us! Have a great journey.</p>
</body>
</html>
