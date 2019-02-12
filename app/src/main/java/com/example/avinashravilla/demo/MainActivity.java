package com.example.avinashravilla.demo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.avinashravilla.demo.RestClient.RestClient;
import com.example.avinashravilla.demo.RestClient.Services;
import com.example.avinashravilla.demo.model.JokeResponse;
import com.example.avinashravilla.demo.model.Value;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt_author)
    TextView txtAuthor;

    @BindView(R.id.txt_joke)
    TextView txtJoke;

    @BindView(R.id.edt_id)
    EditText edtID;

    private ProgressDialog progress;

    private int getJokeRetryCount = 3;

    @OnClick(R.id.btn)
    void getNewJoke() {
        getJoke();
    }

    private void getJoke() {
        String id = edtID.getText().toString();


        showProgress();
        Services service = RestClient.getInstance().getServices();

        if (!TextUtils.isEmpty(id)) {
            service.getJokeByID(id, new Callback<JokeResponse>() {
                @Override
                public void success(JokeResponse jokeResponse, Response response) {
                    validateJokeResponse(jokeResponse);
                }

                @Override
                public void failure(RetrofitError error) {
                    handleFailure(error);
                }
            });
        } else {
            service.getJoke(new Callback<JokeResponse>() {
                @Override
                public void success(JokeResponse jokeResponse, Response response) {
                    validateJokeResponse(jokeResponse);
                }

                @Override
                public void failure(RetrofitError error) {
                    handleFailure(error);
                }
            });
        }

    }

    private void handleFailure(RetrofitError error) {
        if (getJokeRetryCount > 0) {
            getJokeRetryCount--;
            getJoke();
        } else {
            getJokeRetryCount = 3;
            Toast.makeText(MainActivity.this, getResources().getString(R.string.error_getting_joke), Toast.LENGTH_LONG).show();
            dismissProgress();
        }
    }

    private void validateJokeResponse(JokeResponse jokeResponse) {
        getJokeRetryCount = 3;
        if (jokeResponse != null && jokeResponse.getValue() != null) {
            Value value = jokeResponse.getValue();
            if (!TextUtils.isEmpty(value.getAuthor()) && !TextUtils.isEmpty(value.getJoke())) {
                setJokeToUi(value);
            } else {

            }
        } else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.failed_to_get_joke), Toast.LENGTH_LONG).show();
        }

        dismissProgress();
    }

    private void setJokeToUi(Value value) {
        txtAuthor.setText(value.getAuthor());
        txtJoke.setText(value.getJoke());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getJoke();
    }

    public void showProgress() {
        if (progress != null) {
            progress.show();
        } else {
            progress = new ProgressDialog(this);
            progress.setMessage(getResources().getString(R.string.loading_joke));
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.show();
        }
    }

    public void dismissProgress() {
        if (progress != null) {
            progress.dismiss();
        }
    }


}
