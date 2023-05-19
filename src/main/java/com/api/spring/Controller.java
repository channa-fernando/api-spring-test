package com.api.spring;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class Controller {

	@Autowired
	private RestService restService;

	@Autowired
	private BoardEntry boardEntry;
	
	
	@PostMapping("/atms/calculateOrder")
	public ResponseEntity<List<ATM>> calculateOrder(@RequestBody List<Task> taskList) {
		List<ATM> atmList = restService.calculateOrder(taskList);
		return new ResponseEntity<>(atmList, HttpStatus.OK);
	}
	
	@PostMapping("/onlinegame/calculate")
	public ResponseEntity<List<List<Clan>>> enterBoard(@RequestBody GameRequest payload) {
		 for (Clan clan : payload.getClans()) {
             System.out.println(clan.getNumberOfPlayers());
         }
		List<List<Clan>> atmList = boardEntry.enterBoard(payload.getGroupCount(), payload.getClans());
		return new ResponseEntity<>(atmList, HttpStatus.OK);
	}
	
	@PostMapping("/transactions/report")
	public ResponseEntity<List<Account>> processTransactions(@RequestBody List<Transaction> transactionList) {
		List<Account> accountList = restService.processTransactions(transactionList);
		return new ResponseEntity<>(accountList, HttpStatus.OK);
	}
	
}