package com.zepp.spiskovi.repository;

import com.zepp.spiskovi.Common.PosiljkaStats;
import com.zepp.spiskovi.model.Posiljka;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PosiljkaRepository extends CrudRepository<Posiljka, Integer> {
    @Query(value="SELECT p.postanski_broj, p.rejon, COUNT(p.kolicina) FROM posiljka p GROUP BY p.postanski_broj, p.rejon ORDER BY p.postanski_broj, p.rejon", nativeQuery = true)
    //@Query(value="SELECT p.postanski_broj, p.rejon FROM posiljka p ORDER BY p.postanski_broj, p.rejon", nativeQuery = true)
    List<Object[]> spiskoviFinal();
}
