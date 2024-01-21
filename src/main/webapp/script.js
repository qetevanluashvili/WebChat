// Request-ის გაგზავნა
// async function register() {
//     var url = webserverName + servletUrl + '?param1=param1Value' + '&param2=param2Value';
//     var method = "POST" ან "GET"
//     var response = await fetch(url, { method: "POST" });
//
//     // Response body-ს მიღება
//     var body = await response.text();
//
//     // HTML ელემენტის დამატება/შეცვლა
//     var div = document.getElementById("some-div-id");
//     div.innerHTML = 'some html code here';
// }

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
body {
<script>
    const baseUrl = 'http://localhost:8080';

    async function registerUser() {
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        try {
            const response = await fetch(`${baseUrl}/user`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password }),
            });

            if (response.ok) {
                alert('You have successfully registered');
                clearFormFields('userRegistrationForm');
            } else {
                const errorText = await response.text();
                alert(`User registration failed: ${errorText}`);
            }
        } catch (error) {
            console.error('Error during user registration:', error);
            alert('An unexpected error occurred during user registration');
        }
    }

    async function sendMessage() {
        const username = document.getElementById('messageUsername').value;
        const message = document.getElementById('messageText').value;

        if (message.includes('\n')) {
            alert('The message should not contain a new line character ("\n")');
            return;
        }

        try {
            const response = await fetch(`${baseUrl}/message`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, message }),
            });

            if (response.ok) {
                alert('Message sent successfully');
                clearFormFields('messageSendingForm');
            } else {
                const errorText = await response.text();
                alert(`Message sending failed: ${errorText}`);
            }
        } catch (error) {
            console.error('Error during message sending:', error);
            alert('An unexpected error occurred during message sending');
        }
    }

    async function readMessages() {
        const username = document.getElementById('readUsername').value;
        const password = document.getElementById('readPassword').value;

        try {
            const response = await fetch(`${baseUrl}/user?username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`);

            if (response.ok) {
                const messages = await response.text();
                document.getElementById('messages').innerText = messages;
                clearFormFields('readMessagesForm');
            } else {
                const errorText = await response.text();
                alert(`Reading messages failed: ${errorText}`);
            }
        } catch (error) {
            console.error('Error during reading messages:', error);
            alert('An unexpected error occurred during reading messages');
        }
    }

    function clearFormFields(formId) {
        const form = document.getElementById(formId);
        form.reset();
    }
</script>
</body>








