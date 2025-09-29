package net.finmath.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice.QuotingConvention;
import net.finmath.time.ScheduleGenerator;
import net.finmath.time.ScheduleGenerator.DaycountConvention;
import net.finmath.time.ScheduleGenerator.Frequency;
import net.finmath.time.ScheduleGenerator.ShortPeriodConvention;
import net.finmath.time.SchedulePrototype;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CSVSwaptionParserDiffblueTest {
  /**
   * Test {@link CSVSwaptionParser#parseZIPToConvention(File, File, String, String, String,
   * QuotingConvention, double, AnalyticModel[])}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link CSVSwaptionParser#parseZIPToConvention(File, File, String, String,
   * String, SwaptionDataLattice.QuotingConvention, double, AnalyticModel[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SwaptionDataLattice[] CSVSwaptionParser.parseZIPToConvention(File, File, String, String, String, SwaptionDataLattice.QuotingConvention, double, AnalyticModel[])"
  })
  public void testParseZIPToConvention_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype floatMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    CSVSwaptionParser csvSwaptionParser = new CSVSwaptionParser(fixMetaSchedule, floatMetaSchedule);
    File atmFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();
    File otmFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            csvSwaptionParser.parseZIPToConvention(
                atmFile,
                otmFile,
                "GBP",
                "Index",
                "3",
                QuotingConvention.PAYERVOLATILITYLOGNORMAL,
                10.0d,
                new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link CSVSwaptionParser#getReferenceDates(SwaptionDataLattice[])}.
   *
   * <ul>
   *   <li>Then return first element toString is {@code 1970-01-01}.
   * </ul>
   *
   * <p>Method under test: {@link CSVSwaptionParser#getReferenceDates(SwaptionDataLattice[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate[] CSVSwaptionParser.getReferenceDates(SwaptionDataLattice[])"})
  public void testGetReferenceDates_thenReturnFirstElementToStringIs19700101() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype floatMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);
    SchedulePrototype fixMetaSchedule =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    SwaptionDataLattice swaptionDataLattice =
        new SwaptionDataLattice(
            referenceDate,
            QuotingConvention.PAYERVOLATILITYLOGNORMAL,
            "Forward Curve Name",
            "3",
            floatMetaSchedule,
            fixMetaSchedule,
            new String[] {},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Act
    LocalDate[] actualReferenceDates =
        CSVSwaptionParser.getReferenceDates(new SwaptionDataLattice[] {swaptionDataLattice});

    // Assert
    LocalDate localDate = actualReferenceDates[0];
    assertEquals("1970-01-01", localDate.toString());
    assertEquals(1, actualReferenceDates.length);
    assertSame(referenceDate, localDate);
  }
}
