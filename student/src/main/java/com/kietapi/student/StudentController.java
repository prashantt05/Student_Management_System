package com.kietapi.student;
import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController                // Marks this class as a REST controller
@RequestMapping("/student")    // Base URL for all APIs in this controller
public class StudentController {

    private List<Student> stdList =  new ArrayList<>();   // In-memory list to store students

    @GetMapping               // GET /student → Fetch all students
    public List<Student> getAll() {
        return stdList;      // Returns the complete student list
    }

    @PostMapping             // POST /student → Add a new student
    public String addStudent(@RequestBody Student std) {
        stdList.add(std);    // Adds incoming student object to list
        return "Student added successfully";
    }

    @PutMapping("/{roll}")   // PUT /student/{roll} → Update student details
    public String updateStudent(@PathVariable int roll, @RequestBody Student updatedStudent) {

        // Iterate through list to find matching roll number
        for(Student s: stdList){
            if(s.getRoll() == roll){
                s.setName(updatedStudent.getName());     // Update name
                s.setBranch(updatedStudent.getBranch()); // Update branch
                return "Student updated successfully";
            }
        }

        return "Student data not updated";  // If roll not found
    }

    @DeleteMapping("/{roll}")  // DELETE /student/{roll} → Delete a student
    public String deleteStudent(@PathVariable int roll) {

        // Using iterator to avoid ConcurrentModificationException
        Iterator<Student> it = stdList.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getRoll() == roll) {
                it.remove();               // Safe removal from list
                return "Deleted Successfully";
            }
        }

        return "Data not deleted";  // If no matching student found
    }

}
