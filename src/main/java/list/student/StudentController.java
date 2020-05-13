package list.student;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import list.student.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        System.out.println("LIST LIST LIST LIST LIST LIST LIST LIST LIST LIST LIST LIST LIST LIST LIST LIST ");
        return studentRepo.findAll();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/studentS")
    public Student createStudent(@Valid @RequestBody Student student) {
        System.out.println("SAVE SAVE SAVE SAVE SAVE SAVE SAVE SAVE SAVE SAVE SAVE SAVE ");
        return studentRepo.save(student);

    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId,
                                                   @Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id : " + studentId));

        student.setEmailId(studentDetails.getEmailId());
        student.setLastName(studentDetails.getLastName());
        student.setFirstName(studentDetails.getFirstName());
        final Student updatedStudent = studentRepo.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/students/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        studentRepo.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

