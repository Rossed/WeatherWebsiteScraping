
import groovy.json.*

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.text.SimpleDateFormat
import Year

class Weather {

	def static weather

    static void main(String[] args) {

		File f = new File("src/main/resources/weather.json")

		def yearFormat
		def monthFormat
		def dayFormat
		def hourFormat

    	while (true) {
			println "running..."
			def date = new Date()
			def timeNow = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
			def onTheHour = new SimpleDateFormat("mm:ss")
			def onTheMinute = new SimpleDateFormat("ss")

			yearFormat = new SimpleDateFormat("yyyy")
			monthFormat = new SimpleDateFormat("MM")
			dayFormat = new SimpleDateFormat("dd")
			hourFormat = new SimpleDateFormat("HH")

			if (onTheHour.format(date) == "00:00") {

				Document doc = Jsoup.connect("http://www.bom.gov.au/places/act/canberra/").get()
				Elements summary = doc.select("#summary-1")
				Elements temperatureElement = summary.select(".airT")
				Elements rainElement = summary.select(".rain")

				def year = yearFormat.format(date)
				def month = monthFormat.format(date)
				def day = dayFormat.format(date)
				def hour = hourFormat.format(date)

				def temperature = temperatureElement.text()
				def rain = rainElement.text()

				if (weather == null) {
					weather = new Year(year, month, day, hour, temperature, rain)
				} else {
					Year tempObj = weather
					tempObj.update(year, month, day, hour, temperature, rain)
					weather = tempObj
				}

				String json = JsonOutput.prettyPrint(JsonOutput.toJson(weather))
				f.write(json)
			}
		}
    }
}