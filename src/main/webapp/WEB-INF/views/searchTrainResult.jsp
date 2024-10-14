<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Train Search Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 1000px;
            width: 100%;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        a.book-link {
            padding: 8px 12px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }

        a.book-link:hover {
            background-color: #45a049;
        }

        p {
            text-align: center;
            font-size: 1.1em;
            color: #555;
        }

        @media (max-width: 768px) {
            th, td {
                font-size: 0.9em;
                padding: 10px;
            }

            h2 {
                font-size: 1.5em;
            }

            a.book-link {
                font-size: 0.9em;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Train Search Results</h2>

        <c:choose>
            <c:when test="${not empty trains}">
                <table>
                    <thead>
                        <tr>
                            <th>Train Name</th>
                            <th>Source</th>
                            <th>Destination</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Available Seats</th>
                            <th>Price</th>
                            <th>Book</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="train" items="${trains}">
                            <tr>
                                <td>${train.name}</td>
                                <td>${train.source}</td>
                                <td>${train.destination}</td>
                                <td>${train.date}</td>
                                <td>${train.time}</td>
                                <td>${train.availableSeats}</td>
                                <td>${train.price}</td>
                                <td><a href="/booking/create?trainId=${train.id}&name=${train.name}&price=${train.price}" class="book-link">Book Now</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No trains found.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
