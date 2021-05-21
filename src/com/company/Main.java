package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static String mainPath = File.separator + "home" + File.separator + "yuri" + File.separator + "Рабочий стол" + File.separator + "Games";
    private static StringBuilder log = new StringBuilder();

    private static void createCatalog(String name) {
        File file = new File(mainPath + File.separator + name);
        if (file.mkdir()) {
            log.append("Каталог ").append(name).append(" создан").append('\n');
        }
    }

    private static void createFile(String name) {
        File file = new File(mainPath + File.separator + name);
        try {
            if (file.createNewFile()) {
                log.append("Файл ").append(name).append(" создан").append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Main.createCatalog("src");
        Main.createCatalog("res");
        Main.createCatalog("savegames");
        Main.createCatalog("temp");
        Main.createCatalog("src/main");
        Main.createCatalog("src/test");
        Main.createFile("src/main/Main.java");
        Main.createFile("src/main/Utils.java");
        Main.createCatalog("res/vectors");
        Main.createCatalog("res/icons");
        Main.createCatalog("res/drawables");
        Main.createFile("temp/temp.txt");

        //запись сведений в файл temp
        try (FileWriter fileWriter = new FileWriter("/home/yuri/Рабочий стол/Games/temp/temp.txt")) {
            fileWriter.write(String.valueOf(log));
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


