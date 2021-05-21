package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();

        File src = new File("/home/yuri/Рабочий стол/Games/src");
        if (src.mkdir()) {
            stringBuilder.append("Каталог src создан" + '\n');
        }
        File res = new File("/home/yuri/Рабочий стол/Games/res");
        if (res.mkdir()) {
            stringBuilder.append("Каталог res создан" + '\n');
        }
        File savegames = new File("/home/yuri/Рабочий стол/Games/savegames");
        if (savegames.mkdir()) {
            stringBuilder.append("Каталог savegames создан" + '\n');
        }
        File temp = new File("/home/yuri/Рабочий стол/Games/temp");
        if (temp.mkdir()) {
            stringBuilder.append("Каталог temp создан" + '\n');
        }
        File file1 = new File("/home/yuri/Рабочий стол/Games/src/main");
        if (file1.mkdir()) {
            stringBuilder.append("Каталог main создан" + '\n');
        }
        File file2 = new File("/home/yuri/Рабочий стол/Games/src/java");
        if (file2.mkdir()) {
            stringBuilder.append("Каталог java создан" + '\n');
        }
        File file3 = new File("/home/yuri/Рабочий стол/Games/src/main/Main.java");
        try {
            if (file3.createNewFile()) {
                stringBuilder.append("Файл Main.java создан" + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file4 = new File("/home/yuri/Рабочий стол/Games/src/main/Utils.java");
        try {
            if (file4.createNewFile()) {
                stringBuilder.append("Файл Utils.java создан" + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File fileRes1 = new File("/home/yuri/Рабочий стол/Games/res/vectors");
        if (fileRes1.mkdir()) {
            stringBuilder.append("Каталог vectors  в каталоге res создан" + '\n');
        }
        File fileRes2 = new File("/home/yuri/Рабочий стол/Games/res/icons");
        if (fileRes2.mkdir()) {
            stringBuilder.append("Каталог icons  в каталоге res создан" + '\n');
        }
        File drawables = new File("/home/yuri/Рабочий стол/Games/res/drawables");
        if (drawables.mkdir()) {
            stringBuilder.append("Каталог drawables в каталоге res создан" + '\n');
        }
        File file5 = new File("/home/yuri/Рабочий стол/Games/temp/temp.txt");
        try {
            if (file5.createNewFile()) {
                stringBuilder.append("Файл temp.java создан" + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //запись сведений в файл temp
        try (FileWriter fileWriter = new FileWriter("/home/yuri/Рабочий стол/Games/temp/temp.txt")) {
            fileWriter.write(String.valueOf(stringBuilder));
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //создание объктов...
        GameProgress gameProgress1 = new GameProgress(100, 3, 6, 25.6);
        GameProgress gameProgress2 = new GameProgress(80, 6, 10, 45.3);
        GameProgress gameProgress3 = new GameProgress(45, 8, 15, 70.5);

        //сохранение состояния объектов...
        OperationFiles.saveGame("/home/yuri/Рабочий стол/Games/savegames/save1.dat", gameProgress1);
        OperationFiles.saveGame("/home/yuri/Рабочий стол/Games/savegames/save2.dat", gameProgress2);
        OperationFiles.saveGame("/home/yuri/Рабочий стол/Games/savegames/save3.dat", gameProgress3);

        //добавление путей к сохраннениям в список
        List<String> filenames = new ArrayList<>();
        filenames.add("/home/yuri/Рабочий стол/Games/savegames/save1.dat");
        filenames.add("/home/yuri/Рабочий стол/Games/savegames/save2.dat");
        filenames.add("/home/yuri/Рабочий стол/Games/savegames/save3.dat");

        //архивация сохранений
        OperationFiles.zipFiles("/home/yuri/Рабочий стол/Games/savegames/zip.zip", filenames);
        OperationFiles.zipFiles("/home/yuri/Рабочий стол/Games/savegames/zip.zip", filenames);
        OperationFiles.zipFiles("/home/yuri/Рабочий стол/Games/savegames/zip.zip", filenames);

        //удаление сохранений не входящих в архив
        File fileDel1 = new File("/home/yuri/Рабочий стол/Games/savegames", "save1.dat");
        File fileDel2 = new File("/home/yuri/Рабочий стол/Games/savegames", "save2.dat");
        File fileDel3 = new File("/home/yuri/Рабочий стол/Games/savegames", "save3.dat");
        fileDel1.delete();
        fileDel2.delete();
        fileDel3.delete();

        //распаковка архива
        OperationFiles.openZip("/home/yuri/Рабочий стол/Games/savegames/zip.zip", "/home/yuri/Рабочий стол/Games/temp");

        //вывод на экран распакованных сохранений
        System.out.println(OperationFiles.openProgress("/home/yuri/Рабочий стол/Games/temp/save1.dat").toString());
        System.out.println(OperationFiles.openProgress("/home/yuri/Рабочий стол/Games/temp/save2.dat").toString());
        System.out.println(OperationFiles.openProgress("/home/yuri/Рабочий стол/Games/temp/save3.dat").toString());
    }
}


