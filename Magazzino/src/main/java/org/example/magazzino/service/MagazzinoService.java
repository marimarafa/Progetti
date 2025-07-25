package org.example.magazzino.service;


import org.example.magazzino.dto.CategoriaDTO;
import org.example.magazzino.dto.ProdottoDTO;
import org.example.magazzino.dto.SottoCategoriaDTO;
import org.example.magazzino.dto.UnitaMisuraDTO;
import org.example.magazzino.entity.Prodotto;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public interface MagazzinoService {
    //      METODI PRODOTTO
    public List<ProdottoDTO> prodottiPerCategoriaeSottoCategoria(int sottoCategoria, int categoria) ;
    public ProdottoDTO insertProdotto(ProdottoDTO prodotto) throws InstanceAlreadyExistsException;
    public ProdottoDTO updateProdotto(ProdottoDTO prodotto);
    public ProdottoDTO deleteProdotto(ProdottoDTO prodotto);
    public List<ProdottoDTO> selectAllProdotti();
    public ProdottoDTO selectByIdProdotto(int id);

    //        METODI SOTTOCATEGORIA
    public SottoCategoriaDTO insertSottoCategoria(SottoCategoriaDTO sottoCategoria) throws InstanceAlreadyExistsException;
    public SottoCategoriaDTO updateSottoCategoria(SottoCategoriaDTO sottoCategoria);
    public SottoCategoriaDTO deleteSottoCategoria(SottoCategoriaDTO sottoCategoria);
    public List<SottoCategoriaDTO> selectAllSottoCategorie();
    public SottoCategoriaDTO selectByIdSottoCategoria(int id);
    public ProdottoDTO prodottoPerNome(String nome);
    public int quantitaProdotto(String nome);


    //          METODI CATEGORIA
    public CategoriaDTO insertCategoria(CategoriaDTO categoria) throws InstanceAlreadyExistsException;
    public CategoriaDTO updateCategoria(CategoriaDTO categoria);
    public CategoriaDTO deleteCategoria(CategoriaDTO categoria);
    public List<CategoriaDTO> selectAllCategorie();
    public CategoriaDTO selectByIdCategoria(int id);

    //          METODI UNITAMISURA
    public UnitaMisuraDTO insertUnitaMisura(UnitaMisuraDTO unita) throws InstanceAlreadyExistsException;
    public UnitaMisuraDTO updateUnitaMisura(UnitaMisuraDTO unita);
    public UnitaMisuraDTO deleteUnitaMisura(UnitaMisuraDTO unita);
    public List<UnitaMisuraDTO> selectAllUnitaMisura();
    public UnitaMisuraDTO selectByIdUnitaMisura(int id);


}
