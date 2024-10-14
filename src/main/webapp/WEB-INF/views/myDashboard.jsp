<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .navbar {
            background-color: #333;
            overflow: hidden;
            padding: 14px 20px;
        }
        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }
        .right {
            float: right;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
         .dashboard-content {
             display: flex;
             justify-content: center;
             align-items: center;
             height: 50vh; /* Adjust height as needed */
             flex-direction: column;
    }
        .button-container {
             display: flex;
             flex-direction: column;
             gap: 10px; /* Spacing between buttons */
    }
     .action-btn {
        background-color: #28a745;
        color: white;
        padding: 15px 30px;
        text-align: center;
        text-decoration: none;
        border-radius: 5px;
        font-size: 16px;
        font-weight: bold;
        width: 200px;
    }
    
    .action-btn:hover {
        background-color: #218838;
    }
         .modal {
            display: none; /* Hidden by default */
            position: fixed;
            z-index: 1;
            padding-top: 100px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }
        .modal-content {
            background-color: #fff;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 30%;
            border-radius: 8px;
            text-align: center;
        }
         .close1 {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close1:hover,
        .close1:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        .about-info {
            margin-top: 15px;
            text-align: left;
        }
        .contact-info {
            margin-top: 15px;
        }
        .close2 {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close2:hover,
        .close2:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        a.mailto {
            color: #007bff;
            text-decoration: none;
        }
        a.mailto:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <!-- Navigation Bar -->
    <div class="navbar">
        <a href="/">Home</a>
        <a href="#" id="aboutUsBtn">About Us</a>
        <a href="#" id="contactUsBtn">Contact Us</a>
        <div class="right">
            Welcome <span class="username"><%= session.getAttribute("username") != null ? session.getAttribute("username") : "Guest" %>!</span>
        </div>
    </div>
    
     <!-- About Us Modal Structure -->
    <div id="aboutModal" class="modal">
        <div class="modal-content">
            <span class="close1">&times;</span>
            <h2>About Us</h2>
            <div class="about-info">
                <p>This Train Ticket Booking Application was created by <strong>Vijayaraghavan</strong>.</p>
                <ul>
                    <li>Allows users to search for trains and book tickets easily.</li>
                    <li>Features a user-friendly interface for both booking and cancellation of tickets.</li>
                    <li>Supports managing user accounts and viewing booking history.</li>
                    <li>Focuses on making rail travel more convenient and accessible to users.</li>
                   
                </ul>
            </div>
        </div>
    </div>
    
     <!-- Modal Structure -->
    <div id="contactModal" class="modal">
        <div class="modal-content">
            <span class="close2">&times;</span>
            <h2>Contact Admin</h2>
            <div class="contact-info">
                <p>Admin Email: <a href="mailto:vijayaraghavan90039@gmail.com" class="mailto">vijayaraghavan90039@gmail.com</a></p>
                <p>Admin Phone: 9003913742</p>
            </div>
        </div>
    </div>
    
   <div class="dashboard-content">
    <h1>Welcome to the Travel Port Rail Ticket Booking System</h1>
    <div class="button-container">
        <a href="/searchTrains" class="action-btn">Search & Book Train</a>
        <a href="/cancelTrain" class="action-btn">Cancel Ticket</a>
        <a href="/view-booking" class="action-btn">View Booking</a>
    </div>

 <script>
 
 
 // Get the modal elements
         var aboutModal = document.getElementById("aboutModal");

 // Get the button that opens the about modal
         var aboutBtn = document.getElementById("aboutUsBtn");

 // Get the <span> element that closes the modal
         var closeBtn = document.getElementsByClassName("close1")[0];

 // When the user clicks the button, open the modal 
        aboutBtn.onclick = function() {
             aboutModal.style.display = "block";
             }

 // When the user clicks on <span> (x), close the modal
        closeBtn.onclick = function() {
            aboutModal.style.display = "none";
             }

 // When the user clicks anywhere outside of the modal, close it
         window.onclick = function(event) {
            if (event.target == aboutModal) {
              aboutModal.style.display = "none";
              }
        }
 
        // Get the modal
        var modal = document.getElementById("contactModal");

        // Get the button that opens the modal
        var btn = document.getElementById("contactUsBtn");

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close2")[0];

        // When the user clicks the button, open the modal 
        btn.onclick = function() {
            modal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }

    </script>

</body>
</html>
