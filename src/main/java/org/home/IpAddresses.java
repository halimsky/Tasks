package org.home;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Halim on 07.02.2017.
 */
public class IpAddresses {
    private static boolean ipIsCorrect;
    private static String error = "Ошибка! Введите заново";
    private static List<Integer> firstAddressBits = new ArrayList<Integer>();
    private static List<Integer> secondAddressBits = new ArrayList<Integer>();

    private static long startAddress;
    private static long endAddress;

    private static List<String> allowValues = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        System.out.println("Введите начальный адрес");
        inputAddress(firstAddressBits);
        ipIsCorrect = false;
        System.out.println("Введите конечный адрес");
        inputAddress(secondAddressBits);
        createListValues(firstAddressBits, secondAddressBits);
        allowValues.remove(0);
        printTest(allowValues);
    }


    //ввод адресов
    public static void inputAddress(List<Integer> list) throws IOException {
        while (!ipIsCorrect) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String address = br.readLine().trim();
            ipIsCorrect = checkAddress(address, list);
        }
    }

    //проверка адреса
    public static boolean checkAddress(String address, List<Integer> list) {
        int countDots = countDots(address);
        String[] bits = address.split("\\.");
        if (countDots == 3 && bits.length == 4) {
            for (int i = 0; i < bits.length; i++) {
                if (!checkBit(bits[i])) {
                    System.out.println(error);
                    return false;
                }
                int number = Integer.parseInt(bits[i]);
                if (inRange(number)) {
                    list.add(number);
                } else {
                    System.out.println(error);
                    return false;
                }
            }
            return true;
        } else {
            System.out.println(error);
            return false;
        }
    }

    //проверка бита на число
    public static boolean checkBit(String bit) {
        char[] bitSymbols = bit.toCharArray();
        for (int i = 0; i < bitSymbols.length; i++) {
            if (!Character.isDigit(bitSymbols[i])) {
                return false;
            }
        }
        return true;
    }

    //проверка диапазона
    public static boolean inRange(int number) {
        if (number >= 0 && number <= 255) {
            return true;
        }
        return false;
    }

    //кол-во точек
    public static int countDots(String address) {
        int count = 0;
        char[] arraySymbols = address.toCharArray();
        for (int i = 0; i < address.length(); i++) {
            if (arraySymbols[i] == '.') {
                count++;
            }
        }
        return count;
    }

    public static void printTest(List<String> list) {
        for (String i : list) {
            System.out.println(i);
        }

        /*try {
            FileWriter writer = new FileWriter("ipshki.txt", false);
            try {
                for (String i : list) {
                    System.out.println(i);
                    writer.write(i);
                    writer.append('\n');
                }
                writer.flush();
            } finally {
                writer.close();
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }*/
    }

    public static void createListValues(List<Integer> firstAddressBits, List<Integer> secondAddressBits) {
        int firstBit = firstAddressBits.get(0);
        int secondBit = firstAddressBits.get(1);
        int thirdBit = firstAddressBits.get(2);
        int fourthBit = firstAddressBits.get(3);

        for (; firstBit < secondAddressBits.get(0); firstBit++) {
            for (; secondBit <= 255; secondBit++) {
                for (; thirdBit <= 255; thirdBit++) {
                    for (; fourthBit <= 255; fourthBit++) {
                        allowValues.add(toIp(firstBit, secondBit, thirdBit, fourthBit));
                    }
                    fourthBit = 0;
                }
                thirdBit = 0;
            }
            secondBit = 0;
        }

        for (; secondBit < secondAddressBits.get(1); secondBit++) {
            for (; thirdBit <= 255; thirdBit++) {
                for (; fourthBit <= 255; fourthBit++) {
                    allowValues.add(toIp(firstBit, secondBit, thirdBit, fourthBit));
                }
                fourthBit = 0;
            }
            thirdBit = 0;
        }
        for (; thirdBit < secondAddressBits.get(2); thirdBit++) {
            for (; fourthBit <= 255; fourthBit++) {
                allowValues.add(toIp(firstBit, secondBit, thirdBit, fourthBit));
            }
            fourthBit = 0;
        }
        for (; fourthBit < secondAddressBits.get(3); fourthBit++) {
            if (fourthBit != secondAddressBits.get(3)) {
                allowValues.add(toIp(firstBit, secondBit, thirdBit, fourthBit));
            }
        }
    }

    public static String toIp(int firstBit, int secondBit, int thirdBit, int fourthBit) {
        return firstBit + "." + secondBit + "." + thirdBit + "." + fourthBit;
    }
}
