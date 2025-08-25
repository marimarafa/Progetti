package org.example.magazzino.service;

import org.example.magazzino.dao.*;
import org.example.magazzino.dto.*;
import org.example.magazzino.entity.*;
import org.example.magazzino.utility.Conversioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
@Transactional
public class BancoServiceImpl implements BancoService {

    @Autowired
    private ClienteDAO daoCliente;
    @Autowired
    private OrdineDAO daoOrdine;
    @Autowired
    private MovimentoDAO daoMovimento;
    @Autowired
    private ProdottoDAO daoProdotto;
    @Autowired
    private OrdineRefProdottoDAO  daoOrdineRefProdotto;

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
    public ClienteDTO deleteCliente(int id) {
        Cliente eliminato = daoCliente.deleteById(id);
        return Conversioni.daClienteAClienteDTO(eliminato);
    }

    @Override
    public List<ClienteDTO> selectAllClienti() {
        List<Cliente> clienti = daoCliente.selectAll();
        List<ClienteDTO> clientiDTO = new ArrayList<>();
        for (Cliente c : clienti) {
            clientiDTO.add(Conversioni.daClienteAClienteDTO(c));
        }
        return clientiDTO;
    }

    @Override
    public ClienteDTO selectByIdCliente(int id) {
        Cliente c = daoCliente.selectById(id);
        return Conversioni.daClienteAClienteDTO(c);
    }

    // ---------------- METODI ORDINE ----------------

    @Override
    public ProdottiOrdineDTO insertOrdine(ProdottiOrdineDTO dto) {
        OrdineDTO ordineDto = dto.getOrdineDTO();
        Cliente cliente = daoCliente.selectById(ordineDto.getClienteId().getId());

        Ordine ordine = new Ordine();
        ordine.setQuantita(ordineDto.getQuantita());
        ordine.setDataOra(ordineDto.getDataOra());
        ordine.setPrezzoTotale(ordineDto.getPrezzoTotale());
        ordine.setSospeso(ordineDto.isSospeso());
        ordine.setClienteId(cliente);

        Ordine ordineSalvato = daoOrdine.insert(ordine);

        for (ProdottoDTO prodottoDTO : dto.getProdotti()) {
            if (!daoProdotto.existsById(prodottoDTO.getId())) {
                throw new NoSuchElementException("Prodotto con ID " + prodottoDTO.getId() + " non trovato nel database.");
            }
            Prodotto prodotto = daoProdotto.selectById(prodottoDTO.getId());
            OrdineRefProdottoDTO orp = new OrdineRefProdottoDTO();
            orp.setOrdine(ordineSalvato.getId());
            orp.setProdotto(prodotto.getId());
            daoOrdineRefProdotto.insert(Conversioni.daOrdineRefProdottoDTOAOrdineRefProdotto(ordineSalvato,prodotto));
        }

        OrdineDTO ordineDtoSalvato = Conversioni.daOrdineAOrdineDTO(ordineSalvato);
        return new ProdottiOrdineDTO(ordineDtoSalvato, dto.getProdotti());
    }


    @Override
    public OrdineDTO updateOrdine(OrdineDTO ordineDTO) {
        Cliente cliente = daoCliente.selectById(ordineDTO.getClienteId().getId());
        Ordine ordine = Conversioni.daOrdineDTOAOrdine(ordineDTO);
        ordine.setClienteId(cliente);

        return Conversioni.daOrdineAOrdineDTO(daoOrdine.update(ordine));
    }


    @Override
    public OrdineDTO sospendiOrdine(int ordineId) {
        Ordine ordine = daoOrdine.selectById(ordineId);
        ordine.setSospeso(!ordine.isSospeso());
        return Conversioni.daOrdineAOrdineDTO(daoOrdine.update(ordine));
    }

    @Override
    public OrdineDTO deleteOrdine(int ordineId) {
        Ordine eliminato = daoOrdine.deleteById(ordineId);
        return Conversioni.daOrdineAOrdineDTO(eliminato);
    }

    @Override
    public List<OrdineDTO> selectAllOrdini() {
        List<Ordine> ordini = daoOrdine.selectAll();
        List<OrdineDTO> ordiniDto = new ArrayList<>();
        for (Ordine o : ordini) {
            ordiniDto.add(Conversioni.daOrdineAOrdineDTO(o));
        }
        return ordiniDto;
    }

    @Override
    public List<OrdineDTO> ordiniCliente(int clienteId) {
        List<Ordine> ordini = daoOrdine.findByClienteId(clienteId);
        List<OrdineDTO> ordiniDto = new ArrayList<>();
        for (Ordine o : ordini) {
            ordiniDto.add(Conversioni.daOrdineAOrdineDTO(o));
        }
        return ordiniDto;
    }


    //...................METODI ORDINEREFPRODOTTO..............................


    @Override
    public List<OrdineRefProdottoDTO> selectAllOrdineRefProdotto() {
        List<OrdineRefProdotto> orprodotti  = daoOrdineRefProdotto.selectAll();
        List<OrdineRefProdottoDTO> orpdto = new ArrayList<>();
        for (OrdineRefProdotto orp : orprodotti ) {
            orpdto.add(Conversioni.daOrdineRefProdottoAOrdineRefProdottoDTO(orp));
        }
        return orpdto;
    }

    @Override
    public OrdineRefProdottoDTO insertOrdineRefProdotto(OrdineRefProdottoDTO orpDTO) {
        Ordine o = daoOrdine.selectById(orpDTO.getOrdine());
        Prodotto p = daoProdotto.selectById(orpDTO.getProdotto());

        OrdineRefProdotto entity = Conversioni.daOrdineRefProdottoDTOAOrdineRefProdotto(o,p);
        entity.setOrdine(o);
        entity.setProdotto(p);
        return Conversioni.daOrdineRefProdottoAOrdineRefProdottoDTO(daoOrdineRefProdotto.insert(entity));
    }

    @Override
    public boolean EliminaProdottoInOrdine(int ordineId,int prodottoId){
        return daoOrdineRefProdotto.EliminaProdottoInOrdine(ordineId, prodottoId);
    }

    //......................METODI MOVIMENTO..................................


    @Override
    public MovimentoDTO insertMovimento(MovimentoDTO dto) {
        Ordine ordine = daoOrdine.selectById(dto.getOrdine().getId());
        Movimento entity = Conversioni.daMovimentoDTOAMovimento(dto);
        entity.setOrdine(ordine);
        return Conversioni.daMovimentoAMovimentoDTO(daoMovimento.insert(entity));
    }

    @Override
    public MovimentoDTO updateMovimento(MovimentoDTO dto) {
        Ordine ordine = daoOrdine.selectById(dto.getOrdine().getId());
        Movimento entity = Conversioni.daMovimentoDTOAMovimento(dto);
        entity.setOrdine(ordine);
        return Conversioni.daMovimentoAMovimentoDTO(daoMovimento.update(entity));
    }

    @Override
    public MovimentoDTO deleteMovimento(int id) {
        return Conversioni.daMovimentoAMovimentoDTO(daoMovimento.deleteById(id));
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
