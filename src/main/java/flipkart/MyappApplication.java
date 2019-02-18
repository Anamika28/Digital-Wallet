package flipkart;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MyappApplication {


	public static void main(String[] args) throws IOException {
		SpringApplication.run(MyappApplication.class, args);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//		String input = reader.readLine();
//		String inputArr[] = input.split(",");

		/*take input as json, parse using ObjectMapper*/
		ObjectMapper mapper = new ObjectMapper();
		

		Accounts accounts = Accounts.getInstance();

		createWallet("8130810825", new BigDecimal(10));
		createWallet("9368090330", new BigDecimal(20));
		createWallet("1234567890", new BigDecimal(30));
		makeTransaction("8130810825", "9368090330", new BigDecimal(4));
		List<Transaction> userStatement = getUserStatement("8130810825");

		if(userStatement!=null){
			for(Transaction transaction : userStatement){
				System.out.println("from : "+transaction.getFromAcc()+" , to : "+transaction.getToAcc());
			}
		}
		overView();
		accounts.Offer2();
	}


	private static void createWallet(String userId, BigDecimal userBal) {

			Wallet wallet = new Wallet();
			wallet.setUserId(userId);
			wallet.setCurrBalance(userBal);
			wallet.setUsersTransactions(new ArrayList<>());

			Accounts accounts = Accounts.getInstance();
			accounts.getAccountsList().add(wallet);
			System.out.println("account has been created successfully for userId : "+userId);
	}

	private static void makeTransaction(String fromUserId, String toUserId, BigDecimal amount){

			if(amount.compareTo(new BigDecimal(0.00001))==-1){
				System.out.println("Sorry, amount is too low, transaction is unsuccessful");
				return;
			}

			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			transaction.setFromAcc(fromUserId);
			transaction.setToAcc(toUserId);

			Accounts accounts = Accounts.getInstance();
			BigDecimal p1 = null;
			BigDecimal p2 = null;
			boolean fromFlag = false;
			boolean toFlag = false;

			for(Wallet wallet : accounts.accountsList){


				if(fromUserId.equals(wallet.getUserId())){
					if(wallet.getCurrBalance().compareTo(amount)==-1){
						System.out.println("User : "+fromUserId+"account balance is too low, transaction failed");
						return;
					}
					wallet.setCurrBalance(wallet.getCurrBalance().subtract(new BigDecimal(String.valueOf(amount))));
					fromFlag = true;
					p1 = wallet.getCurrBalance();
				}

				if(toUserId.equals(wallet.getUserId())){
					wallet.setCurrBalance(wallet.getCurrBalance().add(new BigDecimal(String.valueOf(amount))));
					toFlag = true;
					p2 = wallet.getCurrBalance();
				}


			}
			if(!fromFlag){
				System.out.println("User : "+fromUserId+"does not exit");
				return;
			}

			if(!toFlag){
				System.out.println("User : "+fromUserId+"does not exit");
				return;
			}
			if(p1!=null && p2!=null){
				if(p1.compareTo(p2)==0){
					transaction.offer1();
				}
			}
			System.out.println("transaction is successful from account : "+fromUserId+"to account : "+toUserId);
	}

	private static List<Transaction> getUserStatement(String userId){

			Accounts accounts = Accounts.getInstance();
			List<Transaction> userTransactions = null;

			for(Wallet wallet : accounts.accountsList){

				if(userId.equals(wallet.getUserId())){
					userTransactions =  wallet.getUsersTransactions();
				}
			}
			return userTransactions;
	}

	private static void overView(){

			Accounts accounts = Accounts.getInstance();

			for(Wallet wallet : accounts.accountsList){

				System.out.println("userId : "+wallet.getUserId()+" current balance : "+ wallet.getCurrBalance());
			}
	}

}



