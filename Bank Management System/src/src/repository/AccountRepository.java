package repository;
import model.Account;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepository {
    /**
     * In-memory store for all bank accounts.
     * Uses a HashMap: key = accountNumber, value = Account object.
     */

    private Map<String, Account> accountStore = new HashMap<>();

    /**
     * Save a new or updated account.
     */

    public void save(Account account) {
        accountStore.put(account.getAccountNumber(), account);
    }

    /**
     * Find an account by its account number. Returns null if not found.
     */

    public Account findByAccountNumber(String accountNumber) {
        return accountStore.get(accountNumber);
    }

    /**
     * Return all accounts as a list.
     */
    public List<Account> findAll() {
        return new ArrayList<>(accountStore.values());
    }

    /**
     * Delete an account. Returns true if found and deleted, false otherwise.
     */
    public boolean delete(String accountNumber) {
        if (accountStore.containsKey(accountNumber)) {
            accountStore.remove(accountNumber);
            return true;
        }
        return false;
    }

    /* Check if an account exists. */
    public boolean exists(String accountNumber) {
        return accountStore.containsKey(accountNumber);
    }
}
