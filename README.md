🏥 Hospital Integration System
A comprehensive Hospital Integration System designed to enable hospitals across a city to efficiently share resources, track real-time bed availability, manage OPD queues, and schedule patient appointments. Built with Spring Boot and MySQL, this system enhances operational efficiency, streamlines patient management, and supports role-based access control for secure interactions.

🚀 Key Features
🛏️ Real-Time Bed Vacancy Updates: View available beds across various hospitals in real-time.

📅 Patient Appointment Scheduling: Schedule, modify, and track patient appointments with ease.

🏥 OPD Queue Management: Efficiently manage and track patients in the OPD queue for timely care.

🔐 Role-Based Access Control (RBAC): Different roles (Admin, Doctor, Nurse, Receptionist) with restricted access to specific system functionalities.

🌍 City-Wide Hospital Integration: Connects multiple hospitals, enabling them to share resources, including available beds, doctors, and equipment.

🛠️ Tech Stack
Backend: Spring Boot

Database: MySQL

Authentication & Authorization: Spring Security, Role-Based Access Control (RBAC)

Others: JPA, Lombok, REST APIs

🧑‍💻 Getting Started
1. Clone the repository
bash
Copy
Edit
git clone https://github.com/your-username/hospital-integration-system.git
cd hospital-integration-system
2. Configure MySQL
Update your application.properties with your local or remote MySQL credentials.

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/hospital_system
spring.datasource.username=your-username
spring.datasource.password=your-password
3. Run the application
bash
Copy
Edit
./mvnw spring-boot:run
The app will be available at: http://localhost:8080

🏥 Features in Detail
1. Real-Time Bed Vacancy Updates
The system keeps track of bed availability in real-time, ensuring hospitals can optimize their space and provide timely care. Hospitals across the city can update their bed status, and others can see the changes instantly.

2. OPD Queue Management
Managing OPD queues is streamlined, allowing receptionists and doctors to track patient flow, minimize waiting times, and ensure smoother hospital operations.

3. Appointment Scheduling
Patients can schedule, cancel, or reschedule their appointments, and healthcare providers can efficiently manage their schedules.

4. Role-Based Access Control (RBAC)
Roles such as Admin, Doctor, Nurse, and Receptionist are defined with specific access privileges to ensure that only authorized personnel can access sensitive data and perform critical operations.
