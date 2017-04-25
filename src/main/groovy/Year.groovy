import groovy.json.JsonOutput
import Month

class Year {

	def year = [:]
	
	Year(year, month, day, hour, temperature, rain) {
		this.year[year] = new Month(month, day, hour, temperature, rain)
	}

	void update(year, month, day, hour, temperature, rain) {
		if (this.year[year] == null) {
			this.year[year] = new Month(month, day, hour, temperature, rain)
		} else {
			Month tempObj = this.year[year]
			tempObj.update(month, day, hour, temperature, rain)
			this.year[year] = tempObj
		}
	}

	@Override
	String toString() {
		return JsonOutput.prettyPrint(JsonOutput.toJson(year))
	}
}