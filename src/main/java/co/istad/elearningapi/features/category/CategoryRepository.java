package co.istad.elearningapi.features.category;

import co.istad.elearningapi.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String categoryName);

    List<Category> findAllByParentCategoryIdIsNull();

    boolean existsByAlias(String alias);

    Optional<Category> findByAlias(String alias);

    Page<Category> findAllByIsDeletedFalse(PageRequest pageRequest);

    @Modifying
    @Query("UPDATE Category as ca SET ca.isDeleted = true WHERE ca.alias = ?1")
    void disableCategoryByAlias(String alias);

}
