package com.example.demo.service;

import com.example.demo.model.Supplier;
import com.example.demo.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Integer id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado"));
    }

    public Supplier updateSupplier(Integer id, Supplier supplier) {
        Supplier existingSupplier = getSupplierById(id);
        existingSupplier.setNome(supplier.getNome());
        existingSupplier.setApiEndpoint(supplier.getApiEndpoint());
        return supplierRepository.save(existingSupplier);
    }

    public void deleteSupplier(Integer id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado");
        }
        supplierRepository.deleteById(id);
    }
}
