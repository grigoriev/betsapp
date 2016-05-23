package eu.grigoriev.controller.data.loader;

import eu.grigoriev.constants.Cups;
import eu.grigoriev.constants.Matches;
import eu.grigoriev.constants.Teams;
import eu.grigoriev.controller.AbstractController;
import eu.grigoriev.model.response.general.Response;
import eu.grigoriev.model.response.general.SuccessResponse;
import eu.grigoriev.persistence.entity.*;
import eu.grigoriev.persistence.service.*;
import eu.grigoriev.utils.mapping.Mapping;
import eu.grigoriev.utils.security.SecurityRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(Mapping.INITIAL_DATA.ROOT)
@PreAuthorize(SecurityRules.ALLOWED_FOR_ADMIN_ROLE)
public class InitialDataController extends AbstractController {

    @Autowired
    CupsRepository cupsRepository;

    @Autowired
    CupStagesRepository cupStagesRepository;

    @Autowired
    CupMenuItemsRepository cupMenuItemsRepository;

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

    @Autowired
    UsersRepository usersRepository;

    private CupEntity cupWc2014;
    private CupEntity cupEuro2016;
    private TeamTypeEntity teamTypeNational;
    private TeamTypeEntity teamTypeClub;
    private MatchTypeEntity matchTypeStandard;
    private MatchTypeEntity matchTypeAet;
    private MatchTypeEntity matchTypePenalty;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index() {
        return "data/loader";
    }

    @RequestMapping(value = Mapping.INITIAL_DATA.ALL, method = {RequestMethod.GET, RequestMethod.HEAD})
    @ResponseBody
    public Response all() {
        clear();
        types();
        cups();
        cupsForUsers();
        teamsNational();
        teamsClub();
        matchesWc2014();
        groupsWc2014();
        matchesEuro2016();
        groupsEuro2016();

        return new SuccessResponse();
    }

    public void clear() {
        List<CupEntity> cupEntities = cupsRepository.findAll();
        for (CupEntity cupEntity : cupEntities) {
            List<TeamEntity> teamEntities = cupEntity.getTeamEntities();
            if (teamEntities != null) {
                teamEntities.clear();
            }
            cupsRepository.update(cupEntity);
        }
        cupsRepository.deleteAll();
        cupStagesRepository.deleteAll();
        cupMenuItemsRepository.deleteAll();
        matchesRepository.deleteAll();
        groupsRepository.deleteAll();
        teamsRepository.deleteAll();
        teamTypesRepository.deleteAll();
        matchTypesRepository.deleteAll();

        cupsRepository.resetAutoincrement();
        cupMenuItemsRepository.resetAutoincrement();
        cupStagesRepository.resetAutoincrement();
        matchesRepository.resetAutoincrement();
        groupsRepository.resetAutoincrement();
        teamsRepository.resetAutoincrement();
        teamTypesRepository.resetAutoincrement();
        matchTypesRepository.resetAutoincrement();
    }

    public void cups() {
        cupWc2014 = cupsRepository.findById(cupsRepository.save(new CupEntity(Cups.WC2014.NAME, Cups.WC2014.DISPLAY_NAME)));
        cupEuro2016 = cupsRepository.findById(cupsRepository.save(new CupEntity(Cups.EURO2016.NAME, Cups.EURO2016.DISPLAY_NAME)));

        cupStagesRepository.save(new CupStageEntity(Cups.WC2014.MENU_ITEMS.GROUPS_STAGE.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.GROUPS_STAGE.URL, cupWc2014));
        cupStagesRepository.save(new CupStageEntity(Cups.WC2014.MENU_ITEMS.ROUND_OF_16.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.ROUND_OF_16.URL, cupWc2014));
        cupStagesRepository.save(new CupStageEntity(Cups.WC2014.MENU_ITEMS.QUARTER_FINAL.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.QUARTER_FINAL.URL, cupWc2014));
        cupStagesRepository.save(new CupStageEntity(Cups.WC2014.MENU_ITEMS.SEMI_FINAL.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.SEMI_FINAL.URL, cupWc2014));
        cupStagesRepository.save(new CupStageEntity(Cups.WC2014.MENU_ITEMS.FINAL.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.FINAL.URL, cupWc2014));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.WC2014.MENU_ITEMS.OTHERS.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.OTHERS.URL, cupWc2014));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.WC2014.MENU_ITEMS.SEPARATOR.NAME, "", cupWc2014));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.WC2014.MENU_ITEMS.SCORES.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.SCORES.URL, cupWc2014));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.WC2014.MENU_ITEMS.SEPARATOR.NAME, "", cupWc2014));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.WC2014.MENU_ITEMS.HELP.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.HELP.URL, cupWc2014));

        cupStagesRepository.save(new CupStageEntity(Cups.EURO2016.MENU_ITEMS.GROUPS_STAGE.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.GROUPS_STAGE.URL, cupEuro2016));
        cupStagesRepository.save(new CupStageEntity(Cups.EURO2016.MENU_ITEMS.ROUND_OF_16.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.ROUND_OF_16.URL, cupEuro2016));
        cupStagesRepository.save(new CupStageEntity(Cups.EURO2016.MENU_ITEMS.QUARTER_FINAL.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.QUARTER_FINAL.URL, cupEuro2016));
        cupStagesRepository.save(new CupStageEntity(Cups.EURO2016.MENU_ITEMS.SEMI_FINAL.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.SEMI_FINAL.URL, cupEuro2016));
        cupStagesRepository.save(new CupStageEntity(Cups.EURO2016.MENU_ITEMS.FINAL.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.FINAL.URL, cupEuro2016));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.EURO2016.MENU_ITEMS.OTHERS.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.OTHERS.URL, cupEuro2016));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.EURO2016.MENU_ITEMS.SEPARATOR.NAME, "", cupEuro2016));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.EURO2016.MENU_ITEMS.SCORES.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.SCORES.URL, cupEuro2016));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.EURO2016.MENU_ITEMS.SEPARATOR.NAME, "", cupEuro2016));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.EURO2016.MENU_ITEMS.HELP.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.HELP.URL, cupEuro2016));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.EURO2016.MENU_ITEMS.PARTICIPANTS.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.PARTICIPANTS.URL, cupEuro2016));
    }

    private void cupsForUsers() {
        UserEntity grigoriev = usersRepository.findByName("grigoriev");
        List<CupEntity> grigorievCups = new ArrayList<>();
        grigorievCups.add(cupWc2014);
        grigorievCups.add(cupEuro2016);
        grigoriev.setCupEntities(grigorievCups);
        usersRepository.update(grigoriev);

        UserEntity sg = usersRepository.findByName("sg");
        List<CupEntity> sgCups = new ArrayList<>();
        sgCups.add(cupWc2014);
        sg.setCupEntities(sgCups);
        usersRepository.update(sg);
    }

    public void types() {
        teamTypeNational = teamTypesRepository.findById(teamTypesRepository.save(new TeamTypeEntity(Teams.Types.NATIONAL)));
        teamTypeClub = teamTypesRepository.findById(teamTypesRepository.save(new TeamTypeEntity(Teams.Types.CLUB)));

        matchTypeStandard = matchTypesRepository.findById(matchTypesRepository.save(new MatchTypeEntity(Matches.Types.STANDARD)));
        matchTypeAet = matchTypesRepository.findById(matchTypesRepository.save(new MatchTypeEntity(Matches.Types.AET)));
        matchTypePenalty = matchTypesRepository.findById(matchTypesRepository.save(new MatchTypeEntity(Matches.Types.PENALTY)));
    }

    public void teamsNational() {
        TeamEntity albania = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ALBANIA, "", teamTypeNational)));
        TeamEntity algeria = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ALGERIA, "", teamTypeNational)));
        TeamEntity argentina = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ARGENTINA, "", teamTypeNational)));
        TeamEntity australia = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.AUSTRALIA, "", teamTypeNational)));
        TeamEntity austria = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.AUSTRIA, "", teamTypeNational)));
        TeamEntity belgium = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.BELGIUM, "", teamTypeNational)));
        TeamEntity bosniaAndHerzegovina = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.BOSNIA_AND_HERZEGOVINA, "", teamTypeNational)));
        TeamEntity brazil = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.BRAZIL, "", teamTypeNational)));
        TeamEntity cameroon = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.CAMEROON, "", teamTypeNational)));
        TeamEntity chile = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.CHILE, "", teamTypeNational)));
        TeamEntity colombia = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.COLOMBIA, "", teamTypeNational)));
        TeamEntity costaRica = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.COSTA_RICA, "", teamTypeNational)));
        TeamEntity croatia = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.CROATIA, "", teamTypeNational)));
        TeamEntity czechRepublic = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.CZECH_REPUBLIC, "", teamTypeNational)));
        TeamEntity ecuador = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ECUADOR, "", teamTypeNational)));
        TeamEntity england = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ENGLAND, "", teamTypeNational)));
        TeamEntity france = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.FRANCE, "", teamTypeNational)));
        TeamEntity germany = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.GERMANY, "", teamTypeNational)));
        TeamEntity ghana = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.GHANA, "", teamTypeNational)));
        TeamEntity greece = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.GREECE, "", teamTypeNational)));
        TeamEntity honduras = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.HONDURAS, "", teamTypeNational)));
        TeamEntity hungary = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.HUNGARY, "", teamTypeNational)));
        TeamEntity iceland = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ICELAND, "", teamTypeNational)));
        TeamEntity iran = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.IRAN, "", teamTypeNational)));
        TeamEntity ireland = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.IRELAND, "", teamTypeNational)));
        TeamEntity italy = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ITALY, "", teamTypeNational)));
        TeamEntity ivoryCoast = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.IVORY_COAST, "", teamTypeNational)));
        TeamEntity japan = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.JAPAN, "", teamTypeNational)));
        TeamEntity mexico = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.MEXICO, "", teamTypeNational)));
        TeamEntity netherlands = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.NETHERLANDS, "", teamTypeNational)));
        TeamEntity nigeria = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.NIGERIA, "", teamTypeNational)));
        TeamEntity northernIreland = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.NORTHERN_IRELAND, "", teamTypeNational)));
        TeamEntity poland = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.POLAND, "", teamTypeNational)));
        TeamEntity portugal = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.PORTUGAL, "", teamTypeNational)));
        TeamEntity romania = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ROMANIA, "", teamTypeNational)));
        TeamEntity russia = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.RUSSIA, "", teamTypeNational)));
        TeamEntity slovakia = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.SLOVAKIA, "", teamTypeNational)));
        TeamEntity southKorea = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.SOUTH_KOREA, "", teamTypeNational)));
        TeamEntity spain = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.SPAIN, "", teamTypeNational)));
        TeamEntity sweden = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.SWEDEN, "", teamTypeNational)));
        TeamEntity switzerland = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.SWITZERLAND, "", teamTypeNational)));
        TeamEntity turkey = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.TURKEY, "", teamTypeNational)));
        TeamEntity ukraine = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.UKRAINE, "", teamTypeNational)));
        TeamEntity uruguay = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.URUGUAY, "", teamTypeNational)));
        TeamEntity usa = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.USA, "", teamTypeNational)));
        TeamEntity wales = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.WALES, "", teamTypeNational)));

        List<TeamEntity> teamsWc2014 = new ArrayList<>();
        teamsWc2014.add(algeria);
        teamsWc2014.add(argentina);
        teamsWc2014.add(australia);
        teamsWc2014.add(belgium);
        teamsWc2014.add(bosniaAndHerzegovina);
        teamsWc2014.add(brazil);
        teamsWc2014.add(cameroon);
        teamsWc2014.add(chile);
        teamsWc2014.add(colombia);
        teamsWc2014.add(costaRica);
        teamsWc2014.add(croatia);
        teamsWc2014.add(ecuador);
        teamsWc2014.add(england);
        teamsWc2014.add(france);
        teamsWc2014.add(germany);
        teamsWc2014.add(ghana);
        teamsWc2014.add(greece);
        teamsWc2014.add(honduras);
        teamsWc2014.add(iran);
        teamsWc2014.add(italy);
        teamsWc2014.add(ivoryCoast);
        teamsWc2014.add(japan);
        teamsWc2014.add(netherlands);
        teamsWc2014.add(nigeria);
        teamsWc2014.add(mexico);
        teamsWc2014.add(portugal);
        teamsWc2014.add(russia);
        teamsWc2014.add(southKorea);
        teamsWc2014.add(spain);
        teamsWc2014.add(switzerland);
        teamsWc2014.add(uruguay);
        teamsWc2014.add(usa);
        cupWc2014.setTeamEntities(teamsWc2014);
        cupWc2014 = cupsRepository.update(cupWc2014);

        List<TeamEntity> teamsEuro2016 = new ArrayList<>();
        teamsEuro2016.add(albania);
        teamsEuro2016.add(austria);
        teamsEuro2016.add(belgium);
        teamsEuro2016.add(croatia);
        teamsEuro2016.add(czechRepublic);
        teamsEuro2016.add(england);
        teamsEuro2016.add(france);
        teamsEuro2016.add(germany);
        teamsEuro2016.add(hungary);
        teamsEuro2016.add(iceland);
        teamsEuro2016.add(italy);
        teamsEuro2016.add(ireland);
        teamsEuro2016.add(northernIreland);
        teamsEuro2016.add(poland);
        teamsEuro2016.add(portugal);
        teamsEuro2016.add(romania);
        teamsEuro2016.add(russia);
        teamsEuro2016.add(slovakia);
        teamsEuro2016.add(spain);
        teamsEuro2016.add(sweden);
        teamsEuro2016.add(switzerland);
        teamsEuro2016.add(turkey);
        teamsEuro2016.add(ukraine);
        teamsEuro2016.add(wales);
        cupEuro2016.setTeamEntities(teamsEuro2016);
        cupEuro2016 = cupsRepository.update(cupEuro2016);
    }

    public void teamsClub() {
        TeamEntity fcBarcelona = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.Club.FC_BARCELONA, "", teamTypeClub)));
        TeamEntity fcBayern = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.Club.FC_BAYERN, "", teamTypeClub)));
    }

    public void matchesWc2014() {
    }

    public void groupsWc2014() {
    }

    public void matchesEuro2016() {
    }

    public void groupsEuro2016() {
    }
}
