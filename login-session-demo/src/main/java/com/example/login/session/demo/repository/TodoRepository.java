package com.example.login.session.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.login.session.demo.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, String> {

	@Query(value = "select * from TODO_TABLE t where t.USER_ID = ?1 order by t.TARGET_DATE", nativeQuery = true)
	public Optional<List<Todo>> getallRecords(String userId);
	
	@Modifying
	@Query(value = "update TODO_TABLE t set t.IS_DONE = ?3 where t.USER_ID = ?1 AND t.TODO_ID = ?2", nativeQuery = true)
	public void updateStatus(String userId, int todoId, boolean status);
}
