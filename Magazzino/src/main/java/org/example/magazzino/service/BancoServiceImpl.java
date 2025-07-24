package org.example.magazzino.service;

import org.example.magazzino.dao.ClienteDAO;
import org.example.magazzino.dto.ClienteDTO;
import org.example.magazzino.entity.Cliente;
import org.example.magazzino.utility.Conversioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class BancoServiceImpl implements BancoService {
    @Autowired
    private ClienteDAO dao;

    public BancoServiceImpl(ClienteDAO dao) {
        this.dao = dao;
    }

    @Override
    public ClienteDTO insert(ClienteDTO clienteDTO) {
        Cliente clienteEntity = Conversioni.daClienteDTOACliente(clienteDTO);
        Cliente salvato = dao.insert(clienteEntity);
        return Conversioni.daClienteAClienteDTO(salvato);
    }

    @Override
    public ClienteDTO update(ClienteDTO clienteDTO) {
        Cliente esistente = dao.selectById(clienteDTO.getId());

        if (clienteDTO.getNome() != null) esistente.setNome(clienteDTO.getNome());
        if (clienteDTO.getCognome() != null) esistente.setCognome(clienteDTO.getCognome());
        if (clienteDTO.getIndirizzo() != null) esistente.setIndirizzo(clienteDTO.getIndirizzo());
        if (clienteDTO.getEmail() != null) esistente.setEmail(clienteDTO.getEmail());
        if (clienteDTO.getCodice_fiscale() != null) esistente.setCodice_fiscale(clienteDTO.getCodice_fiscale());
        if (clienteDTO.getTelefono() != null) esistente.setTelefono(clienteDTO.getTelefono());
        if (clienteDTO.getNumero_carta() != null) esistente.setNumero_carta(clienteDTO.getNumero_carta());
        if (clienteDTO.getPartita_iva() != null) esistente.setPartita_iva(clienteDTO.getPartita_iva());

        Cliente aggiornato = dao.update(esistente);
        return Conversioni.daClienteAClienteDTO(aggiornato);
    }

    @Override
    public ClienteDTO delete(ClienteDTO clienteDTO) {
        Cliente entity = Conversioni.daClienteDTOACliente(clienteDTO);
        Cliente eliminato = dao.deleteById(entity.getId());
        return Conversioni.daClienteAClienteDTO(eliminato);
    }

    @Override
    public List<ClienteDTO> selectAll() {
        List<Cliente> clienti = dao.selectAll();
        List<ClienteDTO> dtos = new ArrayList<>();
        for (Cliente c : clienti) {
            dtos.add(Conversioni.daClienteAClienteDTO(c));
        }
        return dtos;
    }

    @Override
    public ClienteDTO selectById(int id) {
        Cliente c = dao.selectById(id);
        return Conversioni.daClienteAClienteDTO(c);
    }
}
