package com.pedroostanik.rifa.main;

import com.pedroostanik.rifa.ui.MenuLayoutService;
import org.springframework.stereotype.Component;

//@Component
//public class Main {
//
////    @Autowired
//    private RaffleSlotRepository raffleSlotRepositorio;
//    private RaffleRepository raffleRepositorio;
//    private ParticipantRepository participantRepositorio;
//    private RaffleService raffleService;
//    private ParticipantService participantService;
//
//
//    public void execute(RaffleSlotRepository raffleSlotRepositorio, RaffleRepository raffleRepositorio,
//                        ParticipantRepository participantRepository, RaffleService raffleService, ParticipantService participantService) {
//        MenuLayoutService menuLayoutService = new MenuLayoutService(raffleService, participantService);
//        menuLayoutService.render(raffleRepositorio, raffleSlotRepositorio, participantRepository);
//
//    }
//
//
//
//
//}

@Component
public class Main {

    private final MenuLayoutService menuLayoutService;

    public Main(MenuLayoutService menuLayoutService) {
        this.menuLayoutService = menuLayoutService;
    }

    public void execute() {
        menuLayoutService.render();
    }
}
