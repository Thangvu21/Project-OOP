package Base;

import javafx.scene.chart.ScatterChart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;


public class Dictionary {

    private ArrayList<Word> dictionary_Array = new ArrayList<>();
    private TreeMap<String, String> dictionary_Map = new TreeMap<String, String>();
    private Scanner sc = new Scanner(System.in);
    private Connection connect;
//    try {
//        connect = Connect.connect();
//    } catch {
//
//    }
}