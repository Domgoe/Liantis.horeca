package kg.liantis.horeca.service;

import kg.liantis.horeca.domain.Horeca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface HorecaService {

    List<Horeca> findAll();

    Horeca saveRating(Long id, int rating) throws Exception;

    Horeca save(Horeca horeca) throws Exception;

    List<Horeca> findAllByCriteria(String naam, String branche, String winkelgebied) throws Exception;

    Set<String> getBranches();

    Set<String> getWinkelgebieden();

    Page<Horeca> getHorecaPage(Pageable pageable);

}
