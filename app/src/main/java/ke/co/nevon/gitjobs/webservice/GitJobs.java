package ke.co.nevon.gitjobs.webservice;

import java.util.List;

import ke.co.nevon.gitjobs.models.JobObject;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GitJobs {
    @GET("positions.json")
    Call<List<JobObject>>Joblist();


}
