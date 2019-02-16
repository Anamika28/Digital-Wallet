package flipkart;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by anamika on 16/2/19.
 */
public class Wallet {

    private String userId;
    private String userName;
    private BigDecimal currBalance;
    private List<Transaction> usersTransactions;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getCurrBalance() {
        return currBalance;
    }

    public void setCurrBalance(BigDecimal currBalance) {
        this.currBalance = currBalance;
    }

    public List<Transaction> getUsersTransactions() {
        return usersTransactions;
    }

    public void setUsersTransactions(List<Transaction> usersTransactions) {
        this.usersTransactions = usersTransactions;
    }
}
