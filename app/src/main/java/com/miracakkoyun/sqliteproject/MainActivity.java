package com.miracakkoyun.sqliteproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try{
            SQLiteDatabase database= this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            //bir veri tabanı oluşturuyoruz Musicians sınıf adında

            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY,name VARCHAR,age INT)");
            // Veri tabanına eklenecek veri tiplerini bekliyorun birer string ve int

            //database.execSQL("INSERT INTO musicians (name,age)VALUES('James',50)");
            // Veri tabanına james string adında ve 50 değerinde veri ekliyoruz
            // yukarıdaki kodu yorum satırına alma sebebim kod bir kere kayıt oldu ve programı sğrekli çalıştırısam yine kayıt altına alacak amaç bunu engelleme
            //iki defa açtığım için iki tane james var
            //database.execSQL("INSERT INTO musicians (name,age)VALUES('Mirac',21)");
            //database.execSQL("INSERT INTO musicians (name,age)VALUES('YUSUF',23)");




            //Gğncelleme
            //database.execSQL("UPDATE musicians SET age=35 WHERE name='YUSUF'");
            //database.execSQL("UPDATE musicians SET id=21 WHERE name='Mirac'");

            //Silme
            //database.execSQL("DELETE FROM musicians WHERE id=21");


            Cursor cursor = database.rawQuery("SELECT * FROM musicians",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE id=2",null);
            //burada idsi 2 olanları filtrelerdik , SELECT * FROM musicians sadece böyle yapsaydık her şeyi getirirdik
            // bir imleç gibi hareket edip bize verileri bildiriyor , query sorgu atmak demek oluyor
            //yıldız * herşey anlamına geliyor yani musicians altındaki tüm verileri istiyoruz


            Cursor cursor2 = database.rawQuery("SELECT * FROM musicians WHERE name LIKE '%Y'",null);
            //küçük harf ile yüzde işareti kullanırsak adının sonu
            int nameIx=cursor.getColumnIndex("name");
            int ageIx=cursor.getColumnIndex("age");// burada name ve age yazammın sebebi 30.satırda veri tipi adını belirtmemden kaynaklı
            int idIX=cursor.getColumnIndex("id");
            int nameIxx=cursor2.getColumnIndex("name");
            int ageIxx=cursor2.getColumnIndex("age");// burada name ve age yazammın sebebi 30.satırda veri tipi adını belirtmemden kaynaklı
            int idIXx=cursor2.getColumnIndex("id");
            while(cursor.moveToNext()){
                //cursor.moveToNext gidebildiği kadar gitsin anlamına geliyor burda
                System.out.println("Name: "+cursor.getString(nameIx));
                System.out.println("Age: "+cursor.getString(ageIx));
                System.out.println("id: "+cursor.getString(idIX));
                System.out.println("Name: "+cursor2.getString(nameIx));
                System.out.println("Age: "+cursor2.getString(ageIx));
                System.out.println("id: "+cursor2.getString(idIX));
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}