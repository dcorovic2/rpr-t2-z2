package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetna, krajnja;
    private boolean pripadaPocetna, pripadaKrajnja;

    public Interval(double pocetna, double krajnja, boolean pripadaPocetna, boolean pripadaKrajnja) {
        if(pocetna > krajnja) throw new IllegalArgumentException();

        this.pocetna = pocetna;
        this.krajnja = krajnja;
        this.pripadaPocetna = pripadaPocetna;
        this.pripadaKrajnja = pripadaKrajnja;
    }

    public Interval(){
        this.pocetna = 0;
        this.krajnja = 0;
        pripadaPocetna = pripadaKrajnja = false;
    }

    public boolean isNull(){
        if(pocetna == 0 && krajnja == 0) return true;
        return false;
    }

    public boolean isIn(double tacka){
        if(pocetna < tacka && tacka < krajnja) return true;
        if(pocetna < tacka && tacka == krajnja && pripadaKrajnja == true) return true;
        if(tacka < krajnja && tacka == pocetna && pripadaPocetna == true) return true;
        return false;
    }

    public Interval intersect​(Interval i){
        if((i.krajnja < pocetna && i.krajnja < pocetna) || (i.pocetna > krajnja && i.krajnja > krajnja)) {
            Interval novi = new Interval();
            return novi;
        }

        if(pocetna < i.pocetna && i.krajnja < krajnja) return i;
        if(i.pocetna < pocetna && krajnja < i.krajnja) {
            Interval novi = new Interval(pocetna, krajnja, pripadaPocetna, pripadaKrajnja);
            return novi;
        }
        if(i.pocetna > pocetna && i.pocetna < krajnja && i.krajnja > krajnja) {
            Interval novi = new Interval(i.pocetna, krajnja, i.pripadaPocetna, pripadaKrajnja);
            return novi;
        }

        Interval novi = new Interval(pocetna, i.krajnja, pripadaPocetna, i.pripadaKrajnja);
        return novi;
    }

    public static Interval intersect​(Interval i1, Interval i2){
        if(i1.krajnja < i2.pocetna || i1.pocetna > i2.krajnja) {
            Interval novi = new Interval();
            return novi;
        }
        if(i1.pocetna < i2.pocetna && i2.krajnja < i1.krajnja) return i2;
        if(i2.pocetna < i1.pocetna && i1.krajnja < i2.krajnja) return i1;
        if(i2.pocetna > i1.pocetna && i2.pocetna < i1.krajnja && i1.krajnja < i2.krajnja) {
            Interval novi = new Interval(i2.pocetna, i1.krajnja, i2.pripadaPocetna, i1.pripadaKrajnja);
            return novi;
        }

        return new Interval(i1.pocetna, i2.krajnja, i1.pripadaPocetna, i2.pripadaKrajnja);
    }

    @Override
    public String toString(){
        String s = new String();
        if(pripadaPocetna == true) s += "[";
        else s += "(";

        if(pocetna != 0 && krajnja != 0) {
            s += String.valueOf(pocetna);
            s += ",";
            s += String.valueOf(krajnja);
        }

        if(pripadaKrajnja == true) s += "]";
        else s += ")";

        return s;
    }

    public boolean equals(Interval i){
        if(i.pocetna == pocetna && i.krajnja == krajnja && i.pripadaPocetna == pripadaPocetna && i.pripadaKrajnja == pripadaKrajnja) return true;
        return false;
    }
}
