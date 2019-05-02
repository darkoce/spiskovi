package com.zepp.spiskovi.service;

import com.zepp.spiskovi.Common.PosiljkaStats;
import com.zepp.spiskovi.model.Posiljka;
import com.zepp.spiskovi.repository.PosiljkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosiljkaServiceImpl implements PosiljkaService{

    private final PosiljkaRepository posiljkaRepository;

    @Autowired
    public PosiljkaServiceImpl(PosiljkaRepository posiljkaRepository) {
        this.posiljkaRepository = posiljkaRepository;
    }

    @Override
    public List<Posiljka> getPosiljka() {
        return (List<Posiljka>) posiljkaRepository.findAll();
    }

    @Override
    public Optional<Posiljka> findById(int posiljkaId) {
        Optional<Posiljka> posiljka = posiljkaRepository.findById(posiljkaId);
        if(!posiljka.isPresent()){
            //throw new NotFoundException("Posiljka sa id-jem " + posiljkaId + " nije pronađena!!!");
            //ovde treba implementirati message ewindow!!!!!!!!!!!!!!!!!
        }
        return posiljka;
    }

    @Override
    public void delete(int posiljkaId) {
        posiljkaRepository.deleteById(posiljkaId);
    }

    @Override
    public Posiljka savePosiljka(Posiljka posiljka) {
        return posiljkaRepository.save(posiljka);
    }

    @Override
    public List<Object[]> spiskoviFinal() {
        return posiljkaRepository.spiskoviFinal();
    }
}
