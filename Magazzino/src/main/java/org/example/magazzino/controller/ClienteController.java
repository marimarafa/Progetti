package org.example.magazzino.controller;

import org.example.magazzino.dto.ClienteDTO;
import org.example.magazzino.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private BancoService service;


    @PostMapping(consumes = "application/json")
    public ClienteDTO insert(@RequestBody ClienteDTO dto) {
        return service.insert(dto);
    }

    @GetMapping
    public List<ClienteDTO> getAll() {
        return service.selectAll();
    }


    @GetMapping("/{id}")
    public ClienteDTO getById(@PathVariable int id) {
        return service.selectById(id);
    }


    @PutMapping("/{id}")
    public ClienteDTO update(@PathVariable int id, @RequestBody ClienteDTO cliente) {
        cliente.setId(id);
        return service.update(cliente);
    }

    @DeleteMapping
    public ClienteDTO delete(@RequestBody ClienteDTO dto) {
        return service.delete(dto);
    }
}
