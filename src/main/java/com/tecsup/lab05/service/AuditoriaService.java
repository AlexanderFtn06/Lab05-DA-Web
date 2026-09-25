package com.tecsup.lab05.service;

import com.tecsup.lab05.model.AuditoriaLog;
import com.tecsup.lab05.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository repo;

    public void registrar(String accion, String metodo, String detalle,String usuario) {
        AuditoriaLog log = new AuditoriaLog(accion, metodo, detalle,usuario);
        repo.save(log);
    }
}