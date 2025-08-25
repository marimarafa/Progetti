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
    @Autowired
    SottoCategoriaDAO dao_SottoCategoria;
    @Autowired
    CategoriaDAO dao_Categoria;
    @Autowired
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
        SottoCategoria sottoCategoria = dao_SottoCategoria.selectById(prodotto.getSottoCategoria_id().getId());
        UnitaMisura unitaMisura = dao_UnitaMisura.selectById(prodotto.getUnitaMisura_id().getId());
        Prodotto entity = Conversioni.daProdottoDTOAProdotto(prodotto);
        entity.setSottoCategoria(sottoCategoria);
        entity.setUnitaMisura(unitaMisura);
        return Conversioni.daProdottoAProdottoDTO(dao_Prodotto.insert(entity));
    }

    @Override
    public ProdottoDTO updateProdotto(ProdottoDTO prodotto) {
        SottoCategoria sottoCategoria = dao_SottoCategoria.selectById(prodotto.getSottoCategoria_id().getId());
        UnitaMisura unitaMisura = dao_UnitaMisura.selectById(prodotto.getUnitaMisura_id().getId());
        Prodotto entity = Conversioni.daProdottoDTOAProdotto(prodotto);
        entity.setSottoCategoria(sottoCategoria);
        entity.setUnitaMisura(unitaMisura);
        return Conversioni.daProdottoAProdottoDTO(dao_Prodotto.update(entity));
    }

    @Override
    public ProdottoDTO deleteProdotto(int id) {
        return Conversioni.daProdottoAProdottoDTO(dao_Prodotto.deleteById(id));
    }

    @Override
    public List<ProdottoDTO> selectAllProdotti() {
        List<Prodotto> prodotti = dao_Prodotto.selectAll();
        List<ProdottoDTO> prodottiDTO = new ArrayList<ProdottoDTO>();
        for (Prodotto prodotto : prodotti) {
            prodottiDTO.add(Conversioni.daProdottoAProdottoDTO(prodotto));
        }
        return prodottiDTO;
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
    public int SetDisponibilta(String nome) {
        Prodotto prodotto = dao_Prodotto.prodottoPerNome(nome);

        if (prodotto.getQuantita() == 0) {
            prodotto.setDisponibilita(false);
            dao_Prodotto.update(prodotto);
            throw new RuntimeException("Prodotto non disponibile in magazzino");
        } else {
            prodotto.setDisponibilita(true);
            dao_Prodotto.update(prodotto);
        }

        return prodotto.getQuantita();
    }


    // ......................METODI SOTTOCATEGORIA.......................................................

    @Override
    public SottoCategoriaDTO insertSottoCategoria(SottoCategoriaDTO sottoCategoria) throws InstanceAlreadyExistsException {
        Categoria categoria = dao_Categoria.selectById(sottoCategoria.getCategoriaId().getId());
        SottoCategoria entity = Conversioni.daSottoCategoriaDTOASottoCategoria(sottoCategoria);
        entity.setCategoriaId(categoria);
        return Conversioni.daSottoCategoriaASottoCategoriaDTO(dao_SottoCategoria.insert(entity));
    }

    @Override
    public SottoCategoriaDTO updateSottoCategoria(SottoCategoriaDTO sottoCategoria) {
        Categoria categoria = dao_Categoria.selectById(sottoCategoria.getCategoriaId().getId());
        SottoCategoria entity = Conversioni.daSottoCategoriaDTOASottoCategoria(sottoCategoria);
        entity.setCategoriaId(categoria);
        return Conversioni.daSottoCategoriaASottoCategoriaDTO(dao_SottoCategoria.update(entity));
    }

    @Override
    public SottoCategoriaDTO deleteSottoCategoria(int id) {
        return Conversioni.daSottoCategoriaASottoCategoriaDTO(dao_SottoCategoria.deleteById(id));
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
    public CategoriaDTO deleteCategoria(int id) {
        return Conversioni.daCategoriaACategoriaDTO(dao_Categoria.deleteById(id));
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
    public UnitaMisuraDTO deleteUnitaMisura(int id) {
        return Conversioni.daUnitaMisuraAUnitaMisuraDTO(dao_UnitaMisura.deleteById(id));
    }

    @Override
    public List<UnitaMisuraDTO> selectAllUnitaMisura() {
        List<UnitaMisura> unitaMisure = dao_UnitaMisura.selectAll();
        List<UnitaMisuraDTO> unitaMisureDTO = new ArrayList<>();
        for (UnitaMisura u : unitaMisure) {
            unitaMisureDTO.add(Conversioni.daUnitaMisuraAUnitaMisuraDTO(u));
        }
        return unitaMisureDTO;
    }

    @Override
    public UnitaMisuraDTO selectByIdUnitaMisura(int id) {
        return Conversioni.daUnitaMisuraAUnitaMisuraDTO(dao_UnitaMisura.selectById(id));
    }
}
