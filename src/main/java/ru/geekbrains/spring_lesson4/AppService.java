package ru.geekbrains.spring_lesson4;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.data.domain.Sort;


@Service
@RequiredArgsConstructor
@Transactional
@Repository
public class AppService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ManufacturerDao manufacturerDao;


    @Transactional
    public Product saveProduct(Product product) {
        if(product.getId() != null) {
            Optional<Product> productFromDbOptional = productDao.findById(product.getId());
            if(productFromDbOptional.isPresent()) {
                Product productFromDb = productFromDbOptional.get();
                productFromDb.setTitle(product.getTitle());
                productFromDb.setCost(product.getCost());
                productFromDb.setDate(product.getDate());
                checkManufacturer(productFromDb, product.getManufacturer());
                return productDao.save(productFromDb);
            }
        }
        checkManufacturer(product, product.getManufacturer());
        return productDao.save(product);
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productDao.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }


    @Transactional(readOnly = true)
    public List<Product> getProductsSortedAsc() {
        return productDao.findAll(Sort.by(Sort.Direction.ASC, "cost"));
    }


    @Transactional(readOnly = true)
    public List<Product> getProductsSortedDesc() {
        return productDao.findAll(Sort.by(Sort.Direction.DESC, "cost"));
    }


    @Transactional
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }


        private void checkManufacturer(Product product, Manufacturer manufacturer) {
            ArrayList<Manufacturer> manufacturersFromDb = (ArrayList<Manufacturer>) manufacturerDao.findAll();
            for(Manufacturer manufacturerFromDb : manufacturersFromDb) {
                if(manufacturer.getName().equals(manufacturerFromDb.getName())) {
                    product.setManufacturer(manufacturerFromDb);
                return;
            }
            }
            product.setManufacturer(manufacturer);
        }

}
