package org.example.magazzino.utility;

import org.example.magazzino.dto.*;
import org.example.magazzino.entity.*;


public class Conversioni {

    // -------------------- Conversione CLIENTE --------------------
    public static Cliente daClienteDTOACliente(ClienteDTO dto) {
        return new Cliente(
                dto.getId(),
                dto.getNome(),
                dto.getCognome(),
                dto.getIndirizzo(),
                dto.getCodice_fiscale(),
                dto.getEmail(),
                dto.getTelefono(),
                dto.getNumero_carta(),
                dto.getPartita_iva()
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
                cliente.getCodice_fiscale(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getNumero_carta(),
                cliente.getPartita_iva()
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
        return new SottoCategoria(dto.getId(), dto.getNome(), daCategoriaDTOACategoriaID(dto.getCategoria_id()));
    }

    public static SottoCategoriaDTO daSottoCategoriaASottoCategoriaDTO(SottoCategoria sc) {
        return new SottoCategoriaDTO(sc.getId(), sc.getNome(), daCategoriaACategoriaDTOID(sc.getCategoria_id()));
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
                dto.getData_ora(),
                dto.getPrezzo_totale(),
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
                ordine.getData_ora(),
                ordine.getPrezzo_totale(),
                ordine.isSospeso(),
                daClienteAClienteDTOID(ordine.getClienteId())
        );
    }

    private static ProdottoDTO daProdottoAProdottoDTOID(Prodotto prodotto) {
        return new ProdottoDTO(prodotto.getId());
    }


    // -------------------- Conversione MOVIMENTO --------------------
    public static Movimento daMovimentoDTOAMovimento(MovimentoDTO dto) {
        return new Movimento(dto.getDescrizione(), dto.getData_ora(), dto.getTipo(), dto.getId(), dto.getOrdine());
    }

    public static MovimentoDTO daMovimentoAMovimentoDTO(Movimento movimento) {
        return new MovimentoDTO(movimento.getTipo(), movimento.getData_ora(), movimento.getDescrizione(), movimento.getId(), movimento.getOrdine());
    }

    //-------------------------Conversione OrdineRefProdotto--------------------------

    public static OrdineRefProdottoDTO daOrdineRefProdottoAOrdineRefProdottoDTO(OrdineRefProdotto orp) {
        return new OrdineRefProdottoDTO(orp.getOrdine().getId(), orp.getProdotto().getId());
    }
    public static OrdineRefProdotto daOrdineRefProdottoDTOAOrdineRefProdotto(OrdineRefProdottoDTO orp) {
        return new OrdineRefProdotto(orp.getOrdine(), orp.getProdotto());
    }

}