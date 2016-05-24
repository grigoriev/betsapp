package eu.grigoriev.controller.cups;

import eu.grigoriev.constants.Cups;
import eu.grigoriev.controller.AbstractController;
import eu.grigoriev.persistence.entity.CupEntity;
import eu.grigoriev.persistence.entity.CupStageEntity;
import eu.grigoriev.persistence.entity.MatchEntity;
import eu.grigoriev.persistence.service.CupStagesRepository;
import eu.grigoriev.persistence.service.CupsRepository;
import eu.grigoriev.persistence.service.MatchesRepository;
import eu.grigoriev.utils.mapping.Mapping;
import eu.grigoriev.utils.security.SecurityRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(Mapping.WC2014.ROOT)
public class Wc2014Controller extends AbstractController {

    @Autowired
    CupsRepository cupsRepository;

    @Autowired
    CupStagesRepository cupStagesRepository;

    @Autowired
    MatchesRepository matchesRepository;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index() {
        return "cup/matches";
    }

    @RequestMapping(value = Mapping.WC2014.GROUP_STAGE, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String groupStage(
            ModelMap modelMap
    ) {
        CupEntity cupWc2014 = cupsRepository.findByName(Cups.WC2014.NAME);
        CupStageEntity stage = cupStagesRepository.findByName(Cups.WC2014.STAGES.GROUPS_STAGE.NAME);

        List<MatchEntity> matchEntities = matchesRepository.findByCupAndStage(cupWc2014, stage);
        modelMap.addAttribute("matches", matchEntities);

        return "cup/matches";
    }

    @RequestMapping(value = Mapping.WC2014.ROUND_OF_16, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String roundOf16(
            ModelMap modelMap
    ) {

        return "cup/matches";
    }

    @RequestMapping(value = Mapping.WC2014.QUARTER_FINAL, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String quarterFinal(
            ModelMap modelMap
    ) {

        return "cup/matches";
    }

    @RequestMapping(value = Mapping.WC2014.SEMI_FINAL, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String semiFinal(
            ModelMap modelMap
    ) {

        return "cup/matches";
    }

    @RequestMapping(value = Mapping.WC2014.FINAL, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String finalStage(
            ModelMap modelMap
    ) {

        return "cup/matches";
    }
}
