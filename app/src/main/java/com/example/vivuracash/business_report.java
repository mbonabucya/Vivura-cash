package com.example.vivuracash;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
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
import android.graphics.pdf.PdfDocument;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class business_report extends Fragment{

    private ImageButton back_btn;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView netBalances;
    Button GeneratePdf;
    Bitmap bitmap;
    DatabaseHelper db;
    String uid;
    public business_report() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.businnness_report_layout, container, false);
        // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
         uid = sh.getString("user_id", "");


// We can then use the data


      //netbalances.setText(db.TotalBusinessBalance(uid));

        tabLayout =view.findViewById(R.id.tabLayout);
        viewPager =view.findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Show Incomes"));
        tabLayout.addTab(tabLayout.newTab().setText("Show Expenses"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        GeneratePdf=view.findViewById(R.id.bus_report_btn);

        GeneratePdf.setText("Get PDF");
        GeneratePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap=LoadBitMap(v.getRootView(),v.getRootView().getWidth(),v.getRootView().getHeight());
                createPDF();
            }
        });

        final Business_Report_adapter adapter = new Business_Report_adapter(getContext(),getChildFragmentManager(),
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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        db=new DatabaseHelper(getContext());
        netBalances=view.findViewById(R.id.mybalance);
        String businessName=getActivity().getIntent().getStringExtra("business_name");
        netBalances.append(" "+db.TotalBusinessBalance(uid,businessName));
    }

    private Bitmap LoadBitMap(View v, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    private void createPDF() {
        WindowManager windowManager = (WindowManager)this.getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
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
            Toast.makeText(this.getActivity().getApplicationContext(), "Something went wrong!,try again" + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        // close the document
        document.close();
        Toast.makeText(this.getActivity().getApplicationContext(), "Successfuly downloaded. ", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this.getActivity().getApplicationContext(), "An error occured, maybe an activity is not found :"+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}

