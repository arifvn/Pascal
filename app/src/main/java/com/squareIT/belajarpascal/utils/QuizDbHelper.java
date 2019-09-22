package com.squareIT.belajarpascal.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareIT.belajarpascal.model.Category;
import com.squareIT.belajarpascal.model.Question;
import com.squareIT.belajarpascal.utils.QuizContract.CategoriesTable;
import com.squareIT.belajarpascal.utils.QuizContract.QuestionTable;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizPascal.db";
    private static final int DATABASE_VERSION = 1;

    //Buat instance agar hanya ada satu database untuk semua activity
    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ketika pertama kali kelas ini QuizDbHelper di Instansiasi -> Buat tabel baru
        //Tabel hanya sekali dibuat saat pertama install app
        //Kalau mau refactor column harus reinstall app atau panggil method onUpgrade dibawah
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT," +
                CategoriesTable.COLUMN_HIGHSCORE + " INTEGER" +
                ")";

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_OPTION5 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NUMBER + " INTEGER, " +
                QuestionTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        //Eksekusi Perintah SQL pembuatan tabel diatas
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTION_TABLE);

        //Isi tabel dengan data
        fillCategoriesTable();
        fillQuestionTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Latihan Soal 1", 0);
        addCategory(c1);

        Category c2 = new Category("Latihan Soal 2", 0);
        addCategory(c2);

        Category c3 = new Category("Latihan Soal 3", 0);
        addCategory(c3);
    }

    private void addCategory(Category category) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoriesTable.COLUMN_NAME, category.getName());
        contentValues.put(CategoriesTable.COLUMN_HIGHSCORE, category.getHighscore());
        db.insert(CategoriesTable.TABLE_NAME, null, contentValues);
    }

    private void fillQuestionTable() {
        Question q1 = new Question(
                "Dalam menyusun suatu program,langkah pertama yang harus di lakkukan adalah ...",
                "A. Membuat program",
                "B. Membuat Algoritma",
                "C. Membeli komputer",
                "D. Proses",
                "E. Mempelajari program",
                2,
                Category.LATIHAN_SOAL_1);
        addQuestion(q1);

        Question q2 = new Question(
                "Sebuah prosedur langkah demi langkah yang pasti untuk menyelesaikan sebuah   masalah di sebut ...",
                "A. Proses",
                "B. Program",
                "C. Algoritma",
                "D. Step",
                "E. Diagram",
                3,
                Category.LATIHAN_SOAL_1);
        addQuestion(q2);

        Question q3 = new Question(
                "Pseudocode yang di gunakan pada penulisan algoritma berupa ...",
                "A. Bahasa Inggris",
                "B. Bahasa Puitis",
                "C. Bahasa pemograman",
                "D. Sembarang bahasa asal terstruktur",
                "E. Bahasa Mesin",
                3,
                Category.LATIHAN_SOAL_1);
        addQuestion(q3);

        Question q4 = new Question(
                "Pada pembuatan program komputer, algoritma dibuat ...",
                "A. Sebelum pembuatan program",
                "B. Pada saat program dibuat",
                "C. Sesudah pembuatan program",
                "D. Pada saat verifikasi program",
                "E. Pada saat di jalankan",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q4);

        Question q5 = new Question(
                "Tahapan dalam menyelesaikan suatu masalah adalah ...",
                "A. Masalah-Pseudocode-Flowchart-Program-Eksekusi-Hasil",
                "B. Masalah-Algoritma-Flowchart-Program-Eksekusi-Hasil",
                "C. Masalah-Model-Algoritma-Eksekusi-Hasil",
                "D. Masalah-Model-Algoritma-Program-Eksekusi-hasil ",
                "E. Algoritma-Program-Model-Eksekusi-Hasil",
                4,
                Category.LATIHAN_SOAL_1);
        addQuestion(q5);

        Question q6 = new Question(
                "Diketahui bahwa kantong P kosong. Kantong Q berisi 10 buah kelereng dan kantong R berisi 15 kelereng. Apabila yang terbawa hanya sebuah kantong dan di katakan BUKAN kantong P yang terbawa, Maka jumlah kelereng yang terbawa adalah ...",
                "A. 10",
                "B. 15",
                "C. 10 atau 15",
                "D. 10 dan 15",
                "E. Kosong",
                3,
                Category.LATIHAN_SOAL_1);
        addQuestion(q6);

        Question q7 = new Question(
                "Apabila a=5, b=10, maka jika di berikan instruksi a=b; b=a akan mengakibatkan ...",
                "A. a=0, b=5",
                "B. a=10, b=5",
                "C. a=10, b=0",
                "D. a=b",
                "E. a=10, b=10",
                5,
                Category.LATIHAN_SOAL_1);
        addQuestion(q7);

        Question q8 = new Question(
                "Diberikan algoritma : Apabila warna merah maka jadi hijau. Apabila warna hijau maka jadi putih, selain warna merah dan hijau maka jadi ungu. Jika kondisi input warna adalah hitam, maka warna jadi ...",
                "A. Merah",
                "B. Ungu",
                "C. Hijau",
                "D. Putih",
                "E. Abu-abu",
                2,
                Category.LATIHAN_SOAL_1);
        addQuestion(q8);

        Question q9 = new Question(
                "Instruksi P=Q akan mengakibatkan nilai P=nilaiQ dan nilai Q menjadi ...",
                "A. Menjadi Sembarang Nilai",
                "B. Menjadi hampa",
                "C. Q tetap",
                "D. Menjadi 10",
                "E. P tetap",
                5,
                Category.LATIHAN_SOAL_1);
        addQuestion(q9);

        Question q10 = new Question(
                "Di berikan algoritma P=10; P=P+5; Q=P. Nilai P dan Q masing-masing adalah ...",
                "A. 15 dan 0",
                "B. 0 dan 15",
                "C. 15 dan 15",
                "D. 0 dan 10",
                "E. 10 dan 15",
                3,
                Category.LATIHAN_SOAL_1);
        addQuestion(q10);

        Question q11 = new Question(
                "Struktur pertama dalam pascal adalah ...",
                "A. Char",
                "B. String",
                "C. End",
                "D. Writeln",
                "E. Uses crt",
                5,
                Category.LATIHAN_SOAL_1);
        addQuestion(q11);

        Question q12 = new Question(
                "Untuk melihat hasil dari program yang di kerjakan tekan tombol ...",
                "A. CRTL+F5",
                "B. CRTL+F9",
                "C. CRTL+F2",
                "D. CRTL+F12",
                "E. CRTL+F4",
                2,
                Category.LATIHAN_SOAL_1);
        addQuestion(q12);

        Question q13 = new Question(
                "Berapa kode yang digunakan untuk membuat tulisan berwarna \"CYAN\" ...",
                "A. 4",
                "B. 2",
                "C. 128",
                "D. 7",
                "E. 3",
                3,
                Category.LATIHAN_SOAL_1);
        addQuestion(q13);

        Question q14 = new Question(
                "Perintah dasar sederhana dalam program pascal adalah ...",
                "A. Readln",
                "B. End",
                "C. Write",
                "D. Wtiteln",
                "E. Uses crt",
                3,
                Category.LATIHAN_SOAL_1);
        addQuestion(q14);

        Question q15 = new Question(
                "Tipe data bahasa pascal untuk TRUE FALSE adalah ...",
                "A. String",
                "B. Char",
                "C. Boolean",
                "D. Byte",
                "E. Real",
                3,
                Category.LATIHAN_SOAL_1);
        addQuestion(q15);

        Question q16 = new Question(
                "Siapkah penemu program pascal ...",
                "A. Greyson change",
                "B. Prof.niklaus wirth",
                "C. Kondrazuse",
                "D. Prof.niklaus smirth",
                "E. Dr.harcules",
                2,
                Category.LATIHAN_SOAL_1);
        addQuestion(q16);

        Question q17 = new Question(
                "Struktur bahasa pemrograman pascal paling pertama adalah ...",
                "A. Var",
                "B. Begin",
                "C. End",
                "D. Uses crt",
                "E. Writeln",
                4,
                Category.LATIHAN_SOAL_1);
        addQuestion(q17);

        Question q18 = new Question(
                "Tipe data pascal untuk karakter adalah ...",
                "A. Char",
                "B. Integer",
                "C. Boolean",
                "D. Real",
                "E. Byte",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q18);

        Question q19 = new Question(
                "Kapan terbentuknya pascal ...",
                "A. 1981",
                "B. 1971",
                "C. 1961",
                "D. 1991",
                "E. 1987",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q19);

        Question q20 = new Question(
                "Apa kepanjangan dari USES ...",
                "A. Unit secure",
                "B. Unit syntax",
                "C. Unit system",
                "D. Up software",
                "E. Unit semiconductor",
                3,
                Category.LATIHAN_SOAL_1);
        addQuestion(q20);

        Question q21 = new Question(
                "Berikut ini adalah penulisan identifier yang benar dalam pemrograman pascal adalah ...",
                "A. 9program_satu",
                "B. Program_1",
                "C. Program satu",
                "D. Begin",
                "E. Array",
                2,
                Category.LATIHAN_SOAL_1);
        addQuestion(q21);

        Question q22 = new Question(
                "Tipe bilangan bulat dalam bahasa pascal dikenal sebagai ...",
                "A. Byte",
                "B. Integer",
                "C. Char",
                "D. String",
                "E. Boolean",
                2,
                Category.LATIHAN_SOAL_1);
        addQuestion(q22);

        Question q23 = new Question(
                "Istilah \"perulangan\" dalam pemrograman pascal dikenal dengan ...",
                "A. Repeating",
                "B. Again",
                "C. Function",
                "D. Replay",
                "E. Looping",
                5,
                Category.LATIHAN_SOAL_1);
        addQuestion(q23);

        Question q24 = new Question(
                "Perintah untuk menutup program dalam pascal adalah ...",
                "A. End",
                "B. Program",
                "C. Uses crt",
                "D. Finish",
                "E. End",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q24);

        Question q25 = new Question(
                "Menggambarkan program secara logika merupakan fungsi dari ...",
                "A. Flowchart",
                "B. Dxdiag",
                "C. Begin",
                "D. SI",
                "E. Sistem operasi",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q25);

        Question q26 = new Question(
                "Deklarasi yang digunakan untuk mengidentifikasikan data yang nilainya sudah ditentukan dan pasti,tidak dapat dirubah dalam program disebut deklarasi ...",
                "A. Deklarasi label",
                "B. Deklarasi konstanta",
                "C. Deklarasi tipe",
                "D. Deklarasi variabel",
                "E. Deklarasi prosedur",
                2,
                Category.LATIHAN_SOAL_1);
        addQuestion(q26);

        Question q27 = new Question(
                "Di bawah ini termasuk dalam deklarasi dalam pascal, kecuali ...",
                "A. Deklarasi perubah",
                "B. Deklarasi proses",
                "C. Deklarasi konstanta",
                "D. Deklarasi tipe",
                "E. Deklarasi prosedur",
                2,
                Category.LATIHAN_SOAL_1);
        addQuestion(q27);

        Question q28 = new Question(
                "Bilangan yang mengandung pecahan, paling sedikit harus ada satu digit sebelum dan sesudah titik desimal termasuk dalam tipe data ...",
                "A. Real",
                "B. Boolean",
                "C. Integer",
                "D. Longint",
                "E. Byte",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q28);

        Question q29 = new Question(
                "Prosedur yang digunakan untuk membersihkan layar saat program dijalankan adalah ...",
                "A. Writeln",
                "B. Readln",
                "C. Begin",
                "D. Write",
                "E. Clrscr",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q29);

        Question q30 = new Question(
                "Bentuk dari suatu statement IF berada di dalam lingkungan statement IF yang lainya, disebut IF dalam kondisi ...",
                "A. IF bersarang",
                "B. IF bercabang",
                "C. IF tunggal",
                "D. IF-THEN",
                "E. IF do while-Until",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q30);

        Question q31 = new Question(
                "Tipe data terstruktur yang terdiri dari sejumlah komponen, komponen yang mempunyai tipe sama, disebut tipe data ...",
                "A. Array",
                "B. Byte",
                "C. Longint",
                "D. Integer",
                "E. Boolean",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q31);

        Question q32 = new Question(
                "Perintah untuk menampilkan atau cetak di layar monitor tanpa pindah baris, disebut... ...",
                "A. Writeln",
                "B. Write",
                "C. Readln",
                "D. Read",
                "E. Begin",
                2,
                Category.LATIHAN_SOAL_1);
        addQuestion(q32);

        Question q33 = new Question(
                "Perintah untuk menampilkan atau mencetak di layar monitor lalu pindah baris kebawah, disebut ...",
                "A. Writeln",
                "B. Write",
                "C. Readln",
                "D. Read",
                "E. Begin",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q33);

        Question q34 = new Question(
                "Suatu indentifier non standar yang nilainya tidak tetap atau nilainya merupakan hasil dari suatu proses,disebut ...",
                "A. Variabel",
                "B. Ripe data",
                "C. prosedur",
                "D. deklarasi",
                "E. Array",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q34);

        Question q35 = new Question(
                "Suatu program terpisah dalam blok sendiri yang berfungsi sebagai subprogram (program bagian), disebut ...",
                "A. Variabel",
                "B. Tipe data",
                "C. Prosedur",
                "D. Deklarasi",
                "E. Array",
                3,
                Category.LATIHAN_SOAL_1);
        addQuestion(q35);

        Question q36 = new Question(
                "Berikut ini yang termasuk operator aritmatika yaitu ...",
                "A. *",
                "B. /",
                "C. %",
                "D. +",
                "E. Semua benar",
                5,
                Category.LATIHAN_SOAL_1);
        addQuestion(q36);

        Question q37 = new Question(
                "Array terdiri dari berbagai tipe kecuali ...",
                "A. Array Multi-Dimensi",
                "B. Array Dimensi Satu",
                "C. Array Dimensi Tiga",
                "D. Array Dimensi Dua",
                "E. Semua jawaban benar",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q37);

        Question q38 = new Question(
                "Nama variabel berikut ini yang benar adalah ...",
                "A. NamaGuru",
                "B. Alamat Guru",
                "C. alm gr",
                "D. sts/status",
                "E. tpt.tgl",
                1,
                Category.LATIHAN_SOAL_1);
        addQuestion(q38);

        Question q39 = new Question(
                "Tipe data yang cocok untuk menyimpan data nama siswa adalah ...",
                "A. Numeric",
                "B. Character",
                "C. Date/Time",
                "D. Array",
                "E. Integer",
                2,
                Category.LATIHAN_SOAL_1);
        addQuestion(q39);

        Question q40 = new Question(
                "Pemberian nama variabel yang benar adalah ...",
                "A. %nilai",
                "B. nilai_mahasiswa",
                "C. nama mahasiswa",
                "D. &panjang",
                "E. alamat!",
                2,
                Category.LATIHAN_SOAL_1);
        addQuestion(q40);

        /*SOAL 2---------------------------------------------------------------------------------------------*/

        Question q41 = new Question(
                "Struktur pertama dalam pascal adalah ...",
                "A. Char",
                "B. String",
                "C. End",
                "D. Writeln",
                "E. Uses crt",
                5,
                Category.LATIHAN_SOAL_2);
        addQuestion(q41);

        Question q42 = new Question(
                "Untuk melihat hasil dari program yang dikerjakan tekan tombol ...",
                "A. Crtl+f5",
                "B. Ctrl+f9",
                "C. Ctrl+f2",
                "D. Ctrl+f12",
                "E. Ctrl+f4",
                2,
                Category.LATIHAN_SOAL_2);
        addQuestion(q42);

        Question q43 = new Question(
                "Berapa kode yang digunakan untuk membuat tulisan berwarna \"CYAN\" ...",
                "A. 4",
                "B. 2",
                "C. 128",
                "D. 7",
                "E. 3",
                3,
                Category.LATIHAN_SOAL_2);
        addQuestion(q43);

        Question q44 = new Question(
                "Perintah dasar sederhana dalam program pascal adalah ...",
                "A. Readln",
                "B. End",
                "C. Write",
                "D. Writeln",
                "E. Uses crt",
                4,
                Category.LATIHAN_SOAL_2);
        addQuestion(q44);

        Question q45 = new Question(
                "Tipe data bahasa pascal untuk TRUE FALSE adalah ...",
                "A. String",
                "B. Char",
                "C. Boolean",
                "D. Byte",
                "E. Real",
                3,
                Category.LATIHAN_SOAL_2);
        addQuestion(q45);

        Question q46 = new Question(
                "Siapakah penemu program pascal ...",
                "A. Greyson change",
                "B. Prof. Niklaus Wirth",
                "C. Kondrazuse",
                "D. Prof. Niklaus Smirth",
                "E. Dr. Hercules",
                2,
                Category.LATIHAN_SOAL_2);
        addQuestion(q46);

        Question q47 = new Question(
                "Struktur bahasa pemrograman pascal paling pertama adalah ...",
                "A. Var",
                "B. Begin",
                "C. End",
                "D. Uses crt",
                "E. Writeln",
                4,
                Category.LATIHAN_SOAL_2);
        addQuestion(q47);

        Question q48 = new Question(
                "Tipe data pascal untuk karakter adalah ...",
                "A. Char",
                "B. Boolean",
                "C. Integer",
                "D. Real",
                "E. Byte",
                1,
                Category.LATIHAN_SOAL_2);
        addQuestion(q48);

        Question q49 = new Question(
                "Kapan terbentuknya pascal ...",
                "A. 1981",
                "B. 1971",
                "C. 1961",
                "D. 1991",
                "E. 1987",
                2,
                Category.LATIHAN_SOAL_2);
        addQuestion(q49);

        Question q50 = new Question(
                "Tipe bilangan bulat dalam bahasa pascal dikenal dengan ...",
                "A. Byte",
                "B. Integer",
                "C. Char",
                "D. String",
                "E. Boolean",
                2,
                Category.LATIHAN_SOAL_2);
        addQuestion(q50);

        Question q51 = new Question(
                "Apakah kepanjangan dari USES ...",
                "A. Unit secure",
                "B. Unit syntax",
                "C. Unit system",
                "D. Up software",
                "E. Unit semiconductor",
                3,
                Category.LATIHAN_SOAL_2);
        addQuestion(q51);

        Question q52 = new Question(
                "Berikut ini adalah penulisan identifier yang benar dalam pemrograman pascal adalah ...",
                "A. 9program_satu",
                "B. Program_satu",
                "C. Program satu",
                "D. Begin",
                "E. Array",
                2,
                Category.LATIHAN_SOAL_2);
        addQuestion(q52);

        Question q53 = new Question(
                "Perintah untuk menutup program pada Pascal adalah ...",
                "A. Program",
                "B. Finish",
                "C. End",
                "D. Uses crt",
                "E. Read",
                3,
                Category.LATIHAN_SOAL_2);
        addQuestion(q53);

        Question q54 = new Question(
                "Istilah “perulangan” dalam pemrograman pascal dikenal sebagai ...",
                "A. Repeating",
                "B. Again",
                "C. Function",
                "D. Replay",
                "E. Looping",
                5,
                Category.LATIHAN_SOAL_2);
        addQuestion(q54);

        Question q55 = new Question(
                "Menggambarkan program secara logika merupakan fungsi dari ...",
                "A. flowchart",
                "B. DxDiag",
                "C. Begin",
                "D. Begin",
                "E. Sistem Operasi",
                1,
                Category.LATIHAN_SOAL_2);
        addQuestion(q55);

        Question q56 = new Question(
                "Tipe data terstruktur yang terdiri dari sejumlah komponen-komponen yang mempunyai tipe sama, disebut tipe data ...",
                "A. Array",
                "B. Byte",
                "C. Longint",
                "D. Integer",
                "E. Boolean",
                1,
                Category.LATIHAN_SOAL_2);
        addQuestion(q56);

        Question q57 = new Question(
                "Deklarasi yang digunakan untuk mengidentifikasi data yang nilainya sudah ditentukan dan pasti, tidak dirubah dalam program disebut deklarasi ...",
                "A. Deklarasi label",
                "B. Deklarasi Konstanta",
                "C. Deklarasi tipe",
                "D. Deklarasi Variabel",
                "E. Deklarasi Prosedur",
                2,
                Category.LATIHAN_SOAL_2);
        addQuestion(q57);

        Question q58 = new Question(
                "Dibawah ini termasuk dalam Deklarasi dalam Pascal kecuali ...",
                "A. Deklarasi perubah",
                "B. Deklarasi proses",
                "C. Deklarasi konstanta",
                "D. Deklarasi tipe",
                "E. Deklarasi prosedure",
                2,
                Category.LATIHAN_SOAL_2);
        addQuestion(q58);

        Question q59 = new Question(
                "Bilangan yang mengandung pecahan, paling sedikit harus ada satu digit sebelum dan sesudah titik desimal termasuk dalam tipe data ...",
                "A. Boolean",
                "B. Real",
                "C. Integer",
                "D. Longint",
                "E. Byte",
                2,
                Category.LATIHAN_SOAL_2);
        addQuestion(q59);

        Question q60 = new Question(
                "Prosedur yang digunakan untuk membersihkan layar saat program dijalankan adalah ...",
                "A. Writeln",
                "B. Readln",
                "C. Begin",
                "D. Write",
                "E. Clrscr",
                5,
                Category.LATIHAN_SOAL_2);
        addQuestion(q60);

        Question q61 = new Question(
                "Bentuk dari suatu statement IF berada didalam lingkungan statement IF yang lainnya, disebut IF dalam kondisi ...",
                "A. IF bersarang",
                "B. IF bercabang",
                "C. IF tunggal",
                "D. IF tunggal",
                "E. IF do while-until",
                1,
                Category.LATIHAN_SOAL_2);
        addQuestion(q61);

        Question q62 = new Question(
                "Suatu program terpisah dalam blok sendiri yang berfungsi sebagai subprogram (bagian program), disebut ...",
                "A. Variabel",
                "B. Tipe Data",
                "C. Prosedur",
                "D. Deklarasi",
                "E. Array",
                3,
                Category.LATIHAN_SOAL_2);
        addQuestion(q62);

        Question q63 = new Question(
                "Suatu identifier non-standar yang nilainya tidak tetap atau nilainya merupakan hasil dari suatu proses, disebut ...",
                "A. Variabel",
                "B. Tipe Data",
                "C. Prosedur",
                "D. Deklarasi",
                "E. Array",
                1,
                Category.LATIHAN_SOAL_2);
        addQuestion(q63);

        Question q64 = new Question(
                "Perintah untuk menampilkan atau cetak dilayar monitor lalu pindah baris kebawah, disebut ...",
                "A. Writeln",
                "B. Write",
                "C. Readln",
                "D. Read",
                "E. Begin",
                1,
                Category.LATIHAN_SOAL_2);
        addQuestion(q64);

        Question q65 = new Question(
                "Perintah untuk menampilkan atau cetak dilayar monitor tanpa pindah baris, disebut ...",
                "A. Writeln",
                "B. Write",
                "C. Readln",
                "D. Read",
                "E. Begin",
                2,
                Category.LATIHAN_SOAL_2);
        addQuestion(q65);

        /*SOAL 3 ----------------------------------------------------------------------------------*/

        Question q66 = new Question(
                "Suatu identifier non-standar yang nilainya tidak tetap atau nilainya merupakan hasil dari suatu proses, disebut ...",
                "A. Variabel",
                "B. Tipe Data",
                "C. Prosedur",
                "D. Deklarasi",
                "E. Array",
                1,
                Category.LATIHAN_SOAL_3);
        addQuestion(q66);

        Question q67 = new Question(
                "Diketahui bahwa kantong P kosong. Kantong Q berisi 10 buah kelereng dan kantong R berisi 15 kelereng. Apabila yang terbawa hanya sebuah kantong dan di katakan BUKAN kantong P yang terbawa, Maka jumlah kelereng yang terbawa adalah ...",
                "A. 10",
                "B. 15",
                "C. 10 atau 15",
                "D. 10 dan 15",
                "E. Kosong",
                3,
                Category.LATIHAN_SOAL_3);
        addQuestion(q67);

        Question q68 = new Question(
                "Instruksi P=Q akan mengakibatkan nilai P=nilaiQ dan nilai Q menjadi ...",
                "A. Menjadi Sembarang Nilai",
                "B. Menjadi hampa",
                "C. Q tetap",
                "D. Menjadi 10",
                "E. P tetap",
                5,
                Category.LATIHAN_SOAL_3);
        addQuestion(q68);

        Question q69 = new Question(
                "Di berikan algoritma P=10; P=P+5; Q=P. Nilai P dan Q masing-masing adalah ...",
                "A. 15 dan 0",
                "B. 0 dan 15",
                "C. 15 dan 15",
                "D. 0 dan 10",
                "E. 10 dan 15",
                3,
                Category.LATIHAN_SOAL_3);
        addQuestion(q69);

        Question q70 = new Question(
                "Untuk melihat hasil dari program yang di kerjakan tekan tombol ...",
                "A. CRTL+F5",
                "B. CRTL+F9",
                "C. CRTL+F2",
                "D. CRTL+F12",
                "E. CRTL+F4",
                2,
                Category.LATIHAN_SOAL_3);
        addQuestion(q70);

        Question q71 = new Question(
                "Berikut ini adalah penulisan identifier yang benar dalam pemrograman pascal adalah ...",
                "A. 9program_satu",
                "B. Program_1",
                "C. Program satu",
                "D. Begin",
                "E. Array",
                2,
                Category.LATIHAN_SOAL_3);
        addQuestion(q71);

        Question q72 = new Question(
                "Menggambarkan program secara logika merupakan fungsi dari ...",
                "A. Flowchart",
                "B. Dxdiag",
                "C. Begin",
                "D. SI",
                "E. Sistem operasi",
                1,
                Category.LATIHAN_SOAL_3);
        addQuestion(q72);

        Question q73 = new Question(
                "Nama variabel berikut ini yang benar adalah ...",
                "A. NamaGuru",
                "B. Alamat Guru",
                "C. alm gr",
                "D. sts/status",
                "E. tpt.tgl",
                1,
                Category.LATIHAN_SOAL_3);
        addQuestion(q73);

        Question q74 = new Question(
                "Tipe data yang cocok untuk menyimpan data nama siswa adalah ...",
                "A. Numeric",
                "B. Character",
                "C. Date/Time",
                "D. Array",
                "E. Integer",
                2,
                Category.LATIHAN_SOAL_3);
        addQuestion(q74);

        Question q75 = new Question(
                "Struktur pertama dalam pascal adalah ...",
                "A. Char",
                "B. String",
                "C. End",
                "D. Writeln",
                "E. Uses crt",
                5,
                Category.LATIHAN_SOAL_3);
        addQuestion(q75);

        Question q76 = new Question(
                "Kapan terbentuknya pascal ...",
                "A. 1981",
                "B. 1971",
                "C. 1961",
                "D. 1991",
                "E. 1987",
                2,
                Category.LATIHAN_SOAL_3);
        addQuestion(q76);

        Question q77 = new Question(
                "Apakah kepanjangan dari USES ...",
                "A. Unit secure",
                "B. Unit syntax",
                "C. Unit system",
                "D. Up software",
                "E. Unit semiconductor",
                3,
                Category.LATIHAN_SOAL_3);
        addQuestion(q77);

        Question q78 = new Question(
                "Tipe data terstruktur yang terdiri dari sejumlah komponen-komponen yang mempunyai tipe sama, disebut tipe data ...",
                "A. Array",
                "B. Byte",
                "C. Longint",
                "D. Integer",
                "E. Boolean",
                1,
                Category.LATIHAN_SOAL_3);
        addQuestion(q78);

        Question q79 = new Question(
                "Suatu program terpisah dalam blok sendiri yang berfungsi sebagai subprogram (bagian program), disebut ...",
                "A. Variabel",
                "B. Tipe Data",
                "C. Prosedur",
                "D. Deklarasi",
                "E. Array",
                3,
                Category.LATIHAN_SOAL_3);
        addQuestion(q79);

        Question q80 = new Question(
                "Perintah untuk menampilkan atau cetak dilayar monitor lalu pindah baris kebawah, disebut ...",
                "A. Writeln",
                "B. Write",
                "C. Readln",
                "D. Read",
                "E. Begin",
                1,
                Category.LATIHAN_SOAL_3);
        addQuestion(q80);

        Question q81 = new Question(
                "Baris komentar pada Pascal harus diletakkan diantara tanda ...",
                "A. { } atau (  )",
                "B. ‘ ‘ atau { }",
                "C. ( ) atau ‘ ‘",
                "D. (*  *) atau { }",
                "E. (*  *) atau \" \"",
                4,
                Category.LATIHAN_SOAL_3);
        addQuestion(q81);

        Question q82 = new Question(
                "String, Char, integer, real dan boolean termasuk ke dalam tipe data ...",
                "A. User defined",
                "B. Petunjuk",
                "C. Standar ",
                "D. sederhana",
                "E. Klasik",
                3,
                Category.LATIHAN_SOAL_3);
        addQuestion(q82);

        Question q83 = new Question(
                "Struktur program Pascal terdiri dua bagian utama yaitu ...",
                "A. Judul Program, Blok Program",
                "B. Blok deklarasi, Pernyataan",
                "C. Judul program, Blok deklarasi",
                "D. Judul program, Blok Pernyataan",
                "E. Blok Program, blok pernyataan",
                1,
                Category.LATIHAN_SOAL_3);
        addQuestion(q83);

        Question q84 = new Question(
                "Deklarasi label digunakan jika pada penulisan program akan menggunakan statement ...",
                "A. Writeln",
                "B. Write",
                "C. ifThen",
                "D. Goto",
                "E. End",
                4,
                Category.LATIHAN_SOAL_3);
        addQuestion(q84);

        Question q85 = new Question(
                "Prosedur standar Clrscr adalah untuk menghapus layar namun untuk menggunakannya unit yang harus disebutkan dalam program adalah ...",
                "A. CRT",
                "B. User Screen",
                "C. CTR",
                "D. Graph",
                "E. Begin",
                1,
                Category.LATIHAN_SOAL_3);
        addQuestion(q85);
    }

    private void addQuestion(Question question) {
        ContentValues contentValues = new ContentValues();
        //Buat pasangan KUNCI dan ISI dari data tersebut -> Insert ke Tabel
        contentValues.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        contentValues.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        contentValues.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        contentValues.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        contentValues.put(QuestionTable.COLUMN_OPTION4, question.getOption4());
        contentValues.put(QuestionTable.COLUMN_OPTION5, question.getOption5());
        contentValues.put(QuestionTable.COLUMN_ANSWER_NUMBER, question.getAnswerNumber());
        contentValues.put(QuestionTable.COLUMN_CATEGORY_ID, question.getCategoryId());

        db.insert(QuestionTable.TABLE_NAME, null, contentValues);
    }

    //Ambil kembali data(row) yang tersimpan dalam tabel, Masukan kembali ke object Category
    //Akumulasikan masing-masing object Category dalam bentuk List Category untuk mendapat semua row
    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> categoryArrayList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (cursor != null && cursor.getCount() >0 && cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(cursor.getColumnIndex(CategoriesTable._ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                category.setHighscore(cursor.getInt(cursor.getColumnIndex(CategoriesTable.COLUMN_HIGHSCORE)));
                categoryArrayList.add(category);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return categoryArrayList;
    }

    public void updateHighscore(int newHighscore, int id) {
        db = getWritableDatabase();

        Cursor cursor = db.rawQuery("UPDATE " + CategoriesTable.TABLE_NAME + " SET " + CategoriesTable.COLUMN_HIGHSCORE + "=" +
                newHighscore + " WHERE " + CategoriesTable._ID + "=" + id, null);

        Category category = new Category();

        if (cursor != null && cursor.getCount() >0 && cursor.moveToFirst()) {
            do {
                category.setId(cursor.getInt(cursor.getColumnIndex(CategoriesTable._ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                category.setHighscore(cursor.getInt(cursor.getColumnIndex(CategoriesTable.COLUMN_HIGHSCORE)));
            } while (cursor.moveToNext());
        }

        cursor.close();
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (cursor != null && cursor.getCount() >0 && cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setOption5(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION5)));
                question.setAnswerNumber(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_ANSWER_NUMBER)));
                question.setCategoryId(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));

                questionList.add(question);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryId) {
        ArrayList<Question> questionArrayList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionTable.COLUMN_CATEGORY_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(categoryId)};

        Cursor cursor = db.query(
                QuestionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.getCount() >0 && cursor.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setOption5(cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_OPTION5)));
                question.setAnswerNumber(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_ANSWER_NUMBER)));
                question.setCategoryId(cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));

                questionArrayList.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return questionArrayList;
    }
}

