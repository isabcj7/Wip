package com.example.demo.service;


import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    public Product createProduct(Product product) {
        if (!stockRepository.existsById(product.getStock().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estoque não encontrado");
        }
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    public Product updateProduct(Integer id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setNome(product.getNome());
        existingProduct.setQuantidade(product.getQuantidade());
        existingProduct.setExpiryDate(product.getExpiryDate());
        existingProduct.setPreco(product.getPreco());
        existingProduct.setStock(product.getStock());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        productRepository.deleteById(id);
    }
}