# API Design

## Authentication & Users
Register new user (doctor or patient): `POST /api/auth/register`
Authenticate user: `POST /api/auth/login`


## Patients
Get patient profile: `GET /api/patients/{id}`
View booked appointments: `GET /api/patients/{id}/appointments`
Book appointment: `POST /api/patients/{id}/appointments` 
Cancel appointment: `DELETE /api/patients/{id}/appointments/{appointmentId}`
Search doctors by filter: `GET /api/doctors/search?location=X&specialty=Y&name=Z`


## Doctors
List all doctors: `GET /api/doctors` 
Get doctor profile: `GET /api/doctors/{id}` 
Set working hours:  `PUT /api/doctors/{id}/working-hours`
View doctor’s appointments: `GET /api/doctors/{id}/appointments` 
Accept/Decline appointment: `PUT /api/doctors/{id}/appointments/{appointmentId}/status`
Assign priority (Low, Medium, High): `PUT /api/doctors/{id}/appointments/{appointmentId}/priority`


## Appointments
Get appointment details: `GET /api/appointments/{id}` 
Create appointment: `POST /api/appointments` 
Cancel appointment: `DELETE /api/appointments/{id}`
