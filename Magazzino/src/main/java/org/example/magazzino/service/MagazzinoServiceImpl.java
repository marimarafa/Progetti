package org.example.magazzino.service;

import jakarta.transaction.Transactional;
import org.example.magazzino.dao.ProdottoDAO;
import org.example.magazzino.dao.SottoCategoriaDAO;
import org.example.magazzino.dto.CategoriaDTO;
import org.example.magazzino.dto.ProdottoDTO;
import org.example.magazzino.dto.SottoCategoriaDTO;
import org.example.magazzino.entity.Prodotto;
import org.example.magazzino.entity.SottoCategoria;
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

    //........................... METODI PRODOTTO .................................................................

    @Override
    public List<ProdottoDTO> prodottiPerCategoriaeSottoCategoria(SottoCategoriaDTO sottoCategoria, int categoria) {
        List<Prodotto> prodotti = dao_Prodotto.prodottiPerCategoriaeSottoCategoria(
                Conversioni.daSottoCategoriaDTOASottoCategoria(sottoCategoria),
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


}
