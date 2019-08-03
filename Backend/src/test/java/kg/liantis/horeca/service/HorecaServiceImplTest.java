package kg.liantis.horeca.service;

import kg.liantis.horeca.domain.Horeca;
import kg.liantis.horeca.repository.HorecaRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HorecaServiceImplTest {

    HorecaServiceImpl horecaService;

    @Mock
    HorecaRepository horecaRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private List<Horeca> horecaList;
    private Horeca h1;
    private Horeca h2;
    private Horeca h3;
    private Horeca h4;

    private Pageable pageable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.horecaService = new HorecaServiceImpl(this.horecaRepository);

        this.horecaList = new ArrayList<>();

        this.h1 = new Horeca();
        this.h1.setId(1L);
        this.h1.setNaam("Hotel Liantis");
        this.h1.setBranche("Branche1");
        this.h1.setWinkelgebied("wgebied2");
        this.h1.setRating(0);

        this.h2 = new Horeca();
        this.h2.setId(2L);
        this.h2.setNaam("Café Liantis");
        this.h2.setBranche("Branche2");
        this.h2.setWinkelgebied("wgebied1");
        this.h2.setRating(0);

        this.h3 = new Horeca();
        this.h3.setId(3L);
        this.h3.setNaam("Restaurant Liantis");
        this.h3.setBranche("Branche2");
        this.h3.setWinkelgebied("wgebied1");
        this.h3.setRating(2);

        this.h4 = new Horeca();
        this.h4.setId(4L);
        this.h4.setNaam("De Gouden Stier");
        this.h4.setBranche("Café");
        this.h4.setWinkelgebied("Sint-Kruis");

        this.horecaList.add(h1);
        this.horecaList.add(h2);
        this.horecaList.add(h3);
        this.horecaList.add(h4);

        this.pageable = PageRequest.of(1, 3);
    }

    @Test
    public void findAll_OK() {

        when(this.horecaService.findAll()).thenReturn(horecaList);

        List<Horeca> testList = this.horecaService.findAll();

        assertEquals(testList.size(), 4);
        assertEquals(Long.valueOf(1), testList.get(0).getId());
        assertEquals("Hotel Liantis", testList.get(0).getNaam());
        assertEquals("Branche1", testList.get(0).getBranche());
        assertEquals("wgebied2", testList.get(0).getWinkelgebied());

        verify(this.horecaRepository, times(1)).findAll();
    }

    @Test
    public void saveRating_OK() throws Exception {
        //h3 starts with a rating of 2
        when(this.horecaRepository.findById(anyLong())).thenReturn(Optional.of(h3));

        when(this.horecaService.saveRating(anyLong(), 4)).thenReturn(h3);

        Horeca savedHoreca =  this.horecaService.saveRating(h3.getId(), 4);

        assertEquals(4, savedHoreca.getRating());

        verify(this.horecaRepository, times(1)).save(h3);
    }

    @Test
    public void saveRating_LargerThen5_NOK() throws Exception {

        thrown.expect(Exception.class);
        thrown.expectMessage(is("De rating moet zich tussen 0 en 5 bevinden"));

        when(this.horecaRepository.findById(anyLong())).thenReturn(Optional.of(h3));

        when(this.horecaService.saveRating(anyLong(), 6)).thenReturn(h3);

        Horeca savedHoreca =  this.horecaService.saveRating(h3.getId(), 6);
    }

    @Test
    public void saveRating_Lessthen0_NOK() throws Exception {

        thrown.expect(Exception.class);
        thrown.expectMessage(is("De rating moet zich tussen 0 en 5 bevinden"));

        when(this.horecaRepository.findById(anyLong())).thenReturn(Optional.of(h3));

        when(this.horecaService.saveRating(anyLong(), -1)).thenReturn(h3);

        Horeca savedHoreca =  this.horecaService.saveRating(h3.getId(), -1);
    }

    @Test
    public void save_OK() throws Exception {
        Horeca newHoreca = new Horeca();
        newHoreca.setId(4L);
        newHoreca.setNaam("De reisduif");

        when(this.horecaService.save(any())).thenReturn(newHoreca);

        Horeca savedHoreca = this.horecaService.save(newHoreca);

        assertEquals(Long.valueOf(4L), savedHoreca.getId());
        assertEquals("De reisduif", newHoreca.getNaam());
        verify(this.horecaRepository, times(1)).save(any());
    }



    /**TODO: BUG : when(this.horecaService.findAllByCriteria(anyString(), anyString(), anyString())).thenReturn(this.horecaList);
     * org.mockito.exceptions.misusing.InvalidUseOfMatchersException:
     * Invalid use of argument matchers!
     * 0 matchers expected, 3 recorded:
     * -> at kg.liantis.horeca.service.HorecaServiceImplTest.findAllByCriteria(HorecaServiceImplTest.java:94)
     * -> at kg.liantis.horeca.service.HorecaServiceImplTest.findAllByCriteria(HorecaServiceImplTest.java:94)
     * -> at kg.liantis.horeca.service.HorecaServiceImplTest.findAllByCriteria(HorecaServiceImplTest.java:94)
     *
     * This exception may occur if matchers are combined with raw values:
     *     //incorrect:
     *     someMethod(anyObject(), "raw String");
     * When using matchers, all arguments have to be provided by matchers.
     * For example:
     *     //correct:
     *     someMethod(anyObject(), eq("String by matcher"));
     */
    @Test
    public void findAllByNaam_OK() throws Exception {

        when(this.horecaService.findAll()).thenReturn(this.horecaList);

        List<Horeca> testList = this.horecaService.findAllByCriteria("hotel", "", "");

        assertEquals(1, testList.size());
        assertEquals(Long.valueOf(1) ,testList.get(0).getId());
        assertEquals("Hotel Liantis", testList.get(0).getNaam());
        assertEquals("Branche1", testList.get(0).getBranche());
        assertEquals("wgebied2", testList.get(0).getWinkelgebied());

        verify(this.horecaRepository, times(1)).findAll();
    }

    @Test
    public void findAllByBranche_OK() throws Exception {

        when(this.horecaService.findAll()).thenReturn(this.horecaList);

        List<Horeca> testList = this.horecaService.findAllByCriteria("", "Branche2", "");

        assertEquals(2, testList.size());
        assertEquals(Long.valueOf(2) ,testList.get(0).getId());
        assertEquals("Café Liantis", testList.get(0).getNaam());
        assertEquals("Branche2", testList.get(0).getBranche());
        assertEquals("wgebied1", testList.get(0).getWinkelgebied());

        verify(this.horecaRepository, times(1)).findAll();
    }

    @Test
    public void findAllByNaamAndBranche_OK() throws Exception {

        when(this.horecaService.findAll()).thenReturn(this.horecaList);

        List<Horeca> testList = this.horecaService.findAllByCriteria("Hotel", "Branche1", "");

        assertEquals(1, testList.size());
        assertEquals(Long.valueOf(1) ,testList.get(0).getId());
        assertEquals("Hotel Liantis", testList.get(0).getNaam());
        assertEquals("Branche1", testList.get(0).getBranche());
        assertEquals("wgebied2", testList.get(0).getWinkelgebied());

        verify(this.horecaRepository, times(1)).findAll();
    }

    @Test
    public void findAllByNaamAndBrancheAndWgebied_OK() throws Exception {

        when(this.horecaService.findAll()).thenReturn(this.horecaList);

        List<Horeca> testList = this.horecaService.findAllByCriteria("Restaurant Liantis", "Branche2", "wgebied1");

        assertEquals(1, testList.size());
        assertEquals(Long.valueOf(3) ,testList.get(0).getId());
        assertEquals("Restaurant Liantis", testList.get(0).getNaam());
        assertEquals("Branche2", testList.get(0).getBranche());
        assertEquals("wgebied1", testList.get(0).getWinkelgebied());

        verify(this.horecaRepository, times(1)).findAll();
    }

    @Test
    public void findAllByBrancheAndWGebied_OK() throws Exception {

        when(this.horecaService.findAll()).thenReturn(this.horecaList);

        List<Horeca> testList = this.horecaService.findAllByCriteria("", "Branche2", "wgebied1");

        assertEquals(2, testList.size());
        assertEquals(Long.valueOf(2) ,testList.get(0).getId());
        assertEquals("Café Liantis", testList.get(0).getNaam());
        assertEquals("Branche2", testList.get(0).getBranche());
        assertEquals("wgebied1", testList.get(0).getWinkelgebied());

        verify(this.horecaRepository, times(1)).findAll();
    }

    @Test
    public void findAllByNaamAndWGebied_OK() throws Exception {

        when(this.horecaService.findAll()).thenReturn(this.horecaList);

        List<Horeca> testList = this.horecaService.findAllByCriteria("Liantis", "", "wgebied1");

        assertEquals(2, testList.size());
        assertEquals(Long.valueOf(2) ,testList.get(0).getId());
        assertEquals("Café Liantis", testList.get(0).getNaam());
        assertEquals("Branche2", testList.get(0).getBranche());
        assertEquals("wgebied1", testList.get(0).getWinkelgebied());

        verify(this.horecaRepository, times(1)).findAll();
    }

    @Test
    public void findAllBy_OK() throws Exception {

        when(this.horecaService.findAll()).thenReturn(this.horecaList);

        List<Horeca> testList = this.horecaService.findAllByCriteria("", "", "");

        assertEquals(4, testList.size());
        assertEquals(Long.valueOf(1) ,testList.get(0).getId());
        assertEquals("Hotel Liantis", testList.get(0).getNaam());
        assertEquals("Branche1", testList.get(0).getBranche());
        assertEquals("wgebied2", testList.get(0).getWinkelgebied());

        verify(this.horecaRepository, times(1)).findAll();
    }

    @Test
    public void getBranches_OK() {
        when(this.horecaService.findAll()).thenReturn(this.horecaList);

        Set<String> testBranches = this.horecaService.getBranches();

        assertEquals(3, testBranches.size());
        assertEquals(true, testBranches.contains("Branche2"));
        verify(this.horecaRepository, times(1)).findAll();
    }

    @Test
    public void getWinkelgebieden_OK() {
        when(this.horecaService.findAll()).thenReturn(this.horecaList);

        Set<String> testWinkelgebieden = this.horecaService.getWinkelgebieden();

        assertEquals(3, testWinkelgebieden.size());
        assertEquals(true, testWinkelgebieden.contains("wgebied1"));
        verify(this.horecaRepository, times(1)).findAll();
    }


    @Test
    public void getHorecaPage_AllEmpty_OK() {
        List<Horeca> testList = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            Horeca h = new Horeca();
            h.setId(Long.valueOf(i));
            testList.add(h);
        }

        Page<Horeca> horecaPage = new PageImpl<>(testList, pageable, 3);

        when(this.horecaService.getHorecaPage(this.pageable, "", "", "")).thenReturn(horecaPage);

        Page<Horeca> testPage = this.horecaService.getHorecaPage(this.pageable, "", "", "");

        assertEquals(12, testPage.getTotalElements()); //We starten op pagina 1
        assertEquals(3, testPage.getSize()); // paginagrootte
        assertEquals(4, testPage.getTotalPages()); // 3 pagina's verwacht.
        assertEquals(Long.valueOf(3), testPage.getContent().get(2).getId()); //3de horecazaak
        assertEquals(1, testPage.getNumber()); // pagina 1

        //Repository alleen zoeken op naam wordt uitgevoerd
        verify(this.horecaRepository, times(1)).findByNaamContainingAllIgnoreCase(this.pageable, "");
        verifyNoMoreInteractions(this.horecaRepository);
    }

    @Test
    public void getHorecaPage_NaamAndBranche_OK() {
        List<Horeca> testList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Horeca h = new Horeca();
            h.setId(Long.valueOf(i));
            testList.add(h);
        }

        Page<Horeca> horecaPage = new PageImpl<>(testList, pageable, 3);

        when(this.horecaService.getHorecaPage(this.pageable, "Hotel", "Branche2", "")).thenReturn(horecaPage);

        Page<Horeca> testPage = this.horecaService.getHorecaPage(this.pageable, "Hotel", "Branche2", "");

        assertEquals(13, testPage.getTotalElements()); //We starten op pagina 1 (pagina 0: 3 items)
        assertEquals(3, testPage.getSize()); // paginagrootte
        assertEquals(5, testPage.getTotalPages()); // We starten op pagina 1 (pagina 0: 3 items)
        assertEquals(Long.valueOf(3), testPage.getContent().get(2).getId()); //3de horecazaak
        assertEquals(1, testPage.getNumber()); // pagina 1


        //Repository zoeken op naam en branche wordt uitgevoerd
        verify(this.horecaRepository, times(1)).findByNaamContainingAndBrancheAllIgnoreCase(this.pageable, "Hotel",  "Branche2");
        verifyNoMoreInteractions(this.horecaRepository);
    }

    @Test
    public void getHorecaPage_NaamAndWinkelgebied_OK() {
        List<Horeca> testList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Horeca h = new Horeca();
            h.setId(Long.valueOf(i));
            testList.add(h);
        }

        Page<Horeca> horecaPage = new PageImpl<>(testList, pageable, 3);

        when(this.horecaService.getHorecaPage(this.pageable, "Hotel", "", "Wgebied1")).thenReturn(horecaPage);

        Page<Horeca> testPage = this.horecaService.getHorecaPage(this.pageable, "Hotel", "", "Wgebied1");

        assertEquals(13, testPage.getTotalElements()); //We starten op pagina 1 (pagina 0: 3 items)
        assertEquals(3, testPage.getSize()); // paginagrootte
        assertEquals(5, testPage.getTotalPages()); // We starten op pagina 1 (pagina 0: 3 items)
        assertEquals(Long.valueOf(3), testPage.getContent().get(2).getId()); //3de horecazaak
        assertEquals(1, testPage.getNumber()); // pagina 1


        //Repository zoeken op naam en branche wordt uitgevoerd
        verify(this.horecaRepository, times(1)).findByNaamContainingAndWinkelgebiedAllIgnoreCase(this.pageable, "Hotel",  "Wgebied1");
        verifyNoMoreInteractions(this.horecaRepository);
    }

    @Test
    public void getHorecaPage_NaamAndBrancheAndWinkelgebied_OK() {
        List<Horeca> testList = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {
            Horeca h = new Horeca();
            h.setId(Long.valueOf(i));
            testList.add(h);
        }

        Page<Horeca> horecaPage = new PageImpl<>(testList, pageable, 3);

        when(this.horecaService.getHorecaPage(this.pageable, "Hotel", "Branche2", "Wgebied1")).thenReturn(horecaPage);

        Page<Horeca> testPage = this.horecaService.getHorecaPage(this.pageable, "Hotel", "Branche2", "Wgebied1");

        assertEquals(18, testPage.getTotalElements()); //We starten op pagina 1 (pagina 0: 3 items)
        assertEquals(3, testPage.getSize()); // paginagrootte
        assertEquals(6, testPage.getTotalPages()); // We starten op pagina 1 (pagina 0: 3 items)
        assertEquals(Long.valueOf(3), testPage.getContent().get(2).getId()); //3de horecazaak
        assertEquals(1, testPage.getNumber()); // pagina 1


        //Repository zoeken op naam, branche en winkelgebied wordt uitgevoerd
        verify(this.horecaRepository, times(1))
                .findByNaamContainingAndBrancheAndWinkelgebiedAllIgnoreCase(this.pageable, "Hotel",  "Branche2", "Wgebied1");
        verifyNoMoreInteractions(this.horecaRepository);
    }
}