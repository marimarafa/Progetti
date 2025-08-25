package org.example.magazzino.controller;

import jakarta.validation.Valid;
import org.example.magazzino.dto.CategoriaDTO;
import org.example.magazzino.dto.ProdottoDTO;
import org.example.magazzino.dto.SottoCategoriaDTO;
import org.example.magazzino.dto.UnitaMisuraDTO;
import org.example.magazzino.service.MagazzinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

@Validated
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

    @GetMapping("/prodotti/nome")
    public ProdottoDTO getProdottoByNome(@RequestParam String nome) {
        return service.prodottoPerNome(nome);
    }

    @GetMapping("/prodotti/quantita")
    public int SetDisponibilta(@RequestParam String nome) {
        return service.SetDisponibilta(nome);
    }

    @PostMapping(path = "/prodotti", consumes = "application/json", produces = "application/json")
    public ProdottoDTO insertProdotto(@RequestBody @Valid ProdottoDTO prodotto) throws InstanceAlreadyExistsException {
        return service.insertProdotto(prodotto);
    }

    @PutMapping("/prodotti")
    public ProdottoDTO updateProdotto(@RequestBody @Valid ProdottoDTO prodotto) {
        return service.updateProdotto(prodotto);
    }

    @DeleteMapping("/prodotti/{id}")
    public ProdottoDTO deleteProdotto(@PathVariable int id) {
        return service.deleteProdotto(id);
    }

    @GetMapping("/prodotti/{sottoCategoria_id}/{categoria_id}")
    public List<ProdottoDTO> getProdottiByCategoriaAndSottoCategoria(
            @PathVariable int sottoCategoria_id,
            @PathVariable int categoria_id
            ) {
        return service.prodottiPerCategoriaeSottoCategoria(sottoCategoria_id, categoria_id);
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
    public SottoCategoriaDTO insertSottoCategoria(@RequestBody @Valid SottoCategoriaDTO sottoCategoria) throws InstanceAlreadyExistsException {
        return service.insertSottoCategoria(sottoCategoria);
    }

    @PutMapping("/sottocategorie")
    public SottoCategoriaDTO updateSottoCategoria(@RequestBody @Valid SottoCategoriaDTO sottoCategoria) {
        return service.updateSottoCategoria(sottoCategoria);
    }

    @DeleteMapping("/sottocategorie/{id}")
    public SottoCategoriaDTO deleteSottoCategoria(@PathVariable int id) {
        return service.deleteSottoCategoria(id);
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
    public CategoriaDTO insertCategoria(@RequestBody @Valid CategoriaDTO categoria) throws InstanceAlreadyExistsException {
        return service.insertCategoria(categoria);
    }

    @PutMapping("/categorie")
    public CategoriaDTO updateCategoria(@RequestBody @Valid CategoriaDTO categoria) {
        return service.updateCategoria(categoria);
    }

    @DeleteMapping("/categorie/{id}")
    public CategoriaDTO deleteCategoria(@PathVariable int id) {
        return service.deleteCategoria(id);
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
    public UnitaMisuraDTO insertUnitaMisura(@RequestBody @Valid UnitaMisuraDTO unita) throws InstanceAlreadyExistsException {
        return service.insertUnitaMisura(unita);
    }

    @PutMapping("/unita-misura")
    public UnitaMisuraDTO updateUnitaMisura(@RequestBody @Valid UnitaMisuraDTO unita) {
        return service.updateUnitaMisura(unita);
    }

    @DeleteMapping("/unita-misura/{id}")
    public UnitaMisuraDTO deleteUnitaMisura(@PathVariable int id) {
        return service.deleteUnitaMisura(id);
    }
}
