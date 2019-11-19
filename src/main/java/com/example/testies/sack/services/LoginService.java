package com.example.testies.sack.services;

import com.example.testies.sack.entities.ClassEntity;
import com.example.testies.sack.entities.ClassNameEntity;
import com.example.testies.sack.entities.UserEntity;
import com.example.testies.sack.pojos.*;
import com.example.testies.sack.respositories.SchoolClassNameRepository;
import com.example.testies.sack.respositories.SchoolClassRepository;
import com.example.testies.sack.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SchoolClassRepository schoolClassRepository;

    @Autowired
    SchoolClassNameRepository schoolClassNameRepository;

    private List<UserEntity> users;
    private List<ClassNameEntity> classNameEntities;
    private HashMap<Integer, String> classNameMap = new HashMap<Integer, String>();
    private HashMap<Integer, String> teacherNameMap = new HashMap<Integer, String>();
    private HashMap<Integer, String> studentNameMap = new HashMap<Integer, String>();

    public LoginResponse userLogin(Login login) {
        UserEntity userEntity = userRepository.findUserByUsername(login.getUsername());
        if (userEntity == null) {
            return new LoginResponse();
        }
        return buildAccount(userEntity);
    }

    public LoginResponse imageFullResponse(Integer userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            return new LoginResponse();
        }
        return buildAccount(userEntity);
    }

    public LoginResponse buildAccount(UserEntity userEntity){
        List<ClassEntity> classEntities;
        if(userEntity.getType().equalsIgnoreCase("student")) {
            classEntities = schoolClassRepository.findByUserId(userEntity.getUserId());
        }else{
            classEntities = schoolClassRepository.findByTeacherId(userEntity.getUserId());
        }
        if(classEntities == null) {
            return new LoginResponse();
        }
        classNameEntities = schoolClassNameRepository.findAll();
        classNameEntities.stream().forEach(cn -> classNameMap.put(cn.getClassNameId(), cn.getClassName()));
        users = userRepository.findAll();
        users.stream().filter(teacher -> teacher.getType().equalsIgnoreCase("Teacher")).forEach(t -> teacherNameMap.put(t.getUserId(), t.getName()));
        users.stream().filter(student -> student.getType().equalsIgnoreCase("Student")).forEach(t -> studentNameMap.put(t.getUserId(), t.getName()));
        return buildLoginResponse(userEntity,classEntities);
    }

    private LoginResponse buildLoginResponse(UserEntity userEntity, List<ClassEntity> classEntities){
        LoginResponse loginResponse= new LoginResponse();
        loginResponse.setUserId(userEntity.getUserId());
        loginResponse.setType(userEntity.getType());
        loginResponse.setYear(userEntity.getSchoolYear());
        loginResponse.setUsername(userEntity.getName());
        if(userEntity.getType().equalsIgnoreCase("Teacher")){
            loginResponse.setTeacherClasses(buildTeacherClasses(classEntities));
        }else {
            loginResponse.setStudentSubjects(buildStudentSubject(classEntities));
        }
        return loginResponse;
    }

    private List<StudentSubjects> buildStudentSubject(List<ClassEntity> classEntities){
        List<StudentSubjects> studentSubjectsList = new ArrayList<>();
        for(ClassEntity classEntity : classEntities){
            if(studentSubjectsList.isEmpty() || !containsNameInStudentList(studentSubjectsList, classNameMap.get(classEntity.getClassNameId()))) {
                StudentSubjects studentSubjects = new StudentSubjects();
                studentSubjects.setSubjectName(classNameMap.get(classEntity.getClassNameId()));
                studentSubjects.setResults(buildResults(classEntities, classEntity.getClassNameId(), classEntity.getUserId()));
                studentSubjects.setBand(classEntity.getBand());
                studentSubjects.setClassId(classEntity.getClassId());
                studentSubjects.setTeacherName(teacherNameMap.get(classEntity.getTeacherId()));
                studentSubjects.setOverallResult(getOverallScore(studentSubjects.getResults()));
                studentSubjectsList.add(studentSubjects);
            }
        }
        return studentSubjectsList;
    }

    private Integer getOverallScore(List<ImageResult> results){
        Integer overallResult = 0;
        int count = 0;
        for(ImageResult result : results){
            count++;
            overallResult += result.getResult();
        }

        if (count == 0) {
            return 0;
        }
        return overallResult / count;
    }

    private List<ImageResult> buildResults(List<ClassEntity> classEntities, Integer classNameId, Integer userId){
        List<ImageResult> results = new ArrayList<>();
        for(ClassEntity entity : classEntities){
            if(entity.getClassNameId() == classNameId && entity.getUserId() == userId && entity.getResult() != null){
                ImageResult imageResult = new ImageResult();
                imageResult.setImageResult(entity.getImageId());
                imageResult.setResult(entity.getResult());
                imageResult.setTestTime(entity.getTestTime());
                results.add(imageResult);
            }
        }
        return results;
    }

    private List<TeacherClasses> buildTeacherClasses(List<ClassEntity> classEntities){
        List<TeacherClasses> teacherClassesList = new ArrayList<>();
        for(ClassEntity classEntity : classEntities){
            if(teacherClassesList.isEmpty() || !containsName(teacherClassesList, classNameMap.get(classEntity.getClassNameId()))) {
                TeacherClasses teacherClasses = new TeacherClasses();
                teacherClasses.setClassId(classEntity.getClassId());
                teacherClasses.setSubjectName(classNameMap.get(classEntity.getClassNameId()));
                teacherClasses.setBand(classEntity.getBand());
                teacherClasses.setClassResult(0);
                teacherClasses.setStudents(buildStudents(classEntities, classEntity.getClassNameId(), classEntity.getTeacherId()));
                int total = teacherClasses.getStudents()
                        .stream()
                        .filter(s -> getAverageScore(s) != 0)
                        .mapToInt(this::getAverageScore)
                        .sum();
                int count = (int) teacherClasses.getStudents()
                        .stream()
                        .filter(s -> getAverageScore(s) != 0)
                        .count();

                if (count != 0) {
                    teacherClasses.setClassResult(total / count);
                }
                teacherClassesList.add(teacherClasses);
            }
        }
        return teacherClassesList;
    }

    private int getAverageScore(Student student) {
        int total = student.getResults()
                .stream()
                .filter(i -> i.getResult() != null)
                .mapToInt(ImageResult::getResult)
                .sum();

        int count = (int) student.getResults()
                .stream()
                .filter(i -> i.getResult() != null)
                .count();

        if (count == 0) {
            return 0;
        }

        return total / count;
    }

    public boolean containsName(final List<TeacherClasses> list, final String name){
        return list.stream().anyMatch(o -> o.getSubjectName().equals(name));
    }

    public boolean containsNameInStudentList(final List<StudentSubjects> list, final String name){
        return list.stream().anyMatch(o -> o.getSubjectName().equals(name));
    }

    private List<Student> buildStudents(List<ClassEntity> classEntities, Integer classId, Integer teacherId){
        List<Student> students = new ArrayList<>();
        for(ClassEntity classEntity : classEntities){
            if(classEntity.getUserId() != teacherId && teacherId == classEntity.getTeacherId() && classNameMap.get(classEntity.getClassNameId()).equalsIgnoreCase(classNameMap.get(classId))) {
                Student student = new Student();
                student.setUserId(classEntity.getUserId());
                student.setResults(buildResults(classEntities, classEntity.getClassNameId(), classEntity.getUserId()));
                student.setUsername(studentNameMap.get(classEntity.getUserId()));
                students.add(student);
            }
        }
        return students;
    }
}
