package kg.liantis.horeca.repository;

import kg.liantis.horeca.domain.Horeca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorecaRepository extends JpaRepository<Horeca, Long> {



}
