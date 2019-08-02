package kg.liantis.horeca.service;

import kg.liantis.horeca.domain.Horeca;
import kg.liantis.horeca.repository.HorecaRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        horecaList.add(h1);
        horecaList.add(h2);
        horecaList.add(h3);
    }

    @Test
    public void findAll_OK() {

        when(this.horecaService.findAll()).thenReturn(horecaList);

        List<Horeca> testList = this.horecaService.findAll();

        assertEquals(testList.size(), 3);
        verify(this.horecaRepository, times(1)).findAll();
    }

    @Test
    public void saveRating_OK() throws Exception {
        //h3 starts with a rating of 2
        when(this.horecaRepository.findById(anyLong())).thenReturn(Optional.of(h3));

        when(this.horecaService.saveRating(anyLong(), 4)).thenReturn(h3);

        Horeca savedHoreca =  this.horecaService.saveRating(h3.getId(), 4);

        assertEquals(4, savedHoreca.getRating());
    }

    @Test
    public void saveRating_LargerThen5() throws Exception {

        thrown.expect(Exception.class);
        thrown.expectMessage(is("De rating moet zich tussen 0 en 5 bevinden"));

        when(this.horecaRepository.findById(anyLong())).thenReturn(Optional.of(h3));

        when(this.horecaService.saveRating(anyLong(), 6)).thenReturn(h3);

        Horeca savedHoreca =  this.horecaService.saveRating(h3.getId(), 6);
    }

    @Test
    public void saveRating_Lessthen0() throws Exception {

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

        when(this.horecaService.save(any())).thenReturn(newHoreca);

        Horeca savedHoreca = this.horecaService.save(newHoreca);

        assertEquals(Long.valueOf(4L), savedHoreca.getId());
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
    public void findAllByCriteria_OK() throws Exception {

        when(this.horecaService.findAll()).thenReturn(this.horecaList);

        List<Horeca> testList1 = this.horecaService.findAllByCriteria("hotel", "", "");
        List<Horeca> testList2 = this.horecaService.findAllByCriteria("liantis", "", "");
        List<Horeca> testList3 = this.horecaService.findAllByCriteria("liantis", "branche1", "wgebied1");
        List<Horeca> testList4 = this.horecaService.findAllByCriteria("", "", "");

        assertEquals(testList1.size(), 1);
        assertEquals(testList2.size(), 3);
        assertEquals(testList3.size(), 0);
        assertEquals(testList4.size(), 3);
    }

    @Test
    public void getBranches_OK() {
        when(this.horecaService.findAll()).thenReturn(this.horecaList);

        Set<String> testBranches = this.horecaService.getBranches();

        assertEquals(testBranches.size(), 2);
        verify(this.horecaRepository, times(1)).findAll();
    }

    @Test
    public void getWinkelgebieden_OK() {
        when(this.horecaService.findAll()).thenReturn(this.horecaList);

        Set<String> testWinkelgebieden = this.horecaService.getWinkelgebieden();

        assertEquals(testWinkelgebieden.size(), 2);
        verify(this.horecaRepository, times(1)).findAll();
    }

    @Test
    public void getHorecaPage() {
    }


}