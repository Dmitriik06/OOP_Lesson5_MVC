package personal.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NewFileOperation extends FileOperationImpl implements FileOperation{


    public NewFileOperation(String fileName) {
        super(fileName);
    }

    @Override
    public List<String> readAllLines() {
        List<String> lines = new ArrayList<>();
        String temp;
        try {
            File file = new File(fileName);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            while (sc.hasNext()){
                temp = sc.next();
                if (!temp.isEmpty()){
                    lines.add(temp);
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public void saveAllLines(List<String> lines) {
        super.saveAllLines(lines);
    }
}
