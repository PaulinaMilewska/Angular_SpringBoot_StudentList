package list.student;

import org.springframework.data.jpa.repository.JpaRepository;

public class StudentRepo {

    public interface StudentRepository extends JpaRepository<Student, Long> {
    }
}


