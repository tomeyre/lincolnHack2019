package com.example.testies.sack.respositories;

import com.example.testies.sack.entities.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolClassRepository extends JpaRepository<ClassEntity, Long> {

    @Query(value = "SELECT * FROM CLASS_INFO  WHERE USER_ID = ?",
            nativeQuery = true)
    List<ClassEntity> findByUserId(Integer userId);

    @Query(value = "SELECT * FROM CLASS_INFO  WHERE TEACHER_ID = ?",
            nativeQuery = true)
    List<ClassEntity> findByTeacherId(Integer teacherId);

    @Query(value = "SELECT * FROM CLASS_INFO  WHERE CLASS_ID = ?1 AND USER_ID = ?2",
            nativeQuery = true)
    ClassEntity findByUserIdAndClassId(Integer classId, Integer userId);

    @Query(value = "SELECT * FROM CLASS_INFO  WHERE CLASS_NAME_ID = ?1 AND USER_ID = ?2 AND TEST_ID = 2",
            nativeQuery = true)
    ClassEntity findByUserIdAndClassNameId(Integer classNameId, Integer userId);
}
