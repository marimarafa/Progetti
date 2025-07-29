package org.example.magazzino.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "movimento")
public class Movimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "DATA_ORA")
    private LocalDateTime data_ora;
    @Column(name = "DESCRIZIONE")
    private String descrizione;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ORDINE_ID",referencedColumnName = "ID")
    private Ordine ordine ;

    public Movimento(String descrizione, LocalDateTime data_ora, String tipo, int id,Ordine ordine) {
        this.descrizione = descrizione;
        this.data_ora = data_ora;
        this.tipo = tipo;
        this.id = id;
        this.ordine = ordine;
    }

    public Movimento() {
    }

    public Ordine getOrdine() {
        return ordine;
    }


    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDateTime getData_ora() {
        return data_ora;
    }

    public void setData_ora(LocalDateTime data_ora) {
        this.data_ora = data_ora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
