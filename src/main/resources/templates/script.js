// Toggle between Login & Signup forms
function toggleForms() {
    const loginForm = document.getElementById("login-form");
    const signupForm = document.getElementById("signup-form");

    if (loginForm.style.display === "none") {
        loginForm.style.display = "block";
        signupForm.style.display = "none";
    } else {
        loginForm.style.display = "none";
        signupForm.style.display = "block";
    }
}

// Handle Signup
document.getElementById("signup-form").addEventListener("submit", async function (event) {
    event.preventDefault();

    const hospitalData = {
        hospitalName: document.getElementById("hospital-name").value,
        hospitalId: document.getElementById("hospital-id").value,
        emailId: document.getElementById("signup-email").value,
        password: document.getElementById("signup-password").value,
        phoneNo: document.getElementById("signup-phone").value
    };

    try {
        const response = await fetch("http://localhost:8080/hospitals/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(hospitalData)
        });

        if (response.ok) {
            alert("Signup successful!");
            toggleForms();
        } else {
            alert("Signup failed: " + (await response.text()));
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Error connecting to server.");
    }
});

// Handle Login (Dummy Implementation - Modify for Actual Authentication)
document.getElementById("login-form").addEventListener("submit", async function (event) {
    event.preventDefault();

    const email = document.getElementById("login-email").value;
    const password = document.getElementById("login-password").value;

    try {
        const response = await fetch("http://localhost:8080/hospitals", {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        });

        if (!response.ok) {
            alert("Error fetching data.");
            return;
        }

        const hospitals = await response.json();
        const foundHospital = hospitals.find(h => h.emailId === email && h.password === password);

        if (foundHospital) {
            alert("Login successful!");
            window.location.href = "dashboard.html"; // Redirect to a new page after login
        } else {
            alert("Invalid email or password.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Error connecting to server.");
    }
});
