package com.teamalphano.zombieboom.common;

import java.util.ArrayList;
import java.util.List;

public class CharStringEdit {

    // "[ 1 , 2 ]" 형태의 데이터 list로 return
    public List<Integer> getIntList(String listString){

        // 불필요한 문자 제거
        String cleanedString = listString.replaceAll("\\[|\\]", "");

        // 숫자 분리 및 리스트로 변환
        String[] numbers = cleanedString.split(",");
        List<Integer> integerList = new ArrayList<>();

        for (String number : numbers) {
            integerList.add(Integer.parseInt(number.trim()));
        }

        return integerList;
    }

    public String getListToString(List<Integer> integerList){
        //json 변환
        String jsonString = "[";
        for (int i = 0; i < integerList.size(); i++) {
            jsonString += integerList.get(i);
            if (i < integerList.size() - 1) {
                jsonString += ",";
            }
        }
        jsonString += "]";
        return jsonString;
    }
}
