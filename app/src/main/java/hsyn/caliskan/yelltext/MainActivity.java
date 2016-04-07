package hsyn.caliskan.yelltext;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;
//**
// You have to install Turkish package in your phone.
// /
public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        textToSpeech = new TextToSpeech(this,this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                yellPlease();
            }
        });
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS)
        {
            Locale locale = new Locale("tr", "TR");
            int lang = textToSpeech.setLanguage(locale);
            if (lang == TextToSpeech.LANG_MISSING_DATA || lang == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("Yell", "Not Supported Language");
            } else {
                button.setEnabled(true);
                yellPlease();
            }
        }
        else {
            Log.e("Yell", "Failed.");
        }

    }

    private void yellPlease()
    {
        String text = editText.getText().toString();

        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);

    }
}
