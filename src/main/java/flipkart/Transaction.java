package flipkart;

import java.math.BigDecimal;

/**
 * Created by anamika on 16/2/19.
 */
public class Transaction {

    private BigDecimal amount;
    private String fromAcc;
    private String toAcc;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFromAcc() {
        return fromAcc;
    }

    public void setFromAcc(String fromAcc) {
        this.fromAcc = fromAcc;
    }

    public String getToAcc() {
        return toAcc;
    }

    public void setToAcc(String toAcc) {
        this.toAcc = toAcc;
    }

    public void grantOffer(){

    }

    public void offer1(){

        String from = this.fromAcc;
        String to = this.toAcc;

        BigDecimal balFrom = null;
        BigDecimal balTo = null;

        Wallet walletFrom = null;
        Wallet walletTo = null;

        for(Wallet wallet : Accounts.getInstance().accountsList){

            if(from.equals(wallet.getUserId())){
                balFrom = wallet.getCurrBalance();
                walletFrom = wallet;
            }

            if(from.equals(wallet.getUserId())){
                balTo = wallet.getCurrBalance();
                walletTo = wallet;
            }

            if(balFrom.compareTo(balTo)==0){

                walletFrom.setCurrBalance(walletFrom.getCurrBalance().add(new BigDecimal(10)));
                walletTo.setCurrBalance(walletTo.getCurrBalance().add(new BigDecimal(10)));
            }
        }
    }
}
