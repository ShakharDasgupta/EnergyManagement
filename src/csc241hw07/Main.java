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
 * You should have received acc copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package csc241hw07;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;

/**
 *
 * @author Shakhar Dasgupta<sdasgupt@oswego.edu>
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the Energy Management System");
        Scanner sc = new Scanner(System.in);
        ArrayList<Customer> customers = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.##");
        String command, entity, search;
        boolean found;
        while (true) {
            System.out.println("Enter a command:");
            command = sc.next();
            switch (command) {
                case "load":
                    String file = sc.next();
                    XMLReader reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
                    EMSHandler handler = new EMSHandler();
                    reader.setContentHandler(handler);
                    try {
                        reader.parse(file);
                    } catch (FileNotFoundException e) {
                        System.out.println("Invalid input file - " + file);
                        break;
                    }
                    customers.addAll(handler.getCustomers());
                    System.out.println("Load successful: " + file);
                    break;
                case "clear":
                    customers.clear();
                    break;
                case "show":
                    entity = sc.next();
                    switch (entity) {
                        case "customer":
                            search = sc.next();
                            found = false;
                            for (Customer c : customers) {
                                if (c.getLastName().equalsIgnoreCase(search)) {
                                    found = true;
                                    System.out.println("Last name: " + c.getLastName());
                                    System.out.println("First name: " + c.getFirstName());
                                    System.out.println("Accounts:");
                                    for (Account acc : c.getAccounts()) {
                                        System.out.println("  " + acc.getAccountNumber());
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("No records found");
                            }
                            break;
                        case "account":
                            search = sc.next();
                            found = false;
                            for (Customer c : customers) {
                                for (Account acc : c.getAccounts()) {
                                    if (acc.getAccountNumber().equalsIgnoreCase(search)) {
                                        found = true;
                                        System.out.println("Account number: " + acc.getAccountNumber());
                                        System.out.println("Balance: $" + df.format(acc.getCurrentBalance()));
                                        System.out.println("Addresses:");
                                        for (Address addr : acc.getAddresses()) {
                                            System.out.println("  " + addr.getNumber() + " " + addr.getStreet());
                                        }
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("No records found");
                            }
                            break;
                        case "address":
                            search = sc.nextLine().trim();
                            found = false;
                            for (Customer c : customers) {
                                for (Account acc : c.getAccounts()) {
                                    for (Address addr : acc.getAddresses()) {
                                        if ((addr.getNumber() + " " + addr.getStreet()).equalsIgnoreCase(search)) {
                                            found = true;
                                            System.out.println("Number: " + addr.getNumber());
                                            System.out.println("Street: " + addr.getStreet());
                                            System.out.println("Type: " + addr.getType());
                                            if (addr instanceof Apartment) {
                                                System.out.println("Unit: " + ((Apartment) addr).getUnit());
                                            }
                                            System.out.println("Meters:");
                                            for (Meter m : addr.getMeters()) {
                                                System.out.println("  " + m.getID());
                                            }
                                        }
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("No records found");
                            }
                            break;
                        case "meter":
                            search = sc.next();
                            found = false;
                            for (Customer c : customers) {
                                for (Account acc : c.getAccounts()) {
                                    for (Address addr : acc.getAddresses()) {
                                        for (Meter m : addr.getMeters()) {
                                            if (m.getID().equalsIgnoreCase(search)) {
                                                found = true;
                                                System.out.println("ID: " + m.getID());
                                                System.out.println("Brand: " + m.getBrand());
                                                System.out.println("Location: " + m.getLocation());
                                                System.out.println("Type: " + m.getType());
                                                System.out.println("Meter Readings:");
                                                for (MeterReading mr : m.getReadings()) {
                                                    LocalDateTime ldt = mr.getDateTime();
                                                    System.out.printf("%d-%d-%d %d:%d:%d%n", ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth(), ldt.getHour(), ldt.getMinute(), ldt.getSecond());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println("No records found");
                            }
                            break;
                        default:
                            System.out.println("Invalid entity: " + entity);
                    }
                    break;
                case "check":
                    entity = sc.next();
                    switch(entity) {
                        case "customer":
                            for(Customer c : customers) {
                                if(c.getAccounts().length == 0) {
                                    System.out.println(c.getLastName() + ", " + c.getFirstName());
                                }
                            }
                            break;
                        case "account":
                            for(Customer c : customers) {
                                for(Account acc : c.getAccounts()) {
                                    if(acc.getAddresses().length == 0) {
                                        System.out.println(acc.getAccountNumber());
                                    }
                                }
                            }
                            break;
                        case "address":
                            for(Customer c : customers) {
                                for(Account acc : c.getAccounts()) {
                                    for(Address addr : acc.getAddresses()) {
                                        if(addr.getMeters().length == 0) {
                                            System.out.println(addr.getNumber() + " " + addr.getStreet());
                                        }
                                    }
                                }
                            }
                            break;
                        case "meter":
                            for(Customer c : customers) {
                                for(Account acc : c.getAccounts()) {
                                    for(Address addr : acc.getAddresses()) {
                                        for(Meter m : addr.getMeters()) {
                                            if(m.getReadings().length == 0)
                                                System.out.println(m.getID());
                                            else {
                                                for(MeterReading mr : m.getReadings()) {
                                                    if(!mr.getFlag().equals(m.getType())) {
                                                        System.out.println(m.getID());
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid entity: " + entity);
                    }
                    break;
                case "report":
                    entity = sc.next();
                    switch(entity) {
                        case "balance":
                            for(Customer c : customers) {
                                for(Account acc : c.getAccounts()) {
                                    System.out.println("Account: " + acc.getAccountNumber());
                                    System.out.println("  Customer: " + c.getLastName() + ", " + c.getFirstName());
                                    System.out.println("  Balance: $" + df.format(acc.getCurrentBalance()));
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid entity: " + entity);
                    }
                case "quit":
                    System.out.println("Program ending");
                    System.exit(0);
                default:
                    System.out.println("Invalid command: " + command);
            }
        }
    }
}
