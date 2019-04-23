/*
 * Copyright 2019 Eetu, Janne, Jouni, Toni. All rights reserved. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.example.hokikoutsi2019.Classes;

import java.io.Serializable;

public class Line implements Serializable {
    private OffenceLine offenseLine1;
    private OffenceLine offenseLine2;

    private DefenceLine defenceLine1;
    private DefenceLine defenceLine2;

    public void setOffenseLine1(Player rightWing, Player leftWing) {
        offenseLine1 = new OffenceLine(rightWing, leftWing);
    }

    public void setOffenseLine2(Player rightWing, Player leftWing) {
        offenseLine2 = new OffenceLine(rightWing, leftWing);
    }

    public OffenceLine getOffenseLine1() {
        return this.offenseLine1;
    }

    public OffenceLine getOffenseLine2() {
        return this.offenseLine2;
    }


    public void setDefenceLine1(Player rightD, Player leftD) {
        defenceLine1 = new DefenceLine(rightD, leftD);
    }

    public void setDefenceLine2(Player rightD, Player leftD) {
        defenceLine2 = new DefenceLine(rightD, leftD);
    }

    public DefenceLine getDefenceLine1() {
        return this.defenceLine1;
    }

    public DefenceLine getDefenceLine2() {
        return this.defenceLine2;
    }
}
