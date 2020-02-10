package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ReverseTrainAdventure {

    public static void main(String args[]) {
        ReverseTrainAdventure.train();
    }

    public static int[] readFile(){
        BufferedReader in = null;
        int[] station = new int[0];
        try {
            File file = new File("File/index.txt");
            in = new BufferedReader(new FileReader(file));
            String str = in.readLine();
            String[] strArray = str.split(" ");
            station = new int[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                station[i] = Integer.parseInt(strArray[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return station;
    }

    public static void train(){
        int[] station =  ReverseTrainAdventure.readFile();
        LinkedHashSet<Set<Integer>> journey = new LinkedHashSet<>();
        int next;
        int count = 0;
        for (int i = 1; i < station.length; i++) {
            LinkedHashSet<Integer> possible = new LinkedHashSet<>();
            // store the first station number
            possible.add(i);
            // if the first station is odd, count++
            if (i % 2 != 0) {
                count += 1;
            }
            // set the value to next variable as second station
            next = station[i];
            // store the second station number
            possible.add(next);
            // if the second station is odd, count++
            if (next % 2 != 0) {
                count += 1;
            }
            // the in the first 2 station only one station is odd, then we can go to next station
            if (count <= 1) {
                // while next station is not 0 which is start station, then we can keep going to next
                // if the next station is 0, which means this path is already finished
                while(next != 0) {
                    // create a array list which can contain repeated station numbers
                    ArrayList<Integer> repeated = new ArrayList<>(possible);
                    // go to next station
                    next = station[next];
                    // if the possible set does not have 2 odd numbers or duplicate numbers
                    // the possible set will end up with 0
                    possible.add(next);
                    repeated.add(next);
                    // if there is a odd station
                    if (next % 2 != 0){
                        // count++
                        count++;
                        // check if count > 1
                        if (count > 1) {
                            // because we did not contain the beginning station at the beginning
                            // we need to add 0 at the end of the set
                            possible.add(0);
                            // if count > 1, then break the loop, which means this path is already finished
                            break;
                        }
                    }
                    // if the repeated array list's length is longer than possible set, then break the the loop
                    if (repeated.size() > possible.size()) {
                        // because we did not contain the beginning station at the beginning
                        // we need to add 0 at the end of the set
                        possible.add(0);
                        break;
                    }
                }
            }
            // Set journey collect the paths
            journey.add(possible);
            // reset the count for the next path
            count = 0;
        }
        // to get the max length of paths
        int max = 0;
        for (Set<Integer> set : journey) {
            if (set.size() > max) {
                max = set.size();
            }
            System.out.println(set + " : " + set.size());
        }
        System.out.println(max);
    }

}
