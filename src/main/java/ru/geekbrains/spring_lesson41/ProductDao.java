package ru.geekbrains.spring_lesson41;

public interface ProductDao {
    Iterable<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
