package eu.grigoriev.controller.cups;

import eu.grigoriev.constants.Cups;
import eu.grigoriev.persistence.entity.MatchEntity;
import eu.grigoriev.utils.mapping.Mapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(Mapping.EURO2016.ROOT)
public class Euro2016Controller extends CupController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index() {
        return "redirect:" + Mapping.EURO2016.ROOT + Mapping.EURO2016.GROUP_STAGE;
    }

    @RequestMapping(value = Mapping.EURO2016.GROUP_STAGE, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String groupStage(
            ModelMap modelMap
    ) {
        List<MatchEntity> matchEntities = getMatchEntitiesForCupStage(Cups.EURO2016.NAME, Cups.EURO2016.STAGES.GROUPS_STAGE.NAME);
        modelMap.addAttribute("matches", matchEntities);

        return "cup/matches";
    }

    @RequestMapping(value = Mapping.EURO2016.ROUND_OF_16, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String roundOf16(
            ModelMap modelMap
    ) {
        List<MatchEntity> matchEntities = getMatchEntitiesForCupStage(Cups.EURO2016.NAME, Cups.EURO2016.STAGES.ROUND_OF_16.NAME);
        modelMap.addAttribute("matches", matchEntities);

        return "cup/matches";
    }

    @RequestMapping(value = Mapping.EURO2016.QUARTER_FINAL, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String quarterFinal(
            ModelMap modelMap
    ) {
        List<MatchEntity> matchEntities = getMatchEntitiesForCupStage(Cups.EURO2016.NAME, Cups.EURO2016.STAGES.QUARTER_FINAL.NAME);
        modelMap.addAttribute("matches", matchEntities);

        return "cup/matches";
    }

    @RequestMapping(value = Mapping.EURO2016.SEMI_FINAL, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String semiFinal(
            ModelMap modelMap
    ) {
        List<MatchEntity> matchEntities = getMatchEntitiesForCupStage(Cups.EURO2016.NAME, Cups.EURO2016.STAGES.SEMI_FINAL.NAME);
        modelMap.addAttribute("matches", matchEntities);

        return "cup/matches";
    }

    @RequestMapping(value = Mapping.EURO2016.FINAL, method = {RequestMethod.GET, RequestMethod.HEAD})
    public String finalStage(
            ModelMap modelMap
    ) {
        List<MatchEntity> matchEntities = getMatchEntitiesForCupStage(Cups.EURO2016.NAME, Cups.EURO2016.STAGES.FINAL.NAME);
        modelMap.addAttribute("matches", matchEntities);

        return "cup/matches";
    }
}
