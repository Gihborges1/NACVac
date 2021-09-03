package com.example.vacina;

import android.database.Cursor;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public DataBaseManager db;
    public ArrayList<Paciente> pacientes = new ArrayList<>();
    public Integer idAtual = 0;
    public Integer totalPacientes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = new DataBaseManager(this,"base",null,1);
        startDatabase();
        populaTela();
    }

    public void startDatabase(){
        this.db.inserirPaciente(01,"CORONAVAC","05102021","UBS SANTO AMARO","2 DOSE");
        this.db.inserirPaciente(02,"ASTRAZENECA","10062021","UBS VILA PRUDENTE","1 DOSE");
        this.db.inserirPaciente(03,"JANSSEN","06082021","UBS CAMBUCI","DOSE UNICA");
    }

    public void populaTela(){
        Cursor c = db.listaPacientes();

        if (c.getCount() > 0){
            c.moveToFirst();
            totalPacientes = c.getCount();
            idAtual = 0;

            pacientes.clear();

            do{
                Paciente pac = new Paciente();
                pac.setId(c.getInt(c.getColumnIndex("ID")));
                pac.setVacina(c.getString(c.getColumnIndex("VACINA")));
                pac.setData_vac(c.getColumnName(c.getColumnIndex("DATA_VAC")));
                pac.setUnidade(c.getColumnName(c.getColumnIndex("UNIDADE")));
                pac.setDose(c.getColumnName(c.getColumnIndex("DOSE")));

                pacientes.add(pac);

            }while(c.moveToNext());
        }
    }
    public void showProximo(View view){
        EditText id = findViewById(R.id.id);
        EditText vacina = findViewById(R.id.vacina);
        EditText data_vac = findViewById(R.id.data_vac);
        EditText unidade = findViewById(R.id.unidade);
        EditText dose = findViewById(R.id.dose);

        Paciente p = pacientes.get(idAtual);

        id.setText(p.getId().toString());
        vacina.setText(p.getVacina());
        data_vac.setText(p.getData_vac());
        unidade.setText(p.getUnidade());
        dose.setText(p.getDose());

        idAtual++;

        if(idAtual == totalPacientes) idAtual = 0;

    }

    public void atualizar(View view){
        EditText id = findViewById(R.id.id);
        EditText vacina = findViewById(R.id.vacina);
        EditText data_vac = findViewById(R.id.data_vac);
        EditText unidade = findViewById(R.id.unidade);
        EditText dose = findViewById(R.id.dose);

        this.db.atualizarPaciente(new Integer(id.getText().toString()), vacina.getText().toString(),data_vac.getText().toString(),unidade.getText().toString(), dose.getText().toString());
        populaTela();

    }

    public void apagar(View view){
        EditText id = findViewById(R.id.id);

        this.db.deletarPaciente(id.getText().toString());
        populaTela();
    }

    public void novo(View view){
        EditText id = findViewById(R.id.id);
        EditText vacina = findViewById(R.id.vacina);
        EditText data_vac = findViewById(R.id.data_vac);
        EditText unidade = findViewById(R.id.unidade);
        EditText dose = findViewById(R.id.dose);

        id.setText((new Integer(totalPacientes+1)).toString());
        vacina.setText("");
        data_vac.setText("");
        unidade.setText("");
        dose.setText("");

    }

    public void inserir(View view){
        EditText id = findViewById(R.id.id);
        EditText vacina = findViewById(R.id.vacina);
        EditText data_vac = findViewById(R.id.data_vac);
        EditText unidade = findViewById(R.id.unidade);
        EditText dose = findViewById(R.id.dose);

        this.db.inserirPaciente(new Integer(id.getText().toString()), vacina.getText().toString(),data_vac.getText().toString(),unidade.getText().toString(), dose.getText().toString());
        populaTela();

    }

}
