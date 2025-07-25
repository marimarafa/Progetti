package org.example.magazzino.service;

import org.example.magazzino.dto.ClienteDTO;
import org.example.magazzino.dto.MovimentoDTO;
import org.example.magazzino.dto.OrdineDTO;

import java.util.List;


public interface BancoService {
    //      METODI CLIENTE
    public ClienteDTO insertCliente(ClienteDTO cliente);
    public ClienteDTO updateCliente(ClienteDTO cliente);
    public ClienteDTO deleteCliente(ClienteDTO cliente);
    public List<ClienteDTO> selectAllClienti();
    public ClienteDTO selectByIdCliente(int id);
    //      METODI ORDINI
    OrdineDTO insertOrdine(OrdineDTO ordineDTO);
    OrdineDTO updateOrdine(OrdineDTO ordineDTO);
    OrdineDTO sospendiOrdine(int ordineId);
    List<OrdineDTO> ordiniCliente(int clienteId);
    //      METODI MOVIMENTI
    MovimentoDTO insertMovimento(MovimentoDTO movimentoDTO);
    MovimentoDTO updateMovimento(MovimentoDTO movimentoDTO);
    MovimentoDTO deleteMovimento(MovimentoDTO movimentoDTO);
    List<MovimentoDTO> selectAllMovimenti();
    MovimentoDTO selectMovimentoById(int id);
}
