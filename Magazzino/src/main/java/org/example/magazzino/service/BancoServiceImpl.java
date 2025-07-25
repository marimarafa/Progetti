package org.example.magazzino.service;

import org.example.magazzino.dao.ClienteDAO;
import org.example.magazzino.dao.MovimentoDAO;
import org.example.magazzino.dao.OrdineDAO;
import org.example.magazzino.dto.ClienteDTO;
import org.example.magazzino.dto.MovimentoDTO;
import org.example.magazzino.dto.OrdineDTO;
import org.example.magazzino.entity.Cliente;
import org.example.magazzino.entity.Movimento;
import org.example.magazzino.entity.Ordine;
import org.example.magazzino.utility.Conversioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class BancoServiceImpl implements BancoService {

    @Autowired
    private ClienteDAO daoCliente;
    private OrdineDAO daoOrdine;
    private MovimentoDAO daoMovimento;

    // ---------------- METODI CLIENTE ----------------

    @Override
    public ClienteDTO insertCliente(ClienteDTO dto) {
        Cliente entity = Conversioni.daClienteDTOACliente(dto);
        Cliente salvato = daoCliente.insert(entity);
        return Conversioni.daClienteAClienteDTO(salvato);
    }

    @Override
    public ClienteDTO updateCliente(ClienteDTO dto) {
        Cliente esistente = daoCliente.selectById(dto.getId());
        if (esistente == null) throw new RuntimeException("Cliente non trovato");
        if (dto.getNome() != null) esistente.setNome(dto.getNome());
        if (dto.getCognome() != null) esistente.setCognome(dto.getCognome());

        Cliente aggiornato = daoCliente.update(esistente);
        return Conversioni.daClienteAClienteDTO(aggiornato);
    }

    @Override
    public ClienteDTO deleteCliente(ClienteDTO dto) {
        Cliente eliminato = daoCliente.deleteById(dto.getId());
        return Conversioni.daClienteAClienteDTO(eliminato);
    }

    @Override
    public List<ClienteDTO> selectAllClienti() {
        List<Cliente> clienti = daoCliente.selectAll();
        List<ClienteDTO> clientidto = new ArrayList<>();
        for (Cliente c : clienti) {
            clientidto.add(Conversioni.daClienteAClienteDTO(c));
        }
        return clientidto;
    }

    @Override
    public ClienteDTO selectByIdCliente(int id) {
        Cliente c = daoCliente.selectById(id);
        return Conversioni.daClienteAClienteDTO(c);
    }

    // ---------------- METODI ORDINE ----------------

    @Override
    public OrdineDTO insertOrdine(OrdineDTO ordineDTO) {
        Ordine ordine = Conversioni.daOrdineDTOAOrdine(ordineDTO);
        Ordine salvato = daoOrdine.insert(ordine);
        return Conversioni.daOrdineAOrdineDTO(salvato);
    }

    @Override
    public OrdineDTO updateOrdine(OrdineDTO ordineDTO) {
        Ordine esistente = daoOrdine.selectById(ordineDTO.getId());
        if (esistente != null){
            Ordine aggiornato = daoOrdine.update(esistente);
            return Conversioni.daOrdineAOrdineDTO(aggiornato);
        } else {
            throw new RuntimeException("Ordine non trovato");
        }
    }


    @Override
    public OrdineDTO sospendiOrdine(int ordineId) {
        Ordine ordine = daoOrdine.selectById(ordineId);
        ordine.setSospeso(true);
        return Conversioni.daOrdineAOrdineDTO(daoOrdine.update(ordine));
    }

    @Override
    public List<OrdineDTO> ordiniCliente(int clienteId) {
        List<Ordine> ordini = daoOrdine.findByClienteId(clienteId);
        List<OrdineDTO> ordinidto = new ArrayList<>();
        for (Ordine o : ordini) {
            ordinidto.add(Conversioni.daOrdineAOrdineDTO(o));
        }
        return ordinidto;
    }

    //......................METODI MOVIMENTO..................................


    @Override
    public MovimentoDTO insertMovimento(MovimentoDTO dto) {
        Movimento m = Conversioni.daMovimentoDTOAMovimento(dto);
        return Conversioni.daMovimentoAMovimentoDTO(daoMovimento.insert(m));
    }

    @Override
    public MovimentoDTO updateMovimento(MovimentoDTO dto) {
        Movimento m = Conversioni.daMovimentoDTOAMovimento(dto);
        return Conversioni.daMovimentoAMovimentoDTO(daoMovimento.update(m));
    }

    @Override
    public MovimentoDTO deleteMovimento(MovimentoDTO dto) {
        return Conversioni.daMovimentoAMovimentoDTO(daoMovimento.deleteById(dto.getId()));
    }

    @Override
    public List<MovimentoDTO> selectAllMovimenti() {
        List<Movimento> movimenti  = daoMovimento.selectAll();
        List<MovimentoDTO> movimentidto = new ArrayList<>();
        for (Movimento m : movimenti ) {
            movimentidto.add(Conversioni.daMovimentoAMovimentoDTO(m));
        }
        return movimentidto;
    }

    @Override
    public MovimentoDTO selectMovimentoById(int id) {
        return Conversioni.daMovimentoAMovimentoDTO(daoMovimento.selectById(id));
    }
}
