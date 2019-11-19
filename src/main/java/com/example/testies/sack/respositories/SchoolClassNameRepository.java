package com.example.testies.sack.respositories;

import com.example.testies.sack.entities.ClassEntity;
import com.example.testies.sack.entities.ClassNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolClassNameRepository extends JpaRepository<ClassNameEntity, Long> {
}
