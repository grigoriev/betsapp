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

import java.sql.Timestamp;
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

    private TeamEntity albania;
    private TeamEntity algeria;
    private TeamEntity argentina;
    private TeamEntity australia;
    private TeamEntity austria;
    private TeamEntity belgium;
    private TeamEntity bosniaAndHerzegovina;
    private TeamEntity brazil;
    private TeamEntity cameroon;
    private TeamEntity chile;
    private TeamEntity colombia;
    private TeamEntity costaRica;
    private TeamEntity croatia;
    private TeamEntity czechRepublic;
    private TeamEntity ecuador;
    private TeamEntity england;
    private TeamEntity france;
    private TeamEntity germany;
    private TeamEntity ghana;
    private TeamEntity greece;
    private TeamEntity honduras;
    private TeamEntity hungary;
    private TeamEntity iceland;
    private TeamEntity iran;
    private TeamEntity ireland;
    private TeamEntity italy;
    private TeamEntity ivoryCoast;
    private TeamEntity japan;
    private TeamEntity mexico;
    private TeamEntity netherlands;
    private TeamEntity nigeria;
    private TeamEntity northernIreland;
    private TeamEntity poland;
    private TeamEntity portugal;
    private TeamEntity romania;
    private TeamEntity russia;
    private TeamEntity slovakia;
    private TeamEntity southKorea;
    private TeamEntity spain;
    private TeamEntity sweden;
    private TeamEntity switzerland;
    private TeamEntity turkey;
    private TeamEntity ukraine;
    private TeamEntity uruguay;
    private TeamEntity usa;
    private TeamEntity wales;

    private CupStageEntity wc2014GroupStage;
    private CupStageEntity wc2014RoundOf16;
    private CupStageEntity wc2014QuarterFinal;
    private CupStageEntity wc2014SemiFinal;
    private CupStageEntity wc2014Final;
    private CupStageEntity euro2016GroupStage;
    private CupStageEntity euro2016RoundOf16;
    private CupStageEntity euro2016QuarterFinal;
    private CupStageEntity euro2016SemiFinal;
    private CupStageEntity euro2016Final;

    ArrayList<MatchEntity> wc2014Matches = new ArrayList<>();
    ArrayList<MatchEntity> euro2016Matches = new ArrayList<>();

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

        wc2014GroupStage = cupStagesRepository.findById(cupStagesRepository.save(new CupStageEntity(Cups.WC2014.MENU_ITEMS.GROUPS_STAGE.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.GROUPS_STAGE.URL, cupWc2014)));
        wc2014RoundOf16 = cupStagesRepository.findById(cupStagesRepository.save(new CupStageEntity(Cups.WC2014.MENU_ITEMS.ROUND_OF_16.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.ROUND_OF_16.URL, cupWc2014)));
        wc2014QuarterFinal = cupStagesRepository.findById(cupStagesRepository.save(new CupStageEntity(Cups.WC2014.MENU_ITEMS.QUARTER_FINAL.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.QUARTER_FINAL.URL, cupWc2014)));
        wc2014SemiFinal = cupStagesRepository.findById(cupStagesRepository.save(new CupStageEntity(Cups.WC2014.MENU_ITEMS.SEMI_FINAL.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.SEMI_FINAL.URL, cupWc2014)));
        wc2014Final = cupStagesRepository.findById(cupStagesRepository.save(new CupStageEntity(Cups.WC2014.MENU_ITEMS.FINAL.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.FINAL.URL, cupWc2014)));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.WC2014.MENU_ITEMS.OTHERS.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.OTHERS.URL, cupWc2014));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.WC2014.MENU_ITEMS.SEPARATOR.NAME, "", cupWc2014));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.WC2014.MENU_ITEMS.SCORES.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.SCORES.URL, cupWc2014));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.WC2014.MENU_ITEMS.SEPARATOR.NAME, "", cupWc2014));
        cupMenuItemsRepository.save(new CupMenuItemEntity(Cups.WC2014.MENU_ITEMS.HELP.NAME, Cups.WC2014.URL + Cups.WC2014.MENU_ITEMS.HELP.URL, cupWc2014));

        euro2016GroupStage = cupStagesRepository.findById(cupStagesRepository.save(new CupStageEntity(Cups.EURO2016.MENU_ITEMS.GROUPS_STAGE.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.GROUPS_STAGE.URL, cupEuro2016)));
        euro2016RoundOf16 = cupStagesRepository.findById(cupStagesRepository.save(new CupStageEntity(Cups.EURO2016.MENU_ITEMS.ROUND_OF_16.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.ROUND_OF_16.URL, cupEuro2016)));
        euro2016QuarterFinal = cupStagesRepository.findById(cupStagesRepository.save(new CupStageEntity(Cups.EURO2016.MENU_ITEMS.QUARTER_FINAL.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.QUARTER_FINAL.URL, cupEuro2016)));
        euro2016SemiFinal = cupStagesRepository.findById(cupStagesRepository.save(new CupStageEntity(Cups.EURO2016.MENU_ITEMS.SEMI_FINAL.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.SEMI_FINAL.URL, cupEuro2016)));
        euro2016Final = cupStagesRepository.findById(cupStagesRepository.save(new CupStageEntity(Cups.EURO2016.MENU_ITEMS.FINAL.NAME, Cups.EURO2016.URL + Cups.EURO2016.MENU_ITEMS.FINAL.URL, cupEuro2016)));
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
        albania = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ALBANIA, "", teamTypeNational)));
        algeria = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ALGERIA, "", teamTypeNational)));
        argentina = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ARGENTINA, "", teamTypeNational)));
        australia = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.AUSTRALIA, "", teamTypeNational)));
        austria = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.AUSTRIA, "", teamTypeNational)));
        belgium = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.BELGIUM, "", teamTypeNational)));
        bosniaAndHerzegovina = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.BOSNIA_AND_HERZEGOVINA, "", teamTypeNational)));
        brazil = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.BRAZIL, "", teamTypeNational)));
        cameroon = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.CAMEROON, "", teamTypeNational)));
        chile = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.CHILE, "", teamTypeNational)));
        colombia = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.COLOMBIA, "", teamTypeNational)));
        costaRica = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.COSTA_RICA, "", teamTypeNational)));
        croatia = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.CROATIA, "", teamTypeNational)));
        czechRepublic = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.CZECH_REPUBLIC, "", teamTypeNational)));
        ecuador = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ECUADOR, "", teamTypeNational)));
        england = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ENGLAND, "", teamTypeNational)));
        france = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.FRANCE, "", teamTypeNational)));
        germany = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.GERMANY, "", teamTypeNational)));
        ghana = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.GHANA, "", teamTypeNational)));
        greece = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.GREECE, "", teamTypeNational)));
        honduras = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.HONDURAS, "", teamTypeNational)));
        hungary = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.HUNGARY, "", teamTypeNational)));
        iceland = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ICELAND, "", teamTypeNational)));
        iran = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.IRAN, "", teamTypeNational)));
        ireland = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.IRELAND, "", teamTypeNational)));
        italy = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ITALY, "", teamTypeNational)));
        ivoryCoast = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.IVORY_COAST, "", teamTypeNational)));
        japan = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.JAPAN, "", teamTypeNational)));
        mexico = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.MEXICO, "", teamTypeNational)));
        netherlands = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.NETHERLANDS, "", teamTypeNational)));
        nigeria = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.NIGERIA, "", teamTypeNational)));
        northernIreland = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.NORTHERN_IRELAND, "", teamTypeNational)));
        poland = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.POLAND, "", teamTypeNational)));
        portugal = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.PORTUGAL, "", teamTypeNational)));
        romania = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.ROMANIA, "", teamTypeNational)));
        russia = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.RUSSIA, "", teamTypeNational)));
        slovakia = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.SLOVAKIA, "", teamTypeNational)));
        southKorea = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.SOUTH_KOREA, "", teamTypeNational)));
        spain = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.SPAIN, "", teamTypeNational)));
        sweden = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.SWEDEN, "", teamTypeNational)));
        switzerland = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.SWITZERLAND, "", teamTypeNational)));
        turkey = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.TURKEY, "", teamTypeNational)));
        ukraine = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.UKRAINE, "", teamTypeNational)));
        uruguay = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.URUGUAY, "", teamTypeNational)));
        usa = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.USA, "", teamTypeNational)));
        wales = teamsRepository.findById(teamsRepository.save(new TeamEntity(Teams.National.WALES, "", teamTypeNational)));

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
        int serialNumber = 1;

        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-12 20:00:00"), brazil, croatia, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-13 16:00:00"), mexico, cameroon, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-13 19:00:00"), spain, netherlands, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-13 22:00:00"), chile, australia, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-14 16:00:00"), colombia, greece, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-14 19:00:00"), uruguay, costaRica, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-14 22:00:00"), england, italy, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-15 01:00:00"), ivoryCoast, japan, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-15 16:00:00"), switzerland, ecuador, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-15 19:00:00"), france, honduras, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-15 22:00:00"), argentina, bosniaAndHerzegovina, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-16 16:00:00"), germany, portugal, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-16 19:00:00"), iran, nigeria, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-16 22:00:00"), ghana, usa, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-17 16:00:00"), belgium, algeria, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-17 19:00:00"), brazil, mexico, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-17 22:00:00"), russia, southKorea, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-18 16:00:00"), australia, netherlands, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-18 19:00:00"), spain, chile, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-18 22:00:00"), cameroon, croatia, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-19 16:00:00"), colombia, ivoryCoast, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-19 19:00:00"), uruguay, england, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-19 22:00:00"), japan, greece, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-20 16:00:00"), italy, costaRica, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-20 19:00:00"), switzerland, france, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-20 22:00:00"), honduras, ecuador, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-21 16:00:00"), argentina, iran, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-21 19:00:00"), germany, ghana, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-21 22:00:00"), nigeria, bosniaAndHerzegovina, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-22 16:00:00"), belgium, russia, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-22 19:00:00"), southKorea, algeria, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-22 22:00:00"), usa, portugal, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-23 16:00:00"), australia, spain, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-23 16:00:00"), netherlands, chile, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-23 20:00:00"), cameroon, brazil, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-23 20:00:00"), croatia, mexico, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-24 16:00:00"), italy, uruguay, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-24 16:00:00"), costaRica, england, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-24 20:00:00"), japan, colombia, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-24 20:00:00"), greece, ivoryCoast, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-25 16:00:00"), nigeria, argentina, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-25 16:00:00"), bosniaAndHerzegovina, iran, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-25 20:00:00"), honduras, switzerland, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-25 20:00:00"), ecuador, france, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-26 16:00:00"), usa, germany, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-26 16:00:00"), portugal, ghana, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-26 20:00:00"), southKorea, belgium, matchTypeStandard, wc2014GroupStage))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-26 20:00:00"), algeria, russia, matchTypeStandard, wc2014GroupStage))));

        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-28 16:00:00"), brazil, chile, matchTypePenalty, wc2014RoundOf16))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-28 20:00:00"), colombia, uruguay, matchTypeStandard, wc2014RoundOf16))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-29 16:00:00"), netherlands, mexico, matchTypeStandard, wc2014RoundOf16))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-29 20:00:00"), costaRica, greece, matchTypePenalty, wc2014RoundOf16))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-30 16:00:00"), france, nigeria, matchTypeStandard, wc2014RoundOf16))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-06-30 20:00:00"), germany, algeria, matchTypeAet, wc2014RoundOf16))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-07-01 16:00:00"), argentina, switzerland, matchTypeAet, wc2014RoundOf16))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-07-01 20:00:00"), belgium, usa, matchTypeAet, wc2014RoundOf16))));

        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-07-04 16:00:00"), france, germany, matchTypeStandard, wc2014QuarterFinal))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-07-04 20:00:00"), brazil, colombia, matchTypeStandard, wc2014QuarterFinal))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-07-05 16:00:00"), argentina, belgium, matchTypeStandard, wc2014QuarterFinal))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-07-05 20:00:00"), netherlands, costaRica, matchTypePenalty, wc2014QuarterFinal))));

        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-07-08 20:00:00"), brazil, germany, matchTypeStandard, wc2014SemiFinal))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-07-09 20:00:00"), netherlands, argentina, matchTypePenalty, wc2014SemiFinal))));

        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-07-12 20:00:00"), brazil, netherlands, matchTypeStandard, wc2014Final))));
        wc2014Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupWc2014, Timestamp.valueOf("2014-07-13 19:00:00"), germany, argentina, matchTypeAet, wc2014Final))));

        serialNumber = 0;
        (wc2014Matches.get(serialNumber++)).setResult(3, 1);
        (wc2014Matches.get(serialNumber++)).setResult(1, 0);
        (wc2014Matches.get(serialNumber++)).setResult(1, 5);
        (wc2014Matches.get(serialNumber++)).setResult(3, 1);
        (wc2014Matches.get(serialNumber++)).setResult(3, 0);
        (wc2014Matches.get(serialNumber++)).setResult(1, 3);
        (wc2014Matches.get(serialNumber++)).setResult(1, 2);
        (wc2014Matches.get(serialNumber++)).setResult(2, 1);
        (wc2014Matches.get(serialNumber++)).setResult(2, 1);
        (wc2014Matches.get(serialNumber++)).setResult(3, 0);
        (wc2014Matches.get(serialNumber++)).setResult(2, 1);
        (wc2014Matches.get(serialNumber++)).setResult(4, 0);
        (wc2014Matches.get(serialNumber++)).setResult(0, 0);
        (wc2014Matches.get(serialNumber++)).setResult(1, 2);
        (wc2014Matches.get(serialNumber++)).setResult(2, 1);
        (wc2014Matches.get(serialNumber++)).setResult(0, 0);
        (wc2014Matches.get(serialNumber++)).setResult(1, 1);
        (wc2014Matches.get(serialNumber++)).setResult(2, 3);
        (wc2014Matches.get(serialNumber++)).setResult(0, 2);
        (wc2014Matches.get(serialNumber++)).setResult(0, 4);
        (wc2014Matches.get(serialNumber++)).setResult(2, 1);
        (wc2014Matches.get(serialNumber++)).setResult(2, 1);
        (wc2014Matches.get(serialNumber++)).setResult(0, 0);
        (wc2014Matches.get(serialNumber++)).setResult(0, 1);
        (wc2014Matches.get(serialNumber++)).setResult(2, 5);
        (wc2014Matches.get(serialNumber++)).setResult(1, 2);
        (wc2014Matches.get(serialNumber++)).setResult(1, 0);
        (wc2014Matches.get(serialNumber++)).setResult(2, 2);
        (wc2014Matches.get(serialNumber++)).setResult(1, 0);
        (wc2014Matches.get(serialNumber++)).setResult(1, 0);
        (wc2014Matches.get(serialNumber++)).setResult(2, 4);
        (wc2014Matches.get(serialNumber++)).setResult(2, 2);
        (wc2014Matches.get(serialNumber++)).setResult(0, 3);
        (wc2014Matches.get(serialNumber++)).setResult(2, 0);
        (wc2014Matches.get(serialNumber++)).setResult(1, 4);
        (wc2014Matches.get(serialNumber++)).setResult(1, 3);
        (wc2014Matches.get(serialNumber++)).setResult(0, 1);
        (wc2014Matches.get(serialNumber++)).setResult(0, 0);
        (wc2014Matches.get(serialNumber++)).setResult(1, 4);
        (wc2014Matches.get(serialNumber++)).setResult(2, 1);
        (wc2014Matches.get(serialNumber++)).setResult(2, 3);
        (wc2014Matches.get(serialNumber++)).setResult(3, 1);
        (wc2014Matches.get(serialNumber++)).setResult(0, 3);
        (wc2014Matches.get(serialNumber++)).setResult(0, 0);
        (wc2014Matches.get(serialNumber++)).setResult(0, 1);
        (wc2014Matches.get(serialNumber++)).setResult(2, 1);
        (wc2014Matches.get(serialNumber++)).setResult(0, 1);
        (wc2014Matches.get(serialNumber++)).setResult(1, 1);

        (wc2014Matches.get(serialNumber++)).setResult(1, 1, 1, 1, 3, 2);
        (wc2014Matches.get(serialNumber++)).setResult(2, 0);
        (wc2014Matches.get(serialNumber++)).setResult(2, 1);
        (wc2014Matches.get(serialNumber++)).setResult(1, 1, 1, 1, 5, 3);
        (wc2014Matches.get(serialNumber++)).setResult(2, 0);
        (wc2014Matches.get(serialNumber++)).setResult(0, 0, 2, 1);
        (wc2014Matches.get(serialNumber++)).setResult(0, 0, 1, 0);
        (wc2014Matches.get(serialNumber++)).setResult(2, 1);

        (wc2014Matches.get(serialNumber++)).setResult(2, 1);
        (wc2014Matches.get(serialNumber++)).setResult(0, 1);
        (wc2014Matches.get(serialNumber++)).setResult(0, 0, 0, 0, 4, 3);
        (wc2014Matches.get(serialNumber++)).setResult(1, 0);

        (wc2014Matches.get(serialNumber++)).setResult(1, 7);
        (wc2014Matches.get(serialNumber++)).setResult(0, 0, 0, 0, 2, 4);

        (wc2014Matches.get(serialNumber++)).setResult(0, 3);
        (wc2014Matches.get(serialNumber++)).setResult(0, 0, 1, 0);

        for (MatchEntity matchEntity : wc2014Matches) {
            matchesRepository.update(matchEntity);
        }
    }

    public void groupsWc2014() {
    }

    public void matchesEuro2016() {
        int serialNumber = 1;

        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-10 19:00:00"), france, romania, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-11 13:00:00"), albania, switzerland, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-11 16:00:00"), wales, slovakia, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-11 19:00:00"), england, russia, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-12 13:00:00"), turkey, croatia, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-12 16:00:00"), poland, northernIreland, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-12 19:00:00"), germany, ukraine, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-13 13:00:00"), spain, czechRepublic, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-13 16:00:00"), ireland, sweden, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-13 19:00:00"), belgium, italy, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-14 16:00:00"), austria, hungary, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-14 19:00:00"), portugal, iceland, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-15 13:00:00"), russia, slovakia, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-15 16:00:00"), romania, switzerland, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-15 19:00:00"), france, albania, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-16 13:00:00"), england, wales, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-16 16:00:00"), ukraine, northernIreland, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-16 19:00:00"), germany, poland, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-17 13:00:00"), italy, sweden, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-17 16:00:00"), czechRepublic, croatia, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-17 19:00:00"), spain, turkey, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-18 13:00:00"), belgium, ireland, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-18 16:00:00"), iceland, hungary, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-18 19:00:00"), portugal, austria, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-19 19:00:00"), romania, albania, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-19 19:00:00"), switzerland, france, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-20 19:00:00"), russia, wales, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-20 19:00:00"), slovakia, england, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-21 16:00:00"), ukraine, poland, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-21 16:00:00"), northernIreland, germany, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-21 19:00:00"), czechRepublic, turkey, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-21 19:00:00"), croatia, spain, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-22 16:00:00"), iceland, austria, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-22 16:00:00"), hungary, portugal, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-22 19:00:00"), italy, ireland, matchTypeStandard, euro2016GroupStage))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-22 19:00:00"), sweden, belgium, matchTypeStandard, euro2016GroupStage))));

        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-25 13:00:00"), null, null, matchTypeStandard, euro2016RoundOf16))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-25 16:00:00"), null, null, matchTypeStandard, euro2016RoundOf16))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-25 19:00:00"), null, null, matchTypeStandard, euro2016RoundOf16))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-26 13:00:00"), null, null, matchTypeStandard, euro2016RoundOf16))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-26 16:00:00"), null, null, matchTypeStandard, euro2016RoundOf16))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-26 19:00:00"), null, null, matchTypeStandard, euro2016RoundOf16))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-27 16:00:00"), null, null, matchTypeStandard, euro2016RoundOf16))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-27 19:00:00"), null, null, matchTypeStandard, euro2016RoundOf16))));

        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-06-30 19:00:00"), null, null , matchTypeStandard, euro2016QuarterFinal))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-07-01 19:00:00"), null, null , matchTypeStandard, euro2016QuarterFinal))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-07-02 19:00:00"), null, null , matchTypeStandard, euro2016QuarterFinal))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-07-03 19:00:00"), null, null , matchTypeStandard, euro2016QuarterFinal))));

        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-07-06 19:00:00"), null, null , matchTypeStandard, euro2016SemiFinal))));
        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-07-07 19:00:00"), null, null , matchTypeStandard, euro2016SemiFinal))));

        euro2016Matches.add(matchesRepository.findById(matchesRepository.save(new MatchEntity(serialNumber++, cupEuro2016, Timestamp.valueOf("2016-07-10 19:00:00"), null, null , matchTypeStandard, euro2016Final))));
    }

    public void groupsEuro2016() {
    }
}
