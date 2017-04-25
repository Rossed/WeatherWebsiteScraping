import groovy.json.JsonOutput
import Day

class Month {

	def month = [:]
	
	Month(month, day, hour, temperature, rain) {
		this.month[month] = new Day(day, hour, temperature, rain)
	}

	void update(month, day, hour, temperature, rain) {
		if (this.month[month] == null) {
			this.month[month] = new Day(day, hour, temperature, rain)
		} else {
			Day tempObj = this.month[month]
			tempObj.update(day, hour, temperature, rain)
			this.month[month] = tempObj
		}
	}

	@Override
	String toString() {
		return JsonOutput.prettyPrint(JsonOutput.toJson(month))
	}

}