package com.pedroostanik.rifa;

import com.pedroostanik.rifa.main.Main;
import com.pedroostanik.rifa.repository.ParticipantRepository;
import com.pedroostanik.rifa.repository.RaffleRepository;
import com.pedroostanik.rifa.repository.RaffleSlotRepository;
import com.pedroostanik.rifa.service.ParticipantService;
import com.pedroostanik.rifa.service.RaffleService;
import com.pedroostanik.rifa.ui.MenuLayoutService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RifaApplication implements CommandLineRunner {

//	@Autowired
	private MenuLayoutService menuLayoutService;



	public RifaApplication(MenuLayoutService menuLayoutService) {

		this.menuLayoutService = menuLayoutService;

	}

	public static void main(String[] args) {
		SpringApplication.run(RifaApplication.class, args);
	}

	@Override
	public void run(String... args) {
//
//		Main main = new Main(menuLayoutService);
//		main.execute();

	}

}
