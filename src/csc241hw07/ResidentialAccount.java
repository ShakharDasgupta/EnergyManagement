/*
 * Copyright (C) 2016 Shakhar Dasgupta<sdasgupt@oswego.edu>
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

/**
 *
 * @author Shakhar Dasgupta<sdasgupt@oswego.edu>
 */
public class ResidentialAccount extends Account {

    public ResidentialAccount(String accountNumber, Customer customer) {
        super(accountNumber, customer);
    }

    public void updateBalance() {
        double sum = 0;
        for (Address a : getAddresses()) {
            for (Meter m : a.getMeters()) {
                for (MeterReading mr : m.getReadings()) {
                    sum += mr.getReading();
                }
            }
        }
        setBalance(sum * Constants.residentialUnitRate);
    }
}
