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
package csc241hw04;

import java.util.ArrayList;

/**
 *
 * @author Shakhar Dasgupta <sdasgupt@oswego.edu>
 */
public abstract class Address {

    private String street;
    private int number;
    private String zipCode;
    protected String type;
    private ArrayList<Meter> meters;
    private Account account;

    public Address(String street, int number, String zipCode, String type) {
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.type = type;
        meters = new ArrayList<>();
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public abstract String getType();

    public Meter[] getMeters() {
        return meters.toArray(new Meter[meters.size()]);
    }

    public boolean addMeter(Meter m) {
        return meters.add(m);
    }

    public boolean removeMeter(String id) {
        for (Meter m : meters) {
            if (m.getID().equals(id)) {
                return meters.remove(m);
            }
        }
        return false;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
