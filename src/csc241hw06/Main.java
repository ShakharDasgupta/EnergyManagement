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
package csc241hw06;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;

/**
 *
 * @author Shakhar Dasgupta<sdasgupt@oswego.edu>
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the input file name:");
        String file = sc.nextLine();
        System.out.println("Enter the account number:");
        String accountNumber = sc.nextLine();
        XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        EMSHandler handler = new EMSHandler();
        reader.setContentHandler(handler);
        reader.parse(file);
        ArrayList<Customer> customers = handler.getCustomers();
        boolean found = false;
        for(Customer customer : handler.getCustomers()) {
            for(Account account : customer.getAccounts()) {
                if(account.getAccountNumber().equals(accountNumber)) {
                    found = true;
                    DecimalFormat df = new DecimalFormat("#.00");
                    System.out.println("Balance: $" + df.format(account.getCurrentBalance()));
                }
            }
        }
        if(!found) {
            System.out.println("Account number " + accountNumber + " not found.");
        }
    }
}
