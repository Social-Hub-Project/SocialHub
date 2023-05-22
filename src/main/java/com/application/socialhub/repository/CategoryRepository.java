package com.application.socialhub.repository;

import com.application.socialhub.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long>{
}
