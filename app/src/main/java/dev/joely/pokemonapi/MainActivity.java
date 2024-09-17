package dev.joely.pokemonapi;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import dev.joely.pokemonapi.fragments.FavoritesFragment;
import dev.joely.pokemonapi.fragments.PokemonListFragment;
import dev.joely.pokemonapi.fragments.TrainerFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.nav_pokemon_list) {
                    selectedFragment = new PokemonListFragment();
                } else if (item.getItemId() == R.id.nav_favorites) {
                    selectedFragment = new FavoritesFragment();
                } else if (item.getItemId() == R.id.nav_trainer) {
                    selectedFragment = new TrainerFragment();
                }

                if (selectedFragment != null) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                }
                return true;
            }
        });

        // Load default fragment
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_pokemon_list); // Load default fragment
        }
    }
}
