package kuma.ma.UserMngBackend.Services;

import kuma.ma.UserMngBackend.entities.Student;

import java.util.List;

public interface StudentService {
    public Student addUser(Student student);
    public Student addAdmin(Student student);
    public void updateUser(Long idStudent,Student student);
    public void deleteUser(Long idStudent);
    public List<Student> getUsers();
    public Student getStudentByUsername(String username);
    public List<Student> getStudentLike(String keyword);
}
