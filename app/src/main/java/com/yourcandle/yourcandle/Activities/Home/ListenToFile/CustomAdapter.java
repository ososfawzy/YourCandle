package com.yourcandle.yourcandle.Activities.Home.ListenToFile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.yourcandle.yourcandle.R;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<PDFDoc> pdfDocs;
    TextToSpeechManager ttsManager;

    public CustomAdapter(Context c, ArrayList<PDFDoc> pdfDocs) {
        this.c = c;
        this.pdfDocs = pdfDocs;
    }

    @Override
    public int getCount() {
        return pdfDocs.size();
    }

    @Override
    public Object getItem(int i) {
        return pdfDocs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            //INFLATE CUSTOM LAYOUT
            view = LayoutInflater.from(c).inflate(R.layout.model, viewGroup, false);
        }

        final PDFDoc pdfDoc = (PDFDoc) this.getItem(i);

        TextView nameTxt = (TextView) view.findViewById(R.id.nameTxt);
        ImageView img = (ImageView) view.findViewById(R.id.pdfImage);

        //BIND DATA
        nameTxt.setText(pdfDoc.getName());
        img.setImageResource(R.drawable.pdf_icon);

        ttsManager = new TextToSpeechManager(c);
        ttsManager.initializeTTS();

        //VIEW ITEM CLICK
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String utteranceId=this.hashCode() + "";
                    ttsManager.speakMessage("Extract Text Started, Please wait...",utteranceId);
                    String text=extractPDF(pdfDoc.getPath());
                    Toast.makeText(c,"Extract Text Done",Toast.LENGTH_LONG).show();
                    ttsManager.speakMessage("Extract Text Done",utteranceId);
                    Intent intent = new Intent(c,TextToSpeechActivity.class);
                    intent.putExtra("PARSEDTEXT",text);
                    intent.putExtra("PDFNAME",pdfDoc.getName());
                    c.startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    //OPEN PDF VIEW
    private String extractPDF(String name) throws IOException {

        PdfReader reader = new PdfReader(name);
        StringBuilder text = new StringBuilder();
        for (int i=1;i<=reader.getNumberOfPages();i++){
            String data = PdfTextExtractor.getTextFromPage(reader,i,new SimpleTextExtractionStrategy());
            text.append(Bidi.BidiText(data,1).getText());
        }
        return text.toString();
    }
}
