package com.UCDb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UCDb.models.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer>{

	Disciplina findById(long id);

	List<Disciplina> findAll();

}
