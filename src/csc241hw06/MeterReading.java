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
package csc241hw06;

import java.time.LocalDateTime;

/**
 *
 * @author Shakhar Dasgupta <sdasgupt@oswego.edu>
 */
public class MeterReading {

    private double reading;
    private LocalDateTime dateTime;
    private String flag;
    private Meter meter;

    public MeterReading(double reading, LocalDateTime dateTime, String flag, Meter meter) {
        this.reading = reading;
        this.dateTime = dateTime;
        this.flag = flag;
        this.meter = meter;
    }

    public double getReading() {
        return reading;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getFlag() {
        return flag;
    }

    public Meter getMeter() {
        return meter;
    }

}
