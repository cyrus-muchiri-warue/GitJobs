package ke.co.nevon.gitjobs.synchronize;

import ke.co.nevon.gitjobs.webservice.GitJobs;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "https://jobs.github.com/";
    private Retrofit retrofit;
    private static RetrofitClient mInstance;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public GitJobs getGitJobs() {
        return retrofit.create(GitJobs.class);
    }
}
