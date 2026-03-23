package com.pedroostanik.rifa.ui;

import com.pedroostanik.rifa.domain.Participant;
import com.pedroostanik.rifa.domain.Raffle;
import com.pedroostanik.rifa.domain.RaffleSlot;
import com.pedroostanik.rifa.service.ParticipantService;
import com.pedroostanik.rifa.service.RaffleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;


@Service
public class MenuLayoutService {

    private final RaffleService raffleService;
    private final ParticipantService participantService;
    private final Scanner scanner = new Scanner(System.in);


    public MenuLayoutService(
            RaffleService raffleService,
            ParticipantService participantService

    ) {
        this.raffleService = raffleService;
        this.participantService = participantService;
    }

    public void render() {

        while (true) {

            System.out.print("""                
                
                Bem-vindo(a) ao sistema de rifas!
                
                Escolha uma opção abaixo:
                
                1. Consultar as rifas vigentes
                2. Cadastrar uma nova rifa
                0. Encerrar
                
                
                
                
                """);

            Scanner sc = new Scanner(System.in);
            int initialChoice = sc.nextInt();

            if (initialChoice == 1) {
                Long idRaffle = renderRaffles(raffleService);
                Long idSlot = renderSlots(raffleService, idRaffle);
                System.out.println("Qual seu nome? ");
                String nome = scanner.nextLine();
                raffleService.registerParticipant(raffleService, this.participantService, idRaffle, idSlot, nome);
            }
            if (initialChoice == 2) {
                openRegister(raffleService);

            }
            if (initialChoice == 0) {
                System.out.println("Encerrando Sistema");
                break;
            }
            System.out.println("Escolha uma opção válida.");



        }

    }

    private void openRegister(RaffleService raffleService) {
        System.out.println("Qual o nome da rifa? ");
        String raffleName = scanner.nextLine();

        Raffle raffle = new Raffle(raffleName);
        raffleService.saveRaffle(raffle);

        while (true) {
            System.out.println("Digite um nome para cadastrar como vago na rifa,ou [0] para encerrar o Sistema.");
            String emptyName = scanner.nextLine();

            if ("0".equals(emptyName)) {
                break;
            }
            RaffleSlot raffleSlot = new RaffleSlot(raffle, emptyName);
            raffleService.saveRaffleSlot(raffleSlot);
        }


//        System.out.printf("Rifa %s salva com sucesso!", raffleName);
    }

    private Long renderRaffles(RaffleService raffleService) {


        List<Raffle> listaRaffles = raffleService.listaRaffles();
        List<String> optionsRaffle = IntStream.range(0, listaRaffles.size())
                .mapToObj(i -> i + 1 + " - " + listaRaffles.get(i).getTitle())
                .toList();

        System.out.println(optionsRaffle);

        int raffleNumberChoosed = scanner.nextInt();
        scanner.nextLine();

        return listaRaffles.get(raffleNumberChoosed - 1).getId();
    }

    private Long renderSlots(RaffleService raffleService, Long idRaffle) {

        List<RaffleSlot> availablesRafflesSlot = raffleService.getAvailablesRafflesSlot(idRaffle);

        List<String> optionsSlot = availablesRafflesSlot.stream().map(raffleSlot -> raffleSlot.getId() + " - " + raffleSlot.getValue())
                .toList();

        System.out.println("Available Options: ");
        System.out.println(optionsSlot);

        Long slotChoosed = scanner.nextLong();
        scanner.nextLine(); // consume \n

        return slotChoosed;
    }

}
