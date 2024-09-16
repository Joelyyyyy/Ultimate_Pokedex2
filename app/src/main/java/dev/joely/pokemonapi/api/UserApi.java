package dev.joely.pokemonapi.api;

import dev.joely.pokemonapi.dto.LoginRequest;
import dev.joely.pokemonapi.dto.SignupRequest;
import dev.joely.pokemonapi.dto.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("login")
    Call<TokenResponse> login(@Body LoginRequest loginRequest);

    @POST("signup")
    Call<TokenResponse> signup(@Body SignupRequest signupRequest);
}
