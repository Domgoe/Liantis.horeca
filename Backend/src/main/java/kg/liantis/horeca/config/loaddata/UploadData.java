package kg.liantis.horeca.config.loaddata;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.liantis.horeca.domain.Coordinate;
import kg.liantis.horeca.domain.Geometry;
import kg.liantis.horeca.domain.Horeca;
import kg.liantis.horeca.service.HorecaService;
import kg.liantis.horeca.util.DateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;

@Component
public class UploadData implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(UploadData.class);
    private HorecaService horecaService;

    public UploadData(HorecaService horecaService) {
        this.horecaService = horecaService;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = ResourceUtils.getFile("classpath:jsondata/horeca.json");
            HorecaJsonModel[] horecaJsonModels = objectMapper.readValue(file, HorecaJsonModel[].class);
            for (HorecaJsonModel h: horecaJsonModels) {
                Coordinate coordinates = new Coordinate((Double)h.getGeometryJsonModel().coordinates.get(1),
                                                        (Double)h.getGeometryJsonModel().coordinates.get(0));

                Geometry geometry = new Geometry(h.getGeometryJsonModel().getType(), coordinates);

                Horeca horeca = new Horeca(h.getJson_featuretype(), h.getNaam(), h.getFormule(), h.getStraat(),
                                            h.getHuisNr(), h.getHuisNrToev(), h.getPostcode(), h.getDeelgemeente(), h.getGemeente(),
                                            h.getBranche(), DateFormatter.convertStringtoDate(h.getCheck_dat(), "yyyyMMdd"),
                                            h.getWinkelgebied(), h.getSubcentrum(), h.getStrnmEnHuisnr(),
                                            DateFormatter.convertStringtoDate(h.getAangemaakt_op(), "dd/MM/yyyy"),
                                            h.getJson_ogc_wkt_crs(), geometry);

                this.horecaService.save(horeca);

            }
            logger.info("Data successfully loaded");
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
