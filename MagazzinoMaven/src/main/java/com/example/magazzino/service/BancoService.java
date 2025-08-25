package com.example.magazzino.service;

import com.example.magazzino.dto.*;

import java.util.List;


public interface BancoService {
    //      METODI CLIENTE
    ClienteDTO insertCliente(ClienteDTO cliente);
    ClienteDTO updateCliente(ClienteDTO cliente);
    ClienteDTO deleteCliente(int id);
    List<ClienteDTO> selectAllClienti();
    ClienteDTO selectByIdCliente(int id);
    //      METODI ORDINI
    ProdottiOrdineDTO insertOrdine(ProdottiOrdineDTO dto);
    OrdineDTO updateOrdine(OrdineDTO ordineDTO);
    OrdineDTO sospendiOrdine(int ordineId);
    OrdineDTO deleteOrdine(int ordineId);
    List<OrdineDTO> selectAllOrdini();
    List<OrdineDTO> ordiniCliente(int clienteId);
    //       METODI ORDINEREFPRODOTTO
    List<OrdineRefProdottoDTO> selectAllOrdineRefProdotto();
    OrdineRefProdottoDTO insertOrdineRefProdotto(OrdineRefProdottoDTO orpDTO);
    boolean EliminaProdottoInOrdine(int OrdineId, int ProdottoId);
    //      METODI MOVIMENTI
    MovimentoDTO insertMovimento(MovimentoDTO movimentoDTO);
    MovimentoDTO updateMovimento(MovimentoDTO movimentoDTO);
    MovimentoDTO deleteMovimento(int id);
    List<MovimentoDTO> selectAllMovimenti();
    MovimentoDTO selectMovimentoById(int id);
}
