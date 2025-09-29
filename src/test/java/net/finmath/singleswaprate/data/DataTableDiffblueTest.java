package net.finmath.singleswaprate.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import net.finmath.singleswaprate.data.DataTable.TableConvention;
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

public class DataTableDiffblueTest {
  /**
   * Test {@link DataTable#exportTable(DataTable)}.
   *
   * <ul>
   *   <li>Then return size is seven.
   * </ul>
   *
   * <p>Method under test: {@link DataTable#exportTable(DataTable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DataTable.exportTable(DataTable)"})
  public void testExportTable_thenReturnSizeIsSeven() {
    // Arrange
    DataTableLight baseTable = new DataTableLight("name", TableConvention.MONTHS);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    SchedulePrototype scheduleMetaData =
        new SchedulePrototype(
            Frequency.DAILY,
            DaycountConvention.E30_360_ISDA,
            ShortPeriodConvention.FIRST,
            DateRollConvention.UNADJUSTED,
            new BusinessdayCalendarAny(),
            1,
            1,
            true);

    DataTableBasic table =
        DataTableBasic.upgradeDataTableLight(baseTable, referenceDate, scheduleMetaData);

    // Act
    Map<String, Object> actualExportTableResult = DataTable.exportTable(table);

    // Assert
    assertEquals(7, actualExportTableResult.size());
    Object getResult = actualExportTableResult.get("maturities");
    assertTrue(getResult instanceof List);
    Object getResult2 = actualExportTableResult.get("referenceDate");
    assertEquals("1970-01-01", getResult2.toString());
    assertEquals("MONTHS", actualExportTableResult.get("tableConvention"));
    assertEquals("name", actualExportTableResult.get("name"));
    assertTrue(((List<Object>) getResult).isEmpty());
    assertTrue(actualExportTableResult.containsKey("scheduleMetaData"));
    assertEquals(getResult, actualExportTableResult.get("terminations"));
    assertEquals(getResult, actualExportTableResult.get("values"));
    assertSame(referenceDate, getResult2);
  }
}
