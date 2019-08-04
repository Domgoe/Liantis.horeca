package kg.liantis.horeca.repository;

import kg.liantis.horeca.domain.Horeca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorecaRepository extends JpaRepository<Horeca, Long> {

    Page<Horeca> findByNaamContainingAndBrancheAndWinkelgebiedAllIgnoreCase(Pageable pageable, String naam, String branche, String winkelgebied);
    Page<Horeca> findByNaamContainingAndBrancheAllIgnoreCase(Pageable pageable, String naam, String branche);
    Page<Horeca> findByNaamContainingAndWinkelgebiedAllIgnoreCase(Pageable pageable, String naam, String winkelgebied);
    Page<Horeca> findByNaamContainingAllIgnoreCase(Pageable pageable, String naam);


}
