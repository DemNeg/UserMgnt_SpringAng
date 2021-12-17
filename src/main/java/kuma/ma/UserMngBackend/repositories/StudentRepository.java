package kuma.ma.UserMngBackend.repositories;

import kuma.ma.UserMngBackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student,Long> {
    public Student findByUsername(String username);
    public List<Student> findByFirstnameContains(String keyword);
    public List<Student> findByLastnameContains(String keyword);

}
