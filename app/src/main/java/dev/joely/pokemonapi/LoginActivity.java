package dev.joely.pokemonapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import dev.joely.pokemonapi.api.ApiClient;
import dev.joely.pokemonapi.api.UserApi;
import dev.joely.pokemonapi.dto.LoginRequest;
import dev.joely.pokemonapi.dto.SignupRequest;
import dev.joely.pokemonapi.dto.TokenResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton, signupButton;
    private UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);

        userApi = ApiClient.getClient().create(UserApi.class);

        loginButton.setOnClickListener(v -> login());
        signupButton.setOnClickListener(v -> signup());
    }

    private void login() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        userApi.login(new LoginRequest(username, password)).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    String token = response.body().getAccessToken();
                    // Store the token in shared preferences or another storage
                    navigateToMainActivity();
                } else {
                    // Handle login failure
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void signup() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        userApi.signup(new SignupRequest(username, password)).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    String token = response.body().getAccessToken();
                    // Store the token in shared preferences or another storage
                    navigateToMainActivity();
                } else {
                    // Handle signup failure
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
