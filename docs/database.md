# Database (MongoDB)

## User 

User {
  _id: ObjectId
  email: string
  username: string
  password: hashed string
  role: enum[ADMIN, PATIENT, DOCTOR]
  doctor: ref → Doctor
  patient: ref → Patient
}


## Patient 

Patient {
  _id: ObjectId
  age: int
  sex: enum[MALE, FEMALE]
}

## Doctor

Doctor {
  _id: ObjectId
  specialty: enum
  location: string
  start_time: LocalDateTime
  end_time: LocalDateTime
}

## Appointment

Appointment {
  _id: ObjectId
  date: Date
  state: enum[PENDING, ACCEPTED, CANCELLED]
  priority: enum[LOW, MEDIUM, HIGH]
  case: string
  patient: ref → Patient
  doctor: ref → Doctor
}
