package list.student;

import org.springframework.data.jpa.repository.JpaRepository;



    public interface StudentRepo extends JpaRepository<Student, Long> {
    }



