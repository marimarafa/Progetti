package org.example.magazzino.controller;

import org.example.magazzino.dto.CategoriaDTO;
import org.example.magazzino.dto.ProdottoDTO;
import org.example.magazzino.dto.SottoCategoriaDTO;
import org.example.magazzino.service.MagazzinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

@RestController
@RequestMapping("/magazzino")
public class MagazzinoController {

    @Autowired
    private MagazzinoService service;

    // -------------------- PRODOTTI ------------------------

    @GetMapping("/prodotti")
    public List<ProdottoDTO> getAllProdotti() {
        return service.selectAllProdotti();
    }

    @GetMapping("/prodotti/{id}")
    public ProdottoDTO getProdottoById(@PathVariable int id) {
        return service.selectByIdProdotto(id);
    }

    @PostMapping("/prodotti")
    public ProdottoDTO insertProdotto(@RequestBody ProdottoDTO prodotto) throws InstanceAlreadyExistsException {
        return service.insertProdotto(prodotto);
    }

    @PutMapping("/prodotti")
    public ProdottoDTO updateProdotto(@RequestBody ProdottoDTO prodotto) {
        return service.updateProdotto(prodotto);
    }

    @DeleteMapping("/prodotti")
    public ProdottoDTO deleteProdotto(@RequestBody ProdottoDTO prodotto) {
        return service.deleteProdotto(prodotto);
    }

    @PostMapping("/prodotti/per-categoria")
    public List<ProdottoDTO> getProdottiByCategoriaAndSottoCategoria(@RequestBody int sottoCategoria,
                                                                     @RequestParam("categoriaId") int categoriaId) {
        return service.prodottiPerCategoriaeSottoCategoria(sottoCategoria,categoriaId);
    }

    // -------------------- SOTTOCATEGORIE ------------------------

    @GetMapping("/sottocategorie")
    public List<SottoCategoriaDTO> getAllSottoCategorie() {
        return service.selectAllSottoCategorie();
    }

    @GetMapping("/sottocategorie/{id}")
    public SottoCategoriaDTO getSottoCategoriaById(@PathVariable int id) {
        return service.selectByIdSottoCategoria(id);
    }

    @PostMapping("/sottocategorie")
    public SottoCategoriaDTO insertSottoCategoria(@RequestBody SottoCategoriaDTO sottoCategoria) throws InstanceAlreadyExistsException {
        return service.insertSottoCategoria(sottoCategoria);
    }

    @PutMapping("/sottocategorie")
    public SottoCategoriaDTO updateSottoCategoria(@RequestBody SottoCategoriaDTO sottoCategoria) {
        return service.updateSottoCategoria(sottoCategoria);
    }

    @DeleteMapping("/sottocategorie")
    public SottoCategoriaDTO deleteSottoCategoria(@RequestBody SottoCategoriaDTO sottoCategoria) {
        return service.deleteSottoCategoria(sottoCategoria);
    }
}

