package ru.geekbrains.spring_lesson4;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ManufacturerDao extends JpaRepository<Manufacturer, Long> {


}
