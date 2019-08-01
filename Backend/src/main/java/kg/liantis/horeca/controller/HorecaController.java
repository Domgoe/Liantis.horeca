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

    @PostMapping({"/find"})
    public List<Horeca> findAllBy(@RequestBody Horeca horeca) throws Exception {
        List<Horeca> result = this.horecaService.findAllByCriteria(horeca.getNaam(), horeca.getBranche(), horeca.getWinkelgebied());
        return result;
    }

    @PostMapping({"/saveRating"})
    public Horeca saveRating(@RequestBody Horeca horeca) {
        return this.horecaService.saveRating(horeca);
    }

    @GetMapping("/branches")
    public Set<String> getAllBranches() {
        return this.horecaService.getBranches();
    }

    @GetMapping("/winkelgebieden")
    public Set<String> getAllWinkelgebieden() {
        return this.horecaService.getWinkelgebieden();
    }

    @GetMapping("/get")
    public Page<Horeca> getHorecaPage(Pageable pageable) {
        return this.horecaService.getHorecaPage(pageable);
    }
}
