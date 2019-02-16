package flipkart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MyappApplication {


	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);

		Accounts accounts = Accounts.getInstance();

		createWallet("8130810825");
		createWallet("9368090330");
		makeTransaction("8130810825", "9368090330", new BigDecimal(10));
		List<Transaction> userStatement = getUserStatement("8130810825");

		if(userStatement!=null){
			for(Transaction transaction : userStatement){
				System.out.println("from : "+transaction.getFromAcc()+" , to : "+transaction.getToAcc());
			}
		}
		overView();

	}


	private static void createWallet(String userId) {

			Wallet wallet = new Wallet();
			wallet.setUserId(userId);
			wallet.setCurrBalance(new BigDecimal(0));
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

			for(Wallet wallet : accounts.accountsList){

				boolean fromFlag = false;
				if(fromUserId.equals(wallet.getUserId())){
					if(wallet.getCurrBalance().compareTo(amount)==-1){
						System.out.println("User : "+fromUserId+"account balance is too low, transaction failed");
						return;
					}
					wallet.setCurrBalance(wallet.getCurrBalance().subtract(new BigDecimal(String.valueOf(amount))));
					fromFlag = true;
				}
				if(!fromFlag){
					System.out.println("User : "+fromUserId+"does not exit");
					return;
				}

				boolean toFlag = false;
				if(toUserId.equals(wallet.getUserId())){
					wallet.setCurrBalance(wallet.getCurrBalance().add(new BigDecimal(String.valueOf(amount))));
					toFlag = true;
				}

				if(!toFlag){
					System.out.println("user account : " + toUserId + "does not exist");
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

	public static void overView(){

			Accounts accounts = Accounts.getInstance();

			for(Wallet wallet : accounts.accountsList){

				System.out.println("userId : "+wallet.getUserId()+" current balance : "+ wallet.getCurrBalance());
			}
	}

}



