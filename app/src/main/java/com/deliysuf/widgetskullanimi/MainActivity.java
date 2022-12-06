package com.deliysuf.widgetskullanimi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.deliysuf.widgetskullanimi.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonResim1.setOnClickListener(view->{
            binding.imageView.setImageResource(R.drawable.resim1);
        });
        binding.buttonResim2.setOnClickListener(view->{
            binding.imageView.setImageResource(getResources().
                    getIdentifier("resim2","drawable",
                            getPackageName()));
        });
        binding.switch1.setOnCheckedChangeListener((v,b)->{
            if(b){
                Log.e("winged","switch On");
            }else{
                Log.e("winged","switch Off");
            }
        });
        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                compoundButton.isChecked();
            }
        });
        binding.checkBox.setOnCheckedChangeListener((v,b)->{
            if(b){
                Log.e("winged","switch On");
            }else{
                Log.e("winged","switch Off");
            }
        });

        binding.barcelona.setOnCheckedChangeListener((v,b)->{
            Log.e("winged","barcelona secildi");
        });
        binding.reelMadrid.setOnCheckedChangeListener((v,b)->{
            Log.e("winged","reelmadrid secildi");
        });

        binding.buttonBasla.setOnClickListener(view->{
            binding.progressBar.setVisibility(View.VISIBLE);

        });
        binding.buttonDur.setOnClickListener(view->{
            binding.progressBar.setVisibility(View.INVISIBLE);

        });
        binding.textViewSonuc.setText(String.valueOf(binding.seekBar4.getProgress()));
        binding.seekBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.textViewSonuc.setText(String.valueOf(binding.seekBar4.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.Saat.setOnClickListener(view->{
          Calendar calender = Calendar.getInstance();
          int saat = calender.get(Calendar.HOUR_OF_DAY);
          int dakika = calender.get(Calendar.MINUTE);

            TimePickerDialog pd = new TimePickerDialog(this,(d,h,m)->{
                binding.editTextSaat.setText(h+""+m);

            },saat,dakika,true);
            pd.setButton(DialogInterface.BUTTON_POSITIVE,"SEC",pd);
            pd.setButton(DialogInterface.BUTTON_NEGATIVE,"IPTAL",pd);
            pd.show();
        });
        binding.Tarih.setOnClickListener(view->{
            Calendar calender = Calendar.getInstance();
            int yil = calender.get(Calendar.YEAR);
            int ay = calender.get(Calendar.MONTH);
            int gun = calender.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog pd = new DatePickerDialog(this,(d,y, a, g)->{
                binding.editTextTarih.setText(y+""+(a+1)+""+g);

            },yil,ay,gun);
            pd.setButton(DialogInterface.BUTTON_POSITIVE,"SEC",pd);
            pd.setButton(DialogInterface.BUTTON_NEGATIVE,"IPTAL",pd);
            pd.show();
        });

        ArrayList<String> ulkeler = new ArrayList<>();

        ulkeler.add("turkiye");
        ulkeler.add("japonya");
        ulkeler.add("urdun");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                ulkeler);
        binding.spinner.setAdapter(adapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Snackbar.make(view,ulkeler.get(i)+" : secildi",Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.buttonGoster.setOnClickListener(view->{
            Log.e("widged","DurumGoster :"+binding.barcelona.isChecked());
            Log.e("widged","DurumGoster :"+binding.reelMadrid.isChecked());
            Log.e("widged","DurumGoster :"+binding.checkBox.isChecked());
            Log.e("widged","DurumGoster :"+binding.switch1.isChecked());
            Log.e("widged","DurumGoster :"+binding.seekBar4.getProgress());
            Log.e("widged","Ulke : "+ulkeler.get(binding.spinner.getSelectedItemPosition()));

        });

       binding.buttonToast.setOnClickListener(view->{
           Toast.makeText(this,"ToastMassage",Toast.LENGTH_SHORT).show();
       });
        binding.buttonSnackBar.setOnClickListener(view->{
           Snackbar.make(view,"Silmek istiyormusun",Snackbar.LENGTH_LONG).setAction("evet",v->{
               Snackbar.make(v,"dsfsfd",Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).
                       setTextColor(Color.WHITE).show();
           }).setBackgroundTint(Color.WHITE).
                   setTextColor(Color.BLUE).
                   setActionTextColor(Color.RED).
                   show();
        });
        binding.buttonAlert.setOnClickListener(view->{
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("Baslik");
            ad.setMessage("Merhaba");
            ad.setPositiveButton("Tamam",(d,i)->{
                Toast.makeText(this,"tamamSecildi",Toast.LENGTH_SHORT).show();
            });
            ad.setNegativeButton("Iptal",(d,i)->{
                Toast.makeText(this,"iptal",Toast.LENGTH_SHORT).show();
            });


            ad.create().show();


        });
    }
}