JAVA activity:

package com.example.mybmiio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference UI elements
        EditText etWeight = findViewById(R.id.etWeight);
        EditText etHeight = findViewById(R.id.etHeight);
        EditText etAge = findViewById(R.id.etAge);
        EditText etNationality = findViewById(R.id.etNationality);
        RadioGroup unitToggle = findViewById(R.id.unitToggle);
        RadioButton rbMetric = findViewById(R.id.rbMetric);
        TextView tvResult = findViewById(R.id.tvResult);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        // List of supported nationalities and their average BMIs in a HashMap
        HashMap<String, Double> nationalityBMIMap = new HashMap<>();
        nationalityBMIMap.put("pakistan", 23.5);
        nationalityBMIMap.put("india", 22.1);
        nationalityBMIMap.put("usa", 27.8);
        nationalityBMIMap.put("china", 24.4);
        nationalityBMIMap.put("uk", 25.0);
        nationalityBMIMap.put("canada", 26.0);
        nationalityBMIMap.put("australia", 26.1);
        nationalityBMIMap.put("germany", 24.8);
        nationalityBMIMap.put("france", 23.9);
        nationalityBMIMap.put("japan", 22.5);
        nationalityBMIMap.put("south korea", 22.8);
        nationalityBMIMap.put("brazil", 27.5);
        nationalityBMIMap.put("mexico", 28.0);
        nationalityBMIMap.put("italy", 24.0);
        nationalityBMIMap.put("turkey", 27.1);
        nationalityBMIMap.put("spain", 26.3);
        nationalityBMIMap.put("saudi arabia", 26.5);
        nationalityBMIMap.put("south africa", 27.3);
        nationalityBMIMap.put("egypt", 28.1);
        nationalityBMIMap.put("nigeria", 25.6);
        nationalityBMIMap.put("argentina", 26.8);
        nationalityBMIMap.put("indonesia", 23.0);
        nationalityBMIMap.put("malaysia", 25.0);
        nationalityBMIMap.put("thailand", 23.6);
        nationalityBMIMap.put("vietnam", 22.2);
        nationalityBMIMap.put("bangladesh", 24.1);
        nationalityBMIMap.put("poland", 26.2);
        nationalityBMIMap.put("netherlands", 24.5);
        nationalityBMIMap.put("colombia", 26.7);
        nationalityBMIMap.put("sweden", 25.3);
        nationalityBMIMap.put("norway", 24.9);
        nationalityBMIMap.put("denmark", 24.8);
        nationalityBMIMap.put("finland", 25.2);
        nationalityBMIMap.put("chile", 26.0);
        nationalityBMIMap.put("peru", 24.7);
        nationalityBMIMap.put("greece", 25.1);
        nationalityBMIMap.put("philippines", 24.3);
        nationalityBMIMap.put("iran", 26.4);
        nationalityBMIMap.put("iraq", 27.2);
        nationalityBMIMap.put("morocco", 25.4);
        nationalityBMIMap.put("kenya", 23.7);
        nationalityBMIMap.put("ethiopia", 23.8);
        nationalityBMIMap.put("portugal", 24.5);
        nationalityBMIMap.put("belgium", 24.7);
        nationalityBMIMap.put("ukraine", 25.9);
        nationalityBMIMap.put("venezuela", 27.0);
        nationalityBMIMap.put("new zealand", 26.3);
        nationalityBMIMap.put("switzerland", 24.9);

        // Set up button click listener
        btnCalculate.setOnClickListener(v -> {
            // Get user inputs
            String weightInput = etWeight.getText().toString().trim();
            String heightInput = etHeight.getText().toString().trim();
            String ageInput = etAge.getText().toString().trim();
            String nationalityInput = etNationality.getText().toString().trim().toLowerCase(); // Convert input to lowercase

            // Validate inputs
            if (weightInput.isEmpty() || heightInput.isEmpty() || ageInput.isEmpty() || nationalityInput.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            double weight = Double.parseDouble(weightInput);
            double height = Double.parseDouble(heightInput);
            double bmi;

            // Determine selected unit and calculate BMI
            if (rbMetric.isChecked()) {
                // Metric: weight in kg, height in meters
                bmi = weight / (height * height);
            } else {
                // Imperial: weight in lbs, height in inches
                bmi = (weight * 703) / (height * height);
            }

            // Determine BMI category
            String bmiCategory;
            if (bmi < 18.5) {
                bmiCategory = "Underweight";
            } else if (bmi < 24.9) {
                bmiCategory = "Normal weight";
            } else if (bmi < 29.9) {
                bmiCategory = "Overweight";
            } else {
                bmiCategory = "Obesity";
            }

            // Check if nationality is supported
            if (!nationalityBMIMap.containsKey(nationalityInput)) {
                tvResult.setText(String.format("Your BMI: %.2f\nCategory: %s\n\nSorry, we don't have sufficient data for %s.",
                        bmi, bmiCategory, nationalityInput));
                return;
            }

            // Get average BMI for the user's nationality
            double averageBMI = nationalityBMIMap.get(nationalityInput);
            String comparison = compareBmiWithNationality(bmi, averageBMI);

            // Display BMI, category, nationality comparison, and recommendation
            String recommendation = getRecommendation(bmi);
            tvResult.setText(String.format(
                    "Your BMI: %.2f\nCategory: %s\n\nBased on your nationality (%s), we recommend:\n%s\n\nComparison: %s",
                    bmi, bmiCategory, nationalityInput, recommendation, comparison
            ));
        });
    }

    // Method to generate recommendations based on BMI
    private String getRecommendation(double bmi) {
        if (bmi < 18.5) {
            return "Increase your calorie intake with a balanced diet. Include more protein, healthy fats, and carbohydrates.";
        } else if (bmi < 24.9) {
            return "You are in the healthy range. Maintain your current lifestyle and stay active.";
        } else if (bmi < 29.9) {
            return "Adopt a healthy, calorie-controlled diet. Incorporate regular physical activity such as walking or jogging.";
        } else {
            return "Focus on a nutrient-rich, low-calorie diet. Engage in consistent exercise and consider consulting a dietitian.";
        }
    }

    // Method to compare user's BMI with the average BMI for the same nationality
    private String compareBmiWithNationality(double bmi, double averageBMI) {
        if (bmi < averageBMI) {
            return "Your BMI is below the average for people from your nationality.";
        } else if (bmi > averageBMI) {
            return "Your BMI is above the average for people from your nationality.";
        } else {
            return "Your BMI is equal to the average for people from your nationality.";
        }
    }
}


