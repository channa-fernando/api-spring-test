package com.api.spring;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class RestService {
	
	public List<ATM> calculateOrder(List<Task> taskList) {
		List<Task> malfunctionTasks = new ArrayList<>();
        List<Task> priorityReplenishmentTasks = new ArrayList<>();
        List<Task> standardTasks = new ArrayList<>();
        List<Task> lowCashBalanceTasks = new ArrayList<>();

        // Categorize tasks based on request type
        for (Task task : taskList) {
            switch (task.getRequestType()) {
                case "FAILURE_RESTART":
                    malfunctionTasks.add(task);
                    break;
                case "PRIORITY":
                    priorityReplenishmentTasks.add(task);
                    break;
                case "STANDARD":
                    standardTasks.add(task);
                    break;
                case "SIGNAL_LOW":
                    lowCashBalanceTasks.add(task);
                    break;
                default:
                    break;
            }
        }

        // Sort the tasks within each category based on region in ascending order
        malfunctionTasks.sort(Comparator.comparingInt(Task::getRegion));
        priorityReplenishmentTasks.sort(Comparator.comparingInt(Task::getRegion));
        standardTasks.sort(Comparator.comparingInt(Task::getRegion));
        lowCashBalanceTasks.sort(Comparator.comparingInt(Task::getRegion));

        // Generate the final ordered list of ATMs
        List<ATM> orderedATMs = new ArrayList<>();

        // Handle malfunction tasks first
        for (Task task : malfunctionTasks) {
            orderedATMs.add(new ATM(task.getRegion(), task.getAtmId()));
        }

        // Handle priority replenishment tasks next
        for (Task task : priorityReplenishmentTasks) {
            orderedATMs.add(new ATM(task.getRegion(), task.getAtmId()));
        }

        // Handle standard tasks after priority replenishment
        for (Task task : standardTasks) {
            orderedATMs.add(new ATM(task.getRegion(), task.getAtmId()));
        }

        // Handle low cash balance tasks after completing priority orders for each region
        for (Task task : lowCashBalanceTasks) {
            orderedATMs.add(new ATM(task.getRegion(), task.getAtmId()));
        }
        
        // Sort the final ordered list of ATMs based on region in ascending order
        orderedATMs.sort(Comparator.comparingInt(ATM::getRegion));

        return orderedATMs;
	}
	
	 public List<Account> processTransactions(List<Transaction> transactions) {
	        Map<String, Account> accountsMap = new HashMap<>();

	        for (Transaction transaction : transactions) {
	            String debitAccount = transaction.getDebitAccount();
	            String creditAccount = transaction.getCreditAccount();
	            double amount = transaction.getAmount();

	            Account debitAcc = accountsMap.getOrDefault(debitAccount, new Account(debitAccount));
	            debitAcc.setDebitCount(debitAcc.getDebitCount() + 1);
	            debitAcc.setBalance(debitAcc.getBalance() - amount);
	            accountsMap.put(debitAccount, debitAcc);

	            Account creditAcc = accountsMap.getOrDefault(creditAccount, new Account(creditAccount));
	            creditAcc.setCreditCount(creditAcc.getCreditCount() + 1);
	            creditAcc.setBalance(creditAcc.getBalance() + amount);
	            accountsMap.put(creditAccount, creditAcc);
	        }

	        List<Account> accounts = new ArrayList<>(accountsMap.values());
	        Collections.sort(accounts);
	        return accounts;
	    }
}
