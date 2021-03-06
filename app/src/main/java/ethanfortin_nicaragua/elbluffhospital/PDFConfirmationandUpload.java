package ethanfortin_nicaragua.elbluffhospital;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import static ethanfortin_nicaragua.elbluffhospital.R.id.imageView;
import static ethanfortin_nicaragua.elbluffhospital.R.id.pdfView;

public class PDFConfirmationandUpload extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    private ProgressDialog progress;

    @Override
    //Opening the photo gallery
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_confirmation_and_upload);

        //Create intent to open galley
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        //Start the intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);

        final Button confirm_doc = (Button) findViewById(R.id.confirm_doc);
        confirm_doc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "El documento se ha guardado.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast2 = Toast.makeText(context, text, duration);
                toast2.show();

                Intent i = new Intent(confirm_doc.getContext(),DoctorMain.class);
                startActivity(i);
            }
        });

    }

    //Choosing a picture and displaying it on the screen
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //When an image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                //Get the image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                //Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);

                //Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.pdfView);
                //Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
            } else {
                Toast.makeText(this, "You haven't picked an Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_LONG).show();
        }

    }

/*    Touch the picture to make it full screen
    boolean isImageFitToScreen;
    ImageView imgView = (ImageView) findViewById(R.id.pdfView);

    @Override
            imgView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void pdfbig(View v){
            if (isImageFitToScreen) {
                isImageFitToScreen = false;
                imgView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                imgView.setAdjustViewBounds(true);
            } else {
                isImageFitToScreen = true;
                imgView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                imgView.setScaleType(ImageView.ScaleType.FIT_XY);

            }
        }
    };*/


   /* //Loading bar while picture is uploading
    public void button3(View view) {
        // TODO   This is for the progress bar while the record is being saved to the database. It may be
        // TODO   inoperative but not sure because it isn't linked to anything yet, not sure how it times without file size
        progress = new ProgressDialog(this);
        progress.setMessage("Uploading Record");
        progress.setCancelable(true);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();

        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        sleep(200);
                        jumpTime += 5;
                        progress.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }*/


//    public void BackToPDF(View v) {
//        Intent backtopdf = new Intent(this, UploadOldFileHome.class);
//        startActivity(backtopdf);
//    }



}

