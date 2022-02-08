package ru.geekbrains.spring_lesson41;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class AppService {

    private final JpaProductDao jpaProductDao;


    public Product saveProduct(Product product) {
        return jpaProductDao.save(product);
    }


    public Product getProductById(Long id) {
        try {
            return jpaProductDao.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return (List<Product>) jpaProductDao.findAll();
    }

    public Product editProduct(Product product) {
       return jpaProductDao.save(product);
    }

    public void deleteById(Long id) {
        jpaProductDao.deleteById(id);
    }

}
