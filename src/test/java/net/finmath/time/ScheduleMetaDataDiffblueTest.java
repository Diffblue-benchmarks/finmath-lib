package net.finmath.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.ScheduleGenerator.DaycountConvention;
import net.finmath.time.ScheduleGenerator.Frequency;
import net.finmath.time.ScheduleGenerator.ShortPeriodConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar;
import net.finmath.time.businessdaycalendar.BusinessdayCalendar.DateRollConvention;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ScheduleMetaDataDiffblueTest {
  /**
   * Test {@link ScheduleMetaData#ScheduleMetaData(Frequency, DaycountConvention,
   * ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)}.
   *
   * <p>Method under test: {@link ScheduleMetaData#ScheduleMetaData(Frequency, DaycountConvention,
   * ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ScheduleMetaData.<init>(Frequency, DaycountConvention, ShortPeriodConvention, DateRollConvention, BusinessdayCalendar, int, int, boolean)"
  })
  public void testNewScheduleMetaData() {
    // Arrange
    BusinessdayCalendarAny businessdayCalendar = new BusinessdayCalendarAny();

    // Act
    ScheduleMetaData actualScheduleMetaData =
        new ScheduleMetaData(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            businessdayCalendar,
            1,
            1,
            true);

    // Assert
    BusinessdayCalendar businessdayCalendar2 = actualScheduleMetaData.getBusinessdayCalendar();
    assertTrue(businessdayCalendar2 instanceof BusinessdayCalendarAny);
    assertEquals(1, actualScheduleMetaData.getFixingOffsetDays());
    assertEquals(1, actualScheduleMetaData.getPaymentOffsetDays());
    assertEquals(DaycountConvention.E30_360_ISDA, actualScheduleMetaData.getDaycountConvention());
    assertEquals(Frequency.DAILY, actualScheduleMetaData.getFrequency());
    assertEquals(ShortPeriodConvention.FIRST, actualScheduleMetaData.getShortPeriodConvention());
    assertEquals(DateRollConvention.UNADJUSTED, actualScheduleMetaData.getDateRollConvention());
    assertTrue(actualScheduleMetaData.isUseEndOfMonth());
    assertSame(businessdayCalendar, businessdayCalendar2);
  }
}
