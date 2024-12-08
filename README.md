Overview
This document provides details on how to interact with the GraphQLAPI, including queries, mutations, and authentication requirements. The API supports role-based access control using basic authentication.
Authentication
The API uses basic authentication with in-memory user details and roles. Below are the user credentials:
Admin:
Username: admin
Password: admin123
Roles: ADMIN, USER
Employee:
Username: employee
Password: emp123
Roles: USER
GraphQL Queries and Mutations
Queries
1. Retrieve All Employees (Admin Only)
This query allows only admin users to access the list of all employees along with their paginated attendance records.
graphql
query {
employees {
id
grade
attendences(first: 5) {
edges {
node {
id
present
date
}
}
pageInfo {
hasPreviousPage
hasNextPage
endCursor
}
}
}
}
2. Retrieve an Employee by ID (Admin and User)
This query can be accessed by both admin and user roles to fetch details of a specific employee, including their paginated attendance records.
query {
employee(id: 1) {
id
grade
attendences(first: 2) {
edges {
node {
id
present
date
}
}
pageInfo {
hasPreviousPage
hasNextPage
endCursor
}
}
}
}
Mutations
1. Create an Employee (Admin Only)
This mutation allows an admin to create a new employee.
mutation {
createEmployee(id: 1, name: "John Doe", age: 30, grade: "Grade 3", subjects: MATH) {
id
name
age
grade
subjects
}
}
2. Update an Employee (Admin and User)
This mutation can be accessed by both admin and user roles to update the details of an existing employee.
mutation {
updateEmployee(id: 1, name: "Jane Doe", age: 32, grade: "Grade 4", subjects: ENGLISH) {
id
name
age
grade
subjects
}
}
3. Create an Attendance Record (Admin and User)
This mutation allows both admin and user roles to create a new attendance record for a specific employee.
mutation {
createAttendence(employeeId: 1, date: "20241208", present: true) {
id
date
present
}
}
4. Update an Attendance Record (Admin Only)
This mutation allows an admin to update an attendance record for a specific employee.
mutation {
updateAttendence(employeeId: 1, date: "20241208", present: false) {
id
date
present
}
}
