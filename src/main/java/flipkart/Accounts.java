package flipkart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anamika on 16/2/19.
 */
public class Accounts {

    public List<Wallet> accountsList = new ArrayList<>();


    private static Accounts accounts;

    static {
        accounts = new Accounts();
    }

    private Accounts(){

    }

    public static Accounts getInstance(){
        return accounts;
    }

    public List<Wallet> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(List<Wallet> accountsList) {
        this.accountsList = accountsList;
    }

    public void Offer2() {

        List<Wallet> walletsWithHighestTrans = new ArrayList<>();

        if(accountsList==null)
            return;

        Integer max1 = Integer.MIN_VALUE;
        Integer max2 = Integer.MIN_VALUE;
        Integer max3 = Integer.MIN_VALUE;

        Wallet walletWithMaxVal = null;
        for (Wallet wallet : accountsList) {
            if (max1 < wallet.getUsersTransactions().size()) {
                max1 = wallet.getUsersTransactions().size();
                walletWithMaxVal = wallet;
            }
        }
        walletsWithHighestTrans.add(walletWithMaxVal);

        walletWithMaxVal = null;
        for (Wallet wallet : accountsList) {
            if (max2 < wallet.getUsersTransactions().size() && max2 <= max1) {
                max1 = wallet.getUsersTransactions().size();
            }
        }
        walletsWithHighestTrans.add(walletWithMaxVal);
        walletWithMaxVal = null;
        for (Wallet wallet : accountsList) {
            if (max3 < wallet.getUsersTransactions().size() && max3 <= max2) {
                max3 = wallet.getUsersTransactions().size();
            }
        }
        walletsWithHighestTrans.add(walletWithMaxVal);

        if(walletsWithHighestTrans==null || walletsWithHighestTrans.size() ==0){
            return;
        }

        Wallet wallet = walletsWithHighestTrans.get(0);
        wallet.setCurrBalance(wallet.getCurrBalance().add(new BigDecimal(10)));
        System.out.println("new user balance for user1 : "+wallet.getCurrBalance());

        if(walletsWithHighestTrans.get(1)!=null){
        wallet = walletsWithHighestTrans.get(1);
        wallet.setCurrBalance(wallet.getCurrBalance().add(new BigDecimal(5)));
        }
        System.out.println("new user balance for user2 : "+wallet.getCurrBalance());

        if(walletsWithHighestTrans.get(2)!=null) {
            wallet = walletsWithHighestTrans.get(2);
            wallet.setCurrBalance(wallet.getCurrBalance().add(new BigDecimal(2)));
        }
        System.out.println("new user balance for user3 : "+wallet.getCurrBalance());
    }
}
