package com.codepulse.svc_catalog.repository;

import com.codepulse.svc_catalog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
