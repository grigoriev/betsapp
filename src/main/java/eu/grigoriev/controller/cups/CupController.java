package eu.grigoriev.controller.cups;

import eu.grigoriev.controller.AbstractController;
import eu.grigoriev.persistence.entity.CupEntity;
import eu.grigoriev.persistence.entity.CupStageEntity;
import eu.grigoriev.persistence.entity.MatchEntity;
import eu.grigoriev.persistence.service.CupStagesRepository;
import eu.grigoriev.persistence.service.CupsRepository;
import eu.grigoriev.persistence.service.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public abstract class CupController extends AbstractController {
    @Autowired
    CupsRepository cupsRepository;

    @Autowired
    CupStagesRepository cupStagesRepository;

    @Autowired
    MatchesRepository matchesRepository;

    protected CupEntity getCurrentCup(String cupName) {
        return cupsRepository.findByName(cupName);
    }

    protected List<MatchEntity> getMatchEntitiesForCupStage(CupEntity cupEntity, String stageName) {
        CupStageEntity stage = cupStagesRepository.findByCupAndName(cupEntity, stageName);

        return matchesRepository.findByCupAndStage(cupEntity, stage);
    }
}
