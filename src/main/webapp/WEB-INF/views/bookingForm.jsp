<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Train Ticket</title>
    <style>
        html, body {
            height: 100%; /* Full height for the body */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f4f4f4;
        }

        .container {
            background-color: #fff;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            width: 100%;
            max-height: 100vh;
            overflow-y: auto; /* Allow scrolling if content exceeds screen height */
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 15px;
        }

        form {
            margin-bottom: 10px;
        }

        label {
            font-size: 1em;
            margin-bottom: 5px;
            display: block;
        }

        input[type="text"], input[type="number"], select {
            width: calc(100% - 10px);
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 0.95em;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 1em;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 15px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #1E90FF; /* Blue color for table heading */
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        p {
            text-align: center;
            font-size: 1em;
            color: #555;
        }

        /* For better responsiveness on smaller screens */
        @media (max-width: 768px) {
            input[type="text"], input[type="number"], select {
                font-size: 0.9em;
            }

            input[type="submit"] {
                font-size: 1em;
            }

            th, td {
                font-size: 0.9em;
            }

            h2 {
                font-size: 1.5em;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Book Your Ticket</h2>

        <p><strong>Train ID:</strong> <%= request.getParameter("trainId") != null ? request.getParameter("trainId") : session.getAttribute("trainId") %></p>
        <p><strong>Train Name:</strong> <%=  request.getParameter("name") != null ? request.getParameter("name") : session.getAttribute("name") %></p>
        <p><strong>Price:</strong><%= request.getParameter("price") != null ? request.getParameter("price") : session.getAttribute("price") %></p>

        <form action="/booking/addPassenger" method="POST">
            <input type="hidden" name="trainId" value="<%= request.getParameter("trainId") != null ? request.getParameter("trainId") : session.getAttribute("trainId") %>">
            <input type="hidden" name="name" value="<%= request.getParameter("name") != null ? request.getParameter("name") : session.getAttribute("name") %>">
            <input type="hidden" name="price" value="<%= request.getParameter("price") != null ? request.getParameter("price") : session.getAttribute("price") %>">
            
            <label for="passengerName">Passenger Name:</label>
            <input type="text" id="passengerName" name="passengerName" required>
            
            <label for="age">Age:</label>
            <input type="number" id="age" name="age" required>
            
            <label for="gender">Gender:</label>
            <select id="gender" name="gender">
                <option value="1">Male</option>
                <option value="2">Female</option>
            </select>
            
            <input type="submit" value="Add Passenger">
        </form>

        <c:if test="${not empty passengers}">
            <h2>Passenger List</h2>
            <table>
                <thead>
                    <tr>
                        <th>Train ID</th>
                        <th>Train Name</th>
                        <th>Passenger Name</th>
                        <th>Age</th>
                        <th>Gender</th>
                        <th>Price</th>
                        <th>Edit</th> 
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${passengers}">
                        <tr class="row">
                            <td><c:out value="${book.trainId}" /></td>
                            <td><c:out value="${book.name}" /></td>
                            <td><c:out value="${book.passengerName}" /></td>
                            <td><c:out value="${book.age}" /></td>
                            <td><c:out value="${book.gender == 1 ? 'Male' : 'Female'}" /></td>
                            <td><c:out value="${book.price}" /></td>
                            <td>
                               <form action="/booking/editPassenger" method="GET" style="display:inline;">
                                  <input type="hidden" name="seatIndex" value="${book.seatIndex}" />
                                  <input type="submit" value="Edit">
                               </form>
                             </td>
                              <td>
                                <form action="/booking/deletePassenger" method="POST" style="display:inline;" onsubmit="return confirmDelete();">
                                   <input type="hidden" name="seatIndex" value="${book.seatIndex}" />
                                   <input type="submit" value="Delete">
                                 </form>
                               </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

 <p><strong>Price:</strong><%= request.getParameter("totalPrice") != null ? request.getParameter("totalPrice") : session.getAttribute("totalPrice") %></p>        </c:if>

        <c:if test="${empty passengers}">
            <p>No bookings to review.</p>
        </c:if>

        <!-- Review booking and submit all passenger details -->
        <form action="/booking/confirm" method="POST">
            <input type="submit" value="Confirm Booking">
        </form>
    </div>
   
    <script type="text/javascript">
    function confirmDelete() {
        return confirm("Are you sure you want to delete this passenger?");
    }
    </script>
</body>
</html>
