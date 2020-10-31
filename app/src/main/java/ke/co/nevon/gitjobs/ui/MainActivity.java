package ke.co.nevon.gitjobs.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ke.co.nevon.gitjobs.R;
import ke.co.nevon.gitjobs.adapters.JoblistAdapter;
import ke.co.nevon.gitjobs.models.JobObject;
import ke.co.nevon.gitjobs.synchronize.RetrofitClient;
import ke.co.nevon.gitjobs.webservice.GitJobs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static ke.co.nevon.gitjobs.synchronize.RetrofitClient.BASE_URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Retrofit retrofit;
    RecyclerView recyclerView;
    JoblistAdapter joblistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);






    }

    @Override
    protected void onResume() {
        super.onResume();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitJobs gitJobs= retrofit.create(GitJobs.class);
        Call<List<JobObject>>call=gitJobs.Joblist();
        call.enqueue(new Callback<List<JobObject>>() {
            @Override
            public void onResponse(Call<List<JobObject>> call, Response<List<JobObject>> response) {
                if (!response.isSuccessful())
                {
                    return;
                }

                List<JobObject>jobObjects=response.body();
                joblistAdapter=new JoblistAdapter(jobObjects);


            }

            @Override
            public void onFailure(Call<List<JobObject>> call, Throwable t) {

                Log.d(TAG, "onFailure: "+t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(joblistAdapter);
        recyclerView.setHasFixedSize(true);

    }
}
