package com.tecsup.lab05.service;

import com.tecsup.lab05.model.Producto;
import com.tecsup.lab05.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository repo;

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto guardar(Producto p) {
        return repo.save(p);
    }

    public Producto obtener(Long id) {
        return repo.findById(id).orElse(null);
    }
    public List<Producto> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
