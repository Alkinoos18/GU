package com.company.check_in_request;

import java.io.*;
import java.nio.charset.Charset;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class checkInRequest {
    public int idRequest, idSender, test;
    public String descriptionRequest;
    public LocalDateTime dateRequest;
    public String valuta;
    public String typeRequest;
    public float price;
    public boolean inWork, isGod;
    public String pathFile;


    public checkInRequest(){
        test = 0;
        pathFile = "";
    }

    public static void write(String pathFile, String[] args){
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(pathFile)))){
            // Type
            // DateTime
            // DescriptionRequest
            writer.println(args[6]);

            String date = args[2]+" "+ args[3];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime DateRequest = LocalDateTime.parse(date, formatter);

            DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.MEDIUM);
            writer.println(DateRequest.format(formatter2));

            String DescriptionRequest="";
            for (int i = 0; i < Integer.parseInt(args[7]); i++) {
                DescriptionRequest = DescriptionRequest + args[i+8]+" ";
            }
            DescriptionRequest = DescriptionRequest.trim();
            writer.println(DescriptionRequest);

            //System.out.println(Charset.defaultCharset().displayName());
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }


     public void checkParam(String[] args) {
        int k = Integer.parseInt(args[7]);

        if (args.length < k+8) {
            System.out.println("В текущем запросе не хватает параметров для создания заявки!");
        }
        else if (args.length > k+8) {
            System.out.println("В текущем запросе слишком много параметров для создания заявки!");
        }
        else {
            try {
                int IdRequest = Integer.parseInt(args[0]);
                if (args[0].length() != 9){
                    System.out.println("The length of argument 1 (idRequest) is not 9 characters.");
                    System.exit(1);
                }
                //System.out.println(IdRequest);
            } catch (NumberFormatException e) {
                System.out.println("The argument 1 must be an integer.");
                System.exit(1);
            }
            try {
                int IdSender = Integer.parseInt(args[1]);
                if (args[1].length() != 9){
                    System.out.println("The length of argument 2 (idSender) is not 9 characters.");
                    System.exit(1);
                }
                //System.out.println(IdSender);
            } catch (NumberFormatException e) {
                System.out.println("The argument 2 must be an integer.");
                System.exit(1);
            }
            try {
                String date = args[2]+" "+ args[3];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime DateRequest = LocalDateTime.parse(date, formatter);
                LocalDateTime now = LocalDateTime.now();
                if (now.isAfter(DateRequest)) {
                    test++;
                }
                //System.out.println(DateRequest);
            } catch (DateTimeException e) {
                System.out.println("The argument 2 must be an date (yyyy-MM-dd) and argument 3 must be an Time (HH:mm:ss).");
                System.exit(1);
            }
            try {
                float Price = Float.parseFloat(args[4]);
                if (Price > 0) {
                    test++;
                }
                //System.out.println(Price);
            } catch (NumberFormatException e) {
                System.out.println("The argument 4 must be an float.");
                System.exit(1);
            }
            try {
                String Valuta = args[5];
                if (Valuta.isEmpty()) {
                    System.out.println("The argument 5 is empty");
                    System.exit(1);
                }
                else if (!Valuta.equalsIgnoreCase("RUB") ){
                    System.out.println("The argument 5 is not RUB");
                    System.exit(1);
                }
                //System.out.println(Valuta);
            } catch (NumberFormatException e) {
                System.out.println("The argument 5 is not RUB.");
                System.exit(1);
            }

            try {
                String TypeRequest = args[6];
                if (TypeRequest.isEmpty()) {
                    System.out.println("The argument 6 is empty");
                    System.exit(1);
                }
                switch (TypeRequest){
                    case "ESIA":
                        break;
                    case "SMEV":
                        break;
                    case "INTERNAL":
                        break;
                    default : {
                        System.out.println("The argument 6 is wrong");
                        System.exit(1);
                    }
                }
                //System.out.println(TypeRequest);
            } catch (NumberFormatException e) {
                System.out.println("The argument 6 is wrong.");
                System.exit(1);
            }

            try {
                String DescriptionRequest="";
                for (int i = 0; i < k; i++) {
                    DescriptionRequest = DescriptionRequest + args[i+8]+" ";
                }
                DescriptionRequest = DescriptionRequest.trim();
                //System.out.println(DescriptionRequest);
            } catch (NumberFormatException e) {
                System.out.println("The argument 2 must be an integer.");
                System.exit(1);
            }
        }

         switch (test){
             case 2:
                 new File(System.getProperty("user.dir")+"/"+ args[1]).mkdirs();
                 pathFile = System.getProperty("user.dir")+"/"+ args[1]+"/"+args[0];
                 write(pathFile, args);
                 System.out.println("По заданным параметрам создана новая заявка!");
                 System.out.println(System.getProperty("user.dir")+"/"+ args[1]+"/"+args[0]);
                 break;
             default:
                 System.out.println("Заявка не создана !");
                 System.exit(1);
         }

    }

}
