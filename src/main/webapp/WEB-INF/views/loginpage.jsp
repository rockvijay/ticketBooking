<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: white;
            display: flex;
            flex-direction:column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background-color:white;  
            padding: 20px;
            border-radius: 8px;
            box-shadow: 10px 10px 5px lightblue;
            max-width: 400px;
            width: 100%;
        }
        .error {
            color: red;
            font-size: 14px;
            text-align: center;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            width: 90%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .extra-options {
            display: flex;
            justify-content: space-between;
            margin-top: 10px;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Welcome to Travelport Rail Booking</h1>
    <div class="login-container">
        <form action="/user/login" method="post">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Login">
            <div class="error">
                <!-- Display error message if authentication fails -->
                <span th:text="${error}"></span>
            </div>
            
            <div class="extra-options">
                <a href="forgotten-password">Forgotten Password?</a>
                <a href="signup">Sign Up</a>
            </div>
        </form>
    </div>

</body>
</html>
