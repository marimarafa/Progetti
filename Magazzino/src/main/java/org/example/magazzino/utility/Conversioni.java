package org.example.magazzino.utility;

import org.example.magazzino.dto.*;
import org.example.magazzino.entity.*;

import java.util.ArrayList;
import java.util.List;


public class Conversioni {

    // -------------------- Conversione CLIENTE --------------------
    public static Cliente daClienteDTOACliente(ClienteDTO dto) {
        return new Cliente(
                dto.getId(),
                dto.getNome(),
                dto.getCognome(),
                dto.getIndirizzo(),
                dto.getCodiceFiscale(),
                dto.getEmail(),
                dto.getTelefono(),
                dto.getNumeroCarta(),
                dto.getPartitaIva()
        );
    }

    public static Cliente daClienteDTOAClienteID(ClienteDTO dto) {
        return new Cliente(dto.getId());
    }

    public static ClienteDTO daClienteAClienteDTOID(Cliente cliente) {
        return new ClienteDTO(cliente.getId());
    }


    public static ClienteDTO daClienteAClienteDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCognome(),
                cliente.getIndirizzo(),
                cliente.getCodiceFiscale(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getNumeroCarta(),
                cliente.getPartitaIva()
        );
    }

    // -------------------- Conversione PRODOTTO --------------------
    public static Prodotto daProdottoDTOAProdotto(ProdottoDTO dto) {
        return new Prodotto(
                dto.getId(),
                dto.getNome(),
                dto.getPrezzo(),
                dto.getDescrizione(),
                dto.getQuantita(),
                dto.isDisponibilita(),
                daUnitaMisuraDTOAUnitaMisuraID(dto.getUnitaMisura_id()),
                daSottoCategoriaDTOASottoCategoriaID(dto.getSottoCategoria_id())
        );
    }

    public static ProdottoDTO daProdottoAProdottoDTO(Prodotto prodotto) {
        return new ProdottoDTO(
                prodotto.getId(),
                prodotto.getNome(),
                prodotto.getPrezzo(),
                prodotto.getDescrizione(),
                prodotto.getQuantita(),
                prodotto.isDisponibilita(),
                daUnitaMisuraAUnitaMisuraDTO(prodotto.getUnitaMisura()),
                daSottoCategoriaASottoCategoriaDTOID(prodotto.getSottoCategoria())
        );
    }

    // -------------------- Conversione CATEGORIA --------------------
    public static Categoria daCategoriaDTOACategoria(CategoriaDTO dto) {
        return new Categoria(dto.getId(), dto.getNome());
    }

    public static CategoriaDTO daCategoriaACategoriaDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNome());
    }

    public static CategoriaDTO daCategoriaACategoriaDTOID(Categoria categoria) {
        return new CategoriaDTO(categoria.getId());
    }

    public static Categoria daCategoriaDTOACategoriaID(CategoriaDTO dto) {
        return new Categoria(dto.getId());
    }


    // -------------------- Conversione UNITA DI MISURA --------------------
    public static UnitaMisura daUnitaMisuraDTOAUnitaMisura(UnitaMisuraDTO dto) {
        return new UnitaMisura(dto.getId(), dto.getDescrizione());
    }

    public static UnitaMisuraDTO daUnitaMisuraAUnitaMisuraDTO(UnitaMisura um) {
        return new UnitaMisuraDTO(um.getId(), um.getDescrizione());
    }

    public static UnitaMisura daUnitaMisuraDTOAUnitaMisuraID(UnitaMisuraDTO dto) {
        return new UnitaMisura(dto.getId());
    }

    public static UnitaMisuraDTO daUnitaMisuraAUnitaMisuraDTOID(UnitaMisura um) {
        return new UnitaMisuraDTO(um.getId());
    }

    // -------------------- Conversione SOTTOCATEGORIA --------------------
    public static SottoCategoria daSottoCategoriaDTOASottoCategoria(SottoCategoriaDTO dto) {
        return new SottoCategoria(dto.getId(), dto.getNome(), daCategoriaDTOACategoriaID(dto.getCategoriaId()));
    }

    public static SottoCategoriaDTO daSottoCategoriaASottoCategoriaDTO(SottoCategoria sc) {
        return new SottoCategoriaDTO(sc.getId(), sc.getNome(), daCategoriaACategoriaDTOID(sc.getCategoriaId()));
    }

    public static SottoCategoriaDTO daSottoCategoriaASottoCategoriaDTOID(SottoCategoria sc) {
        return new SottoCategoriaDTO(sc.getId());
    }

    public static SottoCategoria daSottoCategoriaDTOASottoCategoriaID(SottoCategoriaDTO dto) {
        return new SottoCategoria(dto.getId());
    }

    // -------------------- Conversione ORDINE --------------------
    public static Ordine daOrdineDTOAOrdine(OrdineDTO dto) {
        return new Ordine(dto.getId()
                , dto.getQuantita(),
                dto.getDataOra(),
                dto.getPrezzoTotale(),
                dto.isSospeso(),
                daClienteDTOAClienteID(dto.getClienteId()));
    }

    private static Prodotto daProdottoDTOAProdottoID(ProdottoDTO prodottoDTO) {
        return new Prodotto(prodottoDTO.getId());
    }

    public static OrdineDTO daOrdineAOrdineDTO(Ordine ordine) {
        return new OrdineDTO(
                ordine.getId(),
                ordine.getQuantita(),
                ordine.getDataOra(),
                ordine.getPrezzoTotale(),
                ordine.isSospeso(),
                daClienteAClienteDTOID(ordine.getClienteId())
        );
    }

    private static ProdottoDTO daProdottoAProdottoDTOID(Prodotto prodotto) {
        return new ProdottoDTO(prodotto.getId());
    }

    public static Ordine daOrdineDTOAOrdineID(OrdineDTO dto) {
        return new Ordine(dto.getId());
    }

    public static OrdineDTO daOrdineAOrdineDTOID(Ordine ordine) {
        return new OrdineDTO(ordine.getId());
    }



    // -------------------- Conversione MOVIMENTO --------------------

    public static Movimento daMovimentoDTOAMovimento(MovimentoDTO dto) {
        return new Movimento(dto.getDescrizione(), dto.getDataOra(), dto.getTipo(), dto.getId(),
                daOrdineDTOAOrdineID(dto.getOrdine()));
    }

    public static MovimentoDTO daMovimentoAMovimentoDTO(Movimento movimento) {
        return new MovimentoDTO(movimento.getTipo(), movimento.getDataOra(), movimento.getDescrizione(), movimento.getId(),
                daOrdineAOrdineDTOID(movimento.getOrdine()));
    }

    //-------------------------Conversione OrdineRefProdotto--------------------------

    public static OrdineRefProdottoDTO daOrdineRefProdottoAOrdineRefProdottoDTO(OrdineRefProdotto orp) {
        return new OrdineRefProdottoDTO(orp.getOrdine().getId(), orp.getProdotto().getId());
    }
    public static OrdineRefProdotto daOrdineRefProdottoDTOAOrdineRefProdotto(Ordine ordine,Prodotto prodotto) {
        return new OrdineRefProdotto(ordine,prodotto);
    }



}