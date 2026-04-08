const BASE_URL = "http://localhost:8080";


function getProfile() {

    const token = localStorage.getItem("token");

    fetch(BASE_URL + "/employees/me", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(res => res.json())
    .then(data => {

        const emp = data.data;

        let html = `
            <h3>My Profile</h3>

            <table border="1" cellpadding="10">
                <tr><th>ID</th><td>${emp.empId}</td></tr>
                <tr><th>First Name</th><td>${emp.firstName}</td></tr>
                <tr><th>Last Name</th><td>${emp.lastName}</td></tr>
                <tr><th>Email</th><td>${emp.email}</td></tr>
                <tr><th>Phone</th><td>${emp.phone}</td></tr>
                <tr><th>Department</th><td>${emp.department}</td></tr>
                <tr><th>Salary</th><td>${emp.salary}</td></tr>
                <tr><th>Joining Date</th><td>${emp.joiningDate}</td></tr>
                <tr><th>Address</th><td>${emp.address}</td></tr>
                <tr><th>Username</th><td>${emp.username}</td></tr>
                <tr><th>Role</th><td>${emp.role}</td></tr>
                <tr><th>Status</th><td>${emp.status}</td></tr>
            </table>
        `;

        document.getElementById("profile").innerHTML = html;
    })
    .catch(err => {
        console.error(err);
        alert("Error loading profile");
    });
}