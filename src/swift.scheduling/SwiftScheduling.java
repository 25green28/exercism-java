package swift.scheduling;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

import static java.time.temporal.TemporalAdjusters.*;

public class SwiftScheduling {
    public static LocalDateTime convertToDeliveryDate(LocalDateTime meetingStart, String description) {
        boolean nMonthPattern = description.matches("\\d+M");
        boolean nQuarterPattern = description.matches("Q\\d+");

        if (nMonthPattern || nQuarterPattern) {
            int numberParam = Integer.parseInt(description.replaceAll("\\D", ""));

            return nMonthPattern ? nMonth(meetingStart, numberParam) : nQuarter(meetingStart, numberParam);
        }

        switch (description) {
            case "NOW" -> {
                return meetingStart.plusHours(2);
            }
            case "ASAP" -> {
                if (meetingStart.getHour() < 13) {
                    return meetingStart.withHour(17).withMinute(0);
                }
                return meetingStart.plusDays(1).withHour(13).withMinute(0);
            }
            case "EOW" -> {
                Set<DayOfWeek> monTueWed = Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY);
                if (monTueWed.contains(meetingStart.getDayOfWeek())) {
                    return meetingStart.with(next(DayOfWeek.FRIDAY)).withHour(17).withMinute(0);
                }
                return meetingStart.with(next(DayOfWeek.SUNDAY)).withHour(20).withMinute(0);
            }
        }

        throw new IllegalArgumentException("Cannot calculate date!");
    }

    private static LocalDateTime nMonth(LocalDateTime meetingStart, int numberFromDesc) {
        LocalDateTime customDeliveryDate =  meetingStart
                .withMonth(numberFromDesc)
                .with(firstDayOfMonth())
                .withHour(8)
                .withMinute(0);

        if (meetingStart.getMonthValue() >= numberFromDesc) {
            customDeliveryDate = customDeliveryDate.plusYears(1);
        }

        if (customDeliveryDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                customDeliveryDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            customDeliveryDate = customDeliveryDate.with(next(DayOfWeek.MONDAY));
        }

        return customDeliveryDate;
    }

    private static LocalDateTime nQuarter(LocalDateTime meetingStart, int numberFromDesc) {
        int lastMonthOfQuarter = numberFromDesc * 3;
        LocalDateTime customDeliveryDate = meetingStart
                .withMonth(lastMonthOfQuarter)
                .withHour(8)
                .with(lastDayOfMonth())
                .withMinute(0);

        if (meetingStart.getMonthValue() > lastMonthOfQuarter) {
            customDeliveryDate = customDeliveryDate.plusYears(1);
        }

        if (customDeliveryDate.getDayOfWeek() == DayOfWeek.SATURDAY ||
                customDeliveryDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            customDeliveryDate = customDeliveryDate.with(previous(DayOfWeek.FRIDAY));
        }

        return customDeliveryDate;
    }
}
