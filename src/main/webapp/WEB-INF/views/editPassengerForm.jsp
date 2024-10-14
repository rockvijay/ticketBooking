<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Passenger</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #ff0000, #0000ff); /* Red to Blue Gradient */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            color: white;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
            color: #333;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 5px;
            font-size: 1.1em;
        }

        input[type="text"], input[type="number"], select {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1em;
            width: calc(100% - 20px);
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1.1em;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
        }

        .back-link a {
            text-decoration: none;
            color: #1E90FF;
            font-size: 1em;
        }

        .back-link a:hover {
            text-decoration: underline;
        }

        @media (max-width: 768px) {
            input[type="text"], input[type="number"], select {
                font-size: 0.9em;
            }

            input[type="submit"] {
                font-size: 1em;
            }

            h2 {
                font-size: 1.5em;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Passenger Details</h2>
        <form action="/booking/updatePassenger" method="POST">
        <input type="hidden" name="seatIndex" value="${passenger.seatIndex}">
        <label for="passengerName">Passenger Name:</label>
        <input type="text" id="passengerName" name="passengerName" value="${passenger.passengerName}" required>

        <label for="age">Age:</label>
        <input type="number" id="age" name="age" value="${passenger.age}" required>

        <label for="gender">Gender:</label>
        <select id="gender" name="gender">
            <option value="1" <c:if test="${passenger.gender == 1}">selected</c:if>>Male</option>
            <option value="2" <c:if test="${passenger.gender == 2}">selected</c:if>>Female</option>
        </select>

        <input type="submit" value="Update Passenger">
    </form><br>
        <form action="/booking/create" method="GET">
           <input type="hidden" name="trainId" value="${trainId}">
           <input type="hidden" name="name" value="${name}">
           <input type="hidden" name="price" value="${price}">
           <input type="submit" value="Back to Review">
        </form>
    </div>
</body>
</html>
