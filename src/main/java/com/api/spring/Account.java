package com.api.spring;

//inner class representing an account
class Account implements Comparable<Account> {
    private String account;
    private int debitCount;
    private int creditCount;
    private double balance;

    public Account(String account) {
        this.setAccount(account);
        this.setDebitCount(0);
        this.setCreditCount(0);
        this.setBalance(0.0);
    }

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getDebitCount() {
		return debitCount;
	}

	public void setDebitCount(int debitCount) {
		this.debitCount = debitCount;
	}

	public int getCreditCount() {
		return creditCount;
	}

	public void setCreditCount(int creditCount) {
		this.creditCount = creditCount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

//     sort by account ID in ascending order
    @Override
    public int compareTo(Account other) {
        return this.account.compareTo(other.account);
    }

    @Override
    public String toString() {
        return "Account: " + account + ", debitCount=" + debitCount + ", creditCount=" + creditCount + ", balance=" + balance;
    }
}
