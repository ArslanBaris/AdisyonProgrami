package cbu.httf.adisyonprogram.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cbu.httf.adisyonprogram.Adapters.TablesMenuAdapter;
import cbu.httf.adisyonprogram.Fragment.Table.TableAddFragment;
import cbu.httf.adisyonprogram.Fragment.Table.TableUpdateFragment;
import cbu.httf.adisyonprogram.Network.Service;
import cbu.httf.adisyonprogram.R;
import cbu.httf.adisyonprogram.data.model.TablesModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TablesActivity extends AppCompatActivity {

    private GridView gridView;
    public  String takentoken;
    private TablesMenuAdapter tablesMenuAdapter;

    private void init(){
       gridView=(GridView)findViewById(R.id.tables_gridView);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        Intent takenIntent = getIntent();
        takentoken=takenIntent.getStringExtra("token");

        init();

        getTables();

    }

    private void getTables() {
        Call<List<TablesModel>> tablesModelCall= Service.getServiceApi().getTables(takentoken);
        tablesModelCall.enqueue(new Callback<List<TablesModel>>() {
            @Override
            public void onResponse(Call<List<TablesModel>> call, Response<List<TablesModel>> response) {
                if(response.isSuccessful()){
                    ArrayList<TablesModel> tablesModels = new ArrayList<>();
                    tablesModels =(ArrayList<TablesModel>) response.body();
                    initTables(tablesModels);

                }else{
                    Toast.makeText(TablesActivity.this, "Request failed. "+response.code() , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<TablesModel>> call, Throwable t) {
                Toast.makeText(TablesActivity.this, "Request failed. "+t.getMessage() , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initTables(ArrayList<TablesModel> tablesModel){
        TablesMenuAdapter tablesMenuAdapter=new TablesMenuAdapter(tablesModel,this);
        gridView.setAdapter(tablesMenuAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(TablesActivity.this, "Yıklama" , Toast.LENGTH_LONG).show();
            }
        });
    }
}