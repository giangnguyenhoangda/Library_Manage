/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Nguyen Hoang Giang
 */
public class NewClass {

    public static void main(String[] args) {
        // Định dạng thời gian
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        String inputString1 = "9/11/2017";
        String inputString2 = "11/11/2017";

        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();
            System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
