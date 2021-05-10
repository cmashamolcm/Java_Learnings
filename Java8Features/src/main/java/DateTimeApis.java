import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DateTimeApis {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println("Today is " + date);

        LocalDate birthDay = LocalDate.of(1991, 05, 24);
        System.out.println("Birthday is on " + birthDay);
        System.out.println("Formatted DOB = " + birthDay.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        //gives error as Feb has only till 28/29
        //LocalDate.of(1991, Month.FEBRUARY, 30);
        //error saying not a leap year
        //LocalDate.of(1991, Month.FEBRUARY, 29);

        LocalTime time  = LocalTime.now();
        System.out.println("Time now = " + time);
        LocalTime birthTime = LocalTime.of(15, 33, 33);
        System.out.println("Born at " + birthTime.format(DateTimeFormatter.ofPattern("hh::mm::ss")));//03/33/33

        System.out.println("Time at " + ZoneId.getAvailableZoneIds().stream().findFirst() + " is " + LocalTime.now(ZoneId.of(ZoneId.getAvailableZoneIds().stream().findFirst().get())));
        System.out.println("ZonedDateTime = " + ZonedDateTime.of(time.atDate(LocalDate.now()), ZoneId.of(ZoneId.getAvailableZoneIds().stream().findFirst().get())));

        System.out.println("Local Time to Local Date Time = " +  time.atDate(LocalDate.now()));
        System.out.println("Local Date to Local Date Time = " +  date.atTime(LocalTime.now()));

        System.out.println("Machine Readable Time= " + Instant.now()); //GMT time
        System.out.println("Machine to human readable = " + LocalDateTime.ofInstant(Instant.now(), ZoneId.of(ZoneId.getAvailableZoneIds().stream().findFirst().get())));

        System.out.println(LocalDate.parse("1992-10-30"));// default it takes yyyy-mm-dd format.
    }
}
