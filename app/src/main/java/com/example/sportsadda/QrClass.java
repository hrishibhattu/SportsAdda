package com.example.sportsadda;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QrClass extends AppCompatActivity {

    String text;
    Button gen_button;
    ImageView image;
    String text2Qr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            text = bundle.getString("bookId");
        }


//        gen_button = (Button)findViewById(R.id.generateQR);
        image = (ImageView) findViewById(R.id.image1);
//        gen_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                text2Qr = text;
//                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//                try{
//                    BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,200,200);
//                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
//                    image.setImageBitmap(bitmap);
//                }catch (WriterException e){
//                    e.printStackTrace();
//                }
//
//            }
//        });

        text2Qr = text;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            image.setImageBitmap(bitmap);
        }catch (WriterException e){
            e.printStackTrace();
        }

    }
}
