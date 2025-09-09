package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StockRepository;
import com.example.demo.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public Order createOrder(Order order) {
        if (!stockRepository.existsById(order.getStock().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estoque não encontrado");
        }
        if (!supplierRepository.existsById(order.getSupplier().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fornecedor não encontrado");
        }
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    public Order updateOrder(Integer id, Order order) {
        Order existingOrder = getOrderById(id);
        existingOrder.setStock(order.getStock());
        existingOrder.setSupplier(order.getSupplier());
        existingOrder.setStatus(order.getStatus());
        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
        orderRepository.deleteById(id);
    }
}
