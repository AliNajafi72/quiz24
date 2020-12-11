const studentSignup = document.getElementById("student-signup-container")
const teacherSignup = document.getElementById("teacher-signup-container")
studentSignup.addEventListener("click", () => {
    window.open("signup/student","_self")
})
teacherSignup.addEventListener("click", () => {
    window.open("signup/teacher","_self")
})
