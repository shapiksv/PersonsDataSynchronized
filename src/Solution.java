import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);



        switch (args[0]) {

            case "-c":
                synchronized (allPeople) {
                    String name;
                    Date dates;
                    int dl = args.length;
                    String sexx;

                    for (int i = 1; i < dl; ) {
                        name = args[i++];
                        if (args[i++].equals("м")) {
                            sexx = "м";
                        } else {
                            sexx = "ж";
                        }
                        dates = format.parse(args[i++]);
                        if (sexx.equals("м")) {
                            allPeople.add(Person.createMale(name, dates));
                            System.out.println(allPeople.size() - 1);
                        } else if (sexx.equals("ж")) {
                            allPeople.add(Person.createFemale(name, dates));
                            System.out.println(allPeople.size() - 1);
                        }

                    }
                }

                break;

            case ("-u"):
                synchronized (allPeople) {

                    int dl2 = args.length;
                    int id2;
                    String name2;
                    Date dates2;
                    String sexx2;
                    for (int i = 1; i < dl2; ) {

                        id2 = Integer.parseInt(args[i++]);
                        name2 = args[i++];
                        if (args[i++].equals("м")) {
                            sexx2 = "м";
                        } else {
                            sexx2 = "ж";
                        }
                        dates2 = format.parse(args[i++]);
                        if (sexx2.equals("м")) {
                            allPeople.set(id2, Person.createMale(name2, dates2));

                        } else if (sexx2.equals("ж")) {
                            allPeople.set(id2, Person.createFemale(name2, dates2));

                        }


                    }
                }
                break;

//
            case("-d"):
                synchronized (allPeople) {
                    int dl3 = args.length;
                    for (int i = 1; i < dl3; ) {
                        int id3 = Integer.parseInt(args[i++]);
                        allPeople.get(id3).setName(null);
                        allPeople.get(id3).setSex(null);
                        allPeople.get(id3).setBirthDate(null);

                    }
                }

                break;


            case("-i"):
                synchronized (allPeople) {
                    int dl4 = args.length;
                    for (int i = 1; i < dl4; ) {

                        int id4 = Integer.parseInt(args[i++]);
                        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                        String date = outputDateFormat.format(allPeople.get(id4).getBirthDate());
                        String Sexs = null;
                        if (allPeople.get(id4).getSex().equals(Sex.MALE)) {
                            Sexs = "м";
                        } else if (allPeople.get(id4).getSex().equals(Sex.FEMALE)) {
                            Sexs = "ж";
                        }
                        System.out.println(allPeople.get(id4).getName() + " " + Sexs + " " + date);

                    }
                }
                break;


        }
    }
}
