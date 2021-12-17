package kuma.ma.UserMngBackend.Services;

import kuma.ma.UserMngBackend.entities.Role;
import kuma.ma.UserMngBackend.entities.Student;
import kuma.ma.UserMngBackend.repositories.RoleRepository;
import kuma.ma.UserMngBackend.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;
    private RoleRepository roleRepository;
    public static String USER_ROLE = "USER";
    public static  String ADMIN_ROLE = "ADMIN";

    public StudentServiceImpl(StudentRepository studentRepository, RoleRepository roleRepository) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
    }

    public boolean studentExiste(String username){
        // On verifie l'existence d'un etudiant avec ce username
        Student studentTest = studentRepository.findByUsername(username);
        if (studentTest != null)
            return true;
        else
            return false;
    }

    @Override
    public Student addUser(Student student) {

        if (studentExiste(student.getUsername())){
            throw new RuntimeException("Cet etudiant existe déja");
        }else{
            Role role = roleRepository.findByRoleName(USER_ROLE);
            student.getRoles().add(role);
        }
        return studentRepository.save(student);
    }

    @Override
    public Student addAdmin(Student student) {
        if (studentExiste(student.getUsername())){
            throw new RuntimeException("Cet etudiant existe déja");
        }else{
            Role role = roleRepository.findByRoleName(ADMIN_ROLE);
            student.getRoles().add(role);
        }
        return studentRepository.save(student);
    }

    @Override
    public void updateUser(Long idStudent,Student student) {
        Student student1 = new Student();
        student1 = studentRepository.findById(idStudent).get();
        if (student1.equals(null)) {
            throw new RuntimeException("Etudiant inexistant !");
        } else {
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            student1.setPassword(student.getPassword());
            student1.setUsername(student.getUsername());
            studentRepository.save(student1);
        }
    }

    @Override
    public void deleteUser(Long idStudent) {
        Student student = studentRepository.findById(idStudent).get();
        if(student == null){
            throw new RuntimeException("Etudiant inexistant !");
        }
        studentRepository.delete(student);
    }

    @Override
    public List<Student> getUsers() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    @Override
    public List<Student> getStudentLike(String keyword) {
        List<Student> allStudent = new ArrayList<>();
        List<Student> list1 = studentRepository.findByFirstnameContains(keyword);
        List<Student> list2 = studentRepository.findByLastnameContains(keyword);
        list1.forEach(student -> {
            allStudent.add(student);
        });
        list2.forEach(student -> {
            allStudent.add(student);
        });
        return allStudent;
    }
}
