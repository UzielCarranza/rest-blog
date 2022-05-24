package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {

    @Query("FROM Post a WHERE a.title like %:term%")
    List<Post> searchByTiTleLike(@Param("term") String term);
}
