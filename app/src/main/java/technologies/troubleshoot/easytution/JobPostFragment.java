package technologies.troubleshoot.easytution;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static technologies.troubleshoot.easytution.LoginActivity.KEY_EMAIL;

/**
 * Created by kaizer on 2/2/17.
 */

public class JobPostFragment extends Fragment {

    public static final String DATA_URL = "http://tuition.troubleshoot-tech.com/post.php";
    public static final String JSON_ARRAY = "job_post";
    public static String STATUS_TIME = "date_time";
    public static String ADDITIONAL_INFO = "content";

    public static String TITLE = "title";
    public static String DAYS_IN_WEEK = "days_in_week";
    public static String PREFERRED_TEACHER_GENDER = "preferred_teacher_gender";
    public static String CLASS = "student_class";
    public static String PREFERRED_MEDIUM = "preferred_medium";
    public static String SALARY = "salary";

    public JobPostFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.student_post_layout, container, false);

        postStatus("kaizer@gmail.com", "content", "1st Job Post", "1", "male", "A-level", "English", "5000");

        return rootView;
    }

    private void postStatus(final String email, final String content, final String title, final String days_in_week, final String preferred_teacher_gender, final String student_class, final String preferred_medium, final String salary) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), "Status Updated : Thug LiFe", Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Unable to Post", Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_EMAIL, email);
                params.put(ADDITIONAL_INFO, content);
                params.put(TITLE, title);
                params.put(DAYS_IN_WEEK, days_in_week);
                params.put(PREFERRED_TEACHER_GENDER, preferred_teacher_gender);
                params.put(CLASS, student_class);
                params.put(PREFERRED_MEDIUM, preferred_medium);
                params.put(SALARY, salary);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
