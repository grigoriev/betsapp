package eu.grigoriev.controller.data.loader;

import eu.grigoriev.constants.Cups;
import eu.grigoriev.constants.Matches;
import eu.grigoriev.constants.Teams;
import eu.grigoriev.controller.AbstractController;
import eu.grigoriev.model.response.general.Response;
import eu.grigoriev.model.response.general.SuccessResponse;
import eu.grigoriev.persistence.entity.CupEntity;
import eu.grigoriev.persistence.entity.MatchTypeEntity;
import eu.grigoriev.persistence.entity.TeamEntity;
import eu.grigoriev.persistence.entity.TeamTypeEntity;
import eu.grigoriev.persistence.service.*;
import eu.grigoriev.utils.mapping.Mapping;
import eu.grigoriev.utils.security.SecurityRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(Mapping.DATA_LOADER.ROOT)
@PreAuthorize(SecurityRules.ALLOWED_FOR_ADMIN_ROLE)
public class DataLoaderController extends AbstractController {

    @Autowired
    CupsRepository cupsRepository;

    @Autowired
    TeamTypesRepository teamTypesRepository;

    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    MatchTypesRepository matchTypesRepository;

    @Autowired
    MatchesRepository matchesRepository;

    @Autowired
    GroupsRepository groupsRepository;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index() {
        return "data/loader";
    }

    @RequestMapping(value = Mapping.DATA_LOADER.ALL, method = {RequestMethod.GET, RequestMethod.HEAD})
    public Response all() {
        clear();
        types();
        teamsNational();
        teamsClub();
        cups();
        matchesWc2014();
        groupsWc2014();
        matchesEuro2016();
        groupsEuro2016();

        return new SuccessResponse();
    }

    @RequestMapping(value = Mapping.DATA_LOADER.CLEAR, method = {RequestMethod.GET, RequestMethod.HEAD})
    public void clear() {
        matchesRepository.deleteAll();
        groupsRepository.deleteAll();
        teamsRepository.deleteAll();
        teamTypesRepository.deleteAll();
        matchTypesRepository.deleteAll();
        cupsRepository.deleteAll();
    }

    @RequestMapping(value = Mapping.DATA_LOADER.CUPS, method = {RequestMethod.GET, RequestMethod.HEAD})
    public void cups() {
        cupsRepository.save(new CupEntity(Cups.WC2014));
        cupsRepository.save(new CupEntity(Cups.EURO2016));
    }

    @RequestMapping(value = Mapping.DATA_LOADER.TYPES, method = {RequestMethod.GET, RequestMethod.HEAD})
    public void types() {
        teamTypesRepository.save(new TeamTypeEntity(Teams.Types.NATIONAL));
        teamTypesRepository.save(new TeamTypeEntity(Teams.Types.CLUB));

        matchTypesRepository.save(new MatchTypeEntity(Matches.Types.STANDARD));
        matchTypesRepository.save(new MatchTypeEntity(Matches.Types.AET));
        matchTypesRepository.save(new MatchTypeEntity(Matches.Types.PENALTY));
    }

    @RequestMapping(value = Mapping.DATA_LOADER.TEAMS_NATIONAL, method = {RequestMethod.GET, RequestMethod.HEAD})
    public void teamsNational() {
        TeamTypeEntity pkTeamTypeNational = teamTypesRepository.findByType(Teams.Types.NATIONAL);

        teamsRepository.save(new TeamEntity(Teams.National.ALBANIA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.ALGERIA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.ARGENTINA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.AUSTRALIA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.AUSTRIA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.BELGIUM, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.BOSNIA_AND_HERZEGOVINA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.BRAZIL, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.CAMEROON, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.CHILE, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.COLOMBIA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.COSTA_RICA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.CROATIA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.CZECH_REPUBLIC, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.ECUADOR, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.ENGLAND, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.FRANCE, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.GERMANY, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.GHANA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.GREECE, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.HONDURAS, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.HUNGARY, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.ICELAND, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.IRAN, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.IRELAND, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.ITALY, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.IVORY_COAST, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.JAPAN, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.MEXICO, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.NETHERLANDS, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.NIGERIA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.NORTHERN_IRELAND, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.POLAND, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.PORTUGAL, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.ROMANIA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.RUSSIA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.SLOVAKIA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.SOUTH_KOREA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.SPAIN, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.SWEDEN, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.SWITZERLAND, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.TURKEY, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.UKRAINE, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.URUGUAY, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.USA, "", pkTeamTypeNational));
        teamsRepository.save(new TeamEntity(Teams.National.WALES, "", pkTeamTypeNational));
    }

    @RequestMapping(value = Mapping.DATA_LOADER.TEAMS_CLUB, method = {RequestMethod.GET, RequestMethod.HEAD})
    public void teamsClub() {
        TeamTypeEntity pkTeamTypeClub = teamTypesRepository.findByType(Teams.Types.CLUB);

        teamsRepository.save(new TeamEntity("FC Bayern", "", pkTeamTypeClub));
    }

    @RequestMapping(value = Mapping.DATA_LOADER.MATCHES_WC2014, method = {RequestMethod.GET, RequestMethod.HEAD})
    public void matchesWc2014() {
        MatchTypeEntity pkMatchTypeStandard = matchTypesRepository.findByType(Matches.Types.STANDARD);
        MatchTypeEntity pkMatchTypeAet = matchTypesRepository.findByType(Matches.Types.AET);
        MatchTypeEntity pkMatchTypePenalty = matchTypesRepository.findByType(Matches.Types.PENALTY);
    }

    @RequestMapping(value = Mapping.DATA_LOADER.GROUPS_WC2014, method = {RequestMethod.GET, RequestMethod.HEAD})
    public void groupsWc2014() {
    }

    @RequestMapping(value = Mapping.DATA_LOADER.MATCHES_EURO2016, method = {RequestMethod.GET, RequestMethod.HEAD})
    public void matchesEuro2016() {
        MatchTypeEntity pkMatchTypeStandard = matchTypesRepository.findByType(Matches.Types.STANDARD);
        MatchTypeEntity pkMatchTypeAet = matchTypesRepository.findByType(Matches.Types.AET);
        MatchTypeEntity pkMatchTypePenalty = matchTypesRepository.findByType(Matches.Types.PENALTY);
    }

    @RequestMapping(value = Mapping.DATA_LOADER.GROUPS_EURO2016, method = {RequestMethod.GET, RequestMethod.HEAD})
    public void groupsEuro2016() {
    }
}
