package co.istad.elearningapi.features.category;

import co.istad.elearningapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String categoryName);

    List<Category> findAllByParentCategoryIdIsNull();

    boolean existsByAlias(String alias);

    Category findByAlias(String alias);

}
