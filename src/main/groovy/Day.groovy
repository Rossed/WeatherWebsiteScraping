import groovy.json.JsonOutput

import Hour
/**
 * Created by rossedwa on 16/4/17.
 */
class Day {

    def day = [:]

    Day(day, hour, temperature, rain) {
        this.day[day] = new Hour(hour, temperature, rain)
    }

    void update(day, hour, temperature, rain) {
        if (this.day[day] == null) {
            this.day[day] = new Hour(hour, temperature, rain)
        } else {
            Hour tempObj = this.day[day]
            tempObj.update(hour, temperature, rain)
            this.day[day] = tempObj
        }

    }

    @Override
    String toString() {
        return JsonOutput.prettyPrint(JsonOutput.toJson(day))
    }
}

