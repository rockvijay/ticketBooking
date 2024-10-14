<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Details</title>
    <style>
          body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }
        .container {
            width: 100%;
            max-width:1000px;
            margin: 50px auto;
            padding: 20px;
            background-color: #ffffff;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .btn {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background-color: #ff4d4d;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .btn:hover {
            background-color: #ff3333;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Booking Details</h1>
        <table border="1">
            <tr>
                <th>Booking ID</th>
                <th>Passenger Name</th>
                <th>Train ID</th>
                <th>Booking Date</th>
                <th>Gender</th>
                <th>Age</th>
                <th>Price</th>
            </tr>
            <tr>
                <td>${booking.bookingId}</td>
                <td>${booking.passengerName}</td>
                <td>${booking.trainId}</td>
                <td>${booking.bookingDate}</td>
                <td>${booking.gender == 1 ? 'Male' : 'Female'}</td>
                <td>${booking.age}</td>
                <td>${booking.price}</td>
            </tr>
        </table>
        
        <form action="/confirmCancelBooking" method="post">
            <input type="hidden" name="bookingId" value="${booking.bookingId}" />
            <input type="submit" value="Confirm Cancel Booking">
        </form>
    </div>
</body>
</html>
