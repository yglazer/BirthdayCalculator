package com.example.birthdaycalculator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.birthdaycalculator.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Locale;

import kotlin.Triple;

/*hello*/
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EditText etMonth, etDay, etYear;
    private Snackbar snackBar;
    private BDayCalc mBDayCalc; // Model Birthday Calculator

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        setupToolbar();
        setupFAB();
        setupFields();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
    }

    private void setupFields() {
        etMonth = findViewById(R.id.et_month);
        etDay = findViewById(R.id.et_day);
        etYear = findViewById(R.id.et_year);
        View layoutMain = findViewById(R.id.activity_main);
        snackBar = Snackbar.make(layoutMain, "", Snackbar.LENGTH_LONG);
    }
    private void setupFAB() {
        binding.fab.setOnClickListener(view -> handleFABClick());
    }

    private void handleFABClick() {
        // Variable that can hold 3 different variables. Puts the day, month and year together
        Triple< String, String, String> monthDayYear = new Triple<>(
                etMonth.getText().toString(),
                etDay.getText().toString(),
                etYear.getText().toString());
        // If the data is valid, then display it
        if (isValidFormData(monthDayYear)){
            setModelFields(monthDayYear);
            String msg = generateFormattedStringFromModel();
            showMessageWithLinkToResultsActivity(msg);
        }
        // If the data is not valid, display a toast that the data is not valid
        else {
            Toast.makeText(getApplicationContext(), R.string.error_msg, Toast.LENGTH_SHORT).show();
        }
    }

    // Actually shows the data, in a snackbar
    private void showMessageWithLinkToResultsActivity(String msg) {
        snackBar.setText(msg);
        snackBar.show();
    }

    // Calculates the age and generates it into a formatted string
    private String generateFormattedStringFromModel() {
        final int yearsOld, monthsOld, daysOld;
        yearsOld = mBDayCalc.calcYears();
        monthsOld = mBDayCalc.calcMonths();
        daysOld = mBDayCalc.calcDays();

        return String.format(Locale.getDefault(),
                "%s %d %s %d %s %d %s",
                getString(R.string.you_are), yearsOld, getString(R.string.years),
                monthsOld, getString(R.string.months), daysOld, getString(R.string.days_old));
    }

    // Sets the fields to be what the user inputted into the app
    private void setModelFields(Triple<String, String, String> monthDayYear) {
            assert monthDayYear.getFirst() != null;
            assert monthDayYear.getSecond() != null;
            assert monthDayYear.getThird() != null;

            int month = Integer.parseInt(monthDayYear.getFirst());
            int day = Integer.parseInt(monthDayYear.getSecond());
            int year = Integer.parseInt(monthDayYear.getThird());

            if (mBDayCalc == null)
                mBDayCalc = new BDayCalc(month, day, year);
            else
            {
                mBDayCalc.setMonth(month);
                mBDayCalc.setDay(day);
                mBDayCalc.setYear(year);
            }
        }

    private boolean isValidFormData(Triple<String, String, String> monthDayYear) {
        LocalDate date = LocalDate.now();
        String month = monthDayYear.getFirst();
        String day = monthDayYear.getSecond();
        String year = monthDayYear.getThird();

        // Return true if it's a valid date
        return month != null && day != null && year != null &&
                month.length() > 0 && day.length() > 0 && year.length() > 0
                && Integer.parseInt(month) > 0 && Integer.parseInt(month) <= 12
                && Integer.parseInt(day) > 0 && dayIsValid(monthDayYear)
                && Integer.parseInt(year) <= date.getYear();
    }

    // Validates that the day is valid considering how many days are in that month
    private boolean dayIsValid(Triple<String, String, String> monthDayYear) {
        int month = Integer.parseInt(monthDayYear.getFirst());
        int day = Integer.parseInt(monthDayYear.getSecond());
        if (month == 2 && day <= 29){
            return true;
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30) {
            return true;
        } else return (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 ||
                month == 10 || month == 12) && day <= 31;
    }

    private void setContentView() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
