import groovy.json.JsonOutput
import Data

/**
 * Created by rossedwa on 16/4/17.
 */
class Hour {

    def hour = [:]

    Hour (hour, rain, temperature) {
        this.hour[hour] = new Data(temperature, rain)
    }

    void update(hour, temperature, rain) {
        this.hour[hour] = new Data(temperature, rain)
    }

    @Override
    String toString() {
        return JsonOutput.prettyPrint(JsonOutput.toJson(hour))
    }


}
