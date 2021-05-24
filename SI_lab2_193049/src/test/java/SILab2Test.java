
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {


    private List<Time> timeList = new ArrayList<>();
    private List<Integer>  integerList = new ArrayList<>();
    RuntimeException ex;

    @Test
    public void everyBranchTest(){

        //negativ hour
        Time time1 = new Time( -8,00,00);
        List<Time> timeList1 = new ArrayList<>();
        timeList1.add(time1);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList1));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));

        //invalid hour
        Time time2 = new Time(  26,00,00);
        List<Time> timeList2 = new ArrayList<>();
        timeList2.add(time2);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList2));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));

        //list with invalid min
        Time time = new Time(  8,05,05);
        Time time3 = new Time(  8,-05,00);
        List<Time> timeList3 = new ArrayList<>();
        timeList3.add(time);
        timeList3.add(time3);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList3));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        //list with invalid sec
        Time time4 = new Time(8,05,05);
        Time time5 = new Time(8,00,-05);
        Time time6 = new Time (13,50,05);
        List<Time> timeList4 = new ArrayList<>();
        timeList4.add(time4);
        timeList4.add(time5);
        timeList4.add(time6);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList4));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        //normal flow
        Time time7 = new Time(8,05,05);
        Time time8 = new Time (13,50,05);
        List<Time> timeList5 = new ArrayList<>();
        timeList5.add(time7);
        timeList5.add(time8);
        List<Integer> expectedResult1 = new ArrayList<>();
        expectedResult1.add(29105);
        expectedResult1.add(49805);
        assertEquals(expectedResult1, SILab2.function(timeList5));

        //time greater than max
        Time time9 = new Time(24,00,00);
        Time time10 = new Time(24,00,05);
        List<Time> timeList6 = new ArrayList<>();
        timeList6.add(time9);
        timeList6.add(time10);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList6));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

    }

    @Test
    public void multipleConditionsTest(){

        //if (hr < 0 || hr > 24)
        //TX
        Time time1 = new Time( -8,00,00);
        List<Time> timeList1 = new ArrayList<>();
        timeList1.add(time1);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList1));
        assertTrue(ex.getMessage().contains("The hours are smaller than the minimum"));

        //FT
        Time time2 = new Time(  26,00,00);
        List<Time> timeList2 = new ArrayList<>();
        timeList2.add(time2);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList2));
        assertTrue(ex.getMessage().contains("The hours are grater than the maximum"));

        //FF
        Time time3 = new Time( 8,00, 00);
        List<Time> timeList3 = new ArrayList<>();
        timeList3.add(time3);
        List<Integer> expectedResult1 = new ArrayList<>();
        expectedResult1.add(28800);
        assertEquals(expectedResult1, SILab2.function(timeList3));

        //if (min < 0 || min > 59)
        //TX
        Time time4 = new Time(  8,-05,00);
        List<Time> timeList4 = new ArrayList<>();
        timeList4.add(time4);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList4));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        //FT
        Time time5 = new Time(   8,61,05);
        List<Time> timeList5 = new ArrayList<>();
        timeList5.add(time5);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList5));
        assertTrue(ex.getMessage().contains("The minutes are not valid!"));

        //FF
        //08:05:05
        Time time6 = new Time(8,05,05);
        List<Time> timeList6 = new ArrayList<>();
        timeList6.add(time6);
        List<Integer> expectedResult2 = new ArrayList<>();
        expectedResult2.add(29105);
        assertEquals(expectedResult2, SILab2.function(timeList6));

        //if (sec >= 0 && sec <= 59)
        //TX
        Time time7 = new Time(8,05,05);
        List<Time> timeList7 = new ArrayList<>();
        timeList7.add(time7);
        List<Integer> expectedResult3 = new ArrayList<>();
        expectedResult3.add(29105);
        assertEquals(expectedResult3, SILab2.function(timeList6));

        //FT
        Time time8 = new Time( 8,00,61);
        List<Time> timeList8 = new ArrayList<>();
        timeList8.add(time8);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList8));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        //FX
        Time time9 = new Time( 8,00,-05);
        List<Time> timeList9 = new ArrayList<>();
        timeList9.add(time9);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList9));
        assertTrue(ex.getMessage().contains("The seconds are not valid"));

        //if (hr == 24 && min == 0 && sec == 0)
        //TTT
        Time time10 = new Time(24,00,00);
        List<Time> timeList10 = new ArrayList<>();
        timeList10.add(time10);
        List<Integer> expectedResult4 = new ArrayList<>();
        expectedResult4.add(86400);
        assertEquals(expectedResult4, SILab2.function(timeList10));

        //TTF
        Time time11 = new Time(24,00,05);
        List<Time> timeList11 = new ArrayList<>();
        timeList11.add(time11);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList11));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

        //TFX
        Time time12 = new Time(24,05,05);
        List<Time> timeList12 = new ArrayList<>();
        timeList12.add(time12);
        ex = assertThrows(RuntimeException.class, () -> SILab2.function(timeList12));
        assertTrue(ex.getMessage().contains("The time is greater than the maximum"));

        //FXX
        // ne e voznomozno,
        //nikogash nema da dojde vo ovoj uslov za bilo koja vrednost na hr, ke vleze vo prethodnite uslovi


    }

}