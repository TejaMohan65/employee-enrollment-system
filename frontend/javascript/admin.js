function getAllEmployees() {
    const token = localStorage.getItem("token");

    fetch("https://employee-enrollment-system-5.onrender.com/employees", {
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(res => res.json())
    .then(data => {
        let employees = data.data;

    document.getElementById("totalEmployees").innerText = employees.length;

    let activeCount = employees.filter(emp => emp.status === "ACTIVE").length;
    document.getElementById("activeEmployees").innerText = activeCount;

    let departments = new Set(employees.map(emp => emp.department));
    document.getElementById("departments").innerText = departments.size;

        let html = `
        <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Department</th>
            <th>Salary</th>
            <th>Joining Date</th>
            <th>Address</th>
            <th>Username</th>
            <th>Role</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>`;

        data.data.forEach(emp => {
            html += `
            <tr>
                <td>${emp.empId}</td>
                <td>${emp.firstName} ${emp.lastName}</td>
                <td>${emp.email}</td>
                <td>${emp.phone}</td>
                <td>${emp.department}</td>
                <td>${emp.salary}</td>
                <td>${emp.joiningDate}</td>
                <td>${emp.address}</td>
                <td>${emp.username}</td>
                <td>${emp.role}</td>
                <td>${emp.status}</td>
                <td>
                    <button onclick="toggleStatus(${emp.empId}, '${emp.status}')">
                        ${emp.status === 'ACTIVE' ? 'Deactivate' : 'Activate'}
                    </button>
                    <button onclick="editEmployee(${emp.empId})">Edit</button>
                </td>
            </tr>`;
        });

        html += "</table>";

        document.getElementById("employeeTable").innerHTML = html;
        renderEmployees(data.data);
    });
}

function enrollEmployee() {
    const token = localStorage.getItem("token");

    fetch("https://employee-enrollment-system-5.onrender.com/employees/enroll", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify({
            firstName: document.getElementById("firstName").value,
            lastName: document.getElementById("lastName").value,
            email: document.getElementById("email").value,
            phone: document.getElementById("phone").value,
            department: document.getElementById("department").value,
            salary: document.getElementById("salary").value,
            joiningDate: document.getElementById("joiningDate").value,
            address: document.getElementById("address").value,
            username: document.getElementById("username").value,
            password: document.getElementById("password").value
        })
    })
    .then(res => res.json())
    .then(data => {
        alert("Employee Enrolled");
        getAllEmployees();
    });
}
function searchEmployee() {

    const input = document.getElementById("searchInput").value.toLowerCase();

    const token = localStorage.getItem("token");

    fetch("https://employee-enrollment-system-5.onrender.com/employees", {
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(res => res.json())
    .then(data => {

        const employees = data.data;

        
        const filtered = employees.filter(emp =>
            (emp.firstName + " " + emp.lastName).toLowerCase().includes(input)
        );

        renderEmployees(filtered); 
    });
}
function toggleStatus(id, status) {
    const token = localStorage.getItem("token");

    let newStatus = status === "ACTIVE" ? "INACTIVE" : "ACTIVE";

    fetch(`https://employee-enrollment-system-5.onrender.com/employees/${id}/status?status=${newStatus}`, {
        method: "PUT",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(res => res.json())
    .then(() => {
        alert("Status Updated");
        getAllEmployees();
    });
}
function updateEmployee() {
    const id = document.getElementById("editId").value;

    const updated = {
        firstName: document.getElementById("editFirstName").value,
        lastName: document.getElementById("editLastName").value,
        email: document.getElementById("editEmail").value,
        phone: document.getElementById("editPhone").value,
        department: document.getElementById("editDepartment").value,
        salary: document.getElementById("editSalary").value
    };

    const token = localStorage.getItem("token");

    fetch(`https://employee-enrollment-system-5.onrender.com/employees/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify(updated)
    })
    .then(res => res.json())
    .then(() => {
        alert("Employee Updated Successfully");
        getAllEmployees();

        document.getElementById("editSection").style.display = "none";
    });
}
function editEmployee(id) {

    const token = localStorage.getItem("token");

    fetch(`https://employee-enrollment-system-5.onrender.com/employees/${id}`, {
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(res => res.json())
    .then(data => {

        const emp = data.data;

        document.getElementById("editId").value = emp.empId;
        document.getElementById("editFirstName").value = emp.firstName;
        document.getElementById("editLastName").value = emp.lastName;
        document.getElementById("editEmail").value = emp.email;
        document.getElementById("editPhone").value = emp.phone;
        document.getElementById("editDepartment").value = emp.department;
        document.getElementById("editSalary").value = emp.salary;

        document.getElementById("editSection").style.display = "block";
        document.getElementById("editSection").scrollIntoView({ behavior : "smooth" });

    });
}
function renderEmployees(list) {

    let html = `
    <table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Department</th>
        <th>Salary</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>`;

    list.forEach(emp => {
        html += `
        <tr>
            <td>${emp.empId}</td>
            <td>${emp.firstName} ${emp.lastName}</td>
            <td>${emp.email}</td>
            <td>${emp.phone}</td>
            <td>${emp.department}</td>
            <td>${emp.salary}</td>
            <td>${emp.status}</td>
            <td>
                <button onclick="toggleStatus(${emp.empId}, '${emp.status}')">
                    ${emp.status === 'ACTIVE' ? 'Deactivate' : 'Activate'}
                </button>
                <button onclick="editEmployee(${emp.empId})">Edit</button>
            </td>
        </tr>`;
    });

    html += "</table>";

    document.getElementById("employeeTable").innerHTML = html;
}

function logout() {
    alert("Logged out successfully");
    window.location.href = "index.html";
}
function initDashboard(){
    getAllEmployees();
}