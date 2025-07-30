package org.example.magazzino.service;


import org.example.magazzino.dto.CategoriaDTO;
import org.example.magazzino.dto.ProdottoDTO;
import org.example.magazzino.dto.SottoCategoriaDTO;
import org.example.magazzino.dto.UnitaMisuraDTO;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public interface MagazzinoService {
    //      METODI PRODOTTO
    List<ProdottoDTO> prodottiPerCategoriaeSottoCategoria(int sottoCategoria, int categoria) ;
    ProdottoDTO insertProdotto(ProdottoDTO prodotto) throws InstanceAlreadyExistsException;
    ProdottoDTO updateProdotto(ProdottoDTO prodotto);
    ProdottoDTO deleteProdotto(int id);
    List<ProdottoDTO> selectAllProdotti();
    ProdottoDTO selectByIdProdotto(int id);
    ProdottoDTO prodottoPerNome(String nome);
    int quantitaProdotto(String nome);

    //        METODI SOTTOCATEGORIA
    SottoCategoriaDTO insertSottoCategoria(SottoCategoriaDTO sottoCategoria) throws InstanceAlreadyExistsException;
    SottoCategoriaDTO updateSottoCategoria(SottoCategoriaDTO sottoCategoria);
    SottoCategoriaDTO deleteSottoCategoria(int id);
    List<SottoCategoriaDTO> selectAllSottoCategorie();
    SottoCategoriaDTO selectByIdSottoCategoria(int id);

    //          METODI CATEGORIA
    CategoriaDTO insertCategoria(CategoriaDTO categoria) throws InstanceAlreadyExistsException;
    CategoriaDTO updateCategoria(CategoriaDTO categoria);
    CategoriaDTO deleteCategoria(int id);List<CategoriaDTO> selectAllCategorie();
    CategoriaDTO selectByIdCategoria(int id);

    //          METODI UNITAMISURA
    UnitaMisuraDTO insertUnitaMisura(UnitaMisuraDTO unita) throws InstanceAlreadyExistsException;
    UnitaMisuraDTO updateUnitaMisura(UnitaMisuraDTO unita);
    UnitaMisuraDTO deleteUnitaMisura(int id);
    List<UnitaMisuraDTO> selectAllUnitaMisura();
    UnitaMisuraDTO selectByIdUnitaMisura(int id);


}
