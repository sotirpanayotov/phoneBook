package com.panayotov.phoneBook;

public class Record implements Comparable<Record>{

    private String name;
    private String number;

    public Record(String name, String number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return name + " -> " + number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int compareTo(Record otherRecord) {
        return name.compareTo(otherRecord.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (name != null ? !name.equals(record.name) : record.name != null) return false;
        return number != null ? number.equals(record.number) : record.number == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}
