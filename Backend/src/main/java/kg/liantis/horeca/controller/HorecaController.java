package kg.liantis.horeca.controller;

import kg.liantis.horeca.domain.Horeca;
import kg.liantis.horeca.service.HorecaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/horeca")
public class HorecaController {

    private HorecaService horecaService;

    public HorecaController(HorecaService horecaService) {
        this.horecaService = horecaService;
    }

    @GetMapping({"","/all"})
    public List<Horeca> getAll() {
        try {
            return this.horecaService.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping({"/getBy"})
    public List<Horeca> getAllBy(@RequestParam String naam, @RequestParam String branche, @RequestParam String winkelgebied) throws Exception {
        try {
            List<Horeca> result = this.horecaService.findAllByCriteria(naam, branche, winkelgebied);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping({"/saveRating/{id}"})
    public Horeca saveRating(@RequestParam String rating, @RequestParam String id) throws Exception {
        try {
            return this.horecaService.saveRating(Long.valueOf(id), Integer.valueOf(rating));
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/branches")
    public Set<String> getAllBranches() {
        try {
            return this.horecaService.getBranches();
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/winkelgebieden")
    public Set<String> getAllWinkelgebieden() {
        try {
            return this.horecaService.getWinkelgebieden();
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/getPage")
    public Page<Horeca> getHorecaPage(Pageable pageable,
                                      @RequestParam String naam, @RequestParam String branche, @RequestParam String winkelgebied) {
        try {
            return this.horecaService.getHorecaPage(pageable, naam, branche, winkelgebied);
        } catch (Exception e) {
            throw e;
        }
    }
}
