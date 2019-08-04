package kg.liantis.horeca.service;

import kg.liantis.horeca.domain.Horeca;
import kg.liantis.horeca.repository.HorecaRepository;
import kg.liantis.horeca.util.StringHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class HorecaServiceImpl implements HorecaService {

    private HorecaRepository horecaRepository;

    public HorecaServiceImpl(HorecaRepository horecaRepository) {
        this.horecaRepository = horecaRepository;
    }

    @Override
    public List<Horeca> findAll() {
        try {
            return this.horecaRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Horeca saveRating(Long id, int rating) throws Exception {
        Optional<Horeca> horecaOptional = this.horecaRepository.findById(id);

        if (!horecaOptional.isPresent())
            throw new EntityNotFoundException("Horeca-zaak niet gevonden");
        else
        {
            Horeca h = horecaOptional.get();
            if (rating >= 0 && rating < 6) {
                h.setRating(rating);
                return this.horecaRepository.save(h);
            } else {
                throw new Exception("De rating moet zich tussen 0 en 5 bevinden");
            }
        }
    }

    @Override
    public Horeca save(Horeca horeca) throws Exception {
        try {
            return this.horecaRepository.save(horeca);
        } catch (Exception e){
            throw e;
        }
     }

    //Client-side pagination
    @Override
    public List<Horeca> findAllByCriteria(String naam, String branche, String winkelgebied) throws Exception {
        List<Horeca> result = this.findAll()
                                    .stream()
                                    .filter(horeca -> horeca.getNaam().toLowerCase().contains(naam.toLowerCase()))
                                    .collect(Collectors.toList());

        if(!StringHelper.isNullOrEmpty(branche))
            result = result.stream()
                            .filter(horeca -> branche.toLowerCase().equals(horeca.getBranche().toLowerCase()))
                            .collect(Collectors.toList());
        if(!StringHelper.isNullOrEmpty(winkelgebied))
            result = result.stream()
                            .filter(horeca -> winkelgebied.toLowerCase().equals(horeca.getWinkelgebied().toLowerCase()))
                            .collect(Collectors.toList());

        return result;

     }

    @Override
    public Set<String> getBranches() {
        Set<String> branches = this.horecaRepository.findAll()
                                        .stream()
                                        .map(b -> b.getBranche())
                                        .collect(Collectors.toSet());
        return branches;
    }

    @Override
    public Set<String> getWinkelgebieden() {
        Set<String> winkelgebieden = this.horecaRepository.findAll()
                                        .stream()
                                        .map(w -> w.getWinkelgebied())
                                        .collect(Collectors.toSet());

        return winkelgebieden;
    }

    @Override
    public Page<Horeca> getHorecaPage(Pageable pageable, String naam, String branche, String winkelgebied) {
        //Zoeken op Naam containing (ook lege string), branche en winkelgebied
        if (!StringHelper.isNullOrEmpty(branche) && !StringHelper.isNullOrEmpty(winkelgebied)) {
            return this.horecaRepository.findByNaamContainingAndBrancheAndWinkelgebiedAllIgnoreCase(pageable, naam, branche, winkelgebied);
        }
        //Zoeken op Naam containing (ook lege string) en branche
        else if (!StringHelper.isNullOrEmpty(branche) && StringHelper.isNullOrEmpty(winkelgebied)){
            return this.horecaRepository.findByNaamContainingAndBrancheAllIgnoreCase(pageable, naam, branche);
        }
        //Zoeken op Naam containing (ook lege string) en winkelgebied
        else if(StringHelper.isNullOrEmpty(branche) && !StringHelper.isNullOrEmpty(winkelgebied)) {
            return this.horecaRepository.findByNaamContainingAndWinkelgebiedAllIgnoreCase(pageable, naam, winkelgebied);
        }
        //Zoeken op Naam containing (ook lege string)
        else {
            return this.horecaRepository.findByNaamContainingAllIgnoreCase(pageable, naam);
        }


    }

}
