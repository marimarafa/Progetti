package org.example.magazzino.controller;

import org.example.magazzino.dto.CategoriaDTO;
import org.example.magazzino.dto.ProdottoDTO;
import org.example.magazzino.dto.SottoCategoriaDTO;
import org.example.magazzino.dto.UnitaMisuraDTO;
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

    @GetMapping("/prodotti/nome/{nome}")
    public ProdottoDTO getProdottoByNome(@PathVariable String nome) {
        return service.prodottoPerNome(nome);
    }

    @GetMapping("/prodotti/quantita/{nome}")
    public int getQuantitaProdotto(@PathVariable String nome) {
        return service.quantitaProdotto(nome);
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

    @GetMapping("/prodotti/filtra")
    public List<ProdottoDTO> getProdottiByCategoriaAndSottoCategoria(
            @RequestParam int categoriaId,
            @RequestParam int sottoCategoriaId) {
        return service.prodottiPerCategoriaeSottoCategoria(sottoCategoriaId, categoriaId);
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

    // -------------------- CATEGORIE ------------------------

    @GetMapping("/categorie")
    public List<CategoriaDTO> getAllCategorie() {
        return service.selectAllCategorie();
    }

    @GetMapping("/categorie/{id}")
    public CategoriaDTO getCategoriaById(@PathVariable int id) {
        return service.selectByIdCategoria(id);
    }

    @PostMapping("/categorie")
    public CategoriaDTO insertCategoria(@RequestBody CategoriaDTO categoria) throws InstanceAlreadyExistsException {
        return service.insertCategoria(categoria);
    }

    @PutMapping("/categorie")
    public CategoriaDTO updateCategoria(@RequestBody CategoriaDTO categoria) {
        return service.updateCategoria(categoria);
    }

    @DeleteMapping("/categorie")
    public CategoriaDTO deleteCategoria(@RequestBody CategoriaDTO categoria) {
        return service.deleteCategoria(categoria);
    }

    // -------------------- UNITA' DI MISURA ------------------------

    @GetMapping("/unita-misura")
    public List<UnitaMisuraDTO> getAllUnitaMisura() {
        return service.selectAllUnitaMisura();
    }

    @GetMapping("/unita-misura/{id}")
    public UnitaMisuraDTO getUnitaMisuraById(@PathVariable int id) {
        return service.selectByIdUnitaMisura(id);
    }

    @PostMapping("/unita-misura")
    public UnitaMisuraDTO insertUnitaMisura(@RequestBody UnitaMisuraDTO unita) throws InstanceAlreadyExistsException {
        return service.insertUnitaMisura(unita);
    }

    @PutMapping("/unita-misura")
    public UnitaMisuraDTO updateUnitaMisura(@RequestBody UnitaMisuraDTO unita) {
        return service.updateUnitaMisura(unita);
    }

    @DeleteMapping("/unita-misura")
    public UnitaMisuraDTO deleteUnitaMisura(@RequestBody UnitaMisuraDTO unita) {
        return service.deleteUnitaMisura(unita);
    }
}
