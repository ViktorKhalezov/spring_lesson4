package ru.geekbrains.spring_lesson4;


import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductDao extends JpaRepository<Product, Long> {

}
