package com.zepp.spiskovi.service;

import com.zepp.spiskovi.Common.PosiljkaStats;
import com.zepp.spiskovi.model.Posiljka;

import java.util.List;
import java.util.Optional;

public interface PosiljkaService {
    List<Posiljka> getPosiljka();
    Optional<Posiljka> findById(int posiljkaId);
    void delete(int posiljkaId);
    Posiljka savePosiljka(Posiljka posiljka);
    List<Object[]> spiskoviFinal();
}
