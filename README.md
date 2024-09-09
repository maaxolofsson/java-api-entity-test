# Instructions

We are going to model the API for a doctor's clinic. The Clinic has Doctors and Patients and we need an Appointment system for patients to book appointments with doctors.

The focus of the exercise is on: creating appropriate classes and the relationships between them to enable us to manage the data using a database. You can use all of the techniques we've covered so far to enable this as well as making use of more advance techniques such as DTOs as and when required.

We have provided some basic scaffolding for you to get started including a working `build.gradle` file and a partially completed `application.yml` file. You will need to add your database credentials to this to get it working (we have already added the file to the .gitignore file so you don't have to.)

## Core Criteria

### Part 1: Patient model

- Create a `Patient` model to which maps to a suitable table name, primary key and column names. Make sure to use good naming conventions for postgres.
- Add a suitable `PatientRepository` class (actually an interface)
- Add a suitable `PatientController` class with the usual endpoints, you can also add whatever other classes you need to, in order to make your application work as you wish.
- Use BeeKeeper Studio or a similar other tool to add some dummy records which you can use alongside the endpoints to check everything is working correctly.

### Part 2: Doctor & Appointment models

- Add a Doctor model, with the following properties: id, name
- Add an Appointments model, with the following properties: patientId, doctorId, appointmentDate
- Update the Doctor and Patient models to include a list of appointments; define all the foreign keys and relevant model properties
- Can you make it so that the primary key for the Appointments model is a composite key of patientId and doctorId? Should you? What are the implications of doing this?
- Add minimal repositories and controllers to allow you to run the code and have it build the tables in the database.
- Create some data and then check that the links etc are correctly implemented.

### Part 3: Doctor & Appointment endpoints
- Implement get all doctors, get doctor by id and create doctor endpoints in the controller; make sure to use DTOs as required for returning the results (you can also create update and delete doctor endpoints if you need to - but you will need to think about what happens to an appointment if a doctor is deleted? Should the appointment remain? What about historical appointments which have already occurred? Should you be able to delete a doctor or should something else happen?)
- Implement get all appointments, get appointment by id, get appointments by doctor id, get appointments by patient id and create new appointment endpoints in the controller. What about modifying an appointment or deleting one? What do you need to think about in terms of managing these?

- Update all models and tables so that the following data is returned:
  - a patient should include their appointments and each appointment include the doctor's name / id
  - a doctor should include their appointments and each appointment include the patient's name / id
  - an appointment should include the patient's name / id and the doctor's name / id

You can either use DTOs to manage what data is being passed around/returned, or you can use JSONIgnore/JSONInclude as necessary, both solutions are valid ways to achieve the same outcome.

## Extensions

- Create a Medicines model and a Prescription model;
  - Add a many-to-many relationship (or create suitable linking tables) between the Medicines and Prescription models; Each medicine on the prescription should have a quantity, a notes section for instructions on how to take it
  - Each prescription should be associated to an appointment (and hence linked to a Doctor + Patient)
  - Create suitable classes to allow these to be created
  - Create an endpoint controller to get prescriptions, to create prescriptions and attach to an appointment, etc
  
- Extend appointments to include the type of appointment: eg if it was in person or online.

## Super Extension

- Add tests to the project, both for the basic POJOs (Plain Old Java Objects) and Spring's code. You will need to research how to create and administer tests for a Spring Boot Application. How close to 100% test coverage can you get?

