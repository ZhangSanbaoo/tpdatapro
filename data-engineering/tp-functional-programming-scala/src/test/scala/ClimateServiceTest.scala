import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {
  import org.mockito.Mockito.{mock, times, verify, verifyNoMoreInteractions}
  test("containsWordGlobalWarming - non climate related words should return false") {
    assert(ClimateService.isClimateRelated("pizza") == false)
  }

  test("isClimateRelated - climate related words should return true") {
    assert(ClimateService.isClimateRelated("climate change") == true)
    assert(ClimateService.isClimateRelated("IPCC"))
  }

  //@TODO
  test("parseRawData") {
    // our inputs
    val firstRecord = (2003, 1, 355.2) //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val output = List(Some(co2RecordWithType), Some(co2RecordWithType2))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  test("parseRawDataNegative") {
    // our inputs
    val firstRecord = (2003, 1, -355.2) //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, -375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val output = List(None, None)

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  //@TODO
  test("filterDecemberData") {
    val co2Record1 = CO2Record(2020, 11, 412.55)
    val co2Record2 = CO2Record(2020, 12, 414.06)
    val co2Record3 = CO2Record(2021, 11, 414.7)
    val co2Record4 = CO2Record(2021, 12, 416.21)

    val list = List(Some(co2Record1), Some(co2Record2), Some(co2Record3), Some(co2Record4))

    val expected = List(co2Record1, co2Record3)

    assert(ClimateService.filterDecemberData(list) == expected)
  }

  test("getMinMax") {
    val co2Record1 = CO2Record(2020, 11, 412.55)
    val co2Record2 = CO2Record(2020, 12, 414.06)
    val co2Record3 = CO2Record(2021, 11, 414.7)
    val co2Record4 = CO2Record(2021, 12, 416.21)

    val list = List(co2Record1, co2Record2, co2Record3, co2Record4)

    val expected = (412.55, 416.21)

    assert(ClimateService.getMinMax(list) == expected)
  }

  test("getMinMaxByYear") {
    val co2Record1 = CO2Record(2020, 11, 412.55)
    val co2Record2 = CO2Record(2020, 12, 414.06)
    val co2Record3 = CO2Record(2021, 11, 414.7)
    val co2Record4 = CO2Record(2021, 12, 416.21)

    val list = List(co2Record1, co2Record2, co2Record3, co2Record4)

    val expected = (414.7, 416.21)


    assert(ClimateService.getMinMaxByYear(list, 2021) == expected)
  }

  test("getDiffMinMax") {
    val co2Record1 = CO2Record(2020, 11, 412.55)
    val co2Record2 = CO2Record(2020, 12, 414.06)
    val co2Record3 = CO2Record(2021, 10, 413.15)
    val co2Record4 = CO2Record(2021, 11, 414.7)
    val co2Record5 = CO2Record(2021, 12, 416.21)
    val co2Record6 = CO2Record(2022, 1, 417.37)
    val co2Record7 = CO2Record(2022, 2, 418.23)
    val co2Record8 = CO2Record(2022, 3, 419.12)

    val list = List(co2Record1, co2Record2, co2Record3, co2Record4, co2Record5, co2Record6, co2Record7, co2Record8)

    val actual = ClimateService.getDiffMinMax(list)
    val expected = 6.57
    val tolerance = 0.001
    assert(math.abs(actual - expected) < tolerance)
  }

  test("showCO2Data") {
    val co2Record1 = CO2Record(2020, 11, 412.55)
    val co2Record2 = CO2Record(2020, 12, 414.06)
    val co2Record3 = CO2Record(2021, 11, 414.7)
    val co2Record4 = CO2Record(2021, 12, 416.21)

    val list = List(Some(co2Record1), Some(co2Record2), Some(co2Record3), Some(co2Record4))

    val logger = mock[Logger]

    ClimateService.showCO2Data(list)(logger)

    verify(logger).info("Call ClimateService.filterDecemberData here")
    verify(logger, times(2)).info("Call record.show function here inside a map function")
  }
}

