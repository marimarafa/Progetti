package org.example.magazzino.service;

import org.example.magazzino.dto.ClienteDTO;

import java.util.List;


public interface BancoService {
    public ClienteDTO insert(ClienteDTO cliente);
    public ClienteDTO update(ClienteDTO cliente);
    public ClienteDTO delete(ClienteDTO cliente);
    public List<ClienteDTO> selectAll();
    public ClienteDTO selectById(int id);
}
