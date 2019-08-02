package kg.liantis.horeca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.liantis.horeca.domain.Horeca;
import kg.liantis.horeca.service.HorecaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class HorecaControllerTestIT {

    private static final ObjectMapper om = new ObjectMapper();

    @MockBean
    HorecaService horecaService;

    @Autowired
    private MockMvc mockMvc;

    List<Horeca> horecaTestList;
    Horeca h1;
    Horeca h2;

    @Before
    public void setUp() throws Exception {
        this.horecaTestList = new ArrayList<>();
        this.h1 = new Horeca();
        this.h1.setId(1L);
        this.h1.setNaam("Hotel Liantis");
        this.h1.setBranche("Hotel");
        this.h1.setWinkelgebied("Brugge");

        this.h2 = new Horeca();
        this.h2.setId(2L);
        this.h2.setNaam("Restaurant Liantis");
        this.h2.setBranche("Restaurant");
        this.h2.setWinkelgebied("Brugge");

        this.horecaTestList.add(h1);
        this.horecaTestList.add(h2);
    }

    @Test
    public void getAll_OK() throws Exception {
        when(this.horecaService.findAll()).thenReturn(this.horecaTestList);

        mockMvc.perform(get("/api/horeca/all"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].naam", is("Hotel Liantis")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].naam", is("Restaurant Liantis")));

        verify(this.horecaService, times(1)).findAll();

    }

    @Test
    public void getAllBy_OK() throws Exception {
        when(this.horecaService.findAllByCriteria(anyString(), anyString(), anyString())).thenReturn(this.horecaTestList);

        mockMvc.perform(get("/api/horeca/getBy")
                .param("naam", "")
                .param("branche", "")
                .param("winkelgebied", ""))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id", is(1)))
                    .andExpect(jsonPath("$[0].naam", is("Hotel Liantis")))
                    .andExpect(jsonPath("$[1].id", is(2)))
                    .andExpect(jsonPath("$[1].naam", is("Restaurant Liantis")));

        verify(this.horecaService, times(1)).findAllByCriteria(anyString(), anyString(), anyString());
    }

    @Test
    public void getAllByNaam_OK() throws Exception {
        List<Horeca> testList = new ArrayList<>();
        Horeca horeca = new Horeca();
        horeca.setId(1L);
        horeca.setNaam("Hotel Liantis");
        testList.add(horeca);

        when(this.horecaService.findAllByCriteria(eq("Hotel"), anyString(), anyString())).thenReturn(testList);

        mockMvc.perform(get("/api/horeca/getBy")
                .param("naam", "Hotel")
                .param("branche", "")
                .param("winkelgebied", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].naam", is("Hotel Liantis")));

        verify(this.horecaService, times(1)).findAllByCriteria(anyString(), anyString(), anyString());
    }

    @Test
    public void getAllByNaam_NoParam() throws Exception {
        List<Horeca> testList = new ArrayList<>();
        Horeca horeca = new Horeca();
        horeca.setId(1L);
        horeca.setNaam("Hotel Liantis");
        testList.add(horeca);

        when(this.horecaService.findAllByCriteria(anyString(), anyString(), anyString())).thenReturn(testList);

        mockMvc.perform(get("/api/horeca/getBy"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAllByNaamAndBranche_OK() throws Exception {
        List<Horeca> testList = new ArrayList<>();
        Horeca hor1 = new Horeca();
        hor1.setId(1L);
        hor1.setNaam("Hotel Liantis A");
        hor1.setBranche("Hotel");

        Horeca hor2 = new Horeca();
        hor2.setId(2L);
        hor2.setNaam("Hotel Liantis B");
        hor2.setBranche("Hotel");

        testList.add(hor1);
        testList.add(hor2);

        when(this.horecaService.findAllByCriteria(eq("Hotel"), eq("Hotel"), anyString())).thenReturn(testList);

        mockMvc.perform(get("/api/horeca/getBy")
                .param("naam", "Hotel")
                .param("branche", "Hotel")
                .param("winkelgebied", ""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].naam", is("Hotel Liantis A")))
                .andExpect(jsonPath("$[0].branche", is("Hotel")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].naam", is("Hotel Liantis B")))
                .andExpect(jsonPath("$[1].branche", is("Hotel")));

        verify(this.horecaService, times(1)).findAllByCriteria(anyString(), anyString(), anyString());
    }

    @Test
    public void getAllBranches_OK() throws Exception {
        Set<String> branchesTestList = new TreeSet<>();
        branchesTestList.add("Branche1");
        branchesTestList.add("Branche2");
        branchesTestList.add("Branche3");

        when(this.horecaService.getBranches()).thenReturn(branchesTestList);

        mockMvc.perform(get("/api/horeca/branches"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("Branche1")))
                .andExpect(jsonPath("$[1]", is("Branche2")))
                .andExpect(jsonPath("$[2]", is("Branche3")));

        verify(this.horecaService, times(1)).getBranches();
    }

    @Test
    public void getAllWinkelgebieden_Ok() throws Exception {
        Set<String> winkelgebiedenTestList = new TreeSet<>();
        winkelgebiedenTestList.add("Wgebied1");
        winkelgebiedenTestList.add("Wgebied2");
        winkelgebiedenTestList.add("Wgebied3");

        when(this.horecaService.getWinkelgebieden()).thenReturn(winkelgebiedenTestList);

        mockMvc.perform(get("/api/horeca/winkelgebieden"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", is("Wgebied1")))
                .andExpect(jsonPath("$[1]", is("Wgebied2")))
                .andExpect(jsonPath("$[2]", is("Wgebied3")));

        verify(this.horecaService, times(1)).getWinkelgebieden();
    }
}