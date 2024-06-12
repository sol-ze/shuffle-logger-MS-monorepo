package com.sol.serviceshuffle;

import com.sol.serviceshuffle.service.ShuffleServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class ServiceShuffleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceShuffleApplication.class, args);

    }

//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		return runner -> {
//			ShuffleServiceImpl sh = new ShuffleServiceImpl();
//			ArrayList<Integer> list = sh.shuffle(10);
//			System.out.println(sh.printList(list));
//			list = sh.shuffle(7);
//			System.out.println(sh.printList(list));
//			list = sh.shuffle(1);
//			System.out.println(sh.printList(list));
//			list = sh.shuffle(-5);
//			System.out.println(sh.printList(list));
//		};
//	}

}
