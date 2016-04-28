package com.company;

import com.company.database.*;
import edu.stanford.smi.protege.model.KnowledgeBase;
import edu.stanford.smi.protege.model.Project;
import edu.stanford.smi.protege.util.IntegerField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;
import java.util.*;

public class Main {

    private static final String PROJECT_FILE_NAME = "src/resources/alfastrah.pprj";
    private static MyFactory rs;

    private static ArrayList<StrahCompany> strahCompanyArrayList;

    public static void main(String[] args) {
        Collection<Object> errors = new ArrayList<Object>();
        Project project = new Project(PROJECT_FILE_NAME, errors);
        if (errors.size() == 0) {
            KnowledgeBase kb = project.getKnowledgeBase();
            rs = new MyFactory(kb);
            String str = null;
            strahCompanyArrayList = getDataTable(8,null);
            System.out.print("\nВведите команду (справка '-help'): ");
            str = new Scanner(System.in).nextLine();
            while (!Objects.equals(str, "-exit")) {
                if (Objects.equals(str, "-help")) {
                    System.out.println("Справка: \n" +
                            "Введите, чтобы посмотреть информацию по всем водителям  '-human: -all';\n" +
                            "Введите, чтобы посмотреть информацию по конкретному водителю '-human: ***';\n" +
                            "Введите, чтобы добавить водителя - '-adddriver';\n" +
                            "Рейтинг машин '-ratingcar';\n" +
                            "Выход из программы: '-exit'");
                    System.out.println();
                } else if (Objects.equals(str, "-adddriver")) {
                    Human human = addNewDriver();
                    if (human != null)
                        project.save(errors);
                } else if (Objects.equals(str, "-ratingcar")) {
                    ratingCar();
                } else if (str.startsWith("-human: ")) {
                    str = str.replaceAll("-human: ", "");
                    humanDptTask(str);
                    polisPrice(str);
                } else if (str.startsWith("-накидатьдатвбазу")) {
                    ArrayList<DateDTP> dateDTPs = addMoreDateInDateBase();
                        project.save(errors);
                } else {
                    System.out.print("Неправельный ввод '-help'. " +
                            "Повторите команду: ");
                    str = new Scanner(System.in).nextLine();
                    continue;
                }
                System.out.print("Введите команду (справка '-help'):");
                str = new Scanner(System.in).nextLine();
            }
        }
        waitForContinue();
    }

    private static ArrayList<DateDTP> addMoreDateInDateBase() {
        ArrayList<DateDTP> arraylist = new ArrayList<>();
        System.out.println("Сколько данных забросить в базу (от 1 до бесконечности, но в разумных пределах?");
        int n = new Scanner(System.in).nextInt();
        Random random = new Random();
        for (int i=0;i<n;i++) {
            int day, month, year;
            while (true) {
                try {
                    day = random.nextInt(30) + 1;
                    month = 1 + random.nextInt(12);
                    year = 1999 + random.nextInt(16);
                    Date date_tmp = new Date(year, month, day);
                    break;
                } catch (Exception e) {
                    if (Objects.equals(e.toString(), "")) {
                        int o = 0;
                    }
                }
            }
            DateDTP dateDTP = rs.createDateDTP("Car_"+year+month+day+random.nextDouble());
            String d,m;
            if (day < 10) d = "0"+day;
            else d = day+"";
            if (month < 10) m = "0"+month;
            else m = month + "";
            dateDTP.setDate(d+"."+m+"."+year);
            arraylist.add(dateDTP);
        }
        return arraylist;
    }

    private static Date getDatefromString(String tmp) {
        int[] datecomplect = new int[3];
        Date date = null;
        for (int i = 0 , j = 0, k = 0; i < tmp.length(); i++) {
            try {
                datecomplect[j] = Integer.parseInt(String.valueOf(tmp.toCharArray()[i]));
                if (i >= tmp.length()-1) {
                    String str = tmp.substring(k,i+1);
                    datecomplect[j] = Integer.parseInt(str);
                    k=i+1; j++;
                }
            }
            catch (Exception ex) {
                String str = tmp.substring(k,i);
                datecomplect[j] = Integer.parseInt(str);
                k=i+1; j++;
            }
        }
        if (datecomplect[2] > 1900 && datecomplect[2] <= Year.now().getValue())
            if (datecomplect[0] >=0 && datecomplect [0] <= 31
                    && datecomplect[1] >= 0 && datecomplect[1] <= 12)
                date = new Date(datecomplect[2],datecomplect[1],datecomplect[0]);
            else if (datecomplect[1] >=0 && datecomplect [1] <= 31
                    && datecomplect[0] >= 0 && datecomplect[0] <= 12)
                date = new Date(datecomplect[2], datecomplect[0], datecomplect[1]);
            else if (datecomplect[0] > 1900 && datecomplect[0] <= Year.now().getValue())
                if (datecomplect[1] >= 0 && datecomplect[1] <= 12
                        && datecomplect[2] >= 0 && datecomplect[2] <= 31)
                    date = new Date(datecomplect[0],datecomplect[1],datecomplect[2]);
        //System.out.println(date.getDay() + " " + date.getMonth() + " " + date.getYear());
        return date;
    }

    /*
    люди - 1
    машины - 2
    даты дтп - 3
    коэф шанс дтп - 4
    коэф кбм - 5
    коэф кс - 6
    коэф квс - 7
    страховщики - 8
    коэф км - 9
     */
    private static ArrayList getDataTable(int ifi, String name) {
        ArrayList list = null;
        try {
            switch (ifi) {
                case 1:
                    list = new ArrayList<Human>();
                    if (Objects.equals(name, "-all") || name == null) {
                        for (Human human : rs.getAllHumanObjects()) {
                            list.add(human);
                        }
                    } else {
                        for (Human human : rs.getAllHumanObjects())
                            if (human.getHumanName().equals(name))
                                list.add(human);
                    }
                    break;
                case 2:
                    list = new ArrayList<Car>();
                    for (Car car : rs.getAllCarObjects())
                        list.add(car);
                    break;
                case 3:
                    list = new ArrayList<DateDTP>();
                    for (DateDTP dateDTP : rs.getAllDateDTPObjects())
                        list.add(dateDTP);
                    break;
                case 4:
                    list = new ArrayList<KoefChanceDTP>();
                    for (KoefChanceDTP chanceDTP : rs.getAllKoefChanceDTPObjects())
                        list.add(chanceDTP);
                    break;
                case 5:
                    list = new ArrayList<KoefKBM>();
                    for (KoefKBM kbm : rs.getAllKoefKBMObjects())
                        list.add(kbm);
                    break;
                case 6:
                    list = new ArrayList<KoefKS>();
                    int mounth = Integer.parseInt(name);
                    for (KoefKS ks : rs.getAllKoefKSObjects())
                        if (mounth == ks.getKlassKS()) {
                            list.add(ks);
                            break;
                        }
                    break;
                case 7:
                    list = new ArrayList<KoefKVS>();
                    for (KoefKVS kvs : rs.getAllKoefKVSObjects())
                        list.add(kvs);
                    break;
                case 8:
                    list = new ArrayList<StrahCompany>();
                    for (StrahCompany strahCompany : rs.getAllStrahCompanyObjects())
                        list.add(strahCompany);
                    break;
                case 9:
                    list = new ArrayList<KoefKM>();
                    int power = Integer.parseInt(name);
                    for (KoefKM koefKM : rs.getAllKoefKMObjects())
                        if (power <= koefKM.getPower()) {
                            list.add(koefKM);
                            break;
                        }
                    break;
                case 10:
                    list = new ArrayList<City>();
                    for (City city : rs.getAllCityObjects())
                        list.add(city);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error read '" + ifi + "' from database: " + ex.getMessage());
        }
        return list;
    }

    private static float getChanceDtp(Human human)
    {
        float res = -1;
        int age = human.getYearBithday();
        int staj = human.getStajStartyear() - age;
        age = Year.now().getValue() - age;
        ArrayList<KoefChanceDTP> chanceDTPArrayList = getDataTable(4,null);
        for (int i=0;i<chanceDTPArrayList.size();i++) {
            int cage = chanceDTPArrayList.get(i).getAgeKoef();
            int cstaj = chanceDTPArrayList.get(i).getStajKoef();
            if (cage > 0 && cstaj > 0) {
                if (age > cage && staj > cstaj)
                    res = chanceDTPArrayList.get(i).getValue();
            } else if (cage > 0 && cstaj < 0) {
                cstaj*=-1;
                if (age > cage && staj <= cstaj)
                    res = chanceDTPArrayList.get(i).getValue();
            } else if (cage < 0 && cstaj < 0) {
                cage*=-1; cstaj*=-1;
                if (age <= cage && staj <= cstaj)
                    res = chanceDTPArrayList.get(i).getValue();
            } else if (cage < 0 && cstaj > 0) {
                cage*=-1;
                if (age <= cage && staj > cstaj)
                    res = chanceDTPArrayList.get(i).getValue();
            }
            }
            return res;
    }

    private static float getKoefKBMnow(Human human) {
        int klass = 0, index = 1;
        ArrayList<KoefKBM> kbmArrayList = getDataTable(5, null);
        ArrayList<DateDTP> dateDTPArrayList = new ArrayList<DateDTP>(human.getDTP());
        for (int i = human.getStajStartyear(); i < Year.now().getValue(); i++) {
            int dtpyear = 0;
            for (DateDTP aDateDTPArrayList : dateDTPArrayList) {
                int yeardtp = getDatefromString(aDateDTPArrayList.getDate()).getYear();
                if (yeardtp == i)
                    dtpyear++;
            }
            fori: for (int j=0;j<kbmArrayList.size();j++) {
                if (klass == kbmArrayList.get(j).getKlassKBM()) {
                    index = j;
                    switch (dtpyear){
                        case 0:
                            klass = kbmArrayList.get(j).getKBM0();
                            break fori;
                        case 1:
                            klass = kbmArrayList.get(j).getKBM1();
                            break fori;
                        case 2:
                            klass = kbmArrayList.get(j).getKBM2();
                            break fori;
                        case 3:
                            klass = kbmArrayList.get(j).getKBM3();
                            break fori;
                        case 4:
                            klass = kbmArrayList.get(j).getKBM4();
                            break fori;
                    }
                }
            }
        }
        return kbmArrayList.get(index).getValue();
    }

    private static float getKoefKM(int power) {
        ArrayList<KoefKM> koefKMArrayList = getDataTable(9,power+"");
        return koefKMArrayList.get(0).getValue();
    }

    private static int getCountDTPLastYear(Human human) {
        ArrayList<DateDTP> dateDTPArrayList = new ArrayList<DateDTP>(human.getDTP());
        int count = 0;
        for (DateDTP aDateDTPArrayList : dateDTPArrayList) {
            Date date = getDatefromString(aDateDTPArrayList.getDate());
            if (date.getYear() == Year.now().getValue()) {
                count++;
            }
        }
        return count;
    }

    private static float getKoefKVS(int age, int staj) {
        ArrayList<KoefKVS> koefKVSArrayList = getDataTable(7,null);
        float koef = -1;
        for (KoefKVS aKoefKVSArrayList : koefKVSArrayList) {
            int kage = aKoefKVSArrayList.getAgeKoef();
            int kstaj = aKoefKVSArrayList.getStajKoef();
            float value = aKoefKVSArrayList.getValue();
            if (kage > 0 && kstaj > 0 && age > kage && staj > kstaj)
                koef = value;
            else if (kage > 0 && kstaj < 0) {
                kstaj *= -1;
                if (age > kage && staj <= kstaj)
                    koef = value;
            } else if (kage < 0 && kstaj > 0) {
                kage *= -1;
                if (age <= kage && staj > kstaj)
                    koef = value;
            } else if (kage < 0 && kstaj < 0) {
                kage *= -1;
                kstaj *= -1;
                if (age <= kage && staj <= kstaj)
                    koef = value;
            }
        }

        return koef;
    }

    private static float getNewKoefKBMandNewSkidka(Human human) {
        float nowprice = getPolisPrice(human, strahCompanyArrayList.get(0), 10);
        float kbmnow = getKoefKBMnow(human);
        float kbmnew = -1;
        float newprice = 0;
        int   newklasskbm = -1;

        ArrayList<KoefKBM> koefKBMArrayList = getDataTable(5, null);
        for (int i = 0; i < koefKBMArrayList.size(); i++) {
            if (kbmnow == koefKBMArrayList.get(i).getValue()) {
                newklasskbm = koefKBMArrayList.get(i).getKBM0();
                break;
            }
        }
        for (int i = 0; i < koefKBMArrayList.size(); i++) {
            if (newklasskbm == koefKBMArrayList.get(i).getKlassKBM())
                kbmnew = koefKBMArrayList.get(i).getValue();
        }

        int humanAge = Year.now().getValue() - human.getYearBithday();
        int humanStaj = human.getStajStartyear() - human.getYearBithday();
        double tb = strahCompanyArrayList.get(0).getBasisStavka();
        double kt = human.getMyCity().getKoeef();
        double kbm = kbmnew;
        double kvs = getKoefKVS(humanAge, humanStaj);
        double km = getKoefKM(human.getMy_Car().getPower());
        double ko = 1;
        ArrayList<KoefKS> koefKSArrayList = getDataTable(6, 10 + "");
        double ks = koefKSArrayList.get(0).getValue();
        double kn = getCountDTPLastYear(human);
        if (kn > 0) kn = 1.5;
        else kn = 1;

        double t = tb * kt * kbm * kvs * ko * km * ks * kn;
        newprice = new BigDecimal(t).setScale(2, RoundingMode.UP).floatValue();
        newprice = 100 - newprice / nowprice * 100;
        newprice = new BigDecimal(newprice).setScale(2, RoundingMode.UP).floatValue();
        return newprice;
    }

    private static float getPolisPrice(Human human, StrahCompany strahCompany, int month) {
        int humanAge = Year.now().getValue() - human.getYearBithday();
        int humanStaj = human.getStajStartyear() - human.getYearBithday();
        double tb = strahCompany.getBasisStavka();
        double kt = human.getMyCity().getKoeef();
        double kbm = getKoefKBMnow(human);
        double kvs = getKoefKVS(humanAge,humanStaj);
        double km = getKoefKM(human.getMy_Car().getPower());
        double ko = 1;
        ArrayList<KoefKS> koefKSArrayList = getDataTable(6, month+"");
        double ks = koefKSArrayList.get(0).getValue();
        double kn = getCountDTPLastYear(human);
        if (kn > 0) kn = 1.5;
        else kn = 1;

        double t = tb * kt * kbm * kvs * ko * km * ks * kn;
        t = new BigDecimal(t).setScale(2, RoundingMode.UP).doubleValue();
        return (float)t;
    }
    //? = ?? x ?? x ??? x ??? x ?? x ?? x ?? x ??
    private static String[][] getPolisPricesAll(Human human) {
        String[][] mass = new String[9][strahCompanyArrayList.size()+1];
        mass[0][0] = "Срок \t";
        for (int i=1;i<mass.length-1;i++)
            mass[i][0] = (i+2)+"\t";
        mass[mass.length-1][0]="10+\t";
        for (int i=1;i<mass[0].length;i++)
            mass[0][i] = strahCompanyArrayList.get(i-1).getCompanyName() + " ";
        for (int i=1;i<mass.length;i++)
            for (int j=1;j<mass[0].length;j++)
                mass[i][j] = getPolisPrice(human,strahCompanyArrayList.get(j-1),(i+2))+" ";
        for (int i=1;i<mass[0].length-1;i++)
            for (int j=i+1;j<mass[0].length;j++)
                if (Float.parseFloat(mass[8][i]) > Float.parseFloat(mass[8][j])) {
                    for (int k = 0; k < mass.length; k++) {
                        String temp = mass[k][i];
                        mass[k][i] = mass[k][j];
                        mass[k][j] = temp;
                    }
                }
        return mass;
    }

    private static ArrayList getRatingFrom1950() {
        ArrayList<DateDTP> dateDTPArrayList = getDataTable(3, null);
        ArrayList<Date> dateArrayList = new ArrayList<Date>();
        for (DateDTP aDateDTPArrayList : dateDTPArrayList) { //01.34.6789
            int day=-1,month=-1,year=-1;
            try {
                day = Integer.parseInt(aDateDTPArrayList.getDate().substring(0, 2));
                month = Integer.parseInt(aDateDTPArrayList.getDate().substring(3, 5));
                year = Integer.parseInt(aDateDTPArrayList.getDate().substring(6, 10));
            } catch (NumberFormatException ex) {
                String tmp = "", d = aDateDTPArrayList.getDate();
                int j=0;
                for (int i=0;i<d.length();i++,j++) {
                    try {
                        int t = Integer.parseInt(d.toCharArray()[i]+"");
                    } catch (NumberFormatException ex1) {
                        day = Integer.parseInt(d.substring(j-i,j-1));
                    }
                }
                j++;
                for (int i=j;i<d.length();i++,j++) {
                    try {
                        int t = Integer.parseInt(d.toCharArray()[i]+"");
                    } catch (NumberFormatException ex1) {
                        month = Integer.parseInt(d.substring(j-i,j-1));
                    }
                }
                j++;
                for (int i=j;i<d.length()+1;i++,j++) {
                    try {
                        int t = Integer.parseInt(d.toCharArray()[i]+"");
                    } catch (Exception ex1) {
                        year = Integer.parseInt(d.substring(j-i,j-1));
                    }
                }

            }
            Date date = new Date(year, month, day);
            dateArrayList.add(date);
        }
        dateDTPArrayList = null;
        int year = Year.now().getValue();
        double allcount = dateArrayList.size(), lastprocent = -1;

        //первая измерение по процентам, второе измерение годы, которые относятся к этому проценту
        ArrayList<ArrayList<Float>> arrayListArrayList = new ArrayList<>();

        System.out.print("Всего в базе записано " + (int) allcount + " случаев дтп");
        while (true) {
            double icountyear = 0;
            for (Date idate : dateArrayList) {
                int ii = idate.getYear();
                if (ii == year)
                    icountyear++;
            }
            if (icountyear == 0 && year < 1950) break;
            double procent = (icountyear / allcount) * 100;
            if ((int) procent != 0) {
                boolean ifi = true;
                for (ArrayList<Float> anArrayListArrayList : arrayListArrayList) {
                    if (anArrayListArrayList.get(0) == (float)procent) {
                        ifi = false;
                        anArrayListArrayList.add((float) year);
                        break;
                    }
                }
                if (ifi) {
                    ArrayList<Float> arrayList = new ArrayList<>();
                    arrayList.add((float) procent);
                    arrayList.add((float) year);
                    arrayListArrayList.add(arrayList);
                }
                /*if (procent != lastprocent) {
                    System.out.print(".\n" + (float) procent + "% из было в " + year + " году");
                } else
                    System.out.print(", в " + year + "году");*/
            }
            if (procent != 0) lastprocent = procent;
            year--;
        }
        return arrayListArrayList;
    }
    private static class Caret {
        public Car car;
        public int dtp;
        public float risk;
    };

    private static ArrayList getCarRetRisk() {
        ArrayList<Caret> caretArrayList = new ArrayList<>();
        ArrayList<Human> humanArrayList = getDataTable(1, null);
        ArrayList<DateDTP> dateDTPArrayListtmp = new ArrayList<>();
        ArrayList<DateDTP> dateDTPArrayList = getDataTable(3, null);
        for (Human aHumanArrayList : humanArrayList) {
            boolean проверка = true;
            for (Caret aCaretArrayList : caretArrayList) {
                if (Objects.equals(aHumanArrayList.getMy_Car().getMarka(), aCaretArrayList.car.getMarka()) &&
                        Objects.equals(aHumanArrayList.getMy_Car().getModel(), aCaretArrayList.car.getModel())) {
                    aCaretArrayList.dtp += aHumanArrayList.getDTP().size();
                    проверка = false;
                }
            }
            dateDTPArrayListtmp.addAll(aHumanArrayList.getDTP());
            if (проверка) {
                Caret caret = new Caret();
                caret.car = aHumanArrayList.getMy_Car();
                caret.dtp = aHumanArrayList.getDTP().size();
                caret.risk = 0;
                caretArrayList.add(caret);
            }
        }
        /*на выходе из массива получаем список машин с кол-вом их дтп и рисков зануленных,
        а так же список дтп к которым причастны водители из списка.
        Необходимо проанализировать все три списка, чтобы выявить степень риска покупки каждого автомобиля
        Сначало определим количество дтп к которым водители не причастны для этого будем проходить по списку
        дтп и сравнивать его со списком дтп водителей в случае совпадения удаляем это из списка и потом смотрим на
        кол-во записей*/
        int dtpstoprocent = dateDTPArrayList.size(); //общее число дтп
        int dtpfantomcount = dtpstoprocent;
        whilebirk:
            for (int i = 0; i < dateDTPArrayList.size(); i++) {
                boolean ifi = true;
                for (DateDTP aDateDTPArrayListtmp : dateDTPArrayListtmp) {
                    if (Objects.equals(dateDTPArrayList.get(i), aDateDTPArrayListtmp)) {
                        ifi = false;
                        dtpfantomcount--;
                        //dateDTPArrayList.remove(i);
                    }
                }
            }
        /*теперь у нас есть количество дтп к которым неприячастны водители
        из списка */
        for (Caret aCaretArrayList : caretArrayList) {
            double d = aCaretArrayList.dtp;
            double risk = (d / dtpstoprocent) * (double) 100;
            aCaretArrayList.risk = (float) risk;
        }

        for (int i = 0; i < caretArrayList.size() - 1; i++)
            for (int j = i + 1; j < caretArrayList.size(); j++)
                if (caretArrayList.get(i).risk > caretArrayList.get(j).risk)
                    Collections.swap(caretArrayList, i, j);

        Caret caret = new Caret();
        caret.dtp = dtpfantomcount;
        caret.car = null;
        caret.risk = (float) (((double)dtpfantomcount / (double) dtpstoprocent) * 100);

        caretArrayList.add(caret);

        return caretArrayList;
    }

    private static void ratingCar() {
        System.out.println("------------------------");
        ArrayList<ArrayList<Float>> arrayLists = getRatingFrom1950();
        System.out.println(".");
        for (int i = 0;i<arrayLists.size();i++) {
            String tmp = arrayLists.get(i).get(0) + "% из было в " + arrayLists.get(i).get(1).intValue();
            for (int j = 2; j < arrayLists.get(i).size(); j++)
                tmp += ", " + arrayLists.get(i).get(j).intValue();
            if (arrayLists.get(i).size() == 2)
                tmp += " году;";
            else
                tmp += " годах;";
            if (i == arrayLists.size() - 1)
                tmp = tmp.replace(';', '.');
            System.out.println(tmp);
        }
        System.out.println("------------------------");
        System.out.println("Формирование списка рейтинга машин по степени риска попадения в аварию");
        ArrayList<Caret> caretArrayList = getCarRetRisk();
        System.out.println("Рейтинг машин по степени риска (от наименьшего к наибольшему)");
        System.out.println("Марка модель \t Рискованность \t Кол-во ДТП");
        for (int i=0;i<caretArrayList.size();i++) {
            String marka_model;
            if (caretArrayList.get(i).car != null)
                marka_model = caretArrayList.get(i).car.getMarka() + " " + caretArrayList.get(i).car.getModel();
            else
                marka_model = "Неизвестно";
            float risk = caretArrayList.get(i).risk;
            //risk = new BigDecimal(risk).setScale(2, RoundingMode.UP).floatValue();
            int dtp = caretArrayList.get(i).dtp;
            System.out.println(marka_model + " \t " + risk + "% \t " + dtp);
        }
        System.out.println("------------------------");
    }

    private static void humanDptTask(String name) {
        ArrayList<Human> humanArrayList = getDataTable(1, name);
        int len = humanArrayList.size();
        if (len == 0) {
            if (Objects.equals(name, "-all")) System.out.println("В базе данных нет водителей");
            else System.out.println("В базе данных нет информацию по данному водителю.");
            return;
        } else if (len > 1) System.out.println("Список вероятностей попадения в аварию: ");
        for (int i = 0; i < humanArrayList.size(); i++) {
            System.out.print("Вероятность, что " + humanArrayList.get(i).getHumanName()
                    + " попадет в аварию - "
                    + getChanceDtp(humanArrayList.get(i)) + "%");
            if (i < humanArrayList.size() - 1)
                System.out.println(";");
        }
        System.out.println(".");
    }

    private static void polisPrice(String name) {
        if (strahCompanyArrayList.size() == 0) {
            System.out.println("В базе нет информации о страховых компаниях");
            return;
        }
        ArrayList<Human> humanArrayList = getDataTable(1,name);
        for (Human aHumanArrayList : humanArrayList) {
            String[][] mass = getPolisPricesAll(aHumanArrayList);
            float skidka = getNewKoefKBMandNewSkidka(aHumanArrayList);
            System.out.println("Цены полисов для " + aHumanArrayList.getHumanName() + "(если в следующем году ДТП не будет то скидка составит " + skidka + "%):");
            /*AsciiTable table = AsciiTable.newTable(mass[0].length,mass.length);
            for (int i=0;i<mass.length;i++) {
                table.addRow(mass[i][0],mass[i][1],mass[i][2],mass[i][3],mass[i][4],mass[i][5],mass[i][6]);
            }
            System.out.println(table.render());*/
            for (int i = 0; i < mass.length; i++) {
                for (int j = 0; j < mass[0].length; j++)
                    System.out.print(mass[i][j] + "\t");
                System.out.print("\n");
            }
        }
    }

    private static boolean ProvProvNewDriver(String name) {
        ArrayList<Human> humanArrayList = getDataTable(1,name);
        for (Human i : humanArrayList) {
            System.out.println(i.getHumanName());
            if (Objects.equals(i.getHumanName(), name))
                return true;
        }
        return false;
    }

    private static Human addNewDriver() {
        System.out.println("Для добавления нового водителя необходимо сначало добавить машину, " +
                "\nесли машины данного водителя нет в базе знаний, далее ввести данные водителя." +
                "\nЗаполнить необходимо всю информацию");
        Random random = new Random();
        String name,cityname; int bith, staj;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        name = (scanner.next());
        System.out.print("Введите год рождения: ");
        bith = (scanner.nextInt());
        System.out.print("Введите год начала стажа: ");
        staj = (scanner.nextInt());
        City city = null;
        while (true) {
            System.out.print("Введите город проживания: ");
            String tmp = scanner.next();
            city = getCityThisNewHuman(tmp);
            if (city == null) {
                System.out.println("Данного города нет в списке. Повторите ввод");
            } else {
                /*human.setMyCity(city);*/ break;
            }
        }
        if (ProvProvNewDriver(name)) {
            System.out.println("Ввод данных отменен системой");
            System.out.println("Данный водитель уже есть в базе данных");
            System.out.println("Пожалуйста добавьте нового водителя");
            return null;
        }
        Human human = rs.createHuman("Human_"+random.nextDouble());
        human.setHumanName(name);
        human.setYearBithday(bith);
        human.setStajStartyear(staj);
        human.setMyCity(city);
        System.out.print("Введите через пробел марку, модель, мощность машины: ");
        String marka = scanner.next();
        String model = scanner.next();
        int power = scanner.nextInt();
        Car car = getCarProv(marka,model,power);
        human.setMy_Car(car);

        //добавить дтп
        System.out.println("Введите через '-' или '.' дату ДТП в формате dd-mm-year." +
                "Наппример: '01.01.2016'. " +
                "После ввода очередной даты нажмите <Enter>");
        System.out.println("Чтобы прекратить ввод дат введите 'break'");
        Collection<DateDTP> dtpCollection = human.getDTP();
        String dt = scanner.next();
        while (!Objects.equals(dt, "break")) {
            dt = getProvCorrectDateVvod(dt,scanner);
            if (dt == null) {
                dt = scanner.next();
                continue;
            }
            dtpCollection.add(getNewDateDtp(dt)); //даты нет, создаем новую
            dt = scanner.next();
            }
        human.setDTP(dtpCollection);
        return human;
    }

    private static String getProvCorrectDateVvod(String dt, Scanner scanner)
    {
        int j=0, k=0;
        int[] len = new int[]{ 0,0,0 };
        boolean ifi = false;
        String[] pro = new String[]{",", "-", "/", "_"};
        for (String aPro : pro) {
            dt = dt.replaceAll(aPro, ".");
        }
        for (int i=0;i<dt.length();i++) {
            if (dt.toCharArray()[i] == '.')
                j++;
            try {
                int n = Integer.parseInt(dt.toCharArray()[i]+"");
                if (k > 2) { System.out.println("Неправельный ввод"); return null; }
                len[k]++;
            } catch (Exception ex) {
                ifi = false;
                k++;
            }
        }
        if (((len[0] != 2 && len[1] != 2 && len[2] != 4)
        && (len[0] != 4 && len[1] != 2 && len[2] != 2))
        || (j!=2 && dt.length() != "12-12-2012".length())) {
            System.out.println("Неправельный ввод"); return null;
            }
        return dt;
    }

    private static DateDTP getNewDateDtp(String dt) {
        DateDTP dateDTP = rs.createDateDTP("DateDTP_" + new Random().nextDouble());
        dateDTP.setDate(dt);
        return dateDTP;
    }

    private static Car getCarProv(String marka, String model, int power) {
        Car car = null;
        ArrayList<Car> carArrayList = getDataTable(2,null);
        ArrayList<Integer> indexArrayList = new ArrayList<Integer>();
        for (int i=0;i<carArrayList.size();i++) {
            boolean[] bool = { false, false, false };
            if (marka.startsWith(carArrayList.get(i).getMarka())
                    || marka.endsWith(carArrayList.get(i).getMarka())) bool[0] = true;
            if (model.startsWith(carArrayList.get(i).getModel())
                    || model.endsWith(carArrayList.get(i).getModel())) bool[1] = true;
            if (carArrayList.get(i).getPower() == power) bool[2] = true;

            if (bool[0] && bool[1] && bool[2]) {
                car = carArrayList.get(i); break;
            } else if (bool[0] && bool[1] && !bool[2]) {
                indexArrayList.add(i);
            } else if (bool[0] && !bool[1] && bool[2]) {
                indexArrayList.add(i);
            } else if (!bool[0] && bool[1] && bool[2]) {
                indexArrayList.add(i);
            }
        }
        if (car == null) {
            if (indexArrayList.size()>=1) {
                System.out.println("В базе обнаружены машины с подобными характеристики. Ниже будет приведен список: ");
                System.out.println("---------table---------");
                System.out.println("I \t Марка \t Модель \t Мощность");
                for (Integer i : indexArrayList) {
                    System.out.println(i + " \t " + carArrayList.get(i).getMarka() + " \t "
                            +carArrayList.get(i).getModel() + " \t "
                            +carArrayList.get(i).getPower() + " \t ");
                }
                System.out.println("---------end-table---------");
                System.out.print("Если в этом списке есть машина, которую вы хотели выбрать,\n то введите номер машины (левый столбик)." +
                        "\nЕсли машины в этом списке нет введите '-create':  ");
                while(true) {
                    String str = new Scanner(System.in).next();
                    if (str.startsWith("-create") || str.startsWith("-создать")) {
                        car = getNewCreateCar(marka, model, power);
                        break;
                    } else if (str.startsWith("-")) {
                        str = str.replaceAll("-", "");
                        int i = Integer.parseInt(str);
                        if (i>=0 && i<carArrayList.size()) {
                            car = carArrayList.get(i);
                            break;
                        }
                    }
                }
            }
        }
        return car;
    }

    private static Car getNewCreateCar(String marka, String model, int power) {
        Random random = new Random();
        Car car = rs.createCar("Car_"+random.nextDouble());
        car.setMarka(marka);
        car.setModel(model);
        car.setPower(power);
        return car;
    }

    private static City getCityThisNewHuman(String value)
    {
        ArrayList<City> cityArrayList = getDataTable(10,null);
        for (City aCityArrayList : cityArrayList) {
            if (Objects.equals(aCityArrayList.getCityName(), value))
                return aCityArrayList;
        }
        return null;
    }

    private static void waitForContinue() {
        System.out.println("Press <Enter> to exit");
        try {
            System.in.read();
        } catch (Exception e) {}
    }
}
