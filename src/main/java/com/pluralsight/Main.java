package com.pluralsight;

import static com.pluralsight.DataManager.myScanner;

public class Main {
    public static void main(String[] args) {
    DataManager.startConnection();
        System.out.println("""
                Hello User, What would you like to do ?
                1) Add a new Shipper
                2) Update a Shipper
                3) Delete a Shipper
                0) Exit""");
        String userChoice = myScanner.nextLine();
        switch (userChoice){
            case "1": DataManager.newShipper();
            break;
            case "2":
                DataManager.updateShipper();
            break;
            case "3": DataManager.deleteShipper();
                break;
            case "0":
                System.out.println("Thank you and have a good day.");
                System.exit(0);
        }


    }
}
