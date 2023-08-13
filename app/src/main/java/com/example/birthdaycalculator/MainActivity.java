package com.example.birthdaycalculator;

import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;

import com.example.birthdaycalculator.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
/*hello*/
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showSettings();
            return true;
        }
        else if (id == R.id.action_about) {
            showAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAbout() {
        showInfoDialog(MainActivity.this, "About Birthday Calculator",
                "Tells you exactly how old you are, years, months, and days\n" +
                        "\nAndroid app by SG, YG, and CP.");
    }

    private void showSettings() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }


    public static void showInfoDialog (Context context, String strTitle, String strMsg)
    {
        // create the listener for the dialog
        final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener ()
        {
            @Override
            public void onClick (DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        };

        // Create the AlertDialog.Builder object
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder (context);

        // Use the AlertDialog's Builder Class methods to set the title, icon, message, et al.
        alertDialogBuilder.setTitle (strTitle);
        alertDialogBuilder.setIcon (R.mipmap.ic_launcher);
        alertDialogBuilder.setMessage (strMsg);
        alertDialogBuilder.setCancelable (true);
        alertDialogBuilder.setNeutralButton (context.getString (android.R.string.ok), listener);

        // Create and Show the Dialog
        alertDialogBuilder.show ();
    }

}
