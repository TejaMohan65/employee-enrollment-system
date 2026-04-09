const BASE_URL = "https://employee-enrollment-system-5.onrender.com";

function login() {
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  fetch(BASE_URL + "/auth/login", {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify({ username, password })
  })
  .then(res => res.json())
  .then(data => {
    if (data.data) {
      localStorage.setItem("token", data.data);

      if (username === "admin") {
        window.location = "admin.html";
      } else {
        window.location = "employee.html";
      }
    } else {
      document.getElementById("error").innerText = data.message;
    }
  });
}