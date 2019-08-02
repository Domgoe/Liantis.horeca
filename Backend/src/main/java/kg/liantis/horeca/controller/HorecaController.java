package kg.liantis.horeca.controller;

import kg.liantis.horeca.domain.Horeca;
import kg.liantis.horeca.service.HorecaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/horeca")
public class HorecaController {

    private HorecaService horecaService;

    public HorecaController(HorecaService horecaService) {
        this.horecaService = horecaService;
    }

    @GetMapping({"","/all"})
    public List<Horeca> getAll() {
        return this.horecaService.findAll();
    }

    @GetMapping({"/getBy"})
    public List<Horeca> getAllBy(@RequestParam String naam, @RequestParam String branche, @RequestParam String winkelgebied) throws Exception {
        List<Horeca> result = this.horecaService.findAllByCriteria(naam, branche, winkelgebied);
        return result;
    }

    @PutMapping({"/saveRating/{id}"})
    public Horeca saveRating(@RequestParam String rating, @RequestParam String id) throws Exception {
        return this.horecaService.saveRating(Long.valueOf(id), Integer.valueOf(rating));
    }

    @GetMapping("/branches")
    public Set<String> getAllBranches() {
        return this.horecaService.getBranches();
    }

    @GetMapping("/winkelgebieden")
    public Set<String> getAllWinkelgebieden() {
        return this.horecaService.getWinkelgebieden();
    }

    @GetMapping("/getPage")
    public Page<Horeca> getHorecaPage(Pageable pageable,
                                      @RequestParam String naam, @RequestParam String branche, @RequestParam String winkelgebied) {
        return this.horecaService.getHorecaPage(pageable, naam, branche, winkelgebied);
    }
}
