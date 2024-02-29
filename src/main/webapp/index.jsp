<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TodoLists</title>
    <link rel="stylesheet" href="css/styles.css">
</head>

<body>
	<h1>TodoLists</h1>
    <img src="images/todoimg.jpg" alt="Image"/>
    
    <form action="signup" method="post" onsubmit="return validateForm()">
      <h2>Sign Up</h2>
      <label for="username">Username:</label>
      <input type="text" id="username" name="username" required>

      <label for="email">Email:</label>
      <input type="email" id="email" name="email" required>

      <label for="password">Password:</label>
      <input type="password" id="password" name="password" minlength="8" required>

      <label for="confirm_password">Confirm Password:</label>
      <input type="password" id="confirm_password" name="confirm_password" minlength="8" required>

      <button type="submit">Sign Up</button>
      <a href="login.jsp">Already a User?</a>
   </form>


    <script>
        function validateForm() {
            var password = document.getElementById("password").value;
            var confirm_password = document.getElementById("confirm_password").value;

            // Check if passwords match
            if (password !== confirm_password) {
                alert("Passwords do not match");
                return false;
            }

            // Additional validation can be added here if needed

            return true; // Continue with form submission
        }
    </script>
</body>

</html>


