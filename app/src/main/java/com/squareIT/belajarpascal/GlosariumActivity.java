package com.squareIT.belajarpascal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.squareIT.belajarpascal.adapter.GlosariumExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlosariumActivity extends AppCompatActivity {

    private ImageView imageViewBackButton;

    private ExpandableListView expandableListView;
    private GlosariumExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glosarium);

        expandableListView = (ExpandableListView) findViewById(R.id.elv_glosarium);
        initData();
        listAdapter = new GlosariumExpandableListAdapter(this, listDataHeader, listHashMap);
        expandableListView.setAdapter(listAdapter);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageViewBackButton = (ImageView) findViewById(R.id.iv_glosarium_back);
        imageViewBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlosariumActivity.super.onBackPressed();
            }
        });
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listDataHeader.add("Uses crt/ Uses wincrt");
        listDataHeader.add("Clrscr");
        listDataHeader.add("Begin");
        listDataHeader.add("Write");
        listDataHeader.add("WriteIn");
        listDataHeader.add("Read");
        listDataHeader.add("ReadIn");
        listDataHeader.add("End");
        listDataHeader.add("Const");
        listDataHeader.add("Var");
        listDataHeader.add("Type");

        List<String> usesCrt = new ArrayList<>();
        usesCrt.add("Memanipulasi layar dalam pengetikan naskah yakni menampung 80 karakter dan 25 baris");

        List<String> clrscr = new ArrayList<>();
        clrscr.add("Membersihkan isi memori dan seluruh tulisan pada layar setiap pembacaan awal program");

        List<String> begin = new ArrayList<>();
        begin.add("Memulai program");

        List<String> write = new ArrayList<>();
        write.add("Perintah untuk mencetak hasil pada layar dan pada baris yang sama");

        List<String> writeIn = new ArrayList<>();
        writeIn.add("Mencetak hasil pada layar dan pada baris berikutnya");

        List<String> read = new ArrayList<>();
        read.add("Membaca data yang dimasukkan pada baris yang sama");

        List<String> readIn = new ArrayList<>();
        readIn.add("Membaca data yang dimasukkan pada baris yang baru");

        List<String> end = new ArrayList<>();
        end.add("Untuk mengakhiri program");

        List<String> constp = new ArrayList<>();
        constp.add("Mendeklarasikan konstanta");

        List<String> var = new ArrayList<>();
        var.add("Mendeklarasikan variabel");

        List<String> type = new ArrayList<>();
        type.add("Mendeklarasikan tipe data");


        listDataHeader.add("Int");
        List<String> inte = new ArrayList<>();
        inte.add("Perintah untuk mengabaikan angka dibalik koma. Penggunaannya : Int(45,78)=45");

        listDataHeader.add("Frac");
        List<String> frac = new ArrayList<>();
        frac.add("Mengambil desimal suatu bilangan. Frac(45,78)=0,78");

        listDataHeader.add("Sqr");
        List<String> Sqr = new ArrayList<>();
        Sqr.add("Mencari pangkat 2 dari suatu bilangan. Sqr(3)=9");

        listDataHeader.add("Sqrt");
        List<String> Sqrt = new ArrayList<>();
        Sqrt.add("Mencari akar pangkat dua dari suatu bilangan. Sqrt(16)=4");

        listDataHeader.add("Exp");
        List<String> Exp = new ArrayList<>();
        Exp.add("Untuk mencari eksponensial (exponential) dari suatu bilangan");

        listDataHeader.add("Ln");
        List<String> Ln = new ArrayList<>();
        Ln.add("Mencari logaritma natural dari suatu bilangan");

        listDataHeader.add("Pi");
        List<String> Pi = new ArrayList<>();
        Pi.add("Untuk memasukkan bilangan pi (3,1415926536)");

        listDataHeader.add("Sin");
        List<String> Sin = new ArrayList<>();
        Sin.add("Untuk mencari Sinus dari suatu bilangan dalam satuan radian. " +
                "Bentuk umumnya adalah: Sin(angka)");

        listDataHeader.add("Cos");
        List<String> Cos = new ArrayList<>();
        Cos.add("Untuk mencari Cosinus dari suatu bilangan dalam satuan radian.\n" +
                "Bentuk umumnya adalah : Cos(angka)");

        listDataHeader.add("Tan");
        List<String> Tan = new ArrayList<>();
        Tan.add("Turbo pascal tidak menyediakan perintah untuk mencari tangen dari suatu bilangan. " +
                "Tetapi dengan perintah Sinus dan Cosinus, maka Tangen dari suatu bilangan dengan dicari " +
                "dengan rumus : Tan=Sin(angka)/Cos(angka)");

        listDataHeader.add("Arctan");
        List<String> Arctan = new ArrayList<>();
        Arctan.add("Untuk mencari Arctangen dari suatu bilangan");

        listDataHeader.add("Readkey");
        List<String> Readkey = new ArrayList<>();
        Readkey.add("Untuk pembacaan sebuah karakter dari keyboard. Tipe data yang dihasilkan adalah char");

        listDataHeader.add("GoToXY");
        List<String> GOTOXY = new ArrayList<>();
        GOTOXY.add("Untuk menempatkan posisi kursor pada layar. Sintaks: GOTOXY(X, Y: Byte)");

        listDataHeader.add("DELLINE");
        List<String> DELLINE = new ArrayList<>();
        DELLINE.add("Untuk menghapus sebuah baris pada posisi kursor dan menaikkan baris-baris dibawahnya");

        listDataHeader.add("INSLINE");
        List<String> INSLINE = new ArrayList<>();
        INSLINE.add("Untuk menyisipkan sebuah baris pada posisi kursor dan menggeser kebawah tampilan baris dibawahnya");

        listDataHeader.add("DELAY");
        List<String> DELAY = new ArrayList<>();
        DELAY.add("Untuk menghentikan sejenak proses program");

        listDataHeader.add("CONCAT");
        List<String> CONCAT = new ArrayList<>();
        CONCAT.add("Untuk menggabungkan 2 atau beberapa variabel string.\n" +
                "Sintaks: CONCAT(s1 [,s2,…,sn]: String) : STRING");

        listDataHeader.add("COPY");
        List<String> COPY = new ArrayList<>();
        COPY.add("Mengambil satu(1) atau beberapa karakter dari sebuah string.\n" +
                "Sintaks: COPY(S,Index,Count) : String");

        listDataHeader.add("DELETE");
        List<String> DELETE = new ArrayList<>();
        DELETE.add("Menghapus sebagian karakter dari sebuah string.\n" +
                "Sintaks: DELETE(S,Index,Count)");

        listDataHeader.add("INSERT");
        List<String> INSERT = new ArrayList<>();
        INSERT.add("Menyisipkan satu(1) atau beberapa karakter ke dalam sebuah string.\n" +
                "Sintaks: INSERT(Source,var S,Index)");

        listDataHeader.add("LENGTH");
        List<String> LENGTH = new ArrayList<>();
        LENGTH.add("Memberikan nilai panjang dari suatu string (jumlah karakterdalam string).\n" +
                "Sintaks: LENGTH(S)");

        listDataHeader.add("POS");
        List<String> POS = new ArrayList<>();
        POS.add("Mencari posisi sebuah bagian string (substring) didalam sebuah string.\n" +
                "Sintaks: POS(Substr,S); {menghasilkan nilai Byte}");

        listDataHeader.add("STR");
        List<String> STR = new ArrayList<>();
        STR.add("Merubah nilai numerik ke dalam nilai string.\n" +
                "Sintaks: STR(N,S)");

        listDataHeader.add("VAL");
        List<String> VAL = new ArrayList<>();
        VAL.add("Merubah nilai string ke dalam nilai numerik.\n" +
                "Sintaks: VAL(S,N,P)");

        listDataHeader.add("UPCASE");
        List<String> UPCASE = new ArrayList<>();
        UPCASE.add("Memberikan huruf kapital dari argumen.\n" +
                "Sintaks: UPCASE(S)");

        listDataHeader.add("CHR");
        List<String> CHR = new ArrayList<>();
        CHR.add("Merubah nilai dari byte ke bentuk karakter yang sesuai dengan kode ASCII.\n" +
                "Sintaks: CHR(x)");

        listDataHeader.add("ORD");
        List<String> ORD = new ArrayList<>();
        ORD.add("Merubah nilai suatu variabel dari bentuk karakter ke bentuk longint.\n" +
                "Sintaks: ORD(X)");

        listDataHeader.add("ROUND");
        List<String> ROUND = new ArrayList<>();
        ROUND.add("Membulatkan data tipe real ke data tipe longint.\n" +
                "Sintaks: ROUND(X)");

        listDataHeader.add("TRUNC");
        List<String> TRUNC = new ArrayList<>();
        TRUNC.add("Membulatkan kebawah data tipe real ke data tipe longint.\n" +
                "Sintaks: TRUNC(X)");

        listDataHeader.add("SUCC");
        List<String> SUCC = new ArrayList<>();
        SUCC.add("Memberikan nilai sesudah nilai argumen dalam urutannya dalam ASCII.\n" +
                "Sintaks: SUCC(x)");

        listDataHeader.add("PRED");
        List<String> PRED = new ArrayList<>();
        PRED.add("Memberikan nilai sebelum nilai argumen dalam urutannya dalam ASCII.\n" +
                "Sintaks: PRED(x)");

        listDataHeader.add("INC");
        List<String> INC = new ArrayList<>();
        INC.add("Menambah (increments) nilai suatu variabel.\n" +
                "Sintaks: INC(x,i); {i >= 1}");

        listDataHeader.add("DEC");
        List<String> DEC = new ArrayList<>();
        DEC.add("Mengurangi (decrements) nilai suatu variabel.\n" +
                "Sintaks: DEC(x,i); {i >=1}");

        listDataHeader.add("TEXTCOLOR");
        List<String> TEXTCOLOR = new ArrayList<>();
        TEXTCOLOR.add("Untuk mengatur warna dari karakter-karakter di layar.\n" +
                "Sintaks: TEXTCOLOR(color : Byte);");

        listDataHeader.add("TEXTBACKGROUND");
        List<String> TEXTBACKGROUND = new ArrayList<>();
        TEXTBACKGROUND.add("Untuk mengatur warna latar belakang dari karakter-karakter dilayar.\n" +
                "Sintaks: TEXTBACKGROUND(Color : Byte);");

        listDataHeader.add("WINDOW");
        List<String> WINDOW = new ArrayList<>();
        WINDOW.add("Untuk membuat suatu jendela (window) yang terletak pada layar.\nSintaks: WINDOW(x1,x2,y1,y2 : Byte)");

        listDataHeader.add("TEXTMODE");
        List<String> TEXTMODE = new ArrayList<>();
        TEXTMODE.add("Untuk mengatur lebar layar, 80 kolom atau 40 kolom.\n" +
                "Sintaks: TEXTMODE(Mode: Byte)");

        listDataHeader.add("SOUND");
        List<String> SOUND = new ArrayList<>();
        SOUND.add("Untuk mengaktifkan suara(beep) pada internal speaker.\n" +
                "Sintaks: SOUND(Hz : word)");


        listHashMap.put(listDataHeader.get(0), usesCrt);
        listHashMap.put(listDataHeader.get(1), clrscr);
        listHashMap.put(listDataHeader.get(2), begin);
        listHashMap.put(listDataHeader.get(3), write);
        listHashMap.put(listDataHeader.get(4), writeIn);
        listHashMap.put(listDataHeader.get(5), read);
        listHashMap.put(listDataHeader.get(6), readIn);
        listHashMap.put(listDataHeader.get(7), end);
        listHashMap.put(listDataHeader.get(8), constp);
        listHashMap.put(listDataHeader.get(9), var);
        listHashMap.put(listDataHeader.get(10), type);

        listHashMap.put(listDataHeader.get(11), inte);
        listHashMap.put(listDataHeader.get(12), frac);
        listHashMap.put(listDataHeader.get(13), Sqr);
        listHashMap.put(listDataHeader.get(14), Sqrt);
        listHashMap.put(listDataHeader.get(15), Exp);
        listHashMap.put(listDataHeader.get(16), Ln);
        listHashMap.put(listDataHeader.get(17), Pi);
        listHashMap.put(listDataHeader.get(18), Sin);
        listHashMap.put(listDataHeader.get(19), Cos);
        listHashMap.put(listDataHeader.get(20), Tan);
        listHashMap.put(listDataHeader.get(21), Arctan);
        listHashMap.put(listDataHeader.get(22), Readkey);
        listHashMap.put(listDataHeader.get(23), GOTOXY);
        listHashMap.put(listDataHeader.get(24), DELLINE);
        listHashMap.put(listDataHeader.get(25), INSLINE);
        listHashMap.put(listDataHeader.get(26), DELAY);
        listHashMap.put(listDataHeader.get(27), CONCAT);
        listHashMap.put(listDataHeader.get(28), COPY);
        listHashMap.put(listDataHeader.get(29), DELETE);
        listHashMap.put(listDataHeader.get(30), INSERT);
        listHashMap.put(listDataHeader.get(31), LENGTH);
        listHashMap.put(listDataHeader.get(32), POS);
        listHashMap.put(listDataHeader.get(33), STR);
        listHashMap.put(listDataHeader.get(34), VAL);
        listHashMap.put(listDataHeader.get(35), UPCASE);
        listHashMap.put(listDataHeader.get(36), CHR);
        listHashMap.put(listDataHeader.get(37), ORD);
        listHashMap.put(listDataHeader.get(38), ROUND);
        listHashMap.put(listDataHeader.get(39), TRUNC);
        listHashMap.put(listDataHeader.get(40), SUCC);
        listHashMap.put(listDataHeader.get(41), PRED);
        listHashMap.put(listDataHeader.get(42), INC);
        listHashMap.put(listDataHeader.get(43), DEC);
        listHashMap.put(listDataHeader.get(44), TEXTCOLOR);
        listHashMap.put(listDataHeader.get(45), TEXTBACKGROUND);
        listHashMap.put(listDataHeader.get(46), WINDOW);
        listHashMap.put(listDataHeader.get(47), TEXTMODE);
        listHashMap.put(listDataHeader.get(48), SOUND);

    }
}