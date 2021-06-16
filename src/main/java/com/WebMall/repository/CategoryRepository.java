package com.WebMall.repository;

import com.WebMall.model.GoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<GoodCategory, Long> {
    GoodCategory findByName(String name);
}
