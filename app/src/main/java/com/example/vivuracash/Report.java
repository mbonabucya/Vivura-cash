package com.example.vivuracash;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Report extends AppCompatActivity {

    private ImageButton back_btn;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView netbalances;
    Button pdfButton;
    DatabaseHelper db;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_layout);
        db = new DatabaseHelper(this);
        netbalances = findViewById(R.id.balance);
        pdfButton = findViewById(R.id.generate_pdf_btn);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Show Incomes"));
        tabLayout.addTab(tabLayout.newTab().setText("Show Expenses"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final Persoanl_Report_adapter adapter = new Persoanl_Report_adapter(this, getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        back_btn = findViewById(R.id.backButton);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), personalActivity.class);
                String userId = getIntent().getStringExtra("user_id");
                intent.putExtra("user_id", userId);
                startActivity(intent);
            }
        });
        pdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bitmap=LoadBitMap(v.getRootView(),v.getRootView().getWidth(),v.getRootView().getHeight());
                createPDF();
            }
        });
        String userId = getIntent().getStringExtra("user_id");
        System.out.println("Gucheckinga id" + userId);
        netbalances.setText(" " + db.TotalBalance(userId) + "RWF");
    }

    public String getUserId() {
        return getIntent().getStringExtra("user_id");
    }

    private Bitmap LoadBitMap(View v, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    private void createPDF() {
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels;
        float height = displayMetrics.heightPixels;
        int convertWidth = (int) width;
        int convertHeight = (int) height;

        // create a new document
        PdfDocument document = new PdfDocument();

        // create a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth,convertHeight, 1).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        // draw something on the page
        Paint paint = new Paint();
        canvas.drawPaint(paint);
        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHeight, true);

        canvas.drawBitmap(bitmap, 0, 0, null);

        // finish the page
        document.finishPage(page);

        // add more pages
        String targetPdf = "/sdcard/Personal_report.pdf";
        File file = new File(
                targetPdf
        );
        try {
            document.writeTo(new FileOutputStream(file));
        } catch (IOException ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Something went wrong!,try again" + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        // close the document
        document.close();
         Toast.makeText(getApplicationContext(), "Successfuly downloaded. ", Toast.LENGTH_SHORT).show();
         openPdf();//this method is used to open  the document
    }

    private void openPdf() {
    File file=new File("sdcard/Personal_report.pdf");

    if(file.exists()){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        Uri uri=Uri.fromFile(file);
        intent.setDataAndType(uri,"application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            startActivity(intent);
        }catch(ActivityNotFoundException e){
            Toast.makeText(getApplicationContext(), "An error occured, maybe an activity is not found :"+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    }
}
