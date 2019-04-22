/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import java.io.Serializable;

public class Player implements Serializable {

    private Stats stats;

    private Contact contact;
    private String firstname;
    private String lastname;
    private int number;
    private String handness;
    private String birthdate;

    public Player(String fname, String lname, int jerseyNumber) {
        this.firstname = fname;
        this.lastname = lname;
        this.number = jerseyNumber;
        this.birthdate = "";
        this.handness = "";
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getHandness() {
        return handness;
    }

    public void setHandness(String handness) {
        this.handness = handness;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setContact(String address, String zipCode, String city, String phoneNumber) {
        this.contact = new Contact(address, zipCode, city, phoneNumber);
    }

    public Stats getStats() {
        return this.stats;
    }

    public void setStats(int seasonStart) {
        stats = new Stats(2019);
    }

    public Contact getContact() {
        return contact;
    }
}
