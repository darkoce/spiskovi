package com.zepp.spiskovi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Posiljka {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int posiljkaId;

    private int postanskiBroj;
    private int rejon;
    private int kolicina;

    public Posiljka(int postanskiBroj, int rejon, int kolicina) {
        this.postanskiBroj = postanskiBroj;
        this.rejon = rejon;
        this.kolicina = kolicina;
    }

    public int getPosiljkaId() {
        return posiljkaId;
    }

    public void setPosiljkaId(int posiljkaId) {
        this.posiljkaId = posiljkaId;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public int getRejon() {
        return rejon;
    }

    public void setRejon(int rejon) {
        this.rejon = rejon;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}
