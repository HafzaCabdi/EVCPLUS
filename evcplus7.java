import java.util.Scanner;

public class evcplus7 {
    // Variables-kan waxay kaydiyaan haraaga, PIN-ka, luuqadda, taariikhda 10-ka dhaqdhaqaaq
    // ee ugu dambeeyay, iyo macluumaadka wareejinta iyo iibsashada ugu dambaysay.
    static double balance = 7000.0;
    static String pin = "6789";
    static String language = "Somali";
    static String[] transactions = new String[10];
    static int transactionCount = 0;

    static String lastTransferNumber = "";
    static double lastTransferAmount = 0.0;
    static String lastPurchase = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Fadlan geli *770# si aad u gasho: ");
        String code = sc.nextLine();
        if (!code.equals("*770#")) {
            System.out.println(" Khalad! Fadlan geli *770# si sax ah.");
            return;
        }

        // Hubinta in user-ku galo *770# ka hor inta uusan geli karin PIN-ka,
        // kadibna PIN-ka waxaa la ogaanayaa ilaa 3 isku day; haddii 3 jeer PIN khaldan la geliyo, barnaamijka wuu xirmayaa.
        int pinAttempts = 0;
        while (pinAttempts < 3) {
            System.out.print("Fadlan geli PIN-kaaga: ");
            String inputPin = sc.nextLine();
            if (inputPin.equals(pin)) {
                // PIN sax ah, geli menu-ga
                break;
            } else {
                pinAttempts++;
                if (pinAttempts < 3) {
                    System.out.println(" PIN khaldan. Fadlan isku day mar kale.");
                }
            }
        }

        if (pinAttempts == 3) {
            System.out.println(" Waxaad gelisay PIN khaldan 3 jeer. Barnaamijka wuu xirmayaa.");
            return;
        }


        // Loop-kan wuxuu ku celcelinayaa muujinta menu-ga ilaa user-ku uu doorto in uu ka baxo;
       // markaasi barnaamijka wuu istaagayaa kadibna mahadnaq ayaa la soo bandhigayaa.

        while (true) {
            if (!showMainMenu(sc)) {
                break;
            }
        }

        System.out.println(" Mahadsanid isticmaalka EVCPlus.");
    }


    // Muujinta menu-ga EVCPlus iyo aqbalida doorashada user-ka;
    // haddii doorasho sax ah la geliyo, hawsha la xiriirta ayaa la fuliyaa;
    // haddii doorasho khaldan la geliyo, fariin ayaa la tusaa oo barnaamijka wuu xirmayaa.

    static boolean showMainMenu(Scanner sc) {
        System.out.println("\n EVCPlus Menu:");
        System.out.println("1. Itus Haraaga");
        System.out.println("2. Kaarka Hadalka");
        System.out.println("3. Bixi Biil");
        System.out.println("4. U wareeji EVCPlus");
        System.out.println("5. Warbixin Kooban");
        System.out.println("6. Salaam Bank");
        System.out.println("7. Maareynta");
        System.out.println("8. Bill Payment");
        System.out.print(" Doorasho: ");


        String input = sc.nextLine();
        int choice;

        try {
            choice = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println(" Fadlan geli doorasho sax ah.");
            return true;
        }

        switch (choice) {
            case 1 -> showBalance();
            case 2 -> kaarkaHadalka(sc);
            case 3 -> bixiBiil(sc);
            case 4 -> wareejiEVC(sc);
            case 5 -> warbixinKooban(sc);
            case 6 -> salaamBank(sc);
            case 7 -> maareynta(sc);
            case 8 -> billPayment(sc);
            default -> {
                System.out.println(" Doorasho khaldan. Barnaamijka wuu xirmayaa.");
                return false; // Exit program
            }
        }

        // Haddii hawsha la qabtay, dib menu soo celi
        return true;
    }

    static void showBalance() {
        // Muujinta haraaga user-ka iyo diiwaangelinta dhaqdhaqaaqaas.
        System.out.println(" Haraagaagu waa: $" + balance);
        addTransaction("Eegay haraaga: $" + balance);
    }

    // Muujinta submenu-ka Kaarka Hadalka iyo aqbalida doorashada user-ka;
 // haddii doorasho aan sax ahayn la geliyo, fariin khalad ayaa la soo bandhigaa oo hawshu waa la joojinayaa.

    static void kaarkaHadalka(Scanner sc) {
        System.out.println("\n Kaarka Hadalka:");
        System.out.println("1. Ku shubo AirTime");
        System.out.println("2. Ugu shub qof kale");
        System.out.println("3. MIFI Packages");
        System.out.println("4. Ku shubo Internet");
        System.out.println("5. Uu shub qof kale (MMT)");
        System.out.print("Doorasho: ");
        int subChoice = 0;
        try {
            subChoice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println(" Doorasho khaldan.");
            return;
        }

        // Fulinta doorashada Kaarka Hadalka iyadoo lagu saleynayo qiimaha subChoice;
  // lacagaha kala duwan sida AirTime, MIFI, Internet, iyo wareejinta qof kale ayaa laga jarayaa balance-ka;
  // sidoo kale waxaa la diiwaangelinayaa dhaqdhaqaaqyada ku habboon;
  // haddii lacagtu ku filnaato haraaga, hawsha waa la qabtaa, haddii kale fariin khalad ayaa la soo bandhigaa;
  // haddii doorasho aan jirin la geliyo, fariin khalad ayaa la tusayaa.
        switch (subChoice) {
            case 1 -> {
                System.out.print("Geli qadarka aad rabto in aad ku shubato AirTime: $");
                double amount = sc.nextDouble();
                sc.nextLine();
                if (amount <= balance) {
                    balance -= amount;
                    System.out.println(" Waxaa laguu shubay AirTime: $" + amount);
                    addTransaction("Ku shubtay AirTime: -$" + amount);
                    lastPurchase = "AirTime -$" + amount;
                } else {
                    System.out.println(" Haraagaagu kuguma filna.");
                }
            }
            case 2 -> {
                System.out.print("Geli number-ka qofka: ");
                String number = sc.nextLine();
                System.out.print("Geli qadarka aad rabto in aad ugu shubto: $");
                double amount = sc.nextDouble();
                sc.nextLine();
                if (amount <= balance) {
                    balance -= amount;
                    System.out.println(" $" + amount + " ayaa loo shubay " + number);
                    addTransaction("Ugu shubay qof kale: " + number + " -$" + amount);
                    lastTransferNumber = number;
                    lastTransferAmount = amount;
                    lastPurchase = "Transfer -$" + amount;
                } else {
                    System.out.println(" Haraagaagu kuguma filna.");
                }
            }
            case 3 -> {
                System.out.print("Geli qadarka MIFI Package: $");
                double amount = sc.nextDouble();
                sc.nextLine();
                if (amount <= balance) {
                    balance -= amount;
                    System.out.println(" MIFI Package $" + amount + " ayaa la helay.");
                    addTransaction("Ku shubtay MIFI Package: -$" + amount);
                    lastPurchase = "MIFI Package -$" + amount;
                } else {
                    System.out.println(" Haraagaagu kuguma filna.");
                }
            }
            case 4 -> {
                System.out.print("Geli qadarka Internet-ka: $");
                double amount = sc.nextDouble();
                sc.nextLine();
                if (amount <= balance) {
                    balance -= amount;
                    System.out.println(" Internet $" + amount + " ayaa laguu shubay.");
                    addTransaction("Ku shubtay Internet: -$" + amount);
                    lastPurchase = "Internet -$" + amount;
                } else {
                    System.out.println(" Haraagaagu kuguma filna.");
                }
            }
            case 5 -> {
                System.out.print("Geli number-ka qofka MMT: ");
                String number = sc.nextLine();
                System.out.print("Geli qadarka aad rabto in aad u shubto: $");
                double amount = sc.nextDouble();
                sc.nextLine();
                if (amount <= balance) {
                    balance -= amount;
                    System.out.println(" $" + amount + " ayaa loo diray (MMT) " + number);
                    addTransaction("MMT - U shub qof kale: " + number + " -$" + amount);
                    lastTransferNumber = number;
                    lastTransferAmount = amount;
                    lastPurchase = "MMT Transfer -$" + amount;
                } else {
                    System.out.println(" Haraagaagu kuguma filna.");
                }
            }
            default -> System.out.println(" Doorasho khaldan.");
        }
    }

    static void bixiBiil(Scanner sc) {
        System.out.println("\n Bixi Biil:");
        System.out.println("1. Post Paid");
        System.out.println("2. Ku libso");
        System.out.print("Doorasho: ");
        int billChoice = 0;
        try {
            billChoice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println(" Doorasho khaldan.");
            return;
        }

        System.out.print("Geli qadarka biilka: $");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (amount <= balance) {
            balance -= amount;
            String type = (billChoice == 1) ? "Post Paid" : "Ku libso";
            System.out.println(" Biilka " + type + " waa la bixiyay.");
            addTransaction("Biil bixiyay: " + type + " -$" + amount);
            lastPurchase = "Bill " + type + " -$" + amount;
        } else {
            System.out.println(" Haraagaagu kuguma filna.");
        }
    }

     // Muujinta submenu-ga bixinta biilka, helida doorashada nooca biilka (Post Paid ama Ku libso),
     // kadibna lacagta laga jarayo haraaga haddii ku filan tahay,
    // ugu dambeyn diiwaangelinta dhaqdhaqaaqa bixinta biilka.
    static void wareejiEVC(Scanner sc) {
        System.out.print("\n Geli number-ka aad u wareejinayso: ");
        String number = sc.nextLine();

        if (number.length() != 10 || !(number.startsWith("061") || number.startsWith("077"))) {
            System.out.println(" Lambarka la geliyay ma ahan mid jira.");
            return;
        }

        System.out.print(" Geli qadarka: $");
        double amount = sc.nextDouble();
        sc.nextLine();

        if (amount <= balance) {
            balance -= amount;
            System.out.println(" $" + amount + " ayaa loo wareejiyay " + number);
            System.out.println(" Haraagaaga cusub waa: $" + balance);

            lastTransferNumber = number;
            lastTransferAmount = amount;

            addTransaction("Wareejiyay EVC: " + number + " -$" + amount);
        } else {
            System.out.println(" Haraagaaga kugu ma filna.");
        }
    }


    // Muujinta submenu-ga Warbixin Kooban, kaas oo user-ku ka dooran karo inuu arko dhaqdhaqaaqa ugu dambeeyay,
    // wareejintii ugu dambeysay, iibsashadii ugu dambeysay, saddexda dhaqdhaqaaq ee ugu dambeeyay,
    // ama inuu helo email mock ah oo ku saabsan taariikhda
    // dhaqdhaqaaqyada; sidoo kale wuxuu xakameynayaa doorashooyinka khaldan.
    static void warbixinKooban(Scanner sc) {
        System.out.println("\n Warbixin Kooban:");
        System.out.println("1. Last Action");
        System.out.println("2. Wareejintii u dambaysay");
        System.out.println("3. Iibsashadii u dambeysay");
        System.out.println("4. Last 3 Actions");
        System.out.println("5. Email me my activity (mock)");
        System.out.print("Doorasho: ");

        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println(" Doorasho khaldan.");
            return;
        }

        switch (choice) {
            case 1 -> {
                if (transactionCount == 0)
                    System.out.println(" Ma jirto wax dhaqdhaqaaq ah.");
                else
                    System.out.println(" Last Action: " + transactions[transactionCount - 1]);
            }
            case 2 -> {
                if (lastTransferNumber.isEmpty())
                    System.out.println(" Ma jirto wareejin hore.");
                else
                    System.out.println(" Wareejintii u dambaysay: $" + lastTransferAmount + " to " + lastTransferNumber);
            }
            case 3 -> {
                if (lastPurchase.isEmpty())
                    System.out.println(" Ma jirto iibsasho hore.");
                else
                    System.out.println(" Iibsashadii u dambeysay: " + lastPurchase);
            }
            case 4 -> {
                System.out.println(" Last 3 Actions:");
                int start = Math.max(0, transactionCount - 3);
                for (int i = start; i < transactionCount; i++) {
                    System.out.println(" - " + transactions[i]);
                }
            }
            case 5 -> System.out.println(" Emailkaaga waxaa loo soo diray taariikhda dhaqdhaqaaqyada. (Mock)");
            default -> System.out.println(" Doorasho khaldan.");
        }
    }

    static void salaamBank(Scanner sc) {
        System.out.println("\n Salaam Bank:");
        System.out.println("1. Itus Haraaga");
        System.out.println("2. Lacag dhigasho");
        System.out.println("3. Lacag qaadasho");
        System.out.println("4. Ka wareeji EVCPlus");
        System.out.print("Doorasho: ");
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println(" Doorasho khaldan.");
            return;
        }

        switch (choice) {
            case 1 -> {
                System.out.println(" Salaam Bank Haraagaagu waa: $" + balance);
                addTransaction("Salaam Bank: Eegay haraaga");
            }
            case 2 -> {
                System.out.print("Geli qadarka aad rabto inaad dhigato: $");
                double amount = sc.nextDouble();
                sc.nextLine();
                balance += amount;
                System.out.println(" $" + amount + " ayaa lagu dhigay akoonka Salaam Bank.");
                addTransaction("Salaam Bank: Lacag dhigasho +$" + amount);
            }
            case 3 -> {
                System.out.print("Geli qadarka aad rabto inaad ka qaadato: $");
                double amount = sc.nextDouble();
                sc.nextLine();
                if (amount <= balance) {
                    balance -= amount;
                    System.out.println(" $" + amount + " ayaa lagaa qaaday akoonka Salaam Bank.");
                    addTransaction("Salaam Bank: Lacag qaadasho -$" + amount);
                } else {
                    System.out.println(" Lacag kugu filan ma hayso.");
                }
            }
            case 4 -> {
                System.out.print("Geli number-ka aad u wareejinayso (Salaam Bank): ");
                String number = sc.nextLine();
                System.out.print("Geli qadarka: $");
                double amount = sc.nextDouble();
                sc.nextLine();
                if (amount <= balance) {
                    balance -= amount;
                    System.out.println(" $" + amount + " ayaa loo wareejiyay " + number + " (Salaam Bank).");
                    addTransaction("Salaam Bank: Wareejiyay " + number + " -$" + amount);
                    lastTransferNumber = number;
                    lastTransferAmount = amount;
                } else {
                    System.out.println(" Lacag kugu filan ma hayso.");
                }
            }
            default -> System.out.println(" Doorasho khaldan.");
        }
    }

    // Muujinta submenu-ga Salaam Bank oo ay ku jiraan:
     // 1) Eegista haraaga,
     // 2) Lacag dhigasho,
    // 3) Lacag qaadasho,
     // 4) Wareejinta lacag EVCPlus gudaha Salaam Bank,
     // iyadoo la hubinayo lacag ku filan iyo diiwaangelinta dhaqdhaqaaqyada;
    // sidoo kale xakameynaya doorashooyinka khaldan.
    static void maareynta(Scanner sc) {
        System.out.println("\n Maareynta:");
        System.out.println("1. Bedel lambarka sirta ah");
        System.out.println("2. Bedel luuqadda");
        System.out.println("3. Wargelin mobile lumay");
        System.out.println("4. Lacag xirasho");
        System.out.println("5. U celi lacag qaldantay");
        System.out.print("Doorasho: ");
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println(" Doorasho khaldan.");
            return;
        }

        switch (choice) {
            case 1 -> {
                System.out.print("Geli PIN cusub (4 digit): ");
                String newPin = sc.nextLine();
                if (newPin.length() == 4 && newPin.matches("\\d{4}")) {
                    pin = newPin;
                    System.out.println(" PIN waa la beddelay.");
                    addTransaction("Bedelay PIN");
                } else {
                    System.out.println(" PIN-ga waa inuu ahaadaa 4 digit.");
                }
            }
            case 2 -> {
                System.out.println("Luuqadaha la heli karo:");
                System.out.println("1. Somali");
                System.out.println("2. English");
                System.out.print("Doorasho: ");
                int langChoice = 0;
                try {
                    langChoice = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println(" Doorasho khaldan.");
                    return;
                }
                if (langChoice == 1) {
                    language = "Somali";
                    System.out.println(" Luuqadda waa la beddelay oo hadda waa Somali.");
                } else if (langChoice == 2) {
                    language = "English";
                    System.out.println(" Language changed to English.");
                } else {
                    System.out.println(" Doorasho khaldan.");
                }
                addTransaction("Bedelay luuqadda");
            }
            case 3 -> {
                System.out.println(" Wargelin: Fadlan la xiriir adeeg bixiyahaaga haddii mobile-kaagu lumay.");
                addTransaction("Wargelin mobile lumay");
            }
            case 4 -> {
                System.out.println(" Lacag xirasho lama taageero (Mock).");
                addTransaction("Lacag xirasho (Mock)");
            }
            case 5 -> {
                System.out.println("↩U celi lacag qaldantay (Mock).");
                addTransaction("U celi lacag qaldantay (Mock)");
            }
            default -> System.out.println(" Doorasho khaldan.");
        }
    }

    // Muujinta submenu-ga Bill Payment oo ay ku jiraan:
    // 1) Eegista haraaga bill-ka iyadoo la isticmaalayo Reference Number,
    // 2) Bixinta bill-ka oo dhan,
   // 3) Bixinta qayb ka mid ah bill-ka,
   // iyadoo la hubinayo lacag ku filan iyo diiwaangelinta dhaqdhaqaaqyada; s
  // idoo kale xakameynaya doorashooyinka khaldan.
    static void billPayment(Scanner sc) {
        System.out.println("\n Bill Payment:");
        System.out.println("1. Itus Haraaga Bill-ka");
        System.out.println("2. Wada bixi Bill");
        System.out.println("3. Qayb ka bixi Bill-ka");
        System.out.print("Doorasho: ");
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println(" Doorasho khaldan.");
            return;
        }

        System.out.print("Fadlan gali Bill Reference Number: ");
        String refNumber = sc.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.println(" Haraaga Bill-kaaga (" + refNumber + ") waa: $" + balance);
                addTransaction("Eegay haraaga Bill-ka: Ref#" + refNumber);
            }
            case 2 -> {
                System.out.print("Geli qadarka Bill-ka oo dhan: $");
                double amount = sc.nextDouble();
                sc.nextLine();
                if (amount <= balance) {
                    balance -= amount;
                    System.out.println(" Bill-ka (" + refNumber + ") waa la wada bixiyay.");
                    addTransaction("Wada bixiyay Bill-ka Ref#" + refNumber + " -$" + amount);
                    lastPurchase = "Bill full -$" + amount;
                } else {
                    System.out.println(" Haraagaagu kuguma filna.");
                }
            }
            case 3 -> {
                System.out.print("Geli qadarka aad qayb ka bixinayso: $");
                double amount = sc.nextDouble();
                sc.nextLine();
                if (amount <= balance) {
                    balance -= amount;
                    System.out.println(" Qayb ka bixinta Bill-ka (" + refNumber + ") waa la sameeyay.");
                    addTransaction("Qayb bixiyay Bill-ka Ref#" + refNumber + " -$" + amount);
                    lastPurchase = "Bill partial -$" + amount;
                } else {
                    System.out.println(" Haraagaagu kuguma filna.");
                }
            }
            default -> System.out.println(" Doorasho khaldan.");
        }
    }

    // Ku darista dhaqdhaqaaqa cusub ee liiska transactions; haddii liiska buuxsamo,
    // waxay ka saaraysaa kan ugu horreeya si loo haysto 10-kii dhaqdhaqaaq ee ugu dambeeyay.
    static void addTransaction(String desc) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount++] = desc;
        } else {
            // Shift left to keep last 10 transactions
            for (int i = 1; i < transactions.length; i++) {
                transactions[i - 1] = transactions[i];
            }
            transactions[transactions.length - 1] = desc;
        }
    }
}
