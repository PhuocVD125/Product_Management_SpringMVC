package phuocvu.org.crud_web_demo.repositories;

import org.springframework.data.repository.CrudRepository;
import phuocvu.org.crud_web_demo.models.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
    Iterable<Product> findByCategoryID(String CategoryID);
}
