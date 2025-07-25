package org.example.magazzino.service;

import jakarta.transaction.Transactional;
import org.example.magazzino.dao.CategoriaDAO;
import org.example.magazzino.dao.ProdottoDAO;
import org.example.magazzino.dao.SottoCategoriaDAO;
import org.example.magazzino.dao.UnitaMisuraDAO;
import org.example.magazzino.dto.CategoriaDTO;
import org.example.magazzino.dto.ProdottoDTO;
import org.example.magazzino.dto.SottoCategoriaDTO;
import org.example.magazzino.dto.UnitaMisuraDTO;
import org.example.magazzino.entity.Categoria;
import org.example.magazzino.entity.Prodotto;
import org.example.magazzino.entity.SottoCategoria;
import org.example.magazzino.entity.UnitaMisura;
import org.example.magazzino.utility.Conversioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MagazzinoServiceImpl implements MagazzinoService{

    @Autowired
    ProdottoDAO dao_Prodotto;
    SottoCategoriaDAO dao_SottoCategoria;
    CategoriaDAO dao_Categoria;
    UnitaMisuraDAO  dao_UnitaMisura;

    //........................... METODI PRODOTTO .................................................................

    @Override
    public List<ProdottoDTO> prodottiPerCategoriaeSottoCategoria(int sottoCategoria, int categoria) {
        List<Prodotto> prodotti = dao_Prodotto.prodottiPerCategoriaeSottoCategoria(
                sottoCategoria,
                categoria );

        List<ProdottoDTO> prodottiDTO = new ArrayList<>();
        for (Prodotto p : prodotti) {
            prodottiDTO.add(Conversioni.daProdottoAProdottoDTO(p));
        }

        return prodottiDTO;
         }

    @Override
    public ProdottoDTO insertProdotto(ProdottoDTO prodotto) throws InstanceAlreadyExistsException {
         return Conversioni.daProdottoAProdottoDTO(dao_Prodotto.insert(Conversioni.daProdottoDTOAProdotto(prodotto)));
    }

    @Override
    public ProdottoDTO updateProdotto(ProdottoDTO prodotto) {
        return Conversioni.daProdottoAProdottoDTO(dao_Prodotto.update(Conversioni.daProdottoDTOAProdotto(prodotto)));
    }

    @Override
    public ProdottoDTO deleteProdotto(ProdottoDTO prodotto) {
        return Conversioni.daProdottoAProdottoDTO(dao_Prodotto.deleteById(Conversioni.daProdottoDTOAProdotto(prodotto).getId()));
    }

    @Override
    public List<ProdottoDTO> selectAllProdotti() {
        List<Prodotto> prodotti = dao_Prodotto.selectAll();
        List<ProdottoDTO> prodottidto = new ArrayList<ProdottoDTO>();
        for (Prodotto prodotto : prodotti) {
            prodottidto.add(Conversioni.daProdottoAProdottoDTO(prodotto));
        }
        return prodottidto;
    }

    @Override
    public ProdottoDTO selectByIdProdotto(int id) {
        return Conversioni.daProdottoAProdottoDTO(dao_Prodotto.selectById(id));
    }

    @Override
    public ProdottoDTO prodottoPerNome(String nome) {
        Prodotto prodotto = dao_Prodotto.prodottoPerNome(nome);
        return Conversioni.daProdottoAProdottoDTO(prodotto);
    }


    @Override
    public int quantitaProdotto(String nome) {
        int quantita = dao_Prodotto.quantitaProdotto(nome);

        Prodotto prodotto = dao_Prodotto.prodottoPerNome(nome);
        if (quantita == 0) {
            prodotto.setDisponibilita(false);
            dao_Prodotto.update(prodotto);
            throw new RuntimeException("Prodotto non disponibile in magazzino");
        } else {
            prodotto.setDisponibilita(true);
            dao_Prodotto.update(prodotto);
        }

        return quantita;
    }


    // ......................METODI SOTTOCATEGORIA.......................................................

    @Override
    public SottoCategoriaDTO insertSottoCategoria(SottoCategoriaDTO sottoCategoria) throws InstanceAlreadyExistsException {
        return Conversioni.daSottoCategoriaASottoCategoriaDTO(dao_SottoCategoria.insert(Conversioni.daSottoCategoriaDTOASottoCategoria(sottoCategoria)));
    }

    @Override
    public SottoCategoriaDTO updateSottoCategoria(SottoCategoriaDTO sottoCategoria) {
        return Conversioni.daSottoCategoriaASottoCategoriaDTO(dao_SottoCategoria.update(Conversioni.daSottoCategoriaDTOASottoCategoria(sottoCategoria)));
    }

    @Override
    public SottoCategoriaDTO deleteSottoCategoria(SottoCategoriaDTO sottoCategoria) {
        return Conversioni.daSottoCategoriaASottoCategoriaDTO(dao_SottoCategoria.deleteById(Conversioni.daSottoCategoriaDTOASottoCategoria(sottoCategoria).getId()));
    }

    @Override
    public List<SottoCategoriaDTO> selectAllSottoCategorie() {
        List<SottoCategoria> sottoCategorie = dao_SottoCategoria.selectAll();
        List<SottoCategoriaDTO> sottoCategoriedto = new ArrayList<SottoCategoriaDTO>();
        for (SottoCategoria sottoCategoria : sottoCategorie) {
            sottoCategoriedto.add(Conversioni.daSottoCategoriaASottoCategoriaDTO(sottoCategoria));
        }
        return sottoCategoriedto;
    }

    @Override
    public SottoCategoriaDTO selectByIdSottoCategoria(int id) {
        return Conversioni.daSottoCategoriaASottoCategoriaDTO(dao_SottoCategoria.selectById(id));
    }




    // ...........................METODI CATEGORIA............................................................

    @Override
    public CategoriaDTO insertCategoria(CategoriaDTO categoria) throws InstanceAlreadyExistsException {
        return Conversioni.daCategoriaACategoriaDTO(dao_Categoria.insert(Conversioni.daCategoriaDTOACategoria(categoria)));
    }

    @Override
    public CategoriaDTO updateCategoria(CategoriaDTO categoria) {
        return Conversioni.daCategoriaACategoriaDTO(dao_Categoria.update(Conversioni.daCategoriaDTOACategoria(categoria)));
    }

    @Override
    public CategoriaDTO deleteCategoria(CategoriaDTO categoria) {
        return Conversioni.daCategoriaACategoriaDTO(dao_Categoria.deleteById(Conversioni.daCategoriaDTOACategoria(categoria).getId()));
    }

    @Override
    public List<CategoriaDTO> selectAllCategorie() {
        List<Categoria> categorie = dao_Categoria.selectAll();
        List<CategoriaDTO> categorieDTO = new ArrayList<>();
        for (Categoria c : categorie) {
            categorieDTO.add(Conversioni.daCategoriaACategoriaDTO(c));
        }
        return categorieDTO;
    }

    @Override
    public CategoriaDTO selectByIdCategoria(int id) {
        return Conversioni.daCategoriaACategoriaDTO(dao_Categoria.selectById(id));
    }

    //.................................METODI UNITAMISURA................................................


    @Override
    public UnitaMisuraDTO insertUnitaMisura(UnitaMisuraDTO unita) throws InstanceAlreadyExistsException {
        return Conversioni.daUnitaMisuraAUnitaMisuraDTO(
                dao_UnitaMisura.insert(Conversioni.daUnitaMisuraDTOAUnitaMisura(unita))
        );
    }

    @Override
    public UnitaMisuraDTO updateUnitaMisura(UnitaMisuraDTO unita) {
        return Conversioni.daUnitaMisuraAUnitaMisuraDTO(
                dao_UnitaMisura.update(Conversioni.daUnitaMisuraDTOAUnitaMisura(unita))
        );
    }

    @Override
    public UnitaMisuraDTO deleteUnitaMisura(UnitaMisuraDTO unita) {
        return Conversioni.daUnitaMisuraAUnitaMisuraDTO(
                dao_UnitaMisura.deleteById(Conversioni.daUnitaMisuraDTOAUnitaMisura(unita).getId())
        );
    }

    @Override
    public List<UnitaMisuraDTO> selectAllUnitaMisura() {
        List<UnitaMisura> unitaMisure = dao_UnitaMisura.selectAll();
        List<UnitaMisuraDTO> unitaMisuredto = new ArrayList<>();
        for (UnitaMisura u : unitaMisure) {
            unitaMisuredto.add(Conversioni.daUnitaMisuraAUnitaMisuraDTO(u));
        }
        return unitaMisuredto;
    }

    @Override
    public UnitaMisuraDTO selectByIdUnitaMisura(int id) {
        return Conversioni.daUnitaMisuraAUnitaMisuraDTO(dao_UnitaMisura.selectById(id));
    }
}
