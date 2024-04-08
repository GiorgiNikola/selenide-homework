package ge.tbcitacademy.dataprovider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {
    @DataProvider
    public Object[][] offersDataProvider() {
        return new Object[][]{
                {"ჩემპიონთა აკადემია | Champions Academy", 240, 40},
                {"ასტრა პარკი | Astra Park", 25, 5},
                {"სითი სპორტი | City Sport", 40, 18},
                {"ჩოგბურთის კორტები ნუცუბიძეზე", 250, 70},
                {"ლისი ლემანსი | Lisi Lemans", 45, 10},
                {"ლილო არენა | Karting Lilo", 25, 5},
                {"ზიპინ ვაკე | Zipin Vake", 100, 50},
                {"ქუად სფეის ჯორჯია | Quad Space Georgia", 50, 10},
                {"World Fitness", 90, 25},
                {"დიელ ფიტნესი | DL fitness", 350, 100}
        };
    }

    @DataProvider
    public Object[][] offerNamesDataProvider() {
        return new Object[][]{
                {"ჩემპიონთა აკადემია | Champions Academy"},
                {"ასტრა პარკი | Astra Park"},
                {"სითი სპორტი | City Sport"},
                {"ჩოგბურთის კორტები ნუცუბიძეზე"},
                {"ლისი ლემანსი | Lisi Lemans"},
                {"ლილო არენა | Karting Lilo"},
                {"ზიპინ ვაკე | Zipin Vake"},
                {"თბილისის პადელის კლუბი | Tbilisi Padel Club"},
                {"World Fitness"},
                {"დიელ ფიტნესი | DL fitness"}
        };
    }

    @DataProvider
    public Object[][] factoryDataProvider() {
        return new Object[][]{
                {"კინო"},
                {"ესთეტიკა"},
                {"დასვენება"},
                {"კვება"},
                {"საბავშვო"},
                {"გართობა"},
                {"სპორტი"}
        };
    }

    @DataProvider
    public Object[][] studentInfoDataProvider() {
        return new Object[][]{
                {"John", "Doe", "Male", "1234567890"},
                {"Jane", "Smith", "Female", "9876543210"},
                {"Bob", "Johnson", "Other", "5555555555"}
        };
    }
}
