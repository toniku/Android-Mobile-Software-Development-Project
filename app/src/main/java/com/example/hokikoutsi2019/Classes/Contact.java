/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import java.io.Serializable;

public class Contact implements Serializable {
    public String getAddress() {
        return address;
    }

    private String address;

    public String getZipCode() {
        return zipCode;
    }

    private String zipCode;

    public String getCity() {
        return city;
    }

    private String city;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private String phoneNumber;

    public Contact(String address, String zipCode, String city, String phoneNumber)
    {
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }
}
