package com.mycampusdock.erp;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MarkAttendance extends AppCompatActivity {

    private Spinner classes;
    private RecyclerView students;
    private List<Student> studentList;
    private StudentsAdapter studentsAdapter;
    private Button push;

    private ArrayList<String> students_ab = new ArrayList<>();
    private String target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        final List<String> spinnerArray =  new ArrayList<>();
        spinnerArray.add("6CSA");
        spinnerArray.add("6CSB");
        spinnerArray.add("6CCA");
        spinnerArray.add("6CCB");
        spinnerArray.add("6BAO");
        spinnerArray.add("6ITM");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
        studentList = new ArrayList<>();
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classes = findViewById(R.id.classes);
        classes.setAdapter(adapter);
        classes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                        studentList.clear();
                        studentList.add(new Student("Dolly", "12341"));
                        studentList.add(new Student("Rahul", "12342"));
                        studentList.add(new Student("Urmit", "12343"));
                        studentList.add(new Student("Mongo", "12344"));
                        studentList.add(new Student("Harry", "12345"));
                        studentList.add(new Student("Divya", "12346"));
                        studentList.add(new Student("Golu", "12347"));
                        studentList.add(new Student("Chotu", "12348"));
                        studentsAdapter.notifyDataSetChanged();
                        break;
                    case 1 :
                        studentList.clear();
                        studentList.add(new Student("Ayushi", "12331"));
                        studentList.add(new Student("Shefali", "12332"));
                        studentList.add(new Student("Ali", "12333"));
                        studentList.add(new Student("Shahrukh", "12334"));
                        studentList.add(new Student("Hrithik", "12335"));
                        studentList.add(new Student("Roshan", "12336"));
                        studentList.add(new Student("Cute", "12337"));
                        studentList.add(new Student("Munda", "12338"));
                        studentList.add(new Student("Expert", "12339"));
                        studentList.add(new Student("Jaat", "123310"));
                        studentsAdapter.notifyDataSetChanged();

                    case 2 :
                        studentList.clear();
                        studentList.add(new Student("Ligox1", "12331"));
                        studentList.add(new Student("Ligox2", "12332"));
                        studentList.add(new Student("Ligox3", "12333"));
                        studentList.add(new Student("Ligox4", "12334"));
                        studentList.add(new Student("Ligox5", "12335"));
                        studentList.add(new Student("Ligox6", "12336"));
                        studentList.add(new Student("Ligox7", "12337"));
                        studentList.add(new Student("Ligox8", "12338"));
                        studentList.add(new Student("Ligox9", "12339"));
                        studentList.add(new Student("Ligox10", "123310"));
                        studentsAdapter.notifyDataSetChanged();
                        break;
                    case 3 :
                        studentList.clear();
                        studentList.add(new Student("Pix1", "12321"));
                        studentList.add(new Student("Pix2", "12322"));
                        studentList.add(new Student("Pix3", "12323"));
                        studentList.add(new Student("Pix4", "12324"));
                        studentList.add(new Student("Pix5", "12325"));
                        studentList.add(new Student("Pix6", "12326"));
                        studentsAdapter.notifyDataSetChanged();
                        break;
                    case 4 :
                        studentList.clear();
                        studentList.add(new Student("Poonam", "12311"));
                        studentList.add(new Student("Avinash", "12312"));
                        studentList.add(new Student("Rajat", "12313"));
                        studentList.add(new Student("Vijaya", "12314"));
                        studentList.add(new Student("Dipesh", "12315"));
                        studentList.add(new Student("Jaspreet", "12316"));
                        studentList.add(new Student("Manoj", "12317"));
                        studentsAdapter.notifyDataSetChanged();
                        break;
                    case 5 :
                        studentList.clear();
                        studentList.add(new Student("Vipul", "12301"));
                        studentList.add(new Student("Nitin", "12302"));
                        studentList.add(new Student("Rajmani", "12303"));
                        studentList.add(new Student("Anadi", "12304"));
                        studentList.add(new Student("Abhishek", "12305"));
                        studentList.add(new Student("Deepanshu", "12306"));
                        studentList.add(new Student("Asad", "12307"));

                        studentsAdapter.notifyDataSetChanged();
                        break;
                }
                target = spinnerArray.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        students = findViewById(R.id.students);
        push = findViewById(R.id.push);
        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
                Date now = new Date();
                String fileName = formatter.format(now) + "_" + target + ".txt";
                for(Student s : studentList){
                    if(s.isMarked())
                        students_ab.add(s.getName()+" :: Roll :: "+s.getRoll());
                }
                storeOnDB(getApplicationContext(), fileName, students_ab);
            }
        });
        studentsAdapter = new StudentsAdapter(getApplicationContext(), studentList, new StudentsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Student item, View view, int pos) {
                item.setMarked(!item.isMarked());
                if(item.isMarked()){
                    view.setAlpha(0.5f);
                }
                else
                    view.setAlpha(0.5f);
            }
        });

        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getApplicationContext());
        students.setLayoutManager(gridLayoutManager);
        students.setAdapter(studentsAdapter);
    }

    public void storeOnDB(Context context, String sFileName, ArrayList<String> sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory()+File.separator+"ERP", "AttendanceDB");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            String body = "";
            for(String s : sBody){
                body = body + s + "\n";
            }
            writer.append(body);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved To DB", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
