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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Shakhar Dasgupta<sdasgupt@oswego.edu>
 */
public class EMSHandler extends DefaultHandler {

    private ArrayList<Customer> customers;
    private Customer customer;
    private Account account;
    private Address address;
    private ArrayList<Address> addresses;
    private Meter meter;
    private MeterReading meterReading;
    private String lastName;
    private String firstName;
    private String accountType;
    private String accountNumber;

    public void startDocument() throws SAXException {
        customers = new ArrayList<>();
        addresses = new ArrayList<>();
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "customer":
                lastName = attributes.getValue("lastName");
                firstName = attributes.getValue("firstName");
                customer = new Customer(lastName, firstName);
                break;
            case "account":
                accountType = attributes.getValue("type");
                accountNumber = attributes.getValue("accountNumber");
                if (accountType.equals("residential")) {
                    account = new ResidentialAccount(accountNumber, customer);
                } else if (accountType.equals("commercial")) {
                    account = new CommercialAccount(accountNumber, customer);
                }
                break;
            case "address":
                if (attributes.getValue("type").equals("mailing")) {
                    if (attributes.getValue("unit") != null) {
                        address = new Apartment(attributes.getValue("street"), Integer.parseInt(attributes.getValue("number")), attributes.getValue("zipCode"), "mailing", attributes.getValue("unit"));
                    } else {
                        address = new Commercial(attributes.getValue("street"), Integer.parseInt(attributes.getValue("number")), attributes.getValue("zipCode"), "mailing");
                    }
                    customer.setMailingAddress(address);
                } else if (attributes.getValue("type").equals("apartment")) {
                    address = new Apartment(attributes.getValue("street"), Integer.parseInt(attributes.getValue("number")), attributes.getValue("zipCode"), "apartment", attributes.getValue("unit"));
                } else if (attributes.getValue("type").equals("commercial")) {
                    address = new Commercial(attributes.getValue("street"), Integer.parseInt(attributes.getValue("number")), attributes.getValue("zipCode"), "commercial");
                } else if (attributes.getValue("type").equals("house")) {
                    address = new House(attributes.getValue("street"), Integer.parseInt(attributes.getValue("number")), attributes.getValue("zipCode"), "house");
                }
                break;
            case "meter":
                if (attributes.getValue("type").equals("push")) {
                    meter = new PushMeter(attributes.getValue("id"), attributes.getValue("brand"), "push");
                    meter.setLocation(address, attributes.getValue("location"));
                } else if (attributes.getValue("type").equals("poll") || attributes.getValue("type").equals("polling")) {
                    meter = new PollMeter(attributes.getValue("id"), attributes.getValue("brand"), "poll");
                    meter.setLocation(address, attributes.getValue("location"));
                }
                break;
            case "meterReading":
                meterReading = new MeterReading(Double.parseDouble(attributes.getValue("reading")), LocalDateTime.ofEpochSecond(Long.parseLong(attributes.getValue("date")), 0, ZoneOffset.ofHours(0)), attributes.getValue("flag"), meter);
                break;
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "customer":
                if (customer != null) {
                    customers.add(customer);
                    customer = null;
                }
                break;
            case "account":
                if (account != null) {
                    for (Address a : addresses) {
                        account.addAddress(a);
                    }
                    addresses = new ArrayList<>();
                    account.updateBalance();
                    customer.addAccount(account);
                    account = null;
                }
                break;
            case "address":
                if (address != null) {
                    if (!address.getType().equals("mailing")) {
                        addresses.add(address);
                    }
                    address = null;
                }
                break;
            case "meter":
                if (meter != null) {
                    address.addMeter(meter);
                    meter = null;
                }
                break;
            case "meterReading":
                if (meterReading != null) {
                    meter.addReading(meterReading);
                    meterReading = null;
                }
                break;
        }
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
