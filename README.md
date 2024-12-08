<!-- Doc 2 is in language en-US. Optimizing Doc 2 for scanning, using lists and bold where appropriate, but keeping language en-US, and adding id attributes to every HTML element: --><h2 id="yrzhh0h">Overview</h2>
<p id="6aja8vg">This document provides details on how to interact with the <strong>GraphQL API</strong>, including <strong>queries</strong>, <strong>mutations</strong>, and <strong>authentication requirements</strong>. The API supports <strong>role-based access control</strong> using basic authentication.</p>
<h3 id="c7epqf9">Authentication</h3>
<p id="0nkjlrs">The API uses basic authentication with in-memory user details and roles. Below are the user credentials:</p>
<h4 id="kznfikl">Admin:</h4>
<ul id="kznfikl">
<li id="7ltpkd"><strong>Username:</strong> admin</li>
<li id="woffvp"><strong>Password:</strong> admin123</li>
<li id="pivjpm"><strong>Roles:</strong> ADMIN, USER</li>
</ul>
<h4 id="zi9lbj">Employee:</h4>
<ul id="zi9lbj">
<li id="2ybxl4i"><strong>Username:</strong> employee</li>
<li id="nc4z5ag"><strong>Password:</strong> emp123</li>
<li id="8wwj7pb"><strong>Roles:</strong> USER</li>
</ul>
<h3 id="ynuazzg">GraphQL Queries and Mutations</h3>
<h4 id="z2xzabv">Queries</h4>
<ol id="z2xzabv">
<li id="2fg3pm"><strong>Retrieve All Employees (Admin Only)</strong>
<p id="bw8slhr4">This query allows only admin users to access the list of all employees along with their paginated attendance records.</p>
<pre id="iun7bi">graphql
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
}</pre>
</li>
<li id="vhr6dc"><strong>Retrieve an Employee by ID (Admin and User)</strong>
<p id="z6vn1zj">This query can be accessed by both admin and user roles to fetch details of a specific employee, including their paginated attendance records.</p>
<pre id="1m5enab">query {
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
}</pre>
</li>
</ol>
<h4 id="v4b9jbt">Mutations</h4>
<ol id="v4b9jbt">
<li id="r8snh9i"><strong>Create an Employee (Admin Only)</strong>
<p id="xxu9hxk">This mutation allows an admin to create a new employee.</p>
<pre id="86nwkzg">mutation {
  createEmployee(id: 1, name: "John Doe", age: 30, grade: "Grade 3", subjects: MATH) {
    id
    name
    age
    grade
    subjects
  }
}</pre>
</li>
<li id="gdtdxqd"><strong>Update an Employee (Admin and User)</strong>
<p id="rdh4dg">This mutation can be accessed by both admin and user roles to update the details of an existing employee.</p>
<pre id="67dvb47">mutation {
  updateEmployee(id: 1, name: "Jane Doe", age: 32, grade: "Grade 4", subjects: ENGLISH) {
    id
    name
    age
    grade
    subjects
  }
}</pre>
</li>
<li id="vggt1rl"><strong>Create an Attendance Record (Admin and User)</strong>
<p id="ia1xeue">This mutation allows both admin and user roles to create a new attendance record for a specific employee.</p>
<pre id="ifraomt">mutation {
  createAttendence(employeeId: 1, date: "20241208", present: true) {
    id
    date
    present
  }
}</pre>
</li>
<li id="633xy15"><strong>Update an Attendance Record (Admin Only)</strong>
<p id="jov9kb">This mutation allows an admin to update an attendance record for a specific employee.</p>
<pre id="po7j7d">mutation {
  updateAttendence(employeeId: 1, date: "20241208", present: false) {
    id
    date
    present
  }
}</pre>
</li>
</ol>
