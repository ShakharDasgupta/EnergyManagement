/*
 * Copyright (C) 2016 Shakhar Dasgupta <sdasgupt@oswego.edu>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package csc241hw07;

import java.util.ArrayList;

/**
 *
 * @author Shakhar Dasgupta <sdasgupt@oswego.edu>
 */
public abstract class Account {

    private String accountNumber;
    private Customer customer;
    private double currentBalance;
    private ArrayList<Address> addresses;

    public Account(String accountNumber, Customer customer) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        currentBalance = 0.0;
        addresses = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public Address[] getAddresses() {
        return addresses.toArray(new Address[addresses.size()]);
    }

    public boolean addAddress(Address a) {
        return addresses.add(a);
    }

    public boolean removeAddress(Address a) {
        return addresses.remove(a);
    }
    
    public void setBalance(double b) {
        currentBalance = b;
    }
    
    public abstract void updateBalance();
}
