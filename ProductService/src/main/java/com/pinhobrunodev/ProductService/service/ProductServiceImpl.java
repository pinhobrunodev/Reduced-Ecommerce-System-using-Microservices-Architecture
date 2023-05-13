package com.pinhobrunodev.ProductService.service;


import com.pinhobrunodev.ProductService.entity.Product;
import com.pinhobrunodev.ProductService.exceptions.ProductServiceCustomException;
import com.pinhobrunodev.ProductService.model.ProductByIdResponse;
import com.pinhobrunodev.ProductService.model.ProductRequest;
import com.pinhobrunodev.ProductService.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public Long addProduct(ProductRequest productRequest) {
        log.info("Adding Product...");
        return productRepository.save(Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build()).getProductId();
    }

    @Override
    public ProductByIdResponse getProductById(Long id) {
        log.info("Get the product for productId: {}", id);
        return productRepository.findById(id).map(ProductByIdResponse::new).orElseThrow(() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));
    }
    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity, productId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductServiceCustomException(
                "Product with given Id not found",
                "PRODUCT_NOT_FOUND"
        ));
        if (product.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Product Quantity updated Successfully");
    }
}
