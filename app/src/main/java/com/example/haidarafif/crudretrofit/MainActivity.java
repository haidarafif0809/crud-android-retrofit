package com.example.haidarafif.crudretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    public static final String URL = "http://192.168.43.40/crud_android/";
    private RadioButton radioSexButton;
    private ProgressDialog progress;


    @BindView(R.id.radioSesi) RadioGroup radioGroup;
    @BindView(R.id.editTextNPM) EditText editTextNPM;
    @BindView(R.id.editTextNama) EditText editTextNama;
    @BindView(R.id.editTextKelas) EditText editTextKelas;

    @OnClick(R.id.buttonDaftar) void daftar() {

        //membuat progress dialog
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

        //mengambil data dari edittext
        String npm = editTextNPM.getText().toString();
        String nama = editTextNama.getText().toString();
        String kelas = editTextKelas.getText().toString();

        int selectedId = radioGroup.getCheckedRadioButtonId();
        // mencari id radio button
        radioSexButton = (RadioButton) findViewById(selectedId);
        String sesi = radioSexButton.getText().toString();

        CrudService crud = new CrudService();
        crud.tambahMahasiswa(npm,nama,kelas,sesi, new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progress.dismiss();
                if (value.equals("1")) {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(MainActivity.this, "Terjadi Kesalahan!", Toast.LENGTH_SHORT).show();

                t.printStackTrace();
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonLihat) void lihat() {
        startActivity(new Intent(MainActivity.this, ViewActivity.class));
    }
}
