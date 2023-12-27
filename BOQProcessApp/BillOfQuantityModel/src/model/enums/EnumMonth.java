package model.enums;

public enum EnumMonth {
    Jan(1),
    Feb(2),
    Mar(3),
    Apr(4),
    May(5),
    Jun(6),
    Jul(7),
    Aug(8),
    Sep(9),
    Oct(10),
    Nov(11),
    Dec(12);


    private int value;

    EnumMonth(int value) {
        this.value = value;
    }

    public void setValue(int val) {
        value = val;
    }

    public int getValue() {
        return value;
    }
}
