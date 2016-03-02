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
public abstract class Meter {

    private String ID;
    private ArrayList<MeterReading> readings;
    private String brand;
    protected String type;
    private Address address;
    private String locationNotes;

    public Meter(String ID, String brand, String type) {
        readings = new ArrayList<>();
        this.ID = ID;
        this.brand = brand;
        this.type = type;
    }

    public String getID() {
        return ID;
    }

    public MeterReading[] getReadings() {
        return readings.toArray(new MeterReading[readings.size()]);
    }

    public MeterReading getCurrentReading() {
        if (readings.size() > 1) {
            return readings.get(readings.size() - 1);
        } else {
            return null;
        }
    }

    public void addReading(MeterReading m) {
        readings.add(m);
    }

    public String getBrand() {
        return brand;
    }

    public abstract String getType();

    public String getLocation() {
        return locationNotes;
    }

    public Address getAddress() {
        return address;
    }

    public void setLocation(Address a, String n) {
        address = a;
        locationNotes = n;
    }

}
