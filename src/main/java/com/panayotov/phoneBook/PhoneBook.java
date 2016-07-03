package com.panayotov.phoneBook;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

    private List<Record> recordList = new ArrayList<>();
    private Map<Record, Integer> map = new HashMap<>();

    public void add(Record record) {
        String name = record.getName();
        if (StringUtils.isBlank(name)) {
            return;
        }

        Pattern pattern = Pattern.compile("^(\\+359|00359|0)8(7|8|9)[2-9][0-9]{6}");
        Matcher matcher = pattern.matcher(record.getNumber());
        if (!matcher.matches()) {
            return;
        }

        recordList.add(record);
    }

    public void delete(String name){
        for (int i = 0; i < recordList.size(); i++) {
            Record record = recordList.get(i);

            if (name.equals(record.getName())){
                recordList.remove(record);
            }
        }
    }

    public String findNumber(String name){
        for (Record record : recordList) {
            if (name.equals(record.getName())){

                updateMostSearched(record);

                return record.getNumber();
            }
        }
        return null;
    }

    public void printRecords() {
        Collections.sort(recordList);

        for (Record record : recordList) {
            System.out.println(record);
        }
    }

    public void load(File file) {
        try {
            List<String> rows = FileUtils.readLines(file, "UTF-8");
            for (String row : rows) {
                String[] array = row.split(",");
                String name = array[0];
                String phoneNumber = array[1];

                Record record = new Record(name, phoneNumber);
                add(record);
            }
        } catch (IOException e) {
            System.out.println("file Not found");
        }
    }

    public void printFiveMostSearchedPhoneNumbers(){
        List<Map.Entry<Record, Integer>> records = ValueComparator.keysSortedByValue(map);

        int i = 1;
        for (Map.Entry<Record, Integer> entry : records) {
            Record record = entry.getKey();
            System.out.println(record.getName() + " -> " + record.getNumber());
            if(i > 5){
                return;
            } else {
                i ++;
            }
        }
    }

    private void updateMostSearched(Record record) {
        Integer numberOfInquiries = map.get(record);
        if(numberOfInquiries == null){
            map.put(record, 1);
        } else {
            map.put(record, numberOfInquiries + 1);
        }
    }
}

