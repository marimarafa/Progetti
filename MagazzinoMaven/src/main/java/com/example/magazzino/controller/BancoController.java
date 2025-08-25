package com.example.magazzino.controller;

import jakarta.validation.Valid;
import com.example.magazzino.dto.*;
import com.example.magazzino.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/banco")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    // ==================== CLIENTE ====================

    @PostMapping("/cliente")
    public ClienteDTO inserisciCliente( @Valid @RequestBody ClienteDTO clienteDTO) {
        return bancoService.insertCliente(clienteDTO);
    }

    @PutMapping("/cliente")
    public ClienteDTO aggiornaCliente( @Valid @RequestBody ClienteDTO clienteDTO) {
        return bancoService.updateCliente(clienteDTO);
    }

    @DeleteMapping("/cliente/{id}")
    public ClienteDTO eliminaCliente(@PathVariable int id) {
        return bancoService.deleteCliente(id);
    }

    @GetMapping("/clienti")
    public List<ClienteDTO> tuttiClienti() {
        return bancoService.selectAllClienti();
    }

    @GetMapping("/cliente/{id}")
    public ClienteDTO clientePerId(@PathVariable int id) {
        return bancoService.selectByIdCliente(id);
    }

    // ==================== MOVIMENTO ====================

    @PostMapping("/movimento")
    public MovimentoDTO inserisciMovimento(@RequestBody @Valid MovimentoDTO movimentoDTO) {
        return bancoService.insertMovimento(movimentoDTO);
    }

    @PutMapping("/movimento")
    public MovimentoDTO aggiornaMovimento(@RequestBody @Valid MovimentoDTO movimentoDTO) {
        return bancoService.updateMovimento(movimentoDTO);
    }

    @DeleteMapping("/movimento/{id}")
    public MovimentoDTO eliminaMovimento(@PathVariable int id) {
        return bancoService.deleteMovimento(id);
    }

    @GetMapping("/movimenti")
    public List<MovimentoDTO> tuttiMovimenti() {
        return bancoService.selectAllMovimenti();
    }

    @GetMapping("/movimento/{id}")
    public MovimentoDTO movimentoPerId(@PathVariable int id) {
        return bancoService.selectMovimentoById(id);
    }

    // ==================== ORDINE ====================

    @PostMapping(path = "/ordine", consumes = "application/json", produces = "application/json")
    public ProdottiOrdineDTO inserisciOrdine(@RequestBody @Valid ProdottiOrdineDTO ordineDTO) {
        return bancoService.insertOrdine(ordineDTO);
    }

    @PutMapping("/ordine")
    public OrdineDTO aggiornaOrdine(@RequestBody @Valid OrdineDTO ordineDTO) {
        return bancoService.updateOrdine(ordineDTO);
    }

    @DeleteMapping("/ordine/sospendi/{id}")
    public OrdineDTO sospendiOrdine(@PathVariable int id) {
        return bancoService.sospendiOrdine(id);
    }

    @DeleteMapping("/ordine/{id}")
    public OrdineDTO eliminaOrdine(@PathVariable int id) {
        return bancoService.deleteOrdine(id);
    }

    @GetMapping(path= "/ordini/{id}",produces = "application/json")
    public List<OrdineDTO> tuttiOrdiniCliente(@PathVariable int id) {
        return bancoService.ordiniCliente(id);
    }

    @GetMapping(path="/ordini",produces = "application/json")
    public List<OrdineDTO> tuttiOrdini() {
        return bancoService.selectAllOrdini();
    }

    // ==================== ORDINEREFPRODOTTO ====================

    @DeleteMapping("/ordine-ref-prodotto/{ordineid}/{prodottoid}")
    public boolean EliminaProdottoInOrdine(@PathVariable int ordineid, @PathVariable int prodottoid) {
        return bancoService.EliminaProdottoInOrdine(ordineid,prodottoid);
    }

    @PostMapping(path = "/ordine-ref-prodotto", consumes = "application/json", produces = "application/json")
    public OrdineRefProdottoDTO insertOrdineRefProdotto(@RequestBody @Valid OrdineRefProdottoDTO orpDTO) {
        return bancoService.insertOrdineRefProdotto(orpDTO);
    }

    @GetMapping(path="/ordini-ref-prodotto",produces = "application/json")
    public List<OrdineRefProdottoDTO> tuttiOrdiniRefProdotto() {
        return bancoService.selectAllOrdineRefProdotto();
    }

}
