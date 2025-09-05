package net.tufinder.backend.enums;

public enum Day {
    SUNDAY(0),MONDAY(1),TUESDAY(2),WEDNESDAY(3),
    THURSDAY(4),FRIDAY(5),SATURDAY(6);

    private final Integer index;
    Day(Integer index){
        this.index = index;
    }

    public Day getDay(int index){
        for(Day day : Day.values()){
            if(day.index==index) return day;
        }

        return null;
    }

}
