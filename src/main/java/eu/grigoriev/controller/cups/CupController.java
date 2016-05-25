package eu.grigoriev.controller.cups;

import eu.grigoriev.controller.AbstractController;
import eu.grigoriev.persistence.entity.CupEntity;
import eu.grigoriev.persistence.entity.CupStageEntity;
import eu.grigoriev.persistence.entity.MatchEntity;
import eu.grigoriev.persistence.service.CupStagesRepository;
import eu.grigoriev.persistence.service.CupsRepository;
import eu.grigoriev.persistence.service.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class CupController extends AbstractController {
    @Autowired
    CupsRepository cupsRepository;

    @Autowired
    CupStagesRepository cupStagesRepository;

    @Autowired
    MatchesRepository matchesRepository;

    protected List<MatchEntity> getMatchEntitiesForCupStage(String cupName, String stageName) {
        CupEntity cupWc2014 = cupsRepository.findByName(cupName);
        CupStageEntity stage = cupStagesRepository.findByCupAndName(cupWc2014, stageName);

        return matchesRepository.findByCupAndStage(cupWc2014, stage);
    }
}
